public class Die {
    /** Instance Variables **/
    private int sides;

    /** Constructors **/
    public Die() {
        sides = 6;
    }

    public Die(int numSides) {
        sides = numSides;
    }

    /** Methods **/
    public int getSides() {
        return sides;
    }

    /**
     * Returns a random int between 1 and
     * the number of sides on the Die
     */
    public int roll() {
        int num = (int)(Math.random()*sides) + 1;
        return num;
    }

    /**
     * Overrides the string method to print
     * a message
     */
    public String toString() {
        return "Rolling five " + sides + " sided dice...";
    }
}
