import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUIController implements ActionListener, Subscriber, MouseListener {

    GUI gui;
    API api;
    double amount;
    Currencies frånValutaComboBox = Currencies.SEK;
    Currencies tillValutaComboBox = Currencies.USD;
    JTextField frånValutaField;
    Timer timer;
    String originalAmount= "0";

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
            System.out.println("Sending to API");
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
    public void swapFromToLabels() {
        JComboBox<Currencies> comboBox = gui.getFrånValutaComboBox();
        Enum<Currencies> currencyFromFirstComboBox = (Currencies) comboBox.getSelectedItem();
        JComboBox<Currencies> comboBox2 = gui.getTillValutaComboBox();
        Enum<Currencies> currencyFromSecondComboBox = (Currencies) comboBox2.getSelectedItem();

        gui.frånValutaComboBox.setSelectedItem(currencyFromSecondComboBox);
        gui.tillValutaComboBox.setSelectedItem(currencyFromFirstComboBox);

        updateInputText(gui);
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
        swapFromToLabels();
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
