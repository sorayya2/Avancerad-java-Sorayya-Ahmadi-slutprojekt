package com.example.todo.Controllers;

import com.example.todo.Models.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private List<Todo> todos = new ArrayList<>();

    public TodoController() {
        todos.add(new Todo(1, "besök läkare", "läkare besök 5 Jan"));
        todos.add(new Todo(2, "handla mat", "Handla mat for hem"));
    }

    @GetMapping
    public List<Todo> getAllTasks() {
        return todos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTaskId(@PathVariable int id) {
        System.out.println("get called: "+ todos.size());
        return todos.stream()
                .filter(todo -> todo.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Todo> getTaskByName(@PathVariable String name) {
        return todos.stream()
                .filter(todo -> todo.getTitle().equalsIgnoreCase(name))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Todo> addTask(@RequestBody Todo todo) {

        System.out.println("add TAk called: "+todo.getId());
        if (todo != null && todo.getId() > 0) {
            todos.add(todo);
            return ResponseEntity.status(HttpStatus.CREATED).body(todo);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTask(@PathVariable int id, @RequestBody Todo updatedTodo) {
        for (Todo todo : todos){
            if(todo.getId() == id){
                todo.setTitle(updatedTodo.getTitle());
                todo.setDescription(updatedTodo.getDescription());
                return ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        boolean removed = false;
        for (int i = 0; i < todos.size(); i++) {
           if (todos.get(i).getId() == id) {
               todos.remove(i);
               removed = true;
           }
        }
        if(removed){
            return ResponseEntity.ok("Book with ID" + id + " has been deleted.");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID" + id + " NOT FOUND.");
        }
    }
}
