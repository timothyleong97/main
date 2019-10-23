//@@author nattanyz
package dream.fcard.gui;

import dream.fcard.model.cards.FlashCard;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class Gui {
    // the instance of the main window of the application, containing all UI components
    final MainWindow mainWindow;
    // todo: store windowContents here instead of mainWindow?

    public Gui(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    MainWindow getMainWindow() {
        return this.mainWindow;
    }

    void renderFront(FlashCard flashCard) {
        // todo: use functional interfaces, from renderCard?
        // get text to be rendered
        String cardText = flashCard.getFront();

        // generate Node representing flashCard's contents
        FlashCardDisplay node = new FlashCardDisplay(cardText);

        // display the Node in the Gui
        this.display(node);
    }

    void renderBack(FlashCard flashCard) {
        // todo: use functional interfaces, from renderCard?
        // get text to be rendered
        String cardText = flashCard.getBack();

        // generate Node representing flashCard's contents
        FlashCardDisplay node = new FlashCardDisplay(cardText);

        // display the Node in the Gui
        this.display(node);
    }

    // temporary method to render FlashCardDisplay without using FlashCard class
    void renderCard(String cardText) {
        FlashCardDisplay node = new FlashCardDisplay(cardText);
        this.display(node);
    }

    void display(Node node) {
        // get primary display area of MainWindow
        GridPane windowContents = getMainWindow().getWindowContents(); // todo: check coding standards?
        GridPane.setConstraints(node, 0,0);

        // remove anything currently in the display area
        windowContents.getChildren().clear();

        // show the Node in the display area
        windowContents.getChildren().add(node);
    }

    void setTitle(String title) {
        this.getMainWindow().setTitle(title);
    }
}
