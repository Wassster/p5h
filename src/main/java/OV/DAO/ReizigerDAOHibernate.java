package OV.DAO;

import OV.Domein.OVChipkaart;
import OV.Domein.Reiziger;
import OV.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO {

    @Override
    public boolean save(Reiziger reiziger) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(reiziger);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(reiziger);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();


            List<OVChipkaart> ovChipkaarten = reiziger.getOvChipkaarts();
            for (OVChipkaart ovChipkaart : ovChipkaarten) {
                session.delete(ovChipkaart);
            }


            session.delete(reiziger);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public Reiziger findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Reiziger.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Reiziger", Reiziger.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

