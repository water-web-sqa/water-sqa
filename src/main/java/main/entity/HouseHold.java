package main.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "household")
public class HouseHold {
    private String codeHouse;
    private String nameHouse;
    private String address;
    private Date dataBirth;
    private int idSupplier;
    private String typeHouse;

    public HouseHold() {
    }

    public HouseHold(String codeHouse, String nameHouse, String address, Date dataBirth, int idSupplier) {
        this.codeHouse = codeHouse;
        this.nameHouse = nameHouse;
        this.address = address;
        this.dataBirth = dataBirth;
        this.idSupplier = idSupplier;
    }

    @Id
    @Column(name = "code_house")
    public String getCodeHouse() {
        return codeHouse;
    }

    public void setCodeHouse(String codeHouse) {
        this.codeHouse = codeHouse;
    }

    @Basic
    @Column(name = "name_house")
    public String getNameHouse() {
        return nameHouse;
    }

    public void setNameHouse(String nameHouse) {
        this.nameHouse = nameHouse;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_birth")
    public Date getDataBirth() {
        return dataBirth;
    }

    public void setDataBirth(Date dataBirth) {
        this.dataBirth = dataBirth;
    }

    @Column(name = "id_supplier")
    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    @Basic
    @Column(name = "type_house")
    public String getTypeHouse() {
        return typeHouse;
    }

    public void setTypeHouse(String typeHouse) {
        this.typeHouse = typeHouse;
    }
}
