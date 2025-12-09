package pt.pauloazevedomobiliario.backend.model;

public class Contactos {

    private int id;
    private int telemovel;
    private String gmail;
    private String morada;
    private int redesSociais_id;

    public Contactos(int id, int telemovel, String gmail, String morada, int redesSociais_id) {
        this.id = id;
        this.telemovel = telemovel;
        this.gmail = gmail;
        this.morada = morada;
        this.redesSociais_id = redesSociais_id;
    }

    public Contactos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getRedesSociais_id() {
        return redesSociais_id;
    }

    public void setRedesSociais_id(int redesSociais_id) {
        this.redesSociais_id = redesSociais_id;
    }
}
