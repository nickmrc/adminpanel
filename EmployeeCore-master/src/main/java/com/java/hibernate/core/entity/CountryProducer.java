package com.java.hibernate.core.entity;

import javax.persistence.*;

@Entity
@Table(name="CountryProd")
public class CountryProducer implements java.io.Serializable {
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

    @Column(name = "doubled")
    private String doubled;

    @Column(name = "triple")
    private String triple;

    @Column(name = "code")
    private int code;

    public String getDoubled()
    {
        return doubled;
    }

    public void setDoubled(String doubled)
    {
        this.doubled = doubled;
    }

    public String getTriple()
    {
        return triple;
    }

    public void setTriple(String triple)
    {
        this.triple = triple;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }


    public CountryProducer() {

    }

    public CountryProducer(String name, String doubled, String triple, int code) {
        this.name = name;
        this.doubled = doubled;
        this.triple = triple;
        this.code = code;
    }

    public int getId() {
        return this.id;
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

    @Override
    public String toString() {
        return this.getName()+"  "+this.getDoubled();
    }

}
