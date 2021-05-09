package Model;
import java.util.Random;

/**
 * <b>BlueCard</b> is the class that contains the needed attributes
 * and methods to implement the behavior of a blue card in the
 * Thats Life! game. <b>BlueCard</b> is a child class of <b>ValueCard</b>
 *
 * @author      Joshue Salvador A. Jadie
 * @author      Andre Dominic H. Ponce
 * @version     %I%, %G%
 * @see         Card
 * @see         ValueCard
 */
public class BlueCard extends ValueCard
{
    /**
     * The career assigned to the specified blue card.
     */
    private String career;
    /**
     * A list of possible actions for blue cards.
     */
    public static final String[] ACTIONS = {
        "Lawsuit", "Salary Tax Due", "Computer Repair", "Ski Accident",
        "Tip The Server", "F1 Race", "World Cup"
    };

    /**
     * The sole constructor for this class. Creates a blue card with
     * the specified action name to be assigned to its super class and
     * the specified career to it.
     *
     * @param name      the name (action) of the blue card
     * @param career    the specified career assigned to the blue card
     */
    public BlueCard (String name, String career)
    {
        super (name);
        this.career = career;
        setActionValue ();
        setAmount ();
    }

    /**
     * Automatically generates the description of a blue card
     * based on its action name.
     */
    @Override
    protected void generateDescription ()
    {
        String[] descriptions = {
            "Oh no! You have a lawsuit!", "Your salary tax is now due! Pay your taxes!",
            "ERROR 404!", "You got in an accident during skiing!",
            "Be a good customer! Tip the server.", "KA-CHOW! Break a leg!",
            "GOAAAAAAAAAL! Let's bring home the bacon!"
        };

        for (int j = 0; j < ACTIONS.length; j++)
            if (ACTIONS[j].equalsIgnoreCase (name))
                description = descriptions[j];
    }

    /**
     * Sets the action value of the blue card based on its
     * action name.
     */
    protected void setActionValue ()
    {
        int[] actionVals = {1, 2, 3, 4, 5, 6, 7};

        for (int j = 0; j < ACTIONS.length; j++)
            if (ACTIONS[j].equalsIgnoreCase (name))
                actionVal = actionVals[j];
    }

    /**
     * Sets the amount of the blue card based on its action
     * name.
     */
    protected void setAmount ()
    {
        Random randomizer = new Random ();

        if (ACTIONS[0].equalsIgnoreCase (name)) // Lawsuit
            amount = 10000 * (randomizer.nextInt (11) + 5); // between 50000 to 150000
        else if (ACTIONS[4].equalsIgnoreCase (name)) // Tip The Server 
            amount = 1000;
        else if (ACTIONS[3].equalsIgnoreCase (name)) // Ski Accident
            amount = 10000;
        else if (ACTIONS[6].equalsIgnoreCase (name)) // World Cup
            amount = 5000;
        else amount = 0;
    }

    /**
     * Gets the career affiliated with this blue card.
     *
     * @return  the career affiliation of this card
     */
    public String getCareer ()
    {
        return career;
    }

    @Override
    public String toString ()
    {
        return getName () + " Career: " + getCareer () + "\n" + getDescription ();
    }
}
