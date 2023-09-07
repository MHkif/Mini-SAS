package app.repositories;

import app.Database;
import app.entities.Emprunteur;
import app.entities.Livre;
import app.entities.LivreEmpruntes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class LivreEmpruntesRepository {

    Database db  = new Database();
    public boolean emprunter(Emprunteur emprunteur , Livre livre) throws SQLException {
        String sql = "INSERT INTO livreempruntes(memberShip, livreIsbn, retour) VALUES(?,?,DATE_ADD(current_timestamp(), INTERVAL 7 DAY)) ;";

        try(Connection connection = db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, emprunteur.getMemberShip());
            preparedStatement.setInt(2, livre.getIsbn());

            if(preparedStatement.executeUpdate() > 0){
                System.out.println("Le livre a été emprunté par "+ emprunteur.getUsername() + " avec succès .");

                return true;
            }else {
                System.out.println("L'empruntation de livre a échoué ...");
                return false;
            }
        }
    }

    public LivreEmpruntes retourner(Emprunteur emprunteur, Livre livre){
        return new LivreEmpruntes();
    }

    public void genererUnRapport(){
        return;
    }

    public Livre afficherLivreEmpruntes(Livre livre){
        return new Livre();
    }
}
