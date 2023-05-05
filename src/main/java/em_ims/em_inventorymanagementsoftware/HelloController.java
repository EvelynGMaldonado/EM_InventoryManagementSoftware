package em_ims.em_inventorymanagementsoftware;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Evelyn G Morrow.
 * @version 1.0.
 * Public class HelloController is used to retrieve and display the most up-to-date data on the parts and products tables after the user successfully signs in, as well as to manage some functionality such as delete, search, etc.
 */
public class HelloController implements Initializable {

    @FXML
    private Button landingPage_closeBtn;

    @FXML
    private AnchorPane landing_page;

    @FXML
    private Label messageLabel;

    @FXML
    private Button startBtn;

    @FXML
    private Button LogOut_btn;

    @FXML
    private Button addPartPageBtn;

    @FXML
    private Button addProductPageBtn;

    @FXML
    private Button homePage_addNewPartBtn;

    @FXML
    private Button homePage_addNewProductBtn;

    @FXML
    private Button homePage_closeBtn;

    @FXML
    private Button homePage_deletePartBtn;

    @FXML
    private Button homePage_deleteProductBtn;

    @FXML
    private Button homePage_modifyPartBtn;

    @FXML
    private Button homePage_modifyProductBtn;

    @FXML
    private Button homePage_searchPartBtn;

    @FXML
    private TextField homePage_searchPartInputField;

    @FXML
    private Button homePage_searchProductBtn;

    @FXML
    private TextField homePage_searchProductInputField;

    @FXML
    private StackPane home_page;

    @FXML
    private Button modifyPartPageBtn;

    @FXML
    private Button modifyProductPageBtn;

    @FXML
    private TableView<?> parts_tableView;

    @FXML
    private TableColumn<?, ?> parts_tableView_col_inventoryLevel;

    @FXML
    private TableColumn<?, ?> parts_tableView_col_partID;

    @FXML
    private TableColumn<?, ?> parts_tableView_col_partName;

    @FXML
    private TableColumn<?, ?> parts_tableView_col_priceUnit;

    @FXML
    private TableView<?> products_tableView;

    @FXML
    private TableColumn<?, ?> products_tableView_col_inventoryLevel;

    @FXML
    private TableColumn<?, ?> products_tableView_col_priceUnit;

    @FXML
    private TableColumn<?, ?> products_tableView_col_productID;

    @FXML
    private TableColumn<?, ?> products_tableView_col_productName;

    @FXML
    private Button settingsBtn;

    /**
     * Void closeBtnAction() method is used to close the landing page which will basically close the application.
     * @param event represents the event that triggers the action.
     */
    @FXML
    void closeBtnAction(ActionEvent event) {
        Stage stage = (Stage) landingPage_closeBtn.getScene().getWindow();
        stage.close();
    }

    /**
     * Void LoginButtonOnAction() method is used after the user clicks the start button.
     * @param event represents the event that triggers the action.
     * @throws IOException that would be produced by failed or interrupted input/output operations.
     */
    @FXML
    void LoginButtonOnAction(ActionEvent event) throws IOException {
        startBtn.getScene().getWindow().hide();

        //create new stage
        Stage ppMainWindow = new Stage();
        ppMainWindow.setTitle("Parts and Products - EM Inventory Management System");

        //create view for FXML
        FXMLLoader ppMainLoader = new FXMLLoader(getClass().getResource("home_page-parts&products.fxml"));

        //set view in ppMainWindow
        ppMainWindow.setScene(new Scene(ppMainLoader.load(), 800, 400));

        //launch
        ppMainWindow.show();
    }

    @FXML
    void KeyReleaseSearchPart(KeyEvent event) {

    }

    @FXML
    void KeyReleaseSearchProduct(KeyEvent event) {

    }

    /**
     * Void clickAddPartPageBtn() method is used to open Add Part Page.
     * e represents the event that triggers the action.
     * @exception IOException if an input or output error occurs.
     * @see IOException
     */
    @FXML
    void clickAddPartPageBtn (ActionEvent event) throws IOException {
        addPartPageBtn.getScene().getWindow().hide();
        //create new stage
        Stage addPartPageWindow = new Stage();
        addPartPageWindow.setTitle("Add Part - EM Inventory Management System");

        //create view for FXML
        FXMLLoader addPartPageLoader = new FXMLLoader(getClass().getResource("addPart_page.fxml"));

        //set view in ppMainWindow
        addPartPageWindow.setScene(new Scene(addPartPageLoader.load(), 600, 400));

        //launch
        addPartPageWindow.show();
    }

    /**
     * Void clickAddProductPageBtn() method is used to open Add Product Page.
     * e represents the event that triggers the action.
     * @exception IOException if an input or output error occurs.
     * @see IOException
     */
    @FXML
    void clickAddProductPageBtn(ActionEvent event) throws IOException {
        addProductPageBtn.getScene().getWindow().hide();
        //create new stage
        Stage addProductPageWindow = new Stage();
        addProductPageWindow.setTitle("Add Part - EM Inventory Management System");

        //create view for FXML
        FXMLLoader addProductPageLoader = new FXMLLoader(getClass().getResource("addProduct_page.fxml"));

        //set view in ppMainWindow
        addProductPageWindow.setScene(new Scene(addProductPageLoader.load(), 800, 610));

        //launch
        addProductPageWindow.show();
    }

    @FXML
    void clickModifyPartPageBtn(ActionEvent event) {

    }

    @FXML
    void clickModifyProductPageBtn(ActionEvent event) {

    }

    @FXML
    void deleteSelectedPart(ActionEvent event) {

    }

    @FXML
    void deleteSelectedProduct(ActionEvent event) {

    }

    /**
     * Void logOutBtnAction() method is used to log-out from the EM Inventory Management System Application.
     * The homepage is closed and the user is redirected to the landing page which has the option for sign-in or sign-up.
     */
    @FXML
    void logOutBtnAction() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure that you want to Log Out?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)) {

                //or go back to the landing page by doing ...
                LogOut_btn.getScene().getWindow().hide();
                //create new stage
                Stage landingPageWindow = new Stage();
                landingPageWindow.setTitle("Add Part - EM Inventory Management System");

                //create view for FXML
                FXMLLoader landingPageLoader = new FXMLLoader(getClass().getResource("landing_page.fxml"));

                //set view in ppMainWindow
                landingPageWindow.setScene(new Scene(landingPageLoader.load(), 600, 400));

                //launch
                landingPageWindow.show();
            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    /**
     * Public void initialize() method called to initialize a controller after its root element has been completely processed.
     * @param url is used to resolve relative paths for the root object. It is null if the url is not known.
     * @param rb is used to localize the root object, and it is null if the root object is not located.
     * @exception SQLException if a database error or other errors occur.
     * @see SQLException
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}