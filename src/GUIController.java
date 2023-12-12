import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIController implements ActionListener , Subscriber {

    GUI gui;
    API api;
    PresentationView view;



GUIController(API api, GUI gui, PresentationView view){
    this.api = api;
    this.gui = gui;
    this.view = view;


   //PresentationView view = new PresentationView();

    gui.getConvertButton().addActionListener(this);

}

    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == gui.getConvertButton()) {
        api.setApiExchangeInput(Currencies.SEK, Currencies.NOK, 200);
    }


        }


    @Override
    public void update(ExchangeInfo info) {

        view.setAmountTo(info.getExchangedAmount());
        //gui.xChangerName.setText(String.valueOf(info.getExchangedAmount()));
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


