package app;

import app.entities.Livre;

import java.util.Scanner;

public class Helpers {
    public static void opening(){
        System.out.println();
        System.out.println("\n\t\t\t\t\t Bienvenue à la Bibliothèque nationale \t\t\t\t\t\n");
        System.out.println("\nDans notre bibliothèque, vous trouverez tous les livres que vous recherchez .\n");
        System.out.println("Choisis un option :");
        System.out.println("1 - Afficher les livres .");
        System.out.println("2 - Ajouter un livre .");
        System.out.println("3 - Modifier un livre .");
        System.out.println("4 - Supprimer un livre .");
        System.out.println("5 - Rechercher un livre .");
        System.out.println("6 - Emprunter un livre .");
        System.out.println("7 - Retourner un livre .");
        System.out.println("8 - Générer les statistiques.");
        System.out.println("0 - Quitter .");
        System.out.print("\n->  ");

    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void afficherLivre(Livre livre)  {
        System.out.println("\nLes informations de Livre :");
        System.out.println("Titre : "+livre.getTitre()+
                " \nAuteur : "+livre.getAuteur()+" ."+
                " \nISBN : "+livre.getIsbn()+" .");
        if(livre.getStatus() >= 0 && livre.getStatus() <=2){
            switch (livre.getStatus()){
                case 0:
                    System.out.println("Status : emprunté .");
                    break;
                case 1:
                    System.out.println("Status : disponible .");
                    break;
                case 2:
                    System.out.println("Status : perdu .");
                    break;

            }
        }

    }

    public static void modifierStatus(Scanner scanner, Livre livre){
        System.out.print("\nStatus : ");
        System.out.print("\n1-> emprunté .\n2-> disponible . \n3-> perdu . \n->  ");
        boolean is_positive = true;
        while(is_positive){
            int status = Integer.parseInt(scanner.nextLine());

            if(status < 1 || status > 3 ){
                System.out.print("-> Entrez une option correct : \n1-> non disponible .\n2-> disponible  \n->  ");
            } else {
                livre.setStatus(status-1);
                System.out.println("-> La Status a était modifié . ");
                is_positive = false;
            }

        }

    }


    public static String[] LivreColomns = {"Titre" , "Auteur" , "Isbn" , "Status", "Quantite",
                              "titre" , "auteur" , "isbn" , "status", "quantite"};
    public static boolean isInArrayString(String[] arr, String toCheckValue)
    {

        // using Linear Search method
        boolean exist = false;
        for (String element : arr) {
            if (element.equals(toCheckValue)) {
                exist = true;
                break;
            }
        }

        return exist;
    }

    private static void isInArrayInt(int[] arr, int toCheckValue)
    {

        boolean test = false;
        for (int element : arr) {
            if (element == toCheckValue) {
                test = true;
                break;
            }
        }

        // Print the result
        System.out.println("Is " + toCheckValue
                + " present in the array: " + test);
    }

}
