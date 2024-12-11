public abstract class Profession {

    public abstract String getName();

    public abstract int getLunchDuration();

    public void displayDuties() {
        System.out.println("1. Приходит на работу.");
        System.out.println("2. Выполняет должностные обязанности.");
        System.out.println("3. Обедает " + getLunchDuration() + " минут.");
        System.out.println("4. Уходит с работы.");
    }

}



