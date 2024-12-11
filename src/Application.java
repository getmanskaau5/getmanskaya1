import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Иван", "Иванов", 25, "Ответственность", new Cleaner()));
        employees.add(new Employee("Петр", "Петров", 35, "Хорошая реакция", new Driver()));
        employees.add(new Employee("Кузьма", "Кузнецова", 30, "Лидерство", new Manager()));
        employees.add(new Employee("Сергей", "Сергеев", 40, "Внимательность", new Security()));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nСписок сотрудников:");
            for (int i = 0; i < employees.size(); i++) {
                System.out.println((i + 1) + ". " + employees.get(i).getFirstName() + " " + employees.get(i).getLastName() + " (" + employees.get(i).getProfession().getName() + ")");
            }

            System.out.println("\nВведите номер сотрудника для отображения его действий в течение дня (или 0 для выхода): ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Выход из программы.");
                break;
            }

            if (choice > 0 && choice <= employees.size()) {
                Employee selectedEmployee = employees.get(choice - 1);
                System.out.println("\nИнформация о сотруднике:");
                System.out.println(selectedEmployee);
                selectedEmployee.displayDailyActivities();
                System.out.println("\nХотите сохранить информацию о сотруднике в файл? (да/нет): ");
                String saveChoice = scanner.next();
                if (saveChoice.equalsIgnoreCase("да")) {
                    selectedEmployee.saveToFile();
                }
            } else {
                System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }

        scanner.close();
    }
}