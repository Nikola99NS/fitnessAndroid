package classes;

public class Teretana {
    private String name, adresa;
    float ocena;
    int imageId;

    public Teretana(String name, String adresa, float ocena,  int imageId) {
        this.name = name;
        this.adresa = adresa;

        this.ocena = ocena;

        this.imageId = imageId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public float getOcena() {
        return ocena;
    }

    public void setOcena(float ocena) {
        this.ocena = ocena;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
