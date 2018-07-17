package db;

import com.sun.xml.internal.ws.handler.HandlerException;
import models.Product;
import models.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
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

    public static void removeFavProduct(User user, Product product){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Product> favProducts = getAllFavProducts(user);
        try{
            transaction = session.beginTransaction();
//            Criteria cr = session.createCriteria(Product.class);
//            cr.add(Restrictions.eq("user.id", user.getId()));
            favProducts.remove(product);
            transaction.commit();
        }catch (HandlerException e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
//            session.close();
        }
    }

//    public static void removeFavProduct(Product product) {
//        session = HibernateUtil.getSessionFactory().openSession();
//        try {
//            transaction = session.beginTransaction();
//            session.delete(object);
//            transaction.commit();
//        } catch (HibernateException e) {
//            transaction.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
}
