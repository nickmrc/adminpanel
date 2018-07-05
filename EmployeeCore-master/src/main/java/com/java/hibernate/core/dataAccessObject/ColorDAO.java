package com.java.hibernate.core.dataAccessObject;

import com.java.hibernate.core.entity.Color;
import com.java.hibernate.core.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.event.RowEditEvent;

import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;


@javax.faces.bean.ManagedBean (name = "colorDAO")
@ViewScoped
public class ColorDAO implements Serializable
{

    private Session session = HibernateUtil.getSessionFactory().openSession();;



    private  String name;

    public String getEngname()
    {
        return engname;
    }

    public void setEngname(String engname)
    {
        this.engname = engname;
    }

    public int getR()
    {
        return r;
    }

    public void setR(int r)
    {
        this.r = r;
    }

    public int getG()
    {
        return g;
    }

    public void setG(int g)
    {
        this.g = g;
    }


    public int getB()
    {
        return b;
    }

    public void setB(int b)
    {
        this.b = b;
    }

    private  int b;
    private  int g;
    private  int r;
    private  String engname;






    List<Color> list;

    private Color selectedRow;

    public Color getSelectedRow()
    {
        return selectedRow;
    }

    public void setSelectedRow(Color selectedRow)
    {
        this.selectedRow = selectedRow;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }



    public void addColor() throws HibernateException
    {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.save(new Color(this.name, this.engname, this.r, g, b) );
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

    public List<Color> getList()
    {
        List<Color> helplist = getAllColors();
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



    public List<Color> getAllColors() throws HibernateException
    {
        List<Color> list = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Color.class);
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

    public void changeColor(int id, Color color) throws HibernateException {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Color cat = (Color) session.get(Color.class, id);
            cat.setName(color.getName());
            cat.setEngname(color.getEngname());
            cat.setR(color.getR());
            cat.setG(color.getG());
            cat.setB(color.getB());
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

        int id = ((Color)event.getObject()).getId();

        int rownum = list.indexOf((Color) event.getObject());
        changeColor(id, this.list.get(rownum));


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
            Color cat = (Color) session.get(Color.class, selectedRow.getId());
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
