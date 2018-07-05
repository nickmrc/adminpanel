package com.java.hibernate.core.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Delivery")
public class Delivery implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private List<GoodInDelivery> goodInDelivery;

    @OneToOne
    private ContrAgent contrAgent;

    @Column(name = "contractnumber")
    private int contractnumber;

    @Column(name = "conclusiondate")
    private Date conclusiondate;

    @Column(name = "deliverydate")
    private Date deliverydate;


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public List<GoodInDelivery> getGoodInDelivery()
    {
        return goodInDelivery;
    }

    public void setGoodInDelivery(List<GoodInDelivery> goodInDelivery)
    {
        this.goodInDelivery = goodInDelivery;
    }

    public ContrAgent getContrAgent()
    {
        return contrAgent;
    }

    public void setContrAgent(ContrAgent contrAgent)
    {
        this.contrAgent = contrAgent;
    }

    public int getContractnumber()
    {
        return contractnumber;
    }

    public void setContractnumber(int contractnumber)
    {
        this.contractnumber = contractnumber;
    }

    public Date getConclusiondate()
    {
        return conclusiondate;
    }

    public void setConclusiondate(Date conclusiondate)
    {
        this.conclusiondate = conclusiondate;
    }

    public Date getDeliverydate()
    {
        return deliverydate;
    }

    public void setDeliverydate(Date deliverydate)
    {
        this.deliverydate = deliverydate;
    }


    public Delivery() {

    }

    public Delivery(ContrAgent contrAgent, int contractnumber, Date conclusiondate, Date deliverydate)
    {
        this.contrAgent = contrAgent;
        this.contractnumber = contractnumber;
        this.conclusiondate = conclusiondate;
        this.deliverydate = deliverydate;
    }

    public Delivery(List<GoodInDelivery> goodInDelivery, ContrAgent contrAgent, int contractnumber, Date conclusiondate, Date deliverydate)
    {
        this.goodInDelivery = goodInDelivery;
        this.contrAgent = contrAgent;
        this.contractnumber = contractnumber;
        this.conclusiondate = conclusiondate;
        this.deliverydate = deliverydate;
    }

    @Override
    public String toString() {
        return "Delivery:ToString";
    }

}
