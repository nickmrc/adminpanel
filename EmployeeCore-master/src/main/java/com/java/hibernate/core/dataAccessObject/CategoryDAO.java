package com.java.hibernate.core.dataAccessObject;

import com.java.hibernate.core.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.java.hibernate.core.entity.*;
import java.util.List;
import java.io.Serializable;
import java.util.List;



public class CategoryDAO implements Serializable
{
    private Session session = HibernateUtil.getSessionFactory().openSession();

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    private  String description;
    private  String name;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }





//    public void addCategory(String name, String discription) throws HibernateException
//    {
//        session.save(new Category(name, discription));
//    }



//    public UsersDataSet get(long id) throws HibernateException
//    {
//        return (UsersDataSet) session.get(UsersDataSet.class, id);
//    }


    public List<Category> getAllCategories() throws HibernateException
    {
        Criteria criteria = session.createCriteria(Category.class);
        return (List<Category>) criteria.list();
    }



}
