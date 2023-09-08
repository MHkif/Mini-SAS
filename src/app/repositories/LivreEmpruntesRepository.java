package app.repositories;

import app.Database;
import app.entities.Emprunteur;
import app.entities.Livre;
import app.entities.LivreEmpruntes;

import java.sql.*;

public class LivreEmpruntesRepository {

    Database db  = new Database();
    public boolean emprunter(Emprunteur emprunteur , Livre livre) throws SQLException {
        String sql = "INSERT INTO livreempruntes(memberShip, livreIsbn, retour) VALUES(?,?,DATE_ADD(current_timestamp(), INTERVAL 7 DAY)) ;";

        try(Connection connection = db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, emprunteur.getMemberShip());
            preparedStatement.setInt(2, livre.getIsbn());

            if(preparedStatement.executeUpdate() > 0){
                return true;
            }else {

                return false;
            }
        }
    }

    public boolean retourner(Emprunteur emprunteur, Livre livre) throws SQLException{
        String sql = "DELETE FROM livreempruntes WHERE membership = " + emprunteur.getMemberShip() +
                " AND livreIsbn = " + livre.getIsbn() + "  ;";

        try (Connection connection = db.getConnection();
        Statement statement = connection.createStatement();)
        {
            int rows = statement.executeUpdate(sql);
            if(rows > 0){

                return true;
            }else {

                return false;
            }
        }

    }

    public void genererUnRapport(){
        return;
    }

    public LivreEmpruntes afficherLivreEmpruntes(Livre livre) throws SQLException{
        String sql = "SELECT * FROM `livreempruntes` WHERE livreIsbn = "+livre.getIsbn() +" ;";

        LivreEmpruntes livreEmpruntes = new LivreEmpruntes();
        EmprunteurRepository emprunteurRepository = new EmprunteurRepository();
        LivreRepository livreRepository = new LivreRepository();
        try(Connection connection = db.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            while (resultSet.next()){
                livreEmpruntes.setId(resultSet.getInt("id"));
                livreEmpruntes.setEmprunteur(emprunteurRepository.getEmprunteurByMemberShip(resultSet.getInt("memberShip")));
                livreEmpruntes.setLivre(livreRepository.getLivreByIsbn(resultSet.getInt("livreIsbn")));
                livreEmpruntes.setDate(resultSet.getTimestamp("date"));
                livreEmpruntes.setRetour(resultSet.getTimestamp("retour"));
            }
        }
        return livreEmpruntes;
    }
}
