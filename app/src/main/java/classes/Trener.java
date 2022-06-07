package classes;

public class Trener {

    private String ime, godine, kursevi, gym, user;
    int idSlike;

    public Trener(String ime, String godine, String kursevi,int idSlike, String gym,String user){
        this.ime=ime;
        this.godine=godine;
        this.kursevi=kursevi;
        this.idSlike=idSlike;
        this.gym=gym;
        this.user=user;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getGodine() {
        return godine;
    }

    public void setGodine(String godine) {
        this.godine = godine;
    }

    public String getKursevi() {
        return kursevi;
    }

    public void setKursevi(String kursevi) {
        this.kursevi = kursevi;
    }

    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getIdSlike() {
        return idSlike;
    }

    public void setIdSlike(int idSlike) {
        this.idSlike = idSlike;
    }
}


