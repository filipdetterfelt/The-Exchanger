public class Main {
    public static void main(String[] args) {

        API api = new API();
        GUI gui = new GUI();
        GUIController guiController = new GUIController(api, gui);

        api.addObserver(guiController);
    }
}