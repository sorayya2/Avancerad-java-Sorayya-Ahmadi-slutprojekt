# Avancerad-java-Sorayya-Ahmadi-slutprojekt

# To-Do List Applikation

En enkel To-Do List-applikation som hanterar uppgifter genom ett backend-API och en frontend byggd med JavaFX. Projektet syftar till att visa hur frontend och backend kommunicerar via API och hanterar data i minnet.

---

## Funktionalitet

Applikationen tillhandahåller följande funktioner:
- **Visa alla uppgifter:** Hämta och visa en lista med alla uppgifter.
- **Lägg till en uppgift:** Skapa en ny uppgift och lägg till den i listan.
- **Redigera en uppgift:** Uppdatera informationen för en befintlig uppgift.
- **Ta bort en uppgift:** Radera en uppgift från listan.

---

## Teknisk Stack

- **Backend:** 
  - Java Spring Boot
  - API-endpoints för CRUD-operationer (`GET`, `POST`, `PUT`, `DELETE`)
  - Lagring av uppgifter i minnet med hjälp av en Java-lista.

- **Frontend:** 
  - JavaFX för användargränssnitt.
  - Kommunikation med backend via HTTP-anrop.
  - Dynamisk uppdatering av användargränssnittet baserat på serverns svar.

---

## Installation och Körning

### Backend
1. **Förutsättningar:**
   - Java 17 eller senare installerat.
   - En IDE som IntelliJ IDEA eller Eclipse.

2. **Körning av server:**
   - Klona projektet eller ladda ner källkoden.
   - Navigera till backend-mappen i projektet.
   - Kör följande kommando för att starta servern:
     ```bash
     ./mvnw spring-boot:run
     ```
   - API-servern startar på `http://localhost:8080`.

### Frontend
1. **Förutsättningar:**
   - JavaFX installerat och konfigurerat.

2. **Körning av klienten:**
   - Navigera till frontend-koden i projektet.
   - Kör `Main.java` i din IDE för att starta applikationen.

---

## Användning

1. När applikationen startar visas en lista över alla uppgifter som hämtas från backend via ett `GET /tasks`-anrop.
2. **Lägg till en uppgift:**
   - Klicka på knappen "Lägg till uppgift" i frontend och fyll i informationen.
   - Detta skickar ett `POST /tasks`-anrop till backend.
3. **Redigera en uppgift:**
   - Välj en uppgift i listan och klicka på "Redigera". Uppdatera informationen och spara.
   - Detta skickar ett `PUT /tasks/{id}`-anrop.
4. **Ta bort en uppgift:**
   - Markera en uppgift och klicka på "Ta bort".
   - Detta skickar ett `DELETE /tasks/{id}`-anrop.

---

## Projektstruktur

```plaintext
project/
│
├── backend/
│   ├── src/main/java/
│   │   ├── com.example.todo/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── model/
│   │   ├── Application.java
│   └── pom.xml
│
├── frontend/
│   ├── src/
│   │   ├── Main.java
│   │   ├── controllers/
│   │   ├── views/
│   └── resources/
