package OV;

import OV.DAO.OVChipkaartDAO;
import OV.DAO.OVChipkaartDAOHibernate;
import OV.DAO.ProductDAO;
import OV.DAO.ProductDAOHibernate;
import OV.DAO.ReizigerDAO;
import OV.DAO.ReizigerDAOHibernate;
import OV.Domein.OVChipkaart;
import OV.Domein.Product;
import OV.Domein.Reiziger;
import OV.Util.HibernateUtil;

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ReizigerDAO reizigerDAO = new ReizigerDAOHibernate();
        OVChipkaartDAO ovChipkaartDAO = new OVChipkaartDAOHibernate();
        ProductDAO productDAO = new ProductDAOHibernate();


        System.out.println("Reiziger Tests");
        Reiziger reiziger = new Reiziger(1, "J", "van", "Doe", Date.valueOf("1990-01-01"));
        reizigerDAO.save(reiziger);
        System.out.println("Geregistreerde reiziger: " + reiziger);

        reiziger.setAchternaam("Smith");
        reizigerDAO.update(reiziger);
        System.out.println("Bijgewerkte reiziger: " + reizigerDAO.findById(1));

        System.out.println("Alle reizigers: " + reizigerDAO.findAll());


        System.out.println("\nOVChipkaart Test");
        OVChipkaart ovChipkaart = new OVChipkaart(12345, Date.valueOf("2025-01-01"), 2, 50.0);
        ovChipkaart.setReiziger(reiziger);
        ovChipkaartDAO.save(ovChipkaart);
        System.out.println("Geregistreerde OVChipkaart: " + ovChipkaart);

        ovChipkaart.setSaldo(100.0);
        ovChipkaartDAO.update(ovChipkaart);
        System.out.println("Bijgewerkte OVChipkaart: " + ovChipkaartDAO.findById(12345));

        System.out.println("Alle OVChipkaarten: " + ovChipkaartDAO.findAll());

        System.out.println("\n=== Product Tests ===");
        Product product = new Product(1, "NS Dagkaart", 25.0, "Een dag onbeperkt reizen");
        productDAO.save(product);
        System.out.println("Geregistreerd product: " + product);

        product.setPrijs(30.0);
        productDAO.update(product);
        System.out.println("Bijgewerkt product: " + productDAO.findById(1));

        System.out.println("Alle producten: " + productDAO.findAll());


        System.out.println("\n Relatie OVChipkaart-Product Test");
        ovChipkaart.voegProductToe(product);
        ovChipkaartDAO.update(ovChipkaart);

        List<Product> gekoppeldeProducten = productDAO.findByOVChipkaart(12345);
        System.out.println("Gekoppelde producten voor OVChipkaart: " + gekoppeldeProducten);

        product.verwijderOVChipkaart(ovChipkaart);
        productDAO.update(product);
        System.out.println("Na verwijderen: " + productDAO.findByOVChipkaart(12345));

        System.out.println("\nVerwijderen Reizige");
        reizigerDAO.delete(reiziger);
        System.out.println("Reiziger verwijderd. Huidige lijst: " + reizigerDAO.findAll());

        HibernateUtil.shutdown();
    }
}

