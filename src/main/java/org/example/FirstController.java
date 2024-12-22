package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/v1")
public class FirstController {

    @GetMapping("/getRequest")
    public ResponseEntity<String> getRequest(@RequestParam int id, @RequestParam String name) {
        if (id <= 10 || name.length() <= 5) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid parameters");
        }
        // Логика обработки запроса
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/postRequest")
    public ResponseEntity<String> postRequest(@RequestBody Person person) {
        if (person.getName() == null || person.getSurname() == null || person.getAge() <= 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid parameters");
        }
        // Логика обработки запроса
        return ResponseEntity.ok("Success");
    }
}