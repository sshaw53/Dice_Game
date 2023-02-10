import javax.swing.*;
import java.awt.*;

public class DieViewer extends JFrame {
    private Image[] diceImages;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    private DieTester tester;
    private Image board, hand;
    private Die[] computersDice;
    private Die[] playersDice;

    public DieViewer(DieTester tester) {
        // Instantiating Images
        diceImages = new Image[6];
        diceImages[0] = new ImageIcon("Resources/1.png").getImage();
        diceImages[1] = new ImageIcon("Resources/2.png").getImage();
        diceImages[2] = new ImageIcon("Resources/3.png").getImage();
        diceImages[3] = new ImageIcon("Resources/4.png").getImage();
        diceImages[4] = new ImageIcon("Resources/5.png").getImage();
        diceImages[5] = new ImageIcon("Resources/6.png").getImage();
        board = new ImageIcon("Resources/board.png").getImage();
        hand = new ImageIcon("Resources/hand.png").getImage();

        this.tester = tester;
        computersDice = tester.getComputersDice();
        playersDice = tester.getPlayersDice();

        // Setting up the window
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Dice Game");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public Image[] getImages() { return diceImages; }

    public void paint(Graphics g) {
        // draw setup
        g.drawImage(board,0, 0, 1000, 800, this);

        // draw hand over the cards until unveils after counter
        if (!tester.isRevealComp()) {
            for (int i = 0; i < 5; i++) {
                playersDice[i].draw(g, i, 1, this);
            }
            g.drawImage(hand,0, 50, 1000, 200, this);
        }

        // draw dice - for loop through the items in players dice and computers dice arrays
        // (once revealed to show computer's rolls)
        if (tester.isRevealComp()) {
            for (int i = 0; i < 5; i++) {
                playersDice[i].draw(g, i, 1, this);
                computersDice[i].draw(g, i, 2, this);
            }
        }

        // display winning method
        g.setColor(Color.PINK);
        g.setFont(new Font("Serif", Font.PLAIN, 100));
        if (tester.isGameOver()) {
            if (tester.isHasWon()) {
                g.drawString("YOU WIN!", 280, 400);
            }
            if (tester.isHasLost()) {
                g.drawString("YOU LOSE :(", 260, 400);
            }
        }
    }
}