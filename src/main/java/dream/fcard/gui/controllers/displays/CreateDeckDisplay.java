package dream.fcard.gui.controllers.displays;

import java.io.IOException;
import java.util.function.Consumer;

import dream.fcard.gui.controllers.windows.CardCreatingWindow;
import dream.fcard.gui.controllers.windows.MainWindow;
import dream.fcard.model.ConsumerSchema;
import dream.fcard.model.Deck;
import dream.fcard.model.State;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * This class is used for editing an existing deck as well as creating a new deck.
 */
public class CreateDeckDisplay extends AnchorPane {
    @FXML
    private TextField deckNameInput;
    @FXML
    private Button onSaveDeck;
    @FXML
    private Button cancelButton;
    @FXML
    private Label deckName;
    @FXML
    private Label deckSize;
    @FXML
    private ScrollPane cardCreatingPane;

    private int numCards = 0;
    private CardCreatingWindow editingWindow;

    private Consumer<Integer> incrementNumCards = x -> {
        ++numCards;
        deckSize.setText(numCards + (numCards == 1 ? " card" : " cards"));
    };
    @SuppressWarnings("unchecked")
    private Consumer<Boolean> exitEditingMode = State.getState().getConsumer(ConsumerSchema.DISPLAY_DECKS);
    @SuppressWarnings("unchecked")
    private Consumer<String> displayMessage = State.getState().getConsumer(ConsumerSchema.DISPLAY_MESSAGE);
    @SuppressWarnings("unchecked")
    private Consumer<Boolean> clearMessage = State.getState().getConsumer(ConsumerSchema.CLEAR_MESSAGE);

    /**
     * Creates the form required to add questions to a deck.
     *
     */
    public CreateDeckDisplay() {
        try {
            clearMessage.accept(true);
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/Displays/"
                    + "CreateDeckDisplay.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            editingWindow = new CardCreatingWindow(incrementNumCards);
            cardCreatingPane.setContent(editingWindow);
            onSaveDeck.setOnAction(e -> onSaveDeck());
            cancelButton.setOnAction(e -> exitEditingMode.accept(true));
        } catch (IOException e) {
            //TODO: replace or augment with a logger
            e.printStackTrace();
        }
    }

    /**
     * Note that the temporary deck is inside CardCreatingWindow. This method pulls that Deck object out and saves it
     * to the State.
     */
    void onSaveDeck() {
        if (editingWindow != null) {
            Deck deck = editingWindow.getTempDeck();
            if (deck.getCards().size() == 0) {
                displayMessage.accept("No cards made. Exiting deck creation mode.");
            } else {
                String deckName = deckNameInput.getText();
                if (deckName.isBlank()) {
                    displayMessage.accept("You need to give your deck a name!");
                    return;
                }
                deck.setDeckName(deckName);
                State.getState().addDeck(deck);
                displayMessage.accept("Your new deck has been created!");
            }
            exitEditingMode.accept(true);
        }
    }

}