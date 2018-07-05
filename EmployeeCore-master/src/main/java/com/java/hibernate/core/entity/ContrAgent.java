package com.java.hibernate.core.entity;

import javax.persistence.*;

@Entity
@Table(name="Contragent")
public class ContrAgent implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private LegalStatus legalstatus;

    @Column(name = "name")
    private String name;

    @Column(name = "inn")
    private String inn;

    @Column(name = "legaladdress")
    private String legaladdress;

    @Column(name = "phonenumber")
    private String phonenumber;



    public ContrAgent() {
    }

    public ContrAgent(String name, String inn, String legaladdress, String phonenumber, LegalStatus lg) {
        this.setId(-1);
        this.setName(name);
        this.inn = inn;
        this.legaladdress = legaladdress;
        this.phonenumber = phonenumber;
        this.legalstatus = lg;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getInn()
    {
        return inn;
    }

    public void setInn(String inn)
    {
        this.inn = inn;
    }

    public String getLegaladdress()
    {
        return legaladdress;
    }

    public void setLegaladdress(String legaladdress)
    {
        this.legaladdress = legaladdress;
    }

    public String getPhonenumber()
    {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber)
    {
        this.phonenumber = phonenumber;
    }

    public LegalStatus getLegalStatus()
    {
        return this.legalstatus;
    }

    public void setLegalStatus(LegalStatus legalStatus)
    {
        this.legalstatus = legalStatus;
    }


    @Override
    public String toString() {
        return this.getLegalStatus().getAcronym()+" "+this.name;
    }

}
