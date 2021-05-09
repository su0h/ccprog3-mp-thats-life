package Model;

/**
 * <b>Card</b> is the general class for all the card types in the
 * Thats Life! game. Contains the common attributes and methods
 * for each type of card in the game.
 *
 * @author      Joshue Salvador A. Jadie
 * @author      Andre Dominic H. Ponce
 * @version     %I%, %G%
 */
public class Card
{
    /**
     * The name of the card.
     */
    protected String name;
    /**
     * The description of the card.
     */
    protected String description;

    /**
     * The sole constructor for this class. Creates a card with
     * the given name.
     *
     * @param name  the card name
     */
    public Card (String name)
    {
        this.name = name;
        generateDescription ();
    }

    /**
     * Automatically generates the description for the card.
     * <p>
     * As the general class, the method must be overriden
     * by each card type to generate an accurate description.
     */
    protected void generateDescription ()
    {
        description = "Not overriden!";
    }

    /**
     * Gets the name of this card.
     *
     * @return  the card name
     */
    public String getName ()
    {
        return name;
    }

    /**
     * Gets the description of this card.
     *
     * @return  the card description
     */
    public String getDescription ()
    {
        return description;
    }
}
