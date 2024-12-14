import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = "src/text.txt"; // Путь к входному файлу
        String outputFilePath = "src/text1.txt"; // Путь к выходному файлу

        try {
            // Чтение файла и сбор слов
            String content = new String(Files.readAllBytes(Paths.get(inputFilePath)));
            String[] words = content.split("[\\W_]+"); // Разделение по знакам препинания и пробелам

            // Подсчет частоты слов
            Map<String, Integer> wordCount = new HashMap<>();
            for (String word : words) {
                if (!word.isEmpty()) {
                    wordCount.put(word.toLowerCase(), wordCount.getOrDefault(word.toLowerCase(), 0) + 1);
                }
            }

            // Сортировка слов
            List<String> sortedWords = new ArrayList<>(wordCount.keySet());
            Collections.sort(sortedWords);

            // Запись отсортированных слов и их частоты в файл
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                for (String word : sortedWords) {
                    writer.write(word + ": " + wordCount.get(word));
                    writer.newLine();


                    System.out.println("Слова и их частота:");
                        System.out.println(word + ": " + wordCount.get(word));
                }

                // Нахождение слов с максимальной частотой
                int maxFrequency = Collections.max(wordCount.values());
                List<String> mostFrequentWords = new ArrayList<>();
                for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                    if (entry.getValue() == maxFrequency) {
                        mostFrequentWords.add(entry.getKey());
                        System.out.println("Слово(а) с максимальной частотой (" + maxFrequency + "): " + String.join(", ", mostFrequentWords));
                    }
                }

                // Вывод слов с максимальной частотой

                writer.write("Слово(а) с максимальной частотой (" + maxFrequency + "): " + String.join(", ", mostFrequentWords));
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}