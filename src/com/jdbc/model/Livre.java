package com.jdbc.model;

public class Livre {
    private int id;
    private String auteur;
    private String titre;
    private int isbn;
    private int status;
    private int quantite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Livre ajouter(){
        return new Livre();
    }

    public boolean supprimer(int id){
        return true;
    }

    public Livre modifier(int id){
        return new Livre();
    }

    // You need a Union Type here
    public Livre rechercher(String slag){
        return new Livre();
    }

    public Livre afficherLivre(){
        return new Livre();
    }

    public Livre afficherLivres(){
        return new Livre();
    }

}
