import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class HangMan {
    //frame
    static JFrame frame;

    //other stuff
    char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    String[] countryList = {"ENGLAND", "SCOTLAND", "WALES", "NORTHERN IRELAND", "AFGHANISTAN", "ALBANIA", "ANDORRA", "ANGOLA", "ANTIGUA AND BARBUDA", "ARGENTINA", "ARMENIA",
    "AUSTRALIA", "AUSTRIA", "AZERBAIJAN", "THE BAHAMAS", "BAHRAIN", "BANGLADESH", "BARBADOS", "BELARUS", "BELGIUM", " BELIZE",
    "BENIN", "BHUTAN", "BOLIVIA", "BOSNIA AND HERZEGOVINA", "BOTSWANA", "BRAZIL", "BRUNEI", "BULGARIA", "BURKINA FASO",
    "BURUNDI", "CABO VERDE", "CAMBODIA", "CAMEROON", "CANADA", "CENTRAL AFRICAN REPUBLIC", "CHAD", "CHILE", "CHINA",
    "COLOMBIA", "COMOROS", "DEMOCRATIC REPUBLIC OF THE CONGO", "REPUBLIC OF THE CONGO", "COSTA RICA", "IVORY COAST",
    "CROATIA", "CYPRUS", "CZECHIA", "DENMARK", "DJIBOUTI", "DOMINICA", "DOMINICAN REPUBLIC", "EAST TIMOR", "ECUADOR",
    "EGYPT", "EL SALVADOR", "EQUATORIAL GUINEA", "ERITREA", "ESTONIA", "ESWATINI", "ETHIOPIA", "FIJI", "FINLAND", "FRANCE",
    "GABON", "THE GAMBIA", "GEORGIA", "GERMANY", "GHANA", "GREECE", "GRENADA", "GUATEMALA", "GUINEA", "GUINEA BISSAU",
    "GUYANA", "HAITI", "HONDURAS", "HUNGARY", "ICELAND", "INDIA", "INDONESIA", "IRAN", "IRAQ", "IRELAND", "ISRAEL", "ITALY",
    "JAMAICA", "JAPAN", "JORDAN", "KAZAKHSTAN", "KENYA", "KIRIBATI", "NORTH KOREA", "SOUTH KOREA", "KOSOVO", "KUWAIT",
    "KYRGYZSTAN", "LAOS", "LATVIA", "LEBANON", "LESOTHO", "LIBERIA", "LIBYA", "LIECHTENSTEIN", "LITHUANIA", "LUXEMBOURG",
    "MADAGASCAR", "MALAWI", "MALAYSIA", "MALDIVES", "MALI", "MALTA", "MARSHALL ISLANDS", "MAURITANIA", "MAURITIUS", "MEXICO",
    "MICRONESIA", "MOLDOVA", "MONACO", "MONGOLIA", "MONTENEGRO", "MOROCCO", "MOZAMBIQUE", "MYANMAR", "NAMIBIA", "NAURU",
    "NEPAL", "NETHERLANDS", "NEW ZEALAND", "NICARAGUA", "NIGER", "NIGERIA", "NORTH MACEDONIA", "NORWAY", "OMAN", "PAKISTAN",
    "PALAU", "PANAMA", "PAPUA NEW GUINEA", "PARAGUAY", "PERU", "PHILIPPINES", "POLAND", "PORTUGAL", "PALESTINE", "QATAR",
    "SAINT KITTS AND NEVIS", "SAINT LUCIA", "SAINT VINCENT AND THE GRENADINES", "SAMOA", "SAN MARINO", "SAO TOME AND PRINCIPE",
    "SAUDI ARABIA", "SENEGAL", "SERBIA", "SEYCHELLES", "SIERRA LEONE", "SINGAPORE", "SLOVAKIA", "SLOVENIA", "SOLOMON ISLANDS",
    "SOMALIA", "SOUTH AFRICA", "SPAIN", "SRI LANKA", "SUDAN", "SOUTH SUDAN", "SURINAME", "SWEDEN", "SWITZERLAND", "SYRIA",
    "TAIWAN", "TAJIKISTAN", "TANZANIA", "THAILAND", "TOGO", "TONGA", "TRINIDAD AND TOBAGO", "TUNISIA", "TURKIYE", "TURKMENISTAN",
    "TUVALU", "UGANDA", "UKRAINE", "UNITED ARAB EMIRATES", "UNITED STATES", "URUGUAY", "UZBEKISTAN", "VANUATU",
    "VATICAN CITY", "VENEZUELA", "VIETNAM", "YEMEN", "ZAMBIA", "ZIMBABWE"};
    String[] JujutsuKaisenCharacters = {"YUUJI ITADORI", "SATORU GOJO", "MEGUMI FUSHIGURO", "NOBARA KUGISAKI", "YUTA OKKOTSU",
    "SUGURU GETO", "TOGE INUMAKI", "MAKI ZENIN", "KENTO NANAMI", "AOI TODO", "RYOMEN SUKUNA", "MASAMICHI YAGA", "NORITOSHI KAMO",
    "SHOKO IEIRI", "MOMO NISHIMIYA", "MAHITO", "PANDA", "MOMO NISHIMIYA", "MEI MEI", "MASTER TENGEN", "JOGO", "HANAMI", "DAGON",
    "CHOSO", "JUNPEI YOSHINO", "TOJI FUSHIGURO", "TAKADA CHAN"};
    String[] FoodList = {"HAMBURGER", "HOTDOG", "PASTA", "SPAGHETTI", "RAMEN", "RAVIOLI", "STRAWBERRY", "BANANA", "PINEAPPLE",
    "SOUP", "CHICKEN TERIYAKI", "SALAD", "STEAK", "FRENCH FRIES", "STUFFED PEPPERS", "LASAGNA", "SANDWICH", "NACHOS", "TACO",
    "BURRITO", "SALTINE CRACKERS", "TOMATOS", "ONIONS", "BREAKFAST SANDWICH"};
    Random rand = new Random();
    String word = countryList[rand.nextInt(countryList.length)];
    //String word = "MARSHALL ISLANDS";
    JTextField userInputField;

    //Jbuttons
    JButton enterButton;
    JButton countryButton;
    JButton jjkButton;
    JButton foodButton;

    //ints
    int loopCounter;
    int loopCounter2;
    int wordLength = word.length();
    int guessesLeft = 5;
    int spacesCount;
    int answerLettersOnScreen;

    //JLabels
    JButton alphabetLabel;
    JLabel l;
    JLabel numberOfGuesses;

    //JPanels
    JPanel answerPanel;
    JPanel alphabetPanel;
    JPanel hangmanPanel;
    JPanel categoryPanel;

    //lists
    ArrayList<String> guessList = new ArrayList<>();
    JLabel[] answerLabels = new JLabel[wordLength];
    JButton[] alphabetLabels = new JButton[alphabet.length];
    ArrayList<JButton> restartButtonList = new ArrayList<>();
    String[][] catListList = {countryList, JujutsuKaisenCharacters, FoodList};

    HangMan(){
        //create frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        //create layout
        BorderLayout layout = new BorderLayout();
        frame.setLayout(layout);

        //create answer panel
        answerPanel = new JPanel();
        frame.add(answerPanel, BorderLayout.NORTH);

        //create alphabet panel
        alphabetPanel = new JPanel();
        frame.add(alphabetPanel, BorderLayout.SOUTH);

        //create hangman panel
        hangmanPanel = new JPanel();
        frame.add(hangmanPanel, BorderLayout.CENTER);
        numberOfGuesses = new JLabel();
        hangmanPanel.add(numberOfGuesses);

        //create category panel
        categoryPanel = new JPanel(new GridLayout(3, 0));
        frame.add(categoryPanel, BorderLayout.EAST);

        //enter button
        enterButton = new JButton("ENTER");

    }
    public static void main(String[] args) {
        TextField one = new TextField();
        one.CreateLabelBoxes();
        one.UserTextInput();
        one.CreateAlphabetPanel();
        one.CreateHangmanPanel();
        one.CreateRestartButton();
        one.RestartButton();
        one.LetterButtons();
        one.addActionToEnterButton();
        TextField.setVisible();
    }

}



