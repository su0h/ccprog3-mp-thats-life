/**
 * <b>ViewConfig</b> is an interface that provides the defined and fixed
 * configuration for most of the GUI elements, for the view aspect of the
 * Thats Life! game.
 * <p>
 * Contains the lists of URLS for player car images and tile colors, stage
 * titles, and other fixed elements to be used for the graphical user
 * interface (GUI) or view aspect of the Thats Life! game.
 *
 * @author  Joshue Salvador A. Jadie
 * @author  Andre Dominic H. Ponce
 * @version %I%, %G%
 */
public interface ViewConfig
{
    /**
     * List of all player car image URLS.
     */
    public static final String[] CAR_URLS = {
        "images/BlueCar.png", "images/GrayCar.png", "images/GreenCar.png",
        "images/RedCar.png", "images/SilverCar.png", "images/YellowCar.png"
    };
    /**
     * List of all tile color image URLS.
     */
    public static final String[] TILE_URLS = {
        "images/MagentaTile.png", "images/BlueTile.png", "images/GreenTile.png",
        "images/OrangeTile.png", "images/StartTile.png", "images/RetirementTile.png"
    };
    /**
     * List of all stage titles.
     */
    public static final String[] STAGE_TITLES = { // list of stage titles
        "That's Life! :)", "Feeling down? That's Life!", "Feeling Happy? That's Life!",
        "That's Life! Let's play a game!", "Welcome to the Game of Life!", "Life's a Game!"
    };
    /**
     * List of instructions for the instructions scene of <b>View</b>
     */
    public static final String[] INSTRUCTIONS = { // list of instructions
        "That's Life! mirrors life events of people from going to college, having a career, raising a family, investing, buying a house, working, and retiring.",
        "The player decides what kind of life they want to experience during the game.",
        "At the start of each round, the player presses for a randomly generated number to determine the number of spaces they will advance on the board.",
        "The goal of the game is for the players to reach retirement as early as possible with the most cash savings on hand.",
        "For this implementation of That's Life!, the retirement tile is at the 155th space on the board."
    };
    /**
     * List of all possible announcements regarding to specific events
     */
    public static final String[] ANNOUNCE_TYPES = {
      "Max Pay Raise", "Next Player Turn", "Borrow Bank Money", "Matched Blue Career",
      "Pay Day", "Pay Raise", "Stop", "Already Married", "Max Family", "Can't Have Children",
      "No Career", "Degree Required", "Reshuffle"
    };
    /**
     * Max grid size for the board (3x3 grid)
     */
    public static final int MAX_GRID = 9;
    /**
     * List of all board portions (grid patterns)
     */
    public static final int[][] BOARD_GRID = {
        {1,  2,  3,
         0, -1, -1,   // PORTION 1
         17, 18, 19},

        {4,  5,  6,
         -1, -1, -1,  // PORTION 2
         20, 21, 22},

        {7,  8,  9,
         -1, -1, -1,  // PORTION 3
         23, 24, 25},

        {10, 11, 12,
         -1, -1, -1,  // PORTION 4 AND SO ON...
         26, 27, 28},

        {13, 14, 15,
         -1, -1, 32,
         29, 30, 31},

        {-1, -1, -1,
         33, 34, -1,
         -1, 35, -1},

        {-1, 36, -1,
         -1, 37, -1,
         -1, 38, -1},

        {-1, 39, -1,
         41, 40, -1,
         -1, -1, -1},

        {-1, -1, -1,
         44, 43, 42,
         -1, -1, -1},

        {-1, -1, -1,
         47, 46, 45,
         -1, -1, -1},

        {-1, -1, -1,
         50, 49, 48,
         -1, -1, -1},

        {-1, -1, -1,
         -1, 52, 51,
         -1, 53, -1},

        {-1, 54, -1,
         -1, 55, 56,
         -1, -1, -1},

        {-1, -1, -1,
         57, 58, -1,
         -1, 59, -1},

        {74, 60, 61,
         75, -1, 62,
         76, -1, 63},

        {77, -1, 64,
         78, -1, 65,
         79, -1, 66},

        {80, -1, 67,
         81, -1, 68,
         82, -1, 69},

        {83, -1, 70,
         84, -1, 71,
         85, 86, 72},

        {-1, 87, -1,
         -1, 88, 89,
         -1, -1, -1},

        {-1, 92, -1,
         90, 91, -1,
         -1, -1, -1},

        {-1, 95, -1,
         -1, 94, -1,
         -1, 93, -1},

        {-1, 98, -1,
         -1, 97, -1,
         -1, 96, -1},

        {-1, 101, -1,
         -1, 100, -1,
         -1, 99,  -1},

        {-1, -1,  -1,
         -1, 103, 104,
         -1, 102, -1},

        {-1,  -1,  -1,
         105, 106, 107,
         -1,  -1,  -1},

        {109, 110, 111,
         108, -1,  -1,
         125, 126, 127},

        {112, 113, 114,
         -1,  -1,  -1,
         128, 129, 130},

        {115, 116, 117,
          -1, -1,  -1,
         131, 132, 133},

        {118, 119, 120,
         -1,  -1,  -1,
         134, 135, 136},

        {121, 122, 123,
         -1,  -1,  140,
         137, 138, 139},

        {-1, 143, -1,
         141,142, -1,
         -1, -1,  -1},

        {-1, 146, -1,
         -1, 145, -1,
         -1, 144, -1},

        {-1, 149, -1,
         -1, 148, -1,
         -1, 147, -1},

        {-1, 152, -1,
         -1, 151, -1,
         -1, 150, -1},

        {-1, 155, -1,
         -1, 154, -1,
         -1, 153, -1}
    };
}
