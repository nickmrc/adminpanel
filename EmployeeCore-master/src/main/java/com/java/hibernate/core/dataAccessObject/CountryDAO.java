package com.java.hibernate.core.dataAccessObject;


import com.java.hibernate.core.entity.CountryProducer;
import com.java.hibernate.core.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.event.RowEditEvent;

import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;


@javax.faces.bean.ManagedBean (name = "countryDAO")
@ViewScoped
public class CountryDAO implements Serializable
{

    private Session session = HibernateUtil.getSessionFactory().openSession();;

    private String name;

    private int code;

    private String triple;

    private String doubled;

    List<CountryProducer> list;

    private CountryProducer selectedRow;


    public CountryProducer getSelectedRow()
    {
        return selectedRow;
    }

    public void setSelectedRow(CountryProducer selectedRow)
    {
        this.selectedRow = selectedRow;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

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

    public void addCountry() throws HibernateException
    {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.save(new CountryProducer(this.name, this.doubled, this.triple, this.code) );
        }
        catch (HibernateException e) {

        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            getList();
        }
    }

    public List<CountryProducer> getList()
    {
        List<CountryProducer> helplist = getAllCountries();
        if (this.list==null)
        {
            list = helplist;
        }
        else
        {
            if (this.list.size()!= helplist.size())
                list = helplist;
            else {
                //размеры одинаковые, но надо сравнить поэлементно?
            }
        }
        return list;
    }



    public List<CountryProducer> getAllCountries() throws HibernateException
    {
        List<CountryProducer> list = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(CountryProducer.class);
            list = criteria.list();
        }
        catch (HibernateException e) {
        }

        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return list;
        }
    }

    public void changeCountry(int id, CountryProducer producer) throws HibernateException {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            CountryProducer conp = (CountryProducer) session.get(CountryProducer.class, id);

            conp.setName(producer.getName());
            conp.setTriple(producer.getTriple());
            conp.setDoubled(producer.getDoubled());
            conp.setCode(producer.getCode());

            session.update(conp);
            session.getTransaction().commit();

        } catch (HibernateException e) {

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void onRowEdit(RowEditEvent event) {

        int id = ((CountryProducer)event.getObject()).getId();

        int rownum = list.indexOf((CountryProducer) event.getObject());
        changeCountry(id, this.list.get(rownum));

        String S = "";
    }

    public void onRowCancel(RowEditEvent event)
    {

    }

    public void delete() {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            CountryProducer conp = (CountryProducer) session.get(CountryProducer.class, selectedRow.getId());
            session.delete(conp);
            session.getTransaction().commit();

        } catch (HibernateException e) {

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            selectedRow=null;
        }

    }



}
