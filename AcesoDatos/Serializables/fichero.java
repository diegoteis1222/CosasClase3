
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class fichero extends JFrame implements ActionListener {

    private Container contenedor;
    private JButton añadir, crear, salir;
    private JLabel tituloLabel, valorLabel, frecuenciaLabel;
    private JTextArea areaTexto;
    private JScrollPane scrollPanel;
    private JTextField valorField, frecuenciaField;

    public fichero() {
        inicio();
        setTitle("fichero");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void inicio() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        //Label
        tituloLabel = new JLabel("CREAR FICHERO DE PAREJAS DE NÚMERO: ");
        tituloLabel.setBounds(80, 10, 300, 23);
        tituloLabel.setForeground(Color.RED);

        valorLabel = new JLabel("Valor: ");
        valorLabel.setBounds(60, 60, 60, 23);

        frecuenciaLabel = new JLabel("Frecuencia: ");
        frecuenciaLabel.setBounds(200, 60, 60, 23);

        //Field
        valorField = new JTextField();
        valorField.setBounds(60, 80, 100, 23);

        frecuenciaField = new JTextField();
        frecuenciaField.setBounds(200, 80, 100, 23);

        //TextArea
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        //ScrollPanel
        scrollPanel = new JScrollPane(areaTexto);
        scrollPanel.setBounds(40, 120, 180, 200);

        //Buttons
        añadir = new JButton();
        añadir.setText("Añadir");
        añadir.setBounds(250, 150, 100, 23);

        crear = new JButton();
        crear.setText("Crear");
        crear.setBounds(250, 200, 100, 23);

        salir = new JButton();
        salir.setText("Salir");
        salir.setBounds(250, 250, 100, 23);

        //ActionListeners 
        añadir.addActionListener(this);
        crear.addActionListener(this);
        salir.addActionListener(this);

        //Adds
        contenedor.add(tituloLabel);
        contenedor.add(valorLabel);
        contenedor.add(frecuenciaLabel);
        contenedor.add(valorField);
        contenedor.add(frecuenciaField);
        contenedor.add(scrollPanel);
        contenedor.add(añadir);
        contenedor.add(crear);
        contenedor.add(salir);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == añadir) {
            String valor = valorField.getText();
            String frecuencia = frecuenciaField.getText();

            if (!valor.isEmpty() && !frecuencia.isEmpty()) {
                areaTexto.append(valor + "  ,  " + frecuencia + "\n");

                //Para borrar los valores de los campos
                valorField.setText("");
                frecuenciaField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Debes añadir algun numero", "Error", JOptionPane.ERROR_MESSAGE);

            }

        } else if (e.getSource() == crear) {

            //Por si está vacio
            if (areaTexto.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debes añadir algun numero", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("fichero.txt"));
                    areaTexto.write(writer);
                    writer.close();
                    System.out.println("Fichero 'fichero.txt' creado correctamente con el contenido del área de texto.");

                    // Ventana de confirmacion
                    JOptionPane.showMessageDialog(this, "Archivo creado");

                } catch (IOException ex) {
                    System.err.println("Error al crear el fichero: " + ex.getMessage());
                    ex.printStackTrace();

                    // Ventana de error 
                    JOptionPane.showMessageDialog(this, "Error al crear el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else if (e.getSource() == salir) {
            System.exit(0);
        }
    }

}
