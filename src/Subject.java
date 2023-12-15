public interface Subject {
    // Needs ArrayList for the Observed class
    // ArrayList<Subscriber> listOfSubscribers = new ArrayList<>();
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObserver(Object data);
}
