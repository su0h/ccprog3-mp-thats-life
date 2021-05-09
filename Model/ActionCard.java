package Model;
import java.util.Random;

/**
 * <b>ActionCard</b> is the class that contains the needed attributes
 * and methods to implement the behavior of an action card in the
 * Thats Life! game. <b>ActionCard</b> is a child class of <b>ValueCard</b>
 *
 * @author      Joshue Salvador A. Jadie
 * @author      Andre Dominic H. Ponce
 * @version     %I%, %G%
 * @see         Card
 * @see         ValueCard
 */
public class ActionCard extends ValueCard
{
    /**
     * The value indicating the top of the action card deck.
     */
    public static int actionDeckTop = 0;
    /**
     * The amount of action cards in the deck.
     */
    public static final int ACTIONCARD_AMT = 50;
    /**
     * The ratios or percentages of possible actions of
     * an action card.
     */
    public static final double[] CARD_RATIOS = {
        0.4, 0.4, 0.1, 0.1
    };
    /**
     * List of all possible actions of an action card.
     */
    public static final String[][] ACTIONS = {
        {"Tax refund!", "Sell an item!", "Bonus payday!", "Setup a school!","Write a book!"},
        {"Buy an item!", "Visit a place!", "Let's go Hiking!", "Watch a show!", "Win a competition!", "Traffic Violation"},
        {"You received a Lawsuit!", "Christmas Bonus!"},
        {"File a Lawsuit!", "Happy Birthday!"}
    };

    /**
     * The sole constructor for this class. Creates an action card
     * with the specified action name to be assigned to its super class.
     *
     * @param name  the name of the action card
     */
    public ActionCard (String name)
    {
        super (name);
        setActionValue ();
        setAmount ();
    }

    /**
     * Automatically generates a description for the action card
     * based on its action name.
     */
    @Override
    protected void generateDescription ()
    {
        String[] descriptions = {
            "Collect from bank!", "Pay the bank!", "Pay a player!", "Collect from player!",
            "Pay all players!", "Collect from all players!"
        };

        for (int j = 0; j < ACTIONS.length; j++)
            for (int k = 0; k < ACTIONS[j].length; k++)
                if (ACTIONS[j][k].equalsIgnoreCase (name)) {
                    if (j > 1 && k > 0)
                        description = descriptions[j + 2];
                    else description = descriptions[j];
                }
    }

    /**
     * Sets the action value for the action card based on its
     * action name.
     */
    protected void setActionValue ()
    {
        int[] actionVals = {1, 2, 3, 4, 5, 6};

        for (int j = 0; j < ACTIONS.length; j++)
            for (int k = 0; k < ACTIONS[j].length; k++)
                if (ACTIONS[j][k].equalsIgnoreCase (name)) {
                    if (j > 1 && k > 0)
                        actionVal =  actionVals[j + 2];
                    else actionVal =  actionVals[j];
                }
    }

    /**
     * Sets the amount of the action card based on its action
     * name.
     */
    protected void setAmount ()
    {
        Random randomizer = new Random ();
        int num = randomizer.nextInt (10) + 1;

        /* 30% CHANCE FOR $20,000 */
        if (num <= 3)
            amount = 20000;
        /* 40% CHANCE FOR $50,000 */
        else if (num >= 4 && num <= 7)
            amount = 50000;
        /* 20% CHANCE FOR $75,000 */
        else if (num >= 8 && num <= 9)
            amount = 75000;
        /* 10% CHANCE FOR $100,000 */
        else amount = 100000;
    }

    @Override
    public String toString ()
    {
        return getName () + "\n" + getDescription () + " - $" + getAmount () + " Action Value: " + getActionValue ();
    }
}
