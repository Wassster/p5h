package OV.Domein;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_nummer")
    private int product_nummer;

    @Column(name = "naam")
    private String naam;

    @Column(name = "prijs")
    private double prijs;

    @Column(name = "beschrijving")
    private String beschrijving;

    @ManyToMany
    @JoinTable(
            name = "ovchipkaart_product",
            joinColumns = @JoinColumn(name = "product_nummer"),
            inverseJoinColumns = @JoinColumn(name = "kaartnummer")
    )
    private List<OVChipkaart> ovchipkaarts = new ArrayList<>();


    // Constructor
    public Product(int product_nummer, String naam, double prijs, String beschrijving) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.prijs = prijs;
        this.beschrijving = beschrijving;
    }

    public Product() {}

    // Getters and Setters
    public int getProduct_nummer() {
        return product_nummer;
    }

    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public List<OVChipkaart> getOvchipkaarts() {
        return ovchipkaarts;
    }

    public void setOvchipkaarts(List<OVChipkaart> ovchipkaarts) {
        this.ovchipkaarts = ovchipkaarts;
    }

    // Helper methods
    public void voegOVChipkaartToe(OVChipkaart ovChipkaart) {
        if (!ovchipkaarts.contains(ovChipkaart)) {
            ovchipkaarts.add(ovChipkaart);
            ovChipkaart.voegProductToe(this);
        }
    }

    public void verwijderOVChipkaart(OVChipkaart ovChipkaart) {
        if (ovchipkaarts.contains(ovChipkaart)) {
            ovchipkaarts.remove(ovChipkaart);
            ovChipkaart.verwijderProduct(this);
        }
    }

    @Override
    public String toString() {
        return String.format("Product {product_nummer=%d, naam='%s', prijs=%.2f, beschrijving='%s'}",
                product_nummer, naam, prijs, beschrijving);
    }

}

