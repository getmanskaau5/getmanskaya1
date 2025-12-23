import java.io.*;
import java.util.*;
import org.json.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Необходимо передать три аргумента: пути к файлам values.json, tests.json и report.json.");
            return;
        }

        String valuesFilePath = args[0];
        String testsFilePath = args[1];
        String reportFilePath = args[2];

        try {

            String valuesJson = readFile(valuesFilePath);
            JSONArray values = new JSONArray(valuesJson);


            String testsJson = readFile(testsFilePath);
            JSONObject tests = new JSONObject(testsJson);


            fillValues(tests, values);


            writeFile(reportFilePath, tests.toString(2));

            System.out.println("Файл report.json успешно создан.");
        } catch (IOException e) {
            System.err.println("Ошибка при чтении или записи файлов: " + e.getMessage());
        }
    }

    private static void fillValues(JSONObject tests, JSONArray values) {
        JSONArray testsArray = tests.getJSONArray("tests");
        for (int i = 0; i < testsArray.length(); i++) {
            JSONObject test = testsArray.getJSONObject(i);
            fillValuesRecursive(test, values);
        }
    }

    private static void fillValuesRecursive(JSONObject test, JSONArray values) {
        int id = test.getInt("id");
        for (int i = values.length() - 1; i >= 0; i--) {
            if (values.getJSONObject(i).getInt("id") == id) {
                test.put("value", values.getJSONObject(i).getString("value"));
                break;
            }
        }

        if (test.has("values")) {
            JSONArray subValues = test.getJSONArray("values");
            for (int j = 0; j < subValues.length(); j++) {
                fillValuesRecursive(subValues.getJSONObject(j), values);
            }
        }
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }

    private static void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }
}