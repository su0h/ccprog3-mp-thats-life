package Model;
import java.util.Random;

/**
 * <b>SalaryCard</b> is the class that contains the needed attributes
 * and methods to implement the behavior of a salary card in the
 * Thats Life! game. <b>SalaryCard</b> is a child class of <b>PlayerCard</b>
 *
 * @author      Joshue Salvador A. Jadie
 * @author      Andre Dominic H. Ponce
 * @version     %I%, %G%
 * @see         Card
 * @see         PlayerCard
 */
public class SalaryCard extends PlayerCard
{
    /**
     * The salary value of this card.
     */
    private int salary;
    /**
     * The tax value of this card.
     */
    private int tax;
    /**
     * The pay raise value of this card.
     */
    private int payRaise;

    /**
     * The sole constructor for this class. Creates a salary card with
     * a default name ("Your Salary Card") to be assigned to its super
     * class.
     */
    public SalaryCard ()
    {
        super ("Your Salary Card");
        setSalary ();
        setTax ();
        setPayRaise ();
    }

    /**
     * Automatically generates a description for the salary card.
     */
    @Override
    protected void generateDescription ()
    {
        Random randomizer = new Random ();
        String[] descriptions = {
            "Let's work it!", "Let's get some money!", "Hey, I have a salary!"
        };

        description = descriptions[randomizer.nextInt (3)];
    }

    /**
     * Sets the salary value of this salary card randomly.
     */
    private void setSalary ()
    {
        Random randomizer = new Random ();
        salary = (randomizer.nextInt (9) + 1) * 10000;
    }

    /**
     * Sets the tax value of this salary card randomly.
     */
    private void setTax ()
    {
        Random randomizer = new Random ();
        tax = (randomizer.nextInt (9) + 1) * 1000;
    }

    /**
     * Sets the pay raise value of this salary card randomly.
     */
    private void setPayRaise ()
    {
        Random randomizer = new Random ();
        payRaise = (randomizer.nextInt (5) + 1) * 10000;
    }

    /**
     * Gets the salary value of the this salary card.
     *
     * @return  the salary value of the salary card
     */
    public int getSalary ()
    {
        return salary;
    }

    /**
     * Gets the tax value of the this salary card.
     *
     * @return  the tax value of the salary card
     */
    public int getTax ()
    {
        return tax;
    }

    /**
     * Gets the pay raise value of the this salary card.
     *
     * @return  the pay raise value of the salary card
     */
    public int getPayRaise ()
    {
        return payRaise;
    }

    @Override
    public String toString ()
    {
        return "Salary: " + getSalary () + " Tax: " + getTax () + " Pay Raise: " + getPayRaise ();
    }
}
