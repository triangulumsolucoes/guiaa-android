package triangulum.com.guiaa.model;

/**
 * Created by triangulum on 29/12/14.
 */
public class Cidade {

    private int id;
    private String nome;

    public Cidade(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





}
