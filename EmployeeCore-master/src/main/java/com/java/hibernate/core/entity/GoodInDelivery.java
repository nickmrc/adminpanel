package com.java.hibernate.core.entity;

import javax.persistence.*;

@Entity
@Table(name="GoodInDelivery")
public class GoodInDelivery implements java.io.Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Delivery delivery;

    @OneToOne
    private Good good;

    @Column(name = "price")
    private int price;

    @Column(name = "quantity")
    private int quantity;

    public GoodInDelivery(Delivery delivery, Good good, int price, int quantity)
    {
        this.setId(-1);
        this.delivery = delivery;
        this.good = good;
        this.price = price;
        this.quantity = quantity;
    }

    public GoodInDelivery() {
        this.good = null;
        this.delivery = null;
        this.price = 0;
        this.quantity = 0;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Delivery getDelivery()
    {
        return delivery;
    }

    public void setDelivery(Delivery delivery)
    {
        this.delivery = delivery;
    }

    public Good getGood()
    {
        return good;
    }

    public void setGood(Good good)
    {
        this.good = good;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "GoodInDelivery: дописать";
    }

}
