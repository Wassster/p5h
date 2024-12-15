package OV.Domein;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reiziger")
public class Reiziger {

    @Id
    @Column(name = "reiziger_id")
    private int id;

    @Column(name = "voorletters")
    private String voorletters;

    @Column(name = "tussenvoegsel")
    private String tussenvoegsel;

    @Column(name = "achternaam")
    private String achternaam;

    @Column(name = "geboortedatum")
    private Date geboortedatum;

    @OneToMany(mappedBy = "reiziger", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OVChipkaart> ovChipkaarts = new ArrayList<>();



    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public Reiziger() {}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public List<OVChipkaart> getOvChipkaarts() {
        return ovChipkaarts;
    }

    public void setOvChipkaarts(List<OVChipkaart> ovChipkaarts) {
        this.ovChipkaarts = ovChipkaarts;
    }


    public void addOVChipkaart(OVChipkaart ovChipkaart) {
        if (!ovChipkaarts.contains(ovChipkaart)) {
            ovChipkaarts.add(ovChipkaart);
            ovChipkaart.setReiziger(this);
        }
    }
    @Override
    public String toString() {
        return String.format("Reiziger {#%d, %s %s %s, geb. %s}",
                id, voorletters, (tussenvoegsel != null ? tussenvoegsel : ""), achternaam, geboortedatum);
    }

}