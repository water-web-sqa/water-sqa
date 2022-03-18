package main.entity;

import javax.persistence.*;

@Entity
@Table(name = "bill")
public class Bill {
    private Integer id;
    private Integer idStaff;
    private Integer idWaterMoney;
    private Float sumMoney;

    public Bill() {
    }

    public Bill(Integer id, Integer idStaff, Integer idWaterMoney, Float sumMoney) {
        this.id = id;
        this.idStaff = idStaff;
        this.idWaterMoney = idWaterMoney;
        this.sumMoney = sumMoney;
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
    @Column(name = "id_staff")
    public Integer getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(Integer idStaff) {
        this.idStaff = idStaff;
    }

    @Basic
    @Column(name = "id_water_money")
    public Integer getIdWaterMoney() {
        return idWaterMoney;
    }

    public void setIdWaterMoney(Integer idWaterMoney) {
        this.idWaterMoney = idWaterMoney;
    }

    @Basic
    @Column(name = "sum_money")
    public Float getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Float sumMoney) {
        this.sumMoney = sumMoney;
    }
}
