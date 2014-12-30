package triangulum.com.guiaa.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Advertiser implements Serializable {

    private int id;
    private int categoryid;
    private String name;
    private String logo;
    private String latitude;
    private String longitude;
    private String email;
    private ArrayList<String> phones;
    private boolean releasephonecall;
    private boolean releaseselection;
    private Address address;

    public Advertiser() {
    }

    public Advertiser(int id, int categoryid, String name, String logo, String latitude,
                      String longitude, String email, ArrayList<String> phones,
                      boolean releasephonecall, boolean releaseselection, Address adress) {
        this.id = id;
        this.categoryid = categoryid;
        this.name = name;
        this.logo = logo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.email = email;
        this.phones = phones;
        this.releasephonecall = releasephonecall;
        this.releaseselection = releaseselection;
        this.address = adress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isReleasephonecall() {
        return releasephonecall;
    }

    /**
     * @param releasephonecall parâmetro referente à possibilidade de o usuário poder ou não realizar
     *                         uma ligação direta para o anunciante
     * */
    public void setReleasephonecall(boolean releasephonecall) {
        this.releasephonecall = releasephonecall;
    }

    public boolean isReleaseselection() {
        return releaseselection;
    }

    /**
     * @param releaseselection parâmtro referente à possibilidade de o usuário poder ou não copiar
     *                         informações mostradas para o anunciante
     * */
    public void setReleaseselection(boolean releaseselection) {
        this.releaseselection = releaseselection;
    }

    public ArrayList<String> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<String> phones) {
        this.phones = phones;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}