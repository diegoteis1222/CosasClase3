
public class resolucionEcuacion {

    public static void main(String[] args) {

        double a = 2;
        double b = 3;
        double c = 1;
        double d = 2;
        double e = 5;
        double f = 4;

        double determinante = a * d - b * c;

        if (determinante == 0) {
            System.out.println("El sistema no tiene solución única.");
        } else {
            double x = (e * d - b * f) / determinante;
            double y = (a * f - e * c) / determinante;

            System.out.println("La solución del sistema es:");
            System.out.println("x = " + x);
            System.out.println("y = " + y);
            System.out.println();
        }

        double[][] matriz = pedirMatrizCoeficientes2x2();
        double[] terminos = pedirTerminosIndependientes2x1();
        mostrarSolucion(matriz, terminos);

    }

    public static double[][] pedirMatrizCoeficientes2x2() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        double[][] matriz = new double[2][2];

        System.out.println("Ingrese los coeficientes de la matriz 2x2:");

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print("Elemento [" + (i + 1) + "][" + (j + 1) + "]: ");
                matriz[i][j] = scanner.nextDouble();
            }
        }

        return matriz;
    }

    public static double[] pedirTerminosIndependientes2x1() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        double[] terminos = new double[2];

        System.out.println("Ingrese los términos independientes:");

        for (int i = 0; i < 2; i++) {
            System.out.print("Término independiente [" + (i + 1) + "]: ");
            terminos[i] = scanner.nextDouble();
        }

        return terminos;
    }

    public static double determinante2x2(double[][] matriz) {
        return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
    }

    public static double[] resolverEcuacion2x2(double[][] matriz, double[][] terminos) {
        double det = determinante2x2(matriz);
        if (det == 0) {
            return null; // o lanzar una excepción
        }

        double[][] inversa = new double[2][2];
        inversa[0][0] = matriz[1][1] / det;
        inversa[0][1] = -matriz[0][1] / det;
        inversa[1][0] = -matriz[1][0] / det;
        inversa[1][1] = matriz[0][0] / det;

        double[] resultado = new double[2];
        resultado[0] = inversa[0][0] * terminos[0][0] + inversa[0][1] * terminos[1][0];
        resultado[1] = inversa[1][0] * terminos[0][0] + inversa[1][1] * terminos[1][0];

        return resultado;
    }

    public static double determinanteX(double[][] matriz, double[] terminos) {
        return terminos[0] * matriz[1][1] - matriz[0][1] * terminos[1];
    }

    public static double determinanteY(double[][] matriz, double[] terminos) {
        return matriz[0][0] * terminos[1] - terminos[0] * matriz[1][0];
    }

    public static void mostrarSolucion(double[][] matriz, double[] terminos) {
        double det = determinante2x2(matriz);
        if (det == 0) {
            System.out.println("El sistema no tiene solución única.");
            return;
        }

        double detX = determinanteX(matriz, terminos);
        double detY = determinanteY(matriz, terminos);

        double x = detX / det;
        double y = detY / det;

        System.out.println("La solución del sistema es:");
        System.out.println("x = " + x);
        System.out.println("y = " + y);

    }

}
