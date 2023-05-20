package em_ims.em_inventorymanagementsoftware;

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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {
    @FXML
    private Button addPartPageBtn;

    @FXML
    private Button addProductPageBtn;

    @FXML
    private StackPane addProduct_page;

    @FXML
    private Button modifyProduct_closeBtn;

    @FXML
    private Button modifyPartPageBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button startBtn;

    @FXML
    private Button modifyProduct_cancelBtn;

    @FXML
    private TableView<Part> parts_tableView = new TableView<Part>();

    @FXML
    private TableColumn<Part, Integer> parts_tableView_col_inventoryLevel = new TableColumn<>("stock");

    @FXML
    private TableColumn<Part, BigDecimal> parts_tableView_col_priceUnit = new TableColumn<>("price_unit");

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
    private TableColumn<Part, BigDecimal> associatedParts_tableView_col_priceUnit = new TableColumn<>("price_unit");

    @FXML
    private TextField modifyProduct_productIDTextField;

    @FXML
    private TextField modifyProduct_setInventoryLevel;

    @FXML
    private TextField modifyProduct_setMax;

    @FXML
    private TextField modifyProduct_setMin;

    @FXML
    private TextField modifyProduct_setProductName;

    @FXML
    private TextField modifyProduct_setPriceUnit;

    @FXML
    private TextField modifyProduct_searchPartInputField;

    @FXML
    private Button modifyProduct_searchPartBtn;

    private ObservableList<Part> partInventorySearchList = FXCollections.observableArrayList();

    private ObservableList<Part> partInventoryList = FXCollections.observableArrayList();

    private ListView<String> associatedPartsIDsByProduct = new ListView<String>();

    /**
     * private final variables are not accessible outside the class.
     * private final variables values are final (no changes allowed) once the variable is initialized.
     */
    private final String getSingleProductID;
    private final String getSingleProductName;
    private final String getSingleProductStock;
    private final String getSingleProductPriceUnit;
    private final String getSingleProductMin;
    private final String getSingleProductMax;
    private final Integer selectedProductID;

    int index = -1;

    Product newProduct = new Product();
    Product product;

    private static Product productData = null;
    private static ObservableList<Part> associatedPartsData = null;

    /**
     * Public ModifyProductController Constructor accepts:
     * @param product  product parameter and initializes the private final String getSingleProductData variable.
     * @param getSingleProductID getSingleProductID parameter and initializes the private final String getSingleProductID variable.
     * @param getSingleProductName getSingleProductName parameter and initializes the private final String getSingleProductName variable.
     * @param getSingleProductStock getSingleProductStock parameter and initializes the private final String getSingleProductStock variable.
     * @param getSingleProductPriceUnit getSingleProductPriceUnit parameter and initializes the private final String getSingleProductPriceUnit variable.
     * @param getSingleProductMin getSingleProductMin parameter and initializes the private final String getSingleProductMin variable.
     * @param getSingleProductMax getSingleProductMax parameter and initializes the  private final String getSingleProductMax variable.
     */
    public ModifyProductController(Product product, String getSingleProductID, String getSingleProductName, String getSingleProductStock, String getSingleProductPriceUnit, String getSingleProductMin, String getSingleProductMax, Integer selectedProductID) {
        this.product = product;
        this.getSingleProductID = getSingleProductID;
        this.getSingleProductName = getSingleProductName;
        this.getSingleProductStock = getSingleProductStock;
        this.getSingleProductPriceUnit = getSingleProductPriceUnit;
        this.getSingleProductMin = getSingleProductMin;
        this.getSingleProductMax = getSingleProductMax;
        this.selectedProductID = selectedProductID;

        productData = product;
        System.out.println("line 145 --- the productData ID value is: " + productData.getProductID());

        associatedPartsData = FXCollections.observableArrayList(productData.getPassociatedParts());
//        associatedParts_tableview.setItems(associatedPartsData);
//        int index = 0;
//        while(index < associatedParts_tableview.getItems().size()) {
//            productData.setpAssociatedParts(associatedParts_tableview.getItems().get(index));
//            index++;
//        }

    }

    private void addSelectedPart(Part singlePart, String getSingleAssociatedPartID) {

        if(!associatedParts_tableview.getItems().contains(singlePart)){
            newProduct.setpAssociatedParts(singlePart);
            associatedParts_tableview.setItems(newProduct.getPassociatedParts());
            associatedPartsIDsByProduct.getItems().add(getSingleAssociatedPartID);

            associatedParts_tableView_col_partID.setCellValueFactory(new PropertyValueFactory<>("id"));
            associatedParts_tableView_col_partName.setCellValueFactory(new PropertyValueFactory<>("name"));
            associatedParts_tableView_col_inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
            associatedParts_tableView_col_priceUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

            associatedParts_tableview.getSelectionModel().clearSelection();
            parts_tableView.getSelectionModel().clearSelection();

        } else if(associatedParts_tableview.getItems().contains(singlePart)) {
            associatedParts_tableview.setItems(newProduct.getPassociatedParts());

            associatedParts_tableView_col_partID.setCellValueFactory(new PropertyValueFactory<>("id"));
            associatedParts_tableView_col_partName.setCellValueFactory(new PropertyValueFactory<>("name"));
            associatedParts_tableView_col_inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
            associatedParts_tableView_col_priceUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

            associatedParts_tableview.getSelectionModel().clearSelection();
            parts_tableView.getSelectionModel().clearSelection();
        }
    }

    /**
     * Void clickAddAssociatedPartBtn() method is located on the Modify Product page.
     * It checks if a row has been selected, retrieves data from parts table (choose part data), and inserts it into the associated parts data table.
     * Once the insertion is done, we call the displayAssociatedPartDataTableView() method, to display the updated associated parts table on the modify product page.
     * @param event represents the event that triggers the action.
     */
    @FXML
    void clickAddAssociatedPartBtn(ActionEvent event){
        Inventory inventory = new Inventory();

        if(parts_tableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the data row part that you want to associate with your product.");
            alert.showAndWait();
            return;

        } else if(!parts_tableView.getSelectionModel().isEmpty()){
            Part singlePart = parts_tableView.getSelectionModel().getSelectedItem();
            String getSingleAssociatedPartID = String.valueOf(singlePart.getId());

            if(associatedPartsIDsByProduct.getItems().isEmpty() || associatedPartsIDsByProduct.getItems() == null) {

                addSelectedPart(singlePart, getSingleAssociatedPartID);

            }  else if(!associatedPartsIDsByProduct.getItems().isEmpty() || associatedPartsIDsByProduct.getItems() != null) {

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
     * Void clickSaveUpdatedProductAndAssociatedParts() method is used to validate that none of the fields are empty, and that the correct data types have been used.
     * @param event represents the event that triggers the action.
     * If all validations pass, then the validateUpdatedProductNameAndProductID() method will be called; otherwise an error alert will be displayed.
     */
    @FXML
    void clickSaveUpdatedProductAndAssociatedParts(ActionEvent event) {
        //retrieve variables for max, min, and inventory validation.
        String min = modifyProduct_setMin.getText().trim();
        String max = modifyProduct_setMax.getText().trim();
        String stock = modifyProduct_setInventoryLevel.getText().trim();
        int min_check;
        int max_check;
        int stock_check;

        if(!modifyProduct_setProductName.getText().trim().isEmpty() || !modifyProduct_setInventoryLevel.getText().trim().isEmpty() || !modifyProduct_setPriceUnit.getText().trim().isEmpty() || !modifyProduct_setMax.getText().trim().isEmpty() || !modifyProduct_setMin.getText().isEmpty()) {
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
                            //check if the product name is available or if it already exists using the validateProductName method
                            validateUpdatedProductNameAndProductID();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error message");
                            alert.setHeaderText(null);
                            alert.setContentText("Your Inventory value should be more or equal than your Min value, and less or equal than your Max value.");
                            alert.showAndWait();
                        }
                    } else {
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
            } else{
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
     * Public void validateUpdatedProductNameAndProductID()  method is used to validate whether the updated product name matches with its ID.
     * If the validation passes (product name matches with its ID), the method updateProduct() is called, unless an Exception is caught.
     * When the validation does not pass(product name does not match with its ID), the method verifyIfProductNameAlreadyExists() is called, unless an Exception is caught.
     */
    public void validateUpdatedProductNameAndProductID() {
        System.out.println("we are into validateUpdatedProductNameAndProductID() method on line 352!!");
        Inventory inventory = new Inventory();

        String verifyProductName = modifyProduct_setProductName.getText().trim().toLowerCase();
        String productName = "";
        System.out.println("the name of the updated product on line 295 is: " + verifyProductName);

        Integer verifyProductID = Integer.valueOf(modifyProduct_productIDTextField.getText());
        Integer productID = 0;

        for(int i = 0; i < Inventory.allProducts.size(); i++) {
            if(!Inventory.allProducts.get(i).getProduct_name().trim().toLowerCase().contains(verifyProductName) && Inventory.allProducts.get(i).getProductID() == verifyProductID) {
                System.out.println("line 304 -- we are into validateUpdatedProductNameAndProductID() method where productID does not match with the updated product name");
                productName = verifyProductName;
                productID = verifyProductID;
                verifyIfProductNameAlreadyExists(productName, productID);

                break;
            } else if(Inventory.allProducts.get(i).getProduct_name().trim().toLowerCase().contains(verifyProductName) && Inventory.allProducts.get(i).getProductID() == verifyProductID) {
                System.out.println("line 308--- we are into validateProductName() method with no empty inventory and product name already exists");
                productID = verifyProductID;
                productName = verifyProductName;

                updateProduct(productID, productName);
            }
        }
    }

    /**
     * Public void verifyIfProductNameAlreadyExists() method is used to validate if the product name does not exist in the EM database.
     * When the validation passes(product name does not exist in the EM database), the method updateProduct() is called, unless an Exception is caught.
     * When the validation does not pass (product name already exists in the EM database), an error alert will show up, and the user will be requested to use a different name for the updated product.
     * @param productName
     * @param productID
     */
    public void verifyIfProductNameAlreadyExists(String productName, Integer productID) {
        for(int i = 0; i < Inventory.allProducts.size(); i++) {
            if(!Inventory.allProducts.get(i).getProduct_name().trim().toLowerCase().contains(productName)) {
                System.out.println("line 327 -- we are into verifyIfProductNameAlreadyExists(String productName, Integer productID) method. Product name does not exist");
                updateProduct(productID, productName);
                break;
            } else if(Inventory.allProducts.get(i).getProduct_name().trim().toLowerCase().contains(productName)) {
                System.out.println("line 331--- we are into verifyIfProductNameAlreadyExists(String productName, Integer productID) method. Product name already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Product Name already exists. Please try again.");
                alert.showAndWait();
                return;
            }
        }
    }

    /**
     * Private void updateProduct() method called after the product name validation is passed, and no exceptions were caught.
     * Public void updateProduct() is used to update the product specifications into the products table at the EM database.
     * Once the product data is successfully updated into the EM database:
     * an information alert is displayed to notify that the product has been successfully updated into the database, unless an Exception is caught.
     * updateCurrentProductAssociatedParts() method will be called, unless an Exception is caught.
     */
    private void updateProduct(Integer productID, String productName) {

        String modifyPage_productID = modifyProduct_productIDTextField.getText();
        String updatedProductName = modifyProduct_setProductName.getText();
        String updatedProductInventoryLevel = modifyProduct_setInventoryLevel.getText().trim();
        String updatedProductPriceUnit = modifyProduct_setPriceUnit.getText().trim();
        String updatedProductMax= modifyProduct_setMax.getText().trim();
        String updatedProductMin = modifyProduct_setMin.getText().trim();

        if(associatedPartsIDsByProduct.getItems().isEmpty() || associatedPartsIDsByProduct.getItems() == null) {
            Product updatedProductWithoutAssociatedParts = new Product(
                    productID,
                    productName,
                    Double.parseDouble(updatedProductPriceUnit),
                    Integer.parseInt(updatedProductInventoryLevel),
                    Integer.parseInt(updatedProductMin),
                    Integer.parseInt(updatedProductMax)
            );
            int index = 0;
            while(index < associatedParts_tableview.getItems().size()) {
                Part partToAssociate = associatedParts_tableview.getItems().get(index);
                updatedProductWithoutAssociatedParts.setpAssociatedParts(partToAssociate);
                index++;
            }
            Inventory.updateProduct(Inventory.getAllProducts().indexOf(productData), updatedProductWithoutAssociatedParts);
            System.out.println("line 382--- a updated product with zero associated parts has been saved");
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful Product Registration");
                alert.setHeaderText(null);
                alert.setContentText("New Product with zero associated parts has been successfully added to EM Inventory Management System");
                alert.showAndWait();

                //After successfully updating the product we redirect to the home_page, and we are able to see the updated data table
                modifyProductRedirectsToEMIMSHomePage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(!associatedPartsIDsByProduct.getItems().isEmpty()) {
            Product updatedProductWithAssociatedParts = new Product(
                    productID,
                    productName,
                    Double.parseDouble(updatedProductPriceUnit),
                    Integer.parseInt(updatedProductInventoryLevel),
                    Integer.parseInt(updatedProductMin),
                    Integer.parseInt(updatedProductMax)
            );
            int index = 0;
            while(index < associatedParts_tableview.getItems().size()) {
                Part partToAssociate = associatedParts_tableview.getItems().get(index);
                updatedProductWithAssociatedParts.setpAssociatedParts(partToAssociate);
                index++;
            }
            Inventory.updateProduct(Inventory.getAllProducts().indexOf(productData), updatedProductWithAssociatedParts);
            System.out.println("line 411--- a updated product with associated parts has been saved");
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful Product Registration");
                alert.setHeaderText(null);
                alert.setContentText("New Product with associated parts has been successfully added to EM Inventory Management System");
                alert.showAndWait();

                //After successfully updating the product we redirect to the home_page, and we are able to see the updated data table
                modifyProductRedirectsToEMIMSHomePage();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //remove associated part btn removes the data of the selected row from the associated part data table
    /**
     * Void deleteSelectedAssociatedPart() method is used to delete the associated part from the selected row on the modify_associated_parts table.
     * @param event represents the event that triggers the action.
     * A confirmation alert is displayed, if the user clicks ok then the part will be unassociated from the product, and the displayAssociatedPartDataTableView() method will be called, unless an Exception is caught. If the user clicks cancel, then the action is aborted.
     * An error alert is displayed when no row has been selected.
     */
    @FXML
    void deleteSelectedAssociatedPart (ActionEvent event) {
        Inventory inventory = new Inventory();

        Part removeAssociatedPart = associatedParts_tableview.getSelectionModel().getSelectedItem();

        String getSingleAssociatedPartID = "";
        String getSingleAssociatedPartName = "";

        if(associatedParts_tableview.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the data row part that you want to remove from your associated parts table.");
            alert.showAndWait();
        } else if(!associatedParts_tableview.getSelectionModel().isEmpty()) {
            getSingleAssociatedPartID = String.valueOf(removeAssociatedPart.getId());
            System.out.println("the getSingleAssociatedPartID value on line 464 is: " + getSingleAssociatedPartID);
            getSingleAssociatedPartName = removeAssociatedPart.getName();
            System.out.println("the getSingleAssociatedPartName value on line 466 is: " + getSingleAssociatedPartName);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure that you want to remove this associated part from the current product?");
            Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)) {

//                    if(associatedParts_tableview.getItems().contains(removeAssociatedPart)) {
//                        newProduct.removepAssociatedParts(removeAssociatedPart);
//                        associatedParts_tableview.setItems(newProduct.getPassociatedParts());
//
//                        associatedParts_tableView_col_partID.setCellValueFactory(new PropertyValueFactory<>("id"));
//                        associatedParts_tableView_col_partName.setCellValueFactory(new PropertyValueFactory<>("name"));
//                        associatedParts_tableView_col_inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
//                        associatedParts_tableView_col_priceUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
//
//                        associatedParts_tableview.getSelectionModel().clearSelection();
//                        parts_tableView.getSelectionModel().clearSelection();
//                    }

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
        }

        //check if a row has been selected
//        if(!associatedParts_tableview.getSelectionModel().isEmpty()) {
//            Part removeAssociatedPart = associatedParts_tableview.getSelectionModel().getSelectedItem();
//
//            getSingleAssociatedPartID = String.valueOf(removeAssociatedPart.getId());
//            System.out.println("the getSingleAssociatedPartID value on line 117 is: " + getSingleAssociatedPartID);
//            getSingleAssociatedPartName = removeAssociatedPart.getName();
//            System.out.println("the getSingleAssociatedPartName value on line 119 is: " + getSingleAssociatedPartName);
//
//            if(!associatedPartsIDsByProduct.getItems().isEmpty() && removeAssociatedPart != null) {
//                try{
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                    alert.setTitle("Confirmation Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Are you sure that you want to remove this associated part from the current product?");
//                    Optional<ButtonType> option = alert.showAndWait();
//
//                    if(option.get().equals(ButtonType.OK)) {
////                        Inventory.getAllAssociatedParts().remove(removeAssociatedPart);
//                        associatedPartsIDsByProduct.getItems().remove(getSingleAssociatedPartID);
//                        associatedParts_tableview.getItems().remove(removeAssociatedPart);
//
//                        alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("Deletion information");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Associated part has been successfully removed from the current product");
//                        alert.showAndWait();
//
//                        associatedParts_tableview.getSelectionModel().clearSelection();
//                        parts_tableView.getSelectionModel().clearSelection();
//                    } else {
//                        return;
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    e.getCause();
//                }
//            }
//
//        } else if(parts_tableView.getSelectionModel().isEmpty()){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error message");
//            alert.setHeaderText(null);
//            alert.setContentText("Please select the data row part that you want to remove from your associated parts table.");
//            alert.showAndWait();
//        }
    }

    /**
     * Void modifyProduct_cancelBtnAction() method is used to go back to the landing page while still working on updating a product in the database.
     * @param event represents the event that triggers the action.
     * A confirmation alert will be shown when the user clicks the cancel button. If the user clicks OK, then the modifyProduct Page will be hidden, and the user will be redirected to the landing page, unless an exception is caught. If the user press cancel, then it will return to the modifyProduct page to keep working on the product update input.
     */
    public void modifyProduct_cancelBtnAction(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Updated product hasn't been saved yet. Are you sure that you want to leave the window?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)) {
//              go back to the landing page by doing ...
                modifyProduct_cancelBtn.getScene().getWindow().hide();
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
     * Void modifyProduct_addProductBtnAction() method is used to call the modifyProductRedirectsToAddProductPage(), unless an exception is caught.
     * @param event represents the event that triggers the action.
     * A confirmation alert is displayed.
     */
    public void modifyProduct_addProductBtnAction(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Updated product hasn't been saved yet. Are you sure that you want to switch to the Add Product window?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)) {
                modifyProductRedirectsToAddProductPage();
            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    /**
     * Void modifyProduct_addPartBtnAction() method is used to call the modifyProductRedirectsToAddPartPage();, unless an exception is caught.
     * @param event represents the event that triggers the action.
     * A confirmation alert is displayed.
     */
    public void modifyProduct_addPartBtnAction(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Updated product hasn't been saved yet. Are you sure that you want to switch to the Add Part window?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)) {
                modifyProductRedirectsToAddPartPage();
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
     * @param event represents the event that triggers the action.
     */
    public void modifyProduct_modifyPartBtnAction_Error(ActionEvent event) {
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
     * Void keyReleaseSearchPart() method is used to find a part row by typing information in the input field.
     * @param event represents the event that triggers the action.
     */
    @FXML
    void keyReleaseSearchPart(KeyEvent event) {
        //
//        parts_tableView.getItems().clear();
        String text = modifyProduct_searchPartInputField.getText().trim();
        Inventory inventory = new Inventory();
//        addStartDataTables(inventory);
//
        if(!text.isEmpty() && !inventory.getAllParts().isEmpty()) {

            inventory.keySearchPart(text);

            partInventorySearchList.setAll(inventory.getPartInventorySearch());

            parts_tableView_col_partID.setCellValueFactory(new PropertyValueFactory<>("id"));
            parts_tableView_col_partName.setCellValueFactory(new PropertyValueFactory<>("name"));
            parts_tableView_col_inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
            parts_tableView_col_priceUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

            parts_tableView.setItems(partInventorySearchList);

        } else if (!text.isEmpty() && inventory.getAllParts().isEmpty()){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("No parts have been added to the inventory system. Please try again later.");
            alert.showAndWait();

            parts_tableView.getItems().clear();
            modifyProduct_searchPartInputField.clear();

        } else {
            inventory = new Inventory();
//            addStartDataTables(inventory);
            parts_tableView_col_partID.setCellValueFactory(new PropertyValueFactory<>("id"));
            parts_tableView_col_partName.setCellValueFactory(new PropertyValueFactory<>("name"));
            parts_tableView_col_inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
            parts_tableView_col_priceUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

            partInventoryList.setAll(inventory.getAllParts());
            parts_tableView.setItems(partInventoryList);

        }
    }

    //SIDE MENU
    /**
     * Public void modifyProductRedirectsToEMIMSHomePage() method called after successfully saving the updated new product into the database, and no exceptions were caught.
     * Modify Product page is hided, and the user is redirected to the homepage, where it can see the updated product displaying on the products table.
     * @throws IOException if an input or output error occurs
     * @see IOException
     */
    public void modifyProductRedirectsToEMIMSHomePage() throws IOException {
        startBtn.getScene().getWindow().hide();
//        Stage stage1 = (Stage) startBtn.getScene().getWindow();
//        stage1.close();
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
     * Void modifyProductRedirectsToAddPartPage() method is called by the modifyProduct_addPartBtnAction; and it is used to open Add Part Page, unless an exception is caught.
     * @throws IOException if an input or output error occurs
     * @see IOException
     */
    public void modifyProductRedirectsToAddPartPage() throws IOException {
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
     * Void modifyProductRedirectsToAddProductPage() method is called by the modifyProduct_addProductBtnAction; and it is used to open Add Product Page, unless an exception is caught.
     * @throws IOException if an input or output error occurs
     * @see IOException
     */
    public void modifyProductRedirectsToAddProductPage() throws IOException {
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
     * Public void initialize() method called to initialize a controller after its root element has been completely processed.
     * @param url is used to resolve relative paths for the root object. It is null if the url is not known.
     * @param rb is used to localize the root object, and it is null if the root object is not located.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        associatedParts_tableview.getSelectionModel().clearSelection();
        parts_tableView.getSelectionModel().clearSelection();
        associatedParts_tableview.getItems().remove(associatedPartsData);

        String getSingleAssociatedPartID = "";
        String getSingleAssociatedPartName = "";
        String getSingleAssociatedPartStock = "";
        String getSingleAssociatedPartPriceUnit = "";


        Inventory inventory = new Inventory();

        parts_tableView_col_partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        parts_tableView_col_partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        parts_tableView_col_inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        parts_tableView_col_priceUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        parts_tableView.setItems(inventory.getAllParts());


        modifyProduct_productIDTextField.setText(getSingleProductID);
        modifyProduct_setProductName.setText(getSingleProductName);
        modifyProduct_setInventoryLevel.setText(getSingleProductStock);
        modifyProduct_setPriceUnit.setText(getSingleProductPriceUnit);
        modifyProduct_setMin.setText(getSingleProductMin);
        modifyProduct_setMax.setText(getSingleProductMax);

//        associatedPartsData = FXCollections.observableArrayList(productData.getPassociatedParts());
        associatedParts_tableview.setItems(associatedPartsData);
        int index = 0;
        while(index < associatedParts_tableview.getItems().size()) {
            newProduct.setpAssociatedParts(associatedParts_tableview.getItems().get(index));
            index++;
        }

        associatedParts_tableView_col_partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedParts_tableView_col_partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedParts_tableView_col_inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedParts_tableView_col_priceUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
