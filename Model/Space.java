package Model;

/**
 * <b>Space</b> is the class that contains the attributes and methods to
 * implement the behavior of a space in the Thats Life! game.
 *
 * @author      Joshue Salvador A. Jadie
 * @author      Andre Dominic H. Ponce
 * @version     %I%, %G%
 */
public class Space implements BoardConfig
{
    /**
     * the number of this space.
     */
    private int num;
    /**
     * the type of this space.
     */
    private String type;
    /**
     * the magenta action of this space, if the type of this space
     * is magenta.
     */
    private String magenta;
    /**
     * List of all the possible types of spaces.
     */
    public static final String[] TYPES = {
        "Pay Day", "Pay Raise", "Blue Space", "Retirement Space",
        "Orange Space", "Magenta Space"
    };
    /**
     * List of all possible magenta actions of magenta type spaces.
     */
    public static final String[] MAGENTAS = {
        "Graduation Space", "College Career Choice", "Get Married",
        "Which Career Path?", "Job Search", "Which Family Path?",
        "Get Married", "Buy House", "Have Children", "Have Children"
    };
    /**
     * counter (indicator) of magenta action to be assigned to
     * each magenta space sequentially.
     */
    public static int magentaCtr = 0;

    /**
     * Creates a space with the given number (to be treated as index).
     *
     * @param num   the space number
     */
    public Space (int num)
    {
        this.num = num;
        magenta = "N/A";
        setType (); // set space type according to space number given
    }

    /**
     * Sets the type of this space according to space number given
     */
    private void setType ()
    {
        /* SET TYPE ACCORDING TO FIXED BOARD CONFIGURATION */
        int k;

        type = TYPES[4]; // set initially to orange space

        for (k = 0; k < MAGENTA_CONFIG.length; k++) // if magenta space
            if (num == MAGENTA_CONFIG[k]) {
                type = TYPES[5];
                magenta = MAGENTAS[magentaCtr++];
            }

        for (k = 0; k < BLUE_CONFIG.length; k++) // if blue space
            if (num == BLUE_CONFIG[k])
                type = TYPES[2];

        for (k = 0; k < PD_CONFIG.length; k++) // if pay day (green space)
            if (num == PD_CONFIG[k])
                type = TYPES[0];

        for (k = 0; k < PR_CONFIG.length; k++) // if pay raise (green space)
            if (num == PR_CONFIG[k])
                type = TYPES[1];

        if (num == RET_CONFIG) // if retirement space
            type = TYPES[3];
    }

    /**
     * Gets the space number (index) of this space.
     *
     * @return  the space number (index)
     */
    public int getNum ()
    {
        return num;
    }

    /**
     * Gets the type of this space.
     *
     * @return  the space type
     */
    public String getType ()
    {
        return type;
    }

    /**
     * Gets the magenta action of this space if the space type is magenta.
     * <p>
     * If the type of space is not magenta, will return instead "N/A".
     *
     * @return  the magenta action; "N/A" if not a magenta space.
     */
    public String getMagenta ()
    {
        return magenta;
    }

    @Override
    public String toString ()
    {
        return "Space Number: " + num + " Space Type: " + type + "\n";
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        return ((Space) obj).num == num;
    }
}
