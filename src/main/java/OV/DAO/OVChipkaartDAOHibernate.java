package OV.DAO;

import OV.Domein.OVChipkaart;
import OV.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO {

    @Override
    public boolean save(OVChipkaart chipkaart) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(chipkaart);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(OVChipkaart chipkaart) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(chipkaart);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaart chipkaart) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(chipkaart);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public OVChipkaart findById(int kaartnummer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(OVChipkaart.class, kaartnummer);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OVChipkaart> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM OVChipkaart", OVChipkaart.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

