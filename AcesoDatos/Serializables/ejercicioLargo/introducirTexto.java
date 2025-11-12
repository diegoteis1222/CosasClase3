
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
import javax.swing.JTextArea;

public class introducirTexto extends JFrame implements ActionListener {

    private Container contenedor;
    private JLabel introducirLabel;
    private JTextArea areaTexto;
    private JButton crearButton;

    public introducirTexto() {

        inicio();
        setTitle("MeteTextos");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void inicio() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        //Label
        introducirLabel = new JLabel("Introducir texto en el archivo:");
        introducirLabel.setBounds(80, 30, 100, 23);

        //TextArea
        areaTexto = new JTextArea();
        areaTexto.setBounds(30, 60, 400, 200);

        //Button
        crearButton = new JButton("añadir");
        crearButton.setBounds(100, 300, 100, 30);
        crearButton.addActionListener(this);

        //Adds
        contenedor.add(introducirLabel);
        contenedor.add(areaTexto);
        contenedor.add(crearButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == crearButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Selecciona un archivo para guardar");

            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();

                try (FileWriter fileWriter = new FileWriter(fileToSave)) {
                    fileWriter.write(areaTexto.getText());
                    JOptionPane.showMessageDialog(this, "Texto guardado correctamente en: " + fileToSave.getAbsolutePath());
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al guardar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        introducirTexto it = new introducirTexto();
        it.setVisible(true);
    }

}
