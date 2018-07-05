package com.java.hibernate.core.entity;

import javax.persistence.*;

@Entity
@Table(name="LegalStatus")
public class LegalStatus implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "acronym")
    private String acronym;

    public LegalStatus() {
    }

    public LegalStatus(String name, String acronym) {
        this.setId(-1);
        this.setName(name);
        this.setAcronym(acronym);

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

    public String getAcronym()
    {
        return acronym;
    }

    public void setAcronym(String acronym)
    {
        this.acronym = acronym;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
