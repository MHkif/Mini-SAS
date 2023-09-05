package app.controllers;

import app.Helpers;
import app.entities.Livre;
import app.entities.LivreEmpruntes;
import app.repositories.LivreRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Navigator {

    LivreRepository livreRepository = new LivreRepository();
    public void apply(String scanner){
        LivreEmpruntes livreEmpruntes = new LivreEmpruntes();
        try {
            int option = Integer.parseInt(scanner);
            boolean isRunning = false;
           // while (isRunning){
            switch (option) {
                case 1:
                    this.afficherLivres();
                    break;
                case 2:
                    this.afficherLivre();
                    break;
                case 3:
                    livreEmpruntes.afficherLivresEmpruntes();
                    break;
                case 4:
                    this.ajouterLivre();
                    break;
                case 5:
                    this.modifierLivre();
                    break;
                case 6:
                    this.supprimerLivre();
                    break;
                case 7:
                    this.rechercherLivre();
                    break;
                case 8:
                    livreEmpruntes.emprunter(1);
                    break;
                case 9:
                    livreEmpruntes.retourner(1);
                    break;
                case 10:
                    livreEmpruntes.genererUnRapport();
                    break;
                default:
                    System.out.println("Option incorrecte");
                    break;


            }
        //}
        }catch (Exception e){
            System.out.println("Exception : " + e);
        }
    }

    public void ajouterLivre() throws SQLException {
        Helpers.clearScreen();
        Livre livre = new Livre();
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------------------");
        System.out.println("\n-> Pour Continuer cliquer sur Entrer , Entrer 0 pour retour au menu ?");
        if(scanner.nextLine().equals("0")){
            Helpers.clearScreen();
            Helpers.opening();
        }
        System.out.println("\n-> Entrer les informations du nouveau livre : ");
        System.out.print("\n-> Titre du livre : ");
        String titre  = scanner.nextLine();
        System.out.print("Auteur du livre : ");
        String auteur  = scanner.nextLine();
        System.out.print("ISBN du livre : ");
        int isbn  = scanner.nextInt();
        System.out.print("Quantité du livre : ");
        int quantite  = scanner.nextInt();

        System.out.println("\nLe nouveau livre :");
        System.out.println("Titre : "+titre+
                " .\nAuteur : "+auteur+" ."+
                " .\nISBN : "+isbn+" ."+
                " .\nQuantité : "+quantite+" .");

        System.out.print("\n-> Confirmmer vous l'exécution ? (y/n) : ");
        String confirm = scanner.next();

        if(confirm.equals("y") || confirm.equals("yes")){
            livre.setTitre(titre);
            livre.setAuteur(auteur);
            livre.setIsbn(isbn);
            livre.setQuantite(quantite);

            livreRepository.ajouter(livre);

        }else if(confirm.equals("n") || confirm.equals("no")){
            livre = new Livre();
            Helpers.clearScreen();
            Helpers.opening();
        }else{
            System.out.print("\n-> Option incorrecte, Confirmmer vous l'exécution ? (y/n) : ");
        }


    }

    public Livre modifierLivre(){
        Livre livre = new Livre();
        Helpers.clearScreen();
        System.out.print("\nEntrer un id : ");
        Scanner scanner = new Scanner(System.in);
        int id  = scanner.nextInt();
        System.out.println("Tu as entré l'id :"+id);

        return livre;
    }

    public boolean supprimerLivre(){
        System.out.println("\nEntrer un id :");
        Scanner scanner = new Scanner(System.in);
        int id  = scanner.nextInt();
        System.out.println("Tu as entré l'id :"+id);
        return true;
    }
    public Livre rechercherLivre(){
        System.out.println("\nEntrer un id :");
        Scanner scanner = new Scanner(System.in);
        int id  = scanner.nextInt();
        System.out.println("Tu as entré l'id :"+id);
        return new Livre();
    }

    public void afficherLivres() throws SQLException {
        System.out.println("\nLa liste des livres : \n");
        for (Livre livre : livreRepository.afficherLivres()) {
            System.out.println("id : "+livre.getId()+
                    " .\nTitre : "+livre.getAuteur()+" ."+
                    " .\nAuteur : "+livre.getAuteur()+" ."+
                    " .\nISBN : "+livre.getIsbn()+" ."+
                    " .\nQuantité : "+livre.getQuantite()+" .");
            System.out.println();
        }
        System.out.print("\n-> Cliquez sur n'importe quelle touche pour revenir  au menu .");
        String scanner = new Scanner(System.in).nextLine();

           Helpers.clearScreen();
           Helpers.opening();


    }

    public Livre afficherLivre() throws SQLException {
       return  new Livre();
        
    }


}
