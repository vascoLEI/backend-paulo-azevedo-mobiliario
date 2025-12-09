package pt.pauloazevedomobiliario.backend.model;

public class Produto {

    private int id;
    private String name;
    private String description;
    private String image;
    private int categoria_id;

    public Produto(int id, String name, String description, String image, int categoria_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.categoria_id = categoria_id;
    }

    public Produto() {}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }
}
