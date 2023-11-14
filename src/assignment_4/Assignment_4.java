package assignment_4;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Assignment_4 {
    public static void main(String[] args) {
        PhoneNumberManager phoneNumberManager = new PhoneNumberManager();
        phoneNumberManager.startManage();
    }
}

class PhoneNumberManager {
    HashMap<String, Phone> phoneHashMap = new HashMap<>();

    public PhoneNumberManager() {
    }

    public void startManage() {
        Scanner sc = new Scanner(System.in);
        boolean continueManage = true;

        System.out.println("--------------------------------------------------------------");
        System.out.println("전화번호 관리 프로그램을 시작합니다. 파일로 저장하지 않습니다.");
        System.out.println("--------------------------------------------------------------");
        while (continueManage) {
            System.out.print("삽입:0, 삭제:1, 찾기:2, 전체보기: 3, 종료 4>>>");
            int selectNumber = sc.nextInt();
            sc.nextLine();

            switch (selectNumber) {
                case 0:
                    insertPhoneNumber(sc);
                    break;
                case 1:
                    deletePhoneNumber(sc);
                    break;
                case 2:
                    findPhoneNumber(sc);
                    break;
                case 3:
                    showAll();
                    break;
                case 4:
                    System.out.println("프로그램을 종료합니다....");
                    continueManage = false;
                    sc.close();
                    break;
                default:
                    System.out.println("올바르지 않은 번호를 입력하였습니다. 다시 입력해 주세요.");
            }
        }
    }

    // 전화번호 삽입
    private void insertPhoneNumber(Scanner sc) {
        System.out.print("이름>>>");
        String name = sc.nextLine();

        if (checkInserted(name)) {
            System.out.printf("%s은 이미 등록되었습니다.%n", name);
        } else {
            System.out.print("주소>>>");
            String address = sc.nextLine();

            System.out.print("전화번호>>>");
            String phoneNumber = sc.nextLine();

            this.phoneHashMap.put(name, new Phone(address, phoneNumber));
        }
    }

    // 전화번호 삭제
    private void deletePhoneNumber(Scanner sc) {
        System.out.print("이름>>>");
        String name = sc.nextLine();

        if (checkInserted(name)) {
            this.phoneHashMap.remove(name);
            System.out.printf("%s은 삭제되었습니다.%n", name);
        } else {
            System.out.printf("%s은 등록되지 않은 사람입니다.%n", name);
        }
    }

    // 전화번호 찾기
    private void findPhoneNumber(Scanner sc) {
        System.out.print("이름>>>");
        String name = sc.nextLine();

        Phone findedPhone = this.phoneHashMap.get(name);
        if (findedPhone == null) {
            System.out.printf("%s은 등록되지 않은 사람입니다.%n", name);
        } else {
            System.out.printf("%s %s %s%n", name, findedPhone.getAddress(), findedPhone.getPhoneNumber());
        }
    }

    // 전체 보기
    private void showAll() {
        Set<String> keySet = this.phoneHashMap.keySet();

        Iterator<String> keySetIterator = keySet.iterator();

        while (keySetIterator.hasNext()) {
            String name = keySetIterator.next();
            Phone findedPhone = this.phoneHashMap.get(name);
            System.out.printf("%s %s %s%n", name, findedPhone.getAddress(), findedPhone.getPhoneNumber());
        }
    }

    // 등록된 이름인지 확인
    private boolean checkInserted(String name) {
        return this.phoneHashMap.containsKey(name);
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
