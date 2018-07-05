package com.java.hibernate.core.entity;

import javax.persistence.*;

@Entity
@Table(name="Color")
public class Color implements java.io.Serializable {
    /**
     *
     */


    private static final long serialVersionUID = 1L;

    private int id;

    @Column(name = "name")
    private String name;

    public String getEngname()
    {
        return engname;
    }

    public void setEngname(String engname)
    {
        this.engname = engname;
    }

    @Column(name = "engname")
    private String engname;

    public int getR()
    {
        return r;
    }

    public void setR(int r)
    {
        this.r = r;
    }

    @Column(name = "r")
    private int r;

    public int getG()
    {
        return g;
    }

    public void setG(int g)
    {
        this.g = g;
    }

    @Column(name = "g")
    private int g;

    public int getB()
    {
        return b;
    }

    public void setB(int b)
    {
        this.b = b;
    }

    @Column(name = "b")
    private int b;


    public Color()
    {
    }

    public Color(String name, String engname, int r, int g, int b)
    {
        this.setId(-1);
        this.setName(name);
        this.setEngname(engname);
        this.r = r;
        this.g=g;
        this.b = b;
    }



    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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




    @Override
    public String toString() {
        return this.getName();
    }

}
