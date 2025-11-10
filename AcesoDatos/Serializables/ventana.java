
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ventana extends JFrame implements ActionListener {

    private Container contenedor;
    private JButton convertir;
    private JLabel stones, libras, resultado;
    private JTextField campoStones, campoLibras;

    public ventana() {
        inicio();
        setTitle("Convertir a KG");
        setSize(280, 300);
        setLocationRelativeTo(convertir);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void inicio() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        stones = new JLabel("Stones: ");
        stones.setBounds(90, 20, 60, 23);
        campoStones = new JTextField();
        campoStones.setBounds(90, 40, 100, 23);

        libras = new JLabel("Libras: ");
        libras.setBounds(90, 60, 60, 23);
        campoLibras = new JTextField();
        campoLibras.setBounds(90, 80, 100, 23);

        convertir = new JButton();
        convertir.setText("Convertir");
        convertir.setBounds(90, 150, 100, 23);
        convertir.addActionListener(this);

        resultado = new JLabel("Resultado: ");
        resultado.setBounds(90, 180, 100, 23);

        contenedor.add(libras);
        contenedor.add(campoLibras);
        contenedor.add(stones);
        contenedor.add(campoStones);
        contenedor.add(convertir);
        contenedor.add(resultado);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        double pedra = 6.35;
        double libra = 0.45;

        double cantidadStones;
        double cantidadLibras;
        double total = 0;

        try {
            cantidadStones = Double.parseDouble(campoStones.getText());
            cantidadLibras = Double.parseDouble(campoLibras.getText());

            if (cantidadLibras > 13) {
                JOptionPane.showMessageDialog(null,
                        "No puede superar 13",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            total = (cantidadStones * pedra) + (cantidadLibras * libra);
            resultado.setText(String.valueOf(total));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Solo acepto numeros",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
