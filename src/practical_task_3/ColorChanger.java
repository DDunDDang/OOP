package practical_task_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorChanger extends JFrame {
    Container c;
    public ColorChanger() {
        setTitle("색깔 변환 실습");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c = getContentPane();
        c.setLayout(new FlowLayout());
        JButton redButton = new JButton("빨강");
        redButton.setBackground(Color.RED);
        redButton.addActionListener(new RedButtonListener());
        JButton blueButton = new JButton("파랑");
        blueButton.setBackground(Color.BLUE);
        blueButton.addActionListener(new BlueButtonListener());
        JButton yellowButton = new JButton("노랑");
        yellowButton.setBackground(Color.YELLOW);
        yellowButton.addActionListener(new YellowButtonListener());

        c.add(redButton);
        c.add(blueButton);
        c.add(yellowButton);

        setSize(350, 150);
        setVisible(true);
    }

    private class RedButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            c.setBackground(Color.RED);
        }
    }

    private class BlueButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            c.setBackground(Color.BLUE);
        }
    }

    private class YellowButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            c.setBackground(Color.YELLOW);
        }
    }

    public static void main(String[] args) {
        new ColorChanger();
    }
}
