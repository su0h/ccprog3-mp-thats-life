/* JAVAFX LIBRARIES */
import javafx.application.*;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.geometry.*;
/* STANDARD LIBRARIES */
import java.io.*;
import java.util.*;
/* THATS LIFE! MODEL LIBRARY */
import Model.*;

/**
 * <b>View</b> contains the GUI aspect of this implementation of That's Life.
 * <p>
 * This class contains all of the GUI elements that will be utilized whenever
 * one plays That's Life. In order to achieve this, this class coordinates with the Model
 * and Controller classes in implementing an MVC program architecture. JavaFX was used to
 * implement the GUI elements.
 * <p>
 * For the in-game board of this implementation of That's Life, it was implemented as a
 * 3x3 Grid Pane such that only specific parts of the board will be displayed at a time. It
 * was done so in order for the users to easily understand their placement on the board.
 * <p>
 * <i><b> Sources for some assets used:</b>
 * "Flat Car Collection with side view" by freepik
 * Taken from https://www.freepik.com/vectors/car
 *
 * "scorpio | Tumblr - Sky GIF" by -trw-<br>
 * Taken from https://www.pinterest.ph/pin/529102656205490828/<br>
 * <br>
 * "Rainbow Gif" by KirboA<br>
 * Taken from https://www.pixilart.com/art/rainbow-gif-65db0081e38d1b5<br>
 * <br>
 * "Transparent Confetti" by Direskin<br>
 * Taken from https://gifer.com/en/5Q17<br>
 * <br>
 * "animated-sea-and-ocean-image-0014" from animatedimages.org<br>
 * Taken from https://www.animatedimages.org/data/media/1513/animated-sea-and-ocean-image-0014.gif<br>
 * </i>
 *
 * @author  Joshue Salvador A. Jadie
 * @author  Andre Dominic H. Ponce
 * @version %I%, %G%
 */
public class View extends Application implements ViewConfig, BoardConfig
{
    /**
     * The main window of the game.
     */
    private Stage mainStage;

    /* MAIN MENU SCENE ATTRIBUTES */
    /**
     * The main menu scene of the game.
     */
    private Scene mainMenuScene;
    /**
     * The start game button implemented in the main menu scene.
     */
    private Button startBtn;
    /**
     * The see instructions button implemented in the main menu scene.
     */
    private Button instructionsBtn;
    /**
     * The exit game button implemented in the main menu scene.
     */
    private Button exitBtn;

    /* INSTRUCTIONS SCENE ATTRIBUTES */
    /**
     * The instructions scene of the game.
     */
    private Scene instructionsScene;
    /**
     * The main menu button implemented in the instructions scene.
     */
    private Button mainMenuBtn;
    /**
     * The previous button implemented in the instructions scene.
     */
    private Button prevBtn;
    /**
     * The next button implemented in the instructions scene.
     */
    private Button nextBtn;
    /**
     * The instructions to be printed in the instructions scene.
     */
    private Text instructions;
    /**
     * The page number to be displayed in the instructions scene.
     */
    private Text tipsPage;
    /**
     * Page number counter variable for the instructions scene.
     */
    private int instructionsCtr;

    /* GAME SETUP SCENES ATTRIBUTES [NUMBER OF PLAYERS SETUP SCENE AND PLAYER NAME SETUP SCENE] */
    /**
     * The game setup scene of the game.
     */
    private Scene gameSetScene;
    /**
     * The input layout where information will be entered.
     */
    private VBox gameSetInputLayout;
    /**
     * The button layout at the bottom of the game setup scene.
     */
    private HBox gameSetButtonLayout;
    /**
     * The confirm amount of players button in the game setup scene.
     */
    private Button confirmNumBtn;
    /**
     * The confirm name button implemented in the game setup scene.
     */
    private Button confirmNameBtn;
    /**
     * The start game button implemented in the game setup scene.
     */
    private Button startGameBtn;
    /**
     * The back button implemented in the game setup scene.
     */
    private Button backBtn;
    /**
     * The go back to main menu button implemented in the game setup scene.
     */
    private Button backMenuBtn;
    /**
     * The player amount combo box implemented in the game setup scene.
     */
    private ComboBox<Integer> numOfPlayersBox;
    /**
     * The player amount label implemented in the game setup scene.
     */
    private Label numOfPlayersLbl;
    /**
     * The player name labels implemented in the game setup scene.
     */
    private ArrayList<Label> playerLbls;
    /**
     * The player name text fields implemented in the game setup scene.
     */
    private ArrayList<TextField> playerTextFields;
    /**
     * Page number counter variable for the game setup scene.
     */
    private int gameSetPageCtr;

    /* IN-GAME SCENE ATTRIBUTES */
    /**
     * The in-game scene of the game.
     */
    private Scene inGameScene;
    /**
     * The main layout for the in-game scene
     */
    private BorderPane inGameLayout;
    /* LEFT IN-GAME SCENE PANEL ATTRIBUTES */
    /**
     * The card panel label for the in-game scene.
     */
    private Text cardPanelLabel;
    /**
     * The name of the card to be displayed.
     */
    private Text cardName;
    /**
     * The description of the card to be displayed.
     */
    private Text cardDesc;
    /**
     * The value of the card to be displayed (if it exists).
     */
    private Text cardVal;
    /**
     * The card information layout. This contains the Text attributes of a card.
     */
    private VBox cardTextDetails;
    /**
     * The information layout of the card to be displayed.
     */
    private StackPane cardDetailsLayout;
    /**
     * The card display main layout. This integrates the image of the card
     * with its corresponding description.
     */
    private VBox cardMainLayout;
    /**
     * The image of the back of a BlueCard.
     */
    private ImageView blueCardFront;
    /**
     * The image of the back of an ActionCard.
     */
    private ImageView actionCardFront;
    /**
     * The image of the back of a default card.
     */
    private ImageView defaultCardFront;
    /* RIGHT IN-GAME PANEL ATTRIBUTES */
    /**
     * The career of the current player playing (if it exists).
     */
    private Text playerCareer;
    /**
     * The salary of the current player playing (if it exists).
     */
    private Text playerSalary;
    /**
     * The tax of the current player playing (if it exists).
     */
    private Text playerTax;
    /**
     * The name of the house of the current player playing (if it exists).
     */
    private Text playerHouse;
    /**
     * Marriage status of the current player playing.
     */
    private Text playerIsMarried;
    /**
     * The amount of children of the current player playing.
     */
    private Text playerChildren;
    /**
     * Degree status of the current player playing.
     */
    private Text playerDegree;
    /* BOTTOM IN-GAME PANEL ATTRIBUTES */
    /**
     * The name of the current playing player.
     */
    private Text playerName;
    /**
     * The amount of cash of the current player playing.
     */
    private Text playerCash;
    /**
     * The amount of loan of the current player playing.
     */
    private Text playerLoanBalance;
    /**
     * The exit button used to properly exit the game while in-game.
     */
    private Button mainMenuExitBtn;

    /* HOUSE CARD SELECTION ATTRIBUTES */
    /**
     * The stage of the house card selection window.
     */
    private Stage houseCardStage;
    /**
     * The name of the first house to be displayed.
     */
    private Text firstHouseName;
    /**
     * The price of the first house to be displayed.
     */
    private Text firstHousePrice;
    /**
     * The description of the first house to be displayed.
     */
    private Text firstHouseDescription;
    /**
     * The name of the second house to be displayed.
     */
    private Text secondHouseName;
    /**
     * The price of the second house to be displayed.
     */
    private Text secondHousePrice;
    /**
     * The description of the second house to be displayed.
     */
    private Text secondHouseDescription;
    /**
     * The name of the third house to be displayed.
     */
    private Text thirdHouseName;
    /**
     * The price of the third house to be displayed.
     */
    private Text thirdHousePrice;
    /**
     * The description of the third house to be displayed.
     */
    private Text thirdHouseDescription;
    /**
     * The card layout of the first HouseCard.
     */
    private StackPane firstCard;
    /**
     * The card layout of the second HouseCard.
     */
    private StackPane secondCard;
    /**
     * The card layout of the third HouseCard.
     */
    private StackPane thirdCard;

    /* JUNCTION SELECTION ATTRIBUTES  */
    /**
     * The stage of the junction selection scene.
     */
    private Stage junctionChoiceStage;
    /**
     * Button for first junction.
     */
    private Button choiceOne;
    /**
     * Button for the second junction.
     */
    private Button choiceTwo;

    /* WHEEL SPIN ATTRIBUTES */
    /**
     * The stage of the wheel spin scene.
     */
    private Stage wheelStage;
    /**
     * The image of the wheel pointer to be clicked to spin.
     */
    private ImageView wheelPointer;
    /**
     * The stage of the resulting spin number or amount.
     */
    private Stage spinNumberStage;
    /**
     * The resulting spin number or amount.
     */
    private Text spinNumber;
    /**
     * Ok button for spin number scene.
     */
    private Button spinNumberBtn;

    /* DRAWING OF CARDS ATTRIBUTES */
    /**
     * The stage of the draw a card scene.
     */
    private Stage drawCardStage;
    /**
     * The main layout of the draw a card scene.
     */
    private VBox drawCardMainLayout;
    /**
     * The image of the back of an ActionCard.
     */
    private ImageView actionCardBack;
    /**
     * The image of the back of a BlueCard.
     */
    private ImageView blueCardBack;
    /**
     * The image of the back of a CareerCard.
     */
    private ImageView careerCardBack;
    /**
     * The image of the back of a SalaryCard.
     */
    private ImageView salaryCardBack;
    /**
     * The button for reshuffling the decks.
     */
    private Button reshuffleBtn;
    /**
     * The button for drawing card.
     */
    private Button drawBtn;

    /* SHOWING OF CARD DRAWN ATTRIBUTES */
    /**
     * The stage of the showing of card drawn window.
     */
    private Stage drawnCardStage;
    /**
     * The main layout of the showing of card drawn scene.
     */
    private VBox drawnCardMainLayout;
    /**
     * The layout for the details of the card drawn.
     */
    private VBox drawnCardDetails;
    /**
     * The card layout of the drawn card.
     */
    private StackPane drawnCardLayout;
    /**
     * Okay button for the showing of card drawn scene.
     */
    private Button drawnCardOkayBtn;
    /**
     * The title for the showing of card drawn scene.
     */
    private Text drawnCardTitle;
    /**
     * The image of the front of an ActionCard.
     */
    private ImageView drawnActionFront;
    /**
     * The image of the front of a BlueCard.
     */
    private ImageView drawnBlueFront;
    /**
     * The image of the front of a CareerCard.
     */
    private ImageView drawnCareerFront;
    /**
     * The image of the front of a SalaryCard.
     */
    private ImageView drawnSalaryFront;
    /**
     * The name of the card drawn.
     */
    private Text drawnCardName;
    /**
     * The value of the card drawn (if it exists).
     */
    private Text drawnCardValue;
    /**
     * The description of the card drawn.
     */
    private Text drawnCardDesc;

    /* PLAYER SELECTION ATTRIBUTES */
    /**
     * The stage of the player selection window.
     */
    private Stage choosePlayerStage;
    /**
     * The button for the first player choice.
     */
    private Button playerOneBtn;
    /**
     * The button for the second player choice.
     */
    private Button playerTwoBtn;

    /* LIFE SUMMARY ATTRIBUTES */
    /**
     * The stage for the life summary window.
     */
    private Stage lifeSummaryStage;
    /**
     * Okay button for the life sumnmary scene.
     */
    private Button summaryOkayBtn;
    /**
     * Name of the player whose life summary is being shown.
     */
    private Text playerLifeName;
    /**
     * Cash amount of the player whose life summary is being shown.
     */
    private Text playerLifeCash;
    /**
     * Loan amount of the player whose life summary is being shown.
     */
    private Text playerLifeLoan;
    /**
     * Career of the player whose life summary is being shown.
     */
    private Text playerLifeCareer;
    /**
     * Tax of the player whose life summary is being shown.
     */
    private Text playerLifeTax;
    /**
     * House name of the player whose life summary is being shown.
     */
    private Text playerLifeHouse;
    /**
     * Marriage status of the player whose life summary is being shown.
     */
    private Text playerLifeMarried;
    /**
     * Amount of children of the player whose life summary is being shown.
     */
    private Text playerLifeFamily;

    /* CAREER CARD and SALARY SELECTION ATTRIBUTES */
    /**
     * The stage of the Career and Salary Card selection window.
     */
    private Stage chooseTwoCardsStage;
    /**
     * The title of the Career and Salary Card selection scene.
     */
    private Text chooseTwoCardsTitle;
    /**
     * Button for choosing the first set of cards.
     */
    private Button firstSetBtn;
    /**
     * Button for choosing the second set of cards.
     */
    private Button secondSetBtn;
    /**
     * The name of the first CareerCard.
     */
    private Text firstCareerName;
    /**
     * The description of the second CareerCard.
     */
    private Text firstCareerDesc;
    /**
     * The salary of the first SalaryCard.
     */
    private Text firstSalaryVal;
    /**
     * The name of the second CareerCard.
     */
    private Text secondCareerName;
    /**
     * The description of the second CareerCard.
     */
    private Text secondCareerDesc;
    /**
     * The salary of the second SalaryCard.
     */
    private Text secondSalaryVal;

    /* GAME WINNER SCENE ATTRIBUTES */
    /**
     * The scene for announcement of game winner.
     */
    private Scene winnerScene;
    /**
     * The text that states the name of the winner.
     */
    private Text winnerText;
    /**
     * Main menu button for the game winnner announcement scene.
     */
    private Button winnerMenuBtn;

    /* BOARD ATTRIBUTES */
    /**
     * The tiles to be set on the board.
     * */
    private ArrayList<StackPane> tiles;
    /**
     * The tile color images.
     */
    private ArrayList<ImageView> tileColors;
    /**
     * The car images for the players.
     */
    private ArrayList<ImageView> cars;
    // IMAGE COUNTER ATTRIBUTES FOR GAME (GUI) BOARD
    /**
     * Image counter attribute for Magenta Tiles.
     */
    private int magentaTileCtr;
    /**
     * Image counter attribute for Blue Tiles.
     */
    private int blueTileCtr;
    /**
     * Image counter attribute for Green Tiles.
     */
    private int greenTileCtr;
    /**
     * Image counter attribute for Orange Tiles.
     */
    private int orangeTileCtr;

    /* GAME EXIT ALERT BOX ATTRIBUTES */
    /**
     * The stage for the exit alert box window.
     */
    private Stage exitAlertBoxStage;
    /**
     * The main layout for the exit alert box scene.
     */
    private VBox exitAlertBoxMainLayout;
    /**
     * The button layout for the exit alert box scene.
     */
    private HBox exitButtonLayout;
    /**
     * The exit message to be displayed for the exit alert box scene.
     */
    private Text exitMessage;
    /**
     * The yes button for the exit alert box scene.
     */
    private Button yesExitBtn;
    /**
     * The no button for the exit alert box scene.
     */
    private Button noExitBtn;

    /* ANNOUNCEMENT BOX ATTRIBUTES */
    /**
     *  The stage for the announcement box window.
     */
    private  Stage announceStage;
    /**
     * The scene for the announcement box window.
     */
    private Scene announceScene;
    /**
     * The main layout for the announcement box scene.
     */
    private VBox announceMainLayout;
    /**
     * The announcement message to be displayed for the announcement box window.
     */
    private Text announceMessage;
    /**
     * Okay button for the announcement box window.
     */
    private Button announceOkBtn;
    /**
     * Image for Pay Day announcement.
     */
    private ImageView payDayImage;
    /**
     * Image for Pay Raise announcement.
     */
    private ImageView payRaiseImage;
    /**
     * Image for Stop! announcement.
     */
    private ImageView stopImage;

    /**
     * This contains the main method for the View class.
     *
     * @param args  a string array input for program arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes a new View object which contains all the GUI elements that are
     * essential to the Phase 2 implementation of That's Life.
     *
     * @param stage the main stage of the game
     *
     * @throws  Exception if an exception occured in the program process
     */
    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;

        // SETTING UP OF GUI ELEMENTS IN SCENES
        try {
            mainMenuSceneSetup ();
            instructionsSceneSetup ();
            gameSetSceneSetup ();
            // SET NEEDED SCENES AND STAGES FOR IN-GAME SCREEN
            inGameSceneSetup ();
            houseCardWindowSetup ();
            twoChoiceWindowSetup ();
            wheelSpinSetup ();
            drawCardSetup ();
            choosePlayerSetup ();
            lifeSummarySetup ();
            winnerSceneSetup ();
            setupAnnouncement ();
            chooseTwoCardsSetup ();
            boardGUISetup ();
            setupExitAlertBox ();

            // SETUP MAIN WINDOW PROPERTIES
            assignTitleForStage ();
            mainStage.getIcons ().add (
                    new Image (new FileInputStream ("images/lifeIcon.jpg"))
            );
            mainStage.setScene (mainMenuScene);
            mainStage.setResizable (false);
            // ISNTANTIATE CONTROLLER
            new Controller (this);
            // SHOW MAIN STAGE
            mainStage.show ();
        } catch (FileNotFoundException e) {
            // TO TRACE LINE OF EXCEPTION
            e.printStackTrace ();
            System.out.println ("An error occurred in accessing file resource/s! File/s may not exist!");
            // TERMINATE PROGRAM
            exitGame ();
        }
    }

    /**
     * Initializes the GUI elements that are found in the Main Menu scene of the game.
     *
     * @throws  FileNotFoundException if the files needed are not found
     */
    public void mainMenuSceneSetup () throws FileNotFoundException {
        // INITIALIZE LAYOUTS FOR MAIN MENU SCENE
        BorderPane layout = new BorderPane ();
        VBox buttonLayout = new VBox (5);
        /* INITIALIZE AND SETUP BUTTONS FOR MAIN MENU SCENE */
        // START GAME BUTTON
        startBtn = new Button ("START A GAME");
        startBtn.setId ("startNewGameBtn");
        startBtn.setFont(Font.loadFont (new FileInputStream ("fonts/SFCartoonistHand.ttf"), 28));
        startBtn.setPrefSize (300, 30);
        startBtn.setPadding (new Insets (1, 3, 1, 3));
        startBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #FFFFF0");
        // INSTRUCTIONS BUTTON
        instructionsBtn = new Button ("INSTRUCTIONS");
        instructionsBtn.setId ("seeInstructionsBtn");
        instructionsBtn.setFont (Font.loadFont (new FileInputStream ("fonts/SFCartoonistHand.ttf"), 28));
        instructionsBtn.setPrefSize (300, 30);
        instructionsBtn.setPadding (new Insets (1, 3, 1, 3));
        instructionsBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #FFFFF0");
        // EXIT GAME BUTTON
        exitBtn = new Button("EXIT GAME");
        exitBtn.setId ("exitGameBtn");
        exitBtn.setFont(Font.loadFont (new FileInputStream("fonts/SFCartoonistHand.ttf"), 28));
        exitBtn.setPrefSize (300, 30);
        exitBtn.setPadding (new Insets(1, 3, 1, 3));
        exitBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #FFFFF0");
        // SETUP LAYOUT FOR THE BUTTONS
        buttonLayout.getChildren ().addAll (startBtn, instructionsBtn, exitBtn);
        buttonLayout.setAlignment (Pos.TOP_CENTER);
        buttonLayout.setPadding (new Insets(35, 20, 62, 20));
        // SETUP TITLE IMAGE FOR MAIN MENU SCENE
        ImageView titleIV = new ImageView (
                new Image (new FileInputStream ("images/thatsLifeTitle.png"), 350, 225, true, true)
        );
        // SETUP BACKGROUND IMAGE/GIF FOR MAIN MENU SCENE
        BackgroundImage bg = new BackgroundImage (
                new Image (new FileInputStream ("images/thatsLifeBG.gif")), BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize (700, 450, false, false, false, false)
        );
        // SETUP MAIN MENU SCENE LAYOUT
        layout.setTop (titleIV);
        layout.setAlignment (titleIV, Pos.TOP_CENTER);
        layout.setCenter (buttonLayout);
        layout.setPadding (new Insets(1, 1, 65, 1));
        layout.setBackground (new Background(bg));
        // INITIALIZE AND SETUP SCENE
        mainMenuScene = new Scene (layout, 600, 450);
    }

    /**
     * Initializes the GUI elements that are found in the Instructions scene of the game.
     *
     * @throws  FileNotFoundException if the files needed are not found
     */
    public void instructionsSceneSetup () throws FileNotFoundException {
        instructionsCtr = 0; // for changing instruction texts
        // SETUP FOR LAYOUTS
        BorderPane mainLayout = new BorderPane ();
        HBox instructionsButtonLayout = new HBox (5);
        Text labelText = new Text ("About Thats Life");
        // INITIALIZE AND SETUP BUTTONS FOR INSTRUCTIONS SCENE
        mainMenuBtn = new Button ("MAIN MENU");
        mainMenuBtn.setId ("mainMenuBtn");
        prevBtn = new Button ("<<");
        prevBtn.setId ("prevInstructionBtn");
        prevBtn.setDisable (true); // as first page in start of instructions scene
        nextBtn = new Button (">>");
        nextBtn.setId ("nextInstructionBtn");
        // ADD BUTTONS TO BUTTON LAYOUT AND FORMAT
        instructionsButtonLayout.getChildren ().addAll (prevBtn, mainMenuBtn, nextBtn);
        instructionsButtonLayout.setAlignment (Pos.BOTTOM_CENTER);
        // SETUP INITIAL INSTRUCTIONS TEXT FOR FIRST PAGE IN INSTRUCTIONS SCENE
        instructions = new Text ();
        instructions.setText (INSTRUCTIONS[instructionsCtr]);
        instructions.setFill (Color.FLORALWHITE);
        instructions.setWrappingWidth (480);
        instructions.setTextAlignment (TextAlignment.JUSTIFY);
        instructions.setStroke (Color.BLACK);
        instructions.setStrokeWidth (0.3);
        /* FONT SETUP FOR ELEMENTS */
        // TEXT TITLE FONT
        labelText.setFont (Font.loadFont (new FileInputStream ("fonts/HighTide.ttf"), 65));
        labelText.setFill (Color.GOLD);
        labelText.setStroke (Color.GOLDENROD);
        labelText.setStrokeWidth (0.3);
        // INSTRUCTIONS FONT
        instructions.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHICB.ttf"), 25));
        // BUTTONS FONT
        mainMenuBtn.setFont (Font.loadFont (new FileInputStream ("fonts/SFCartoonistHand.ttf"), 19));
        prevBtn.setFont (Font.loadFont (new FileInputStream ("fonts/SFCartoonistHand.ttf"), 19));
        nextBtn.setFont (Font.loadFont (new FileInputStream ("fonts/SFCartoonistHand.ttf"), 19));
        // FORMATTING OF BUTTONS
        mainMenuBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #FFFFF0");
        prevBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #FFFFF0");
        nextBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #FFFFF0");
        // INITIALIZING + FORMATTING MAIN LAYOUT
        VBox bottomLayout = new VBox (3);
        tipsPage = new Text ((instructionsCtr + 1) + "/" + INSTRUCTIONS.length);
        tipsPage.setFont (Font.loadFont (new FileInputStream ("fonts/SFCartoonistHand.ttf"), 23));
        tipsPage.setFill (Color.FLORALWHITE);
        tipsPage.setStroke (Color.BLACK);
        tipsPage.setStrokeWidth (0.05);
        bottomLayout.getChildren ().addAll (tipsPage, instructionsButtonLayout);
        bottomLayout.setAlignment (Pos.CENTER);
        // INSERTION OF ELEMENTS INTO BORDER PANE
        mainLayout.setTop (labelText);
        mainLayout.setBottom (bottomLayout);
        mainLayout.setCenter (instructions);
        // FORMATTING OF BORDER PANE ELEMENTS
        mainLayout.setAlignment (labelText, Pos.TOP_CENTER);
        mainLayout.setAlignment (instructions, Pos.TOP_CENTER);
        mainLayout.setMargin (instructions, new Insets (65, 0, 0, 0));
        mainLayout.setPadding (new Insets (30, 10, 45, 10));
        // SETUP BACKGROUND IMAGE/GIF FOR INSTRUCTIONS SCENE
        BackgroundImage bg = new BackgroundImage (
                new Image (new FileInputStream ("images/1.gif")), BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize (900, 475, false, false, false, false));
        mainLayout.setBackground (new Background(bg));
        instructionsScene = new Scene (mainLayout, 600, 450);
    }

    /**
     * Updates the instructions being displayed on the scene.
     */
    public void updateInstructionPage () {
        tipsPage.setText ((instructionsCtr + 1) + "/" + INSTRUCTIONS.length);
        instructions.setText (INSTRUCTIONS[instructionsCtr]);
    }

    /**
     * Increments and updates the page number of the instructions scene.
     */
    public void nextInstructionPage () {
        instructionsCtr++;
        updateInstructionPage ();
    }

    /**
     * Decrements and updates the page number of the instructions scene.
     */
    public void previousInstructionPage () {
        instructionsCtr--;
        updateInstructionPage ();
    }

    /**
     * Checks if the instructions scene is currently at the first page.
     *
     * @return  <code>true</code> if the instruction scene is at the first
     *           page; <code>false</code> otherwise
     */
    public boolean isAtFirstInstructionPage () {
        return instructionsCtr == 0;
    }

    /**
     * Checks if the instructions scene is currently at the last page.
     *
     * @return  <code>true</code> if the instruction scene is at the last
     *           page; <code>false</code> otherwise
     */
    public boolean isAtLastInstructionPage () {
        return instructionsCtr == INSTRUCTIONS.length - 1;
    }

    /**
     * Disables the previous button in the instructions scene.
     *
     * @param b <code>true</code> if the button is to be disabled; <code>false
     *          </code> otherwise
     */
    public void setDisablePrevButton (boolean b) {
        prevBtn.setDisable (b);
    }

    /**
     * Disables the next button in the instructions scene.
     *
     * @param b <code>true</code> if the button is to be disabled; <code>false
     *          </code> otherwise
     */
    public void setDisableNextButton (boolean b) {
        nextBtn.setDisable (b);
    }

    /**
     * Resets the attributes of the instructions scene.
     */
    public void resetInstructionPage () {
        instructionsCtr = 0;
        prevBtn.setDisable (true);
        nextBtn.setDisable (false);
    }

    /**
     * Disables and enables the buttons in the instructions scene based on some conditions.
     */
    public void updateInstructionButtons () {
        if (isAtFirstInstructionPage ())
            setDisablePrevButton (true);
        else if (isAtLastInstructionPage ())
            setDisableNextButton (true);
        else {
            setDisableNextButton (false);
            setDisablePrevButton (false);
        }
    }

    /**
     * Initializes the GUI elements found in the Game Setup Scene.
     *
     * @throws  FileNotFoundException   if the files needed are not found
     */
    public void gameSetSceneSetup () throws FileNotFoundException
    {
        /* INITIALIZE AND SETUP LABELS FOR GAME SETUP SCENES */
        // MAIN LABEL FOR GAME SETUP SCENES
        Label mainLbl = new Label ("GAME SETUP");
        mainLbl.setFont (Font.loadFont (new FileInputStream ("fonts/SFCartoonistHand.ttf"), 80));
        mainLbl.setTextFill (Color.FIREBRICK);
        // LABEL FOR NUMBER OF PLAYERS SETUP SCENE
        numOfPlayersLbl = new Label ("SET PLAYER AMOUNT");
        numOfPlayersLbl.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHICB.ttf"), 45));
        numOfPlayersLbl.setTextFill (Color.DARKMAGENTA);
        // INITIALIZE LAYOUTS FOR GAME SETUP SCENES
        BorderPane gameSetLayout = new BorderPane ();
        gameSetLayout.setPadding(new Insets (30, 10, 45, 10));
        gameSetInputLayout = new VBox(5);
        gameSetInputLayout.setPadding (new Insets (5, 5, 5, 5));
        gameSetButtonLayout = new HBox (10);
        gameSetButtonLayout.setPadding (new Insets (5, 5, 5, 5));
        // FOR CONTROL ON CHANGING THE ELEMENTS OF THE GAME SETUP SCENE (NUM OF PLAYERS / PLAYER NAMES FOR EACH PLAYER)
        gameSetPageCtr = 0;
        /* INITIALIZE AND SETUP BUTTONS FOR GAME SETUP SCENES */
        // CONFIRM NUMBER OF PLAYERS BUTTON
        confirmNumBtn = new Button ("CONFIRM NUMBER OF PLAYERS");
        confirmNumBtn.setId ("confirmNumBtn");
        confirmNumBtn.setFont (Font.loadFont (new FileInputStream("fonts/SFCartoonistHand.ttf"), 24));
        confirmNumBtn.setPrefSize (300, 35);
        confirmNumBtn.setPadding (new Insets (1, 3, 1, 3));
        confirmNumBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #FFFFF0");
        // CONFIRM PLAYER NAME BUTTON (DEFAULT, FOR START OF GAME SETUP SCENE)
        confirmNameBtn = new Button ("CONFIRM PLAYER NAME");
        confirmNameBtn.setId ("confirmNameBtn");
        confirmNameBtn.setFont (Font.loadFont (new FileInputStream ("fonts/SFCartoonistHand.ttf"), 28));
        confirmNameBtn.setPrefSize (300, 30);
        confirmNameBtn.setPadding (new Insets (1, 3, 1, 3));
        confirmNameBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #FFFFF0");
        // BACK BUTTON (SPECIFICALLY FOR PLAYER NAME SETUP SCENE)
        backBtn = new Button ("GO BACK");
        backBtn.setId ("backBtn");
        backBtn.setFont (Font.loadFont (new FileInputStream ("fonts/SFCartoonistHand.ttf"), 28));
        backBtn.setPrefSize (300, 30);
        backBtn.setPadding (new Insets(1, 3, 1, 3));
        backBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #FFFFF0");
        // START GAME BUTTON
        startGameBtn = new Button ("CONFIRM AND START GAME");
        startGameBtn.setId ("startGameBtn");
        startGameBtn.setFont (Font.loadFont (new FileInputStream ("fonts/SFCartoonistHand.ttf"), 28));
        startGameBtn.setPrefSize (300, 30);
        startGameBtn.setPadding (new Insets(1, 3, 1, 3));
        startGameBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #FFFFF0");
        // BACK TO MAIN MENU BUTTON
        backMenuBtn = new Button ("GO BACK TO MAIN MENU");
        backMenuBtn.setId ("backMenuBtn");
        backMenuBtn.setFont (Font.loadFont (new FileInputStream ("fonts/SFCartoonistHand.ttf"), 28));
        backMenuBtn.setPrefSize (300, 30);
        backMenuBtn.setPadding(new Insets (1, 3, 1, 3));
        backMenuBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #FFFFF0");
        /* INITIALIZE AND SETUP PLAYER INPUT COMPONENTS FOR GAME SETUP SCENES */
        numOfPlayersBox = new ComboBox<> ();
        playerLbls = new ArrayList<> (); // will be updated depending on numOfPlayersBox selected value
        playerTextFields = new ArrayList<> (); // will be updated depending on numOfPlayersBox selected value
        // SETUP COMPONENTS BASED ON MAX NUMBER OF PLAYERS AS DEFINED IN MODEL'S GAME CLASS
        Color[] textFieldColors = {Color.MAROON, Color.DARKBLUE, Color.DARKMAGENTA};
        for (int k = 1; k <= ThatsLife.MAX_PLAYERS; k++) {
            if (k != ThatsLife.MAX_PLAYERS)
                numOfPlayersBox.getItems ().add (k + 1);
            // ADD LABEL AND TEXT FIELD FOR INPUT PROMPT
            playerLbls.add (new Label ("INPUT NAME OF PLAYER #" + (k)));
            playerLbls.get (k - 1).setFont (Font.loadFont(new FileInputStream ("fonts/GOTHICB.ttf"), 45));
            playerLbls.get (k - 1).setTextFill (textFieldColors[k - 1]);
            playerTextFields.add (new TextField ());
            playerTextFields.get (k - 1).setPromptText ( "Input your name here...");
            playerTextFields.get (k - 1).setMaxWidth (200);
        }
        // SELECT FIRST ITEM (NUMBER OF PLAYERS VALUE) AS DEFAULT VALUE
        numOfPlayersBox.getSelectionModel ().selectFirst ();
        numOfPlayersBox.setMaxWidth (150);
        // SETUP DEFAULT PLAYER INPUT COMPONENTS TO PLAYER INPUT LAYOUT FOR FIRST GAME SETUP (NUMBER OF PLAYERS SCENE)
        gameSetInputLayout.getChildren ().addAll (numOfPlayersLbl, numOfPlayersBox);
        gameSetInputLayout.setAlignment (Pos.CENTER);
        // SETUP DEFAULT BUTTONS TO BUTTON LAYOUT FOR FIRST GAME SETUP (NUMBER OF PLAYER SCENE)
        gameSetButtonLayout.getChildren ().addAll (backMenuBtn, confirmNumBtn); // main menu button to go back to main menu
        gameSetButtonLayout.setAlignment (Pos.BOTTOM_CENTER);
        // SET MAIN LABEL AND LAYOUTS TO GAME SETUP SCENE MAIN LAYOUT
        gameSetLayout.setTop (mainLbl);
        gameSetLayout.setCenter (gameSetInputLayout);
        gameSetLayout.setBottom (gameSetButtonLayout);
        // FORMATTING OF GAME SETUP SCENE MAIN LAYOUT ELEMENTS
        gameSetLayout.setAlignment (mainLbl, Pos.TOP_CENTER);
        gameSetLayout.setAlignment (gameSetInputLayout, Pos.CENTER);
        gameSetLayout.setAlignment (gameSetButtonLayout, Pos.BOTTOM_CENTER);
        // SETUP BACKGROUND IMAGE/GIF FOR GAME SETUP SCENE
        BackgroundImage bg = new BackgroundImage (
                new Image (new FileInputStream ("images/rainbowBG.gif")), BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize (700, 450, false, false, false, false)
        );
        // SETTING OF BACKGROUND
        gameSetLayout.setBackground (new Background(bg));
        // SETTING UP OF SCENE
        gameSetScene = new Scene (gameSetLayout, 600, 450);
    }

    /**
     * Increments and updates the current page of the Game Setup Scene
     */
    public void nextGameSetScene ()
    {
        gameSetPageCtr++;
        updateGameSetScene ();
    }

    /**
     * Decrements and updates the current page of the Game Setup Scene.
     */
    public void prevGameSetScene ()
    {
        gameSetPageCtr--;
        updateGameSetScene ();
    }

    /**
     * Updates the current page of the Game Setup Scene. This also controls the button behaviors in this scene.
     */
    public void updateGameSetScene ()
    {
        if (gameSetPageCtr == 0)
            firstGameSetScene ();
        else {
            gameSetInputLayout.getChildren ().setAll (
                    playerLbls.get (gameSetPageCtr - 1), playerTextFields.get (gameSetPageCtr - 1)
            );

            if (gameSetPageCtr == (int) numOfPlayersBox.getValue ())
                gameSetButtonLayout.getChildren ().setAll (backBtn, startGameBtn);
            else gameSetButtonLayout.getChildren ().setAll (backBtn, confirmNameBtn);

            if (playerTextFields.get (gameSetPageCtr - 1).getText ().isEmpty ()) {
                confirmNameBtn.setDisable (true);
                startGameBtn.setDisable (true);
            } else {
                confirmNameBtn.setDisable (false);
                startGameBtn.setDisable (false);
            }
        }
    }

    /**
     * Sets the Game Setup scene to its first page.
     */
    public void firstGameSetScene () {
        gameSetPageCtr = 0;
        gameSetInputLayout.getChildren ().setAll (numOfPlayersLbl, numOfPlayersBox);
        gameSetButtonLayout.getChildren ().setAll (backMenuBtn, confirmNumBtn);
    }

    /**
     * Disables and enables the confirm player name button.
     *
     * @param b true if the button is to be disabled, otherwise false
     */
    public void setDisablePlayerNameBtn (boolean b)
    {
        confirmNameBtn.setDisable (b);
        startGameBtn.setDisable (b);
    }

    /**
     * Initializes the In-Game Scene of the game.
     *
     * @throws FileNotFoundException if the files needed are not found
     */
    public void inGameSceneSetup() throws FileNotFoundException {
        // MAIN LAYOUT FOR IN-GAME SCENE
        inGameLayout = new BorderPane ();
        inGameLayout.setPadding (new Insets (2, 2, 2, 2));

        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~LAYOUT #1: Left Card Panel~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        // INITIALIZE LAYOUTS: CARD PANEL LAYOUTS
        cardMainLayout = new VBox (2); // for final card layout
        cardDetailsLayout = new StackPane (); // for card + card description
        cardTextDetails = new VBox (15); // for card description
        cardTextDetails.setPadding (new Insets (6, 0, 0, 9));
        // INITIALIZE TEXT: CARD PANEL TEXT LABEL
        cardPanelLabel = new Text ("CARD DRAWN:");
        cardPanelLabel.setFont(Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 20));
        cardPanelLabel.setWrappingWidth (132);
        // INITIALIZE TEXT: CARD NAME
        cardName = new Text ();
        cardName.setFont(Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 23));
        cardName.setWrappingWidth (120);
        // INITIALIZE TEXT: CARD AMOUNT VALUE
        cardVal = new Text ();
        cardVal.setFont(Font.loadFont (new FileInputStream ("fonts/GOTHIC.ttf"), 20));
        cardVal.setWrappingWidth (120);
        cardVal.setTextAlignment (TextAlignment.JUSTIFY);
        // INITIALIZE TEXT: CARD DESCRIPTION
        cardDesc = new Text ();
        cardDesc.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHICI.ttf"), 16));
        cardDesc.setWrappingWidth (120);
        cardDesc.setTextAlignment (TextAlignment.JUSTIFY);
        // INITIALIZE IMAGEVIEW: CARD IMAGES
        defaultCardFront = new ImageView (new Image (new FileInputStream ("images/DefaultCard_front.png"), 161.5, 225, true, true));
        actionCardFront = new ImageView (new Image (new FileInputStream ("images/ActionCard_front.png"), 161.5, 225, true, true));
        blueCardFront = new ImageView (new Image (new FileInputStream ("images/BlueCard_front.png"), 161.5, 225, true, true));
        // SETTING UP OF FINAL PANEL LOOK:
        cardMainLayout.getChildren ().setAll (cardPanelLabel, defaultCardFront);
        cardMainLayout.setAlignment (Pos.CENTER);
        cardMainLayout.setPrefWidth (153);
        cardMainLayout.setStyle ("-fx-background-color: #ADD8E6; -fx-border-color: black; -fx-border-width: 1px");
        cardMainLayout.setPadding (new Insets (10, 3, 10, 3));

        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~LAYOUT #2: Bottom Basic Player Info~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        // INITIALIZE LAYOUT: MAIN PLAYER LAYOUT
        HBox playerMainLayout = new HBox (25);
        // INITIALIZE TEXT: PLAYER NAME
        playerName = new Text ();
        playerName.setFont (Font.loadFont (new FileInputStream("fonts/BRLNSR.ttf"), 22));
        playerName.setWrappingWidth (150);
        // INITIALIZE + FORMATTING BUTTON: GO BACK TO MAIN MENU
        mainMenuExitBtn = new Button ("EXIT GAME");
        mainMenuExitBtn.setId ("mainMenuExitBtn");
        mainMenuExitBtn.setFont (Font.loadFont (new FileInputStream ("fonts/SFCartoonistHand.ttf"), 22));
        mainMenuExitBtn.setStyle ("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: #FFFFF0");
        // INITIALIZE TEXT: PLAYER CASH
        playerCash = new Text ();
        playerCash.setFont (Font.loadFont (new FileInputStream("fonts/BRLNSR.ttf"), 20));
        playerCash.setWrappingWidth (200);
        // INITIALIZE TEXT: PLAYER LOAN BALANCE
        playerLoanBalance = new Text ();
        playerLoanBalance.setFont (Font.loadFont (new FileInputStream("fonts/BRLNSR.ttf"), 20));
        playerLoanBalance.setWrappingWidth (200);
        // SETTING UP OF FINAL PANEL LOOK
        playerMainLayout.getChildren ().addAll (playerName, playerCash, playerLoanBalance, mainMenuExitBtn);
        playerMainLayout.setAlignment (Pos.CENTER_RIGHT);
        playerMainLayout.setStyle ("-fx-background-color: #F5DEB3; -fx-border-color: black; -fx-border-width: 1px");
        playerMainLayout.setPadding (new Insets (10, 10, 10, 10));

        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~LAYOUT #3: Right Other Player Info~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        // INITIALIZE LAYOUT: FINAL PLAYER STATS LAYOUT
        VBox playerStatsLayout = new VBox (12);
        // INITIALIZE TEXT: OTHER STATS TEXT LABEL
        Text playerStatsLabel = new Text ("OTHER STATS:");
        playerStatsLabel.setFont (Font.loadFont(new FileInputStream ("fonts/BRLNSR.ttf"), 23));
        // INITIALIZE TEXT: PLAYER CAREER
        playerCareer = new Text ();
        playerCareer.setFont (Font.loadFont(new FileInputStream ("fonts/GOTHICI.ttf"), 18));
        playerCareer.setWrappingWidth (120);
        // INITIALIZE TEXT: PLAYER SALARY
        playerSalary = new Text ();
        playerSalary.setFont (Font.loadFont(new FileInputStream ("fonts/GOTHICI.ttf"), 18));
        // INITIALIZE TEXT: PLAYER TAX
        playerTax = new Text ();
        playerTax.setFont(Font.loadFont(new FileInputStream ("fonts/GOTHICI.ttf"), 18));
        // INITIALIZE TEXT: PLAYER DEGREE
        playerDegree = new Text ();
        playerDegree.setFont (Font.loadFont(new FileInputStream ("fonts/GOTHICI.ttf"), 18));
        // INITIALIZE TEXT: PLAYER HOUSE
        playerHouse = new Text ();
        playerHouse.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHICI.ttf"), 18));
        playerHouse.setWrappingWidth (120);
        // INITIALIZE TEXT: PLAYER MARITAL STATUS
        playerIsMarried = new Text( );
        playerIsMarried.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHICI.ttf"), 18));
        // INITIALIZE TEXT: PLAYER AMOUNT OF CHILDREN
        playerChildren = new Text ();
        playerChildren.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHICI.ttf"), 18));
        // SETTING UP OF FINAL RIGHT PANEL LOOK
        playerStatsLayout.getChildren ().addAll (playerStatsLabel, playerCareer, playerSalary, playerTax, playerDegree, playerHouse, playerIsMarried, playerChildren);
        playerStatsLayout.setPrefWidth (150);
        playerStatsLayout.setStyle ("-fx-background-color: #ADD8E6; -fx-border-color: #000000; -fx-border-width: 1px");
        playerStatsLayout.setPadding (new Insets(5, 5, 5, 5));

        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~FINAL IN-GAME SCENE SETUP~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        // ADDING OF ALL LAYOUTS AND ELEMENTS TO BORDER PANE
        inGameLayout.setRight (playerStatsLayout);
        inGameLayout.setLeft (cardMainLayout);
        inGameLayout.setBottom (playerMainLayout);

        // SETUP BACKGROUND IMAGE/GIF FOR MAIN MENU SCENE
        BackgroundImage bg = new BackgroundImage (
                new Image (new FileInputStream ("images/natureBG.gif")), BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize (800, 600, false, false, false, false)
        );

        inGameLayout.setBackground (new Background (bg));

        // SETTING UP OF SCENE
        inGameScene = new Scene (inGameLayout, 800, 600);
    }

    /**
     * Updates the card panel found on the left side of the In-Game Scene. This only takes in
     * card instances of ActionCard and BlueCard.
     *
     * @param card  the card to be placed in the left panel
     */
    public void updateCardPanel (Card card) {
        // IF THERE IS A CARD
        if (card != null) {
            cardName.setText (card.getName ());
            cardDesc.setText (card.getDescription ());
            cardTextDetails.getChildren ().setAll (cardName);
            if (((ValueCard) card).getAmount () != 0)
                cardTextDetails.getChildren ().add (cardVal);
            cardTextDetails.getChildren ().add (cardDesc);
        }
        // IF CARD IS AN INSTANCE OF ACTIONCARD
        if (card instanceof ActionCard) {
            cardDetailsLayout.getChildren ().setAll (actionCardFront, cardTextDetails);
            cardVal.setText("$" + ((ActionCard) card).getAmount ());
        }
        // IF CARD IS AN INSTANCE OF BLUECARD
        else if (card instanceof BlueCard) {
            cardDetailsLayout.getChildren ().setAll(blueCardFront, cardTextDetails);
            cardDesc.setText ("Career: \n" + ((BlueCard) card).getCareer () + "\n\n" + card.getDescription ());
            cardVal.setText ("$" + ((BlueCard) card).getAmount ());
        }
        // IF CARD IS NOT AN INSTANCE OF ANYTHING
        else {
            cardDetailsLayout.getChildren ().setAll (defaultCardFront);
        }
        cardMainLayout.getChildren ().setAll (cardPanelLabel, cardDetailsLayout);
        cardDetailsLayout.setAlignment (Pos.CENTER);
    }

    /**
     * Updates the player information in the right and bottom panel of the In-Game Scene.
     *
     * @param player    the current player playing their turn
     */
    public void updatePlayerPanel (Player player) {
        // UPDATING OF BASIC PLAYER INFO
        playerName.setText (player.getName ());
        playerCash.setText ("Cash: \n$" + Integer.toString(player.getCash ()));
        playerLoanBalance.setText (
                "Loan Balance: \n$" + Integer.toString(player.getLoanBalance ())
        );
        // IF PLAYER HAS A JOB
        if (player.getStatus ().getCareerCard () != null) {
            playerCareer.setText ("Career: \n" + player.getStatus ().getCareerCard ().getName ());
            playerSalary.setText ("Salary: \n$" + player.getStatus ().getCurrentSalary ());
            playerTax.setText ("Tax: \n$" + player.getStatus ().getCurrentTax ());
        } else {
            playerCareer.setText ("Career: \nN/A");
            playerSalary.setText ("Salary: \n$0");
            playerTax.setText ("Tax: \n$0");
        }
        // IF PLAYER HAS A DEGREE
        if (player.getStatus ().hasDegree ())
            playerDegree.setText ("Degree: \nYES");
        else
            playerDegree.setText ("Degree: \nN/A");
        // IF PLAYER HAS A HOUSE
        if (player.getStatus ().getHouseCard () != null)
            playerHouse.setText ("House: \n" + player.getStatus ().getHouseCard ().getName ());
        else
            playerHouse.setText ("House: \nN/A");
        // IF PLAYER IS MARRIED
        if (player.getStatus ().isMarried()) {
            playerIsMarried.setText ("Married: \nYES");
            playerChildren.setText ("Children: \n" + (player.getStatus ().getFamily () - 1));
        }
        else {
            playerIsMarried.setText ("Married: \nNO");
            playerChildren.setText ("Children: \n0");
        }
    }

    /**
     * Initializes the GUI elements needed for showing announcement boxes
     * all throughout the game process of That's Life.
     *
     * @throws  FileNotFoundException if the files needed are not found
     */
    public void setupAnnouncement() throws FileNotFoundException {
        // INITIALIZE LAYOUTS
        announceStage = new Stage ();
        announceMainLayout = new VBox (3);
        // SETTING UP OF MAIN LAYOUT
        announceMainLayout.setPadding (new Insets (20,20,20,20));
        announceMainLayout.setAlignment (Pos.CENTER);
        // INITIALIZE + FORMAT BUTTON: OKAY BUTTON
        announceOkBtn = new Button ("Okay!");
        announceOkBtn.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 25));
        announceOkBtn.setPrefWidth (100);
        // INITIALIZE TEXT: ANNOUNCEMENT MESSAGE
        announceMessage = new Text ();
        announceMessage.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 35));
        // INITIALIZE IMAGEVIEW: PAYDAY, PAY RAISE, AND STOP IMAGES
        payDayImage = new ImageView (new Image (new FileInputStream ("images/PayDay.png"), 500, 500, true, true));
        payRaiseImage = new ImageView (new Image (new FileInputStream ("images/PayRaise.png"), 500, 500, true, true));
        stopImage = new ImageView (new Image (new FileInputStream("images/StopSign.png"), 300, 300, true, true));
        // ADDING OF CHILDREN INTO MAIN LAYOUT
        announceMainLayout.getChildren ().addAll (announceMessage, announceOkBtn);
        // SETTING UP OF STAGE FORMATTING
        announceStage.initStyle (StageStyle.UNDECORATED);
        announceStage.setResizable (false);
        announceScene = new Scene (announceMainLayout);
        announceStage.setScene (announceScene);
    }

    /**
     * Shows the announcement box pop-up inside the game. This shows announcements for specific events
     * such as Pay Day, Pay Raise, Stop, and Magenta Limit Announcements.
     *
     * @param announceType the type of announcement that will be displayed
     * @param isImageAnnouncement true if the announcement is an image, otherwise false
     */
    public void showAnnouncement (String announceType, boolean isImageAnnouncement) {
        announceOkBtn.setId ("announceOkBtn");
        // IF NOT AN IMAGE ANNOUNCEMENT
        if (!isImageAnnouncement) {
            /* "MAGENTA LIMIT" ANNOUNCEMENTS */
            // ALREADY MARRIED
            if (announceType.equalsIgnoreCase (ViewConfig.ANNOUNCE_TYPES[7]))
                announceMessage.setText ("You are already married!");
                // MAXIMUM CHILDREN REACHED
            else if (announceType.equalsIgnoreCase (ViewConfig.ANNOUNCE_TYPES[8]))
                announceMessage.setText ("Max family members already!");
                // NOT MARRIED, CAN'T HAVE CHILDREN
            else if (announceType.equalsIgnoreCase (ViewConfig.ANNOUNCE_TYPES[9]))
                announceMessage.setText ("You are not married! You cannot have children!");
                /* CAREER-RELATED ANNOUNCEMENTS */
                // NO CAREER YET
            else if (announceType.equalsIgnoreCase (ViewConfig.ANNOUNCE_TYPES[10]))
                announceMessage.setText ("You don't have a career yet!");
                // CAREER REQUIRES DEGREE
            else if (announceType.equalsIgnoreCase (ViewConfig.ANNOUNCE_TYPES[11]))
                announceMessage.setText ("Degree required for this career!");
                /* OTHER ANNOUNCEMENTS */
                // MAX PAY RAISE
            else if (announceType.equalsIgnoreCase (ViewConfig.ANNOUNCE_TYPES[0]))
                announceMessage.setText ("Max pay raise reached!");
                // NEXT PLAYER TURN
            else if (announceType.equalsIgnoreCase (ViewConfig.ANNOUNCE_TYPES[1])) {
                announceOkBtn.setId ("nextPlayerTurnBtn");
                announceMessage.setText ("Next player's turn!");
                // LOAN MONEY FROM THE BANK
            } else if (announceType.equalsIgnoreCase (ViewConfig.ANNOUNCE_TYPES[2]))
                announceMessage.setText ("You have borrowed money from the bank!");
                // MATCH BLUE CAREER
            else if (announceType.equalsIgnoreCase (ViewConfig.ANNOUNCE_TYPES[3]))
                announceMessage.setText ("You have matched a career! Get $15000 instead!");
                // RESHUFFLE DECK
            else if (announceType.equalsIgnoreCase (ViewConfig.ANNOUNCE_TYPES[12])) // reshuffle deck
                announceMessage.setText ("Deck reshuffled!");
            // FORMATTING ANNOUNCEMENT WINDOW
            announceOkBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #F5DEB3"); // set to wheat
            announceMainLayout.setStyle("-fx-background-color: #ADD8E6"); // set to light blue
            announceMainLayout.getChildren().setAll (announceMessage);
            // IF IMAGE ANNOUNCEMENT
        } else {
            // FORMATTING ANNOUNCEMENT WINDOW
            announceOkBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #3CB371"); // set to green
            announceMainLayout.setStyle ("-fx-background-color: #90EE90"); // set to green
            // PAY DAY
            if (announceType.equalsIgnoreCase (ViewConfig.ANNOUNCE_TYPES[4]))
                announceMainLayout.getChildren ().setAll (payDayImage);
                // PAY RAISE
            else if (announceType.equalsIgnoreCase (ViewConfig.ANNOUNCE_TYPES[5]))
                announceMainLayout.getChildren ().setAll (payRaiseImage);
                // STOP!
            else if (announceType.equalsIgnoreCase (ViewConfig.ANNOUNCE_TYPES[6])) {
                announceMainLayout.getChildren ().setAll (stopImage);
                announceMainLayout.setStyle ("-fx-background-color: #CD5C5C"); // set to red
                announceOkBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #F08080"); // set to red
            }
        }
        // SETUP SCENE
        announceMainLayout.getChildren ().add (announceOkBtn);
        announceStage.showAndWait ();
    }

    /**
     * Shows the announcement box pop-up inside the game. This shows announcements for specific events
     * such as Tile Announcements. An example of this is "You landed on an Orange Space!".
     *
     * @param space the space from which the announcement will be based on
     */
    public void showAnnouncement (Space space) {
        announceOkBtn.setId ("announceOkBtn");
        // IF MAGENTA SPACE
        if (space.getType ().equalsIgnoreCase (Space.TYPES[5])) {
            // GRADUATION SPACE
            if (space.getMagenta ().equalsIgnoreCase (Space.MAGENTAS[0]))
                announceMessage.setText ("Graduation Day!");
                // GET MARRIED SPACE
            else if (space.getMagenta ().equalsIgnoreCase(Space.MAGENTAS[2]))
                announceMessage.setText ("Get Married!");
                // BUY A HOUSE SPACE
            else if (space.getMagenta ().equalsIgnoreCase(Space.MAGENTAS[7]))
                announceMessage.setText ("Buy A House!");
                // HAVE CHILDREN SPACE
            else if (space.getMagenta ().equalsIgnoreCase(Space.MAGENTAS[8]))
                announceMessage.setText ("Have Children!");
                // COLLEGE CAREER CHOICE SPACE
            else if (space.getMagenta ().equalsIgnoreCase(Space.MAGENTAS[1]))
                announceMessage.setText ("College Career Choice!");
                // JOB SEARCH SPACE
            else if (space.getMagenta ().equalsIgnoreCase (Space.MAGENTAS[4]))
                announceMessage.setText ("Job Search!");
                // CAREER JUNCTION
            else if (space.getMagenta ().equalsIgnoreCase (Space.MAGENTAS[3]))
                announceMessage.setText ("Make A Career Choice!");
                // FAMILY JUNCTION
            else announceMessage.setText ("Start A Family?");
            // IF NON-MAGENTA SPACE
        } else {
            // ORANGE SPACE
            if (space.getType ().equalsIgnoreCase (Space.TYPES[4]))
                announceMessage.setText ("You landed on an Orange Space!");
                // BLUE SPACE
            else if (space.getType ().equalsIgnoreCase(Space.TYPES[2]))
                announceMessage.setText ("You landed on a Blue Space!");
                // GREEN SPACE
            else if (space.getType ().equalsIgnoreCase (Space.TYPES[0]) ||
                    space.getType ().equalsIgnoreCase (Space.TYPES[1]))
                announceMessage.setText ("You landed on a Green Space!");
                // GREEN SPACE
            else announceMessage.setText ("Retirement time!");
        }
        // FORMAT + SETUP SCENE
        announceMainLayout.getChildren ().setAll (announceMessage, announceOkBtn);
        announceOkBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #F5DEB3");
        announceMainLayout.setStyle ("-fx-background-color: #ADD8E6");
        announceStage.showAndWait ();
    }

    /**
     * Shows the announcement box pop-up inside the game. This shows the announcement when
     * a player lands on a "Have Children" Magenta Tiles, which involves displaying Cash.
     *
     * @param babyAmt the amount of new children that will be gained by the player
     * @param childrenAmt the new amount of children that a player has
     */
    public void showAnnouncement (int babyAmt, int childrenAmt) {
        announceOkBtn.setId ("announceOkBtn");
        childrenAmt += babyAmt;
        if (babyAmt == 1)
            announceMessage.setText ("A baby! Collect $" + (5000*childrenAmt) + " from all players!");
        else announceMessage.setText ("Twins?! Collect $" + (5000*childrenAmt) + " from all players!");
        announceMainLayout.getChildren ().setAll (announceMessage, announceOkBtn);
        announceOkBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #F5DEB3"); // set to wheat
        announceMainLayout.setStyle ("-fx-background-color: #ADD8E6"); // set to light blue
        announceStage.showAndWait ();
    }

    /**
     * Shows the announcement box pop-up inside the game. This shows announcements for specific events
     * such as paying the bank and other players.
     *
     * @param players the list of players in the game
     * @param playerChoice  the player whom the current player chose to pay cash
     */
    public void showAnnouncement (ArrayList<Player> players, int playerChoice) {
        if (playerChoice == -1)
            announceMessage.setText ("You paid the bank!");
        else announceMessage.setText ("You paid " + players.get(playerChoice - 1).getName () + "!");
        announceMainLayout.getChildren ().setAll (announceMessage, announceOkBtn);
        announceStage.showAndWait ();
    }

    /**
     * Closes the announcement box pop-up inside the game.
     */
    public void closeAnnouncement () {
        announceStage.close();
    }

    /**
     * Initializes the GUI elements for the selection of House Cards in the game.
     *
     * @throws FileNotFoundException    if the files needed are not found
     */
    public void houseCardWindowSetup() throws FileNotFoundException {
        // INITIALIZE LAYOUTS:
        houseCardStage = new Stage ();
        VBox houseCardMainLayout = new VBox (5);
        HBox houseCardContentLayout = new HBox (3);
        firstCard = new StackPane ();
        secondCard = new StackPane ();
        thirdCard = new StackPane ();
        // SETTING OF IDs FOR EACH CARD
        firstCard.setId ("House 1");
        secondCard.setId ("House 2");
        thirdCard.setId ("House 3");
        // INITIALIZE TEXT: HOUSE SELECTION LABEL
        Text houseSelectionLbl = new Text ("CHOOSE A HOUSE:");
        houseSelectionLbl.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 20));
        // INITIALIZE IMAGEVIEW: CARD BACKGROUNDS
        ImageView firstHouseCard = new ImageView (new Image (new FileInputStream ("images/HouseCard_front.png"), 161.5, 225, true, true));
        ImageView secondHouseCard = new ImageView (new Image (new FileInputStream ("images/HouseCard_front.png"), 161.5, 225, true, true));
        ImageView thirdHouseCard = new ImageView (new Image (new FileInputStream ("images/HouseCard_front.png"), 161.5, 225, true, true));
        // INITIALIZE + FORMAT TEXT: FIRST HOUSE
        firstHouseName = new Text ();
        firstHouseName.setFont (Font.loadFont(new FileInputStream ("fonts/BRLNSR.ttf"), 23));
        firstHouseName.setWrappingWidth(120);
        firstHousePrice = new Text ();
        firstHousePrice.setFont(Font.loadFont (new FileInputStream ("fonts/GOTHIC.ttf"), 20));
        firstHousePrice.setWrappingWidth (120);
        firstHouseDescription = new Text ();
        firstHouseDescription.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHICI.ttf"), 16));
        firstHouseDescription.setWrappingWidth (120);
        // INITIALIZE + FORMAT TEXT: SECOND HOUSE
        secondHouseName = new Text ();
        secondHouseName.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 23));
        secondHouseName.setWrappingWidth (120);
        secondHousePrice = new Text ();
        secondHousePrice.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHIC.ttf"), 20));
        secondHousePrice.setWrappingWidth (120);
        secondHouseDescription = new Text ();
        secondHouseDescription.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHICI.ttf"), 16));
        secondHouseDescription.setWrappingWidth (120);
        // INITIALIZE + FORMAT TEXT: THIRD HOUSE
        thirdHouseName = new Text ();
        thirdHouseName.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 23));
        thirdHouseName.setWrappingWidth (120);
        thirdHousePrice = new Text ();
        thirdHousePrice.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHIC.ttf"), 20));
        thirdHousePrice.setWrappingWidth (120);
        thirdHouseDescription = new Text ();
        thirdHouseDescription.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHICI.ttf"), 16));
        thirdHouseDescription.setWrappingWidth (120);
        // INITIALIZE VBOX: FIRST HOUSE INFO
        VBox firstHouseInfo = new VBox (15);
        firstHouseInfo.getChildren ().addAll (firstHouseName, firstHousePrice, firstHouseDescription);
        firstCard.getChildren ().addAll (firstHouseCard, firstHouseInfo);
        firstHouseInfo.setPadding (new Insets (3, 0, 0, 7));
        // INITIALIZE VBOX: SECOND HOUSE INFO
        VBox secondHouseInfo = new VBox (15);
        secondHouseInfo.getChildren ().addAll (secondHouseName, secondHousePrice, secondHouseDescription);
        secondCard.getChildren ().addAll (secondHouseCard, secondHouseInfo);
        secondHouseInfo.setPadding (new Insets (3, 0, 0, 7));
        // INITIALIZE VBOX: THIRD HOUSE INFO
        VBox thirdHouseInfo = new VBox (15);
        thirdHouseInfo.getChildren ().addAll (thirdHouseName, thirdHousePrice, thirdHouseDescription);
        thirdCard.getChildren ().addAll (thirdHouseCard, thirdHouseInfo);
        thirdHouseInfo.setPadding (new Insets (3, 0, 0, 7));
        // SETTING UP OF CARD LAYOUT
        houseCardContentLayout.getChildren ().addAll (firstCard, secondCard, thirdCard);
        // SETTING UP OF MAIN LAYOUT
        houseCardMainLayout.setAlignment (Pos.CENTER);
        houseCardMainLayout.setPadding (new Insets(5, 5, 5, 5));
        houseCardMainLayout.setStyle ("-fx-background-color: #ADD8E6");
        houseCardMainLayout.getChildren ().addAll (houseSelectionLbl, houseCardContentLayout);
        // SETTING UP OF STAGE
        houseCardStage.initStyle (StageStyle.UNDECORATED);
        houseCardStage.setResizable (false);
        houseCardStage.getIcons ().add (
                new Image (new FileInputStream("images/lifeIcon.jpg"))
        );
        houseCardStage.setScene (new Scene(houseCardMainLayout));
    }

    /**
     * Updates and shows the House Cards that a player will choose from.
     *
     * @param HouseCards    the list of House Cards in the game
     * @param numbers       the indices of the House Cards that will be displayed
     */
    public void showHouseCardSelection (ArrayList<Card> HouseCards, int[] numbers) {
        // FIRST HOUSE CARD
        firstHouseName.setText (HouseCards.get (numbers[0]).getName ());
        firstHouseDescription.setText(HouseCards.get (numbers[0]).getDescription ());
        firstHousePrice.setText ("$" + ((HouseCard) HouseCards.get(numbers[0])).getPrice ());
        // SECOND HOUSE CARD
        secondHouseName.setText (HouseCards.get (numbers[1]).getName ());
        secondHouseDescription.setText (HouseCards.get (numbers[1]).getDescription ());
        secondHousePrice.setText("$" + ((HouseCard) HouseCards.get (numbers[1])).getPrice ());
        // THIRD HOUSE CARD
        thirdHouseName.setText (HouseCards.get (numbers[2]).getName ());
        thirdHouseDescription.setText (HouseCards.get (numbers[2]).getDescription ());
        thirdHousePrice.setText ("$" + ((HouseCard) HouseCards.get (numbers[2])).getPrice ());
        // SHOW WINDOW
        houseCardStage.showAndWait ();
    }

    /**
     * Closes the House Card selection window.
     */
    public void closeHouseWindow () {
        houseCardStage.close ();
    }

    /**
     * Initializes the GUI elements for the junction selection window.
     *
     * @throws FileNotFoundException    if the files needed are not found
     */
    public void twoChoiceWindowSetup () throws FileNotFoundException {
        // INITIALIZE LAYOUTS
        junctionChoiceStage = new Stage ();
        HBox buttonLayout = new HBox (3);
        VBox twoChoiceMainLayout = new VBox (5);
        // SETTING UP OF MAIN LAYOUT
        twoChoiceMainLayout.setPadding (new Insets(5, 5, 5, 5));
        twoChoiceMainLayout.setAlignment (Pos.CENTER);
        twoChoiceMainLayout.setStyle ("-fx-background-color: #ADD8E6");
        // INITIALIZE + FORMAT TEXT: TITLE
        Text junctionLbl = new Text ("CHOOSE A PATH:");
        junctionLbl.setFont (Font.loadFont(new FileInputStream ("fonts/BRLNSR.ttf"), 20));
        // INITIALIZE + FORMAT BUTTONS: TWO CHOICES
        choiceOne = new Button ();
        choiceOne.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 25));
        choiceOne.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #DDA0DD");
        choiceOne.setPrefWidth (300);
        choiceTwo = new Button ();
        choiceTwo.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 25));
        choiceTwo.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #DDA0DD");
        choiceTwo.setPrefWidth (300);
        // ADDING OF CHILDREN TO LAYOUTS
        buttonLayout.getChildren ().addAll (choiceOne, choiceTwo);
        twoChoiceMainLayout.getChildren ().addAll (junctionLbl, buttonLayout);
        // SETTING UP OF STAGE FORMATTING
        junctionChoiceStage.initStyle (StageStyle.UNDECORATED);
        junctionChoiceStage.setResizable (false);
        junctionChoiceStage.getIcons ().add (
                new Image(new FileInputStream ("images/lifeIcon.jpg"))
        );
        junctionChoiceStage.setScene (new Scene (twoChoiceMainLayout));
    }

    /**
     * Updates and shows the choices in the junction selection window.
     *
     * @param firstChoice   the first path choice
     * @param secondChoice  the second path choice
     */
    public void showJunctionChoices (String firstChoice, String secondChoice) {
        // FIRST CHOICE
        choiceOne.setText (firstChoice);
        choiceOne.setId (firstChoice);
        // SECOND CHOICE
        choiceTwo.setText (secondChoice);
        choiceTwo.setId (secondChoice);
        // SHOW WINDOW
        junctionChoiceStage.showAndWait ();
    }

    /**
     * Closes the junction selection window.
     */
    public void closeJunctionWindow () {
        junctionChoiceStage.close ();
    }

    /**
     * Initializes the GUI elements of the Wheel Spin and Spin Result windows.
     *
     * @throws FileNotFoundException    if the files needed are not found
     */
    public void wheelSpinSetup () throws FileNotFoundException {
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~SPINNING WHEEL SETUP~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        // INITIALIZE LAYOUTS
        StackPane wheelMainLayout = new StackPane ();
        wheelStage = new Stage ();
        // INITIALIZE IMAGE VIEW: WHEEL AND POINTER
        ImageView wheel = new ImageView (new Image (new FileInputStream ("images/wheel.gif"), 450, 450, true, true));
        wheelPointer = new ImageView (new Image (new FileInputStream ("images/WheelPointer.png"), 80, 80, true, true));
        // ADDING OF CHILDREN TO LAYOUT
        wheelMainLayout.getChildren ().addAll (wheel, wheelPointer);
        wheelMainLayout.setAlignment (Pos.CENTER);
        // SETTING ID OF WHEEL POINTER BUTTON
        wheelPointer.setId ("SPIN!");
        // SETTING UP OF STAGE FORMATTING
        wheelStage.initStyle (StageStyle.UNDECORATED);
        wheelStage.setResizable (false);
        wheelStage.getIcons ().add (
                new Image(new FileInputStream ("images/lifeIcon.jpg"))
        );
        // SET TRANSPARENT EVERYTHING
        wheelMainLayout.setBackground (new Background (new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene spinScene = new Scene (wheelMainLayout);
        spinScene.setFill (Color.TRANSPARENT);
        wheelStage.initStyle (StageStyle.TRANSPARENT);
        // SET STAGE
        wheelStage.setScene (spinScene);

        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~NUMBER RESULT SETUP~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        // INITIALIZE LAYOUTS
        VBox spinNumberMainLayout = new VBox (5);
        spinNumberStage = new Stage ();
        // INITIALIZE + FORMAT BUTTON: OK. BUTTON
        spinNumberBtn = new Button ("Ok.");
        spinNumberBtn.setId ("spinNumberOkay");
        spinNumberBtn.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 20));
        spinNumberBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #F5DEB3");
        spinNumberBtn.setPrefWidth (80);
        // INITIALIZE TEXT: RESULTING NUMBER
        spinNumber = new Text ();
        spinNumber.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 35));
        // ADDING CHILDREN TO MAIN LAYOUT + FORMATTING
        spinNumberMainLayout.getChildren ().addAll (spinNumber, spinNumberBtn);
        spinNumberMainLayout.setPadding (new Insets (30,30,30,30));
        spinNumberMainLayout.setAlignment (Pos.CENTER);
        spinNumberMainLayout.setStyle ("-fx-background-color: #ADD8E6");
        // SETTING UP OF STAGE FORMATTING
        spinNumberStage.initStyle (StageStyle.UNDECORATED);
        spinNumberStage.setResizable (false);
        spinNumberStage.getIcons ().add(
                new Image(new FileInputStream ("images/lifeIcon.jpg"))
        );
        spinNumberStage.setScene (new Scene (spinNumberMainLayout));
    }

    /**
     * Shows the wheel spin window.
     */
    public void showWheelSpin () {
        wheelStage.showAndWait ();
    }

    /**
     * Closes the wheel spin window.
     */
    public void closeWheelSpin () {
        wheelStage.close ();
    }

    /**
     * Closes the Spin Number result window.
     */
    public void closeSpinNumber () {
        spinNumberStage.close ();
    }

    /**
     * Updates and shows the resulting spin number from the wheel.
     *
     * @param number    the resulting spin number
     */
    public void showSpinNumber (int number) {
        if (number == 1)
            spinNumber.setText ("Move " + number + " tile!");
        else spinNumber.setText ("Move " + number + " tiles!");
        spinNumberStage.showAndWait ();
    }

    /**
     * Updates and shows the Magenta Tile announcement for Get Married and Have Children -
     * which involves collecting a specific amount from other players. This also updates and shows
     * the Blue Tile announcement.
     *
     * @param amt   the amount to be paid / collected by the current player
     * @param space the space where the current player landed
     */
    public void showSpinCash (int amt, Space space) {
        if (space.getType ().equalsIgnoreCase(Space.TYPES[5])) {// if magenta
            spinNumber.setText ("Collect $" + amt + " from all players!"); // get married
        } else if (space.getType ().equalsIgnoreCase (Space.TYPES[2])) // if blue
            spinNumber.setText ("Pay $" + amt + "!");
        spinNumberStage.showAndWait ();
    }

    /**
     * Initializes the GUI elements of the card draw window.
     *
     * @throws FileNotFoundException    if the files needed are not found
     */
    public void drawCardSetup () throws FileNotFoundException {
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~DRAWING OF CARDS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        // INITIALIZE LAYOUTS:
        drawCardStage = new Stage ();
        drawCardMainLayout = new VBox (3);
        // FORMATTING OF MAIN LAYOUT:
        drawCardMainLayout.setPadding (new Insets (5,5,5,5));
        drawCardMainLayout.setAlignment (Pos.CENTER);
        drawCardMainLayout.setStyle ("-fx-background-color: #ADD8E6;");
        // INITIALIZE IMAGEVIEW: CARD BACKS
        actionCardBack = new ImageView (new Image (new FileInputStream ("images/ActionCard_back.png"), 161.5, 225, true, true));
        blueCardBack = new ImageView (new Image (new FileInputStream ("images/BlueCard_back.png"), 161.5, 225, true, true));
        careerCardBack = new ImageView (new Image (new FileInputStream ("images/CareerCard_back.png"), 161.5, 225, true, true));
        salaryCardBack = new ImageView (new Image (new FileInputStream ("images/SalaryCard_back.png"), 161.5, 225, true, true));
        // INITIALIZE + FORMAT BUTTON: DRAW BUTTON
        drawBtn = new Button ("Draw!");
        drawBtn.setId ("drawBtn");
        drawBtn.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 20));
        drawBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #F5DEB3");
        drawBtn.setPrefWidth (140);
        // INITIALIZE + FORMAT BUTTON: RESHUFFLE BUTTON
        reshuffleBtn = new Button ("Reshuffle!");
        reshuffleBtn.setId ("reshuffleBtn");
        reshuffleBtn.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 20));
        reshuffleBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #F5DEB3");
        reshuffleBtn.setPrefWidth (140);
        // SETTING UP OF STAGE
        drawCardStage.initStyle (StageStyle.UNDECORATED);
        drawCardStage.setResizable (false);
        drawCardStage.getIcons ().add(
                new Image (new FileInputStream ("images/lifeIcon.jpg"))
        );
        drawCardStage.setScene (new Scene (drawCardMainLayout, 200, 320));

        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~POST-CARD DRAW~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        // INITIALIZE + FORMATTING OF LAYOUTS:
        drawnCardDetails = new VBox (15);
        drawnCardLayout = new StackPane ();
        drawnCardDetails.setAlignment (Pos.TOP_LEFT);
        drawnCardDetails.setPadding (new Insets (5, 0, 0, 33));
        drawnCardStage = new Stage ();
        drawnCardMainLayout = new VBox (3);
        drawnCardMainLayout.setPadding (new Insets (5,5,5,5));
        drawnCardMainLayout.setStyle ("-fx-background-color: #ADD8E6;");
        drawnCardMainLayout.setAlignment (Pos.CENTER);
        // INITIALIZE + FORMATTING TEXT: WINDOW TITLE
        drawnCardTitle = new Text ("YOU HAVE DRAWN:");
        drawnCardTitle.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 17));
        // INITIALIZE IMAGEVIEWS: CARD FRONTS
        drawnActionFront = new ImageView (new Image (new FileInputStream ("images/ActionCard_front.png"), 161.5, 225, true, true));
        drawnBlueFront = new ImageView (new Image (new FileInputStream ("images/BlueCard_front.png"), 161.5, 225, true, true));
        drawnCareerFront = new ImageView (new Image (new FileInputStream ("images/CareerCard_front.png"), 161.5, 225, true, true));
        drawnSalaryFront = new ImageView (new Image (new FileInputStream ("images/SalaryCard_front.png"), 161.5, 225, true, true));
        // INITIALIZE TEXT: CARD DESCRIPTION
        drawnCardName = new Text();
        drawnCardName.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 23));
        drawnCardName.setWrappingWidth (120);
        drawnCardValue = new Text ();
        drawnCardValue.setFont (Font.loadFont(new FileInputStream ("fonts/GOTHIC.ttf"), 20));
        drawnCardValue.setWrappingWidth(120);
        drawnCardDesc = new Text ();
        drawnCardDesc.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHICI.ttf"), 16));
        drawnCardDesc.setWrappingWidth(120);
        // INITIALIZE BUTTON: OKAY BUTTON
        drawnCardOkayBtn = new Button("Okay.");
        drawnCardOkayBtn.setId ("drawnCardOkay");
        drawnCardOkayBtn.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 20));
        drawnCardOkayBtn.setStyle ("-fx-background-color: #F5DEB3; -fx-border-color: black; -fx-border-width: 1px");
        drawnCardOkayBtn.setPrefWidth (140);
        // SETTING UP OF STAGE
        drawnCardStage.initStyle (StageStyle.UNDECORATED);
        drawnCardStage.setResizable (false);
        drawnCardStage.getIcons ().add (
                new Image (new FileInputStream ("images/lifeIcon.jpg"))
        );
        drawnCardStage.setScene( new Scene(drawnCardMainLayout, 200, 300));
    }

    /**
     * Updates and shows the card draw scene.
     *
     * @param cardType  the type of card to be drawn by the player
     */
    public void showCardDraw (String cardType) {
        if (cardType.equalsIgnoreCase ("ACTION")) {
            drawCardMainLayout.getChildren ().setAll (actionCardBack, drawBtn, reshuffleBtn);
        } else if (cardType.equalsIgnoreCase ("BLUE")) {
            drawCardMainLayout.getChildren ().setAll (blueCardBack, drawBtn, reshuffleBtn);
        } else if (cardType.equalsIgnoreCase ("CAREER")) {
            drawCardMainLayout.getChildren ().setAll (careerCardBack, drawBtn, reshuffleBtn);
        } else if (cardType.equalsIgnoreCase ("SALARY")) {
            drawCardMainLayout.getChildren ().setAll (salaryCardBack, drawBtn, reshuffleBtn);
        }

        drawCardStage.showAndWait ();
    }

    /**
     * Updates and shows the card that has been drawn by the player.
     *
     * @param card  the card drawn by the player
     */
    public void showDrawnCard (Card card) {
        // UPDATE DRAWN CARD DATA
        drawnCardName.setText(card.getName ());
        drawnCardDetails.getChildren().setAll (drawnCardName);
        if (card instanceof ValueCard) {
            if (((ValueCard) card).getAmount () != 0) {
                drawnCardValue.setText("$" + ((ValueCard) card).getAmount ());
                drawnCardDetails.getChildren ().add (drawnCardValue);
            }
        }
        else if (card instanceof SalaryCard) {
            drawnCardValue.setText ("$" + ((SalaryCard) card).getSalary());
            drawnCardDetails.getChildren ().add(drawnCardValue);
        }
        if (card instanceof CareerCard) {
            if (((CareerCard) card).isDegreeRequired ())
                drawnCardDesc.setText(card.getDescription () + "\n\nDegree Required: Yes");
            else drawnCardDesc.setText(card.getDescription () + "\n\nDegree Required: No");
        } else if (card instanceof BlueCard) {
            drawnCardDesc.setText ("Career: " + ((BlueCard) card).getCareer() + "\n\n" + card.getDescription ());
        } else drawnCardDesc.setText (card.getDescription ());
        drawnCardDetails.getChildren ().add (drawnCardDesc);
        // PREPARE ASSETS FOR DRAWN CARD WINDOW
        if (card instanceof ActionCard) {
            drawnCardLayout.getChildren ().setAll (drawnActionFront, drawnCardDetails);
        } else if (card instanceof BlueCard) {
            drawnCardLayout.getChildren ().setAll (drawnBlueFront, drawnCardDetails);
        } else if (card instanceof CareerCard) {
            drawnCardLayout.getChildren ().setAll (drawnCareerFront, drawnCardDetails);
        } else if (card instanceof SalaryCard) {
            drawnCardLayout.getChildren ().setAll (drawnSalaryFront, drawnCardDetails);
        }
        drawnCardMainLayout.getChildren ().setAll (drawnCardTitle, drawnCardLayout, drawnCardOkayBtn);
        // SHOW DRAWN CARD WINDOW
        drawnCardStage.showAndWait ();
    }

    /**
     * Closes the card draw window.
     */
    public void closeCardDraw () {
        drawCardStage.close ();
    }

    /**
     * Closes the drawn card window.
     */
    public void closeDrawnCard () {
        drawnCardStage.close ();
    }

    /**
     * Initializes the GUI elements of the player selection window.
     *
     * @throws  FileNotFoundException   if the files needed are not found
     */
    public void choosePlayerSetup () throws FileNotFoundException {
        // INITIALIZE + FORMATTING LAYOUTS
        choosePlayerStage = new Stage ();
        VBox choosePlayerMainLayout = new VBox (5);
        choosePlayerMainLayout.setStyle ("-fx-background-color: #ADD8E6;");
        choosePlayerMainLayout.setPadding (new Insets (5,5,5,5));
        choosePlayerMainLayout.setAlignment (Pos.CENTER);
        // INITIALIZE TEXT: WINDOW TITLE
        Text choosePlayerTitle = new Text ("CHOOSE A PLAYER: ");
        choosePlayerTitle.setFont (Font.loadFont (new FileInputStream("fonts/BRLNSR.ttf"), 15));
        // INITIALIZE + FORMATTING BUTTON: FIRST and SECOND PLAYER BUTTONS
        playerOneBtn = new Button ("playerOne");
        playerOneBtn.setId ("playerOne");
        playerOneBtn.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 20));
        playerOneBtn.setStyle ("-fx-background-color: #F5DEB3; -fx-border-color: black; -fx-border-width: 1px");
        playerOneBtn.setPrefWidth (140);
        playerTwoBtn = new Button ("playerTwo");
        playerTwoBtn.setId ("playerTwo");
        playerTwoBtn.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 20));
        playerTwoBtn.setStyle ("-fx-background-color: #F5DEB3; -fx-border-color: black; -fx-border-width: 1px");
        playerTwoBtn.setPrefWidth (140);
        // ADDING OF CHILDREN TO MAIN LAYOUT
        choosePlayerMainLayout.getChildren ().addAll (choosePlayerTitle, playerOneBtn, playerTwoBtn);
        // SETTING UP OF STAGE
        choosePlayerStage.initStyle (StageStyle.UNDECORATED);
        choosePlayerStage.setResizable (false);
        choosePlayerStage.getIcons ().add (
                new Image (new FileInputStream ("images/lifeIcon.jpg"))
        );
        choosePlayerStage.setScene (new Scene (choosePlayerMainLayout));
    }

    /**
     * Updates and shows the player selection window.
     *
     * @param players       the list of players in the game
     * @param playerTurn    index of current player playing
     */
    public void showPlayerChoices (ArrayList<Player> players, int playerTurn)
    {
        // SET INITIALLY TO BLANK
        playerOneBtn.setText ("");
        playerTwoBtn.setText ("");

        for (int k = 0; k < players.size (); k++)
            if (k != playerTurn) {
                if (playerOneBtn.getText ().equalsIgnoreCase (""))
                    playerOneBtn.setText (players.get (k).getName ());
                else playerTwoBtn.setText (players.get (k).getName ());
            }

        choosePlayerStage.showAndWait();
    }

    /**
     * Closes the player selection window.
     */
    public void closePlayerChoices () {
        choosePlayerStage.close ();
    }

    /**
     * Initializes the GUI elements of the life summary window.
     *
     * @throws FileNotFoundException    if the files needed are not found
     */
    public void lifeSummarySetup () throws FileNotFoundException {
        // INITIALIZE + FORMATTING LAYOUTS
        lifeSummaryStage = new Stage ();
        VBox lifeSummaryMainLayout = new VBox (15);
        lifeSummaryMainLayout.setAlignment (Pos.CENTER);
        lifeSummaryMainLayout.setStyle ("-fx-background-color: #ADD8E6");
        lifeSummaryMainLayout.setPadding (new Insets (20,20,20,20));
        HBox firstLayer = new HBox (10);
        HBox secondLayer = new HBox (10);
        HBox thirdLayer = new HBox (10);
        HBox fourthLayer = new HBox (10);
        // INITIALIZE + FORMATTING TEXT: PLAYER DETAILS
        playerLifeName = new Text ();
        playerLifeName.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHICI.ttf"), 18));
        playerLifeName.setWrappingWidth (150);
        playerLifeCash = new Text ();
        playerLifeCash.setFont (Font.loadFont(new FileInputStream ("fonts/GOTHICI.ttf"), 18));
        playerLifeCash.setWrappingWidth (150);
        playerLifeLoan = new Text ();
        playerLifeLoan.setFont (Font.loadFont(new FileInputStream ("fonts/GOTHICI.ttf"), 18));
        playerLifeLoan.setWrappingWidth (150);
        playerLifeCareer = new Text ();
        playerLifeCareer.setFont (Font.loadFont (new FileInputStream( "fonts/GOTHICI.ttf"), 18));
        playerLifeCareer.setWrappingWidth (150);
        playerLifeTax = new Text ();
        playerLifeTax.setFont(Font.loadFont (new FileInputStream ("fonts/GOTHICI.ttf"), 18));
        playerLifeTax.setWrappingWidth (150);
        playerLifeHouse = new Text  ();
        playerLifeHouse.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHICI.ttf"), 18));
        playerLifeHouse.setWrappingWidth (150);
        playerLifeMarried = new Text ();
        playerLifeMarried.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHICI.ttf"), 18));
        playerLifeMarried.setWrappingWidth (150);
        playerLifeFamily = new Text ();
        playerLifeFamily.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHICI.ttf"), 18));
        playerLifeFamily.setWrappingWidth (150);
        // INITIALIZE + FORMATTING TEXT: WINDOW TITLE
        Text lifeSummaryTitle = new Text ("LIFE SUMMARY");
        lifeSummaryTitle.setFont (Font.loadFont (new FileInputStream ("fonts/SFCartoonistHand.ttf"), 80));
        lifeSummaryTitle.setFill (Color.WHEAT);
        lifeSummaryTitle.setStroke (Color.BLACK);
        lifeSummaryTitle.setStrokeWidth (0.5);
        lifeSummaryTitle.setStyle ("-fx-border-color: black; -fx-border-width: 2px; -fx-font-weight: bold");
        // INITIALIZE + FORMATTING BUTTON: OKAY BUTTON
        summaryOkayBtn = new Button ("Okay!");
        summaryOkayBtn.setId ("summaryOkayBtn");
        summaryOkayBtn.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 20));
        summaryOkayBtn.setStyle ("-fx-background-color: #F5DEB3; -fx-border-color: black; -fx-border-width: 1px");
        summaryOkayBtn.setPrefWidth (140);
        // ADDING OF CHILDREN INTO SUB-LAYOUT
        firstLayer.getChildren ().addAll (playerLifeName, playerLifeCareer);
        firstLayer.setAlignment (Pos.CENTER);
        secondLayer.getChildren ().addAll (playerLifeCash, playerLifeHouse);
        secondLayer.setAlignment (Pos.CENTER);
        thirdLayer.getChildren ().addAll (playerLifeLoan, playerLifeMarried);
        thirdLayer.setAlignment (Pos.CENTER);
        fourthLayer.getChildren ().addAll (playerLifeTax, playerLifeFamily);
        fourthLayer.setAlignment (Pos.CENTER);
        // ADDING OF CHILDREN INTO MAIN LAYOUT
        lifeSummaryMainLayout.getChildren ().addAll (lifeSummaryTitle, firstLayer, secondLayer, thirdLayer, fourthLayer, summaryOkayBtn);
        // SETTING UP OF STAGE
        lifeSummaryStage.initStyle (StageStyle.UNDECORATED);
        lifeSummaryStage.setTitle ("Your life in a nutshell!");
        lifeSummaryStage.setResizable (false);
        lifeSummaryStage.getIcons ().add (
                new Image (new FileInputStream ("images/lifeIcon.jpg"))
        );
        lifeSummaryStage.setScene (new Scene (lifeSummaryMainLayout));
    }

    /**
     * Closes the life summary window.
     */
    public void closeLifeSummary () {
        lifeSummaryStage.close ();
    }

    /**
     * Updates and shows the life summary window.
     *
     * @param player    the player whose life summary will be shown
     */
    public void showLifeSummary (Player player) {
        playerLifeName.setText ("Name: \n" + player.getName ());
        if (player.getStatus ().getCareerCard () != null)
            playerLifeCareer.setText ("Career: \n" + player.getStatus ().getCareerCard ().getName ());
        playerLifeCash.setText ("Cash: \n$" + player.getCash ());
        if (player.getStatus ().getHouseCard () != null)
            playerLifeHouse.setText ("House: \n" + player.getStatus ().getHouseCard ().getName ());
        else playerLifeHouse.setText ("House: \n N/A");
        playerLifeLoan.setText ("Loan: \n$" + player.getLoanBalance ());
        if (player.getStatus ().isMarried ()) {
            playerLifeMarried.setText ("Married: \nYes");
            playerLifeFamily.setText ("Children: \n" + (player.getStatus ().getFamily () - 1));
        } else {
          playerLifeMarried.setText ("Married: \nNo");
          playerLifeFamily.setText ("Children: \n0");
        }
        playerLifeTax.setText ("Salary: \n$" + player.getStatus ().getCurrentSalary ());

        lifeSummaryStage.showAndWait ();
    }

    /**
     * Initializes the GUI elements of the announcement of winner scene
     *
     * @throws FileNotFoundException    if the files needed are not found
     */
    public void winnerSceneSetup () throws FileNotFoundException
    {
        // INITIALIZE MAIN LAYOUT FOR GAME WINNER SCENE
        BorderPane layout = new BorderPane ();
        layout.setPadding (new Insets (30, 30, 30, 30));

        // INITIALIZE LABEL FOR GAME WINNER SCENE
        Label lbl = new Label ("WINNER OF THE GAME!");
        lbl.setFont (Font.loadFont (new FileInputStream ("fonts/SFCartoonistHand.ttf"), 65));
        lbl.setTextFill (Color.MEDIUMSEAGREEN);

        // INITIALIZE MAIN MENU BUTTON FOR GAME WINNER SCENE
        winnerMenuBtn = new Button ("MAIN MENU");
        winnerMenuBtn.setId ("winnerMenuBtn");
        winnerMenuBtn.setFont (Font.loadFont (new FileInputStream ("fonts/SFCartoonistHand.ttf"), 28));
        winnerMenuBtn.setPrefSize (300, 30);
        winnerMenuBtn.setPadding (new Insets (1, 3, 1, 3));
        winnerMenuBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #FFFFF0");

        // INITIALIZE WINNER TEXT FOR GAME WINNER SCENE
        winnerText = new Text ();
        winnerText.setFont (Font.loadFont (new FileInputStream ("fonts/SFCartoonistHand.ttf"), 50));
        winnerText.setFill (Color.ORANGERED);

        // SET ELEMENTS TO MAIN LAYOUT
        layout.setTop (lbl);
        layout.setAlignment (lbl, Pos.TOP_CENTER);
        layout.setCenter (winnerText);
        layout.setAlignment (winnerText, Pos.CENTER);
        layout.setBottom (winnerMenuBtn);
        layout.setAlignment (winnerMenuBtn, Pos.BOTTOM_CENTER);

        // SETUP BACKGROUND FOR GAME WINNER SCENE
        BackgroundImage bg = new BackgroundImage (
                new Image (new FileInputStream ("images/winnerBG.gif")), BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize (700, 450, false, false, false, false)
        );

        layout.setBackground (new Background (bg));

        // INITIALIZE AND SETUP SCENE
        winnerScene = new Scene (layout);
    }

    /**
     * Initializes the GUI elements of the Career and Salary cards selection window.
     *
     * @throws FileNotFoundException    if the files needed are not found
     */
    public void chooseTwoCardsSetup () throws FileNotFoundException {
        // INITIALIZE + FORMATTING LAYOUTS
        chooseTwoCardsStage = new Stage ();
        VBox chooseTwoCardsMainLayout = new VBox (5);
        chooseTwoCardsMainLayout.setAlignment (Pos.CENTER);
        chooseTwoCardsMainLayout.setStyle ("-fx-background-color: #ADD8E6");
        chooseTwoCardsMainLayout.setPadding (new Insets (20,20,20,20));
        HBox chooseTwoCardsBtnLayout = new HBox (20);
        chooseTwoCardsBtnLayout.setAlignment (Pos.CENTER);
        HBox firstSet = new HBox (4);
        HBox secondSet = new HBox (4);
        HBox cardChoices = new HBox (20);
        StackPane firstCareer = new StackPane ();
        StackPane firstSalary = new StackPane ();
        StackPane secondCareer = new StackPane ();
        StackPane secondSalary = new StackPane ();
        VBox firstCareerDetails = new VBox (10);
        firstCareerDetails.setPadding (new Insets (3,3,0,7));
        VBox secondCareerDetails = new VBox (10);
        secondCareerDetails.setPadding (new Insets (3,3,0,7));
        // INITIALIZE + FORMATTING TEXT: WINDOW TITLE
        chooseTwoCardsTitle = new Text ("CHOOSE CAREER AND SALARY CARDS:");
        chooseTwoCardsTitle.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 30));
        // INITIALIZE + FORMATTING TEXT: CARD CONTENTS
        firstCareerName = new Text ();
        firstCareerName.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHICB.ttf"), 20));
        firstCareerName.setWrappingWidth (120);
        firstCareerDesc = new Text ();
        firstCareerDesc.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHIC.ttf"), 16));
        firstCareerDesc.setWrappingWidth (125);
        firstSalaryVal = new Text ();
        firstSalaryVal.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHICB.ttf"), 28));
        secondCareerName = new Text ();
        secondCareerName.setFont(Font.loadFont (new FileInputStream ("fonts/GOTHICB.ttf"), 20));
        secondCareerName.setWrappingWidth (120);
        secondCareerDesc = new Text ();
        secondCareerDesc.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHIC.ttf"), 16));
        secondCareerDesc.setWrappingWidth (125);
        secondSalaryVal = new Text ();
        secondSalaryVal.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHICB.ttf"), 28));
        // INITIALIZE IMAGEVIEW: CARD IMAGES
        ImageView firstCareerCard = new ImageView (new Image (new FileInputStream ("images/CareerCard_front.png"),161.5,225,true, true));
        ImageView secondCareerCard = new ImageView (new Image (new FileInputStream ("images/CareerCard_front.png"),161.5,225,true, true));
        ImageView firstSalaryCard = new ImageView (new Image (new FileInputStream ("images/SalaryCard_front.png"),161.5,225,true, true));
        ImageView secondSalaryCard = new ImageView (new Image (new FileInputStream ("images/SalaryCard_front.png"),161.5,225,true, true));
        // INITIALIZE BUTTONS: CHOICE BUTTONS
        firstSetBtn = new Button ("CAREER AND SALARY #1");
        firstSetBtn.setId ("firstSetBtn");
        firstSetBtn.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 20));
        firstSetBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #F5DEB3");
        firstSetBtn.setPrefWidth (286);
        secondSetBtn = new Button ("CAREER AND SALARY #2");
        secondSetBtn.setId ("secondSetBtn");
        secondSetBtn.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 20));
        secondSetBtn.setStyle ("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #F5DEB3");
        secondSetBtn.setPrefWidth (286);
        // ADDING OF CHILDREN TO CARD INFO LAYOUT
        firstCareerDetails.getChildren ().addAll (firstCareerName, firstCareerDesc);
        secondCareerDetails.getChildren ().addAll (secondCareerName, secondCareerDesc);
        // ADDING OF CHILDREN TO CARD LAYOUT
        firstCareer.getChildren ().addAll (firstCareerCard, firstCareerDetails);
        firstSalary.getChildren ().addAll (firstSalaryCard, firstSalaryVal);
        secondCareer.getChildren ().addAll (secondCareerCard, secondCareerDetails);
        secondSalary.getChildren ().addAll (secondSalaryCard, secondSalaryVal);
        // ADDING OF CHILDREN TO CARD SET LAYOUT
        firstSet.getChildren ().addAll (firstCareer, firstSalary);
        secondSet.getChildren ().addAll (secondCareer, secondSalary);
        cardChoices.getChildren ().addAll (firstSet, secondSet);
        // ADDING OF CHILDREN TO BUTTON LAYOUT
        chooseTwoCardsBtnLayout.getChildren ().addAll (firstSetBtn, secondSetBtn);
        // ADDING OF CHILDREN TO MAIN LAYOUT
        chooseTwoCardsMainLayout.getChildren ().addAll (chooseTwoCardsTitle, cardChoices, chooseTwoCardsBtnLayout);
        // SETTING UP OF STAGE
        chooseTwoCardsStage.initStyle (StageStyle.UNDECORATED);
        chooseTwoCardsStage.setResizable (false);
        chooseTwoCardsStage.setScene (new Scene (chooseTwoCardsMainLayout));
    }

    /**
     * Updates and shows the Career and Salary card selection window.
     *
     * @param firstCareer   the first career card to be shown
     * @param firstSalary   the first salary card to be shown
     * @param secondCareer  the second career card to be shown
     * @param secondSalary  the second salary card to be shown
     */
    public void showTwoCardsChoices (CareerCard firstCareer, SalaryCard firstSalary,
                                     CareerCard secondCareer, SalaryCard secondSalary) {
        firstCareerName.setText (firstCareer.getName ());
        firstCareerDesc.setText (firstCareer.getDescription () + "\n\nMax Salary: $" +
                (firstCareer.getMaxPayRaise () * firstSalary.getPayRaise () + firstSalary.getSalary ()) +
                "\nTaxes Due: \n$" + firstSalary.getTax ());
        firstSalaryVal.setText ("Salary: \n$" + firstSalary.getSalary ());
        secondCareerName.setText (secondCareer.getName ());
        secondCareerDesc.setText (secondCareer.getDescription () + "\n\nMax Salary: $" +
                (secondCareer.getMaxPayRaise () * secondSalary.getPayRaise () + secondSalary.getSalary ()) +
                "\nTaxes Due: \n$" + firstSalary.getTax ());
        secondSalaryVal.setText ("Salary: \n$" + secondSalary.getSalary ());

        if (secondCareer.isOwned ()) {
            firstSetBtn.setText ("CHANGE MY CARDS");
            secondSetBtn.setText ("KEEP MY CARDS");
            chooseTwoCardsTitle.setText ("CHANGE CAREER AND SALARY CARDS?");
        }
        chooseTwoCardsStage.showAndWait ();
    }

    /**
     * Closes the Career and Salary card selection window.
     */
    public void closeTwoCardsChoices () {
        chooseTwoCardsStage.close ();
    }

    /**
     * Initializes the GUI elements of the exit alert box window.
     *
     * @throws FileNotFoundException    if the files needed are not found
     */
    public void setupExitAlertBox () throws FileNotFoundException {
        // INITIALIZE + FORMATTING LAYOUTS
        exitAlertBoxStage = new Stage ();
        exitAlertBoxMainLayout = new VBox (10);
        exitAlertBoxMainLayout.setPadding (new Insets (10,10,10,10));
        exitAlertBoxMainLayout.setStyle ("-fx-background-color: #ADD8E6");
        exitButtonLayout = new HBox (7);
        exitButtonLayout.setAlignment (Pos.CENTER);
        // INITIALIZE + FORMTTING TEXT: EXIT MESSAGE
        exitMessage = new Text ("Do you really want to exit? \nAll progress will be lost!");
        exitMessage.setFont (Font.loadFont (new FileInputStream ("fonts/BRLNSR.ttf"), 20));
        // INITIALIZE + FORMATTING BUTTONS: YES AND NO BUTTONS
        yesExitBtn = new Button ("Yes");
        yesExitBtn.setId ("yesExitBtn");
        yesExitBtn.setFont (Font.loadFont (new FileInputStream ("fonts/SFCartoonistHand.ttf"), 20));
        yesExitBtn.setStyle ("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: #F5DEB3");
        yesExitBtn.setPrefWidth (100);
        noExitBtn = new Button ("No");
        noExitBtn.setId ("noExitBtn");
        noExitBtn.setFont (Font.loadFont (new FileInputStream ("fonts/SFCartoonistHand.ttf"), 20));
        noExitBtn.setStyle ("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: #F5DEB3");
        noExitBtn.setPrefWidth (100);
        // ADDING OF BUTTONS TO BUTTON LAYOUT
        exitButtonLayout.getChildren ().addAll (yesExitBtn, noExitBtn);
        // ADDING OF CHILDREN INTO MAIN LAYOUT
        exitAlertBoxMainLayout.getChildren ().addAll (exitMessage, exitButtonLayout);
        // SETTING UP OF STAGE
        exitAlertBoxStage.initStyle (StageStyle.UNDECORATED);
        exitAlertBoxStage.setScene (new Scene (exitAlertBoxMainLayout));
    }

    /**
     * Shows the exit alert box window.
     */
    public void showExitAlertBox () {
        exitAlertBoxStage.showAndWait ();
    }

    /**
     * Closes the exit alert box window.
     */
    public void closeExitAlertBox () {
        exitAlertBoxStage.close ();
    }

    /**
     * Initializes the GUI elements of the in-game board.
     *
     * @throws FileNotFoundException    if the needed files are not found
     */
    public void boardGUISetup () throws FileNotFoundException
    {
        // INITIALIZE BOARD (GUI) COMPONENTS
        GridPane boardGrid = new GridPane ();
        tiles = new ArrayList<> ();
        tileColors = new ArrayList<> ();
        cars = new ArrayList<> ();

        // SET TILES (STACKPANES) FOR GRID CONFIGURATION
        for (int k = 0; k < MAX_GRID; k++) {
            tiles.add (new StackPane ());
            tiles.get (k).setPrefSize (150, 150); // width and height of a single tile is 100
        }

        System.out.println ("Loading 25%...");

        // SET TILE COLORS (IMAGEVIEWS) FOR TILES (STACKPANES)
        for (int k = 0; k < TILE_URLS.length - 1; k++)
            for (int j = 0; j < MAX_GRID; j++)
                tileColors.add (new ImageView (
                        new Image (new FileInputStream (TILE_URLS[k]), 150, 150, true, true)
                ));

        // SET START AND RETIREMENT TILES FOR TILES (STACKPANES)
        tileColors.add (new ImageView (
                new Image (new FileInputStream (TILE_URLS[4]), 150, 150, true, true)
        ));
        tileColors.add (new ImageView (
                new Image (new FileInputStream (TILE_URLS[5]), 150, 150, true, true)
        ));

        System.out.println ("Loading 50%...");

        // SET PLAYER CARS (IMAGEVIEWS)
        for (int k = 0; k < CAR_URLS.length; k++)
            cars.add (new ImageView (
                    new Image (new FileInputStream (CAR_URLS[k]), 100, 100, true, true)
            ));

        System.out.println ("Loading 75%...");

        // SET TILES IN GRID
        int row = 0;
        int col = 0;
        for (int k = 0; k < 9; k++) {
            boardGrid.setConstraints (tiles.get (k), col, row);
            boardGrid.getChildren ().add (tiles.get (k));
            if (col != 2)
                col++;
            else {
                row++;
                col = 0;
            }
        }
        boardGrid.setHgap (5);
        boardGrid.setVgap (5);

        System.out.println ("Loading 100%...");

        // SET BOARD GRID (GRIDPANE) TO IN-GAME LAYOUT AND CENTER ON SCREEN
        boardGrid.setAlignment (Pos.CENTER);
        inGameLayout.setCenter (boardGrid);
        inGameLayout.setAlignment (boardGrid, Pos.CENTER);
    }

    /**
     * Clears the tiles images currently in the GUI scene layout.
     */
    public void clearTiles ()
    {
        for (int k = 0; k < tiles.size (); k++)
            if (tiles.get (k).getChildren ().size () > 0)
                tiles.get (k).getChildren ().clear ();
    }

    /**
     * Updates the contents of the in-game board.
     *
     * @param players                   the list of players in the game
     * @param playerTurn                the index of the player playing
     * @throws FileNotFoundException    if the files needed are not found
     */
    public void updateBoardGUI (ArrayList<Player> players, int playerTurn) throws FileNotFoundException
    {
        // for constructing of new grid
        int curBoardGridIndex;
        int colorIndex;
        Label tileNumber;

        // reset tile color image counters
        magentaTileCtr = 0;
        blueTileCtr = 9;
        greenTileCtr = 18;
        orangeTileCtr = 27;

        // get current board grid index according to current player in-turn's space number
        curBoardGridIndex = getBoardGridIndex (players.get (playerTurn).getSpaceNum ());

        clearTiles (); // clear tiles (stackpanes) for new grid generation

        // update new grid based on current board grid index acquired
        for (int k = 0; k < BOARD_GRID[curBoardGridIndex].length; k++) {
            if (BOARD_GRID[curBoardGridIndex][k] != -1) {
                colorIndex = getTileColorIndex (BOARD_GRID[curBoardGridIndex][k]);

                tileNumber = new Label (BOARD_GRID[curBoardGridIndex][k] + "");
                tileNumber.setFont (Font.loadFont (new FileInputStream ("fonts/GOTHICB.ttf"), 15));
                tiles.get (k).getChildren ().addAll (tileColors.get (colorIndex), tileNumber);
                tiles.get (k).setAlignment (tileNumber, Pos.TOP_CENTER);

                for (int j = 0; j < players.size (); j++)
                    if (players.get (j).getSpaceNum () == BOARD_GRID[curBoardGridIndex][k])
                        tiles.get (k).getChildren ().add (cars.get (j));
            }
        }
    }

    /**
     * Returns the index of the space number input from the board grid.
     *
     * @param   spaceNum  the space number to be located in the board grid
     * @return  the index of the space number in the board grid
     */
    private int getBoardGridIndex (int spaceNum)
    {
        for (int j = 0; j < BOARD_GRID.length; j++)
            for (int k = 0; k < BOARD_GRID[j].length; k++)
                if (spaceNum == BOARD_GRID[j][k])
                    return j;
        return -1;
    }

    /**
     * Returns the index of the color of a tile based on its number.
     *
     * @param   boardNum  the space number whose tile color is to be identified
     * @return  the index of the color attributed to the tile
     */
    private int getTileColorIndex (int boardNum)
    {
        for (int k = 0; k < MAGENTA_CONFIG.length; k++)
            if (boardNum == MAGENTA_CONFIG[k])
                return magentaTileCtr++;
        for (int k = 0; k < BLUE_CONFIG.length; k++)
            if (boardNum == BLUE_CONFIG[k])
                return blueTileCtr++;
        for (int k = 0; k < PD_CONFIG.length; k++)
            if (boardNum == PD_CONFIG[k])
                return greenTileCtr++;
        for (int k = 0; k < PR_CONFIG.length; k++)
            if (boardNum == PR_CONFIG[k])
                return greenTileCtr++;
        if (boardNum == START_CONFIG)
            return 36;
        if (boardNum == RET_CONFIG)
            return 37;
        return orangeTileCtr++;
    }

    /**
     * Sets the EventHandlers and ChangeListeners of the GUI.
     *
     * @param controller    the Controller that would handle the GUI elements
     */
    public void setEventHandlers (Controller controller)
    {
        // FOR MAIN MENU SCENE
        startBtn.setOnAction ((EventHandler) controller);
        instructionsBtn.setOnAction ((EventHandler) controller);
        exitBtn.setOnAction ((EventHandler) controller);

        // FOR INSTRUCTIONS SCENE
        mainMenuBtn.setOnAction ((EventHandler) controller);
        prevBtn.setOnAction ((EventHandler) controller);
        nextBtn.setOnAction ((EventHandler) controller);

        // FOR GAME SETUP SCENE
        confirmNumBtn.setOnAction ((EventHandler) controller);
        confirmNameBtn.setOnAction ((EventHandler) controller);
        backBtn.setOnAction ((EventHandler) controller);
        startGameBtn.setOnAction ((EventHandler) controller);
        backMenuBtn.setOnAction ((EventHandler) controller);
        for (int k = 0; k < playerTextFields.size (); k++)
            playerTextFields.get (k).textProperty ().addListener (controller);

        // FOR HOUSE CARD SELECTION
        firstCard.setOnMouseClicked ((EventHandler) controller);
        secondCard.setOnMouseClicked ((EventHandler) controller);
        thirdCard.setOnMouseClicked ((EventHandler) controller);

        // FOR JUNCTION SELECTION
        choiceOne.setOnAction ((EventHandler) controller);
        choiceTwo.setOnAction ((EventHandler) controller);

        // GENERIC ANNOUNCEMENT
        announceOkBtn.setOnAction ((EventHandler) controller);

        // SPIN THE WHEEL
        wheelPointer.setOnMouseClicked ((EventHandler) controller);
        spinNumberBtn.setOnAction ((EventHandler) controller);

        // DRAW CARDS
        drawBtn.setOnAction ((EventHandler) controller);
        reshuffleBtn.setOnAction ((EventHandler) controller);
        drawnCardOkayBtn.setOnAction ((EventHandler) controller);

        // CHOOSE PLAYER
        playerOneBtn.setOnAction ((EventHandler) controller);
        playerTwoBtn.setOnAction ((EventHandler) controller);

        // LIFE SUMMARY
        summaryOkayBtn.setOnAction ((EventHandler) controller);

        // CHANGE LISTENERS FOR PLAYER NAME INPUT
        playerTextFields.get (0).textProperty ().addListener (controller);
        playerTextFields.get (1).textProperty ().addListener (controller);
        playerTextFields.get (2).textProperty ().addListener (controller);

        // CAREER AND SALARY SELECTION
        firstSetBtn.setOnAction ((EventHandler) controller);
        secondSetBtn.setOnAction ((EventHandler) controller);

        // FOR WINNER SCENE
        winnerMenuBtn.setOnAction ((EventHandler) controller);

        // EXIT ALERT BOX
        yesExitBtn.setOnAction ((EventHandler) controller);
        noExitBtn.setOnAction ((EventHandler) controller);
        mainStage.setOnCloseRequest((EventHandler) controller);
        mainMenuExitBtn.setOnAction ((EventHandler) controller);
    }

    /**
     * Sets the main menu scene as the current main stage scene.
     */
    public void setMainMenuScene ()
    {
        mainStage.setScene (mainMenuScene);
    }

    /**
     * Sets the instructions scene as the current main stage scene.
     */
    public void setInstructionsScene ()
    {
        resetInstructionPage ();
        updateInstructionPage ();
        mainStage.setScene (instructionsScene);
    }

    /**
     * Sets the game setup scene as the current main stage scene.
     */
    public void setGameSetScene ()
    {
        firstGameSetScene ();
        mainStage.setScene (gameSetScene);
    }

    /**
     * Sets the in-game scene as the current main stage scene.
     */
    public void setInGameScene ()
    {
        mainStage.setScene (inGameScene);
    }

    /**
     * Sets the winner scene as the current main stage scene.
     *
     * @param winner the name of the winning player
     */
    public void setWinnerScene (String winner)
    {
        winnerText.setText (winner);
        mainStage.setScene (winnerScene);
    }

    /**
     * Returns the main stage of the GUI.
     *
     * @return  the main stage
     */
    public Stage getStage ()
    {
        return mainStage;
    }

    /**
     * Returns the number of players inputted in the GUI.
     *
     * @return  the number of players
     */
    public int getNumOfPlayersInput ()
    {
        return (int) numOfPlayersBox.getValue ();
    }

    /**
     * Returns the contents of the first text field, which is the name of the first player.
     *
     * @return  the name of the first player
     */
    public TextField getFirstTextField ()
    {
        return playerTextFields.get (0);
    }

    /**
     * Returns the contents of the second text field, which is the name of the second player.
     *
     * @return  the name of the second player
     */
    public TextField getSecondTextField ()
    {
        return playerTextFields.get (1);
    }

    /**
     * Returns the contents of the third text field, which is the name of the third player.
     *
     * @return  the name of the third player
     */
    public TextField getThirdTextField ()
    {
        return playerTextFields.get (2);
    }

    /**
     * Exits the main stage, including the program.
     */
    public void exitGame ()
    {
        System.exit (0);
    }

    /**
     * Assigns a random title for the main stage.
     */
    public void assignTitleForStage ()
    {
        mainStage.setTitle (STAGE_TITLES[(new Random ()).nextInt (STAGE_TITLES.length)]);
    }
}
