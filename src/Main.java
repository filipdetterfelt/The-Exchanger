// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Main {
    public static void main(String[] args) {

        API api = new API();
        GUI gui = new GUI();
        GUIController guiController = new GUIController(api, gui);

        api.addSubscriber(guiController);
        api.addSubscriber(gui);


    }
}

