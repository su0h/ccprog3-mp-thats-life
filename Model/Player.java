package Model;

/**
 * <b>Player</b> is the class that contains the attributes and methods to
 * implement the behavior of a player in the Thats Life! game.
 * <p>
 * Implements the methods the execute the actions that a player must do in
 * the game.
 *
 * @author      Joshue Salvador A. Jadie
 * @author      Andre Dominic H. Ponce
 * @version     %I%, %G%
 */
public class Player
{
    /**
     * the name of this player in game
     */
    private String name;
    /**
     * the current cash value held by this player in game
     */
    private int cash;
    /**
     * the current space number (position) of this player on board
     */
    private int spaceNum;
    /**
     * the current loan balance of this player in game
     */
    private int loanBalance;
    /**
     * the status of this player in game (other necessary information)
     */
    private Status status;

    /**
     * Creates a player with the given name and initializes other starting
     * stats of the player in game.
     *
     * @param name  the player name
     */
    public Player (String name)
    {
        this.name = name;
        cash = 200000;
        spaceNum = 0; // not yet on the game board as start of the game
        loanBalance = 0;
        status = new Status ();
    }

    /**
     * Moves this player in the board by incrementing the space number (position).
     */
    public void move ()
    {
        switch (spaceNum) {
        case 15: move (32); break;
        case 72: move (86); break;
        case 123: move (140); break;
        default: spaceNum++;
        }
    }

    /**
     * Moves the position of this player to a specific position by the given
     * space number.
     *
     * @param spaceNum  the space number that the player will move to
     */
    public void move (int spaceNum)
    {
        this.spaceNum = spaceNum;
    }

    /**
     * Adds an amount to the current cash value of this player.
     *
     * @param amt   the amount that will be added to the current cash
     */
    public void addCash (int amt)
    {
        cash += amt;
    }

    /**
     * Pays (decreases) the current cash value of this player by the given amount.
     *
     * @param amt   the amount to be paid by the player
     * @return      <code>true</code> if player had enough money to pay the
     *              the given amount; <code>false</code> if player borrowed
     *              money from the bank to pay the amount.
     */
    public boolean payCash (int amt)
    {
        boolean temp = isEnoughMoney (amt);

        if (!temp)
            while (!isEnoughMoney (amt))
                borrowLoan ();

        cash -= amt;

        return temp;
    }

    /**
     * Checks if this player has enough money to pay the given amount.
     *
     * @param amt   the amount to be paid
     * @return      <code>true</code> if the player has enough money
     *              to pay the amount; <code>false</code> otherwise.
     */
    private boolean isEnoughMoney (int amt)
    {
        return cash - amt >= 0;
    }

    /**
     * Pay day action of the Thats Life! game. Adds the current salary
     * value of this player to the current cash value.
     */
    public void payDay ()
    {
        addCash (status.getCurrentSalary ());
    }

    /**
     * Borrows money from the bank to increase the current cash value
     * together with the loan balance value.
     */
    public void borrowLoan ()
    {
        cash += 20000;
        loanBalance += 25000;
    }

    /**
     * Decreases the loan balance value of this player by paying (decreasing)
     * with the current cash value.
     *
     * @return  <code>true</code> if the player had enough money to pay the
     *          loan balance; <code>false</code> if the player does not have
     *          enough to pay the loan.
     */
    public boolean payLoan ()
    {
        if (loanBalance != 0 && isEnoughMoney (25000)) {
            cash -= 25000;
            loanBalance -= 25000;
            return true;
        }

        return false;
    }

    /**
     * Gets the name of this player.
     *
     * @return  the player name
     */
    public String getName ()
    {
        return name;
    }

    /**
     * Gets the current cash value of this player.
     *
     * @return  the current cash value
     */
    public int getCash ()
    {
        return cash;
    }

    /**
     * Gets the current position of the player on the board (space number or index).
     *
     * @return  the index of the current space on board
     */
    public int getSpaceNum ()
    {
        return spaceNum;
    }

    /**
     * Gets the current loan balance value of this player.
     *
     * @return  the current loan balance
     */
    public int getLoanBalance ()
    {
        return loanBalance;
    }

    /**
     * Gets the <b>Status</b> object containing the other necessary
     * information of the player in game.
     *
     * @return  the <b>Status</b> object containing the other information
     *          of the player.
     * @see     Status 
     */
    public Status getStatus ()
    {
        return status;
    }

    @Override
    public String toString ()
    {
        String temp = "";

        temp += name + "'s life :\n";
        temp += "Cash owned: $" + cash + "\n\n";
        temp += "Current position on board: " + spaceNum + "\n\n";
        temp += "Current loan balance: $" + loanBalance + "\n";

        return temp += status.toString ();
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        return ((Player) obj).getName ().equalsIgnoreCase (this.getName ());
    }
}
