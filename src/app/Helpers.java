package app;

public class Helpers {
    public static void opening(){
        System.out.println();
        System.out.println("\n\t\t\t\t\t Bienvenue à la Bibliothèque nationale \t\t\t\t\t\n");
        System.out.println("\nDans notre bibliothèque, vous trouverez tous les livres que vous recherchez .\n");
        System.out.println("Choisis un option :");
        System.out.println("1 - Afficher tous les livres .");
        System.out.println("2 - Afficher les livres empruntés .");
        System.out.println("3 - Ajouter un livre .");
        System.out.println("4 - Modifier un livre .");
        System.out.println("5 - Supprimer un livre .");
        System.out.println("6 - Rechercher un livre .");
        System.out.println("7 - Emprunter un livre .");
        System.out.println("8 - Retourner un livre .");
        System.out.println("9 - Générer un rapport .");
        System.out.println("0 - Quitter .");
        System.out.print("\n->  ");

    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
