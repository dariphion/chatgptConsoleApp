package org.example;

import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput (){

        System.out.println("Enter your message: ");
        return scanner.nextLine();
    }

    public void displayMessage(String message){
        System.out.println(message);
    }

    public void close(){
        scanner.close();
    }

}
