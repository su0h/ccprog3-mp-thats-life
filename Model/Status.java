package Model;

/**
 * <b>Status</b> holds all the additional information of a player.
 * <p>
 * Also referred to as "Other Player Information", this contains all the other
 * player info aside from the basic ones such as cash and loan. Specifically,
 * this class contains the Career Card, Salary Card, House Card, Degree Status,
 * Marriage Status, Retirement Status, Current Salary, Current Tax, Pay Raise Amount,
 * and Family Peg Amount of a player.
 *
 * @author  Joshue Salvador A. Jadie
 * @author  Andre Dominic H. Ponce
 * @version %I%, %G%
 */
public class Status
{
    /**
     * The Career Card that a player possesses.
     */
    private CareerCard careerCard;
    /**
     * The Salary Card that a player possesses.
     */
    private SalaryCard salaryCard;
    /**
     * The House Card that a player possesses.
     */
    private HouseCard houseCard;
    /**
     * College degree status of a player.
     */
    private boolean collegeDegree;
    /**
     * Marriage status of a player.
     */
    private boolean married;
    /**
     * Retirement status of a player.
     */
    private boolean retired;
    /**
     * Current salary of a player, based on their Salary Card.
     */
    private int curSalary;
    /**
     * Current tax of a player, based on their Salary Card.
     */
    private int curTax;
    /**
     * The number of pay raises a player has done already.
     */
    private int payRaises;
    /**
     * The number of family members a player currently has.
     */
    private int family;
    /**
     * The maximum number of family members each player can have.
     */
    public static final int MAX_PEGS = 4;

    /**
     * The sole constructor of this class. Allocates a new Status
     * object (creates a new Status).
     */
    public Status ()
    {
        careerCard = null;
        salaryCard = null;
        houseCard = null;
        collegeDegree = married = retired = false;
        curSalary = curTax = payRaises = family = 0;
    }

    /**
     * Increases a player's salary based on his available pay raise amount, current
     * pay raise amount, and Salary Card attributes. This also checks if a player
     * can have a pay raise.
     *
     * @return  <code>true</code> if the player's salary has been successfully
     *          increased; <code>false</code> otherise
     */
    public boolean payRaise ()
    {
        if (careerCard == null || salaryCard == null)
            return false;

        if (!(payRaises == careerCard.getMaxPayRaise ())) {
            curSalary += salaryCard.getPayRaise ();
            curTax += 2000;
            payRaises += 1;
            return true;
        } else return false;
    }

    /**
     * Gives the player a degree. With a degree, a player will be able to take
     * careers that requires it.
     */
    public void graduate ()
    {
        collegeDegree = true;
    }

    /**
     * Makes the player married. By being married, a player will be able to have
     * children in the game. This also checks if a player is already married, as
     * there may be multiple spaces for marriage in the game.
     *
     * @return  <code>true</code> if the player married successfully; <code>false
     *          </code> otherwise.
     */
    public boolean marry ()
    {
        if (!married) {
            married = true;
            family++; // to have a spouse
            return true;
        }

        return false; // player is already married
    }

    /**
     * Makes the player retired. By being retired, the player will not be given a
     * turn in the rotation. This is used at the end of the game when the player
     * reaches the retirement tile.
     */
    public void retire ()
    {
        retired = true;
    }

    /**
     * Adds children under the player. This also checks if the player already has
     * the maximum amount of children possible.
     *
     * @param   amt the amount of family pegs to be added
     * @return      <code>true</code> if the children were successfully added under the
     *              player; <code>false</code> otherwise.
     */
    public boolean addFamily (int amt)
    {
        /* check if children to be family does not surpass the family limit and that player is married */
        boolean valid = (family - 1) + amt <= MAX_PEGS && married;

        if (valid)
            family += amt;

        return valid;
    }

    /**
     * Assigns a Career Card to the player. This also checks if the player already
     * has a degree, for some careers requires it. If a player has an existing
     * Career Card, this will disown it so that it can be reused in the game. If
     * the player meets the requirements for the career, this will assign the Career
     * Card to the player. Once a Career Card is assigned to a player, this will
     * set is as owned so that no other player can acquire it anymore.
     *
     * @param   card  the career card to be assigned
     * @return  <code>true</code> if the Career Card was assigned successfully;
     *          <code>false</code> otherwise
     */
    public boolean setCareerCard (CareerCard card)
    {
        if (card.isDegreeRequired () && !collegeDegree)
			return false;

        if (careerCard != null)
		 	careerCard.setOwned ();

        careerCard = card;
        careerCard.setOwned ();
	    payRaises = 0; // set times of pay raise to 0

	    return true;
    }

    /**
     * Assigns a Salary Card to the player. A Salary Card will be successfully
     * assigned to a player once they meet the requirements for the Career Card
     * that they have drawn. This will also set the Salary Card as owned so that
     * no other players can acquire this card. If the player has an existing
     * Salary Card, this will disown it so that it can be reused in the game.
     *
     * @param card  the salary card to be assigned
     */
    public void setSalaryCard (SalaryCard card)
    {
      if (salaryCard != null)
        salaryCard.setOwned ();

      salaryCard = card;
      salaryCard.setOwned ();

      curSalary = salaryCard.getSalary ();
      curTax = salaryCard.getTax ();
    }

    /**
     * Assigns a House Card to the player. This will also set the House Card as
     * owned so that no other player can acquire it anymore.
     *
     * @param card  the house card to be assigned
     */
    public void setHouseCard (HouseCard card)
    {
        if (getHouseCard () != null)
            houseCard.setOwned ();

        houseCard = card;
    }

    /**
     * Returns the Career Card the player currently possesses.
     *
     * @return  the Career Card the player possesses
     */
    public CareerCard getCareerCard ()
    {
        return careerCard;
    }

    /**
     * Returns the Salary Card the player currently possesses.
     *
     * @return  the Salary Card the player possesses
     */
    public SalaryCard getSalaryCard ()
    {
        return salaryCard;
    }

    /**
     * Returns the House Card the player currently possesses.
     *
     * @return  the House Card the player possesses
     */
    public HouseCard getHouseCard ()
    {
        return houseCard;
    }

    /**
     * Returns the college degree status of the player.
     *
     * @return  <code>true</code> if the player has a college degree; <code>false
     *          </code> otherwise
     */
    public boolean hasDegree ()
    {
        return collegeDegree;
    }

    /**
     * Returns the marriage status of the player.
     *
     * @return  <code>true</code> if the player is married; <code>false
     *          </code> otherwise
     */
    public boolean isMarried ()
    {
        return married;
    }

    /**
     * Returns the retirement status of the player.
     *
     * @return  <code>true</code> if the player is retired; <code>false
     *          </code> otherwise
     */
    public boolean isRetired ()
    {
        return retired;
    }

    /**
     * Returns the current salary of the player. Returns 0 if the player does
     * not have a career yet.
     *
     * @return  the current salary of the player
     */
    public int getCurrentSalary ()
    {
        return curSalary;
    }

    /**
     * Returns the current tax of the player. Returns 0 if the player does not
     * have a career yet.
     *
     * @return  the current tax of the player
     */
    public int getCurrentTax ()
    {
        return curTax;
    }

    /**
     * Returns the amount of pay raises the player has done already.
     *
     * @return  the amount of pay raises the player has done already
     */
    public int getPayRaises ()
    {
        return payRaises;
    }

    /**
     * Returns the amount of family members the player currently has. This
     * includes as well the spouse of the player.
     *
     * @return  the amoung of family members a player has
     */
    public int getFamily ()
    {
        return family;
    }

    @Override
    public String toString ()
    {
        String temp = "";

        temp += "Current Salary: $" + curSalary + "\n";
        temp += "Current Tax: $" + curTax + "\n";
        temp += "Status: ";

        if (family == 0)
            temp += "You are single\n";
        else temp += "You are married. ";
        if (family > 1)
            temp += "You have " + (family - 1) + " children\n";

        if (careerCard != null)
            temp += "Career: " + careerCard.getName () + "\n";

        if (houseCard != null)
            temp += "House: " + houseCard.getName () + "\n";

        return temp + "\n";
    }
}
