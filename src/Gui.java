import javax.swing.*;

public class Gui {
    //Huvudpanel
    JPanel headPanel = new JPanel();
    JPanel topPanel = new JPanel(); //Huvudpanelen för appen
    JPanel centerPanel = new JPanel();
    JPanel bottomPanel = new JPanel();

    JLabel xChangerName = new JLabel("The X:changer"); //Rubriken på appen
    //Lägg till bilden på granen här

    JLabel frånLabel = new JLabel("Från:"); //Label där det står från
    JLabel frånValuta = new JLabel(); //Label där de pressenteras vilken valuta till (lägg in värde)
    JComboBox<String> frånValutaComboBox = new JComboBox<>(); // COmbobox för från valuta
    JTextField frånText = new JTextField(); //Textfield för att kunna skriva in belopp

    JLabel tillLabel = new JLabel("Till"); //Label där det står till
    JLabel tillValuta = new JLabel(); //Label där de pressenteras vilken valuta från (lägg in värde)
    JComboBox<String> tillValutaComboBox = new JComboBox<>(); // COmbobox för till valuta

    JTextField tillText = new JTextField(); //Textfield för att kunna skriva ut det nya beloppet

    JButton swapButton = new JButton(); //Knapp för att swappa valörer (Lägg in pilar)

    JButton convertButton = new JButton("Convert"); //Knapp för att konvertera valutan

    JLabel actualCurrencySek = new JLabel("1 SEK = 0.0953 US Dollars"); //Label för att pressentera nuvarande kurs i sek till usd
    JLabel actualCurrencyUsd = new JLabel(); //Label för att pressentera nuvarande kurs i usd till sek

    JLabel sourceOfData = new JLabel("Data extracted from"); //Label för att pressentera källan av datan

}
