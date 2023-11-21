package practical_task_2;

import javax.swing.*;
import java.awt.*;

public class CalculatorEx extends JFrame {
    public CalculatorEx() {
        setTitle("계산기");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextField textField = new JTextField("");
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        // 크기조절
        textField.setPreferredSize(new Dimension(300, 60));
        // 글씨 크기 조절
        Font font = new Font("Arial", Font.PLAIN, 22);
        textField.setFont(font);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttonLabels = {"1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "*", "0", "C", "=", "/"};
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            buttonPanel.add(button);
        }

        Container calculatorPanel = getContentPane();
        calculatorPanel.setLayout(new BorderLayout());

        calculatorPanel.add(textField, BorderLayout.NORTH);
        calculatorPanel.add(buttonPanel, BorderLayout.CENTER);

        setSize(300, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CalculatorEx();
    }
}
