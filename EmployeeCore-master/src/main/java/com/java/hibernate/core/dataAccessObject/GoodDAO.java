package com.java.hibernate.core.dataAccessObject;

import com.java.hibernate.core.entity.*;
import com.java.hibernate.core.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


@javax.faces.bean.ManagedBean (name = "goodDAO")
@ViewScoped
public class GoodDAO implements Serializable
{
    private Session session= HibernateUtil.getSessionFactory().openSession();;

    private  String description;

    private  String name;

    private boolean toUpdate;

    private  int count;

    private  String vendorcode;

    private Good selectedRow;

    private int categorych;

    private int sizech;

    private  int producerch;

    private  int colorch;

    List<Good> list;

    List<Good> filtredlist;

    List<Category> categories = this.CategoriesInit();

    List<Size> sizes = this.SizesInit();

    List<CountryProducer> countryProducers = this.ProdInit();

    List<Color> colors = this.ColorsInit();

    private  List<Category> CategoriesInit() {
        CategDAO categDAO = new CategDAO();
       return categDAO.getAllCategories();
    }

    private  List<Size> SizesInit() {
        SizeDAO sizeDAO = new SizeDAO();
        return sizeDAO.getAllSizes();
    }

    private  List<CountryProducer> ProdInit() {
        CountryDAO countryDAO = new CountryDAO();
        return countryDAO.getAllCountries();
    }

    private  List<Color>  ColorsInit() {
        ColorDAO colorDAO = new ColorDAO();
        return colorDAO.getAllColors();
    }

    public List<Category> getCategories()
    {
        return categories;
    }

    public void setCategories(List<Category> categories)
    {
        this.categories = categories;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes)
    {
        this.sizes = sizes;
    }

    public List<CountryProducer> getCountryProducers()
    {
        return countryProducers;
    }

    public void setCountryProducers(List<CountryProducer> countryProducers) {
        this.countryProducers = countryProducers;
    }

    public List<Color> getColors()
    {
        return colors;
    }

    public void setColors(List<Color> colors)
    {
        this.colors = colors;
    }


    public int getCategorych()
    {
        return categorych;
    }

    public void setCategorych(int categorych)
    {
        this.categorych = categorych;
    }

    public int getSizech()
    {
        return sizech;
    }

    public void setSizech(int sizech)
    {
        this.sizech = sizech;
    }

    public int getProducerch()
    {
        return producerch;
    }

    public void setProducerch(int producerch)
    {
        this.producerch = producerch;
    }

    public int getColorch()
    {
        return colorch;
    }

    public void setColorch(int colorch)
    {
        this.colorch = colorch;
    }

    public Good getSelectedRow()
    {
        return selectedRow;
    }

    public void setSelectedRow(Good selectedRow)
    {
        this.selectedRow = selectedRow;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getVendorcode()
    {
        return vendorcode;
    }

    public void setVendorcode(String vendorcode)
    {
        this.vendorcode = vendorcode;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

//    public List<Category> getCategories()
//    {
//        categ.
//    }


    public void addGood() throws HibernateException
    {
//        Criteria criteria = session.createCriteria(Category.class);
//        this.list = criteria.list();

        try {
            Category category = null;
            Size size = null;
            CountryProducer producer = null;
            Color color = null;

            session = HibernateUtil.getSessionFactory().openSession();
            if (getCategorych()>0)
            {
                category = getCategories().get(getCategorych()-1);
            }

            if (getSizech()>0)
            {
                size = getSizes().get(getSizech()-1);
            }

            if (getProducerch()>0)
            {
                producer = getCountryProducers().get(getProducerch()-1);
            }

            if (getColorch()>0)
            {
                color = getColors().get(getColorch()-1);
            }

            this.toUpdate = true;
            session.save(new Good(this.name, this.description, this.vendorcode, this.count, category, size, producer, color ) );
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

    public List<Good> getList()
{
    List<Good> helplist = getAllGoods();
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

    public List<Good> getFiltredlist()
    {
        return filtredlist;
    }

    public void setFiltredlist(List<Good> filtredlist)
    {
        this.filtredlist = filtredlist;
    }



    public List<Good> getAllGoods() throws HibernateException
    {
        List<Good> list = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Good.class);
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

    public void changeGood(int id, Good good) throws HibernateException {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Good gd = (Good) session.load(Good.class, id);
            gd.setName(good.getName());
            gd.setDiscription(good.getDiscription());
            gd.setVendorcode(good.getVendorcode());
            gd.setCount(good.getCount());

            if (getCategorych()>0)
            {
                gd.setCategory(getCategories().get(getCategorych()-1));
            }
            else
            {
                gd.setCategory(null);
            }

            if (getSizech()>0)
            {
                gd.setSize(getSizes().get(getSizech()-1));
            }
            else
            {
                gd.setSize(null);
            }

            if (getProducerch()>0)
            {
                gd.setProducer(getCountryProducers().get(getProducerch()-1));
            }
            else
            {
                gd.setProducer(null);
            }

            if (getColorch()>0)
            {
                gd.setColor(getColors().get(getColorch()-1));
            }
            else
            {
                gd.setColor(null);
            }

            this.toUpdate = true;
            session.save(gd);
//            session.flush();
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.out.println(e.toString());
            e.printStackTrace();

        } finally
        {
            if (session != null && session.isOpen()) {
                session.close();
                this.categorych = 0;
                this.sizech = 0;
                this.producerch =0;
                this.colorch =0;
            }
        }
    }

//    public void onCellEdit(CellEditEvent event) {
//        Object oldValue = event.getOldValue();
//        Object newValue = event.;
//
//
//        int id = ((Good)newValue).getId();
//        int rownum = list.indexOf((Good) newValue);
//
//        changeGood(id, this.list.get(rownum));
//    }

    public void onRowEdit(RowEditEvent event) {

        int id = ((Good)event.getObject()).getId();

        int rownum = list.indexOf((Good) event.getObject());
        changeGood(id, this.list.get(rownum));
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
            Good cat = (Good) session.get(Good.class, selectedRow.getId());
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
