import app.Helpers;
import app.controllers.Navigator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Navigator navigator = new Navigator();
        Helpers.opening();
        Scanner scanner = new Scanner(System.in);
        navigator.apply(scanner.nextLine());
    }

 }