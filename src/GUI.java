import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.DecimalFormat;

public class GUI extends JFrame {

    ImageIcon originalJulgranIcon = new ImageIcon("src/resources/tree.png");
    ImageIcon swapButtonIcon = new ImageIcon("src/resources/exchange.png");
    Color myColor = Color.decode("#052543");

    JPanel topPanel = new JPanel(new BorderLayout()); //Huvudpanelen för appen
    JPanel centerPanel = new JPanel();
    JPanel bottomPanel = new JPanel();

    JComboBox<Currencies> frånValutaComboBox = new JComboBox<>((Currencies.values())); // COmbobox för från valuta
    JComboBox<Currencies> tillValutaComboBox = new JComboBox<>((Currencies.values())); // COmbobox för till valuta
    JLabel xChangerName = new JLabel("<html><p style =' color: white;'><br>The <span style=color:#F3A10F>X</span>:changer</html>"); //Rubriken på appen
    JLabel tillLabel = new JLabel("<html><p style =' font-size: 12px; color: white;'><br><br><br>Till:</p>"); //Label där det står till
    JLabel frånLabel = new JLabel("<html><p style =' font-size: 12px; color: white;'><br><br><br>Från:</p>"); //Label där det står från
    JTextField frånValuta = new JTextField(); //Label där de presenterar vilken valuta till (lägg in värde)
    JTextField tillValuta = new JTextField(); //Label där de presenteras vilken valuta från (lägg in värde)

    JLabel rateInformation = new JLabel("<html><h1 style ='color: white;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; " +
            "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      </h1>"+
            "<p style='font-size:15px; color: white;'>             <br><br><br><br></p></html>"); //Label för att presentera nuvarande kurs
    JLabel sourceOfData = new JLabel("<html><p style ='color: #B4B4B4; font-size:8px;'>Data extracted from</html>"); //Label för att presentera källan av datan
    JLabel iconChristmasTree = new JLabel();
    JLabel dateOfExchange = new JLabel();
    JLabel swapButtonLabel = new JLabel();

    public GUI() {

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        topPanel.setBackground(myColor);
        centerPanel.setBackground(myColor);
        bottomPanel.setBackground(myColor);

        //default values for dropbox
        frånValutaComboBox.setSelectedItem(Currencies.SEK);
        tillValutaComboBox.setSelectedItem(Currencies.USD);

        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //xChangerName
        xChangerName.setFont(new Font("Arial", Font.BOLD, 40)); //Ändra stil på titeln
        xChangerName.setHorizontalAlignment(SwingConstants.CENTER);
        xChangerName.setVerticalAlignment(SwingConstants.CENTER);
        topPanel.add(xChangerName, BorderLayout.CENTER); //Lägger till titeln till toppanel

        //iconChristmasTree
        topPanel.add(iconChristmasTree, BorderLayout.SOUTH);
        iconChristmasTree.setIcon(originalJulgranIcon);
        iconChristmasTree.setHorizontalAlignment(SwingConstants.CENTER);

        //frånLabel, frånValutaComboBox, frånValuta
        gbc.insets = new Insets(2, 1, 5, 1); //Marginal mellan komponenter
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
        frånValuta.setColumns(8);
        centerPanel.add(frånValuta, gbc);
        frånValuta.setText("0");

        //swapButton
        gbc.gridx = 2;
        gbc.gridy = 1;
        swapButtonLabel.setPreferredSize(new Dimension(30,30));         //ändrar storlek på swapbutton
        swapButtonLabel.setIcon(swapButtonIcon);
        centerPanel.add(swapButtonLabel, gbc);

        //tillLabel, tillValutaComboBox, tillValuta
        gbc.gridx = 3;
        gbc.gridy = 0;
        centerPanel.add(tillLabel, gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        centerPanel.add(tillValutaComboBox, gbc);
        gbc.gridx = 4;
        tillValuta.setColumns(8); //Ändrar bredden på textfield
        tillValuta.setFocusable(false);
        tillValuta.setEditable(false);
        centerPanel.add(tillValuta, gbc);

        //dateOfExchange
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.insets = new Insets(1,0,0,0);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        centerPanel.add(dateOfExchange, gbc);
        dateOfExchange.setForeground(Color.WHITE);
        dateOfExchange.setBorder(null);
        dateOfExchange.setHorizontalTextPosition(SwingConstants.RIGHT);
        dateOfExchange.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        dateOfExchange.setText("        ");

        //rateInformation, sourceOfData
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(rateInformation,BorderLayout.NORTH);
        bottomPanel.add(sourceOfData,BorderLayout.SOUTH);
        sourceOfData.setHorizontalAlignment(SwingConstants.CENTER);


        setTitle("The X:changer");
        setSize(470, 650);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JLabel getSwapButtonLabel() {
        return swapButtonLabel;
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

    public void updateExchangedAmount(double amount){

        DecimalFormat df = new DecimalFormat("#.##");
        String formattedValue = df.format(amount);
        tillValuta.setText(String.valueOf(formattedValue));
    }

    public void updateExchangedDate(String date){
        dateOfExchange.setText("Updated: " + date.substring(1,(date.length()-10)));
    }

}
