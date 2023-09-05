package app.entities;

import java.util.Date;

public class LivreEmpruntes {
    private int id;
    private String emprunteur;
    private Date date;
    private Date retour;

    public int getId() {
        return id;
    }

    public String getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(String emprunteur) {
        this.emprunteur = emprunteur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getRetour() {
        return retour;
    }

    public void setRetour(Date retour) {
        this.retour = retour;
    }

    public boolean emprunter(int id){
        return true;
    }

    public boolean retourner(int id){
        return true;
    }

    public void genererUnRapport(){
        return;
    }

    public Livre afficherLivresEmpruntes(){
        return new Livre();
    }
}
