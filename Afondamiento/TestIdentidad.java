public class TestIdentidad {
    public static void main(String[] args) {

        System.out.println("Dame una matriz 3x3");
        int[][] matriz = Util.fillFromKeyboard(3, 3);

        Util.print2DArray(matriz);

        int determinante = Util.determinante3x3(matriz);

        System.out.println("El determinante es: " + determinante);

        if (determinante  != 0) {
            System.out.println("Tiene inversa.");
        } else {
            System.out.println("No tiene inversa .");
        }
    }
}
