
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class crearArchivo extends JFrame implements ActionListener {

    private Container contenedor;
    private JLabel crearLabel;
    private JButton crearButton;

    public crearArchivo() {
        inicio();
        setTitle("Creador de Archivos");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void inicio() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        //Label
        crearLabel = new JLabel("Crear archivo de texto:");
        crearLabel.setBounds(80, 30, 200, 23);

        //Button
        crearButton = new JButton("Crear");
        crearButton.setBounds(100, 60, 100, 23);
        crearButton.addActionListener(this);

        //Adds
        contenedor.add(crearLabel);
        contenedor.add(crearButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar archivo");

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            if (!fileToSave.getAbsolutePath().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
            }

            try (FileWriter myWriter = new FileWriter(fileToSave)) {
                myWriter.write("");
                JOptionPane.showMessageDialog(this, "Archivo creado exitosamente en: " + fileToSave.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        //Test
        crearArchivo ca = new crearArchivo();
        ca.setVisible(true);
    }
}
