/* JAVAFX LIBRARIES */
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.beans.value.*;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.application.Platform;
/* THATS LIFE! MODEL LIBRARY */
import Model.*;
/* STANDARD JAVA LIBRARIES */
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

/**
 * <b>Controller</b> connects the View class with the Model package for That's Life.
 * <p>
 * An integral part of the MVC program architecture, this Controller class links the
 * View and Model aspects of this implementation. This class is responsible for
 * coordinating what the users see (View) based on the data (Model) the program
 * currently has in store.
 * <p>
 * This class also contains the <code>EventHandlers</code> and the <code>ChangeListeners</code>
 * which are respoonsible for facilitating the GUI interaction in the View class.
 *
 * @author  Joshue Salvador A. Jadie
 * @author  Andre Dominic H. Ponce
 * @version %I%, %G%
 *
 */
public class Controller implements EventHandler<Event>, ChangeListener<String>
{
    /**
     * The GUI (View) of the game.
     */
    View view;
    /**
     * Thats Life! game.
     */
    ThatsLife game;
    /**
     * An integer array that holds the indices of the House Cards to be
     * shown when a player is about to buy a house.
     */
    int[] nums; // for HouseCard indices selection

    /**
     * The sole constructor of this class. Allocates a new Controller
     * object (creates a new controller) with the View object passed
     * to be controlled.
     *
     * @param   view  the GUI object to be controlled
     */
    public Controller (View view)
    {
        this.view = view;
        this.view.setEventHandlers (this);

        nums = new int[3];
    }

    /**
     * Initializes a new ThatsLife (Game) instance and sets the value of essential attributes
     * such as player amount and player names. This also sets the board to show the start junction.
     *
     * @throws  FileNotFoundException if the files needed are not found
     */
    public void setupGame () throws FileNotFoundException
    {
        // INITIALIZE NEW GAME
        game = new ThatsLife ();

        game.setNumOfPlayers (view.getNumOfPlayersInput ());

        /* SET ALL PLAYER NAMES TO EACH PLAYER AT MAX LENGTH OF 15 */
        // FIRST PLAYER
        if (view.getFirstTextField ().getText().length() > 15)
          game.createPlayer (view.getFirstTextField ().getText ().substring (0, 15));
        else game.createPlayer (view.getFirstTextField().getText());
        // SECOND PLAYER
        if (view.getSecondTextField ().getText().length() > 15)
          game.createPlayer (view.getSecondTextField ().getText ().substring (0, 15));
        else game.createPlayer (view.getSecondTextField ().getText());
        // IF 3 PLAYERS, GET FOR THIRD PLAYER AS WELL
        if (game.getNumOfPlayers () > 2) {
            if (view.getThirdTextField ().getText().length() > 15)
                game.createPlayer (view.getThirdTextField ().getText ().substring (0, 15));
            else game.createPlayer (view.getThirdTextField().getText());
        }

        view.updateBoardGUI (game.getPlayers (), game.playerTurn);
    }

    /**
     * Initiates the main game loop, whose main condition is based on if the game
     * is already finished or not. This then calls other methods to execute certain
     * action related to the state of the game (i.e. when a player is on the start
     * junction). This game loop will end once all player have retired in the game.
     * When this is the case, calls a method to show the winner of the game.
     */
    public void startGameLoop ()
    {
        // IF GAME IS NOT YET FINISHED
        if (!game.isFinished ()) {
            // UPDATE BOTTOM PLAYER INFO PANEL IN VIEW
            view.updatePlayerPanel (game.getCurrentPlayer ());

            // IF PLAYER IS AT START TILE JUNCTION
            if (game.getBoard ().isAtStartJunction (game.getCurrentPlayer ().getSpaceNum ()))
                setPlayerStartPath (); // CHOOSE BETWEEN COLLEGE AND CAREER PATH

            // PROCEED TO PROMPTING PLAYER TO SPIN THE WHEEL
            promptPlayerSpinWheel ();

            // START PLAYER MOVEMENT AND ACTION EXECUTION
            try {
                startPlayerMove (moveChecker ());
            } catch (FileNotFoundException e) {
                System.out.println ("Error occurred in game background process! Terminating program...");
                view.exitGame ();
            }
        }
        // ELSE, GET WINNER OF THE GAME THEN SET TO GAME WINNER SCENE
        else view.setWinnerScene (game.getWinner ());
    }

    /**
     * Prompts the player to choose a path at the start of the game. This
     * then executes the actions related to the path chosen by the player (i.e.
     * for Career Path, drawing of Career and Salary Cards). This is always executed
     * at the beginning of the game.
     */
    public void setPlayerStartPath ()
    {
        // SETUP PATH CHOICES THEN SHOW CHOICE WINDOW FOR PLAYER IN-TURN TO CHOOSE
        view.showJunctionChoices ("COLLEGE PATH", "CAREER PATH");

        // IF PLAYER CHOSE CAREER PATH, GET A CAREER CARD WITH NO DEGREE REQUIRED AND A SALARY CARD
        if (game.playerChoice == 2) {
            // REPEAT UNTIL A CAREER CARD WITH NO DEGREE REQUIRED WAS DRAWN
            do {
                game.clearDrawnCards (); // clear drawn-before cards before drawing
                view.showCardDraw ("CAREER"); // show career card draw prompt scene
                game.drawCareerCard (); // draw a career card
                view.showDrawnCard (game.getCareerCardDrawn ()); // show career card drawn

                // IF DRAWN CARD REQUIRES DEGREE
                if (game.getCareerCardDrawn ().isDegreeRequired ())
                    view.showAnnouncement (ViewConfig.ANNOUNCE_TYPES[11], false);
            } while (game.getCareerCardDrawn ().isDegreeRequired ());

            // DRAW A SALARY CARD
            view.showCardDraw ("SALARY"); // show salary card draw prompt scene
            game.drawSalaryCard (); // draw salary card
            view.showDrawnCard (game.getSalaryCardDrawn ());
        }

        // EXECUTE ACTION BASED ON PLAYER CHOICE
        game.executeAction ();
        game.clearDrawnCards ();

        // SET PATH FOR PLAYER TO TRAVEL ACROSS THE BOARD BASED ON CHOICE
        game.playerChoice = game.getBoard ().chosenPath (game.getCurrentPlayer ().getSpaceNum ());
        game.getCurrentPlayer ().move (game.playerChoice);

        // UPDATE BOTTOM PANEL INFO ON IN-GAME SCREEN
        view.updatePlayerPanel (game.getCurrentPlayer ());
    }

    /**
     * Prompts the player to spin the wheel by prompting the View class to
     * show the wheel spin window. This then displays the resulting spin number.
     */
    public void promptPlayerSpinWheel ()
    {
      // PRIMPTS THE PLAYE TO SPIN THE WHEEL
      view.showWheelSpin ();
      // UPDATE AND SHOW RESULTING SPIN NUMBER
      view.showSpinNumber (game.getWheel ().getNumber ());
    }

    /**
     * Handles the player movement on the board. It checks when the player needs to stop moving
     * (i.e. when a player lands on a Magenta Tile or the Retirement Tile). This also handles the
     * reassignment of player space number whenever a player arrives at a junction.
     *
     * @return  the number of tiles a player moved based on provided conditions
     */
    public int moveChecker ()
    {
      // TEMPORARILY STORE CURRENT PLAYER SPACE NUMBER FOR MOVEMENT CHECKING
      int spaceNum = game.getCurrentPlayer ().getSpaceNum ();
      // TOTAL MOVEMENT COUNTER
      int moveCtr = 1;

      // UNTIL SPIN NUMBER HAS BEEN REACHED OR MAGENTA / RETIREMENT SPACE ENCOUNTERED
      do {
          // MOVE TEMPORARY PLAYER SPACE NUMBER
          switch (spaceNum) {
          // COLLEGE CAREER CHOICE (TILE #15) TO NEXT ORANGE TILE (TILE #32)
          case 15: spaceNum = 32; break;
          // ORANGE TILE (TILE #72) TO NEXT ORANGE TILE (TILE #86)
          case 72: spaceNum = 86; break;
          // HAVE CHILREN (TILE #123) TO NEXT BLUE TILE (TILE #140)
          case 123: spaceNum = 140; break;
          // TEMPORARY PLAYER SPACE
          default: spaceNum++;
          }
          // INCREMENT TOTAL MOVEMENT COUNTER
          moveCtr++;
      } while (moveCtr <= game.getWheel ().getNumber () &&
               !game.getBoard ().isAtMagentaSpace (game.getBoard ().getSpace (spaceNum).getType ()) &&
               !game.getBoard ().isAtRetirementSpace (game.getBoard ().getSpace (spaceNum).getType ()));

      // RETURN FINAL MOVEMENT COUNTER FOR MOVEMENT ANIMATION OF PLAYER ON BOARD
      return moveCtr - 1;
    }

    /**
     * This is responsible for the car movement animation seen on the game board. It
     * utilizes the Timeline class in order to create a movement animation. For every
     * cycle of the animation, the player is also being moved from tile to tile. After
     * the animation, the action affiliated with the landing tile is executed.
     *
     * @param   move                  the number of tiles a player will traverse
     * @throws  FileNotFoundException if the files needed are not found
     */
    public void startPlayerMove (int move) throws FileNotFoundException
    {
        Timeline delay = new Timeline (
            new KeyFrame (
                Duration.millis (400),
                e -> {
                    try {
                        game.getCurrentPlayer ().move ();
                        view.updateBoardGUI (game.getPlayers (), game.playerTurn);
                    } catch (FileNotFoundException exception) {
                        System.out.println ("Error in board process! Terminating program...");
                        view.exitGame ();
                    }
                }
            )
        );
        // SET NUMBER OF MOVEMENTS AS PASSED BY moveChecker
        delay.setCycleCount (move);
        delay.setAutoReverse (false);
        // WHEN MOVEMENT ANIMATION IS DONE, EXECUTE ACTION BASED ON SPACE LANDED BY PLAYER IN-TURN
        delay.setOnFinished (e -> Platform.runLater (() -> startPlayerSpaceAction ()));
        // START MOVEMENT ANIMATION OF PLAYER IN-TURN ON BOARD
        delay.play ();
    }

    /**
     * Determines what the game would do based on the tile a certain player lands on.
     * This then calls other methods related to the landing tile in order to executed the
     * affiliated game actions. This also updates the player information panel found in the GUI.
     */
    public void startPlayerSpaceAction ()
    {
        // IF MAGENTA SPACE, SET ANNOUNCEMENT IMAGE TO SHOW "STOP"
        if (game.getBoard ().isAtMagentaSpace (game.getCurrentSpace ().getType ()))  // MAGENTA SPACE
            view.showAnnouncement (ViewConfig.ANNOUNCE_TYPES[6], true);

        // DISPLAY SPACE LANDED ON BY CURRENT PLAYER
        view.showAnnouncement (game.getCurrentSpace ());

        // IF GREEN SPACE (PAY DAY OR PAY RAISE), SET ANNOUNCEMENT IMAGE TO SHOW
        if (game.getCurrentSpace ().getType ().equalsIgnoreCase (Space.TYPES[0])) // PAY DAY
          view.showAnnouncement (ViewConfig.ANNOUNCE_TYPES[4], true);
        else if (game.getCurrentSpace ().getType ().equalsIgnoreCase (Space.TYPES[1])) // PAY RAISE
          view.showAnnouncement (ViewConfig.ANNOUNCE_TYPES[5], true);

        // IF PLAYER IN-TURN LANDED ON RETIREMENT SPACE
        if (game.getBoard ().isAtRetirementSpace (game.getCurrentSpace ().getType ()))
            startRetirementAction ();
        // IF PLAYER IN-TURN LANDED ON MAGENTA SPACE
        else if (game.getBoard ().isAtMagentaSpace (game.getCurrentSpace ().getType ()))
            startMagentaAction ();
        // IF PLAYER IN-TURN LANDED ON GREEN SPACE (PAY DAY OR PAY RAISE)
        else if (game.getBoard ().isAtGreenSpace (game.getCurrentSpace ().getType ()))
            startGreenAction ();
        // IF PLAYER IN-TURN LANDED ON BLUE SPACE
        else if (game.getBoard ().isAtBlueSpace (game.getCurrentSpace ().getType ()))
            startBlueAction ();
        // ELSE, PLAYER IN-TURN LANDED ON ORANGE SPACE
        else startOrangeAction ();

        // RESET GAME SYSTEM VARIABLES AFTER ACTION
        game.playerChoice = 0;
        game.clearDrawnCards ();

        // UPDATE BOTTOM PANEL PLAYER INFO ON VIEW AFTER ACTION
        view.updatePlayerPanel (game.getCurrentPlayer ());

        // GO BACK TO START OF GAME LOOP
        startGameLoop ();
    }

    /**
     * Retires the current playing player and will show their life summary as well.
     * This is executed whenever a player reaches the retirement tile.
     */
    public void startRetirementAction ()
    {
        game.retirePlayer (); // retire player in-turn
        view.showLifeSummary (game.getCurrentPlayer ()); // show player life stats summary after retirement

        // PROMPT NEXT PLAYER TURN
        startNextPlayerTurn ();
    }

    /**
     * Executes the Magenta Action related to the Magenta Tile that a certain player landed on.
     * This will call specific methods to execute the Magenta Action involved with the tile.
     */
    public void startMagentaAction ()
    {
        // IF "WHICH PATH?" ACTION -> CAREER / FAMILY PATH JUNCTION
        if (game.getBoard ().isAtMagentaJunction (game.getCurrentSpace ().getMagenta ()))
            setPlayerJunctionPath ();
        // IF "GET MARRIED" ACTION -> REQUIRES SPIN; CHECKS AS WELL IF PLAYER IS ALREADY MARRIED OR NOT
        else if (game.isActionRequireSpin ())
            startGetMarried ();
        // IF "HAVE CHILDREN" ACTION -> GET A BABY OR TWIN BASED ON SPIN NUMBER ACQUIRED BEFORE
        else if (game.getBoard ().isHaveChildrenSpace (game.getCurrentSpace ().getMagenta ()))
            startHaveChildren ();
        // IF "COLLEGE CAREER CHOICE" ACTION -> CHOOSE BETWEEN TWO SETS OF CAREER AND SALARY CARDS
        else if (game.getBoard ().isCareerChoiceSpace (game.getCurrentSpace ().getMagenta ()))
            startCareerChoice ();
        // IF "JOB SEARCH" ACTION -> CHOOSE TO CHANGE CAREER AND SALARY CARDS OR KEEP
        else if (game.getBoard ().isJobSearchSpace (game.getCurrentSpace ().getMagenta ()))
            startJobSearch ();
        // IF "BUY HOUSE" ACTION -> CHOOSE BETWEEN 3 HOUSE CARDS TO BUY
        else if (game.getBoard ().isBuyHouseSpace (game.getCurrentSpace ().getMagenta ()))
            startBuyHouse ();
        // ELSE, "GRADUATION SPACE" ACTION -> GRADUATE CURRENT PLAYER
        else game.executeAction ();
    }

    /**
     * Prompts the View class to show the junction selection window. This is executed
     * when a player arrives at a junction (except for the start junction). This will
     * prompt the user to select which path to take.
     */
    public void setPlayerJunctionPath ()
    {
        // IF CAREER PATH JUNCTION
        if (game.getBoard ().isAtCareerJunction (game.getCurrentPlayer ().getSpaceNum ()))
            view.showJunctionChoices ("CHANGE CAREER", "KEEP CURRENT CAREER");
        // ELSE, FAMILY PATH JUNCTION
        else view.showJunctionChoices ("START A FAMILY", "DON'T START A FAMILY");

        game.playerChoice = game.getBoard ().chosenPath (game.getCurrentPlayer ().getSpaceNum ());
        game.getCurrentPlayer ().move (game.playerChoice);
    }

    /**
     * Executes the Magenta Action of Get Married. This is executed whenver
     * a player lands on the Get Married space. This will change a player's marriage
     * status.
     */
    public void startGetMarried ()
    {
        // IF PLAYER IS ALREADY MARRIED, DON'T DO SPIN ACTION
        if (!game.getCurrentPlayer ().getStatus ().isMarried ()) {
            // PROMPT PLAYER TO SPIN THE WHEEL TO DETERMINE WEDDING GIFT FROM ALL PLAYERS
            view.showWheelSpin ();

            // SHOW CASH TO BE ACQUIRED FROM ALL PLAYERS BASED ON SPIN NUMBER ACQUIRED
            // DISPLAY ANNOUNCEMENT DEPENDING ON SPIN NUMBER ACQUIRED
            if (game.getWheel ().getNumber () % 2 != 0) // IF ODD, THEN GET $5000 FROM ALL PLAYERS
                view.showSpinCash (5000, game.getCurrentSpace ());
            else // ELSE, IT IS EVEN AND $10000 FROM ALL PLAYERS
                view.showSpinCash (10000, game.getCurrentSpace ());
        }

        // IF executeAction RETURNED FALSE, PLAYER IS ALREADY MARRIED
        if (!game.executeAction ())
            view.showAnnouncement (ViewConfig.ANNOUNCE_TYPES[7], false);
    }

    /**
     * Executes the Magenta Action of Have Children. This is executed whenever
     * a player lands on the Have Children space. This will increase the number of
     * pegs a player has, if and only if they are married.
     */
    public void startHaveChildren ()
    {
        // IF PLAYER IS NOT MARRIED, DON'T DO ACTION
        if (game.getCurrentPlayer ().getStatus ().isMarried ()) {
            // DETERMINE CASH TO BE ACQUIRED FROM ALL PLAYERS, DEPENDING ON NUMBER OF CHILDREN
            // DISPLAY ANNOUNCEMENT
            view.showAnnouncement (
                game.getWheel ().getNumber () % 2 + 1,
                game.getCurrentPlayer ().getStatus ().getFamily () - 1
            );
        }

        // IF executeAction RETURNED FALSE, EITHER MAX CHILDREN HAS BEEN REACHED OR PLAYER IS NOT MARRIED
        if (!game.executeAction ()) {
            if (game.getCurrentPlayer ().getStatus ().isMarried ()) // max children
              view.showAnnouncement (ViewConfig.ANNOUNCE_TYPES[8], false);
            else // not married, no children
              view.showAnnouncement (ViewConfig.ANNOUNCE_TYPES[9], false);
        }
    }

    /**
     * Executes the Magenta Action of Career Selection. This is executed whenever
     * a player lands on the Career Choice space. This will prompt the player to select
     * a pair of Career and Salary card given two choices.
     */
    public void startCareerChoice ()
    {
        // DRAW 2 CAREER AND SALARY CARDS
        for (int i = 0; i < 2; i++) {
            game.drawCareerCard ();
            game.drawSalaryCard ();
        }

        // DISPLAY CHOICES
        view.showTwoCardsChoices (
            game.getCareerCardDrawn (), game.getSalaryCardDrawn (),
            game.getSecondCareerCardDrawn (), game.getSecondSalaryCardDrawn ()
        );

        game.executeAction ();
    }

    /**
     * Executes the Magenta Action of Job Search. This is executed whenever
     * a player lands on the Job Search space. This will prompt the player to select
     * if they want to change or keep their current career and salary.
     */
    public void startJobSearch ()
    {
        // IF PLAYER HAS A DEGREE, DRAW ANY CAREER CARD
        if (game.getCurrentPlayer ().getStatus ().hasDegree ())
            game.drawCareerCard ();
        // ELSE, DRAW CAREER CARDS WITH NO DEGREE
        else game.drawNoDegreeCareer ();
        // DRAW SALARY CARD
        game.drawSalaryCard ();

        // DISPLAY CHOICES
        view.showTwoCardsChoices (
            game.getCareerCardDrawn (), game.getSalaryCardDrawn (),
            game.getCurrentPlayer ().getStatus ().getCareerCard (),
            game.getCurrentPlayer ().getStatus ().getSalaryCard ()
        );

        game.executeAction ();
    }

    /**
     * Executes the Magenta Action of buying a house. This is executed whenever
     * a player lands on the Buy a House space. This will present the user with the
     * house selection GUI from the View class.
     */
    public void startBuyHouse ()
    {
        // GET HOUSE CARD INDICES TO BE CHOSEN BY PLAYER
        getHouseIndices ();

        // DISPLAY HOUSE CARD CHOICES WINDOW
        view.showHouseCardSelection (game.getHouseCards (), nums);

        // IF executeAction RETURNED FALSE, PLAYER BORROWED MONEY FROM BANK TO BUY HOUSE
        if (!game.executeAction ())  // check if returned false [events stated above]
            view.showAnnouncement (ViewConfig.ANNOUNCE_TYPES[2], false);
    }

    /**
     * Executes Green Tile-related actions. This is executed whenever a certain
     * player lands on a Green space. This will either execute Pay Day or Pay Raise.
     */
    public void startGreenAction ()
    {
        // IF executeAction RETURNED FALSE, DISPLAY MAX PAY RAISE WINDOW
        if (!game.executeAction ()) {
            // IF PLAYER HAS A CAREER CARD, PAY RAISE CAN BE DONE
            if (game.getCurrentPlayer ().getStatus ().getCareerCard () != null)
                view.showAnnouncement (ViewConfig.ANNOUNCE_TYPES[0], false);
            // ELSE, PLAYER HAS NO CAREER CARD THEREFORE CANNOT HAVE PAY RAISE
            else view.showAnnouncement (ViewConfig.ANNOUNCE_TYPES[10], false);
        }

        // UPDATE BOTTOM PLAYER PANEL INFO ON SCREEN AFTER ACTION
        view.updatePlayerPanel (game.getCurrentPlayer ());

        // PROMPT NEXT PLAYER TURN
        startNextPlayerTurn ();
    }

    /**
     * Executes Blue Tile-related actions. This is executed whenever a certain
     * player lands on a Blue space. This will prompt the user to draw a Blue Card.
     * The effect of the Blue Card is executed here as well.
     */
    public void startBlueAction ()
    {
        // PROMPT PLAYER TO DRAW A CARD
        view.showCardDraw ("BLUE");

        // SHOW AND DISPLAY CARD DRAWN
        view.showDrawnCard (game.getCardDrawn ());

        // UPDATE LEFT CARD PANEL
        view.updateCardPanel (game.getCardDrawn ());

        // IF PLAYER MATCHED THE CAREER STATED IN THE BLUE CARD DRAWN
        if (game.isMatchingCareer ())
            view.showAnnouncement (ViewConfig.ANNOUNCE_TYPES[3], false);
        else {
            // FIND PLAYER WITH CAREER; IF -1 WAS RETURNED, PAY THE BANK INSTEAD
            game.findPlayerWithCareer ();

            // IF "TIP THE SERVER" OR "COMPUTER REPAIR" ACTION, PROMPT PLAYER TO SPIN
            if (game.isActionRequireSpin ()) {
                // PROMPT PLAYER TO SPIN THE WHEEL
                view.showWheelSpin ();
                // DISPLAY ANNOUNCEMENT BASED ON ACTION AND SPIN NUMBER ACQUIRED

                // SHOW CASH TO PAY BASED FROM RESULT
                // IF "COMPUTER REPAIR" ACTION
                if (((BlueCard) game.getCardDrawn ()).getName ().equalsIgnoreCase (BlueCard.ACTIONS[2])) {
                    // IF EVEN, PAY $5000
                    if (game.getWheel ().getNumber () % 2 == 0)
                        view.showSpinCash (5000, game.getCurrentSpace ());
                    // OTHERWISE, SPIN NUMBER IS ODD AND PAY $10000
                    else view.showSpinCash (10000, game.getCurrentSpace ());
                }
                // ELSE, "TIP THE SERVER" ACTION
                else view.showSpinCash (game.getWheel ().getNumber () * 1000, game.getCurrentSpace ());
            }

            // DISPLAY ENTITY (PLAYER OR BANK) TO BE PAID
            view.showAnnouncement (game.getPlayers(), game.playerChoice);
        }

        // IF executeAction RETURNED FALSE, PLAYER BORROWED MONEY FROM THE BANK TO PAY
        if (!game.executeAction ())
            view.showAnnouncement (ViewConfig.ANNOUNCE_TYPES[2], false);

        // UPDATE BOTTOM PLAYER PANEL INFO ON SCREEN AFTER ACTION
        view.updatePlayerPanel (game.getCurrentPlayer ());

        // PROMPT NEXT PLAYER TURN
        startNextPlayerTurn ();
    }

    /**
     * Executes Orange Tile-related actions. This is executed whenever a
     * certain player lands on an Orange space. This will prompt the player to
     * draw an Action Card. The effect of the Action Card is executed here as well.
     */
    public void startOrangeAction ()
    {
        // PROMPT PLAYER TO DRAW A CARD
        view.showCardDraw ("ACTION");

        // SHOW AND DISPLAY CARD DRAWN
        view.showDrawnCard (game.getCardDrawn ());

        // UPDATE LEFT CARD PANEL
        view.updateCardPanel (game.getCardDrawn ());

        // IF "PAY TO PLAYER" OR "CASH FROM PLAYER" ACTION
        if (game.isActionRequirePlayer ())
            // IF NUMBER OF PLAYERS IS GREATER THAN 2, PLAYER IN-TURN WILL CHOOSE PLAYERS
            if (game.getNumOfActivePlayers () > 2) {
                view.showPlayerChoices (game.getPlayers (), game.playerTurn);
            }

        // IF executeAction RETURNED FALSE, PLAYER BORROWED MONEY FROM THE BANK TO PAY
        if (!game.executeAction ())
            view.showAnnouncement (ViewConfig.ANNOUNCE_TYPES[2], false);

        // UPDATE NEW INFORMATION ABOUT CURRENT PLAYER AFTER ACTION
        view.updatePlayerPanel (game.getCurrentPlayer ());

        // PROMPT NEXT PLAYER TURN
        startNextPlayerTurn ();
    }

    /**
     * Shifts the turn to the next player, if the game is not yet finished.
     */
    public void startNextPlayerTurn ()
    {
        // IF NOT ALL PLAYERS HAVE RETIRED YET
        if (!game.isFinished ()) {
            view.showAnnouncement (ViewConfig.ANNOUNCE_TYPES[1], false);
            try {
                view.updateBoardGUI (game.getPlayers (), game.playerTurn);
            } catch (FileNotFoundException exception) {
                System.out.println ("Error in board process! Terminating program...");
                view.exitGame ();
            }
        }
    }

    /**
     * Handles all the buttons and other pressable nodes in the View class.
     *
     * @param event the event which occured
     */
    @Override
    public void handle (Event event)
    {
      // IF EVENT ORIGINATED FROM BUTTON
      if (event.getSource () instanceof Button) {
        switch (((Button) event.getSource()).getId()) {
          // START BUTTON from Main Menu
          case "startNewGameBtn":
              view.setGameSetScene();
              view.getStage().centerOnScreen(); // center main window on screen
              break;
          // START BUTTON from Game Setup
          case "startGameBtn":
              try {
                setupGame ();
                view.setInGameScene ();
                view.getStage().centerOnScreen (); // center main window on screen
                startGameLoop (); // start That's Life! game
              } catch (FileNotFoundException e) {
                 System.out.println ("Error in game setup!");
                 view.exitGame ();
              }
              break;
          // SEE INSTRUCTIONS BUTTON from Main Menu
          case "seeInstructionsBtn":
              view.setInstructionsScene();
              view.getStage().centerOnScreen(); // center main window on screen
              break;
          // EXIT GAME BUTTON from Main Menu
          case "exitGameBtn":
              view.exitGame();
              break;
          // MAIN MENU BUTTONs found accross the GUI
          case "mainMenuBtn":
          case "backMenuBtn":
          case "winnerMenuBtn":
              view.setMainMenuScene();
              view.getStage().centerOnScreen(); // center main window on screen
              break;
          // PREVIOUS BUTTON for Instructions
          case "prevInstructionBtn":
              view.previousInstructionPage();
              view.updateInstructionButtons();
              break;
          // NEXT BUTTON for Instructions
          case "nextInstructionBtn":
              view.nextInstructionPage();
              view.updateInstructionButtons();
              break;
          // CONFIRM BUTTONS for Game Setup
          case "confirmNumBtn":
          case "confirmNameBtn":
              view.nextGameSetScene();
              break;
          // BACK BUTTON for Game Setup
          case "backBtn":
              view.prevGameSetScene();
              break;
          // LEFT PATH CHOICEs for junctions
          case "COLLEGE PATH":
          case "CHANGE CAREER":
          case "START A FAMILY":
              game.playerChoice = 1;
              view.closeJunctionWindow();
              break;
          // RIGHT PATH CHOICEs for junctions
          case "CAREER PATH":
          case "KEEP CURRENT CAREER":
          case "DON'T START A FAMILY":
              game.playerChoice = 2;
              view.closeJunctionWindow ();
              break;
          // OKAY BUTTON for announcements
          case "announceOkBtn":
              view.closeAnnouncement ();
              break;
          // OKAY BUTTON for spin number results
          case "spinNumberOkay":
              view.closeSpinNumber ();
              view.closeWheelSpin ();
              break;
          // OKAY BUTTON for post-card draw
          case "drawnCardOkay":
              view.closeDrawnCard ();
              break;
          // RESHUFFLE DECK BUTTON for card draw
          case "reshuffleBtn":
              try {
                game.shuffleDecks ();
                view.showAnnouncement (ViewConfig.ANNOUNCE_TYPES[12], false);
              } catch (Exception e) {
                System.out.println ("Close the window first! " + e.toString ());
              }
              break;
          // DRAW BUTTON for card draw
          case "drawBtn":
              if (game.getBoard ().isAtOrangeSpace(game.getCurrentSpace ().getType ()))
                game.drawActionCard ();
              else if (game.getBoard ().isAtBlueSpace(game.getCurrentSpace ().getType()))
                game.drawBlueCard ();
              view.closeCardDraw ();
              break;
          // CHOOSE FIRST PLAYER BUTTON
          case "playerOne":
              if (game.playerTurn != 0)
                  game.playerChoice = 1;
              else game.playerChoice = 2;
              view.closePlayerChoices();
              break;
          // CHOOSE SECOND PLAYER BUTTON
          case "playerTwo":
              if (game.playerTurn != 2)
                game.playerChoice = 3;
              else game.playerChoice = 2;
              view.closePlayerChoices();
              break;
          // OKAY BUTTON for life summary in retirement
          case "summaryOkayBtn":
              view.closeLifeSummary();
              break;
          // CHOOSE FIRST SET OF CARDS for Career & Salary Card selection
          case "firstSetBtn": // SELECT FIRST SET OF CARDS (CAREER & SALARY)
              game.playerChoice = 1;
              view.closeTwoCardsChoices();
              break;
          // CHOOSE SECOND SET OF CARDS for Career & Salary Card selection
          case "secondSetBtn":
              game.playerChoice = 2;
              view.closeTwoCardsChoices();
              break;
          // OKAY BUTTON for next player turn prompt
          case "nextPlayerTurnBtn":
              do {
                  game.nextPlayer ();
              } while (game.getCurrentPlayer ().getStatus ().isRetired ());
              view.closeAnnouncement ();
              break;
          // EXIT BUTTON for in-game scene
          case "mainMenuExitBtn":
              view.showExitAlertBox();
              break;
          // YES BUTTON for exit alert box
          case "yesExitBtn":
              view.exitGame();
              break;
          // NO BUTTON for exit alert box
          case "noExitBtn":
              view.closeExitAlertBox();
              break;
        }
      }
      // IF EVENT ORIGINATED FROM STACK PANE
      else if (event.getSource() instanceof StackPane) {
        // FOR HOUSE CARD SELECTION
        switch (((StackPane) event.getSource()).getId()) {
          // FIRST HOUSE CARD (LEFTMOST)
          case "House 1":
              game.drawHouseCard (nums[0]);
              break;
          // SECOND HOUSE CARD (MIDDLE CARD)
          case "House 2":
              game.drawHouseCard (nums[1]);
              break;
          // THIRD HOURSE CARD (RIGHTMOST)
          case "House 3":
              game.drawHouseCard (nums[2]);
              break;
        }
        view.closeHouseWindow ();
      }
      // IF EVENT ORIGINATED FROM IMAGE VIEW
      else if (event.getSource () instanceof ImageView) {
        // FOR WHEEL SPIN
        // SPIN WHEEL TO GENERATE NUMBER
        game.getWheel ().spin ();
        view.closeWheelSpin ();
      }
      // IF EVENET ORIGINATED FROM STAGE
      else if (event.getSource () instanceof Stage) {
        // CONSUME EXIT ATTEMPT
        event.consume ();
      }
    }

    /**
     * Generates randomly three integers to be used in the House Card selection.
     */
    private void getHouseIndices ()
    {
      ArrayList<Card> tempHouseCards = game.getHouseCards();
      Random randomizer = new Random ();
      boolean valid;
      int num, ctr = 0, i;

      // RESET VALUE OF ARRAY
      for (i = 0; i < nums.length; i++)
        nums[i] = -1;

      do {
        valid = true;
        // GENERATE RANDOM INTEGER
        num = randomizer.nextInt(HouseCard.NAMES.length);
        // CHECK IF RANDOM INTEGER IS EXISTING ALREADY
        for (i = 0; i < nums.length; i++)
          if (num == nums[i])
              valid = false;
        // CHECK IF THE RANDOM INDEX IS OWNED
        if (((PlayerCard) tempHouseCards.get(num)).isOwned())
          valid = false;
        // SAVE RANDOM INTEGER
        if (valid) {
          nums[ctr] = num;
          ctr+=1;
        }
      } while (ctr != 3);
      // WHILE INTEGERS GENERATED IS LESS THAN 3
    }

    /**
     * Checks for changes in text input GUI elements.
     *
     * @param observableValue the ObservableValue which value changed
     * @param s               the old value
     * @param t1              the new value
     */
    @Override
    public void changed (ObservableValue<? extends String> observableValue, String s, String t1)
    {
        if (t1.length () > 0)
            view.setDisablePlayerNameBtn (false);
        else view.setDisablePlayerNameBtn (true);
    }
}
