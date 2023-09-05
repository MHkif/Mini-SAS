package app.controllers;

import app.Helpers;
import app.entities.Livre;
import app.entities.LivreEmpruntes;

import java.util.ArrayList;
import java.util.Scanner;

public class Navigator {

    public void apply(String scanner){
        LivreEmpruntes livreEmpruntes = new LivreEmpruntes();
        try {
            int option = Integer.parseInt(scanner);
            boolean isRunning = true;
            while (isRunning){
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
        }
        }catch (Exception e){
            System.out.println("Exception : " + e);
        }
    }

    public void ajouterLivre(){
        Helpers.clearScreen();
        Livre livre = new Livre();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEntrer les informations du nouveau livre :");

        System.out.print("\nTitre du livre : ");
        String titre  = scanner.nextLine();
        System.out.print("\nAuteur du livre : ");
        String auteur  = scanner.nextLine();
        System.out.print("\nISBN du livre : ");
        int isbn  = scanner.nextInt();
        System.out.print("\nQuantité du livre : ");
        int quantite  = scanner.nextInt();

        System.out.println("\nLe nouveau livre :");
        System.out.println("Titre : "+titre+
                " .\nAuteur : "+auteur+" ."+
                " .\nISBN : "+isbn+" ."+
                " .\nQuantité : "+quantite+" .");

        System.out.print("\n-> Confirmmer vous l'exécution ? (y/n) : ");
        String confirm = scanner.next();

        if(confirm == "y" || confirm == "yes"){
            livre.setTitre(titre);
            livre.setAuteur(auteur);
            livre.setIsbn(isbn);
            livre.setQuantite(quantite);

            livre.ajouter(livre);

        }else if(confirm == "n" || confirm == "no"){
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

    public ArrayList<Livre> afficherLivres(){
        return new ArrayList<Livre>();
    }

    public Livre afficherLivre(){
        return new Livre();
    }


}
