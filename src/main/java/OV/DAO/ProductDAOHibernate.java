package OV.DAO;

import OV.Domein.Product;
import OV.Domein.OVChipkaart;
import OV.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDAOHibernate implements ProductDAO {

    @Override
    public boolean save(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Product existingProduct = session.get(Product.class, product.getProduct_nummer());
            if (existingProduct != null) {
                System.out.println("Product met nummer " + product.getProduct_nummer() + " bestaat al. Gebruik update in plaats van save.");
                return false;
            }
            session.save(product);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean update(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(product);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(product);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product findById(int product_nummer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Product.class, product_nummer);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Product", Product.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> findByOVChipkaart(int kaartnummer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String query = "SELECT p FROM Product p JOIN p.ovchipkaarts o WHERE o.kaartnummer = :kaartnummer";
            return session.createQuery(query, Product.class)
                    .setParameter("kaartnummer", kaartnummer)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
