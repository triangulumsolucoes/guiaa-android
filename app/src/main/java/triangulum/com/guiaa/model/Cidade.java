package triangulum.com.guiaa.model;

import java.io.Serializable;

/**
 * Created by triangulum on 29/12/14.
 */

public class Cidade implements Serializable{

    private int id;
    private String nome;

    public Cidade(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setName(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
