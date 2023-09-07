package app.repositories;

import app.Database;
import app.entities.Livre;
import com.sun.jdi.Value;

import java.sql.*;
import java.util.ArrayList;

public class LivreRepository {

    Database db  = new Database();

    public Livre ajouter(Livre livre) throws SQLException {
        String sql = "INSERT INTO livre(titre, auteur, isbn, status) VALUES(?,?,?,?) ;";

        try(Connection connection = db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, livre.getTitre());
            preparedStatement.setString(2, livre.getAuteur());
            preparedStatement.setInt(3, livre.getIsbn());
            preparedStatement.setInt(4, livre.getStatus());

          if(preparedStatement.executeUpdate() > 0){
              System.out.println("Livre created successfully .");
          }else {
              System.out.println("Inserer livre failed ....");
          }
        }
        return livre;
    }

    public Livre modifier(Livre livre) throws SQLException {
        String sql = "UPDATE livre SET auteur = ?, titre = ?, isbn = ?, status = ?" +
                "  WHERE id = "+ livre.getId() + " ;";

        try(Connection connection = db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, livre.getAuteur());
            preparedStatement.setString(2, livre.getTitre());
            preparedStatement.setInt(3, livre.getIsbn());
            preparedStatement.setInt(4, livre.getStatus());
            if(preparedStatement.executeUpdate() > 0){
                System.out.println("Livre Updated successfully .");
            }else {
                System.out.println("Update livre failed ....");
            }
        }

        return livre;
    }

    public boolean supprimer(int isbn) throws SQLException {
        String sql = "DELETE FROM livre WHERE isbn = "+ isbn  + " ;";

        try(Connection connection = db.getConnection();
            Statement statement = connection.createStatement();){
            int rows = statement.executeUpdate(sql);
            if(rows > 0){
                System.out.println("Livre deleted successfully .");
            }else {
                System.out.println("delete livre failed ....");
            }
        }
        return true;
    }

    public Livre rechercher(String slag) throws SQLException {
        String sql = "SELECT * FROM `livre` WHERE titre LIKE " +
                "'%"+slag+"%' OR "+  "'%"+slag+"%' ;";

        Livre livre = new Livre();
        try(Connection connection = db.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            while (resultSet.next()){

                livre.setId(resultSet.getInt("id"));
                livre.setTitre(resultSet.getString("titre"));
                livre.setAuteur(resultSet.getString("auteur"));
                livre.setIsbn(resultSet.getInt("isbn"));
                livre.setStatus(resultSet.getInt("status"));

            }
        }
        return livre;

    }



    public ArrayList<Livre> afficherLivres() throws SQLException {
        ArrayList<Livre> livres = new ArrayList<Livre>();
        String sql = "SELECT * FROM `livre` WHERE status = 1";

        try(Connection connection = db.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            while (resultSet.next()){
                Livre livre = new Livre();
                livre.setId(resultSet.getInt("id"));
                livre.setTitre(resultSet.getString("titre"));
                livre.setAuteur(resultSet.getString("auteur"));
                livre.setIsbn(resultSet.getInt("isbn"));
                livre.setStatus(resultSet.getInt("status"));
                livres.add(livre);
            }

        }
        return livres;
    }

    public Livre getLivreByIsbn(int isbn) throws SQLException {
        String sql = "SELECT * FROM livre WHERE isbn = " + Integer.valueOf(isbn).toString();
        Livre livre = new Livre();
        try(Connection connection = db.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            while (resultSet.next()){

                livre.setId(resultSet.getInt("id"));
                livre.setTitre(resultSet.getString("titre"));
                livre.setAuteur(resultSet.getString("auteur"));
                livre.setIsbn(resultSet.getInt("isbn"));
                livre.setStatus(resultSet.getInt("status"));

            }
        }
        return livre;

    }

    public Boolean livreDisponibilite(int isbn) throws SQLException{
       Livre livre =  this.getLivreByIsbn(isbn);
       if(livre.getStatus() == 1){
           return true;
       }else{
           return false;
       }
    }
}
