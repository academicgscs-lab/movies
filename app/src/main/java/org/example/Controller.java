package org.example;

import org.example.managers.model.CustomerManager;
import org.example.managers.model.MovieManager;
import org.example.utils.Printable;
import org.example.view.Presenter;

import java.util.Scanner;

public class Controller {
    public static final int LIST_MOVIES = 1;
    public static final int LIST_CUSTOMERS = 2;
    public static final int EXIT = 3;

    private final CustomerManager customerManager;
    private final MovieManager movieManager;
    private final Scanner scanner;

    public Controller(CustomerManager customerManager, MovieManager movieManager) {
        this.customerManager = customerManager;
        this.movieManager = movieManager;
        scanner = new Scanner(System.in);
    }

    public void run(){
        boolean isRunning = true;
        while (isRunning) {
            System.out.println(Presenter.getMainMenu());
            switch (inquireOption()) {
                case LIST_MOVIES:
                    handleListMovieOption();
                    break;
                case LIST_CUSTOMERS:
                    handleListCustomerOption();
                    break;
                case EXIT:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid selection");
            }
        }
        scanner.close();
    }

    private void handleListMovieOption(){
        Printable[] movies = movieManager.getItems().values().toArray(new Printable[0]);
        System.out.println(Presenter.processArray(movies, "MOVIES"));
        handleItemSelection(movies);
    }

    private void handleListCustomerOption(){
        Printable[] customers = customerManager.getItems().values().toArray(new Printable[0]);
        System.out.println(Presenter.processArray(customers, "CUSTOMERS"));
        handleItemSelection(customers);
    }

    private void handleItemSelection(Printable[] items){
        System.out.println(Presenter.handlePrintable(items[inquireOption()-1]));
    }

    private int inquireOption(){
        System.out.print("Select item\n> ");
        return scanner.nextInt();
    }
}
