import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIController implements ActionListener {
GUIController(){
    API api = new API();
    PresentationView view = new PresentationView();
    GUI gui = new GUI(view);

    gui.getConvertButton().addActionListener(this);
}

    @Override
    public void actionPerformed(ActionEvent e) {
        }
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


