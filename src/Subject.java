public interface Subject {
    // Needs ArrayList for the Observed class
    // ArrayList<Subscriber> listOfSubscribers = new ArrayList<>();
    void addSubscriber(Observer observer);

    void removeSubscriber(Observer observer);

    void notifySubscriber(Object data);
}
