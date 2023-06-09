package em_ims.em_inventorymanagementsoftware;

import Model.*;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;


/**
 * Public class HelloController is used to retrieve and display the most up-to-date data on the parts and products tables after the user successfully signs in, as well as to manage some functionality such as delete, search, etc.
 * RUNTIME ERROR: Fetching the data and transfer it to the next controller. I found out I need to have all the variables into the method I will use to open th next window, and transfer it into the next window controller. The next window page needs to have the next window controller set up in order for us to be able to fetch the data.
 * FUTURE ENHANCEMENT: Implementing mysql database.
 * @author Evelyn G Morrow.
 * @version 1.1.
 */
public class HelloController implements Initializable {
    Inventory inventory;
    int index = -1;

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
    private TableView<Part> parts_tableView = new TableView<Part>();

    @FXML
    private TableColumn<Part, Integer> parts_tableView_col_inventoryLevel = new TableColumn<>("stock");

    @FXML
    private TableColumn<Part, Integer> parts_tableView_col_partID = new TableColumn<>("id");

    @FXML
    private TableColumn<Part, String> parts_tableView_col_partName = new TableColumn<>("name");

    @FXML
    private TableColumn<Part, Double> parts_tableView_col_priceUnit = new TableColumn<>("price");

    @FXML
    private TableView<Product> products_tableView = new TableView<Product>();

    @FXML
    private TableColumn<Product, Integer> products_tableView_col_inventoryLevel = new TableColumn<>("stock");

    @FXML
    private TableColumn<Product, Double> products_tableView_col_priceUnit = new TableColumn<>("price_unit");

    @FXML
    private TableColumn<Product, Integer> products_tableView_col_productID = new TableColumn<>("productID");

    @FXML
    private TableColumn<Product, String> products_tableView_col_productName = new TableColumn<>("product_name");

    @FXML
    private Button settingsBtn;

    private ObservableList<Part> partInventoryList = FXCollections.observableArrayList();
    private ObservableList<Product> productInventoryList = FXCollections.observableArrayList();
    private ObservableList<Part> partInventorySearchList = FXCollections.observableArrayList();
    private ObservableList<Product> productInventorySearchList = FXCollections.observableArrayList();

    public static List<Integer> productsIDManagementList = new LinkedList<Integer>();

    public void productsIDControl() {
        if(!inventory.getAllProducts().isEmpty()) {
            Integer productIdToStore = 0;
            for(int i = 0; i < productsIDManagementList.size(); i++){
                if(!productsIDManagementList.contains(productIdToStore)){
                    productsIDManagementList.add(productIdToStore);
                    Integer greatestProductID = Collections.max(productsIDManagementList);
                    for (int j = 0; j < Inventory.allProducts.size(); j++) {
                        if(Inventory.allProducts.get(j).getProductID() != greatestProductID && !productsIDManagementList.contains(Inventory.allProducts.get(j).getProductID())) {
                            productIdToStore = Inventory.allProducts.get(j).getProductID();
                            System.out.println("the productIdToStore value on line 147 is: " + productIdToStore);

                            productsIDManagementList.add(productIdToStore);
                        }
                    }
                    System.out.println("Max value on line 152 is: " + Collections.max(productsIDManagementList));
                    break;
                } else if(productsIDManagementList.contains(productIdToStore)){
                    Integer greatestProductID = Collections.max(productsIDManagementList);
                    for (int j = 0; j < Inventory.allProducts.size(); j++) {
                        if(Inventory.allProducts.get(j).getProductID() != greatestProductID && !productsIDManagementList.contains(Inventory.allProducts.get(j).getProductID())) {
                            productIdToStore = Inventory.allProducts.get(j).getProductID();
                            System.out.println("the productIdToStore value on line 159 is: " + productIdToStore);

                            productsIDManagementList.add(productIdToStore);
                        }
                    }
                    System.out.println("Max value on line 164 is: " + Collections.max(productsIDManagementList));
                    break;
                }
            }
        } else if(inventory.getAllProducts().isEmpty()) {
            Integer productIdToStore = 0;
            for(int i = 0; i < productsIDManagementList.size(); i++){
                if(!productsIDManagementList.contains(productIdToStore)){
                    productsIDManagementList.add(productIdToStore);
                    break;
                }
            }
        }
    }

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
     * The javadocs will be on the ... folder.
     * @param event represents the event that triggers the action.
     * @throws IOException that would be produced by failed or interrupted input/output operations.
     * RUNTIME ERROR:
     * FUTURE ENHANCEMENT:
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

    /**
     * Void keyReleaseSearchPart() method is used to find a part row by typing information in the input field.
     * e represents the event that triggers the action.
     * @exception SQLException if a database error or other errors occur.
     * @see SQLException
     */
    @FXML
    void keySearchPart(KeyEvent event) {
        System.out.println("we are into key search part method");
        String text = homePage_searchPartInputField.getText().trim();
        ObservableList<Part> searchedParts = Inventory.lookupPart(text);
        if(searchedParts.size() == 0){
            try {
                int partID = Integer.parseInt(text);
                Part findPart = Inventory.lookupPart(partID);

                if (findPart != null) {
                    searchedParts.add(findPart);
                }
            } catch (Exception e){}
        }
        parts_tableView.setItems(searchedParts);
    }

    /**
     * Void btnSearchProduct() method is used to find a product row by typing information in the input field and clicking the search button.
     * e represents the event that triggers the action.
     * @exception SQLException if a database error or other errors occur.
     * @see SQLException
     */
    @FXML
    void keySearchProduct(KeyEvent event) {
        System.out.println("we are into key search product method");
        String text = homePage_searchProductInputField.getText().trim();
        ObservableList<Product> searchedProducts = Inventory.lookupProduct(text);
        if(searchedProducts.size() == 0){
            try {
                int productID = Integer.parseInt(text);
                Product findProduct = Inventory.lookupProduct(productID);

                if (findProduct != null) {
                    searchedProducts.add(findProduct);
                }
            } catch (Exception e){}
        }
        products_tableView.setItems(searchedProducts);

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

    /**
     * Void clickAddProductPageBtn() method is used to open Add Product Page.
     * e represents the event that triggers the action.
     * @exception IOException if an input or output error occurs.
     * @see IOException
     */
    @FXML
    void clickModifyPartPageBtn(ActionEvent event) {
        Part selectedItem = parts_tableView.getSelectionModel().getSelectedItem();

        String getSinglePartID = "";
        String getSinglePartName = "";
        String getSinglePartStock = "";
        String getSinglePartPriceUnit = "";
        String getSinglePartMin = "";
        String getSinglePartMax = "";
        String getSinglePartMachineID = "";
        String getSinglePartCompanyName = "";
        if(selectedItem != null) {

            if(selectedItem instanceof InHouse) {
                InHouse newPart = (InHouse) selectedItem;
                getSinglePartMachineID = Integer.toString(newPart.machineID);
                System.out.println("the getSinglePartMachineID value is: " + getSinglePartMachineID);
                getSinglePartID = String.valueOf(selectedItem.getId());
                getSinglePartName = selectedItem.getName();
                getSinglePartStock = String.valueOf(selectedItem.getStock());
                getSinglePartPriceUnit = String.valueOf(selectedItem.getPrice());
                getSinglePartMin = String.valueOf(selectedItem.getMin());
                getSinglePartMax = String.valueOf(selectedItem.getMax());

                try{
                    modifyPartPageBtn.getScene().getWindow().hide();
                    //create new stage
                    Stage modifyPartPageWindow = new Stage();
                    modifyPartPageWindow.setTitle("Modify Part - EM Inventory Management System");

                    //create view for FXML
                    FXMLLoader modifyPartPageLoader = new FXMLLoader(getClass().getResource("modifyPart_page.fxml"));
                    ModifyPartController modifyPartController = new ModifyPartController(selectedItem, getSinglePartID, getSinglePartName, getSinglePartStock, getSinglePartPriceUnit, getSinglePartMin, getSinglePartMax, getSinglePartMachineID, getSinglePartCompanyName);
                    modifyPartPageLoader.setController(modifyPartController);
                //modifyPartController.checkingIfInOrOutSourced(getSinglePartMachineID, getSinglePartCompanyName);

                    //set view in ppMainWindow
                    modifyPartPageWindow.setScene(new Scene(modifyPartPageLoader.load(), 600, 400));

                    //launch
                    modifyPartPageWindow.show();

                } catch(IOException e) {
                    e.printStackTrace();
                    e.getCause();
                }
            } else if(selectedItem instanceof Outsourced) {
                Outsourced newPart = (Outsourced) selectedItem;
                getSinglePartCompanyName = newPart.company_name;
                System.out.println("the getSinglePartCompanyName value is: " + getSinglePartCompanyName);
                getSinglePartID = String.valueOf(selectedItem.getId());
                getSinglePartName = selectedItem.getName();
                getSinglePartStock = String.valueOf(selectedItem.getStock());
                getSinglePartPriceUnit = String.valueOf(selectedItem.getPrice());
                getSinglePartMin = String.valueOf(selectedItem.getMin());
                getSinglePartMax = String.valueOf(selectedItem.getMax());

                try{
                    modifyPartPageBtn.getScene().getWindow().hide();
                    //create new stage
                    Stage modifyPartPageWindow = new Stage();
                    modifyPartPageWindow.setTitle("Modify Part - EM Inventory Management System");

                    //create view for FXML
                    FXMLLoader modifyPartPageLoader = new FXMLLoader(getClass().getResource("modifyPart_page.fxml"));
                    ModifyPartController modifyPartController = new ModifyPartController(selectedItem, getSinglePartID, getSinglePartName, getSinglePartStock, getSinglePartPriceUnit, getSinglePartMin, getSinglePartMax, getSinglePartMachineID, getSinglePartCompanyName);
                    modifyPartPageLoader.setController(modifyPartController);
//                modifyPartController.checkingIfInOrOutSourced(getSinglePartMachineID, getSinglePartCompanyName);

                    //set view in ppMainWindow
                    modifyPartPageWindow.setScene(new Scene(modifyPartPageLoader.load(), 600, 400));

                    //launch
                    modifyPartPageWindow.show();

                } catch(IOException e) {
                    e.printStackTrace();
                    e.getCause();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the data row that you want to modify.");
            alert.showAndWait();
        }
    }

    /**
     * Void clickModifyProductPageBtn() method is used to open Modify Product Page.
     * e represents the event that triggers the action.
     * An error alert is displayed when no row has been selected.
     * @exception IOException if an input or output error occurs.
     * @see IOException
     * @exception SQLException if a database error or other errors occur.
     * @see SQLException
     */
    @FXML
    void clickModifyProductPageBtn(ActionEvent event) {
        index = products_tableView.getSelectionModel().getSelectedIndex();
        Product selectedItem = products_tableView.getSelectionModel().getSelectedItem();
        String getSingleProductID = "";
        String getSingleProductName = "";
        String getSingleProductStock = "";
        String getSingleProductPriceUnit = "";
        String getSingleProductMin = "";
        String getSingleProductMax = "";

        Integer selectedProductID = 0;

        if(index > -1) {

            try {
                getSingleProductID = String.valueOf(selectedItem.getProductID());
                getSingleProductName = selectedItem.getProduct_name();
                System.out.println("getProductName is: " + getSingleProductName);
                getSingleProductStock = String.valueOf(selectedItem.getStock());
                getSingleProductPriceUnit = String.valueOf(selectedItem.getPrice_unit());
                getSingleProductMin = String.valueOf(selectedItem.getMin());
                getSingleProductMax = String.valueOf(selectedItem.getMax());
                selectedProductID = selectedItem.getProductID();

                modifyProductPageBtn.getScene().getWindow().hide();
                //create new stage
                Stage modifyProductPageWindow = new Stage();
                modifyProductPageWindow.setTitle("Modify Product - EM Inventory Management System");

                //create view for FXML
                FXMLLoader modifyProductPageLoader = new FXMLLoader(getClass().getResource("modifyProduct_page.fxml"));

                ModifyProductController modifyProductController = new ModifyProductController(selectedItem, getSingleProductID, getSingleProductName, getSingleProductStock, getSingleProductPriceUnit, getSingleProductMin, getSingleProductMax, selectedProductID);
                modifyProductPageLoader.setController(modifyProductController);

                //set view in ppMainWindow
                modifyProductPageWindow.setScene(new Scene(modifyProductPageLoader.load(), 800, 610));

                //launch
                modifyProductPageWindow.show();

            } catch (IOException e) {
                e.printStackTrace();
                e.getCause();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the data row that you want to modify.");
            alert.showAndWait();
        }
    }

    /**
     * Void deleteSelectedPart() method is used to delete the records from the selected row on the parts table.
     * e represents the event that triggers the action.
     * A confirmation alert is displayed, if the user clicks ok then the part will be deleted, and the parts table will be updated. If the user clicks cancel, then the action is aborted.
     * An error alert is displayed when no row has been selected.
     */
    @FXML
    void deleteSelectedPart(ActionEvent event) {
        index = products_tableView.getSelectionModel().getSelectedIndex();
        System.out.println("the index is: " + index);
        Inventory inventory = new Inventory();
        Part selectedItem = parts_tableView.getSelectionModel().getSelectedItem();

        Part selectedPart = parts_tableView.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            try{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure that you want to delete this Part from the EM Inventory Management System?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)) {
                    Inventory.deletePart(selectedItem);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Deletion information");
                    alert.setHeaderText(null);
                    alert.setContentText("Part has been successfully removed from the EM Inventory Management System");
                    alert.showAndWait();

                    homePage_modifyPartBtn.getScene().getWindow().hide();
                    viewEMInventoryManagementSystem();
                } else {
                    return;
                }

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the data row that you want to delete.");
            alert.showAndWait();
        }

    }

    /**
     * Public void viewEMInventoryManagementSystem() method is called when the login validation passes.
     * The home page is displayed.
     * @exception IOException if an input or output error occurred.
     * @see IOException
     */
    public void viewEMInventoryManagementSystem() throws IOException {
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

    /**
     * Void deleteSelectedProduct() method is used to delete the product records from the selected row on the products table.
     * e represents the event that triggers the action.
     * A confirmation alert is displayed, if the user clicks ok then the product will be deleted, and the products table will be updated, unless an Exception is caught. If the user clicks cancel, then the action is aborted.
     * An error alert is displayed when no row has been selected.
     * An error alert is displayed when the selected product has an associated part.
     */
    @FXML
    void deleteSelectedProduct(ActionEvent event) {
        Inventory inventory = new Inventory();
        Product selectedProduct = products_tableView.getSelectionModel().getSelectedItem();

        if(products_tableView.getSelectionModel().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the product that you want to remove from the products table.");
            alert.showAndWait();

            return;

        } else if(!products_tableView.getSelectionModel().getSelectedItem().getAllAssociatedParts().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("The selected product " + selectedProduct.getProduct_name() + " has associated parts. Please modify it by deleting the associated parts, and try again.");
            alert.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure that you want to delete this product from the EM Inventory Management System?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)) {
                Inventory.deleteProduct(selectedProduct);

                try {
                    homePage_modifyPartBtn.getScene().getWindow().hide();
                    viewEMInventoryManagementSystem();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Deletion information");
                alert.setHeaderText(null);
                alert.setContentText(selectedProduct.getProduct_name() + " has been successfully removed from the EM Inventory Management System");
                alert.showAndWait();

                products_tableView.getSelectionModel().clearSelection();
                parts_tableView.getSelectionModel().clearSelection();
            }
        }
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
//        root.getStylesheets().add(getClass().getResource("startPageDesign.css").toExternalForm());
        parts_tableView.getSelectionModel().clearSelection();
        products_tableView.getSelectionModel().clearSelection();

        Inventory inventory = new Inventory();

        parts_tableView_col_partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        parts_tableView_col_partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        parts_tableView_col_inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        parts_tableView_col_priceUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        parts_tableView.setItems(inventory.getAllParts());


        products_tableView_col_productID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        products_tableView_col_productName.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        products_tableView_col_inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        products_tableView_col_priceUnit.setCellValueFactory(new PropertyValueFactory<>("price_unit"));

        products_tableView.setItems(inventory.getAllProducts());

        productsIDControl();

    }
}