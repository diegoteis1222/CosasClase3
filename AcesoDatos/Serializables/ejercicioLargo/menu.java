
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class menu extends JFrame implements ActionListener {

    private Container contenedor;
    private JMenuBar menuBar;
    private JMenu menuArchivo, menuOperador;
    private JMenuItem itemCrearArchivo, itemIntroducirTexto, itemContarPalabras, itemContarVocales;

    public menu() {
        inicio();
        setTitle("Lanzador de Aplicaciones");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void inicio() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        //Menu Bar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Menu
        menuArchivo = new JMenu("Archivo");
        menuOperador = new JMenu("Operador_archivos");

        // Menu Items
        itemCrearArchivo = new JMenuItem("Crear Archivo de Texto");
        itemCrearArchivo.addActionListener(this);

        itemIntroducirTexto = new JMenuItem("Introducir Texto en Archivo");
        itemIntroducirTexto.addActionListener(this);

        itemContarPalabras = new JMenuItem("Contar Palabras de un Archivo");
        itemContarPalabras.addActionListener(this);

        itemContarVocales = new JMenuItem("Contar Vocales de un Archivo");
        itemContarVocales.addActionListener(this);

        //Adds
        menuArchivo.add(itemCrearArchivo);
        menuArchivo.add(itemIntroducirTexto);
        menuOperador.add(itemContarPalabras);
        menuOperador.add(itemContarVocales);
        menuBar.add(menuArchivo);
        menuBar.add(menuOperador);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemCrearArchivo) {
            crearArchivo ca = new crearArchivo();
            ca.setVisible(true);
        } else if (e.getSource() == itemIntroducirTexto) {
            introducirTexto it = new introducirTexto();
            it.setVisible(true);
        } else if (e.getSource() == itemContarPalabras) {
            contarPalabras cp = new contarPalabras();
            cp.setVisible(true);
        } else if (e.getSource() == itemContarVocales) {
            contarVocales cv = new contarVocales();
            cv.setVisible(true);
        }
    }

    public static void main(String[] args) {
        menu menu = new menu();
        menu.setVisible(true);
    }
}
