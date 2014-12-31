package triangulum.com.guiaa.model;

import java.io.Serializable;

/**
 * Created by triangulum on 29/12/14.
 */

public class City implements Serializable{

    private int id;
    private String name;

    public City(int id, String nome) {
        this.id = id;
        this.name = nome;
    }

    public String getNome() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
