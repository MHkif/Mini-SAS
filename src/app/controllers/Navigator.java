package app.controllers;

import app.Helpers;
import app.entities.Emprunteur;
import app.entities.Livre;
import app.entities.LivreEmpruntes;
import app.repositories.EmprunteurRepository;
import app.repositories.LivreEmpruntesRepository;
import app.repositories.LivreRepository;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Navigator {

    Scanner scanner = new Scanner(System.in);
    LivreRepository livreRepository = new LivreRepository();
    LivreEmpruntesRepository livreEmpruntesRepository = new LivreEmpruntesRepository();
    EmprunteurRepository emprunteurRepository = new EmprunteurRepository();
    public void apply(){
        Scanner scanner = new Scanner(System.in);
        try {

            boolean isRunning = true;

           while (isRunning){
               Helpers.opening();
               String option = scanner.nextLine();

               switch (Integer.parseInt(option)) {
                   case 0 -> isRunning = false;
                   case 1 -> this.afficherLivresDisponible();
                   case 2 -> this.afficherLivresEmpruntes();
                   case 3 -> this.ajouterLivre();
                   case 4 -> this.modifierLivre();
                   case 5 -> this.supprimerLivre();
                   case 6 -> this.rechercherLivre();
                   case 7 -> this.emprunter();
                   case 8 -> this.retourner();
                   case 9 -> this.genererUnRapport();
                  // case 10 -> this.afficherLivresPerdu();
               }

           }

        }catch (Exception e){

            System.out.print("Entrez un nombre dans le menu . \n-> ");
            this.apply();
        }
    }


    public void afficherLivresDisponible() throws SQLException {
        System.out.println("\nLa liste des livres disponible : \n");
        if(livreRepository.afficherLivresDisponible().size() < 1){
            System.out.print("-> Il n'y a pas des livres dans la bibliotheque .");
        }else {
            for (Livre livre : livreRepository.afficherLivresDisponible()) {
                Helpers.afficherLivre(livre);
                System.out.println();
            }
        }

        System.out.print("\n-> Cliquez sur n'importe quelle touche pour revenir  au menu .");
        scanner.nextLine();
        Helpers.clearScreen();
        Helpers.opening();


    }

    public void afficherLivresEmpruntes() throws SQLException {
        System.out.println("\nLa liste des livres empruntes : \n");
        if(livreRepository.afficherLivresEmpruntes().size() < 1){
            System.out.print("-> Il n'y a pas des livres dans la bibliotheque .");
        }else {
            for (Livre livre : livreRepository.afficherLivresEmpruntes()) {
                Helpers.afficherLivre(livre);
                System.out.println();
            }
        }

        System.out.print("\n-> Cliquez sur n'importe quelle touche pour revenir  au menu .");
        scanner.nextLine();
        Helpers.clearScreen();
        Helpers.opening();


    }


    public void afficherLivresPerdu() throws SQLException {
        System.out.println("\nLa liste des livres perdu : \n");
        if(livreRepository.afficherLivresPerdu().size() < 1){
            System.out.print("-> Il n'y a pas des livres dans la bibliotheque .");
        }else {
            for (Livre livre : livreRepository.afficherLivresPerdu()) {
                Helpers.afficherLivre(livre);
                System.out.println();
            }
        }

        System.out.print("\n-> Cliquez sur n'importe quelle touche pour revenir  au menu .");
        scanner.nextLine();
        Helpers.clearScreen();
        Helpers.opening();


    }

    public void ajouterLivre() throws SQLException {
        Helpers.clearScreen();
        Livre livre = new Livre();
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------------------");
        System.out.println("\n-> Pour Continuer cliquer sur Entrer , Entrer 0 pour revenir au menu ?");
        if(scanner.nextLine().equals("0")){
            Helpers.clearScreen();
            Helpers.opening();
        }else{

            System.out.println("\n-> Entrer les informations du nouveau livre : ");
            System.out.print("\n-> Titre du livre : ");
            String titre  = scanner.nextLine();
            System.out.print("-> Auteur du livre : ");
            String auteur  = scanner.nextLine();
            System.out.print("-> ISBN du livre : ");
            String isbn  = scanner.nextLine();

            System.out.println("\nLe nouveau livre :");
            System.out.println("Titre : "+titre+
                    " .\nAuteur : "+auteur+" ."+
                    " .\nISBN : "+isbn+" .");

            boolean isConfirmed = true;
            while ( isConfirmed ) {
                System.out.println("\n-> Confirmmer vous l'exécution ?");
                System.out.print("\n1-> yes .\n2-> no  \n->  ");
                String confirm = scanner.nextLine();

                if(Integer.parseInt(confirm) == 1){
                    livre.setTitre(titre);
                    livre.setAuteur(auteur);
                    livre.setIsbn(Integer.parseInt(isbn));

                    livreRepository.ajouter(livre);
                    break;
                }else if(Integer.parseInt(confirm) == 0){
                    livre = new Livre();
                    Helpers.clearScreen();
                    Helpers.opening();
                    isConfirmed = false;
                }
            }
        }



    }

    public void modifierLivre() throws SQLException{

        Helpers.clearScreen();
        System.out.print("\nEntrer Isbn de Livre : ");
        String isbn = scanner.nextLine();
        Livre livre  = livreRepository.getLivreByIsbn(Integer.parseInt(isbn));
        Helpers.afficherLivre(livre);

        boolean continuer = true;
        while (continuer){

            System.out.println("""

                    Que Voulez-vous modifier dans ce livre :\s
                    1 -> Titre\s
                    2 -> Auteur\s
                    3 -> Status
                    0 -> Revenir au menu\s""");
            System.out.print("-> Entrez une option : ");

            String option = scanner.nextLine();
            switch (Integer.parseInt(option)) {
                case 1 -> {
                    System.out.print("\nTitre : ");
                    String titre = scanner.nextLine();
                    livre.setTitre(titre);
                }
                case 2 -> {
                    System.out.print("\nAuteur : ");
                    String auteur = scanner.nextLine();
                    livre.setAuteur(auteur);
                }
                case 3 -> Helpers.modifierStatus(scanner, livre);
                case 0 -> {
                    Helpers.clearScreen();
                    Helpers.opening();
                    continuer = false;
                }
            }

            if(Integer.parseInt(option) != 0){

                System.out.println("\nLe Livre Après la Modification :");
                Helpers.afficherLivre(livre);
                boolean isConfirmed = true;
                while ( isConfirmed ) {
                    System.out.println("\n-> Confirmmer vous l'exécution ?");
                    System.out.print("\n1-> yes .\n2-> no  \n->  ");
                    String confirm = scanner.nextLine();

                    if(Integer.parseInt(confirm) == 1){
                        livreRepository.modifier(livre);
                        isConfirmed = false;
                    }else if(Integer.parseInt(confirm) == 2){
                        System.out.println("\nLes Modifcations sont annulé .\n");
                        isConfirmed = false;
                    }
                }

            }

        }




    }

    public void supprimerLivre() throws SQLException{
        Helpers.clearScreen();
        System.out.print("\nEntrer Isbn de Livre : ");
        Scanner scanner = new Scanner(System.in);
        String isbn = scanner.nextLine();
        Livre livre  = livreRepository.getLivreByIsbn(Integer.parseInt(isbn));

        if(!Objects.isNull(livre)){
            Helpers.afficherLivre(livre);
            boolean isConfirmed = true;
            while ( isConfirmed ) {
                System.out.println("\n-> Confirmmer-vous l'exécution ?");
                System.out.print("\n1-> yes .\n2-> no  \n->  ");
                String confirm = scanner.nextLine();

                if(Integer.parseInt(confirm) == 1){
                    livreRepository.supprimer(Integer.parseInt(isbn));
                    isConfirmed = false;
                }else if(Integer.parseInt(confirm) == 0){
                    Helpers.clearScreen();
                    Helpers.opening();
                    isConfirmed = false;
                }
            }

        }else{
            System.out.println("Il n'y a pas de livre avec l'isbn de : "+isbn +" dans la bibliotheque .");
            Helpers.clearScreen();
            Helpers.opening();
        }


    }

    public void rechercherLivre() throws SQLException{
        Livre livre = new Livre();
        boolean isConfirmed = true;
        while ( isConfirmed ) {
            System.out.println("\n-> Voulez-vous rechercher par ?");
            System.out.print("\n1-> Isbn .\n2-> Titre, Auteur . \n0 -> Revenir au menu .\n->  ");
            String confirm = scanner.nextLine();

            switch (Integer.parseInt(confirm)){
                case 0:
                    isConfirmed = false;
                    break;
                case 1:
                    System.out.print("-> Rechercher par Isbn : ");
                    String isbn = scanner.nextLine();
                    livre =  livreRepository.rechercherByIsbn(Integer.parseInt(isbn));
                    Helpers.afficherLivre(livre);
                    System.out.print("\n-> Cliquez sur n'importe quelle touche pour continer ...");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.print("-> Rechercher par Isbn : ");
                    String slag = scanner.nextLine();
                    livre =  livreRepository.rechercher(slag);
                    Helpers.afficherLivre(livre);
                    System.out.print("\n-> Cliquez sur n'importe quelle touche pour continer ...");
                    scanner.nextLine();
                    break;
            }



        }
        Helpers.clearScreen();
        Helpers.opening();
    }

    public void emprunter() throws SQLException {
        Helpers.clearScreen();
        System.out.print("\nEntrer Isbn de Livre : ");
        Emprunteur emprunteur;
        String isbn = scanner.nextLine();
        Livre livre  = livreRepository.getLivreByIsbn(Integer.parseInt(isbn));
        Helpers.afficherLivre(livre);

        if(livre.getId() > 0){
            System.out.print("\nEntrer MemberShip d'emprunteur : ");
            String memberShip = scanner.nextLine();
            emprunteur = emprunteurRepository.getEmprunteurByMemberShip(Integer.parseInt(memberShip));

            if(emprunteur.getMemberShip() > 0){

                if (livreEmpruntesRepository.emprunter(emprunteur, livre)){

                   // livreRepository.livreDisponibilite(livre, 0);
                }else{
                    System.out.print("\n-> Cliquez sur n'importe quelle touche pour revenir  au menu .");
                    scanner.nextLine();
                    Helpers.clearScreen();
                    Helpers.opening();
                }

            }else{

              boolean isValid = true;
               while(isValid){
                   System.out.print("""
                           Ce Emprunteur n'est pas existé dans la base données .\s
                           1-> Créer un nouveau emprunteur .
                           2-> Annuler et revenir au menu .
                           ->\s""");
                   String option = scanner.nextLine();

                   if(Integer.parseInt(option) == 1){
                       System.out.println("\n-> Entrer les informations du nouveau emprunteur : ");
                       System.out.print("-> MemberShip : ");
                       memberShip  = scanner.nextLine();
                       System.out.print("-> Username : ");
                       String username  = scanner.nextLine();
                       System.out.print("-> CIN d'emprunteur : ");
                       String cin  = scanner.nextLine();

                       System.out.println("\nLe nouveau emprunteur :");
                       System.out.println("MemberShip : "+memberShip+
                               " \nUsername : "+username+" ."+
                               " \nCIN : "+cin+" .");

                       boolean isConfirmed = true;
                       while ( isConfirmed ) {
                           System.out.println("\n-> Confirmmer vous l'exécution ?");
                           System.out.print("\n1-> yes .\n2-> no  \n->  ");
                           String confirm = scanner.nextLine();

                           if(Integer.parseInt(confirm) == 1){
                               emprunteur.setMemberShip(Integer.parseInt(memberShip));
                               emprunteur.setUsername(username);
                               emprunteur.setCin(cin);

                               emprunteurRepository.ajouter(emprunteur);
                               livreEmpruntesRepository.emprunter(emprunteur,livre);
                               isConfirmed = false;
                           }else if(Integer.parseInt(confirm) == 2){
                               emprunteur = new Emprunteur();
                               Helpers.clearScreen();
                               Helpers.opening();
                               isConfirmed = false;
                           }
                           else {
                               System.out.println("->Incorrecte option ...");
                           }
                       }

                       isValid = false;
                   }else if (Integer.parseInt(option) == 2){
                       Helpers.clearScreen();
                       Helpers.opening();
                       isValid = false;
                   }else {
                       System.out.println("->Incorrecte option ...");
                   }
               }
            }

        }else{
            System.out.println("->Ce Livre est encore indisponible pour le moment . Essayez d'emprunter un autre livre .");
        }
    }

    public void retourner() throws SQLException{
        Helpers.clearScreen();
        System.out.print("\nEntrer Isbn de Livre : ");
        Emprunteur emprunteur;
        LivreEmpruntes livreEmpruntes;
        String isbn = scanner.nextLine();
        Livre livre  = livreRepository.getLivreByIsbn(Integer.parseInt(isbn));
        Helpers.afficherLivre(livre);

        if(livre.getId() > 0){

            if(livre.getStatus() == 0){
                livreEmpruntes = livreEmpruntesRepository.afficherLivreEmpruntes(livre);
                emprunteur = livreEmpruntes.getEmprunteur();
                System.out.println("\nCe Livre a été emprunté par "+ emprunteur.getUsername() + "en la date de : "+
                        livreEmpruntes.getDate() +" avec une date de retour : "+ livreEmpruntes.getRetour() +" .");

                boolean isConfirmed = true;
                while ( isConfirmed ) {
                    System.out.println("-> Confirmez-vous le retour du livre ?");
                    System.out.print("\n1-> yes .\n2-> no  \n->  ");
                    String confirm = scanner.nextLine();
                    if(Integer.parseInt(confirm) == 1){
                        livreEmpruntesRepository.retourner(emprunteur,livre);
                       // livreRepository.livreDisponibilite(livre, 1);
                        isConfirmed = false;
                    }else if(Integer.parseInt(confirm) == 2){
                        System.out.println("\nLes Modifcations sont annulé .\n");
                        isConfirmed = false;
                    }
                }
            }else if (livre.getStatus() == 1){
                System.out.println("Vous pouvez pas retourner ce Livre car est disponible .");
            }else {
                System.out.println("Ce livre a été perdu vous pouvez le retourner .");
            }

        }else{
            System.out.println("->Ce Livre n'éxiste pas . ");
        }
    }

    public void genererUnRapport(){

    }

}
