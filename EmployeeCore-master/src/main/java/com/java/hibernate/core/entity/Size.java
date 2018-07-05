package com.java.hibernate.core.entity;

import javax.persistence.*;

@Entity
@Table(name="Size")
public class Size implements java.io.Serializable {
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

    @Column(name = "women")
    private int women;

    @Column(name = "men")
    private int men;

   public Size() {

   }

    public Size(String name, int women, int men) {
        this.name = name;
        this.women = women;
        this.men = men;
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

    public int getWomen()
    {
        return women;
    }

    public void setWomen(int women)
    {
        this.women = women;
    }

    public int getMen()
    {
        return men;
    }

    public void setMen(int men)
    {
        this.men = men;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
