package org.example.view;

import org.example.utils.Printable;

import static org.example.Controller.*;

public class Presenter {

    public static final int TITLE_WIDTH = 50;

    public static String getHeader(){
        StringBuilder sb = new StringBuilder();
        sb.append("=".repeat(TITLE_WIDTH)).append("\r\n");
        sb.append("\t\tSCARE_CROW_VIDEO MOVIE RENTAL\r\n");
        sb.append("=".repeat(TITLE_WIDTH)).append("\r\n");
        return sb.toString();
    }

    public static String getMainMenu(){
        Menu mainMenu = new Menu("Main Menu");
        mainMenu.getMenuItems().add(new MenuItem(String.valueOf(LIST_MOVIES), "List Movies"));
        mainMenu.getMenuItems().add(new MenuItem(String.valueOf(LIST_CUSTOMERS), "Print customer rental record"));
        mainMenu.getMenuItems().add(new MenuItem(String.valueOf(EXIT), "Exit"));
        return String.format("%s%s", getHeader(), mainMenu);
    }

    public static String processArray(Printable[] array, String title){
        Menu mainMenu = new Menu(title);
        int counter = 1;
        for (Printable t : array) {
            String enumeration = String.valueOf(counter++);
            mainMenu.getMenuItems().add(new MenuItem(enumeration, t.getTitle()));
        }
        return mainMenu.toString();
    }

    public static String handlePrintable(Printable printable){
        return String.format("%s\n%s", printable.getTitle(), printable.getBody());
    }
}
