package main.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "water_money")
public class WaterMoney {
    private int id;
    private Date dateWater;
    private int numberWater;
    private String codeHouse;
    @CreationTimestamp
    @Column(name = "created_at")
    protected LocalDateTime createdAt;

    public WaterMoney() {
    }

    public WaterMoney(int id, Date dateWater, int numberWater, String codeHouse) {
        this.id = id;
        this.dateWater = dateWater;
        this.numberWater = numberWater;
        this.codeHouse = codeHouse;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "date_water")
    public Date getDateWater() {
        return dateWater;
    }

    public void setDateWater(Date dateWater) {
        this.dateWater = dateWater;
    }

    @Basic
    @Column(name = "number_water")
    public int getNumberWater() {
        return numberWater;
    }

    public void setNumberWater(int numberWater) {
        this.numberWater = numberWater;
    }

    @Column(name = "code_house")
    public String getCodeHouse() {
        return codeHouse;
    }

    public void setCodeHouse(String codeHouse) {
        this.codeHouse = codeHouse;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
