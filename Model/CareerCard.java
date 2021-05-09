package Model;
import java.util.Random;

/**
 * <b>CareerCard</b> is the class that contains the needed attributes
 * and methods to implement the behavior of a career card in the
 * Thats Life! game. <b>CareerCard</b> is a child class of <b>PlayerCard</b>
 *
 * @author      Joshue Salvador A. Jadie
 * @author      Andre Dominic H. Ponce
 * @version     %I%, %G%
 * @see         Card
 * @see         PlayerCard
 */
public class CareerCard extends PlayerCard
{
    /**
     * Indicates if the career card requires a degree or not,
     * <code>true</code> if requires a degree.
     */
    private boolean degreeRequired;
    /**
     * The number of times a pay raise can be done for the
     * career card.
     */
    private int maxPayRaise;
    /**
     * List of all possible careers for the career cards.
     */
    public static final String[] CAREERS = {
        "Lawyer", "Accountant", "Computer Consultant", "Doctor", "Server",
        "Racecar Driver", "Athlete"
    };

    /**
     * The sole constructor for this class. Creates a career card
     * with the specified career name to be assigned to its super
     * class.
     *
     * @param name  the career name
     */
    public CareerCard (String name)
    {
        super (name);
        setDegreeRequired ();
        setMaxPayRaise ();
    }

    /**
     * Automatically generates the description of a career card
     * based on its career name.
     */
    @Override
    protected void generateDescription ()
    {
        String[] descriptions = {
            "Advice about the law!", "A financial master!", "Technologically amazing!",
            "People's savior!", "To serve for the people!", "A beast of a rider!",
            "All-sport mighty!"
        };

        for (int j = 0; j < CAREERS.length; j++)
            if (CAREERS[j].equalsIgnoreCase (name))
                description = descriptions[j];
    }

    /**
     * Sets the degree requirement of a career card based on its
     * career name.
     */
    private void setDegreeRequired ()
    {
        int j = 0;

        while (j < CAREERS.length && !CAREERS[j].equalsIgnoreCase (name))
            j++;

        if (j <= 3)
            degreeRequired = true;
        else degreeRequired = false;
    }

    /**
     * Sets the number of pay raises of a career based on its
     * career name.
     */
    private void setMaxPayRaise ()
    {
        Random randomizer = new Random ();
        int[] randValues = {
            randomizer.nextInt (4) + 5, randomizer.nextInt (4) + 4,
            randomizer.nextInt (5) + 3, randomizer.nextInt (4) + 5,
            randomizer.nextInt (4) + 1, randomizer.nextInt (7) + 2,
            randomizer.nextInt (6) + 1
        };

        for (int j = 0; j < CAREERS.length; j++)
            if (CAREERS[j].equalsIgnoreCase (name))
                maxPayRaise = randValues[j];
    }

    /**
     * Gets the boolean value indicating if the specified career
     * card requires adegree or not.
     *
     * @return  <code>true</code> if the career card requires a
     *          degree; <code>false</code> otherwise.
     */
    public boolean isDegreeRequired ()
    {
        return degreeRequired;
    }

    /**
     * Gets the number of pay raises that can be done by the career.
     *
     * @return  the number of pay raises that can be done
     */
    public int getMaxPayRaise ()
    {
        return maxPayRaise;
    }

    @Override
    public String toString ()
    {
        String temp = "";

        temp += getName () + " ";
        if (isDegreeRequired ())
            temp += "College Degree: REQUIRED ";
        else temp += "College Degree: NOT REQUIRED ";
        temp += "Max Salary Raise Available: " + getMaxPayRaise () + " times";

        return temp;
    }
}
