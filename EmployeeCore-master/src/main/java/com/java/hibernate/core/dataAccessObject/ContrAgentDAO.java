package com.java.hibernate.core.dataAccessObject;

import com.java.hibernate.core.entity.ContrAgent;
import com.java.hibernate.core.entity.LegalStatus;
import com.java.hibernate.core.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.event.RowEditEvent;

import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;


@javax.faces.bean.ManagedBean (name = "contrDAO")
@ViewScoped
public class ContrAgentDAO implements Serializable
{

    private Session session = HibernateUtil.getSessionFactory().openSession();
    ;

    private String name;

    private String inn;

    private String legaladdress;

    private String phonenumber;

    private boolean toUpdate;
    private List<ContrAgent> list;



    private LegalStatus selectedRow;

    public LegalStatus getSelectedRow()
    {
        return selectedRow;
    }

    public void setSelectedRow(LegalStatus selectedRow)
    {
        this.selectedRow = selectedRow;
    }

    public int getStatusch()
    {
        return statusch;
    }

    public void setStatusch(int statusch)
    {
        this.statusch = statusch;
    }

    private int statusch;

    public List<LegalStatus> getLegalStatuses()
    {
        return legalStatuses;
    }

    public void setLegalStatuses(List<LegalStatus> legalStatuses)
    {
        this.legalStatuses = legalStatuses;
    }

    List<LegalStatus> legalStatuses = this.LegalInit();

    private List<LegalStatus> LegalInit() {
        LegalStatusDAO categDAO = new LegalStatusDAO();
        return categDAO.getAllStatuses();
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getInn ()
    {
        return inn;
    }

    public void setInn (String inn)
    {
        this.inn = inn;
    }

    public String getLegaladdress ()
    {
        return legaladdress;
    }

    public void setLegaladdress (String legaladdress)
    {
        this.legaladdress = legaladdress;
    }

    public String getPhonenumber ()
    {
        return phonenumber;
    }

    public void setPhonenumber (String phonenumber)
    {
        this.phonenumber = phonenumber;
    }


        public void addAgent() throws HibernateException
        {

            try
            {
                LegalStatus lg = null;
                session = HibernateUtil.getSessionFactory().openSession();

                if (getStatusch()>0)
                {
                   lg = getLegalStatuses().get(getStatusch()-1);
                }
                session.save(new ContrAgent(this.name, this.inn, this.legaladdress, this.phonenumber, lg));
            } catch (HibernateException e)
            {

            } finally
            {
                if (session != null && session.isOpen())
                {
                    session.close();
                }
                getList();
            }


        }

        public List<ContrAgent> getList ()
        {
            List<ContrAgent> helplist = getAgents();
            if (this.list == null)
            {
                list = helplist;
            } else
            {
                if (this.list.size() != helplist.size())
                    list = helplist;
                else
                {
                    if (toUpdate)
                    {
                        list = helplist;
                        toUpdate = false;
                    }
                    //размеры одинаковые, но надо сравнить поэлементно?
                }
            }
            return list;
        }


        public List<ContrAgent> getAgents() throws HibernateException
        {
            List<ContrAgent> list = null;

            try
            {
                session = HibernateUtil.getSessionFactory().openSession();
                Criteria criteria = session.createCriteria(ContrAgent.class);
                list = criteria.list();
            } catch (HibernateException e)
            {
            } finally
            {
                if (session != null && session.isOpen())
                {
                    session.close();
                }
                return list;
            }
        }

        public void changeAgent(int id, ContrAgent legal ) throws HibernateException {

        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            ContrAgent cat = (ContrAgent) session.get(ContrAgent.class, id);
            cat.setName(legal.getName());
            cat.setInn(legal.getInn());
            cat.setLegaladdress(legal.getLegaladdress());

            if (getStatusch()>0)
            {
                cat.setLegalStatus(getLegalStatuses().get(getStatusch()-1));
            }
            else
            {
                cat.setLegalStatus(null);
            }
            this.toUpdate = true;

            session.update(cat);
            session.getTransaction().commit();

        } catch (HibernateException e)
        {

        } finally
        {
            if (session != null && session.isOpen())
            {
                session.close();
            }
        }
    }

        public void onRowEdit (RowEditEvent event){

        int id = ((ContrAgent) event.getObject()).getId();

        int rownum = list.indexOf((ContrAgent) event.getObject());
        changeAgent(id, this.list.get(rownum));

        String S = "";
    }

//        FacesMessage msg = new FacesMessage("Car Edited", ((Car) event.getObject()).getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);


        public void onRowCancel (RowEditEvent event)
        {
//        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Category) event.getObject()).getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        public void delete () {

        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            ContrAgent cat = (ContrAgent) session.get(ContrAgent.class, selectedRow.getId());
            session.delete(cat);
            session.getTransaction().commit();

        } catch (HibernateException e)
        {

        } finally
        {
            if (session != null && session.isOpen())
            {
                session.close();
            }
            selectedRow = null;
        }


//        this.list.remove(rownum);
    }

        //    public UsersDataSet get(long id) throws HibernateException
//    {
//        return (UsersDataSet) session.get(UsersDataSet.class, id);
//    }




}


