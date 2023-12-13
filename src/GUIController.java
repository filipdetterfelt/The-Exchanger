import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIController implements ActionListener , Subscriber {

    GUI gui;
    API api;

GUIController(API api, GUI gui){
    this.api = api;
    this.gui = gui;

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
        gui.updateExchangedAmount(info.getExchangedAmount());
        gui.updateRateInformation(info.getBaseCurrency(),info.getTargetCurrency(), info.getRate(), info.getReverseRate());
        gui.updateExchangedDate(info.getDate());

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


