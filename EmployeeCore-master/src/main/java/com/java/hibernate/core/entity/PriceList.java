package com.java.hibernate.core.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Pricelist")
public class PriceList implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Good good;

    @Column(name = "price")
    private int price;

    @Column(name = "dated")
    private Date dated;

    public PriceList()
    {
    }

    public PriceList(Good good, int price, Date dated)
    {
        setId(-1);
        this.good = good;
        this.price = price;
        this.dated = dated;
    }

    public int getId() {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public Good getGood()
    {
        return good;
    }

    public void setGood(Good good)
    {
        this.good = good;
    }

    public Date getDated()
    {
        return dated;
    }

    public void setDated(Date dated)
    {
        this.dated = dated;
    }

    @Override
    public String toString() {
        return this.good.getName()+" "+this.price;
    }

}
