package homework_2;

// 실습 문제 3번
public class Homework_2_3 {
    public static void main(String[] args) {
        Song object = new Song("Dancing Queen", "ABBA", 1978, "스웨덴");
        object.show();
    }

}

class Song {
    public Song() {
    }

    public Song(String title, String artist, int year, String country) {
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.country = country;
    }

    public String title;
    public String artist;
    public int year;
    public String country;

    public void show() {
        System.out.println(year + "년 " + country + "국적의 " + artist + "가 부른 " + title);
    }
}
