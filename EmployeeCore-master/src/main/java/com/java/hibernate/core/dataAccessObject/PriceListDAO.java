package com.java.hibernate.core.dataAccessObject;

import com.java.hibernate.core.entity.*;
import com.java.hibernate.core.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.event.RowEditEvent;

import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@javax.faces.bean.ManagedBean (name = "priceDAO")
@ViewScoped
public class PriceListDAO implements Serializable
{
    private Session session = HibernateUtil.getSessionFactory().openSession();;

    private boolean toUpdate;

    private Date dated;

    private int price;

    private int  goodch;

    private PriceList selectedRow;

    List<PriceList> list;

    List<Good> goods = this.GoodsInit();

    private  List<Good> GoodsInit() {
        GoodDAO goodDAO = new GoodDAO();
       return goodDAO.getAllGoods();
    }

    public List<Good> getGoods()
    {
        return goods;
    }

    public void setGoods(List<Good> goods)
    {
        this.goods = goods;
    }


    public int getGoodch()
    {
        return goodch;
    }

    public void setGoodch(int goodch)
    {
        this.goodch = goodch;
    }


    public Date getDated()
    {
        return dated;
    }

    public void setDated(Date dated)
    {
        this.dated = dated;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }


    public PriceList getSelectedRow()
    {
        return selectedRow;
    }

    public void setSelectedRow(PriceList selectedRow)
    {
        this.selectedRow = selectedRow;
    }


    public void addItem() throws HibernateException
    {
//        Criteria criteria = session.createCriteria(Category.class);
//        this.list = criteria.list();

        try {
            Good good = null;

            session = HibernateUtil.getSessionFactory().openSession();
            if (getGoodch()>0)
            {
                good = getGoods().get(getGoodch()-1);
            }

            this.toUpdate = true;
            session.save(new PriceList(good, this.price, this.dated) );
        }
        catch (HibernateException e) {
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            //не уверен, что теперь оно тут надо
            getList();

        }
    }

    public List<PriceList> getList()
    {
        List<PriceList> helplist = getAllPrices();
        if (this.list==null)
        {
            list = helplist;
        }
        else
        {
            if (this.list.size()!= helplist.size())
                list = helplist;
            else {
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



    public List<PriceList> getAllPrices() throws HibernateException
    {
        List<PriceList> list = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(PriceList.class);
            list = criteria.list();

//            Query query = session.createQuery("FROM Good");
//            list = (List<Good>) query.list();

            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }

        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return list;
        }
    }

    public void changeItem(int id, PriceList priceList) throws HibernateException {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            PriceList pr = (PriceList) session.get(PriceList.class, id);
            pr.setPrice(priceList.getPrice());
            pr.setDated(priceList.getDated());

            if (getGoodch()>0)
            {
                pr.setGood(getGoods().get(getGoodch()-1));
            }
            else
            {
                pr.setGood(null);
            }

           this.toUpdate = true;


            session.update(pr);
            session.getTransaction().commit();

        } catch (HibernateException e) {

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                this.goodch=0;

            }
        }
    }

    public void onRowEdit(RowEditEvent event) {

        int id = ((PriceList)event.getObject()).getId();

        int rownum = list.indexOf((PriceList) event.getObject());
        changeItem(id, this.list.get(rownum));

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
            PriceList cat = (PriceList) session.get(PriceList.class, selectedRow.getId());
            session.delete(cat);
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
