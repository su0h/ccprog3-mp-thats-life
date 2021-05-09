package Model;
import java.util.ArrayList;

/**
 * <b>ThatsLife</b> is the <b>master</b> (main) class for the model aspect of the
 * That's Life! game.
 * <p>
 * Composes all of the involved classes in the same package (Model) and
 * contains the implemented methods to manipulate the subclasses and other
 * primitive attributes involved to replicate the game system of That's Life!.
 *
 * @author      Joshue Salvador A. Jadie
 * @author      Andre Dominic H. Ponce
 * @version     %I%, %G%
 */
public class ThatsLife
{
    /**
     * The list of players that will play the game.
     */
    private ArrayList<Player> players;
    /**
     * The board of the game.
     */
    private Board board;
    /**
     * The list of decks in the game.
     */
    private ArrayList<Deck> decks;
    /**
     * The wheel (spinner) of the game.
     */
    private Wheel wheel;
    /**
     * The ordinal ranking of a player in retirement. If the
     * player is the 1st to retire for example.
     */
    private int retirePlace;
    /**
     * The index of the current player in-turn during the game.
     */
    public static int playerTurn;
    /**
     * The value indicating the choice made by the current player
     * in-turn regarding specific events in the game.
     */
    public static int playerChoice;
    /**
     * The number of players that will play the game.
     */
    private int noOfPlayers;
    /**
     * The number of players that have not yet retire in the game.
     */
    private int noOfActivePlayers;
    /**
     * The max number of players that can play the game.
     */
    public static final int MAX_PLAYERS = 3;
    /**
     * Indicates if the game is already finished, all players
     * have retired. <code>true</code> if finished,
     * <code>false</code> if not.
     */
    private boolean finished;

    /**
     * The sole constructor of the object. Allocates a new ThatsLife
     * object (creates a new game).
     */
    public ThatsLife ()
    {
        // initialize ArrayLists, the board, and the wheel of the game
        players = new ArrayList<> ();
        board = new Board ();
        decks = new ArrayList<> ();
        wheel = new Wheel ();

        // initialize other game system variables
        playerTurn = playerChoice = 0;
        retirePlace = 1;
        finished = false; // true if all players have retired; game is finished

        // creates the game decks (ActionCard, BlueCard, ...)
        for (int k = 0; k < Deck.CARD_TYPES.length; k++)
            decks.add(new Deck(Deck.CARD_TYPES[k]));
    }

    /**
     * Sets the number of players that will play the game. Returns a
     * boolean value depending on the validity of the number of players
     * value passed.
     *
     * @param noOfPlayers   the number of players
     * @return              <code>true</code> if the number of players is
     *                      valid; <code>false</code> otherwise.
     */
    public boolean setNumOfPlayers (int noOfPlayers)
    {
        // check if inputted value is less than 2 or greater than max number of players allowed
        if (noOfPlayers < 2 || noOfPlayers > MAX_PLAYERS)
            return false;

        this.noOfPlayers = noOfActivePlayers = noOfPlayers;

        return true;
    }

    /**
     * Creates a new player given the name inputted. Returns a boolean
     * value depending on the validity of the name passed.
     *
     * @param name      the name of the new player to be added
     * @return          <code>true</code> if the name is unique and
     *                  not <code>null</code>; <code>false</code>
     *                  if name already exists from previously
     *                  added players or was <code>null</code>
     */
    public boolean createPlayer (String name)
    {
        // if name passed is empty or null
        if (name == null)
            return false;

        // determine if player name already exists
        boolean exist = isPlayerNameExist (name);

        // if name is unique, create new player to be added to the game
        if (!exist)
            players.add (new Player (name));

        return exist;
    }

    /**
     * Checks whether the given name inputted already exists in
     * previous players or not.
     *
     * @param name      the name of the new player to be added
     * @return          <code>true</code> if name does not exist
     *                  or is unique; <code>false</code> otherwise.
     */
    private boolean isPlayerNameExist (String name)
    {
        for (Player p : players)
            if (p.getName ().equalsIgnoreCase (name))
                return true;

        return false;
    }

    /**
     * Changes the value of the index (<b>playerTurn</b>) to indicate
     * the next player now having the turn.
     */
    public void nextPlayer ()
    {
        if (playerTurn == players.size () - 1)
            playerTurn = 0;
        else playerTurn++;
    }

    /**
     * Checks if the action to be done in the current space landed on
     * by the current player in-turn requires drawing of specific card.
     *
     * @return  <code>true</code> if action requires card draw;
     *          <code>false</code> otherwise.
     */
    public boolean isActionRequireCard ()
    {
        // check all actions that require drawing from a card deck
        for (String s : Deck.DECK_ACTIONS)
            if (s.equalsIgnoreCase (getCurrentSpace ().getMagenta ()) ||
                s.equalsIgnoreCase (getCurrentSpace ().getType ()))
                    return true;

        return false;
    }

    /**
     * Checks if the action to be done in the current space landed on
     * by the current player in-turn requires spinning of the game
     * wheel.
     *
     * @return  <code>true</code> if action requires wheel spin;
     *          <code>false</code> otherwise.
     */
    public boolean isActionRequireSpin ()
    {
        // if "Get Married" or "Have Children" magenta space action
        if (getCurrentSpace ().getMagenta ().equalsIgnoreCase (Space.MAGENTAS[2]))
            return true;

        // if "Tip The Server" or "Computer Repair" blue space action
        if (getCurrentSpace ().getType ().equalsIgnoreCase (Space.TYPES[2]))
            return ((BlueCard) getCardDrawn ()).getActionValue () == 3 || ((BlueCard) getCardDrawn ()).getActionValue () == 5;

        return false;
    }

    /**
     * Checks if the action to be done in the current space landed on
     * by the current player in-turn involves other players in the game.
     *
     * @return  <code>true</code> if action requires involvement of
     *          other players; <code>false</code> otherwise.
     */
    public boolean isActionRequirePlayer ()
    {
        /* if blue space action */
        if (getCurrentSpace ().getType ().equalsIgnoreCase (Space.TYPES[2]))
            return true;
        /* if orange space action */
        else if (getCurrentSpace ().getType ().equalsIgnoreCase (Space.TYPES[4]))
            return ((ActionCard) getCardDrawn ()).getActionValue () == 3 ||
                   ((ActionCard) getCardDrawn ()).getActionValue () == 4;

        return false;
    }

    /**
     * Draws an action card from the decks.
     */
    public void drawActionCard ()
    {
        decks.get (0).drawCard ();
    }

    /**
     * Draws a blue card from the decks.
     */
    public void drawBlueCard ()
    {
        decks.get (1).drawCard ();
    }

    /**
     * Draws a house card from the decks.
     *
     * @param index the index of the house card to be drawn
     */
    public void drawHouseCard (int index)
    {
        decks.get (2).drawCard (index);
    }

    /**
     * Draws a salary card from the decks.
     */
    public void drawSalaryCard ()
    {
        decks.get (3).drawCard ();
    }

    /**
     * Draws a career card regardless of degree required
     * from the decks.
     */
    public void drawCareerCard ()
    {
        decks.get (4).drawCard ();
    }

    /**
     * Draws a career card with no degree required from the
     * decks.
     */
    public void drawNoDegreeCareer ()
    {
        decks.get (4).drawNoDegreeCareer();
    }

    /**
     * Shuffles all game decks.
     */
    public void shuffleDecks ()
    {
        for (int k = 0; k < Deck.DECK_ACTIONS.length; k++)
            decks.get (k).shuffleDeck ();
    }

    /**
     * Clears the previously drawn cards for the next player.
     */
    public void clearDrawnCards ()
    {
        for (int k = 0; k < Deck.DECK_ACTIONS.length; k++)
            decks.get (k).clearDrawnCards ();
    }

    /**
     * Analyzes and executes the action to be done based on the
     * type of the current space landed on by the player in-turn.
     *
     * @return <code>true</code> if player in-turn did not do any
     *         extra action from the specific event, player
     *         borrowing money from the bank for example;
     *         <code>false</code> otherwise.
     */
    public boolean executeAction ()
    {
        boolean result = true; // true for now; will become false from specific events

        // if at start junction
        if (board.isAtStartJunction (getCurrentPlayer ().getSpaceNum ())) {
            switch (playerChoice) {
            case 1: // college path, borrow $40000 from the bank
                for (int k = 0; k < 2; k++)
                    getCurrentPlayer ().borrowLoan ();
                break;
            case 2: // career path, set Career Card and set Salary Card
                getCurrentPlayer ().getStatus ().setCareerCard (getCareerCardDrawn ());
                getCurrentPlayer ().getStatus ().setSalaryCard (getSalaryCardDrawn ());
            }
        } else { // check type of space
            int k = 0;
            while (!getCurrentSpace ().getType ().equalsIgnoreCase (Space.TYPES[k]))
                k++;

            switch (k) {
            case 0:
            case 1: result = greenAction (); break; // green space
            case 2: result = blueAction (); break; // blue space
            case 3: retirePlayer (); break; // retirement space
            case 4: result = orangeAction (); break; // orange space
            case 5: result = magentaAction (); // magenta space
            }
        }

        return result;
    }

    /**
     * Analyzes and executes the action of the green space landed.
     * <p>
     * The returned boolean value is always true if the action done was
     * pay day. For pay raise, will return false if player had
     * reached the number of times a pay raise can be done as
     * stated in the career card.
     *
     * @return  <code>true</code> if pay day or if max pay raise
     *          was not yet reached; <code>false</code> otherwise.
     */
    private boolean greenAction ()
    {
        if (getCurrentSpace ().getType ().equalsIgnoreCase (Space.TYPES[0])) // Pay Day
            getCurrentPlayer ().payDay ();
        else // Pay Raise
            return getCurrentPlayer ().getStatus ().payRaise ();

        return true;
    }

    /**
     * Analyzes the executes the action of the magenta space landed.
     * <p>
     * The returned boolean value will depend on the specific magenta
     * space action done by the player in-turn. For Graduation Space,
     * will always return <code>true</code> since it is an one-time action.
     * <p>
     * Will return <code>false</code> if,
     * <p>
     * For Get Married space, player is already married.
     * For Buy House space, player does not have enough money to pay
     * the house therefore borrowing money from the bank (loan).
     * For Have Children space, player has already reached the max
     * number of children allowed.
     *
     * @return  <code>true</code> if the conditions above the specific
     *          actions were not met; <code>false</code> otherwise.
     */
    private boolean magentaAction ()
    {
        boolean result = true; // true for now; will become false from specific events

        int action = 0;
        while (!getCurrentSpace ().getMagenta ().equalsIgnoreCase (Space.MAGENTAS[action]))
            action++;

        switch (action) {
        // Graduation Space
        case 0: getCurrentPlayer ().getStatus ().graduate (); break;
        // Get Married Space
        case 2:
            result = getCurrentPlayer ().getStatus ().marry ();
            // if player got married recently
            if (result)
                cashFromAll (getWheel ().getNumber () % 2 == 1 ? 5000 : 10000);
            break;
        // College Career Choice or Job Search Space
        case 1: // College Career Choice
        case 4: // Job Search
            if (playerChoice == 1) {
                getCurrentPlayer ().getStatus ().setCareerCard (getCareerCardDrawn ());
                getCurrentPlayer ().getStatus ().setSalaryCard (getSalaryCardDrawn ());
            } else if (getCurrentSpace ().getMagenta ().equalsIgnoreCase (Space.MAGENTAS[1])) { // if career choice
                getCurrentPlayer ().getStatus ().setCareerCard (getSecondCareerCardDrawn ());
                getCurrentPlayer ().getStatus ().setSalaryCard (getSecondSalaryCardDrawn ());
            }
            break;
        // Buy House Space
        case 7:
            getCurrentPlayer ().getStatus ().setHouseCard ((HouseCard) getCardDrawn ());
            result = getCurrentPlayer ().payCash (((HouseCard) getCardDrawn ()).getPrice ());
            break;
        // Have Children Space
        case 8:
            if (getCurrentPlayer (). getStatus ().isMarried ()) {
              result = getCurrentPlayer ().getStatus ().addFamily (getWheel ().getNumber () % 2 + 1);
              cashFromAll (5000 * (getCurrentPlayer ().getStatus ().getFamily () - 1));
            } else result = false;
        }

        return result;
    }

    /**
     * Analyzes and executes the action of the blue card drawn.
     * <p>
     * The returned boolean value will depend if the player matched the
     * career stated in the blue card drawn or if the player will pay
     * another player (if another player has the stated career)
     * or the bank. Will always return <code>true</code> matched the
     * career stated in the blue card drawn.
     *
     * @return  <code>true</code> if the player matched the career or
     *          did not borrow money from the bank to pay; <code>false</code>
     *          if player borrowed money from the bank to pay.
     */
    private boolean blueAction ()
    {
        boolean result = true;

        // if career matched, get $15000 instead
        if (isMatchingCareer ()) {
            getCurrentPlayer ().addCash (15000);
            return result;
        }

        // else, check specified blue action
        switch (((BlueCard) getCardDrawn ()).getActionValue ()) {
        case 1: // Lawsuit
        case 4: // Ski Accident
            result = payToPlayer (((BlueCard) getCardDrawn ()).getAmount ());
            break;
        case 2: // Salary Tax Due
            result = payToPlayer (getCurrentPlayer ().getStatus ().getCurrentTax ());
            break;
        case 3: // Computer Repair
            result = payToPlayer (getWheel ().getNumber () % 2 == 0 ? 5000 : 10000);
            break;
        case 5: // Tip The Server
            result = payToPlayer (((BlueCard) getCardDrawn ()).getAmount () * getWheel ().getNumber ());
            break;
        case 6: // F1 Race
            result = payToPlayer (getCurrentPlayer ().getStatus ().getCurrentSalary () / 10);
            break;
        case 7: // World Cup
            result = payToPlayer (((BlueCard) getCardDrawn ()).getAmount () * players.size ());
        }

        return result;
    }

    /**
     * Analyzes the executes the action of the action card drawn.
     *
     * @return  <code>true</code> if the player did not borrow money
     *          from the bank to pay; <code>false</code> otherwise.
     */
    private boolean orangeAction ()
    {
        boolean result = true;

        switch (((ActionCard) getCardDrawn ()).getActionValue ()) {
        case 1: getCurrentPlayer ().addCash (((ActionCard) getCardDrawn ()).getAmount ()); break; // COLLECT FROM BANK
        case 2: result = getCurrentPlayer ().payCash (((ActionCard) getCardDrawn ()).getAmount ()); break; // PAY TO BANK
        case 3: result = payToPlayer (((ActionCard) getCardDrawn ()).getAmount ()); break; // PAY TO PLAYER
        case 4: result = cashFromPlayer (((ActionCard) getCardDrawn ()).getAmount ()); break; // COLLECT FROM PLAYER
        case 5: result = payToAll (((ActionCard) getCardDrawn ()).getAmount ()); break; // PAY TO ALL PLAYERS
        case 6: result = cashFromAll (((ActionCard) getCardDrawn ()).getAmount ()); // CASH FROM ALL PLAYERS
        }

        return result;
    }

    /**
     * Checks if the player in-turn matched the career stated in the blue
     * card drawn
     *
     * @return  <code>true</code> if player in-turn matched the career;
     *          <code>false</code> otherwise.
     */
    public boolean isMatchingCareer ()
    {
        // if player does not have a career card
        if (getCurrentPlayer ().getStatus ().getCareerCard () == null)
            return false;

        String temp = ((BlueCard) getCardDrawn ()).getCareer ();

        return getCurrentPlayer ().getStatus ().getCareerCard ().getName ().equalsIgnoreCase (temp);
    }

    /**
     * Specifically for blue card action, gets the index of the player
     * that matches the career stated in the blue card if the player
     * in-turn did not match the career. Assigns the index acquired to
     * the <b>playerChoice</b> attribute, -1 if no player found.
     */
    public void findPlayerWithCareer ()
    {
        // get career from the blue card drawn
        String temp = ((BlueCard) getCardDrawn ()).getCareer ();

        playerChoice = -1; // while not yet found

        // find index of player with the career
        for (int k = 1; k <= players.size (); k++)
            if (k - 1 != playerTurn &&
                players.get (k - 1).getStatus ().getCareerCard () != null &&
                !players.get (k - 1).getStatus ().isRetired ())
                if (players.get (k - 1).getStatus ().getCareerCard ().getName ().equalsIgnoreCase (temp))
                   playerChoice = k;
    }

    /**
     * Pays the player chosen by the action of the player in-turn. If
     * the player to be paid is already retired, will not be affected
     * by the action instead.
     *
     * @param amt       the amount to be paid to the chosen player
     * @return          <code>true</code> if the player in-turn did
     *                  not borrow money from the bank to pay;
     *                  <code>false</code> otherwise.
     */
    private boolean payToPlayer (int amt)
    {
        // if 2 non-retired players left, automatically get index for player to be paid
        if (noOfActivePlayers < 3 && playerChoice == 0) {
            playerChoice = 1; // temporarily set to 1

            // while index of player in-turn and is retired
            while (playerChoice - 1 == playerTurn || players.get (playerChoice - 1).getStatus ().isRetired ())
                playerChoice++;
        }

        // playerChoice is -1 if set by findPlayerWithCareer from specified blue card action
        if (playerChoice != -1)
            if (!players.get (playerChoice - 1).getStatus ().isRetired ())
                players.get (playerChoice - 1).addCash (amt);

        return getCurrentPlayer ().payCash (amt); // false if player borrowed money from the bank (loan)
    }

    /**
     * Gets cash from player chosen by the action of the player in-turn. If
     * the player to get cash from is already retired, will not be affected
     * by the action instead.
     *
     * @param amt       the amount to be paid from the chosen player
     * @return          <code>true</code> if the chosen player did
     *                  not borrow money from the bank to pay;
     *                  <code>false</code> otherwise.
     */
    private boolean cashFromPlayer (int amt)
    {
        // if 2 non-retired players left, automatically get index for player to be paid
        if (noOfActivePlayers < 3 && playerChoice == 0) {
            playerChoice = 1; // temporarily set to 1

            // while index of player in-turn and is retired
            while (playerChoice - 1 == playerTurn || players.get (playerChoice - 1).getStatus ().isRetired ())
                playerChoice++;
        }

        getCurrentPlayer ().addCash (amt);

        // playerChoice is -1 if set by findPlayerWithCareer from specified blue card action
        if (playerChoice != -1) {
            if (!players.get (playerChoice - 1).getStatus ().isRetired ())
                return players.get (playerChoice - 1).payCash (amt);
        }

        return true;
    }

    /**
     * Pays all other players as specified by the action of the
     * player in-turn. If the player to be paid is already retired,
     * will not be affected by the action instead.
     *
     * @param amt       the amount to be paid to the players
     * @return          <code>true</code> if the player in-turn did
     *                  not borrow money from the bank to pay;
     *                  <code>false</code> otherwise.
     */
    private boolean payToAll (int amt)
    {
        for (int k = 0; k < noOfPlayers; k++)
            // if index is not from current player in-turn or player is not retired
            if (k != playerTurn && !players.get (k).getStatus ().isRetired ())
                players.get (k).addCash (amt);

        return getCurrentPlayer ().payCash (amt * (noOfActivePlayers - 1));
    }

    /**
     * Gets cash from all players as specified by the action of the
     * player in-turn. If the player to get cash from is already retired,
     * will not be affected by the action instead.
     *
     * @param amt       the amount to be acquired from the players
     * @return          <code>true</code> if the chosen player did
     *                  not borrow money from the bank to pay;
     *                  <code>false</code> otherwise.
     */
    private boolean cashFromAll (int amt)
    {
        boolean result = false;

        for (int k = 0; k < noOfPlayers; k++)
            // if index is not from current player in-turn or player is not retired
            if (k != playerTurn && !players.get (k).getStatus ().isRetired ())
                if (!result)
                    result = players.get (k).payCash (amt);
                else players.get (k).payCash (amt);

        getCurrentPlayer ().addCash (amt * (noOfActivePlayers - 1));

        return result;
    }

    /**
     * Gets the list of players in this game.
     *
     * @return  list of players
     * @see     Player
     */
    public ArrayList<Player> getPlayers ()
    {
        return players;
    }

    /**
     * Gets the current player in-turn in this game.
     *
     * @return  the current player in-turn
     * @see     Player
     */
    public Player getCurrentPlayer ()
    {
        return players.get (playerTurn);
    }

    /**
     * Gets the current space landed on by the current player
     * in-turn.
     *
     * @return  the current space landed on by the current player
     * @see     Space
     */
    public Space getCurrentSpace ()
    {
        return board.getSpace (getCurrentPlayer ().getSpaceNum ());
    }

    /**
     * Gets the board of the game.
     *
     * @return  the board of the game
     * @see     Board
     */
    public Board getBoard ()
    {
        return board;
    }

    /**
     * Gets the wheel of the game.
     *
     * @return  the wheel of the game
     * @see     Wheel
     */
    public Wheel getWheel ()
    {
        return wheel;
    }

    /**
     * Gets the deck of house cards for specific actions.
     *
     * @return  the list (deck) of house cards
     * @see     Card
     * @see     PlayerCard
     * @see     HouseCard
     */
    public ArrayList<Card> getHouseCards ()
    {
        return decks.get (2).getCards ();
    }

    /**
     * Gets the recently drawn card from the specified deck
     * according to action.
     *
     * @return  the recently drawn card
     * @see     Card
     */
    public Card getCardDrawn ()
    {
        // if Action Card or Blue Card (Orange Space or Blue Space)
        for (int k = 0; k < Deck.DECK_ACTIONS.length - 3; k++)
            if (getCurrentSpace ().getType ().equalsIgnoreCase (Deck.DECK_ACTIONS[k]))
                return decks.get (k).getCardDrawn ();

        // if house card
        if (getCurrentSpace ().getMagenta ().equalsIgnoreCase (Deck.DECK_ACTIONS[4]))
            return decks.get (2).getCardDrawn ();

        return null;
    }

    /**
     * Gets the first recent salary card drawn from the
     * salary card deck as specified by action.
     *
     * @return  the first recently drawn salary card
     * @see     Card
     * @see     PlayerCard
     * @see     SalaryCard
     */
    public SalaryCard getSalaryCardDrawn ()
    {
        return (SalaryCard) decks.get (3).getCardDrawn ();
    }

    /**
     * Gets the second recent salary card drawn from the
     * salary card deck as specified by action.
     *
     * @return  the second recently drawn salary card
     * @see     Card
     * @see     PlayerCard
     * @see     SalaryCard
     */
    public SalaryCard getSecondSalaryCardDrawn ()
    {
        return (SalaryCard) decks.get (3).getSecondCardDrawn ();
    }

    /**
     * Gets the first recent career card drawn from the
     * salary card deck as specified by action.
     *
     * @return  the first recently drawn career card
     * @see     Card
     * @see     PlayerCard
     * @see     CareerCard
     */
    public CareerCard getCareerCardDrawn ()
    {
        return (CareerCard) decks.get (4).getCardDrawn ();
    }

    /**
     * Gets the second recent career card drawn from the
     * salary card deck as specified by action.
     *
     * @return  the second recently drawn career card
     * @see     Card
     * @see     PlayerCard
     * @see     CareerCard
     */
    public CareerCard getSecondCareerCardDrawn ()
    {
        return (CareerCard) decks.get (4).getSecondCardDrawn ();
    }

    /**
     * Gets the number of players in this game.
     *
     * @return  the number of players
     */
    public int getNumOfPlayers ()
    {
        return noOfPlayers;
    }

    /**
     * Gets the number of players that are not yet
     * retired in this game.
     *
     * @return  the number of players that are not yet
     *          retired.
     */
    public int getNumOfActivePlayers ()
    {
        return noOfActivePlayers;
    }

    /**
     * Retires the current player having the turn.
     * <p>
     * From retirement the current player will,
     * <p>
     * Get cash according to retirement place, get cash
     * according to how many children the player has, get
     * cash according to the price of the house acquired,
     * and pay all outstanding loan balances with interest.
     * <p>
     * Checks as well if all players have retired to update
     * and mark the game as already finished.
     */
    public void retirePlayer ()
    {
        /* get cash based on retirement place */
        switch (retirePlace) {
        case 1: getCurrentPlayer ().addCash (100000); break;
        case 2: getCurrentPlayer ().addCash (50000); break;
        default: getCurrentPlayer ().addCash (20000);
        }

        /* get $10000 for each child */
        if (getCurrentPlayer ().getStatus ().getFamily () - 1 > 0)
            getCurrentPlayer ().addCash (10000 * (players.get (playerTurn).getStatus ().getFamily () - 1));

        /* get cash from house sold */
        if (getCurrentPlayer ().getStatus ().getHouseCard () != null)
            getCurrentPlayer ().addCash (getCurrentPlayer ().getStatus ().getHouseCard ().getPrice ());

        /*
            pay all outstanding loans with interest, returns false if all outstanding loans have been paid
            or the player cannot pay the loans anymore due to lack of cash
        */
        while (getCurrentPlayer ().payLoan ());

        getCurrentPlayer ().getStatus ().retire (); // player retires

        retirePlace += 1; // for next player that will retire
        noOfActivePlayers -= 1; // decrement number of active players (non-retired players)

        // if all players have retired
        if (retirePlace > noOfPlayers)
            finished = true;
    }

    /**
     * Gets the boolean value indicating if the game is already
     * finished (all players have retired) or not.
     *
     * @return  <code>true</code> if game is already finished;
     *          <code>false</code> otherwise.
     */
    public boolean isFinished ()
    {
        return finished;
    }

    /**
     * Gets the <b>String</b> indicating the winner of the game
     * according to highest amount of cash acquired.
     * <p>
     * If the case occurs wherein atleast two players have the
     * same highest amount of cash, a <b>String</b> indicating
     * that the game ended in a draw is returned instead.
     *
     * @return  the <b>String</b> indicating either the winner
     *          of the game or a draw
     */
    public String getWinner ()
    {
        Player winner = getCurrentPlayer (); // temporary winner
        int highestAmount = getCurrentPlayer ().getCash (); // temporary highest amount
        int i;
        boolean tie = false; // to determine if there is a tie

        for (i = 0; i < noOfPlayers; i++)
            if (players.get (i).getCash () > highestAmount) {
                winner = players.get (i);
                highestAmount = players.get (i).getCash ();
                tie = false;
            } else if (players.get (i).getCash () == highestAmount && !winner.equals (players.get (i)))
                tie = true;

        if (tie)
            return "NONE! ITS A DRAW!";

        return "Winner of the game is " + winner.getName () + " with a total cash of $" + winner.getCash () + "!";
    }
}
