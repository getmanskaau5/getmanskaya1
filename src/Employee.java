import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Employee {

    private String firstName;
    private String lastName;
    private int age;
    private String skills;
    private Profession profession;

    public Employee(String firstName, String lastName, int age, String skills, Profession profession) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.skills = skills;
        this.profession = profession;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Profession getProfession() {
        return profession;
    }

    public void displayDailyActivities() {
        System.out.println("\nДневной распорядок сотрудника: " + firstName + " " + lastName);
        profession.displayDuties();
    }

    @Override
    public String toString() {
        return "Имя: " + firstName + ", Фамилия: " + lastName + ", Возраст: " + age + ", Должность: " + profession.getName() + ", Особые навыки: " + skills;
    }

    public void saveToFile() {
        String fileName = firstName + lastName + profession.getName() + ".txt";
        Path filePath = Path.of(fileName);
        try {
            Files.writeString(filePath, toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Информация о сотруднике сохранена в файл: " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }
}

