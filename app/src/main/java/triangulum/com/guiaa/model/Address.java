package triangulum.com.guiaa.model;

public class Address {

    private String street;
    private String district;
    private String number;
    private String complement;
    private String zipcode;
    private String city;
    private String state;

    public Address(){}

    public Address(String street, String district, String number, String complement, String zipcode,
                   String city, String state) {
        this.street = street;
        this.district = district;
        this.number = number;
        this.complement = complement;
        this.zipcode = zipcode;
        this.city = city;
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
