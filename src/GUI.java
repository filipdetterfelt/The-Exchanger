import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    public GUI(PresentationView view) {

        //Huvudpanel
        JPanel headPanel = new JPanel();
        JPanel topPanel = new JPanel(); //Huvudpanelen för appen
        JPanel centerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        JLabel xChangerName = new JLabel("The X:changer"); //Rubriken på appen
        //Lägg till bilden på granen här

        JLabel frånLabel = new JLabel("Från:"); //Label där det står från
        JLabel frånValuta = new JLabel(); //Label där de pressenteras vilken valuta till (lägg in värde)
        JComboBox<Currencies> frånValutaComboBox = new JComboBox<>(); // COmbobox för från valuta

        JTextField frånText = new JTextField(); //Textfield för att kunna skriva in belopp

        JLabel tillLabel = new JLabel("Till:"); //Label där det står till
        JLabel tillValuta = new JLabel(); //Label där de pressenteras vilken valuta från (lägg in värde)
        JComboBox<Currencies> tillValutaComboBox = new JComboBox<>(); // COmbobox för till valuta

        JTextField tillText = new JTextField(); //Textfield för att kunna skriva ut det nya beloppet

        JButton swapButton = new JButton(); //Knapp för att swappa valörer (Lägg in pilar)

        JButton convertButton = new JButton("Convert"); //Knapp för att konvertera valutan


        JLabel actualCurrencySek = new JLabel("1 SEK = 0.0953 US Dollars"); //Label för att pressentera nuvarande kurs i sek till usd
        JLabel actualCurrencyUsd = new JLabel(); //Label för att pressentera nuvarande kurs i usd till sek

        JLabel sourceOfData = new JLabel("Data extracted from"); //Label för att pressentera källan av datan

            setLayout(new BorderLayout());
            add(headPanel);
            add(topPanel, BorderLayout.NORTH);
            add(centerPanel, BorderLayout.CENTER);
            add(bottomPanel, BorderLayout.SOUTH);

            //Style
            xChangerName.setFont(new Font("Arial", Font.BOLD, 40)); //Ändra stil på titeln

            headPanel.setLayout(new BorderLayout());
            topPanel.add(xChangerName); //Lägger till titeln till toppanel

            centerPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.insets = new Insets(2, 5, 5, 5); //Marginal mellan komponenter

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
            frånText.setColumns(5);
            centerPanel.add(frånText, gbc);


            //Lägger till tillLabel och tillValutaComboBox i centrum

            gbc.gridx = 2; //Öka gridX för att flytta den mer åt höger
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.LINE_END;
            centerPanel.add(tillLabel, gbc);

            gbc.gridx = 2;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.LINE_END;
            centerPanel.add(tillValutaComboBox, gbc);
            gbc.gridx = 3; //Flyttar gridx med 1 så att den hamnar till höger om combobox
            tillText.setColumns(5); //Ändrar bredden på textfield
            centerPanel.add(tillText, gbc);


            //Ändra storlek på convert knappen
            int convertwidth = 200;
            int convertheigth = 40;
            Dimension newDimensionConvertButton = new Dimension(convertwidth, convertheigth);
            convertButton.setPreferredSize(newDimensionConvertButton);

            gbc.gridx = 2;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.LINE_END;
            centerPanel.add(convertButton, gbc);

            ImageIcon originalJulgranIcon = new ImageIcon("C:\\Users\\filli\\IdeaProjects\\JframeTesting" +
                    "\\src\\png-clipart-christmas-tree-christmas-tree-holidays-decor - kopia.png");
            Image originialImage = originalJulgranIcon.getImage();
            //Ändra storlek för bilden
            int imagewidth = 150;
            int imageHeigth = 200;
            Image scaledJulgran = originialImage.getScaledInstance(imagewidth, imageHeigth, Image.SCALE_SMOOTH);
            ImageIcon nyaJulgran = new ImageIcon(scaledJulgran);
            JLabel julgranLabel = new JLabel(nyaJulgran);
            topPanel.add(julgranLabel);

            //Bottompanel
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            bottomPanel.add(actualCurrencySek, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            bottomPanel.add(sourceOfData, gbc);


       /* centerPanel.add(frånLabel);
        centerPanel.add(frånValutaComboBox);

        centerPanel.add(tillLabel);
        centerPanel.add(tillValutaComboBox);*/


            //Lägg till bilden på granen här
            setTitle("The X:changer");
            setSize(400, 650);
            Color myColor = Color.decode("#6c25be");
            topPanel.setBackground(myColor);
            centerPanel.setBackground(myColor);
            bottomPanel.setBackground(myColor);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
            setResizable(false);

        }
    public JButton getConvertButton() {
        return convertButton;
    }

    }

    /*public static void main(String[] args) {
        Gui g = new Gui();
        g.drawXchangePanel();
    }*/

