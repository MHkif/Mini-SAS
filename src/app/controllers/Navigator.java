package app.controllers;

import app.models.Livre;
import app.models.LivreEmpruntes;

public class Navigator {

    public static void apply(String scanner){
        Livre livre = new Livre();
        LivreEmpruntes livreEmpruntes = new LivreEmpruntes();

        try{
            int option = Integer.parseInt(scanner);
            switch (option){
                case 1 :
                    livre.afficherLivres();
                    break;
                case 2 :
                    livre.afficherLivre();
                    break;
                case 3 :
                    livreEmpruntes.afficherLivresEmpruntes();
                    break;
                case 4 :
                    livre.ajouter();
                    break;
                case 5 :
                    livre.modifier(1);
                    break;
                case 6 :
                    livre.supprimer(1);
                    break;
                case 7 :
                    livre.rechercher("");
                    break;
                case 8 :
                    livreEmpruntes.emprunter(1);
                    break;
                case 9 :
                    livreEmpruntes.retourner(1);
                    break;
                case 10 :
                    livreEmpruntes.genererUnRapport();
                    break;


            }

        }catch (Exception e){
            System.out.println("Exception : " + e);
        }

    }

}
