package com.java.hibernate.core.dataAccessObject;

import com.java.hibernate.core.entity.Category;
import com.java.hibernate.core.entity.GoodInDelivery;
import com.java.hibernate.core.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.primefaces.event.RowEditEvent;

import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;


@javax.faces.bean.ManagedBean (name = "goodInDeliveryDAO")
@ViewScoped
public class GoodInDeliveryDAO implements Serializable
{



    private Session session = HibernateUtil.getSessionFactory().openSession();;



    private  String description;

    private  String name;

    List<GoodInDelivery> list;

    public List<GoodInDelivery> getByDelveryId(int delivery_id)
    {
        List<GoodInDelivery> helplist;

        Criteria criteria = session.createCriteria(GoodInDelivery.class);
        list = (List<GoodInDelivery>) criteria.add(Restrictions.eq("delivery_id", delivery_id)).list();
        return list;
    }

    public void addGoodsInDelivery(List<GoodInDelivery> list) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();

            for (GoodInDelivery i :list)
            {
                session.save(new GoodInDelivery(i.getDelivery(), i.getGood(), i.getPrice(), i.getQuantity()) );
            }
        }
        catch (HibernateException e) {

        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    boolean boolka;

    private Category selectedRow;

    public Category getSelectedRow()
    {
        return selectedRow;
    }

    public void setSelectedRow(Category selectedRow)
    {
        this.selectedRow = selectedRow;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public boolean isBoolka()
    {
        return boolka;
    }

    public void setBoolka(boolean boolka)
    {
        this.boolka = boolka;
    }


//    public void addCategory() throws HibernateException
//    {
////        Criteria criteria = session.createCriteria(Category.class);
////        this.list = criteria.list();
//
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            session.save(new Category(this.name, this.description) );
//        }
//        catch (HibernateException e) {
//
//        }
//        finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//            getList();
//        }
//
//
//    }
//
//    public List<Category> getList()
//    {
//        List<Category> helplist = getAllCategories();
//        if (this.list==null)
//        {
//            list = helplist;
//        }
//        else
//        {
//            if (this.list.size()!= helplist.size())
//                list = helplist;
//            else {
//                //размеры одинаковые, но надо сравнить поэлементно?
//            }
//        }
//        return list;
//    }
//
//
//
//
//
//    public List<Category> getAllCategories() throws HibernateException
//    {
//        List<Category> list = null;
//
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            Criteria criteria = session.createCriteria(Category.class);
//            list = criteria.list();
//        }
//        catch (HibernateException e) {
//        }
//
//        finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//            return list;
//        }
//    }
//
//    public void changeCategory(int id, Category category) throws HibernateException {
//
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            Category cat = (Category) session.get(Category.class, id);
//            cat.setName(category.getName());
//            cat.setDescription(category.getDescription());
//            session.update(cat);
//            session.getTransaction().commit();
//
//        } catch (HibernateException e) {
//
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }
//    }
//
//    public void onRowEdit(RowEditEvent event) {
//
//        int id = ((Category)event.getObject()).getId();
//
//        int rownum = list.indexOf((Category) event.getObject());
//        changeCategory(id, this.list.get(rownum));
//
//        String S = "";
//
////        FacesMessage msg = new FacesMessage("Car Edited", ((Car) event.getObject()).getId());
////        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//
//    public void onRowCancel(RowEditEvent event)
//    {
////        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Category) event.getObject()).getId());
////        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//
//    public void delete()
//    {
//
//        try
//        {
//            session = HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            Category cat = (Category) session.get(Category.class, selectedRow.getId());
//            session.delete(cat);
//            session.getTransaction().commit();
//
//        } catch (HibernateException e)
//        {
//
//        } finally
//        {
//            if (session != null && session.isOpen())
//            {
//                session.close();
//            }
//            selectedRow = null;
//        }
//    }



}
