# Guide d'utilisation de l'application : Bibliothéque nationale 

# Prérequis
Avant d'utiliser la Bibliothéque nationale, assurez-vous que les prérequis suivants sont installés sur votre système :

Java Development Kit V17+ (JDK17)+ : L'application est écrite en Java, vous devez donc avoir installé le JDK, Vous pouvez le télécharger sur le site web d'Oracle.

MySQL database : Vous aurez besoin d'un serveur de base de données MySQL. Vous pouvez télécharger et installer MySQL à partir du site officiel de MySQL.

JDBC Driver : Téléchargez et incluez  MySQL JDBC driver dans votre projet. Vous pouvez le télécharger à partir de la page de téléchargement de MySQL Connector/J.


# Installation
 1 - Clonez le dépôt ou téléchargez le code source de l'application sur votre machine locale.
     git clone git
     
 2 - Ouvrez le projet dans votre environnement de développement Java IDE préféré (par exemple, IntelliJ IDEA, Eclipse).

 3 - Inclure le MySQL JDBC driver dans votre projet.


# Database 
Exécutez le script MySQL fourni pour créer la base de données et les tables nécessaires et les remplir avec des données fictives. Vous pouvez utiliser un outil comme MySQL Workbench ou exécuter le script à partir de MySQL commande client.
Veillez à remplacer votre nom d'utilisateur [username] par votre nom d'utilisateur MySQL.
->  mysql -u username -p < mini_sas_db.sql

Vérifiez que la base de données et les tables ont été créées avec succès en consultant votre serveur MySQL.


# Exécution de l'application
 1 - Ouvrez le projet dans votre environnement de développement.
 2 - Testez la connectivité de la base de données pour vous assurer que l'application peut se connecter à votre base de 
     données MySQL avec succès. Vous pouvez le faire en lançant une méthode de connexion de test ou en exécutant un exemple 
     de requête.

 - dans l'application, vous trouverez une classe appelée database qui est responsable de l'établissement de la connexion 
   entre votre application et  mysql database:
   
public class Database {

    private static  final String Driver_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3303/mini_sas_db";
    private static final String USERNAME ="username";
    private static final String PASSWORD = "password";

    public Database(){
        try
        {
            Class.forName(Driver_PATH);
        } catch (Exception e)
        {
            throw new RuntimeException("SomeThing wrong with Database Constructor  : "+e);
        }

    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }
}
     

# Utilisation
 - Utilisez la console de l'application pour effectuer des actions telles que l'ajout de livres, la gestion des impressions, 
   l'emprunter et le retour de livres, ainsi que d'autres tâches liées à la bibliothèque.
   
 - Suivez les instructions et les invites à l'écran pour naviguer dans les fonctionnalités de l'application.
   

