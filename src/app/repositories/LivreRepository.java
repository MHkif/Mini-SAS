package app.repositories;

import app.Database;
import app.entities.Livre;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LivreRepository {

    Database db  = new Database();


    public ArrayList<Livre> afficherLivresDisponible() throws SQLException {
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

    public ArrayList<Livre> afficherLivresEmpruntes() throws SQLException {
        ArrayList<Livre> livres = new ArrayList<Livre>();
        String sql = "SELECT * FROM `livre` WHERE status = 0";

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

    public ArrayList<Livre> afficherLivresPerdu() throws SQLException {
        ArrayList<Livre> livres = new ArrayList<Livre>();
        String sql = "SELECT * FROM `livre` WHERE status = 2";

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

    public Livre ajouter(Livre livre) throws SQLException {
        String sql = "INSERT INTO livre(titre, auteur, isbn, status) VALUES(?,?,?,?) ;";

        try(Connection connection = db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, livre.getTitre());
            preparedStatement.setString(2, livre.getAuteur());
            preparedStatement.setInt(3, livre.getIsbn());
            preparedStatement.setInt(4, livre.getStatus());

          if(preparedStatement.executeUpdate() > 0){
              System.out.println("Le livre a été créer avec  succéss .");
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
                System.out.println("Livre a été supprimé avec succés .");
            }else {
                System.out.println("La suppression du livre a échouer  ....");
            }
        }
        return true;
    }

    public Livre rechercher(String slag) throws SQLException {
        String sql = "SELECT * FROM `livre` WHERE titre LIKE " +
                "'%"+slag+"%' OR auteur LIKE "+  "'%"+slag+"%' ;";

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

    public Livre getLivreByIsbn(int isbn) throws SQLException {
        String sql = "SELECT * FROM `livre` WHERE isbn = "+isbn +" ;";

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


    public void declarerlivrePerdu() throws SQLException {
        String sql = "UPDATE livre " +
                "SET status = 2 " +
                "WHERE isbn IN ( " +
                "    SELECT livreIsbn " +
                "    FROM livreempruntes " +
                "    WHERE retour < CURRENT_TIMESTAMP() " +
                ");";

        try (Connection connection = db.getConnection();
             Statement statement = connection.createStatement();) {
         statement.executeUpdate(sql);


        }

    }

    public void genererStatistiques() throws SQLException{
        Path currentRelativePath = Paths.get("");
        String dirname = currentRelativePath.toAbsolutePath().toString();

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String time;
        time = sdf.format(ts).replaceAll("\\s+","").replaceAll(":", "_");
        try {

            File file = new File(dirname+"/src/statistiques/"+ time +".txt");
            if (file.createNewFile()) {
                try {
                    FileWriter myWriter = new FileWriter(dirname+"/src/statistiques/"+ time +".txt");
                    String s = "\nBienvenue à la Bibliothèque Nationale, l'endroit où la connaissance prend vie." +
                            "\nVoici les statistiques actuelles de notre bibliothèque : " +
                            "\nTotal des livres disponibles : " + this.afficherLivresDisponible().size() +  " ." +
                            "\nTotal des livres empruntés : " + this.afficherLivresEmpruntes().size() +  " ."+
                            "\nTotal des livres perdus : "+ this.afficherLivresPerdu().size() + " ." ;

                    myWriter.write(s);
                    myWriter.close();
                } catch (IOException e) {
                    System.out.println("Une erreur s'est produite.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Fichier déjà existant .");
            }
        } catch (IOException e) {
            System.out.println("Une erreur s'est produite.");
            e.printStackTrace();
        }
    }
}
