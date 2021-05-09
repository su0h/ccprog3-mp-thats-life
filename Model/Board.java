package Model;
import java.util.ArrayList;

/**
 * <b>Board</b> is the class that contains the attributes and methods to
 * implement the behavior of a board in the Thats Life! game.
 * <p>
 * Implements the methods to determine the type of spaces in the board,
 * such as if the space is a magenta type of space for example.
 *
 * @author      Joshue Salvador A. Jadie
 * @author      Andre Dominic H. Ponce
 * @version     %I%, %G%
 */
public class Board
{
    /**
     * The list (sequence) of spaces in the board.
     */
    private ArrayList<Space> spaces;
    /**
     * The max number of spaces in the board.
     */
    public static final int MAX_SPACES = 156;

    /**
     * Creates the game board containing the list (sequence) of spaces.
     */
    public Board ()
    {
        spaces = new ArrayList<> ();
        Space.magentaCtr = 0;

        for (int k = 0; k < MAX_SPACES; k++)
            spaces.add (new Space (k));
    }

    /**
     * Determines the path that the current player will move to based
     * on its choice and its current space number in the board.
     *
     * @param spaceNum      the space number of the current player on the board
     * @return              A space number indicating the starting space of
     *                      the path that the current player will move to.
     */
    public int chosenPath (int spaceNum)
    {
        int left; // left path
        int right; // right path

        // if at start space junction
        if (isAtStartJunction (spaceNum)) {
            left = 0;
            right = 16;
        }
        // if at career space junction
        else if (isAtCareerJunction (spaceNum)) {
            left = 60;
            right = 73;
        }
        // else, at family space junction
        else {
            left = 108;
            right = 124;
        }

        if (ThatsLife.playerChoice == 1) // left path was chosen by the player
            return left;
        else return right; // else, right path was chosen by the player
    }

    /**
     * Determines if the current player is at the start space junction of
     * the board given its current space number.
     *
     * @param num       the current space number of the current player on the board
     * @return          <code>true</code> if the current player is on the
     *                  start space junction of the board; <code>false</code>
     *                  otherwise.
     */
    public boolean isAtStartJunction (int num)
    {
        return num == 0;
    }

    /**
     * Determines if the current player is at the career space junction of
     * the board given its current space number.
     *
     * @param num       the current space number of the current player on the board
     * @return          <code>true</code> if the current player is on the
     *                  career space junction of the board; <code>false</code>
     *                  otherwise.
     */
    public boolean isAtCareerJunction (int num)
    {
        return num == 60;
    }

    /**
     * Determines if the current player is at the family space junction of
     * the board given its current space number.
     *
     * @param num       the current space number of the current player on the board
     * @return          <code>true</code> if the current player is on the
     *                  family space junction of the board; <code>false</code>
     *                  otherwise.
     */
    public boolean isAtFamilyJunction (int num)
    {
        return num == 108;
    }

    /**
     * Determines if the current player is at either the career space or
     * the family space junction of the board given the magenta action
     * of the magenta space landed on by the current player.
     *
     * @param action        the action of the magenta space
     * @return              <code>true</code> if the current player is at
     *                      one of the magenta space junctions; <code>false</code>
     *                      otherwise.
     */
    public boolean isAtMagentaJunction (String action)
    {
        return action.equalsIgnoreCase (Space.MAGENTAS[3]) ||
               action.equalsIgnoreCase (Space.MAGENTAS[5]);
    }

    /**
     * Determines if the current player is at the retirement space of the
     * board given the type of the space currently landed on.
     *
     * @param type      the space type
     * @return          <code>true</code> if the current player is on the
     *                  retirement space of the board; <code>false</code>
     *                  otherwise.
     */
    public boolean isAtRetirementSpace (String type)
    {
        return type.equalsIgnoreCase (Space.TYPES[3]);
    }

    /**
     * Determines if the current player is at the green space of the
     * board given the type of the space currently landed on.
     *
     * @param type      the space type
     * @return          <code>true</code> if the current player is on the
     *                  green space of the board; <code>false</code>
     *                  otherwise.
     */
    public boolean isAtGreenSpace (String type)
    {
        return type.equalsIgnoreCase (Space.TYPES[0]) || type.equalsIgnoreCase (Space.TYPES[1]);
    }

    /**
     * Determines if the current player is at the magenta space of the
     * board given the type of the space currently landed on.
     *
     * @param type      the space type
     * @return          <code>true</code> if the current player is on the
     *                  magenta space of the board; <code>false</code>
     *                  otherwise.
     */
    public boolean isAtMagentaSpace (String type)
    {
        return type.equalsIgnoreCase (Space.TYPES[5]);
    }

    /**
     * Determines if the current player is at the blue space of the
     * board given the type of the space currently landed on.
     *
     * @param type      the space type
     * @return          <code>true</code> if the current player is on the
     *                  blue space of the board; <code>false</code>
     *                  otherwise.
     */
    public boolean isAtBlueSpace (String type)
    {
        return type.equalsIgnoreCase (Space.TYPES[2]);
    }

    /**
     * Determines if the current player is at the orange space of the
     * board given the type of the space currently landed on.
     *
     * @param type      the space type
     * @return          <code>true</code> if the current player is on the
     *                  orange space of the board; <code>false</code>
     *                  otherwise.
     */
    public boolean isAtOrangeSpace (String type)
    {
        return type.equalsIgnoreCase (Space.TYPES[4]);
    }

    /**
     * Determines if the current player is at the college career choice
     * magenta space of the board given the action of the magenta space
     * currently landed on.
     *
     * @param action      the action of the magenta space
     * @return            <code>true</code> if the current player is on the
     *                    college career choice magenta space of the board;
     *                    <code>false</code> otherwise.
     */
    public boolean isCareerChoiceSpace (String action)
    {
        return action.equalsIgnoreCase (Space.MAGENTAS[1]);
    }

    /**
     * Determines if the current player is at the job search magenta
     * space of the board given the action of the magenta space
     * currently landed on.
     *
     * @param action      the action of the magenta space
     * @return            <code>true</code> if the current player is on the
     *                    job search magenta space of the board;
     *                    <code>false</code> otherwise.
     */
    public boolean isJobSearchSpace (String action)
    {
        return action.equalsIgnoreCase (Space.MAGENTAS[4]);
    }

    /**
     * Determines if the current player is at the buy house magenta
     * space of the board given the action of the magenta space
     * currently landed on.
     *
     * @param action      the action of the magenta space
     * @return            <code>true</code> if the current player is on the
     *                    buy house magenta space of the board;
     *                    <code>false</code> otherwise.
     */
    public boolean isBuyHouseSpace (String action)
    {
        return action.equalsIgnoreCase (Space.MAGENTAS[7]);
    }

    /**
     * Determines if the current player is at the have children magenta
     * space of the board given the action of the magenta space
     * currently landed on.
     *
     * @param action      the action of the magenta space
     * @return            <code>true</code> if the current player is on the
     *                    have children magenta space of the board;
     *                    <code>false</code> otherwise.
     */
    public boolean isHaveChildrenSpace (String action)
    {
        return action.equalsIgnoreCase (Space.MAGENTAS[8]);
    }

    /**
     * Gets the space in the board given the space number.
     *
     * @param num       the space number
     * @return          the space corresponding the number
     */
    public Space getSpace (int num)
    {
        return spaces.get (num);
    }

    @Override
    public String toString ()
    {
        String temp = "BOARD:\n";

        for (int k = 0; k < spaces.size (); k++)
            temp += spaces.get (k).toString ();

        return temp;
    }
}
