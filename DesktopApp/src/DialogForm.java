import javax.swing.*;
import java.awt.event.*;

public class DialogForm extends JDialog {

    private JPanel contentPanel;
    private JButton button_ok;
    private JButton button_cancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JLabel label4;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

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

    private boolean collapsePuzzle() {

        if (textField1.getText().length() == 0) {
            JOptionPane.showMessageDialog(
                    contentPanel,
                    "Фамилия не указана. Это обязательно поле.",
                    "Предупреждение",
                    JOptionPane.ERROR_MESSAGE
                    );
            return false;
        }
        if (textField2.getText().length() == 0) {
            JOptionPane.showMessageDialog(
                    contentPanel,
                    "Имя не указано. Это обязательно поле.",
                    "Предупреждение",
                    JOptionPane.ERROR_MESSAGE
                    );
            return false;
        }
        // фамилия и имя введены верно
        return true;
    }

    private boolean expandPuzzle() {

        String nameText = textField4.getText();
        String[] words = nameText.split(" ");
        if (words.length < 2) {
            JOptionPane.showMessageDialog(
                    contentPanel,
                    "Фамилия и имя должны быть указаны.",
                    "Предупреждение",
                    JOptionPane.ERROR_MESSAGE
                    );
            return false;
        } else {
            textField1.setText(words[0]);
            textField2.setText(words[1]);
            if (words.length >= 3)
                textField3.setText(words[2]);
        }
        return true;
    }

    private void onOK() {

        String button_text = button_ok.getText();

        if (button_text.equals("Collapse")) {

            if (collapsePuzzle()) {
                // переход с Collapse на Expand
                label4.setVisible(true);
                textField4.setVisible(true);

                label1.setVisible(false);
                label2.setVisible(false);
                label3.setVisible(false);
                textField1.setVisible(false);
                textField2.setVisible(false);
                textField3.setVisible(false);

                textField4.setText(textField1.getText() + " " + textField2.getText() + " " + textField3.getText());
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                button_ok.setText("Expand");
            }
        } else {

            if (expandPuzzle()) {
                // переход с Expand на Collapse
                label4.setVisible(false);
                textField4.setVisible(false);

                label1.setVisible(true);
                label2.setVisible(true);
                label3.setVisible(true);
                textField1.setVisible(true);
                textField2.setVisible(true);
                textField3.setVisible(true);

                textField4.setText("");
                button_ok.setText("Collapse");
            }
        }

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
