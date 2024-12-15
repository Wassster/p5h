package OV.DAO;


import OV.Domein.Product;

import java.util.List;

public interface ProductDAO {
    boolean save(Product product);
    boolean update(Product product);
    boolean delete(Product product);
    Product findById(int product_nummer);
    List<Product> findAll();
    List<Product> findByOVChipkaart(int kaartnummer);
}

