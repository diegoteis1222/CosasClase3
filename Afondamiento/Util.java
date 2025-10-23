
public class Util {

    final static int[][] Identidad3 = {
        {1, 0, 0},
        {0, 1, 0},
        {0, 0, 1}
    };

    final static int[][] Identidad2 = {
        {1, 0},
        {0, 1}
    };

    public static int[][] dameIdentidad(int n) {
        if (n == 2) {
            return Identidad2;
        } else if (n == 3) {
            return Identidad3;
        } else {
            int[][] identidad = new int[n][n];
            for (int i = 0; i < n; i++) {
                identidad[i][i] = 1;
            }
            return identidad;
        }
    }

    public static int determinante2x2(int[][] A) {
        if (A.length != 2 || A[0].length != 2) {
            throw new IllegalArgumentException("La matriz debe ser 2x2");
        }
        return (A[0][0] * A[1][1]) - (A[0][1] * A[1][0]);
    }

    public static int determinante3x3(int[][] A) {
        if (A.length != 3 || A[0].length != 3) {
            throw new IllegalArgumentException("La matriz debe ser 3x3");
        }
        return A[0][0] * ((A[1][1] * A[2][2]) - (A[1][2] * A[2][1]))
                - A[0][1] * ((A[1][0] * A[2][2]) - (A[1][2] * A[2][0]))
                + A[0][2] * ((A[1][0] * A[2][1]) - (A[1][1] * A[2][0]));
    }

    // Cre un arrray 2D
    public static int[][] array2D(int rows, int cols) {
        return new int[rows][cols];
    }

    // Imprime un array 2D
    public static void print2DArray(int[][] array) {
        for (int[] row : array) {
            for (int elem : row) {
                System.out.print(elem + " ");
            }
            System.out.println();
        }
    }

    public static int[][] fillFromKeyboard(int rows, int cols) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int[][] array = new int[rows][cols];
        System.out.println("Enter " + (rows * cols) + " integers:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = sc.nextInt();
            }
        }
        return array;
    }

    public static int[][] sumMatrices(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = A[0].length;
        int[][] C = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    public static int[][] restaMatrices(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = A[0].length;
        int[][] C = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    public static int[][] multiplyMatrices(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int rowsB = B.length;
        int colsB = B[0].length;

        if (colsA != rowsB) {
            throw new IllegalArgumentException("Incompatible matrix sizes for multiplication");
        }

        int[][] C = new int[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    public static int[][] transposeMatrix(int[][] A) {
        int rows = A.length;
        int cols = A[0].length;
        int[][] transpose = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transpose[j][i] = A[i][j];
            }
        }
        return transpose;
    }

    public static boolean areMatricesEqual(int[][] A, int[][] B) {
        if (A.length != B.length || A[0].length != B[0].length) {
            return false;
        }
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] != B[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isSquareMatrix(int[][] A) {
        return A.length == A[0].length;
    }

    public static boolean esSimetrica(int[][] A) {
        if (!isSquareMatrix(A)) {
            return false;
        }
        return areMatricesEqual(A, transposeMatrix(A));
    }

    public static int[][] inversa3x3(int[][] A) {
        if (A.length != 3 || A[0].length != 3) {
            throw new IllegalArgumentException("La matriz debe ser 3x3.");
        }

        int det = determinante3x3(A);
        if (det == 0) {
            throw new ArithmeticException("La matriz no tiene inversa (determinante = 0).");
        }

        int[][] adjunta = adjunta3x3(A);
        int[][] inversa = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                inversa[i][j] = adjunta[i][j] / det; // Si quieres una versión double, cambia a double[][]
            }
        }

        return inversa;
    }

    public static int[][] adjunta3x3(int[][] A) {
        int[][] adj = new int[3][3];

        adj[0][0] = (A[1][1] * A[2][2] - A[1][2] * A[2][1]);
        adj[0][1] = -(A[1][0] * A[2][2] - A[1][2] * A[2][0]);
        adj[0][2] = (A[1][0] * A[2][1] - A[1][1] * A[2][0]);

        adj[1][0] = -(A[0][1] * A[2][2] - A[0][2] * A[2][1]);
        adj[1][1] = (A[0][0] * A[2][2] - A[0][2] * A[2][0]);
        adj[1][2] = -(A[0][0] * A[2][1] - A[0][1] * A[2][0]);

        adj[2][0] = (A[0][1] * A[1][2] - A[0][2] * A[1][1]);
        adj[2][1] = -(A[0][0] * A[1][2] - A[0][2] * A[1][0]);
        adj[2][2] = (A[0][0] * A[1][1] - A[0][1] * A[1][0]);

        // Transponer la matriz de cofactores
        return transposeMatrix(adj);
    }


}
