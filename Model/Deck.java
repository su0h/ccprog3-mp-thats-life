package Model;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

/**
 * <b>Deck</b> handles the card decks of That's Life.
 * <p>
 * This class handles various card-related features. These features include (1)
 * card deck generation, (2) drawing of cards, and (3) shuffling of card decks.
 * A certain instance of Deck can only contain a single type of card.
 *
 * @author  Joshue Salvador A. Jadie
 * @author  Andre Dominic H. Ponce
 * @version %I%, %G%
 */
public class Deck
{
    /**
     * The cards present in this deck.
     */
    private ArrayList<Card> cards;
    /**
     * The card currently drawn from this deck.
     */
    private ArrayList<Card> drawnCards;
    /**
     * Used in generating random numbers in card generation and card drawing.
     */
    private Random randomizer;

    /**
     * Types of cards that are present in That's Life.
     */
    public static final String[] CARD_TYPES = {
        "ACTION", "BLUE", "HOUSE", "SALARY", "CAREER"
    };
    /**
     * Types of spaces that are present in That's Life.
     */
    public static final String[] DECK_ACTIONS = {
        "Orange Space", "Blue Space", "College Career Choice", "Job Search", "Buy House"
    };

    /**
     * The sole constructor of the object. Allocates a new Deck
     * object (creates a new card deck).
     *
     * @param type  the type of card to be generated
     */
    public Deck (String type)
    {
        cards = new ArrayList<> ();
        drawnCards = new ArrayList<> ();
        randomizer = new Random ();

        int k = 0;
        while (!type.equalsIgnoreCase (CARD_TYPES[k]))
            k++;

        switch (k) {
        case 0: generateActionDeck (); break;
        case 1: generateBlueDeck (); break;
        case 2: generateHouseDeck (); break;
        case 3: generateSalaryDeck (); break;
        default: generateCareerDeck ();
        }
    }

    /**
     * Generates and populates this instance of Deck with Action Cards. This also
     * autmatically shuffles the deck generated.
     */
    private void generateActionDeck ()
    {
        int row;
        int cardCtr = 0;
        int col;

        for (row = 0; row < 4; row++) {
            while (cardCtr < (ActionCard.ACTIONCARD_AMT * ActionCard.CARD_RATIOS[row])) {
                col = randomizer.nextInt (ActionCard.ACTIONS[row].length);
                cards.add (new ActionCard (ActionCard.ACTIONS[row][col]));
                cardCtr++;
            }
            cardCtr = 0;
        }

        shuffleDeck ();
    }

    /**
     * Generates and populates this instance of Deck with Blue Cards.
     */
    private void generateBlueDeck ()
    {
        for (int i = 0; i < BlueCard.ACTIONS.length; i++)
            cards.add (new BlueCard (BlueCard.ACTIONS[i], CareerCard.CAREERS[i]));
    }

    /**
     * Generates and populates this instance of Deck with House Cards.
     */
    private void generateHouseDeck ()
    {
        for (int i = 0; i < HouseCard.NAMES.length; i++)
            cards.add (new HouseCard (HouseCard.NAMES[i]));
    }

    /**
     * Generates and populates this instance of Deck with Salary Cards.
     */
    private void generateSalaryDeck ()
    {
        for (int i = 0; i < 20; i++)
            cards.add (new SalaryCard ());
    }

    /**
     * Generates and populates this instance of Deck with Career Cards.
     */
    private void generateCareerDeck ()
    {
        for (int i = 0; i < CareerCard.CAREERS.length; i++)
            cards.add (new CareerCard (CareerCard.CAREERS[i]));
    }

    /**
     * Gets a card from this deck and separates it temporarily  from the
     * original card deck. Numerous conditions are being checked by this method,
     * depending on the type of card this instance of Deck contains.
     */
    public void drawCard ()
    {
        Card card;

        if (cards.get (0) instanceof ActionCard)
            card = getActionCard ();
        else if (cards.get (0) instanceof PlayerCard)
            do {
                card = cards.get (randomizer.nextInt (cards.size ()));
            } while (isOwned (card) || drawnCards.contains (card));
        else card = cards.get (randomizer.nextInt (cards.size ()));

        drawnCards.add (card);
    }

    /**
     * Gets a card from this deck and separates it temporarily from the
     * original card deck. This is mainly used for House Card decks, where
     * the parameter index is used to determine which House Card to draw.
     *
     * @param index the index of the House Card to be drawn
     */
    public void drawCard (int index) // specifically for house card
    {
        drawnCards.add (cards.get (index));
    }

    /**
     * Gets a card from this deck and separates it temporarily from the original
     * card deck. This is mainly used for drawing Career Cards that does not require
     * a player degree.
     */
    public void drawNoDegreeCareer ()
    {
        boolean drawn;
        Card card;

        do {
            drawn = false;
            card = cards.get (randomizer.nextInt (cards.size()));
            if (drawnCards.size () > 0)
                drawn = drawnCards.get (0) == card;
        } while (isOwned (card) || ((CareerCard) card).isDegreeRequired () || drawnCards.contains (card));

        drawnCards.add (card);
    }

    /**
     * Returns the Action Card currently on top of the deck. This also checks
     * if the Action Card deck is already empty. If it is empty, this automatically
     * reshuffles the deck.
     *
     * @return  the Action Card on top of the deck
     */
    private Card getActionCard ()
    {
        if (ActionCard.actionDeckTop == ActionCard.ACTIONCARD_AMT) {
            shuffleDeck ();
            ActionCard.actionDeckTop = 0;
        }

        return cards.get (ActionCard.actionDeckTop++);
    }

    /**
     * Checks if a certain card is owned by a player. This is only applicable to
     * cards of type Player Card.
     *
     * @param   card  the card to be checked
     * @return  <code>true</code> if the card is owned; <code>false</code> otherwise.
     */
    private boolean isOwned (Card card)
    {
        return ((PlayerCard) card).isOwned ();
    }

    /**
     * Shuffles the cards inside this deck. Utilizes the Collections class to
     * effectively rearrange the cards.
     */
    public void shuffleDeck ()
    {
        Collections.shuffle (cards);
    }

    /**
     * Empties the drawn cards of this deck.
     */
    public void clearDrawnCards ()
    {
        if (drawnCards.size () > 0)
            drawnCards.clear ();
    }

    /**
     * Returns the first card drawn from this deck.
     *
     * @return  first drawn card from this deck
     */
    public Card getCardDrawn ()
    {
        return drawnCards.get (0);
    }

    /**
     * Returns the second card drawn from this deck.
     *
     * @return  second drawn card from this deck
     */
    public Card getSecondCardDrawn ()
    {
        return drawnCards.get (1);
    }

    /**
     * Returns all the cards currently in this deck. This is mainly used for
     * decks that contains House Cards.
     *
     * @return  all the cards of this deck
     */
    public ArrayList<Card> getCards ()
    {
        return cards;
    }

    @Override
    public String toString ()
    {
        String temp = "";

        temp += "\n//-----------------CARD DECK-----------------//" + "\n";

    	for (int i = 0; i < cards.size(); i++) {
          temp += (i + 1) + " : " + cards.get(i).getName() + "\n";
    	}

        return temp;
    }
}
