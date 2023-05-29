package em_ims.em_inventorymanagementsoftware;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Public class ModifyPartController is used to retrieve and display the data row after selecting the part that the user needs to modify from the parts table, and click the modify part button.
 * RUNTIME ERROR: Correctly displaying the landing page after the application is started. I changed the size of the scene for it to fully show the right dimensions, as well as the correct implementation of the css page.
 * FUTURE ENHANCEMENT: Implementing mysql database system for the backend.
 * @author Evelyn G Morrow.
 * @version 1.1.
 */
public class ModifyPartController implements Initializable {
    Part selectedPart;
    int selectedIndex;
    Part part;

    @FXML
    private Button addPartPageBtn;

    @FXML
    private Button addProductPageBtn;

    @FXML
    private Button modifyPart_cancelBtn;

    @FXML
    private StackPane modifyPart_page;

    @FXML
    private Button modifyProductPageBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button startBtn;

    @FXML
    private Button modifyPart_closeBtn;

    @FXML
    private Button modifyPart_saveBtn;

    @FXML
    private ToggleGroup modifyPartSelectInHouseOutsourcedToggleGroup;

    @FXML
    private RadioButton modifyPartOutsourcedRadioBtn;

    @FXML
    private RadioButton modifyPartInHouseRadioBtn;

    @FXML
    private TextField modifyPart_partIDTextField;

    @FXML
    private TextField modifyPart_setPartName;

    @FXML
    private TextField modifyPart_setInventoryLevel;

    @FXML
    private TextField modifyPart_setMax;

    @FXML
    private TextField modifyPart_setMin;

    @FXML
    private TextField modifyPart_setPriceUnit;

    @FXML
    private TextField modifyPart_inputCompanyOrMachineInputField;

    @FXML
    private Label modifyPart_displayCompanyOrMachineLabel;

    /**
     * private final variables are not accessible outside the class.
     * private final variables values are final (no changes allowed) once the variable is initialized.
     */
    private final String getSinglePartID;
    private final String getSinglePartName;
    private final String getSinglePartStock;
    private final String getSinglePartPriceUnit;
    private final String getSinglePartMin;
    private final String getSinglePartMax;
    private final String getSinglePartMachineID;
    private final String getSinglePartCompanyName;
    private static Part partData = null;

    /**
     * Public ModifyPartController Constructor accepts:
     * @param part part parameter and initializes the private final String getSinglePartData variable.
     * @param getSinglePartID getSinglePartID parameter and initializes the private final String getSinglePartID variable.
     * @param getSinglePartName getSinglePartName parameter and initializes the private final String getSinglePartName variable.
     * @param getSinglePartStock getSinglePartStock parameter and initializes the private final String getSinglePartStock variable.
     * @param getSinglePartPriceUnit getSinglePartPriceUnit parameter and initializes the private final String getSinglePartPriceUnit variable.
     * @param getSinglePartMin getSinglePartMin parameter and initializes the private final String getSinglePartMin variable.
     * @param getSinglePartMax getSinglePartMax parameter and initializes the private final String getSinglePartMax variable.
     * @param getSinglePartMachineID getSinglePartMachineID parameter and initializes the private final String getSinglePartMachineID variable.
     * @param getSinglePartCompanyName getSinglePartCompanyName parameter and initializes the private final String getSinglePartCompanyName variable.
     *
     */
    public ModifyPartController(Part part, String getSinglePartID, String getSinglePartName, String getSinglePartStock, String getSinglePartPriceUnit, String getSinglePartMin, String getSinglePartMax, String getSinglePartMachineID, String getSinglePartCompanyName) {
        this.part = part;
        this.getSinglePartID = getSinglePartID;
        this.getSinglePartName = getSinglePartName;
        this.getSinglePartStock= getSinglePartStock;
        this.getSinglePartPriceUnit= getSinglePartPriceUnit;
        this.getSinglePartMin= getSinglePartMin;
        this.getSinglePartMax= getSinglePartMax;
        this.getSinglePartMachineID= getSinglePartMachineID;
        this.getSinglePartCompanyName= getSinglePartCompanyName;

        partData = part;
    }

    /**
     * Toggle Group - radio buttons functionality.
     * @param event represents the event that triggers the action.
     */
    public void displayMachineIDOrCompanyName_modifyPartPage(ActionEvent event) {
        if(modifyPartInHouseRadioBtn.isSelected()) {
            modifyPart_displayCompanyOrMachineLabel.setText("Machine ID:");
            modifyPart_inputCompanyOrMachineInputField.setText(getSinglePartMachineID);
        } else if(modifyPartOutsourcedRadioBtn.isSelected()) {
            modifyPart_displayCompanyOrMachineLabel.setText("Company Name:");
            modifyPart_inputCompanyOrMachineInputField.setText(getSinglePartCompanyName);
        }
    }

    /**
     * Void clickSaveUpdatedPartBtn() method is used to validate that none of the fields are empty, and that the correct data types have been used.
     * @param event represents the event that triggers the action.
     * If all validations pass, then the validateUpdatedPartNameAndPartID() method will be called; otherwise an error alert will be displayed.
     */
    @FXML
    public void clickSaveUpdatedPartBtn(ActionEvent event) {
        //retrieve variables for max, min, and inventory validation.
        String min = modifyPart_setMin.getText().trim();
        String max = modifyPart_setMax.getText().trim();
        String stock = modifyPart_setInventoryLevel.getText().trim();
        String price = modifyPart_setPriceUnit.getText().trim();
        int min_check;
        int max_check;
        int stock_check;
        double price_check;
        //Part Category Selection Validation - No null Accepted ~ it has to select inHouse or Outsourced
        if(modifyPartInHouseRadioBtn.isSelected() && modifyPart_inputCompanyOrMachineInputField.getText().trim().matches("\\d+") || modifyPartOutsourcedRadioBtn.isSelected()) {
            //Not null accepted Input validation checks that none of the fields are blank or empty...
            if(!modifyPart_setPartName.getText().trim().isEmpty() || !modifyPart_setInventoryLevel.getText().trim().isEmpty() || modifyPart_setPriceUnit.getText().trim().isEmpty() || !modifyPart_setMax.getText().trim().isEmpty() || !modifyPart_setMin.getText().trim().isEmpty() || !modifyPart_inputCompanyOrMachineInputField.getText().trim().isEmpty()) {
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
                                    validateUpdatedPartNameAndPartID(stock_check, min_check, max_check, price_check);

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
                    } else {
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
        } else {
            //alert error when part category hasn't been selected
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the Part Category: In-House (must enter only numbers) or Outsourced (accepts letters and numbers).");
            alert.showAndWait();
        }
    }

    /**
     * Public void validateUpdatedPartNameAndPartID()  method is used to validate whether the updated part name matches with its ID.
     * If the validation passes (part name matches with its ID), the method UpdatePart() is called, unless an Exception is caught.
     * When the validation does not pass(part name does not match with its ID), the method verifyIfPartNameAlreadyExists() is called, unless an Exception is caught.
     */
    public void validateUpdatedPartNameAndPartID(Integer stock_check, Integer min_check, Integer max_check, Double price_check) {
        System.out.println("we are into validateUpdatedPartNameAndPartID() method with no empty inventory on line 220!!");
        Inventory inventory = new Inventory();

        String verifyPartName = modifyPart_setPartName.getText().trim().toLowerCase();
        String partName = "";
        System.out.println("the name of the part on line 225 is: " + verifyPartName);

        String verifyPartID = modifyPart_partIDTextField.getText().trim();
        Integer partID = Integer.valueOf(verifyPartID);
        System.out.println("the id of the part on line 228 is: " + verifyPartID);

        for(int i = 0; i < Inventory.allParts.size(); i++) {
            //if updated part name matches with the ID... call the UpdatePart(); method
            if(Inventory.allParts.get(i).getId() == partID && Inventory.allParts.get(i).getName().trim().toLowerCase().contains(verifyPartName)) {
                String lastPartName = Inventory.allParts.get(i).getName();
                partName = lastPartName;
                verifyPartName = partName;
                System.out.println("line 233 -- we are into validateUpdatedPartNameAndPartID() getting the old part name of the currend part id which is: " + lastPartName);
                System.out.println("line 232 -- the partName is: " + partName);
                UpdatePart(verifyPartName, partID, stock_check, min_check, max_check, price_check);
                break;

            //if updated part name does not match with the ID... call the verifyIfPartNameAlreadyExists(); method
            } else if(Inventory.allParts.get(i).getId() == partID && !Inventory.allParts.get(i).getName().trim().toLowerCase().contains(verifyPartName)) {
                System.out.println("line 241 -- our updated part name does not match with the name previously related to our partID");
                partName = verifyPartName;
                System.out.println("line 242 -- the partName is: " + partName);
                verifyIfPartNameAlreadyExists(partName, partID, stock_check, min_check, max_check, price_check);
                break;
            }
        }
    };

    /**
     * Public void verifyIfPartNameAlreadyExists() method is used to validate if the part name does not exist in the EM database.
     * When the validation passes(part name does not exist in the EM database), the method UpdatePart() is called, unless an Exception is caught.
     * When the validation does not pass (part name already exists in the EM database), an error alert will show up, and the user will be requested to use a different name for the updated part.
     * @param partName
     */
    public void verifyIfPartNameAlreadyExists(String partName, Integer partID, Integer stock_check, Integer min_check, Integer max_check, Double price_check) {
        System.out.println("we are into verifyIfPartNameAlreadyExists() method on line 277 and are working with partName value: " + partName);

        try {
            for(int i = 0; i < Inventory.allParts.size(); i++) {
                //if updated part name does not exist in our DB, and it does not match with the current ID... then call the call the UpdatePart();
                if(!Inventory.allParts.get(i).getName().trim().toLowerCase().contains(partName)) {
                    System.out.println("line 287 -- we are into verifyIfPartNameAlreadyExists(String partName) method and verifyPartName value is: " + partName);
                    String verifyPartName = partName;
                    UpdatePart(verifyPartName, partID, stock_check, min_check, max_check, price_check);
                    break;

                //if updated part name already exists in our DB, but it does not match with the current ID ... show an error alert
                } else if(Inventory.allParts.get(i).getName().trim().toLowerCase().contains(partName)) {
                    System.out.println("line 295--- we are into verifyIfPartNameAlreadyExists(String partName) method and part name already exists");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Part Name already exists. Please try again.");
                    alert.showAndWait();
                    return;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    };

    /**
     * Public void UpdatePart() method called after the part name validation is passed, and no exceptions were caught.
     * Once the data is successfully updated into the EM database, the modifyPartRedirectsToEMIMSHomePage() method will be called, if no exceptions are caught.
     * An information alert is displayed to notify that the part has been successfully updated into the database.
     * @param verifyPartName is the new part name value
     */
    private void UpdatePart(String verifyPartName, Integer partID, Integer stock_check, Integer min_check, Integer max_check, Double price_check) {
        System.out.println("we are into private void UpdatePart(String verifyPartName) method on line 317!! and the verifyPartName value is: " + verifyPartName);

        String modifyPage_partID = modifyPart_partIDTextField.getText();
        String updateMachineID = modifyPart_inputCompanyOrMachineInputField.getText().trim();
        String updateCompanyName = modifyPart_inputCompanyOrMachineInputField.getText().trim().toLowerCase();

        if(modifyPartInHouseRadioBtn.isSelected()) {
            InHouse inhouse = new InHouse(
                    partID,
                    verifyPartName,
                    price_check,
                    stock_check,
                    min_check,
                    max_check,
                    Integer.parseInt(updateMachineID)
            );
            Inventory.updatePart(Inventory.getAllParts().indexOf(partData), inhouse);
            System.out.println("line 350 at modifypartcontroller -- a new in house part has been saved");
            try {
                modifyPartRedirectsToEMIMSHomePage();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if(modifyPartOutsourcedRadioBtn.isSelected()) {
            Outsourced outsourced = new Outsourced(
                    partID,
                    verifyPartName,
                    price_check,
                    stock_check,
                    min_check,
                    max_check,
                    updateCompanyName
            );
            Inventory.updatePart(Inventory.getAllParts().indexOf(partData), outsourced);
            System.out.println("a new outsourced part has been saved on line 377 at modify part controller");
            try {
                modifyPartRedirectsToEMIMSHomePage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * Void modifyPart_cancelBtnAction() method is used to go back to the landing page while still working on adding a new part to the database.
     * @param event represents the event that triggers the action.
     * A confirmation alert will be shown when the user clicks the cancel button. If the user clicks OK, then the addPart Page will be hidden, and the user will be redirected to the landing page, unless an exception is caught. If the user press cancel, then it will return to the addPart page to keep working on the data part input.
     */
    public void modifyPart_cancelBtnAction(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Updated part hasn't been saved yet. Are you sure that you want to leave the window?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)) {
                //go back to the landing page by doing ...
                modifyPart_cancelBtn.getScene().getWindow().hide();
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
     * Void modifyPart_addProductBtnAction() method is used to call the modifyPartRedirectsToAddProductPage(), unless an exception is caught.
     * @param event represents the event that triggers the action.
     * A confirmation alert is displayed.
     */
    public void modifyPart_addProductBtnAction(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Updated part hasn't been saved yet. Are you sure that you want to switch to the Add Product window?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)) {
                modifyPartRedirectsToAddProductPage();
            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    /**
     * When the modify product button is clicked, an error alert will be displayed.
     * @param event represents the event that triggers the action.
     */
    public void modifyPart_modifyProductBtnAction_Error(ActionEvent event) {
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

    /**
     * Void modifyPart_addPartBtnAction() method is used to call the modifyPartRedirectsToAddPartPage();, unless an exception is caught.
     * @param event represents the event that triggers the action.
     * A confirmation alert is displayed.
     */
    public void modifyPart_addPartBtnAction(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Updated part hasn't been saved yet. Are you sure that you want to switch to the Add Part window?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)) {
                modifyPartRedirectsToAddPartPage();
            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    //SIDE MENU
    /**
     * Public void modifyPartRedirectsToEMIMSHomePage() method called after successfully saving the updated new part into the database, and no exceptions were caught.
     * Modify Part page is hided, and the user is redirected to the homepage, where it can see the updated part displaying on the parts table.
     * @throws IOException if an input or output error occurs
     * @see IOException
     */
    public void modifyPartRedirectsToEMIMSHomePage() throws IOException {
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
     * Void modifyPartRedirectsToAddProductPage() method is called by the modifyPart_addProductBtnAction; and it is used to open Add Product Page, unless an exception is caught.
     * @throws IOException if an input or output error occurs
     * @see IOException
     */
    public void modifyPartRedirectsToAddProductPage() throws IOException {
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
     * Void modifyPartRedirectsToAddPartPage() method is called by the modifyPart_addPartBtnAction; and it is used to open Add Part Page, unless an exception is caught.
     * @throws IOException if an input or output error occurs
     * @see IOException
     */
    public void modifyPartRedirectsToAddPartPage() throws IOException {
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
     * @param url is used to resolve relative paths for the root object. It is null if the url is not known.
     * @param rb is used to localize the root object, and it is null if the root object is not located.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //modifyPart_setPartName.setText("hellooooo");

        modifyPart_displayCompanyOrMachineLabel.setText("Machine ID:");

        modifyPart_partIDTextField.setText(getSinglePartID);
        modifyPart_setPartName.setText(getSinglePartName);
        modifyPart_setInventoryLevel.setText(getSinglePartStock);
        modifyPart_setPriceUnit.setText(getSinglePartPriceUnit);
        modifyPart_setMin.setText(getSinglePartMin);
        modifyPart_setMax.setText(getSinglePartMax);

        //if machineID DOES exist && companyName DOES NOT exist t --> we have a in-house item so...
        if(getSinglePartMachineID != null && !getSinglePartMachineID.trim().isEmpty()) {
            System.out.println("part machine -In Home data is in the database!!");
            //we are displaying the in-house radio button selected, the machineID label, and the machineID in the inputfield
            modifyPartInHouseRadioBtn.setSelected(true);
            modifyPart_displayCompanyOrMachineLabel.setText("Machine ID:");
            modifyPart_inputCompanyOrMachineInputField.setText(getSinglePartMachineID);
            //if company_name DOES exist && machineID DOES NOT EXIST--> we have an outsourced item so...
        } else if (getSinglePartCompanyName != null && !getSinglePartCompanyName.trim().isEmpty()){
            System.out.println("company name - outsourced data is in the database!!");
            //we are displaying the outsourced radio button selected, the companyName label, and the companyName in the inputfield
            modifyPartOutsourcedRadioBtn.setSelected(true);
            modifyPart_displayCompanyOrMachineLabel.setText("Company Name:");
            modifyPart_inputCompanyOrMachineInputField.setText(getSinglePartCompanyName);
        }
    }
}
