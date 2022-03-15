package main.beans;

public class AddressBeans {
    private String city, distrinct, ward;

    public AddressBeans() {
    }

    public AddressBeans(String city, String distrinct, String ward) {
        this.city = city;
        this.distrinct = distrinct;
        this.ward = ward;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrinct() {
        return distrinct;
    }

    public void setDistrinct(String distrinct) {
        this.distrinct = distrinct;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }
}
