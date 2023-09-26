package homework_1;

import java.util.Scanner;

public class Homework_1 {
    public static void main(String[] args) {
        System.out.println("학과, 학번, 이름을 빈칸으로 분리하여 입력하세요.");
        Scanner sc = new Scanner(System.in);

        String department = sc.next();
        int studentNumber = sc.nextInt();
        String name = sc.next();

        System.out.println("학과는 " + department + ", 학번은 " + studentNumber + ", 이름은 " + name + " 입니다.");

        sc.close();
    }
}