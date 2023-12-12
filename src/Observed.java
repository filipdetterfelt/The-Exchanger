import java.util.ArrayList;

public interface Observed {
    ArrayList<Subscriber> listOfSubscribers = new ArrayList<>();

    void addSubscriber(Subscriber subscriber);

    void removeSubscriber(Subscriber subscriber);

    void notifySubscriber(ExchangeInfo data);
}
