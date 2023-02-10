import java.awt.*;

public class Die {
    /** Instance Variables **/
    private int sides;
    private Image[] number;
    private int num;
    private boolean isChosenDie;

    /** Constructors **/
    public Die() {
        sides = 6;
    }

    public Die(int numSides) {
        sides = numSides;
        isChosenDie = false;
    }

    /** Methods **/
    public int getSides() {
        return sides;
    }

    public int getValue() {
        return num;
    }

    /**
     * Returns a random int between 1 and
     * the number of sides on the Die
     */
    public void roll() {
        num = (int)(Math.random()*sides) + 1;
    }

    /**
     * Overrides the string method to print
     * a message
     */
    public String toString() {
        return "Rolling five " + sides + " sided dice...";
    }

    public void setIsChosenDie() {
        isChosenDie = true;
    }

    public void draw(Graphics g, int index, int player, DieViewer viewer) {
        // draw die image at location
        number = new Image[6];
        number = viewer.getImages();

        int xLoc = 50 + 185 * index;
        int yLoc = 0;
        // player
        if (player == 1)
            yLoc = 600;
        // computer
        else if (player == 2)
            yLoc = 60;
        if (isChosenDie) {
            // draw background around the dice
            g.setColor(Color.PINK);
            g.fillRoundRect(xLoc - 5, yLoc - 5, 160, 160, 30, 30);
        }
        g.drawImage(number[num - 1],xLoc, yLoc, 150, 150, viewer);
    }
}
