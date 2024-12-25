//package org.example;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RequestBody;
//
//
//
//@RestController
//@RequestMapping("/app/v1")
//public class FirstController {
//
//
//    @GetMapping("/getRequest")
//    public ResponseEntity<?> getRequest(@RequestParam int id, @RequestParam String name) {
//        // Проверка условий
//        if (id <= 10) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Ошибка: id должен быть больше 10");
//        }
//        if (name.length() < 5) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Ошибка: длина имени должна быть больше 5");
//        }
//
//        // Формирование JSON-ответа
//        String jsonResponse = String.format("{\"id\": %d, \"name\": \"%s\"}", id, name);
//
//        // Задержка в зависимости от id
//        try {
//            if (id > 10 && id < 50) {
//                Thread.sleep(1000); // 1000 мс
//            } else {
//                Thread.sleep(500); // 500 мс
//            }
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//
//        return ResponseEntity.ok(jsonResponse);
//    }
//
//
//@PostMapping("/postRequest")
//public ResponseEntity<String> handlePostRequest(@RequestBody Person requestBody) {
//    // Проверка на пустые поля
//    if (requestBody.getName() == null || requestBody.getSurname() == null || requestBody.getAge() <= 0) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("{\"ошибка\": \"Пустое поле\"}");
//    }
//
//    // Формирование ответа
//    String jsonResponse = String.format("{\"Person1\":{\"name\":\"%s\",\"surname\":\"%s\",\"age\":%d}," +
//                    "\"Person2\":{\"name2\":\"%s\",\"surname2\":\"%s\",\"age_double\":%d}}",
//            requestBody.getName(), requestBody.getSurname(), requestBody.getAge(),
//            requestBody.getSurname(), requestBody.getName(), requestBody.getAge() * 2);
//
//    return ResponseEntity.ok(jsonResponse);
//}
//}
//
