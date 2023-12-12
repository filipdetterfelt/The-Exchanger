import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIController implements ActionListener {

    private PresentationView presentationView;
    private Gui gui = new Gui(presentationView);

    API api = new API();
    //PresentationView view = new PresentationView();


    public GUIController(PresentationView presentationView) {
        this.presentationView = presentationView;
        // Register the Controller as the listener for GUI events
        this.addButtonActionListener(this);
    }

    //Actionlistener
/*
    ActionListener (Convert){
        api.setApiExchangeInput(view.getCurrencyFrom(), view.getCurrencyTo(), view.getAmountFrom());
    }
    ActionListener (Dropbox){
        view.setCurrencyFrom(currencyFrom);
    }
    ActionListener (Label ruta e){
        view.setAmountTo(e);
    }
    */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gui.) {
            api.setApiExchangeInput(presentationView.getCurrencyFrom(), presentationView.getCurrencyTo(), presentationView.getAmountFrom());
        }
    }
}


