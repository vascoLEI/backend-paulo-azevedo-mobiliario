package pt.pauloazevedomobiliario.backend.model;

public class RedesSociais {

    private int id;
    private String name;
    private String icone;
    private String url;

    public RedesSociais(int id, String name, String icone, String url) {
        this.id = id;
        this.name = name;
        this.icone = icone;
        this.url = url;
    }

    public RedesSociais() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
