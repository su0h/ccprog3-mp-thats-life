package Model;

/**
 * <b>ValueCard</b> is an abstract class that contains the needed attributes
 * and methods to implement the behavior of a card that contains an amount
 * and action value for cash manipulation purposes in the Thats Life! game.
 * <b>ValueCard</b> is an abstract child class of <b>Card</b>
 *
 * @author      Joshue Salvador A. Jadie
 * @author      Andre Dominic H. Ponce
 * @version     %I%, %G%
 * @see         Card
 */
public abstract class ValueCard extends Card
{
    /**
     * The amount (cash) of the card.
     */
    protected int amount;
    /**
     * The action value of the card.
     */
    protected int actionVal;

    /**
     * The sole constructor for this class. Creates a value card with
     * the specified name to be assigned to its super class.
     *
     * @param name  the value card name
     */
    public ValueCard (String name)
    {
        super (name);
    }

    /**
     * Sets the action value of the card according to action name specified.
     * Must be overridden by either <b>BlueCard</b> or <b>ActionCard</b>.
     *
     * @see BlueCard
     * @see ActionCard
     */
    protected abstract void setActionValue ();

    /**
     * Sets the amount of the card according to action name specified.
     * Must be overridden by either <b>BlueCard</b> or <b>ActionCard</b>.
     *
     * @see BlueCard
     * @see ActionCard
     */
    protected abstract void setAmount ();

    /**
     * Gets the amount of the card.
     *
     * @return  the amount of the card
     */
    public int getAmount ()
    {
        return amount;
    }

    /**
     * Gets the action value of the card.
     *
     * @return  the action value of the card
     */
    public int getActionValue ()
    {
        return actionVal;
    }
}
