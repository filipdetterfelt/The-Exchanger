import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

public class GUIController implements ActionListener, Observer, MouseListener {

    GUI gui;
    API api;
    private double amount;
    Currencies frånValutaComboBox = Currencies.SEK;
    Currencies tillValutaComboBox = Currencies.USD;
    JTextField frånValutaField;
    Timer timer;
    private String originalAmount = "0";

    GUIController(API api, GUI gui) {
        this.api = api;
        this.gui = gui;
        this.frånValutaField = gui.getFrånValuta();

        gui.getFrånValutaComboBox().addActionListener(this);
        gui.getTillValutaComboBox().addActionListener(this);
        gui.getFrånValuta().addActionListener(this);
        gui.getTillValuta().addActionListener(this);
        gui.getSwapButtonLabel().addMouseListener(this);

        //Timer för delay i API-utskick
        timer = new Timer(1000, e -> {
            if (!originalAmount.equals("0") && isValidInput(gui.frånValuta.getText())) {
                System.out.println("Sending to API from Timer");
                api.setApiExchangeInput(frånValutaComboBox, tillValutaComboBox, amount);
            }
        });
        timer.setRepeats(false);

        gui.getFrånValuta().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateInputText(gui);
                restartTimer();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateInputText(gui);
                restartTimer();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateInputText(gui);
                restartTimer();
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
    }

    @Override
    public void update(Object exchangeinfo) {
        ExchangeInfo info = (ExchangeInfo) exchangeinfo;
        gui.updateExchangedAmount(info.getExchangedAmount());
        updateRateInformation(info.getBaseCurrency(), info.getTargetCurrency(), info.getRate(), info.getReverseRate());
        gui.updateExchangedDate(info.getDate());
        updateSourceOfData(((ExchangeInfo) exchangeinfo).getDocumentation());
    }

    public void updateInputText(GUI gui) {
        originalAmount = gui.frånValuta.getText();
        if (isValidInput(originalAmount) && !originalAmount.equals("0") && !originalAmount.isEmpty()) {
            amount = Double.parseDouble(originalAmount);
        } else if (originalAmount.isEmpty() || originalAmount.equals("0")) {
            amount = 0;
            gui.tillValuta.setText("0");
            gui.tillValuta.repaint();
        }
    }

    public void updateRateInformation(Enum<Currencies> fromCurrency, Enum<Currencies> toCurrency, double rate, double reversedRate) {
        Currencies tempToCurrency = (Currencies) toCurrency;

        DecimalFormat df = new DecimalFormat("#.####");
        String formattedRate = df.format(rate);
        String formattedRateReverse = df.format(reversedRate);


        gui.rateInformation.setText("<html><h1 style ='color: white;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1 " + fromCurrency + " =" +
                "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</br><span style= 'color: #B4B4B4'>" + formattedRate + " </span>" + tempToCurrency.fullName + "</h1>" +
                "<p style='font-size:12px; color: white;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1 " + toCurrency + " = " + formattedRateReverse + " " + fromCurrency +
                "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br><br><br>" +
                "</p></html>");
    }

    public void updateSourceOfData(String data) {
        gui.sourceOfData.setText("<html><p style ='font-size: 8px; color: #B4B4B4;'>Data extracted from " + data.substring(1, data.length() - 6) + "</html>");
        //gui.sourceOfData.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void swapCurrenciesComboBox() {
        /*Timer timer2 = new Timer(1000, e -> {

        });*/
        JComboBox<Currencies> comboBox = gui.getFrånValutaComboBox();
        Enum<Currencies> currencyFromFirstComboBox = (Currencies) comboBox.getSelectedItem();
        JComboBox<Currencies> comboBox2 = gui.getTillValutaComboBox();
        Enum<Currencies> currencyFromSecondComboBox = (Currencies) comboBox2.getSelectedItem();

        gui.frånValutaComboBox.setSelectedItem(currencyFromSecondComboBox);
        gui.tillValutaComboBox.setSelectedItem(currencyFromFirstComboBox);

        updateInputText(gui);
        System.out.println("Swapping currencies");
        timer.restart();
    }

    public void restartTimer() {
        if (timer.isRunning()) {
            timer.stop();
        }
        timer.restart();
    }

    public boolean isValidInput(String amount) {
        return amount.matches("[0-9]+") && amount.length() < 8;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        swapCurrenciesComboBox();
        gui.revalidate();
        gui.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
