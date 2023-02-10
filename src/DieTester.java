import java.util.Scanner;
//import java.util.ArrayList;

// find somewhere to do window.repaint()
public class DieTester {

    /** INSTANCE VARIABLES **/
    private Die[] computersDice;
    private Die[] playersDice;
    private DieViewer viewer;
    private boolean isGameOver;
    private boolean hasWon;
    private boolean hasLost;
    private boolean revealComp;


    /** CONSTRUCTOR **/
    public DieTester() {
        computersDice = rollDice();
        playersDice = rollDice();
        isGameOver = false;
        hasWon = false;
        hasLost = false;
        revealComp = false;
        viewer = new DieViewer(this);
    }

    public static void main(String[] args) {
        DieTester d = new DieTester();
        d.playGame();
    }

    /** GAME **/
    public void playGame() {
        // Greet player
        printInstructions();

        // Checks to make sure player is ready, if not, repeat question
        Scanner game = new Scanner(System.in);
        String ready = "";
        do {
            System.out.println("Type ready to roll your dice:");
            ready = game.nextLine();
        }
        while (!ready.equals("ready"));

        // Rolls Player and Computer Dice
        playerRoll();
        computerRoll();
        viewer.repaint();

        // Have player make their guess, check to make sure
        // value is between 1-6
        int value = 0;
        do {
            System.out.println("Enter the value rolled 1-6: ");
            value = game.nextInt();
        }
        while (value < 1 || value > 6);
        System.out.println();

        System.out.println("Enter the number of dice that rolled this value: ");
        int number = game.nextInt();


        // Check number of dice using countValue
        int numberDice = countValue(playersDice, computersDice, value);

        // Check to see if that adds up with what the player guessed
        System.out.println();
        if (numberDice >= number) {
            System.out.println("Correct! There were at least " + number + " " + value + "s!");
            revealComp = true;
            isGameOver = true;
            hasWon = true;
            viewer.repaint();
        } else {
            System.out.println("Incorrect :( There were " + numberDice + " " + value + "s!");
            revealComp = true;
            isGameOver = true;
            hasLost = true;
            viewer.repaint();
        }
    }

    /** METHODS **/
    /**
     * Method that rolls 5 dice randomly and inputs it in
     * an array to return as their 5 rolls
     */
    public Die[] getComputersDice() {
        return computersDice;
    }

    public Die[] getPlayersDice() {
        return playersDice;
    }

    public boolean isRevealComp() {
        return revealComp;
    }
    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isHasWon() {
        return hasWon;
    }

    public boolean isHasLost() {
        return hasLost;
    }

    public Die[] rollDice() {
        Die[] hand = new Die[5];

        for (int i = 0; i < 5; i++) {
            Die d = new Die(6);
            d.roll();
            hand[i] = d;
        }
        return hand;
    }

    /**
     * Roll the player's dice by calling roll method
     * and inputting it in an array and
     * then print out the roll / array neatly
     */
    public void playerRoll() {
        System.out.println();
        System.out.print("Player's rolls: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(playersDice[i].getValue() + " ");
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Roll the computer's dice and do not print
     * the roll, then tell player next steps
     */
    public void computerRoll() {
        System.out.println("The computer has rolled." + "\n");

        // Have the player guess the max number of rolls
        System.out.println("Now, you must guess the largest number of die " +
                "that rolled a" + "\n" + "specific value." + "\n\n" +
                "ex. 7 fives would enter 5 as the value and 7 as the number");
        System.out.println();
    }

    /**
     * Prints instructions for the game
     */
    public void printInstructions() {
        System.out.println("Hi! This game requires you to roll 5 dice and" +
                    "\n" + "the computer will also roll 5 dice." + "\n\n"
                    + "Knowing only the values of your dice, you must" +
                    "\n" + "guess the maximum number of a value within the " +
                    "10 rolled dice." + "\n\n" + "Good luck!!");
        System.out.println();
    }

    /**
     * Method that checks both arrays to see how many times
     * that a specific value has popped up
     */
    public static int countValue(Die[] array1, Die[] array2, int value) {
        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (array1[i].getValue() == value) {
                array1[i].setIsChosenDie();
                count++;
            }
        }

        for (int i = 0; i < 5; i++) {
            if (array2[i].getValue() == value) {
                array2[i].setIsChosenDie();
                count++;
            }
        }
        return count;
    }
}
