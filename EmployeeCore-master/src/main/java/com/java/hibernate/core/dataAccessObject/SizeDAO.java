package com.java.hibernate.core.dataAccessObject;


import com.java.hibernate.core.entity.Size;
import com.java.hibernate.core.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.event.RowEditEvent;

import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;


@javax.faces.bean.ManagedBean (name = "sizeDAO")
@ViewScoped
public class SizeDAO implements Serializable
{

    private Session session = HibernateUtil.getSessionFactory().openSession();;

    private  String name;

    private  int women;

    private int men;

    List<Size> list;

    private Size selectedRow;

    public Size getSelectedRow()
    {
        return selectedRow;
    }

    public void setSelectedRow(Size selectedRow)
    {
        this.selectedRow = selectedRow;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getMen()
    {
        return men;
    }

    public void setMen(int men)
    {
        this.men = men;
    }

    public int getWomen()
    {
        return women;
    }

    public void setWomen(int women)
    {
        this.women = women;
    }




    public void addSize() throws HibernateException
    {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.save(new Size(this.name, this.women, this.men) );
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

    public List<Size> getList()
    {
        List<Size> helplist = getAllSizes();
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



    public List<Size> getAllSizes() throws HibernateException
    {
        List<Size> list = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Size.class);
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

    public void changeSize(int id, Size size) throws HibernateException {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Size siz = (Size) session.get(Size.class, id);
            siz.setName(size.getName());
            siz.setMen(size.getMen());
            siz.setWomen(size.getWomen());
            session.update(siz);
            session.getTransaction().commit();

        } catch (HibernateException e) {

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void onRowEdit(RowEditEvent event) {

        int id = ((Size)event.getObject()).getId();

        int rownum = list.indexOf((Size) event.getObject());
        changeSize(id, this.list.get(rownum));

        String S = "";
    }

    public void onRowCancel(RowEditEvent event)
    {

    }

    public void delete() {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Size siz = (Size) session.get(Size.class, selectedRow.getId());
            session.delete(siz);
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
