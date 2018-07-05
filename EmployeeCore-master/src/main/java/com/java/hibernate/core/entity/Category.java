package com.java.hibernate.core.entity;

import javax.persistence.*;

@Entity
@Table(name="Category")
public class Category implements java.io.Serializable {
    /**
     *
     */


    private static final long serialVersionUID = 1L;

    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Category()
    {
    }

    public Category(String name, String description)
    {
        this.setId(-1);
        this.setName(name);
        this.setDescription(description);

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String discription) {
        this.description = discription;
    }


    @Override
    public String toString() {
        return this.getName();
    }

}
