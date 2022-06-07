package classes;

public class Komentar {
    String username, komentar;

    public Komentar(String userKom){
        String[] arrOfStr = userKom.split(" ", 2);
        this.username=arrOfStr[0];
        this.komentar=arrOfStr[1];
    }
}
