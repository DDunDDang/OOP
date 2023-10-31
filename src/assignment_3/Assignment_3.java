package assignment_3;

import java.util.Scanner;

public class Assignment_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ConvenienceStore convenienceStore = new ConvenienceStore();

        convenienceStore.init(sc);
        convenienceStore.operation(sc);

        sc.close();
    }
}

class ConvenienceStore {
    private Goods[] goodsArr;

    public ConvenienceStore() {
    }

    // 물건 설정
    public void init(Scanner sc) {
        System.out.print("슈퍼에서 판매하는 상품의 갯수를 입력하시오. >>> ");
        int goodsNum = sc.nextInt();

        this.goodsArr = new Goods[goodsNum];
        for (int i = 0; i < goodsNum; i++) {
            System.out.printf("%d번째 상품의 이름, 가격, 재고량을 입력하시오. >>> ", i + 1);
            String name = sc.next();
            int price = sc.nextInt();
            int numberOfStock = sc.nextInt();
            goodsArr[i] = new Goods(name, price, numberOfStock, 0);
        }
    }

    // 매장 운영
    public void operation(Scanner sc) {
        boolean continueSale = true;

        while (continueSale) {
            System.out.println("1) 판매     2) 구매     3) 조회     4) 종료");
            System.out.print(">>> ");
            int menuSelect = sc.nextInt();

            switch (menuSelect) {
                case 1:
                    sale(sc);
                    break;
                case 2:
                    buy(sc);
                    break;
                case 3:
                    check();
                    break;
                case 4:
                    continueSale = false;
                    break;
            }
        }
    }

    // 판매
    private void sale(Scanner sc) {
        int totalPrice = 0;

        while (true) {
            for (int i = 0; i < this.goodsArr.length; i++) {
                System.out.print(i + 1 +") " + goodsArr[i].getName() + "  ");
            }
            System.out.println((goodsArr.length + 1) + ") 계산");
            System.out.print(">>> ");
            int selectNum = sc.nextInt();

            if (selectNum >= goodsArr.length + 1) {
                break;
            }

            totalPrice += goodsArr[selectNum - 1].select();
        }
        System.out.println("판매 가격 총액 : " + totalPrice);
        System.out.print("받은 금액을 입력하시오. >>> ");

        showReceipt(totalPrice, sc.nextInt());
    }

    // 구매
    private void buy(Scanner sc) {
        while (true) {
            for (int i = 0; i < this.goodsArr.length; i++) {
                System.out.print(i + 1 +") " + goodsArr[i].getName() + "  ");
            }
            System.out.println((goodsArr.length + 1) + ") 구매 종료");

            int selectNum = sc.nextInt();

            if (selectNum >= goodsArr.length + 1) {
                break;
            }

            System.out.print("구매 수량을 입력하시오. >>> ");
            int buyNum = sc.nextInt();

            Goods selectedGoods = goodsArr[selectNum - 1];
            selectedGoods.buy(buyNum);

            System.out.printf("## %s의 재고량이 %d으로 증가함%n", selectedGoods.getName(), selectedGoods.getNumberOfStock());
        }
    }

    // 조회
    private void check() {
        System.out.println("###   상품명     재고량   ###");
        System.out.println("==========================");
        for (Goods goods : this.goodsArr) {
            System.out.printf("  %9s %8d%n", goods.getName(), goods.getNumberOfStock());
        }
        System.out.println("==========================");
        System.out.println(" ");
    }

    // 영수증 출력
    private void showReceipt(int totalPrice, int receivedMoney ) {
        System.out.println(" ");
        System.out.println("###        영수증        ###");
        System.out.println("==========================");

        for (Goods goods : this.goodsArr) {
            if (goods.getSold() > 0) {
                int goodsPriceSum = goods.getPrice() * goods.getSold();
                System.out.printf("  %-7s %dx%d    %d%n", goods.getName(), goods.getPrice(), goods.getSold(), goodsPriceSum);
            }
        }

        System.out.println("==========================");
        System.out.printf("  %-4s %13d%n", "총액  ", totalPrice);
        System.out.printf("  %-4s %13d%n", "받은금액", receivedMoney);
        System.out.println("==========================");
        System.out.printf("  %-4s %13d%n", "거스름돈", receivedMoney - totalPrice);
        System.out.println(" ");

        reset();
    }

    // sold의 값만큼 numberOfStock을 차감하고, sold를 0으로 되돌림
    private void reset() {
        for (Goods goods : this.goodsArr) {
            goods.resetSold();
        }
    }
}

class Goods {
    private String name;
    private int price;
    private int numberOfStock;
    private int sold;

    public Goods(String name, int price, int numberOfStock, int sold) {
        this.name = name;
        this.price = price;
        this.numberOfStock = numberOfStock;
        this.sold = sold;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getNumberOfStock() {
        return numberOfStock;
    }

    public int getSold() {
        return sold;
    }

    public int select() {
        this.sold += 1;
        return this.price;
    }

    public void buy(int buyNum) {
        this.numberOfStock += buyNum;
    }

    public void resetSold() {
        this.numberOfStock -= this.sold;
        this.sold = 0;
    }
}
