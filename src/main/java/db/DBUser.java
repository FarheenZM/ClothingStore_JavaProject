package db;

import com.sun.xml.internal.ws.handler.HandlerException;
import models.Product;
import models.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Set;

public class DBUser {

    private static Transaction transaction;
    private static Session session;

    public static List<Product> getAllFavProducts(User user){

        session = HibernateUtil.getSessionFactory().openSession();
        List<Product> favProducts = null;
        try{
            Criteria cr = session.createCriteria(Product.class);
            cr.createAlias("users", "user");
            cr.add(Restrictions.eq("user.id", user.getId()));
            favProducts = cr.list();

        }catch (HandlerException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return favProducts;
    }
}
