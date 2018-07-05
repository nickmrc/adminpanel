package com.java.hibernate.core.dataAccessObject;

import com.java.hibernate.core.entity.*;
import com.java.hibernate.core.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.view.ViewScoped;
import javax.persistence.Column;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;


@javax.faces.bean.ManagedBean //(name = "deliveryDAO")
@ApplicationScoped
public class DeliveryDAO implements Serializable
{
    private Session session = HibernateUtil.getSessionFactory().openSession();;


    private void update()
    {
        this.contrAgent = null;
        this.contractnumber = 0;
        this.conclusiondate = null;
        this.deliverydate = null;
        this.goodch=0;
    }

    private ContrAgent contrAgent;

    private int contractnumber;

    private Date conclusiondate;

    private Date deliverydate;

    private int goodch;

    private int sum;

//    @ManagedProperty(name = "delivery", value = "#{param.delivery}")
//    private Delivery delivery;


    private List<Delivery> deliveries;

    public List<Delivery> getFiltered()
    {
        return filtered;
    }

    public List<Delivery> getList()
    {
        List<Delivery> helplist = getAllDeliveries();
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


    public void setFiltered(List<Delivery> filtered)
    {
        this.filtered = filtered;
    }

    private  List<Delivery> filtered;

    public   List<Delivery> getAllDeliveries() {
        List<Delivery> list = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Delivery.class);
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


    //Гетеры и сетеры
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



    public int getContrAgentch()
    {
        return contrAgentch;
    }

    public void setContrAgentch(int contrAgentch)
    {
        this.contrAgentch = contrAgentch;
    }

    public int getGoodch()
    {
        return goodch;
    }

    public void setGoodch(int goodch)
    {
        this.goodch = goodch;
    }

    public int getSum()
    {
        return sum;
    }

    public void setSum(int sum)
    {
        this.sum = sum;
    }

    public List<Delivery> getDeliveries()
    {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries)
    {
        this.deliveries = deliveries;
    }


    @PostConstruct
    public void init() {
        contrAgents = this.ContrAgentsInit();
        goods = this.GoodsInit();
        goodsInDelivery = new ArrayList<GoodInDelivery>();
    }


    public List<ContrAgent> getContrAgents()
    {
        List<ContrAgent> helplist = ContrAgentsInit();
        if (this.contrAgents==null)
        {
            contrAgents = helplist;
        }
        else
        {
            if (this.list.size()!= helplist.size())
                contrAgents = helplist;
            else {
                if (toUpdate)
                {
                    contrAgents = helplist;
                    toUpdate = false;
                }

                //размеры одинаковые, но надо сравнить поэлементно?
            }
        }
        return contrAgents;
    }

    //Инициализируем список контрагентов
    List<ContrAgent> contrAgents = this.ContrAgentsInit();



//    public void setContrAgents(List<ContrAgent> contrAgents)
//    {
//        this.contrAgents = contrAgents;
//    }

    private  List<ContrAgent> ContrAgentsInit() {
        ContrAgentDAO contrAgentDAO = new ContrAgentDAO();
        return contrAgentDAO.getAgents();
    }

    //Инициализируем список товаров для выпадающего списка
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



    private List<GoodInDelivery> goodsInDelivery;

    //Инициализируем список товаровВпоставке по id_поставки

    private  List<GoodInDelivery> GoodsInDeliveryInit(int delivery_id) {
    GoodInDeliveryDAO goodInDeliveryDAO = new GoodInDeliveryDAO();
        return goodInDeliveryDAO.getByDelveryId(delivery_id);
    }

    public List<GoodInDelivery> getGoodsInDelivery()
    {
        return goodsInDelivery;
    }

    public void setGoodsInDelivery(List<GoodInDelivery> goodsInDelivery)
    {
        this.goodsInDelivery = goodsInDelivery;
    }

    //Флаг обновления
    private boolean toUpdate;



    private Delivery selectedDelivery;

    public Delivery getSelectedDelivery()
    {
        return selectedDelivery;
    }

    public void setSelectedDelivery(Delivery selectedDelivery)
    {
        this.selectedDelivery = selectedDelivery;
    }

    //Выбранная строка товара в поставке (с ценой и количеством)
    private GoodInDelivery selectedRow;

    public GoodInDelivery getSelectedRow()
    {
        return selectedRow;
    }

    public void setSelectedRow(GoodInDelivery selectedRow)
    {
        this.selectedRow = selectedRow;
    }

    private int contrAgentch;



    List<Delivery> list;



    //что-то передаем: объект GoodInDelivery или значения полей - хзхзхз
    public void addGoodInDelivery() {

        this.goodsInDelivery.add(new GoodInDelivery());
    }

    public void addDelivery()
    {

            ContrAgent contrAgent = null;
            try {
            session = HibernateUtil.getSessionFactory().openSession();
            if (getContrAgentch()>0)
            {
                contrAgent = getContrAgents().get(getContrAgentch()-1);
            }


            this.toUpdate = true;
            Delivery delivery = new Delivery(contrAgent, this.contractnumber, this.conclusiondate, this.deliverydate);

            session.save(delivery);
            for (GoodInDelivery i :goodsInDelivery)
            {
              i.setDelivery(delivery);
            }

         GoodInDeliveryDAO goodInDeliveryDAO = new GoodInDeliveryDAO();

           goodInDeliveryDAO.addGoodsInDelivery(goodsInDelivery);

        }
        catch (HibernateException e)
        {

        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }

            this.goodsInDelivery = new ArrayList<GoodInDelivery>();
            update();

        }
    }
//    public void addGood() throws HibernateException
//    {
////        Criteria criteria = session.createCriteria(Category.class);
////        this.list = criteria.list();
//
//        try {
//            Category category = null;
//            Size size = null;
//            CountryProducer producer = null;
//            Color color = null;
//
//            session = HibernateUtil.getSessionFactory().openSession();
//            if (getCategorych()>0)
//            {
//                category = getCategories().get(getCategorych()-1);
//            }
//
//            if (getSizech()>0)
//            {
//                size = getSizes().get(getSizech()-1);
//            }
//
//            if (getProducerch()>0)
//            {
//                producer = getCountryProducers().get(getProducerch()-1);
//            }
//
//            if (getColorch()>0)
//            {
//                color = getColors().get(getColorch()-1);
//            }
//
//            this.toUpdate = true;
//            session.save(new Good(this.name, this.description, this.vendorcode, this.count, category, size, producer, color ) );
//        }
//        catch (HibernateException e) {
//        }
//        finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//            //не уверен, что теперь оно тут надо
//            getList();
//
//        }
//    }
//
//    public List<Good> getList()
//    {
//        List<Good> helplist = getAllGoods();
//        if (this.list==null)
//        {
//            list = helplist;
//        }
//        else
//        {
//            if (this.list.size()!= helplist.size())
//                list = helplist;
//            else {
//                if (toUpdate)
//                {
//                    list = helplist;
//                    toUpdate = false;
//                }
//
//                //размеры одинаковые, но надо сравнить поэлементно?
//            }
//        }
//        return list;
//    }
//
//
//
//    public List<Good> getAllGoods() throws HibernateException
//    {
//        List<Good> list = null;
//
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            Criteria criteria = session.createCriteria(Good.class);
//            list = criteria.list();
//
////            Query query = session.createQuery("FROM Good");
////            list = (List<Good>) query.list();
//
//            session.getTransaction().commit();
//        }
//        catch (HibernateException e) {
//            e.printStackTrace();
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
//    public void changeGood(int id, Good good) throws HibernateException {
//
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            Good gd = (Good) session.get(Good.class, id);
//            gd.setName(good.getName());
//            gd.setDiscription(good.getDiscription());
//            gd.setVendorcode(good.getVendorcode());
//            gd.setCount(good.getCount());
//
//            if (getCategorych()>0)
//            {
//                gd.setCategory(getCategories().get(getCategorych()-1));
//            }
//            else
//            {
//                gd.setCategory(null);
//            }
//
//            if (getSizech()>0)
//            {
//                gd.setSize(getSizes().get(getSizech()-1));
//            }
//            else
//            {
//                gd.setSize(null);
//            }
//
//            if (getProducerch()>0)
//            {
//                gd.setProducer(getCountryProducers().get(getProducerch()-1));
//            }
//            else
//            {
//                gd.setProducer(null);
//            }
//
//
//            if (getColorch()>0)
//            {
//                gd.setColor(getColors().get(getColorch()-1));
//            }
//            else
//            {
//                gd.setColor(null);
//            }
//
//           this.toUpdate = true;
//
//
//            session.update(gd);
//            session.getTransaction().commit();
//
//        } catch (HibernateException e) {
//
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//                this.categorych = 0;
//                this.sizech = 0;
//                this.producerch =0;
//                this.colorch =0;
//
//            }
//        }
//    }
//
////    public void onCellEdit(CellEditEvent event) {
////        Object oldValue = event.getOldValue();
////        Object newValue = event.;
////
////
////        int id = ((Good)newValue).getId();
////        int rownum = list.indexOf((Good) newValue);
////
////        changeGood(id, this.list.get(rownum));
////    }
//


    public boolean filterByDate(Object value, Object filter, Locale locale) {

        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        if (value == null) {
            return false;
        }

        DateFormat df = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM);

        Date filterDate = (Date) value;
        Date dateFrom;
        Date dateTo;
        try {
            String fromPart = filterText.substring(0, filterText.indexOf("-"));
            String toPart = filterText.substring(filterText.indexOf("-") + 1);
            dateFrom = fromPart.isEmpty() ? null : df.parse(fromPart);
            dateTo = toPart.isEmpty() ? null : df.parse(toPart);
        } catch (ParseException pe) {

            return false;
        }

        return (dateFrom == null || filterDate.after(dateFrom)) && (dateTo == null || filterDate.before(dateTo));
    }
    public void onRowEdit(RowEditEvent event) {

       GoodInDelivery goodInDelivery = (GoodInDelivery) event.getObject();

        int rownum = goodsInDelivery.indexOf(goodInDelivery);
        this.goodsInDelivery.get(rownum).setGood(getGoods().get(getGoodch()-1));
        this.sum=goodInDelivery.getPrice()*goodInDelivery.getQuantity();



//        FacesMessage msg = new FacesMessage("Car Edited", ((Car) event.getObject()).getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
//
    public void onRowCancel(RowEditEvent event)
    {
//        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Category) event.getObject()).getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
//
    public void delete() {

     this.goodsInDelivery.remove(selectedRow);

        }


        public  void deleteDelivery()
        {

        }
//
//
//
//
//
//
////        this.list.remove(rownum);
//    }

    //    public UsersDataSet get(long id) throws HibernateException
//    {
//        return (UsersDataSet) session.get(UsersDataSet.class, id);
//    }


}
