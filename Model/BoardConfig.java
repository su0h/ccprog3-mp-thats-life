package Model;

/**
 * <b>BoardConfig</b> is an interface that provides the defined and fixed
 * configuration of the board together with its respective spaces.
 * <p>
 * Provides the space number indices, types, and sequence for the generation of
 * the board to be used for the Thats Life! game.
 *
 * @author      Joshue Salvador A. Jadie
 * @author      Andre Dominic H. Ponce
 * @version     %I%, %G%
 */
public interface BoardConfig
{
    /**
     * List of space number indices for the magenta spaces.
     */
    public static final int[] MAGENTA_CONFIG = {
        10, 15, 26, 60, 63, 108, 112, 118, 123, 144
    };
    /**
     * List of space number indices for the blue spaces.
     */
    public static final int[] BLUE_CONFIG = {
        36, 41, 54, 57, 66, 83, 84, 93, 102, 117, 125, 126, 127,
        128, 129, 140, 150, 151, 152
    };
    /**
     * List of space number indices for the pay day (green) spaces.
     */
    public static final int[] PD_CONFIG = {
        33, 35, 39, 47, 50, 56, 64, 68, 71, 79, 80, 81, 92, 104,
        109, 110, 131, 132, 138, 139, 147, 148, 149, 154
    };
    /**
     * List of space number indices for the pay raise (green) spaces.
     */
    public static final int[] PR_CONFIG = {
        37, 44, 52, 58, 62, 75, 76, 77, 90, 103, 130, 133, 137, 145,
        146
    };
    /**
     * Space number index for the starting space in the board.
     */
    public static final int START_CONFIG = 0;
    /**
     * Space number index for the retirement space in the board.
     */
    public static final int RET_CONFIG = 155;
}
