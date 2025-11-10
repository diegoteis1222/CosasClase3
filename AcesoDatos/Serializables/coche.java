
import java.io.Serializable;

public class coche implements Serializable {

    String matricula, marca, modelo;
    int tamañoDeposito;
    double precio;

    public coche(String matricula, String marca, String modelo, int tamañoDeposito, double precio) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tamañoDeposito = tamañoDeposito;
        this.precio = precio;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getTamañoDeposito() {
        return tamañoDeposito;
    }

    public void setTamañoDeposito(int tamañoDeposito) {
        this.tamañoDeposito = tamañoDeposito;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "coche [matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", tamañoDeposito="
                + tamañoDeposito + ", precio=" + precio + "]";
    }

}
