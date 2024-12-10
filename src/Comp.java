public class Comp {
    public static void main(String[] args) {
        // Создаем объект Employee
        Employee cleaner = new Employee("Иван","Иванов", 16,"Убирает");
        Employee driver = new Employee("Николай","Иванов", 26,"водит");
        Employee manager = new Employee("Семён","Иванов", 46,"управляет");
        Employee security = new Employee("Глеб","Иванов", 66,"охраняет");

        // Выводим информацию о сотруднике
        cleaner.displayInfo();
        System.out.println("приходит, убирает, обедает 30 мин., уходит с работы");
        driver.displayInfo();
        System.out.println("приходит, убирает, обедает 40 мин., уходит с работы");
        manager.displayInfo();
        System.out.println("приходит, убирает, обедает 20 мин., уходит с работы");
        security.displayInfo();
        System.out.println("приходит, убирает, обедает 60 мин., уходит с работы");
    }
}

class Employee {
    // Поля класса
    private String name;
    private String lastName;
    private int age;
    private String position;


    // Конструктор класса
    public Employee(String name, String lastName, int age, String position) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.position = position;


    }

    // Метод для вывода информации о сотруднике
    public void displayInfo() {
        System.out.println("Имя: " + name);
        System.out.println("Фамилия: " + lastName);
        System.out.println("Возраст: " + age);
        System.out.println("Должность: " + position);


        System.out.println();
    }

}
