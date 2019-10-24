//@@author nattanyz
package dream.fcard.gui;

import dream.fcard.gui.components.CommandTextField;
import dream.fcard.gui.components.CommandTextFieldPlaceholder;
import dream.fcard.gui.components.ScrollablePane;
import dream.fcard.gui.components.TitleBar;
import dream.fcard.model.State;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow {
    Gui gui;
    private Stage primaryStage;
    private Scene scene;
    private State state;

    // containers
    private VBox window;
    private TitleBar titleBar;
    private ScrollablePane scrollablePane;
    private CommandTextFieldPlaceholder commandTextFieldPlaceholder;

    public MainWindow() {
        // temporary no-arg constructor
    }

    public MainWindow(Stage primaryStage, State state) {
        // todo: refactor linkages between MainWindow, Gui and UiManager
        this.primaryStage = primaryStage;
        this.state = state;
        this.gui = new Gui(this, state);
        onStartup();
        testUiComponents();
    }

    private void onStartup() {
        initializeStage();

        // set up containers for UI components
        initializeContainers();

        // set up initial UI components
        setupCommandTextField();
        titleBar.setTitle("Welcome!");

        // add UI components to scene
        setupScene();

        // finally, display main window
        primaryStage.show();
    }

    // temporary method for testing display of various UI components
    private void testUiComponents() {
        Gui.renderCard("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
//        Gui.renderCard("Pellentesque eu placerat urna, eu tincidunt magna.");
    }

    private void initializeStage() {
        primaryStage.setTitle("FlashCard Pro"); // set title of application window
        primaryStage.setMinHeight(GuiSettings.getMinHeight());
        primaryStage.setMinWidth(GuiSettings.getMinWidth());
    }

    private void initializeContainers() {
        window = new VBox();
        titleBar = new TitleBar();
        scrollablePane = new ScrollablePane();
        commandTextFieldPlaceholder = new CommandTextFieldPlaceholder();
    }

    private void setupCommandTextField() {
        CommandTextField commandTextField = new CommandTextField(state);
        commandTextFieldPlaceholder.add(commandTextField);
    }

    private void setupScene() {
        // add children to window
        window.getChildren().addAll(titleBar, scrollablePane, commandTextFieldPlaceholder);

        // display window
        scene = new Scene(window, 400, 400);
        primaryStage.setScene(scene);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    ScrollablePane getScrollablePane() {
        return this.scrollablePane;
    }

    // private void setAccelerators()
    // private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination)
    // private void setWindowDefaultSize(GuiSettings guiSettings)

    // FXML methods
    // public void handleHelp()
    // private void handleExit()
}
