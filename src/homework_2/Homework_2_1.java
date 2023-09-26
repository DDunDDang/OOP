package homework_2;

// 실습 문제 1번
public class Homework_2_1 {
    public static void main(String[] args) {
        Tv myTv = new Tv("LG", 2017, 32);
        myTv.show();
    }
}

class Tv {
    public Tv(String brand, int year, int inches) {
        this.brand = brand;
        this.year = year;
        this.inches = inches;
    }

    public String brand;
    public int year;
    public int inches;

    public void show() {
        System.out.println(brand + "에서 만든 " + year + "년형 " + inches +  "인치 TV");
    }
}
