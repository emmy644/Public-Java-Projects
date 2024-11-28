import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TextField extends HangMan{

    void addActionToEnterButton(){
        //override for when you press the enter button to check if the letter is there
        enterButton.addActionListener(_ -> {
            String userText = userInputField.getText();
            if (userText.length() == 1 && !guessList.contains(userText)){
                RemoveFromAlphabetButton(userText);

                if (word.contains(userText.toUpperCase())){
                    //add to answer list
                    //for loop to print out letters in box
                    printOutCorrectLetter(userText);

                }
                else {
                    //lowers the guesses left
                    LowersGuessesLeft(userText);
                }

                SpacesCountAndWinMessage();
            }
            AddToGuessList(userText);
        });
    }

    void AddToGuessList(String userInput){
        if (!guessList.contains(userInput) && String.valueOf(alphabet).contains(userInput.toUpperCase())) {
            guessList.add(userInput);
        }
    }

    void SpacesCountAndWinMessage() {
        if (guessesLeft > 0) {
            spacesCount = 0;
            for (int r = 0; r < wordLength; r++) {
                if (word.charAt(r) == ' ') {
                    spacesCount++;
                }
            }
            System.out.println("answer letters on screen: " + answerLettersOnScreen);
            System.out.println("wordlength: " + wordLength);
            System.out.println("spaces: " + spacesCount);
            if (word.contains(" ")) {
                if (answerLettersOnScreen + spacesCount == wordLength) {
                    numberOfGuesses.setText("YOU WIN");
                }
            } else {
                if (answerLettersOnScreen == wordLength) {
                    numberOfGuesses.setText("YOU WIN");
                }
            }
        }
    }

    void RemoveFromAlphabetButton(String inputText){
        loopCounter2 = 0;
        for (char d: alphabet) {
            loopCounter2++;
            if (inputText.toUpperCase().equals(Character.toString(d))) {
                alphabetLabels[loopCounter2 - 1].setText(" ");
            }
        }
    }

    void printOutCorrectLetter(String userInput){
        loopCounter = 0;
        for (char c: word.toCharArray()){
            loopCounter++;
            System.out.println("userinput: " + userInput + ", c: " + c);
            if (userInput.toUpperCase().equals(Character.toString(c))){
                answerLabels[loopCounter-1].setText(Character.toString(word.charAt(loopCounter-1)));
                System.out.println("adding answer letters on screen");
                answerLettersOnScreen ++;

            }
        }
    }

    void LowersGuessesLeft(String userInput) {
        if (guessesLeft > 0) {
            if (String.valueOf(alphabet).contains(userInput.toUpperCase())) {
                guessesLeft = guessesLeft - 1;
                numberOfGuesses.setText("GUESSES LEFT: " + guessesLeft);
            }
            if (guessesLeft == 0) {
                for (int p = 0; p < wordLength; p++) {
                    answerLabels[p].setText(Character.toString(word.charAt(p)));
                    numberOfGuesses.setText("BETTER LUCK NEXT TIME");
                }
            }
        }
    }

    void CreateLabelBoxes(){
        //for loop to set up the boxes for the answer letters
        for (int i = 0; i < word.length(); i++){
            l = new JLabel();
            if (word.length() > 18) {
                l.setPreferredSize(new Dimension(36, 36));
            }
            else {
                l.setPreferredSize(new Dimension(50, 50));
            }
            l.setFont(new Font("Serif", Font.PLAIN, 40));
            if (word.charAt(i) != ' '){
                l.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
            }
            answerPanel.add(l);

            answerLabels[i] = l;
        }
    }

    void UserTextInput(){
        //adds user input box and enter button
        userInputField = new JTextField(5);
        answerPanel.add(userInputField);
        answerPanel.add(enterButton);

    }

    static void setVisible(){
        frame.setVisible(true);
    }

    void CreateAlphabetPanel(){
        for (int k = 0; k < 26; k++){
            alphabetLabel = new JButton();

            alphabetLabel.setPreferredSize(new Dimension(40, 40));
            alphabetLabel.setFont(new Font("Serif", Font.PLAIN, 30));
            alphabetLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
            alphabetLabel.setText(Character.toString(alphabet[k]));

            alphabetPanel.add(alphabetLabel);

            alphabetLabels[k] = alphabetLabel;
        }
    }

    void CreateHangmanPanel(){
        numberOfGuesses.setFont(new Font("Serif", Font.BOLD, 50));
        numberOfGuesses.setText("GUESSES LEFT: " + guessesLeft);

    }

    void CreateRestartButton(){
        countryButton = new JButton("COUNTRY");
        jjkButton = new JButton("JJK");
        foodButton = new JButton("FOOD");
        categoryPanel.add(countryButton);
        categoryPanel.add(jjkButton);
        categoryPanel.add(foodButton);
        restartButtonList.add(countryButton);
        restartButtonList.add(jjkButton);
        restartButtonList.add(foodButton);
    }

    void RestartButton() {
        for (JButton b: restartButtonList) {
            b.addActionListener(_ -> {

                if (b == countryButton) {
                    word = countryList[rand.nextInt(countryList.length)];
                }
                else if (b == jjkButton) {
                    word = JujutsuKaisenCharacters[rand.nextInt(JujutsuKaisenCharacters.length)];
                }
                else {
                    word = FoodList[rand.nextInt(FoodList.length)];
                }

                wordLength = word.length();
                //clear previous screen
                for (JLabel aLabel : answerLabels) {
                    answerPanel.remove(aLabel);
                }
                numberOfGuesses.setText(null);
                answerPanel.remove(enterButton);
                answerPanel.remove(userInputField);

                for (JButton alLabel : alphabetLabels) {
                    alphabetPanel.remove(alLabel);
                }

                //new lists
                guessList.clear();
                answerLabels = new JLabel[wordLength];

                //restart variables
                guessesLeft = 5;
                answerLettersOnScreen = 0;

                CreateLabelBoxes();
                UserTextInput();
                CreateAlphabetPanel();
                CreateHangmanPanel();
                LetterButtons();
                setVisible();
            });
        }
    }

    void LetterButtons(){
        for (JButton aButton : alphabetLabels){
            aButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String letter = aButton.getText();
                    if (!guessList.contains(letter) && String.valueOf(alphabet).contains(letter)) {
                        if (word.contains(letter)) {
                            printOutCorrectLetter(letter);
                        } else {
                            LowersGuessesLeft(letter);
                        }
                        RemoveFromAlphabetButton(letter);
                        SpacesCountAndWinMessage();
                    }
                    AddToGuessList(letter);
                }
            });
        }
    }

}