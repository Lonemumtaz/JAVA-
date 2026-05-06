public class MathUtils {
    public static int square(int n) {
        return n * n;
    }

    public static int cube(int n) {
        return n * n * n;
    }

    public static void main(String[] args) {
        int num = 4;
        System.out.println("Square: " + square(num));
        System.out.println("Cube: " + cube(num));
    }
}
