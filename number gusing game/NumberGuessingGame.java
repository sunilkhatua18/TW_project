import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {

    private Random random = new Random();
    private int minRange = 1;
    private int maxRange = 100;
    private int maxAttempts = 10;
    private int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
    private int attempts = 0;

    private JLabel titleLabel = new JLabel("Number Guessing Game");
    private JLabel instructionLabel = new JLabel("Guess the number between " + minRange + " and " + maxRange + ".");
    private JLabel attemptsLabel = new JLabel("Attempts remaining: " + (maxAttempts - attempts));
    private JTextField guessField = new JTextField(10);
    private JButton guessButton = new JButton("Guess");
    private JTextArea resultArea = new JTextArea(5, 30);

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                takeGuess();
            }
        });

        add(titleLabel);
        add(instructionLabel);
        add(attemptsLabel);
        add(guessField);
        add(guessButton);
        add(resultArea);

        pack();
        setLocationRelativeTo(null); // Center the window
    }

     public void takeGuess() {
        if (attempts >= maxAttempts) {
            resultArea.append("Game over. You've reached the maximum number of attempts.\n");
            resultArea.append("The correct number was: " + randomNumber + "\n");
            guessField.setEnabled(false);
            guessButton.setEnabled(false);
        } else {
            int guess;
            try {
                guess = Integer.parseInt(guessField.getText());
            } catch (NumberFormatException e) {
                resultArea.append("Invalid input. Please enter a valid number.\n");
                return;
            }

            attempts++;

            if (guess == randomNumber) {
                resultArea.append("Congratulations! You guessed the correct number.\n");
                guessField.setEnabled(false);
                guessButton.setEnabled(false);
            } else if (guess < randomNumber) {
                resultArea.append("Attempt #" + attempts + ": Your guess is too low.\n");
                attemptsLabel.setText("Attempts remaining: " + (maxAttempts - attempts));
            } else {
                resultArea.append("Attempt #" + attempts + ": Your guess is too high.\n");
                attemptsLabel.setText("Attempts remaining: " + (maxAttempts - attempts));
            }
        }
        guessField.setText("");
    }

     public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGuessingGame().setVisible(true);
            }
        });
    }
}