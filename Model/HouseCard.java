package Model;
import java.util.Random;

/**
 * <b>HouseCard</b> is the class that contains the needed attributes
 * and methods to implement the behavior of a house card in the
 * Thats Life! game. <b>HouseCard</b> is a child class of <b>PlayerCard</b>
 *
 * @author      Joshue Salvador A. Jadie
 * @author      Andre Dominic H. Ponce
 * @version     %I%, %G%
 * @see         Card
 * @see         PlayerCard
 */
public class HouseCard extends PlayerCard
{
    /**
     * The price of this house card.
     */
    private int price;
    /**
     * List of all possible names for a house card.
     */
    public static final String[] NAMES = {
        "Great Mansion", "Paradise House", "The Mountain", "The Willows", "Ivy Cottage",
        "Mill House", "The Bungalow", "The Granary", "The Laurels"
    };

    /**
     * The sole constructor for this class. Creates a house card
     * with the specified house name to be assigned to its super
     * class.
     *
     * @param name  the house name
     */
    public HouseCard (String name)
    {
        super (name);
        setPrice ();
    }

    /**
     * Automatically generates a random description for the house card created.
     */
    @Override
    protected void generateDescription ()
    {
        Random randomizer = new Random ();
        String[] descriptions = {
            "Be at home!", "Be free as a bird!", "Enjoy the wonders of the world!",
            "Ahhh.. peace and quiet.", "Life at its fullest!", "There is no place like home!",
            "Home is where the heart is!", "Home sweet home!", "I'm home, honey!", "This is life!"
        };

        description = descriptions[randomizer.nextInt (10)];
    }

    /**
     * Sets the price for the house card created.
     */
    private void setPrice ()
    {
        int[] prices = {
            130000, 150000, 170000, 200000, 300000, 450000, 470000, 500000, 550000
        };

        for (int j = 0; j < NAMES.length; j++)
            if (NAMES[j].equalsIgnoreCase (name))
                price = prices[j];
    }

    /**
     * Gets the price of this house card.
     *
     * @return  the price of the house card
     */
    public int getPrice ()
    {
        return price;
    }

    @Override
    public String toString ()
    {
        return getName () + " Price: " + getPrice () + "\n" + getDescription ();
    }
}
