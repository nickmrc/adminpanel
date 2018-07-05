package com.java.hibernate.core.dataAccessObject;

import com.java.hibernate.core.entity.LegalStatus;
import com.java.hibernate.core.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.event.RowEditEvent;

import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;


@javax.faces.bean.ManagedBean (name = "legalDAO")
@ViewScoped
public class LegalStatusDAO implements Serializable
{

    private Session session = HibernateUtil.getSessionFactory().openSession();;

    private  String name;

    private  String acronym;

    private List<LegalStatus> list;

    private LegalStatus selectedRow;

    public LegalStatus getSelectedRow()
    {
        return selectedRow;
    }

    public void setSelectedRow(LegalStatus selectedRow)
    {
        this.selectedRow = selectedRow;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAcronym()
    {
        return acronym;
    }

    public void setAcronym(String acronym)
    {
        this.acronym = acronym;
    }


    public void addStatus() throws HibernateException
    {
//        Criteria criteria = session.createCriteria(Category.class);
//        this.list = criteria.list();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.save(new LegalStatus(this.name, this.acronym) );
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

    public List<LegalStatus> getList()
    {
        List<LegalStatus> helplist = getAllStatuses();
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



    public List<LegalStatus> getAllStatuses() throws HibernateException
    {
        List<LegalStatus> list = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(LegalStatus.class);
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

    public void changeStatus(int id, LegalStatus legal ) throws HibernateException {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            LegalStatus cat = (LegalStatus) session.get(LegalStatus.class, id);
            cat.setName(legal.getName());
            cat.setAcronym(legal.getAcronym());
            session.update(cat);
            session.getTransaction().commit();

        } catch (HibernateException e) {

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void onRowEdit(RowEditEvent event) {

        int id = ((LegalStatus)event.getObject()).getId();

        int rownum = list.indexOf((LegalStatus) event.getObject());
        changeStatus(id, this.list.get(rownum));

        String S = "";

//        FacesMessage msg = new FacesMessage("Car Edited", ((Car) event.getObject()).getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event)
    {
//        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Category) event.getObject()).getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void delete() {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            LegalStatus cat = (LegalStatus) session.get(LegalStatus.class, selectedRow.getId());
            session.delete(cat);
            session.getTransaction().commit();

        } catch (HibernateException e) {

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            selectedRow=null;
        }






//        this.list.remove(rownum);
    }

    //    public UsersDataSet get(long id) throws HibernateException
//    {
//        return (UsersDataSet) session.get(UsersDataSet.class, id);
//    }


}
