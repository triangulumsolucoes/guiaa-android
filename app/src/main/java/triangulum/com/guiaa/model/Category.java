package triangulum.com.guiaa.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {

    private int id;
    private String name;
    private ArrayList<Advertiser> advertisers;

    public Category() {
    }

    public Category(int id, String name, ArrayList<Advertiser> advertisers) {
        this.id = id;
        this.name = name;
        this.advertisers = advertisers;
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

    public ArrayList<Advertiser> getAdvertisers() {
        return advertisers;
    }

    public void setAdvertisers(ArrayList<Advertiser> advertisers) {
        this.advertisers = advertisers;
    }
}
