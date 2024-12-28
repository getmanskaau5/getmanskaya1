package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/app/v1")
public class MyController {

    @GetMapping("/getRequest")
    public ResponseEntity<?> getRequest(@RequestParam int id, @RequestParam String name) {
        // Проверка условий
        if (id <= 10) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка: id должен быть больше 10");
        }
        if (name.length() <= 5) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка: длина имени должна быть больше 5");
        }

        // Задержка
        try {
            if (id > 10 && id < 50) {
                Thread.sleep(1000);
            } else {
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


        // Формирование JSON ответа
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("name", name);
        return ResponseEntity.ok(response);


    }

    @PostMapping("/postRequest")
    public ResponseEntity<?> postRequest(@RequestBody Person requestBody) throws IOException {
        // Проверка условий
        if (requestBody.getName() == null || requestBody.getName().isEmpty() ||
                requestBody.getSurname() == null || requestBody.getSurname().isEmpty() ||
                requestBody.getAge() <= 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка: все поля должны быть заполнены");
        }

        // Формирование JSON ответа

        String postAnswer = new String(Files.readAllBytes(
                Paths.get("src/main/resources/postRequest.txt")));
        String response = postAnswer
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



