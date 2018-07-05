package com.java.hibernate.core.entity;

import javax.persistence.*;

@Entity
@Table(name="Good")
public class Good implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "discription")
    private String discription;

    @Column(name = "vendorcode")
    private String vendorcode;

    @Column(name = "count")
    private int count;

    @OneToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name="category_id")
    private Category category;

    @OneToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name="size_id")
    private Size size;


    @OneToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name="countryprod_id")
    private CountryProducer producer;


    @OneToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name="color_id")
    private Color color;



    public Good ()
    {

    }

    public Good(String name, String discription, String vendorcode, int count)
    {
        this.setId(-1);
        this.name = name;
        this.discription = discription;
        this.vendorcode = vendorcode;
        this.count = count;
    }


    public Good(String name, String discription, String vendorcode, int count, Category category, Size size, CountryProducer producer, Color color)
    {
        this.setId(-1);
        this.name = name;
        this.discription = discription;
        this.vendorcode = vendorcode;
        this.count = count;
        this.category = category;
        this.size = size;
        this.producer = producer ;
        this.color = color;
    }

    public CountryProducer getProducer()
    {
        return producer;
    }

    public void setProducer(CountryProducer producer)
    {
        this.producer = producer;
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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getVendorcode()
    {
        return vendorcode;
    }

    public void setVendorcode(String vendorcode)
    {
        this.vendorcode = vendorcode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Size getSize()
    {
        return size;
    }

    public void setSize(Size size)
    {
        this.size = size;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
