package OV.Domein;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ovchipkaart")
public class OVChipkaart {

    @Id
    @Column(name = "kaartnummer")
    private int kaartnummer;

    @Column(name = "geldige_tot")
    private Date geldige_tot;

    @Column(name = "klasse")
    private int klasse;

    @Column(name = "saldo")
    private double saldo;

    @ManyToOne
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;

    @ManyToMany(mappedBy = "ovchipkaarts", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();


    public OVChipkaart(int kaartnummer, Date geldige_tot, int klasse, double saldo) {
        this.kaartnummer = kaartnummer;
        this.geldige_tot = geldige_tot;
        this.klasse = klasse;
        this.saldo = saldo;
    }

    public OVChipkaart() {}


    public int getKaartnummer() {
        return kaartnummer;
    }

    public void setKaartnummer(int kaartnummer) {
        this.kaartnummer = kaartnummer;
    }

    public Date getGeldige_tot() {
        return geldige_tot;
    }

    public void setGeldige_tot(Date geldige_tot) {
        this.geldige_tot = geldige_tot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public void voegProductToe(Product product) {
        if (!products.contains(product)) {
            products.add(product);
            product.voegOVChipkaartToe(this);
        }
    }

    public void verwijderProduct(Product product) {
        if (products.contains(product)) {
            products.remove(product);
            product.verwijderOVChipkaart(this);
        }
    }

    @Override
    public String toString() {
        return String.format("OVChipkaart {kaartnummer=%d, geldig_tot=%s, klasse=%d, saldo=%.2f}",
                kaartnummer, geldige_tot, klasse, saldo);
    }

}

