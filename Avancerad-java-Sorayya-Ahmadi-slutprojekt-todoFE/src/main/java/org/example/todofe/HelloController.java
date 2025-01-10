package org.example.todofe;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HelloController {

    @FXML
    private TextField input_taskId;

    @FXML
    private TextField input_description;

    @FXML
    private TextField input_taskName;

    @FXML
    private TextArea textArea_allTasks;

    @FXML
    void addNewTask(ActionEvent event) {
        try {
            int taskId = Integer.parseInt(input_taskId.getText());
            String taskName = input_taskName.getText();
            String description = input_description.getText();

            Todo myTodo = new Todo(taskId, taskName, description);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(myTodo);
            System.out.println("json: " + json);

            URL url = new URL("http://localhost:8090/api/todo");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input);
            }

            String response = readResponse(connection);
            textArea_allTasks.setText(response);
        } catch (Exception e) {
            textArea_allTasks.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }



    @FXML
    void deleteTask() {
        try {
            int taskId = Integer.parseInt(input_taskId.getText());

            URL url = new URL("http://localhost:8090/api/todo/" + taskId);
            System.out.println("delete id " + taskId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");


            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String response = readResponse(connection);
                textArea_allTasks.setText("Tasks deleted" + response);
            } else {
                textArea_allTasks.setText("failed to delete" + responseCode);
            }

        } catch (Exception e) {
            textArea_allTasks.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @FXML
    void getAllTasks() {
        try {
            URL url = new URL("http://localhost:8090/api/todo");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            String response = readResponse(connection);
            textArea_allTasks.setText(response);

        } catch (Exception e) {
            textArea_allTasks.setText("Error" + e.getMessage());
        }
    }

   @FXML
    void editTask(ActionEvent event) {
        try {
            int taskId = Integer.parseInt(input_taskId.getText());
            String taskName = input_taskName.getText();
            String description = input_description.getText();

            Todo myTodo = new Todo(taskId, taskName, description);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(myTodo);
            System.out.println("json: " + json);

            URL url = new URL("http://localhost:8090/api/todo/" + taskId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input);
            }


            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String response = readResponse(connection);
                textArea_allTasks.setText("Task updated" + response);
            } else {
                textArea_allTasks.setText("failed to delete" + responseCode);
            }

        } catch (Exception e) {
            textArea_allTasks.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


        private String readResponse(HttpURLConnection connection) throws IOException {
        BufferedReader reader;
        if (connection.getResponseCode() >= 200 && connection.getResponseCode() < 300) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }



}