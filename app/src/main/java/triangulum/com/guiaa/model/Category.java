package triangulum.com.guiaa.model;

import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Category implements Serializable {

    private int id;
    private String name;
    private ArrayList<Advertiser> advertisers;
    private int color;

    public Category() {
    }

    public Category(int id, String name, ArrayList<Advertiser> advertisers) {
        this.id = id;
        this.name = name;
        this.advertisers = advertisers;

        generateColor();
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

    private void generateColor(){
        Random rnd = new Random();

        this.color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public int getColor(){
        return this.color;
    }
}