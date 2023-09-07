package app.controllers;

import app.Helpers;
import app.entities.Emprunteur;
import app.entities.Livre;
import app.entities.LivreEmpruntes;
import app.repositories.LivreEmpruntesRepository;
import app.repositories.LivreRepository;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Navigator {

    LivreRepository livreRepository = new LivreRepository();
    LivreEmpruntesRepository livreEmpruntesRepository = new LivreEmpruntesRepository();

    public void apply(){
        Scanner scanner = new Scanner(System.in);
        LivreEmpruntesRepository livreEmpruntes = new LivreEmpruntesRepository();
        try {

            boolean isRunning = true;

           while (isRunning){

               int option = Integer.parseInt(scanner.nextLine());

                   switch (option) {
                       case 0:
                           isRunning = false;
                           break;
                       case 1:
                           this.afficherLivres();
                           break;
                       case 2:
                           livreEmpruntes.afficherLivresEmpruntes();
                           break;
                       case 3:
                           this.ajouterLivre();
                           break;
                       case 4:
                           this.modifierLivre();
                           break;
                       case 5:
                           this.supprimerLivre();
                           break;
                       case 6:
                           this.rechercherLivre();
                           break;
                       case 7:
                           this.emprunter();
                           break;
                       case 8:
                           this.retourner();
                           break;
                       case 9:
                           this.genererUnRapport();
                           break;
                       default:
                           isRunning = false;
                           break;
                   }
               }

        }catch (Exception e){

            System.out.print("Entrez un nombre dans le menu . \n-> ");
            this.apply();
        }
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

                if(Integer.valueOf(confirm) == 1){
                    livre.setTitre(titre);
                    livre.setAuteur(auteur);
                    livre.setIsbn(Integer.parseInt(isbn));

                    livreRepository.ajouter(livre);
                    break;
                }else if(Integer.valueOf(confirm) == 0){
                    livre = new Livre();
                    Helpers.clearScreen();
                    Helpers.opening();
                    break;
                }
            }
        }



    }

    public void modifierLivre() throws SQLException{

        Helpers.clearScreen();
        System.out.print("\nEntrer Isbn de Livre : ");
        Scanner scanner = new Scanner(System.in);
        String isbn = scanner.nextLine();
        Livre livre  = livreRepository.getLivreByIsbn(Integer.valueOf(isbn));
        this.afficherLivre(livre);

        boolean continuer = true;
        while (continuer){

            System.out.println("\nQue Voulez-vous modifier dans ce livre : " +
                    "\n1 -> Titre \n2 -> Auteur \n3 -> Status" +
                    "\n4 -> Quantite \n0 -> Revenir au menu ");
            System.out.print("-> Entrez une option : ");

            String option = scanner.nextLine();
            switch (Integer.valueOf(option)){
                case 1:
                    System.out.print("\nTitre : ");
                    String titre = scanner.nextLine();
                    livre.setTitre(titre);
                    break;
                case 2:
                    System.out.print("\nAuteur : ");
                    String auteur = scanner.nextLine();
                    livre.setAuteur(auteur);
                    break;
                case 3:
                    this.modifierStatus(scanner, livre);
                    break;


                case 0:
                    Helpers.clearScreen();
                    Helpers.opening();
                    continuer = false;
                    break;
            }

            if(Integer.valueOf(option) != 0){

                System.out.println("\nLe Livre Apres la Modification :");
                System.out.println("Titre : "+livre.getTitre()+
                        " \nAuteur : "+livre.getAuteur()+" ."+
                        " \nStatus : "+livre.getStatus()+" .");
                boolean isConfirmed = true;
                while ( isConfirmed ) {
                    System.out.println("\n-> Confirmmer vous l'exécution ?");
                    System.out.print("\n1-> yes .\n2-> no  \n->  ");
                    String confirm = scanner.nextLine();

                    if(Integer.valueOf(confirm) == 1){
                        livreRepository.modifier(livre);
                        break;
                    }else if(Integer.valueOf(confirm) == 2){
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
        Livre livre  = livreRepository.getLivreByIsbn(Integer.valueOf(isbn));

        if(Objects.isNull(livre)){
            this.afficherLivre(livre);
            boolean isConfirmed = true;
            while ( isConfirmed ) {
                System.out.println("\n-> Confirmmer-vous l'exécution ?");
                System.out.print("\n1-> yes .\n2-> no  \n->  ");
                String confirm = scanner.nextLine();

                if(Integer.valueOf(confirm) == 1){
                    livreRepository.supprimer(Integer.parseInt(isbn));
                    break;
                }else if(Integer.valueOf(confirm) == 0){
                    Helpers.clearScreen();
                    Helpers.opening();
                    break;
                }
            }

        }else{
            System.out.println("Il n'y a pas de livre avec l'isbn de : "+isbn +" dans la bibliotheque .");
            Helpers.clearScreen();
            Helpers.opening();
        }


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
        if(livreRepository.afficherLivres().size() < 1){
            System.out.print("-> Il n'y a pas des livres dans la bibliotheque .");
        }else {
            for (Livre livre : livreRepository.afficherLivres()) {
                System.out.println("id : "+livre.getId()+
                        " \nTitre : "+livre.getAuteur()+" ."+
                        " \nAuteur : "+livre.getAuteur()+" ."+
                        " \nISBN : "+livre.getIsbn()+" .");
                System.out.println();
            }
        }

        System.out.print("\n-> Cliquez sur n'importe quelle touche pour revenir  au menu .");
        String scanner = new Scanner(System.in).nextLine();
           Helpers.clearScreen();
           Helpers.opening();


    }

    public void afficherLivre(Livre livre)  {
        System.out.println("\nLe Livre :");
        System.out.println("Titre : "+livre.getTitre()+
                " \nAuteur : "+livre.getAuteur()+" ."+
                " \nISBN : "+livre.getIsbn()+" ."+
                " \nStatus : "+livre.getStatus()+" .");
        
    }

    public void modifierStatus(Scanner scanner, Livre livre){
        System.out.print("\nStatus : ");
        System.out.print("\n1-> disponible .\n2-> non disponible  \n->  ");
        boolean is_positive = true;
        while(is_positive){
            int status = Integer.parseInt(scanner.nextLine());

            if(status < 1 || status > 2 ){
                System.out.print("->Entrez une option correct : \n1-> disponible .\n2-> non disponible  \n->  ");
            } else {
                livre.setStatus(status);
                System.out.println("-> La Status a était modifié . ");
                is_positive = false;
            }

        }

    }

    public void emprunter() throws SQLException {
        Helpers.clearScreen();
        System.out.print("\nEntrer Isbn de Livre : ");
        Scanner scanner = new Scanner(System.in);
        String isbn = scanner.nextLine();
        Livre livre  = livreRepository.getLivreByIsbn(Integer.valueOf(isbn));
        Emprunteur emprunteur = new Emprunteur();
        if(livre.getStatus() == 1){
            livreEmpruntesRepository.emprunter(emprunteur, livre);
        }else{
            System.out.println("->Ce Livre est encore indisponible pour le moment . Essayez d'emprunter un autre livre .");
        }
    }

    public void retourner(){

    }

    public void genererUnRapport(){

    }

}
