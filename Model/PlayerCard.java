package Model;

/**
 * <b>PlayerCard</b> is an abstract class that contains the needed attributes
 * and methods to implement the behavior o a card that can be owned by a player
 * in the Thats Life! game. <b>PlayerCard</b> is an abstract child class of
 * <b>Card</b>
 *
 * @author      Joshue Salvador A. Jadie
 * @author      Andre Dominic H. Ponce
 * @version     %I%, %G%
 * @see         Card
 */
public abstract class PlayerCard extends Card
{
    /**
     * Indicates whether the card is owned by the player or not.
     */
    protected boolean owned;

    /**
     * The sole constructor for this abstract class. Creates a
     * player card with the specified name to be assigned to
     * its super class.
     *
     * @param name  the player card name
     */
    public PlayerCard (String name)
    {
        super (name);
        owned = false; // true if a player owns (aggregates) the card
    }

    /**
     * Sets the card ownership status to either true or false.
     */
    public void setOwned ()
    {
        owned = !owned; // vice versa
    }

    /**
     * Gets the boolean value indicating if the card is owned or not.
     *
     * @return  <code>true</code> if this card is owned; <code>false</code>
     *          otherwise
     */
    public boolean isOwned ()
    {
        return owned;
    }
}
