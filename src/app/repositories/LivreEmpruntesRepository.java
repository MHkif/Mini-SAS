package app.repositories;

import app.Database;
import app.entities.Emprunteur;
import app.entities.Livre;
import app.entities.LivreEmpruntes;

public class LivreEmpruntesRepository {

    Database db  = new Database();
    public LivreEmpruntes emprunter(Emprunteur emprunteur , Livre livre){

        return new LivreEmpruntes();
    }

    public LivreEmpruntes retourner(Emprunteur emprunteur, Livre livre){
        return new LivreEmpruntes();
    }

    public void genererUnRapport(){
        return;
    }

    public Livre afficherLivresEmpruntes(){
        return new Livre();
    }
}
