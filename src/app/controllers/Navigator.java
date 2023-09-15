package app.controllers;

import app.Helpers;
import app.Status;
import app.Validator;
import app.entities.Emprunteur;
import app.entities.Livre;
import app.entities.LivreEmpruntes;
import app.repositories.EmprunteurRepository;
import app.repositories.LivreEmpruntesRepository;
import app.repositories.LivreRepository;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Navigator {

    Scanner scanner = new Scanner(System.in);
    LivreRepository livreRepository = new LivreRepository();
    LivreEmpruntesRepository livreEmpruntesRepository = new LivreEmpruntesRepository();
    EmprunteurRepository emprunteurRepository = new EmprunteurRepository();


    public void apply(){
      Scanner scanner = new Scanner(System.in);
       Helpers.opening();
        try {

           boolean isRunning = true;

           while (isRunning){
               livreRepository.declarerlivrePerdu();
               Helpers.options();
               String option = scanner.nextLine();
               if(Validator.validInteger(option)){

                   switch (Integer.parseInt(option)) {
                       case 0 -> isRunning = false;
                       case 1 -> this.afficherLivres();
                       case 2 -> this.ajouterLivre();
                       case 3 -> this.modifierLivre();
                       case 4 -> this.supprimerLivre();
                       case 5 -> this.rechercherLivre();
                       case 6 -> this.emprunter();
                       case 7 -> this.retourner();
                       case 8 -> this.genererStatistiques();
                   }

               }
               else{
                   System.out.println("\nEntrée invalide , Choisir une option parmi les suivantes : ");
               }
           }

        }catch (Exception e){
            System.out.println("Crashed : "+e);
        }
    }

    public void afficherLivres() throws  SQLException{

        boolean continuer = true;
        while (continuer){

            System.out.println("""

                    Que Voulez-vous afficher :\s
                    1 -> Les livres disponible .\s
                    2 -> Les livres empruntes .\s
                    3 -> Les livres perdu .
                    0 -> Revenir au menu\s""");
            System.out.print("-> Entrez une option : ");

            String option = scanner.nextLine();
            if (Validator.validInteger(option)) {
                switch (Integer.parseInt(option)) {
                    case 1 -> this.afficherLivresDisponible();
                    case 2 -> this.afficherLivresEmpruntes();
                    case 3 -> this.afficherLivresPerdu();
                    case 0 -> {
                        continuer = false;
                    }
                }
            }else{
                System.out.print("\nEntrée invalide , Choisir une option parmi les suivantes : ");
            }

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


    }

    public void afficherLivresEmpruntes() throws SQLException {
        System.out.println("\nLa liste des livres empruntés : \n");
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


    }

    public void ajouterLivre() throws SQLException {
        Helpers.clearScreen();
        Livre livre = new Livre();
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------------------");
        System.out.println("\n-> Pour continuer entrez n'importe quelle touche  , Entrer 0 pour revenir au menu ?");
        if(scanner.nextLine().equals("0")){
            Helpers.clearScreen();
        }else{

            System.out.println("\n-> Entrer les informations du nouveau livre : ");

            System.out.print("\n-> Titre du livre : ");
            String titre  = scanner.nextLine();
            System.out.print("-> Auteur du livre : ");

            String auteur  = scanner.nextLine();
            if(!Validator.validString(auteur)){
                boolean confirmAutheur = true;
                while (confirmAutheur){
                    System.out.println("\nVous ne pouvez pas créer un nom d'auteur en utilisant des chiffres - utilisez des lettres :");
                    System.out.print("-> Auteur du livre : ");
                    auteur  = scanner.nextLine();
                    if(Validator.validString(auteur)){
                        confirmAutheur = false;
                    }else {
                        confirmAutheur = true;
                    }
                }
            }
            System.out.print("-> ISBN du livre : ");
            String isbn  = scanner.nextLine();
            if(!Validator.validInteger(isbn)){
            boolean confirmIsbn = true;
            while (confirmIsbn){
                System.out.println("\nVous ne pouvez pas créer un Isbn en utilisant des letters - utilisez des chiffres :");
                System.out.print("-> ISBN du livre : ");
                isbn  = scanner.nextLine();
                if(Validator.validInteger(isbn)){
                    confirmIsbn = false;
                }
            }
            }

            System.out.println("\nLe nouveau livre :");
            System.out.println("Titre : "+titre+
                    " .\nAuteur : "+auteur+" ."+
                    " .\nISBN : "+isbn+" .");

            boolean isConfirmed = true;
            while ( isConfirmed ) {
                System.out.println("\n-> Confirmmer vous l'exécution ?");
                System.out.print("\n1-> yes .\n2-> no  \n->  ");
                String confirmOption = scanner.nextLine();

                if(Integer.parseInt(confirmOption) == 1){
                    livre.setTitre(titre);
                    livre.setAuteur(auteur);
                    livre.setIsbn(Integer.parseInt(isbn));
                    livreRepository.ajouter(livre);

                    isConfirmed = false;
                }else if(Integer.parseInt(confirmOption) == 2){
                    livre = new Livre();
                    System.out.println("\nAnnuler L'ajoute d'un nouveau livre .\n");
                    isConfirmed = false;
                }
            }
           Helpers.backToMenu(scanner);
        }



    }

    public void modifierLivre() throws SQLException{
        System.out.println("\nIci, vous pouvez accéder et éditer des livres par isbn.");
        System.out.print("-> Entrer Isbn de Livre : ");
        String isbn = scanner.nextLine();

        if(!Validator.validInteger(isbn)){

            boolean confirmIsbn = true;
            while (confirmIsbn){
                System.out.println("\nL'isbn doit être numérique  .");
                System.out.print("->Entrer Isbn de Livre : ");
                isbn  = scanner.nextLine();
                if(Validator.validInteger(isbn)){
                    confirmIsbn = false;
                }else {
                    confirmIsbn = true;
                }
            }


        }

        Livre livre  = livreRepository.getLivreByIsbn(Integer.parseInt(isbn));
        if(livre.getId() > 0 ){
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
        else {
            System.out.println("Il n'existe aucun livre correspondant au Isbn que vous avez fourni .");
        }

        Helpers.backToMenu(scanner);
    }

    public void supprimerLivre() throws SQLException{
        System.out.print("\nEntrer Isbn de Livre : ");
        String isbn = scanner.nextLine();

        if(!Validator.validInteger(isbn)){

            boolean confirmIsbn = true;
            while (confirmIsbn){
                System.out.println("\nL'isbn doit être numérique  .");
                System.out.print("->Entrer Isbn de Livre : ");
                isbn  = scanner.nextLine();
                if(Validator.validInteger(isbn)){
                    confirmIsbn = false;
                }else {
                    confirmIsbn = true;
                }
            }
        }

        Livre livre  = livreRepository.getLivreByIsbn(Integer.parseInt(isbn));
        if(livre.getId() > 0){
            Helpers.afficherLivre(livre);
            boolean isConfirmed = true;
            while ( isConfirmed ) {
                System.out.println("\n-> Confirmmer-vous l'exécution ?");
                System.out.print("\n1-> yes .\n2-> no  \n->  ");
                String confirm = scanner.nextLine();

                if(Integer.parseInt(confirm) == 1){
                    livreRepository.supprimer(Integer.parseInt(isbn));
                    isConfirmed = false;
                }else if(Integer.parseInt(confirm) == 2){
                    isConfirmed = false;
                }
            }

        }else{
            System.out.println("Il n'y a pas de livre avec l'isbn de : "+isbn +" dans la bibliotheque .");
        }

        Helpers.backToMenu(scanner);
    }

    public void rechercherLivre() throws SQLException{
        Livre livre;
        boolean isConfirmed = true;
        while ( isConfirmed ) {
            System.out.println("\n-> Voulez-vous rechercher par ?");
            System.out.print("\n1-> Isbn .\n2-> Titre, Auteur . \n0 -> Revenir au menu .\n->  ");
            String optionRecherche = scanner.nextLine();

            if(Validator.validInteger(optionRecherche)) {


                switch (Integer.parseInt(optionRecherche)) {
                    case 0:
                        isConfirmed = false;
                        break;
                    case 1:
                        System.out.print("-> Rechercher par Isbn : ");
                        String isbn = scanner.nextLine();
                        if(!Validator.validInteger(isbn)){
                            boolean confirmIsbn = true;
                            while (confirmIsbn){
                                System.out.println("\nL'isbn doit être numérique  .");
                                System.out.print("-> Entrer Isbn de Livre : ");
                                isbn  = scanner.nextLine();
                                if(Validator.validInteger(isbn)){
                                    confirmIsbn = false;
                                }else {
                                    confirmIsbn = true;
                                }
                            }
                        }

                        livre = livreRepository.getLivreByIsbn(Integer.parseInt(isbn));

                        if(livre.getIsbn() > 0){
                            Helpers.afficherLivre(livre);
                        } else {
                            System.out.println("Il n'existe aucun livre correspondant au Isbn que vous avez fourni .");
                        }

                        System.out.print("\n-> Cliquez sur n'importe quelle touche pour continer ...");
                        scanner.nextLine();
                        break;
                    case 2:
                        System.out.print("-> Rechercher par Titre | Auteur : ");
                        String slag = scanner.nextLine();

                        if(!Validator.validString(slag)){
                            boolean confirmSlag = true;
                            while (confirmSlag){
                                System.out.println("\nLa saisie doit être un texte valide  .");
                                System.out.print("-> Entrer Titre | Auteur du Livre : ");
                                slag  = scanner.nextLine();
                                if(Validator.validString(slag)){
                                    confirmSlag = false;
                                }else {
                                    confirmSlag = true;
                                }
                            }
                        }

                        livre = livreRepository.rechercher(slag);
                        if (livre.getId() > 0) {
                            Helpers.afficherLivre(livre);
                        } else {
                            System.out.print("\n-> Il n'existe aucun livre correspondant au nom que vous avez fourni .");

                        }
                        Helpers.backToMenu(scanner);
                        break;
                }

            }else{
                    System.out.print("\nEntrée invalide , Choisir une option parmi les suivantes : ");
                }



        }
    }

    public void emprunter() throws SQLException {
        System.out.print("\nEntrer Isbn de Livre : ");
        Emprunteur emprunteur;
        String isbn = scanner.nextLine();

        if(!Validator.validInteger(isbn)){
            boolean confirmIsbn = true;
            while (confirmIsbn){
                System.out.println("\nL'isbn doit être numérique  .");
                System.out.print("-> Entrer Isbn de Livre : ");
                isbn  = scanner.nextLine();
                if(Validator.validInteger(isbn)){
                    confirmIsbn = false;
                }else {
                    confirmIsbn = true;
                }
            }
        }
        Livre livre  = livreRepository.getLivreByIsbn(Integer.parseInt(isbn));
       if(livre.getId() > 0){
           Helpers.afficherLivre(livre);

           if(livre.getStatus() > 0){
               System.out.print("\n-> Entrer MemberShip d'emprunteur : ");
               String memberShip = scanner.nextLine();

               if(!Validator.validInteger(memberShip)){
                   boolean confirmIsbn = true;
                   while (confirmIsbn){
                       System.out.println("\nMemberShip doit être numérique  .");
                       System.out.print("-> Entrer MemberShip d'emprunteur : ");
                       memberShip  = scanner.nextLine();
                       if(Validator.validInteger(memberShip)){
                           confirmIsbn = false;
                       }else {
                           confirmIsbn = true;
                       }
                   }
               }
               emprunteur = emprunteurRepository.getEmprunteurByMemberShip(Integer.parseInt(memberShip));

               if(emprunteur.getMemberShip() > 0){
                   if (livreEmpruntesRepository.emprunter(emprunteur, livre) ){
                       System.out.println("Le livre a été emprunté par "+ emprunteur.getUsername() + " avec succès .");
                   }else{
                       System.out.println("L'empruntation de livre a échoué ...");
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

                      if(Validator.validInteger(option)){
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
                                      isConfirmed = false;
                                  }
                                  else {
                                      System.out.println("->Incorrecte option ...");
                                  }
                              }

                              isValid = false;
                          }
                          else if (Integer.parseInt(option) == 2){
                              isValid = false;
                          }
                          else {
                              System.out.println("\nEntrée invalide , Choisir une option parmi les suivantes : ");
                          }
                      }
                        else {
                           System.out.println("\nEntrée invalide , Choisir une option parmi les suivantes : ");
                       }
                   }
               }

           }else{
               System.out.println("-> Ce livre n'est toujours pas disponible pour le moment. Essayez d'emprunter un autre livre .");
           }
       }
       else {
           System.out.println("Il n'existe aucun livre correspondant au Isbn que vous avez fourni .");
       }
       Helpers.backToMenu(scanner);
    }

    public void retourner() throws SQLException{
        Helpers.clearScreen();
        System.out.print("\nEntrer Isbn de Livre : ");
        Emprunteur emprunteur;
        LivreEmpruntes livreEmpruntes;
        String isbn = scanner.nextLine();
        isbn = Helpers.intValidation(isbn,"Isbn", scanner);

        Livre livre  = livreRepository.getLivreByIsbn(Integer.parseInt(isbn));

        if(livre.getId() > 0){
            Helpers.afficherLivre(livre);
            if(livre.getStatus() == 0){
                livreEmpruntes = livreEmpruntesRepository.afficherLivreEmpruntes(livre);
                emprunteur = livreEmpruntes.getEmprunteur();

                System.out.print("\nEntrer your membership : ");

                String checkMemberShip = scanner.nextLine();
                checkMemberShip = Helpers.intValidation(checkMemberShip, "membership", scanner);

                if(Integer.parseInt(checkMemberShip) == emprunteur.getMemberShip()){
                    System.out.println("\nCe Livre a été emprunté par "+ emprunteur.getUsername() + " en la date de : "+
                            livreEmpruntes.getDate() +" avec une date de retour : "+ livreEmpruntes.getRetour() +" .");

                    boolean isConfirmed = true;
                    while ( isConfirmed ) {
                        System.out.println("\n-> Confirmez-vous le retour du livre ?");
                        System.out.print("1-> yes .\n2-> no  \n->  ");
                        String confirm = scanner.nextLine();
                        if (Validator.validInteger(confirm)) {
                            switch (Integer.parseInt(confirm)) {
                                case 1 -> {
                                    if (livreEmpruntesRepository.retourner(emprunteur, livre)) {
                                        System.out.println("Le livre a été retourné avec succès .");
                                    } else {
                                        System.out.println("Échec du retour du livre  ....");

                                    }
                                    isConfirmed = false;
                                }
                                case 2 -> {
                                    System.out.println("\nLes Modifcations sont annulé .\n");
                                    isConfirmed = false;
                                }
                            }
                        }else{
                            System.out.println("\nEntrée invalide , Choisir une option parmi les suivantes : ");
                        }


                    }
                }else {
                    System.out.println("votre memberShip ne correspond pas à memberShip emprunteur de ce livre .");
                }
            }else if (livre.getStatus() == 1){
                System.out.println("Vous ne pouvez pas return ce livre car il est disponible .");
            }else {
                System.out.println("Ce livre a été perdu, vous pouvez le rendre.");
            }

        }else{
            System.out.println("Il n'existe aucun livre correspondant au l'isbn que vous avez fourni .");

        }
       Helpers.backToMenu(scanner);

    }

    public void genererStatistiques() throws SQLException{

        System.out.printf("""
                \nBienvenue à la Bibliothèque Nationale, l'endroit où la connaissance prend vie.
                Voici les statistiques actuelles de notre bibliothèque :
                ---------------------------------------               
                | Total des livres disponibles : %s    |
                | Total des livres empruntés : %S      |
                | Total des livres perdus : %S         |
                --------------------------------------- 
                
                """, livreRepository.afficherLivresDisponible().size(),
                livreRepository.afficherLivresEmpruntes().size(),
                livreRepository.afficherLivresPerdu().size());
        livreRepository.genererStatistiques();
        Helpers.backToMenu(scanner);
    }

    public void afficherList(ArrayList<Livre> livres, String status)  throws SQLException{
        System.out.println("\nLa liste des livres "+status+" : ");
        if(livres.size() < 1){
            System.out.print("-> Il n'y a pas des livres "+status+" dans la bibliotheque .");
        }else {
            for (Livre livre : livres) {
                Helpers.afficherLivre(livre);
                System.out.println();
            }
        }
    }

}
