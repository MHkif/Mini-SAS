import com.controllers.Navigator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        Helpers.opening();
        Scanner scanner = new Scanner(System.in);
        Navigator.apply(scanner.nextLine());
    }

 }