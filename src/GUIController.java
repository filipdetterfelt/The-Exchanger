import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIController implements ActionListener, Subscriber {

    GUI gui;
    API api;
    double amount;
    Currencies frånValutaComboBox = Currencies.SEK;
    Currencies tillValutaComboBox = Currencies.USD;
    JTextField frånValutaField;
    Timer timer;
    String originalAmount= "0";          //Måste finnas bokstäver här pga null-värde

    GUIController(API api, GUI gui) {
        this.api = api;
        this.gui = gui;
        this.frånValutaField = gui.getFrånValuta();

   // gui.getConvertButton().addActionListener(this);
    gui.getFrånValutaComboBox().addActionListener(this);
    gui.getTillValutaComboBox().addActionListener(this);
    gui.getFrånValuta().addActionListener(this);
    gui.getTillValuta().addActionListener(this);
        //Timer för delay i API-utskick
        timer = new Timer(1500, e -> {
            if(!originalAmount.equals("0") && isValidInput(gui.frånValuta.getText())) {
                System.out.println("Sending to API");
                api.setApiExchangeInput(frånValutaComboBox, tillValutaComboBox, amount);
            }
        });
        timer.setRepeats(false);
    gui.getFrånValuta().getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateInputText(gui);
            timer.restart();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateInputText(gui);
            timer.restart();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateInputText(gui);
            timer.restart();
        }
    });
}

    @Override
    public void actionPerformed(ActionEvent e) {
            if (e.getSource() == gui.getFrånValutaComboBox()) {
                JComboBox<Currencies> comboBox = gui.getFrånValutaComboBox();
                frånValutaComboBox = (Currencies) comboBox.getSelectedItem();
            }
            if (e.getSource() == gui.getTillValutaComboBox()) {
                JComboBox<Currencies> comboBox = gui.getTillValutaComboBox();
                tillValutaComboBox = (Currencies) comboBox.getSelectedItem();
            }

           /* if (e.getSource() == gui.getConvertButton()) {
                if (isValidInput(originalAmount)) {
                    double amount = Double.parseDouble(originalAmount);
                    api.setApiExchangeInput(frånValutaComboBox, tillValutaComboBox, amount);
                }

            }*/
        }
    @Override
    public void update(ExchangeInfo info) {
        gui.updateExchangedAmount(info.getExchangedAmount());
        gui.updateRateInformation(info.getBaseCurrency(), info.getTargetCurrency(), info.getRate(), info.getReverseRate());
        gui.updateExchangedDate(info.getDate());
    }
    public void updateInputText(GUI gui) {
        originalAmount = gui.frånValuta.getText();
        if (isValidInput(originalAmount) && !originalAmount.equals("0") && !originalAmount.isEmpty()) {
            amount = Double.parseDouble(originalAmount);
        }
        else if (originalAmount.isEmpty() || originalAmount.equals("0")) {
            amount = 0;
            gui.tillValuta.setText("0");
            gui.tillValuta.repaint();
        }
    }

    public boolean isValidInput(String amount) {
        return amount.matches("[0-9]+") && amount.length() < 8;
    }
}
