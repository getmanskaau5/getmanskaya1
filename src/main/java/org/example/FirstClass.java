package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;




@RestController
@RequestMapping("/app/v1")
public class FirstClass {


    @GetMapping("/getRequest")
    public ResponseEntity<?> handleGetRequest(
            @RequestParam("id") int id,
            @RequestParam("name") String name)
            throws IOException,InterruptedException  {

        // Проверка условий
        if (id <= 10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("InternalServerError: Invalid parameters");
        }
        if (name.length() <5) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("InternalServerError: Invalid parameters");
        }
        // Задержка в зависимости от значения id
        if (id > 10 && id < 50) {
            Thread.sleep(1000); // 1000 мс
        } else {
            Thread.sleep(500); // 500 мс
        }
        // Чтение содержимого файла getRequest.txt
        String getRequest = new String(Files.readAllBytes(Paths.get("src/main/resources/getRequest.txt")));
        String response = getRequest
                .replace("{name}", name)
                .replace("{id}", String.valueOf(id));
        return ResponseEntity.ok(response);


    }
    @PostMapping("/postRequest")
    public ResponseEntity<String> handlePostRequest(
            @RequestBody Person requestBody)
            throws IOException {
        // Проверка условий
        if (requestBody.getName() == null || requestBody.getName().isEmpty() ||
                requestBody.getSurname() == null || requestBody.getSurname().isEmpty() ||
                requestBody.getAge() == null || requestBody.getAge() == 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "InternalServerError: Invalid body");
        }
        String postAnswer = new String(Files.readAllBytes(
                Paths.get("src/main/resources/postRequest.txt")));
        String response = postAnswer
                //Person1
                .replace("{name}", requestBody.getName())
                .replace("{surname}", requestBody.getSurname())
                .replace("{age}", String.valueOf(requestBody.getAge()))
                //Person2
                .replace("{name2}", requestBody.getSurname())
                .replace("{surname2}", requestBody.getName())
                .replace("{age_double}", String.valueOf(requestBody.getAge() * 2));


        return ResponseEntity.ok(response);


    }
}