package em_ims.em_inventorymanagementsoftware;

import Model.Inventory;
import Model.Part;
import Model.Product;
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
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

/**
 * @author Evelyn G Morrow.
 * @version 1.1.
 * Public class AddProductController is used to add a new product to the EM management system database.
 * RUNTIME ERROR: The product id was displaying the same number. I just created a conditional: if the product id already exists, count++ so the next product id would be the previous number + 1.
 * FUTURE ENHANCEMENT: auto generate, auto increment, unique product id in the mysql database.
 */
public class AddProductController implements Initializable {
    @FXML
    private Button addPartPageBtn;

    @FXML
    private Button startBtn;

    @FXML
    private Button addProduct_cancelBtn;

    @FXML
    private TableView<Part> parts_tableView = new TableView<Part>();

    @FXML
    private TableColumn<Part, Integer> parts_tableView_col_inventoryLevel = new TableColumn<>("stock");

    @FXML
    private TableColumn<Part, Double> parts_tableView_col_priceUnit = new TableColumn<>("price_unit");

    @FXML
    private TableColumn<Part, Integer> parts_tableView_col_partID = new TableColumn<>("partID");

    @FXML
    private TableColumn<Part, String> parts_tableView_col_partName = new TableColumn<>("part_name");

    @FXML
    private TableView<Part> associatedParts_tableview = new TableView<Part>();

    @FXML
    private TableColumn<Part, Integer> associatedParts_tableView_col_inventoryLevel = new TableColumn<>("stock");

    @FXML
    private TableColumn<Part, Integer> associatedParts_tableView_col_partID = new TableColumn<>("partID");

    @FXML
    private TableColumn<Part, String> associatedParts_tableView_col_partName = new TableColumn<>("part_name");

    @FXML
    private TableColumn<Part, Double> associatedParts_tableView_col_priceUnit = new TableColumn<>("price_unit");

    @FXML
    private TextField addProduct_searchPartInputField;

    @FXML
    private TextField addProduct_setInventoryLevel;

    @FXML
    private TextField addProduct_setMax;

    @FXML
    private TextField addProduct_setMin;

    @FXML
    private TextField addProduct_setPrice;

    @FXML
    private TextField addProduct_setProductName;

    Product newProduct = new Product();

    HelloController helloController;
    private ListView<String> associatedPartsIDsByProduct = new ListView<String>();


    /**
     * Void clickAddAssociatedPartBtn() method is used after the user selects one row from the upper table and clicks the add button.
     * event represents the event that triggers the action.
     * there is a validation to verify if one row from the part table has been selected.
     * All the data from the selected row is retrieved.
     * addSelectedPart(singlePart, getSingleAssociatedPartID) method is called when the data is successfully retrieved from the parts table.
     * error alert is shown when there is no selected row or when the part has been already associated to the product.
     */
    @FXML
    void clickAddAssociatedPartBtn(ActionEvent event){
//        Inventory inventory = new Inventory();

        if(parts_tableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the part that you want to associate with your product.");
            alert.showAndWait();
            return;

        } else if(!parts_tableView.getSelectionModel().isEmpty()){
            Part singlePart = parts_tableView.getSelectionModel().getSelectedItem();
            String getSingleAssociatedPartID = String.valueOf(singlePart.getId());

            if(associatedPartsIDsByProduct.getItems().isEmpty() || associatedPartsIDsByProduct.getItems() == null) {

                addSelectedPart(singlePart, getSingleAssociatedPartID);

            }  else if(!associatedPartsIDsByProduct.getItems().isEmpty()) {

                if(!associatedPartsIDsByProduct.getItems().contains(getSingleAssociatedPartID)){

                    addSelectedPart(singlePart, getSingleAssociatedPartID);

                } else if(associatedPartsIDsByProduct.getItems().contains(getSingleAssociatedPartID)) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("The selected item is already part of this product. Please try again later.");
                    alert.showAndWait();

                    parts_tableView.getSelectionModel().clearSelection();

                    return;

                }
            }

        }
    }

    /**
     * private void addSelectedPart(Part singlePart, String getSingleAssociatedPartID) method is called by the clickAddAssociatedPartBtn(ActionEvent event) to add the associated parts to the associated table.
     * @param singlePart is passed by the clickAddAssociatedPartBtn(ActionEvent event) method.
     * @param getSingleAssociatedPartID is passed by the clickAddAssociatedPartBtn(ActionEvent event) method.
     * The bottom associated parts table updates and shows the current associated parts data after adding a new associated part, unless an exception is caught.
     */
    private void addSelectedPart(Part singlePart, String getSingleAssociatedPartID) {
        newProduct.addAssociatedPart(singlePart);

        associatedParts_tableview.setItems(newProduct.getAllAssociatedParts());

        associatedPartsIDsByProduct.getItems().add(getSingleAssociatedPartID);

        associatedParts_tableview.getSelectionModel().clearSelection();
        parts_tableView.getSelectionModel().clearSelection();
    }

    /**
     * Void addProduct_cancelBtnAction() method is used to go back to the landing page while still working on adding a new product to the database.
     * e represents the event that triggers the action.
     * A confirmation alert will be shown when the user clicks the cancel button.
     * The alert confirms that changes haven't been saved and takes us to homepage.
     * If the user clicks OK, then the addProduct Page will be hidden, and the user will be redirected to the landing page, unless an exception is caught. If the user press cancel, then it will return to the addProduct page to keep working on the data part input.
     */
    @FXML
    void addProduct_cancelBtnAction(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("New product hasn't been saved yet. Are you sure that you want to leave the window?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)) {
//              go back to the landing page by doing ...
                addProduct_cancelBtn.getScene().getWindow().hide();
                //create new stage
                Stage ppMainWindow = new Stage();
                ppMainWindow.setTitle("Parts and Products - EM Inventory Management System");

                //create view for FXML
                FXMLLoader ppMainLoader = new FXMLLoader(getClass().getResource("home_page-parts&products.fxml"));

                //set view in ppMainWindow
                ppMainWindow.setScene(new Scene(ppMainLoader.load(), 800, 400));

                //launch
                ppMainWindow.show();
            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    /**
     * Void deleteSelectedAssociatedPart() method is used to delete the associated part from the selected row on the products_associated_parts table.
     * @param event represents the event that triggers the action.
     * A confirmation alert is displayed, if the user clicks ok then the part will be unassociated from the product, and the products_associated_parts table will be updated, unless an Exception is caught. If the user clicks cancel, then the action is aborted.
     * An error alert is displayed when no row has been selected.
     */
    @FXML
    void deleteSelectedAssociatedPart(ActionEvent event) {

        String getSingleAssociatedPartID = "";
        String getSingleAssociatedPartName = "";
        String getSingleAssociatedPartStock = "";
        String getSingleAssociatedPartPriceUnit = "";

        //check if a row has been selected
        if(!associatedParts_tableview.getSelectionModel().isEmpty()) {
            Part removeAssociatedPart = associatedParts_tableview.getSelectionModel().getSelectedItem();

            getSingleAssociatedPartID = String.valueOf(removeAssociatedPart.getId());
            System.out.println("the getSingleAssociatedPartID value on line 246 is: " + getSingleAssociatedPartID);
            getSingleAssociatedPartName = removeAssociatedPart.getName();
            System.out.println("the getSingleAssociatedPartName value on line 248 is: " + getSingleAssociatedPartName);
            getSingleAssociatedPartStock = String.valueOf(removeAssociatedPart.getStock());
            System.out.println("the getSingleAssociatedPartStock value on line 250 is: " + getSingleAssociatedPartStock);
            getSingleAssociatedPartPriceUnit = String.valueOf(removeAssociatedPart.getPrice());
            System.out.println("the getSingleAssociatedPartPriceUnit value on line 252 is: " + getSingleAssociatedPartPriceUnit);

            if(!associatedPartsIDsByProduct.getItems().isEmpty() && removeAssociatedPart != null) {
                try{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure that you want to remove this associated part from the current product?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if(option.get().equals(ButtonType.OK)) {
//                        Inventory.getAllAssociatedParts().remove(removeAssociatedPart);
                        associatedPartsIDsByProduct.getItems().remove(getSingleAssociatedPartID);
                        associatedParts_tableview.getItems().remove(removeAssociatedPart);

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Deletion information");
                        alert.setHeaderText(null);
                        alert.setContentText("Associated part has been successfully removed from the current product");
                        alert.showAndWait();

                        associatedParts_tableview.getSelectionModel().clearSelection();
                        parts_tableView.getSelectionModel().clearSelection();
                    } else {
                        return;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    e.getCause();
                }
            }

        } else if(associatedParts_tableview.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the data row part that you want to remove from your associated parts table.");
            alert.showAndWait();
        }
    }

    /**
     * Void clickSaveNewProductAndAssociatedParts() method is used to validate that none of the fields are empty, and that the correct data types have been used.
     * event represents the event that triggers the action (on click addProduct_saveBtn).
     * If all validations pass, then the validateProductName() method will be called; otherwise an error alert will be displayed.
     */
    @FXML
    void clickSaveNewProductAndAssociatedParts(ActionEvent event) {
        String min = addProduct_setMin.getText().trim();
        String max = addProduct_setMax.getText().trim();
        String stock = addProduct_setInventoryLevel.getText().trim();
        String price = addProduct_setPrice.getText().trim();
        int min_check;
        int max_check;
        int stock_check;
        double price_check;
        if(!addProduct_setProductName.getText().trim().isEmpty() || !addProduct_setInventoryLevel.getText().trim().isEmpty() || !addProduct_setPrice.getText().trim().isEmpty() || !addProduct_setMax.getText().trim().isEmpty() || !addProduct_setMin.getText().isEmpty()) {
            if(min.matches("\\d+") && max.matches("\\d+") && stock.matches("\\d+")){
                min_check = Integer.parseInt(min);
                max_check = Integer.parseInt(max);
                stock_check = Integer.parseInt(stock);
                //min validation --> min has to be >= 0
                if(min_check >= 0) {
                    //max validation --> max has to be > min
                    if(max_check > min_check) {
                        //inventory validation --> inventory level has to be >= than min, and <= than max
                        if(stock_check >= min_check && stock_check <= max_check){
                            if(price.matches("\\d+(\\.\\d*)?")){
                                price_check = Double.parseDouble(price);

                                //check if the part name is available or if it already exists using the validatePartName method
                                validateProductName(stock_check, min_check, max_check, price_check);
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error message");
                                alert.setHeaderText(null);
                                alert.setContentText("Your Price value should be enter with numbers, double values are prefered.");
                                alert.showAndWait();
                            }
                        } else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error message");
                            alert.setHeaderText(null);
                            alert.setContentText("Your Inventory value should be more or equal than your Min value, and less or equal than your Max value.");
                            alert.showAndWait();
                        }
                    } else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error message");
                        alert.setHeaderText(null);
                        alert.setContentText("Your Max value should be more than your Min value.");
                        alert.showAndWait();
                    }
                } else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Your Min value should be more or equal than 0.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Your must enter positive whole numbers only, for: Inventory Level, Min, and Max");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields.");
            alert.showAndWait();
        }
    }

    /**
     * Public void validateProductName() method is used to validate that the new product name does not exist in the EM database.
     * When the validation passes, the method registerNewProduct() is called, unless an Exception is caught.
     * When the validation does not pass, an error alert will show up, and the user will be requested to use a different name for the new product.
     */
    public void validateProductName(Integer stock_check, Integer min_check, Integer max_check, Double price_check) {
        System.out.println("we are into validateProductName() method on line 389!!");
        Inventory inventory = new Inventory();

        String verifyProductName = addProduct_setProductName.getText().trim().toLowerCase();
        String productName = "";
        System.out.println("the name of the verifyProductName on line 392 is: " + verifyProductName);

        if(!verifyProductName.isEmpty() && inventory.getAllProducts().isEmpty()) {
            productName = verifyProductName;
            System.out.println("the productName on line 398 is: " + productName);
            generateProductId(productName, stock_check, min_check, max_check, price_check);
        }
        else if(!verifyProductName.isEmpty() && !inventory.getAllProducts().isEmpty()){
            System.out.println("we are into validateProductName() method with no empty inventory on line 402!!");
            productName = "";
            for(int i = 0; i < Inventory.allProducts.size(); i++) {
                if(!Inventory.allProducts.get(i).getProduct_name().trim().toLowerCase().contains(verifyProductName)) {
                    System.out.println("line 406 -- we are into validateProductName() method with no empty inventory and desired product name does not match with any pre-existent product name");
                    productName = verifyProductName;
                    generateProductId(productName, stock_check, min_check, max_check, price_check);
                    break;
                } else if(Inventory.allProducts.get(i).getProduct_name().trim().toLowerCase().contains(verifyProductName)) {
                    System.out.println("line 411--- we are into validateProductName() method with no empty inventory and desired product name already exists");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Product Name already exists. Please try again.");
                    alert.showAndWait();
                    break;
                }
            }

        }

    }

    /**
     * Private void generateProductId(String productName) method is used to generate the unique product ID.
     * @param productName is passed once the validateProductName() method verifies that the product name is unique in our EM database.
     */
    private void generateProductId(String productName, Integer stock_check, Integer min_check, Integer max_check, Double price_check) {
        System.out.println("we are into generateProductId() method on line 441!! and the productName value we are working with is: " + productName);

        Inventory inventory = new Inventory();
        Integer setExistentHighestProductID = 0;
        System.out.println("line 459 --- The setExistentHighestProductID retrieved from the productsIDManagementList in hellocontroller is: " + setExistentHighestProductID);
        Integer productID = 0;

        if(inventory.getAllProducts().isEmpty()) {
            if(HelloController.productsIDManagementList.isEmpty()){
                setExistentHighestProductID = 0;
            } else if(!HelloController.productsIDManagementList.isEmpty()){
                setExistentHighestProductID = Collections.max(HelloController.productsIDManagementList);
            }

            if(!HelloController.productsIDManagementList.contains(setExistentHighestProductID)){
                productID = setExistentHighestProductID + 1;
                HelloController.productsIDManagementList.add(productID);
            }
            System.out.println("the productID value on line 473 when inventory is empty is: " + productID);
            registerNewProduct(productName, productID, stock_check, min_check, max_check, price_check);
        } else if(!inventory.getAllProducts().isEmpty()){
            setExistentHighestProductID = Collections.max(HelloController.productsIDManagementList);
            for(int i = 0; i < Inventory.allProducts.size(); i++) {
                if(Inventory.allProducts.get(i).getProductID() == setExistentHighestProductID) {
                    productID = setExistentHighestProductID + 1;
                    System.out.println("the productID value on line 480 is: " + productID);
                    if(!HelloController.productsIDManagementList.contains(productID)){
                        HelloController.productsIDManagementList.add(productID);
                    } else if(HelloController.productsIDManagementList.contains(productID)) {
                        setExistentHighestProductID = productID;
                        System.out.println("the setExistentHighestProductID value on line 465 is: " + setExistentHighestProductID);
                    }
                }
            }
            System.out.println("line 489 -- the productID value on line 460 is: " + productID);
            System.out.println("line 490 -- the productID and productName value on line 472 are: " + productID  + " " + productName);
            registerNewProduct(productName, productID, stock_check, min_check, max_check, price_check);
        }
    }

    /**
     * Public void registerNewProduct() method called after the product name validation is passed, and no exceptions were caught.
     * Once the data is inserted, the registerCurrentProductAssociatedParts() method will be called, if no exceptions are caught.
     * An information alert is displayed to notify that the new product has been successfully registered to the database.
     */
    private void registerNewProduct(String productName, Integer productID, Integer stock_check, Integer min_check, Integer max_check, Double price_check) {
        System.out.println("we are into private void registerNewProduct method on line 482! and the productID and productName values we are working with is: " + productID  + " " + productName);

        if(associatedPartsIDsByProduct.getItems().isEmpty() || associatedPartsIDsByProduct.getItems() == null) {
            Inventory.allProducts.add(new Product(
                    productID,
                    productName,
                    price_check,
                    stock_check,
                    min_check,
                    max_check
            ));
            System.out.println("line 513 --- a new product with none associated parts has been saved");
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful Product Registration");
                alert.setHeaderText(null);
                alert.setContentText("New Product with zero associated parts has been successfully added to EM Inventory Management System");
                alert.showAndWait();

                addProductRedirectsToEMIMSHomePage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(!associatedPartsIDsByProduct.getItems().isEmpty()) {
            Product newProductWithAssociatedParts = new Product(
                    productID,
                    productName,
                    price_check,
                    stock_check,
                    min_check,
                    max_check
            );
            int index = 0;
            while(index < associatedParts_tableview.getItems().size()) {
                Part partToAssociate = associatedParts_tableview.getItems().get(index);
                newProductWithAssociatedParts.addAssociatedPart(partToAssociate);
                index++;
            }
            Inventory.addProduct(newProductWithAssociatedParts);
            System.out.println("line 545--- a new product with associated parts has been saved");
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful Product Registration");
                alert.setHeaderText(null);
                alert.setContentText("New Product with associated parts has been successfully added to EM Inventory Management System");
                alert.showAndWait();
                addProductRedirectsToEMIMSHomePage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Void keySearchPart() method is used to find a part row by typing information in the input field.
     * e represents the event that triggers the action.
     * @exception SQLException if a database error or other errors occur.
     * @see SQLException
     */
    @FXML
    void keySearchPart(ActionEvent event) {

////        parts_tableView.getItems().clear();
        String text = addProduct_searchPartInputField.getText().trim().toLowerCase();
        ObservableList<Part> partInventorySearchList = Inventory.lookupPart(text);
        if(partInventorySearchList.size() == 0){
            try {
                int partID = Integer.parseInt(text);
                Part searchPartById = Inventory.lookupPart(partID);

                if(searchPartById != null) {
                    partInventorySearchList.add(searchPartById);
                }
            } catch (NumberFormatException e){
                e.printStackTrace();
                e.getCause();
            }
        }
        parts_tableView.setItems(partInventorySearchList);
    }

    //Navigation
    /**
     * Void addProduct_addPartBtnAction() method is used to call the addProductRedirectsToAddPartPage();, unless an exception is caught.
     * @param event represents the event that triggers the action.
     * A confirmation alert is displayed.
     */
    @FXML
    void addProduct_addPartBtnAction(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("New product hasn't been saved yet. Are you sure that you want to switch to the Add Part window?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)) {
                addProductRedirectsToAddPartPage();
            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    /**
     * When the modify part button is clicked, an error alert will be displayed.
     * e represents the event that triggers the action.
     */
    @FXML
    void addProduct_modifyPartBtnAction_Error(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please go to Home and select the part that you want to modify.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    /**
     * When the modify product button is clicked, an error alert will be displayed.
     * event represents the event that triggers the action.
     */
    @FXML
    void addProduct_modifyProductBtnAction_Error(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please go to Home and select the product that you want to modify.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    //SIDE MENU
    /**
     * Public void addProductRedirectsToEMIMSHomePage() method called after the new product is successfully registered into the database, and no exceptions were caught.
     * The add Product page is hided, and the user is redirected to the homepage, where it can see the new part displaying on the parts table.
     * @throws IOException if an input or output error occurs
     * @see IOException
     */
    public void addProductRedirectsToEMIMSHomePage() throws IOException {
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
     * Void addProductRedirectsToAddPartPage() method is called by the addProduct_addPartBtnAction; and it is used to open Add Part Page, unless an exception is caught.
     * @throws IOException if an input or output error occurs
     * @see IOException
     */
    public void addProductRedirectsToAddPartPage() throws IOException {
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
     * Public void initialize() method called to initialize a controller after its root element has been completely processed.
     * @parameter url is used to resolve relative paths for the root object. It is null if the url is not known.
     * @parameter rb is used to localize the root object, and it is null if the root object is not located.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        associatedParts_tableview.getSelectionModel().clearSelection();
        parts_tableView.getSelectionModel().clearSelection();

        Inventory inventory = new Inventory();

        parts_tableView_col_partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        parts_tableView_col_partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        parts_tableView_col_inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        parts_tableView_col_priceUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        parts_tableView.setItems(inventory.getAllParts());

        associatedParts_tableView_col_partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedParts_tableView_col_partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedParts_tableView_col_inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedParts_tableView_col_priceUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}
