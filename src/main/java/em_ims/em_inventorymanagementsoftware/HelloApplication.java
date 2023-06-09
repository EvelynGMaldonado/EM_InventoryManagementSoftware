package em_ims.em_inventorymanagementsoftware;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Public class HelloApplication is the primary class of the EM Inventory Management System.
 * Public class HelloApplication extends the Application class, which is a standard class in Java.
 * RUNTIME ERROR: Correctly displaying the landing page after the application is started. I changed the size of the scene for it to fully show the right dimensions, as well as the correct implementation of the css page.
 * FUTURE ENHANCEMENT: Implementing mysql database system for the backend.
 * @author Evelyn G Morrow.
 * @version 1.1.
 */
public class HelloApplication extends Application {

    /**
     * Public void start() method is called when the JavaFX application is started.
     * @param stage of the type Stage is taken.
     * The visual parts of the JavaFX Application (displayed).
     * @exception IOException if an input or output error occurred.
     * @see IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("landing_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("EM-Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Public static void main() method launches the application and let the developer passing command line parameters if needed.
     * The launch() method is a static method located in the Application class, and it detects from which class it is called, so it is not necessary to explicitly tell it what class to launch.
     */
    public static void main(String[] args) {
        launch();
    }
}