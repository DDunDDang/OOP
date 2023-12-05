package assignment_5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Assignment_5 extends JFrame {
    private JTextArea ta = new JTextArea(10, 30);
    private JTextField tfName = new JTextField(30);
    private JTextField tfPhoneNumber = new JTextField(30);
    private JTextField tfAddress = new JTextField(30);

    private HashMap<String, Phone> phoneHashMap = new HashMap<>();

    public Assignment_5() {
        setTitle("Phone Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);

        JScrollPane sp = new JScrollPane(ta);
        sp.setLocation(10, 10);
        sp.setSize(445, 250);
        c.add(sp);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        // 조회 버튼
        JButton showButton = new JButton("조회");
        showButton.addActionListener(new ShowActionListener());
        buttonPanel.add(showButton);

        // 검색 버튼
        JButton findButton = new JButton("검색");
        findButton.addActionListener(new FindActionListener());
        buttonPanel.add(findButton);

        // 삽입 버튼
        JButton insertButton = new JButton("삽입");
        insertButton.addActionListener(new InsertActionListener());
        buttonPanel.add(insertButton);

        // 삭제 버튼
        JButton deleteButton = new JButton("삭제");
        deleteButton.addActionListener(new DeleteActionListener());
        buttonPanel.add(deleteButton);

        buttonPanel.setLocation(460, 20);
        buttonPanel.setSize(430, 50);
        c.add(buttonPanel);

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new GridLayout(3, 1));
        namePanel.add(new JLabel("이     름"));
        namePanel.add(new JLabel("전화번호"));
        namePanel.add(new JLabel("주     소"));

        namePanel.setLocation(470, 70);
        namePanel.setSize(100, 180);
        c.add(namePanel);

        JPanel insertPanel = new JPanel();
        insertPanel.setLayout(new GridLayout(3, 1));
        insertPanel.add(tfName);
        insertPanel.add(tfPhoneNumber);
        insertPanel.add(tfAddress);

        insertPanel.setLocation(570, 70);
        insertPanel.setSize(315, 180);
        c.add(insertPanel);

        setSize(900, 300);
        setVisible(true);
    }

    // 조회 리스너
    private class ShowActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ta.setText("");

            Set<String> keySet = phoneHashMap.keySet();

            Iterator<String> keySetIterator = keySet.iterator();

            while (keySetIterator.hasNext()) {
                String name = keySetIterator.next();
                Phone findedPhone = phoneHashMap.get(name);
                ta.append("이     름 : " + name + "\n");
                ta.append("전화번호 : " + findedPhone.getPhoneNumber() + "\n");
                ta.append("주     소 : " + findedPhone.getAddress() + "\n\n");
            }
        }
    }

    // 검색 리스너
    private class FindActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ta.setText("");

            String name = tfName.getText();

            if (checkInserted(name)) {
                Phone findedPhone = phoneHashMap.get(name);
                ta.append("이     름 : " + name + "\n");
                ta.append("전화번호 : " + findedPhone.getPhoneNumber() + "\n");
                ta.append("주     소 : " + findedPhone.getAddress() + "\n\n");
            } else if (name.equals("")) {
                ta.append("이름을 입력해 주세요.");
            } else {
                ta.append(name + "에 대한 전화번호는 없습니다.");
            }

            resetTextField();
        }
    }

    // 삽입 리스너
    private class InsertActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ta.setText("");
            String name = tfName.getText();

            if (checkInserted(name)) {
                ta.setText(name + "에 대한 전화번호는 이미 등록 되었습니다.");
            } else {
                phoneHashMap.put(name, new Phone(tfAddress.getText(), tfPhoneNumber.getText()));
                ta.setText(name + "의 전화번호가 삽입되었습니다.");
            }
            resetTextField();
        }
    }

    // 삭제 리스너
    private class DeleteActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ta.setText("");
            String name = tfName.getText();

            if (checkInserted(name)) {
                phoneHashMap.remove(name);
                ta.append(name + "의 전화번호가 삭제되었습니다.");
            } else if (name.equals("")) {
                ta.append("이름을 입력해 주세요.");
            } else {
                ta.append(name + "에 대한 전화번호는 없습니다.");
            }

            resetTextField();
        }
    }

    // 등록된 이름인지 확인
    private boolean checkInserted(String name) {
        return this.phoneHashMap.containsKey(name);
    }

    // 텍스트필드 초기화
    private void resetTextField() {
        tfName.setText("");
        tfPhoneNumber.setText("");
        tfAddress.setText("");
    }

    public static void main(String[] args) {
        new Assignment_5();
    }
}

class Phone {
    private String address;
    private String phoneNumber;

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Phone(String address, String phoneNumber) {
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
