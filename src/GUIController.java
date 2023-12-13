import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIController implements ActionListener, Subscriber {

    GUI gui;
    API api;
    double amount;
    Currencies frånValutaComboBox;
    Currencies tillValutaComboBox;

    String originalAmount= "text";          //Måste finnas bokstäver här pga null-värde

    GUIController(API api, GUI gui) {
        this.api = api;
        this.gui = gui;

    gui.getConvertButton().addActionListener(this);
    gui.getFrånValutaComboBox().addActionListener(this);
    gui.getTillValutaComboBox().addActionListener(this);
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gui.getConvertButton()) {
            if(isValidInput(originalAmount)) {
                api.setApiExchangeInput(frånValutaComboBox, tillValutaComboBox, 200);
            }
    if (e.getSource() == gui.getFrånValutaComboBox()) {
        JComboBox<Currencies> comboBox = gui.getFrånValutaComboBox();
        frånValutaComboBox = (Currencies)comboBox.getSelectedItem();
        }
    if (e.getSource() == gui.getTillValutaComboBox()) {
        JComboBox<Currencies> comboBox = gui.getTillValutaComboBox();
        tillValutaComboBox = (Currencies)comboBox.getSelectedItem();
    }}
    }

    @Override
    public void update(ExchangeInfo info) {
        gui.updateExchangedAmount(info.getExchangedAmount());
        gui.updateRateInformation(info.getBaseCurrency(), info.getTargetCurrency(), info.getRate(), info.getReverseRate());
        gui.updateExchangedDate(info.getDate());

    }

    public boolean isValidInput(String amount) {
        return amount.matches("[0-9]+");
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


