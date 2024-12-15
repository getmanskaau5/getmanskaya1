import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    private static Object maxFrequencyPercentage;

    public static void main(String[] args) {
        String inputFilePath = "src/text.txt"; // Путь к входному файлу
        String outputFilePath = "src/text1.txt"; // Путь к выходному файлу

        try {
            // Чтение файла и извлечение слов
            String content = new String(Files.readAllBytes(Paths.get(inputFilePath)));
            String[] words = content.split("[\\W_]+"); // Разделение по не буквенно-цифровым символам
            Map<String, Integer> wordCount = new HashMap<>();

            // Подсчет частоты слов
            for (String word : words) {
                if (!word.isEmpty() && !word.matches(".*\\d.*")) { // Исключение слов с цифрами
                    word = word.toLowerCase(); // Приведение к нижнему регистру
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                }
            }

            // Сортировка слов
            List<String> sortedWords = new ArrayList<>(wordCount.keySet());
            Collections.sort(sortedWords);

            // Нахождение максимальной частоты
            int maxFrequency = Collections.max(wordCount.values());
            List<String> mostFrequentWords = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                if (entry.getValue() == maxFrequency) {
                    mostFrequentWords.add(entry.getKey());
                }
            }

            // Вычисление процента
            int totalWords = Arrays.stream(words).filter(word -> !word.isEmpty() && !word.matches(".*\\d.*")).map(String::toLowerCase).toArray().length;
            double maxFrequencyPercentage = (double) maxFrequency / totalWords * 100;


            // Вывод результатов на экран и запись в файл
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                System.out.println("Слово\tЧастота");
                for (String word : sortedWords) {
                    int count = wordCount.get(word);
                    double frequencyPercentage = (count / (double) words.length) * 100;
                    System.out.println(word + ": " + wordCount.get(word));
                    System.out.printf("%s\t%.2f%%\n", word, frequencyPercentage);
                    writer.write(": " + wordCount.get(word));
                    writer.write(String.format("%s\t%.2f%%\n", word, frequencyPercentage));
                }
                System.out.println("Слово(а) с максимальной частотой: " + mostFrequentWords);
                System.out.println("Частота: " + maxFrequency);
                System.out.println("Процент: " + String.format("%.2f", maxFrequencyPercentage) + "%");
                writer.write("Слово(а) с максимальной частотой: " + mostFrequentWords + "\n");
                writer.write("Частота: " + maxFrequency + "\n");
                writer.write("Процент: " + String.format("%.2f", maxFrequencyPercentage) + "%\n");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}