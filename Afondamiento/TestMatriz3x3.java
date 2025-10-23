import java.util.Scanner;

public class TestMatriz3x3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese los 9 elementos de la matriz 3x3:");
        int[][] A = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                A[i][j] = sc.nextInt();
            }
        }

        System.out.println("\nMatriz ingresada:");
        Util.print2DArray(A);

        try {
            int[][] inv = Util.inversa3x3(A);

            System.out.println("\nMatriz inversa:");
            Util.print2DArray(inv);

            int[][] producto = Util.multiplyMatrices(A, inv);

            System.out.println("\nProducto la matriz * su inversa:");
            Util.print2DArray(producto);

            if (Util.areMatricesEqual(producto, Util.dameIdentidad(3))) {
                System.out.println("\n El producto da la matriz identidad.");
            } else {
                System.out.println("\n El producto NO da exactamente la matriz identidad.");
            }

        } catch (ArithmeticException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }
}
