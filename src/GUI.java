import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    ImageIcon originalJulgranIcon = new ImageIcon("src/resources/tree.png");
    Color myColor = Color.decode("#6c25be");
    Color colorOfButton = Color.decode("#FFCF41");


    //JPanel headPanel = new JPanel();
    JPanel topPanel = new JPanel(new BorderLayout()); //Huvudpanelen för appen
    JPanel centerPanel = new JPanel();
    JPanel bottomPanel = new JPanel();


    JButton convertButton = new JButton("Convert"); //Knapp för att konvertera valutan
    JButton swapButton = new JButton(); //Knapp för att swappa valörer (Lägg in pilar)
    JComboBox<Currencies> frånValutaComboBox = new JComboBox<>((Currencies.values())); // COmbobox för från valuta
    JComboBox<Currencies> tillValutaComboBox = new JComboBox<>((Currencies.values())); // COmbobox för till valuta
    JLabel xChangerName = new JLabel("The X:changer"); //Rubriken på appen
    JLabel tillLabel = new JLabel("Till:"); //Label där det står till
    JLabel frånLabel = new JLabel("Från:"); //Label där det står från
    JTextField frånValuta = new JTextField(); //Label där de presenterar vilken valuta till (lägg in värde)
    JTextField tillValuta = new JTextField(); //Label där de presenteras vilken valuta från (lägg in värde)
    JLabel rateInformation = new JLabel(); //Label för att presentera nuvarande kurs
    JLabel sourceOfData = new JLabel("<html>extracted from</html>"); //Label för att pressentera källan av datan
    JLabel iconChristmasTree = new JLabel();
    JLabel dateOfExchange = new JLabel();

    // Create a new JDialog for the loading screen
    // Create a new ImageIcon for the spinner


    public GUI() {

        //Lägg till bilden på granen här
        topPanel.add(iconChristmasTree, BorderLayout.SOUTH);
        iconChristmasTree.setIcon(originalJulgranIcon);
        iconChristmasTree.setHorizontalAlignment(SwingConstants.CENTER);

        //Style
        xChangerName.setFont(new Font("Arial", Font.BOLD, 40)); //Ändra stil på titeln
        xChangerName.setHorizontalAlignment(SwingConstants.CENTER);
        xChangerName.setVerticalAlignment(SwingConstants.CENTER);
        topPanel.add(xChangerName, BorderLayout.CENTER); //Lägger till titeln till toppanel

        //add(headPanel);
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        topPanel.setBackground(myColor);
        centerPanel.setBackground(myColor);
        bottomPanel.setBackground(myColor);


        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(2, 1, 5, 1); //Marginal mellan komponenter

        //Lägger till frånlabel och frånvalutaComboBox i centrum
        //Fixa så att de flyttas åt höger respektive vänster
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        centerPanel.add(frånLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        centerPanel.add(frånValutaComboBox, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        frånValuta.setColumns(5);
        centerPanel.add(frånValuta, gbc);
        frånValuta.setText("");
        tillValuta.setFocusable(false);
        tillValuta.setEditable(false);

        /*gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        frånValuta.setColumns(5);
        centerPanel.add(frånValuta, gbc);*/


        //Lägger till tillLabel och tillValutaComboBox i centrum
        frånValutaComboBox.setSelectedItem(Currencies.SEK);
        tillValutaComboBox.setSelectedItem(Currencies.USD);

        gbc.gridx = 2; //Öka gridX för att flytta den mer åt höger
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        centerPanel.add(tillLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        centerPanel.add(tillValutaComboBox, gbc);
        gbc.gridx = 3; //Flyttar gridx med 1 så att den hamnar till höger om combobox
        tillValuta.setColumns(5); //Ändrar bredden på textfield
        centerPanel.add(tillValuta, gbc);

       /* gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        centerPanel.add(tillValuta, gbc);
        gbc.gridx = 3;
        tillText.setColumns(5);*/


        //Ändra storlek på convert knappen
        int convertwidth = 200;
        int convertheigth = 40;
        Dimension newDimensionConvertButton = new Dimension(convertwidth, convertheigth);
        convertButton.setPreferredSize(newDimensionConvertButton);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.PAGE_END;
        centerPanel.add(convertButton, gbc);


        //Bottompanel
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        bottomPanel.add(rateInformation, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        bottomPanel.add(sourceOfData, gbc);


       /* centerPanel.add(frånLabel);
        centerPanel.add(frånValutaComboBox);

        centerPanel.add(tillLabel);
        centerPanel.add(tillValutaComboBox);*/


        setTitle("The X:changer");
        setSize(400, 650);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setResizable(false);
    }

    public JButton getConvertButton() {
        return convertButton;
    }

    public JComboBox<Currencies> getFrånValutaComboBox() {
        return frånValutaComboBox;
    }

    public JComboBox<Currencies> getTillValutaComboBox() {
        return tillValutaComboBox;
    }

    public JTextField getFrånValuta() {
        return frånValuta;
    }

    public JTextField getTillValuta() {
        return tillValuta;
    }

    public void updateExchangedAmount(double amount) {
        tillValuta.setText(String.valueOf(amount));
    }
    public void updateRateInformation(Enum<Currencies> fromCurrency, Enum<Currencies> toCurrency, double rate, double reversedRate) {

        Currencies fromCurrencyEnum = (Currencies) fromCurrency;
        String fromCurrencyString = fromCurrencyEnum.fullName;

        Currencies toCurrencyEnum = (Currencies) toCurrency;
        String toCurrencyString = toCurrencyEnum.fullName;

        String rateAsString = String.valueOf(rate);
        String reversedRateAsString = String.valueOf(reversedRate);

        rateInformation.setText(fromCurrencyString + toCurrencyString + rateAsString + reversedRateAsString);

        //rateInformation = "HTML kod här"
    }

    public void updateExchangedDate(String date) {
        dateOfExchange.setText(date);
    }
}
