package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ConfigManager configManager = new ConfigManager();

        APIManager apiManager = new APIManager(configManager);

        UserInterface ui = new UserInterface();

        ui.displayMessage("Welcome to the ChatGPT Console Application! Type 'quit' to quit.");

        try {
            while (true) {

                String input = ui.getUserInput();


                if (input.equalsIgnoreCase("quit")) {
                    ui.displayMessage("Exiting application...");
                    break;
                }

                String response = apiManager.sendMessage(input);

                ui.displayMessage(response);
            }
        } finally {
            ui.close();  // Closing the scanner
        }

        ui.displayMessage("Thank you for using the ChatGPT Console Application!");
    }
}