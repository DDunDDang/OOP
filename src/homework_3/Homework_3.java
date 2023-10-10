package homework_3;

import java.util.Scanner;

public class Homework_3 {
    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        Die die = new Die();
        Scanner sc = new Scanner(System.in);

        Player[] players = setPlayers(sc);
        int gameCount = startGame(die, sc, players);
        endGame(players, gameCount);
    }

    private static Player[] setPlayers(Scanner sc) {
        System.out.println("게임 참가자의 수를 입력하세요");
        int playerCount = sc.nextInt();

        Player[] players = new Player[playerCount];

        System.out.println("게임 참가자의 이름을 차례로 입력하시오.");
        for (int i = 0; i < playerCount; i++) {
            System.out.println((i + 1) + "번째 참가자 이름 입력");
            String playerName = sc.next();
            players[i] = new Player(playerName);
        }
        System.out.println(" ");
        return players;
    }

    private static int startGame(Die die, Scanner sc, Player[] players) {
        System.out.println("***     지금부터 게임을 시작합니다.     ***");

        boolean continueGame = true;
        int gameCount = 1;

        while (continueGame) {
            System.out.println(" ");
            System.out.println(" Game #" + gameCount);

            int winnerScore = 1;

            for (Player player : players) {
                System.out.println(player.getName() + " 차례입니다. 주사위를 던지려면 1을 입력하세요.");
                if (sc.next().equals("1")) {
                    int firstFaceValue = die.roll();
                    int secondFaceValue = die.roll();
                    int sumFaceValue = firstFaceValue + secondFaceValue;

                    System.out.println(player.getName() + " : 첫번째 주사위 " + firstFaceValue + "   두번째 주사위 " + secondFaceValue + "   두 주사위의 합 : " + sumFaceValue);
                    System.out.println(" ");

                    if (winnerScore < sumFaceValue) {
                        winnerScore = sumFaceValue;
                    }

                    player.setFaceValueSum(sumFaceValue);
                }
            }

            StringBuffer sb = new StringBuffer();
            sb.append("이번 게임의 승자는 ");
            for (Player player : players) {
                if (player.getFaceValueSum() == winnerScore) {
                    player.setPoint(player.getPoint() + 1);
                    sb.append(player.getName()).append(" ");
                }
            }
            sb.append("입니다.");

            System.out.println(sb);
            System.out.println(" ");
            System.out.println("게임을 계속 하시겠습니까? (y/n): ");
            if ((sc.nextLine().equalsIgnoreCase("y"))) {
                gameCount++;
            } else if ((sc.nextLine().equalsIgnoreCase("n"))) {
                System.out.println("게임을 종료합니다.");
                continueGame = false;
            }
        }
        return gameCount;
    }

    private static void endGame(Player[] players, int gameCount) {
        System.out.println("전체 " + gameCount + "게임 중");
        System.out.println(" ");
        StringBuffer sb = new StringBuffer();
        for (Player player : players) {
            sb.append(player.getName() + " " + player.getPoint() + "승   ");
        }
        sb.append("하였습니다.");
        System.out.println(sb);
    }
}

class Die {
    private int faceValue;

    public Die() {
    }

    private void reset() {
        setFaceValue(1);
    }

    public int roll() {
        reset();
        setFaceValue((int)(Math.random() * 6) + getFaceValue());
        return getFaceValue();
    }

    public int getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }
}

class Player {
    private String name;

    private int faceValueSum;

    private int point;
    public int getFaceValueSum() {
        return faceValueSum;
    }

    public void setFaceValueSum(int faceValueSum) {
        this.faceValueSum = faceValueSum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Player(String name) {
        this.name = name;
    }
}
