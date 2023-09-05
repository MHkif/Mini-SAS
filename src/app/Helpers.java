package app;

public class Helpers {
    public static void opening(){
        System.out.println();
        System.out.println("\n\t\t\t\t\t Bienvenue à la Bibliothèque nationale \t\t\t\t\t\n");
        System.out.println("\nDans notre bibliothèque, vous trouverez tous les livres que vous recherchez .\n");
        System.out.println("Choisis un option :");
        System.out.println("1 - Afficher tous les livres .");
        System.out.println("2 - Afficher un livre .");
        System.out.println("3 - Afficher les livres empruntés .");
        System.out.println("4 - Ajouter un livre .");
        System.out.println("5 - Modifier un livre .");
        System.out.println("6 - Supprimer un livre .");
        System.out.println("7 - Rechercher un livre .");
        System.out.println("8 - Emprunter un livre .");
        System.out.println("9 - Retourner un livre .");
        System.out.println("10 - Générer un rapport .");
        System.out.println("0 - Quitter .");
        System.out.print("\n->  ");

    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}
