import javax.swing.*;
import java.awt.event.*;

public class DialogForm extends JDialog {

    private JPanel contentPanel;
    private JButton button_ok;
    private JButton button_cancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    public DialogForm() {

        setContentPane(contentPanel);
        setModal(true);
        getRootPane().setDefaultButton(button_ok);

        button_ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        button_cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        if (textField1.getText().length() == 0) {

        };
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        DialogForm dialog = new DialogForm();
        dialog.setSize(500,300);
        dialog.setLocationRelativeTo(null);
        dialog.pack();

        dialog.setVisible(true);
        System.exit(0);
    }
}
