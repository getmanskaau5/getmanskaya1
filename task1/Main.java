public class Main {
    public static void main(String[] args) {
        int n1 = Integer.parseInt(args[0]);
        int m1 = Integer.parseInt(args[1]);
        int n2 = Integer.parseInt(args[2]);
        int m2 = Integer.parseInt(args[3]);

        System.out.print(generatePath(n1, m1));
        System.out.println(generatePath(n2, m2));
    }

    // Метод для построения пути
    private static String generatePath(int size, int step) {
        StringBuilder sb = new StringBuilder();
        int pos = 0;

        do {
            sb.append(pos + 1);
            pos = (pos + step) % size;
        } while (pos != 0);
        return sb.toString();
    }
}