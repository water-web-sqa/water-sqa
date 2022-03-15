package main.entity;

import javax.persistence.*;

@Entity
@Table(name = "water_supplier")
public class WaterSupplier {
    private Integer id;
    private String nameSupplier;
    private String address;
    private String sdt;
    private String fax;

    public WaterSupplier(Integer id, String nameSupplier, String address, String sdt, String fax) {
        this.id = id;
        this.nameSupplier = nameSupplier;
        this.address = address;
        this.sdt = sdt;
        this.fax = fax;
    }

    public WaterSupplier() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_supplier")
    public String getNameSupplier() {
        return nameSupplier;
    }

    public void setNameSupplier(String nameSupplier) {
        this.nameSupplier = nameSupplier;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "sdt")
    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Basic
    @Column(name = "fax")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
