package app.repositories;

import app.Database;
import app.entities.Emprunteur;

import java.sql.*;

public class EmprunteurRepository {

    Database db  = new Database();

    public Emprunteur ajouter(Emprunteur emprunteur) throws SQLException{
        String sql = "INSERT INTO emprunteur(memberShip, username, cin) VALUES(?,?,?) ;";

        try(Connection connection = db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){

            preparedStatement.setInt(1, emprunteur.getMemberShip());
            preparedStatement.setString(2, emprunteur.getUsername());
            preparedStatement.setString(3, emprunteur.getCin());

            if(preparedStatement.executeUpdate() > 0){
                System.out.println("L'emprunteur a été crée avec succès .");
            }else {
                System.out.println("La creation d'un nouvel emprunteur a échoué ....");
                return  new Emprunteur();
            }
        }
        return emprunteur;
    }
    public Emprunteur getEmprunteurByMemberShip(int memberShip) throws SQLException {
        String sql = "SELECT * FROM emprunteur WHERE memberShip = "+ memberShip +" ;";
        Emprunteur emprunteur = new Emprunteur();
        try (Connection connection = db.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);
        ){
            while (resultSet.next()){
                emprunteur.setMemberShip(resultSet.getInt("memberShip"));
                emprunteur.setUsername(resultSet.getString("username"));
                emprunteur.setCin(resultSet.getString("cin"));
            }
        }

        return emprunteur;
    }
}
