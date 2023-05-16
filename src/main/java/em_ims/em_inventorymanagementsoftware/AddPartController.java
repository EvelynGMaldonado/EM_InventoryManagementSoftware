package em_ims.em_inventorymanagementsoftware;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author Evelyn G Morrow.
 * @version 1.1.
 * Public class AddPartController is used to add a new part to the inventory after the user successfully clicks the add part button located on the home page or side menu.
 * RUNTIME ERROR:
 * FUTURE ENHANCEMENT:
 */
public class AddPartController implements Initializable {

    @FXML
    private Button addProductPageBtn;

    @FXML
    private Button startBtn;

    @FXML
    private Label displayCompanyOrMachineLabel;

    @FXML
    private RadioButton inHouseRadioBtn;

    @FXML
    private TextField inputCompanyOrMachineInputField;

    @FXML
    private RadioButton outsourcedRadioBtn;

    @FXML
    private TextField addPart_setPartName;

    @FXML
    private TextField addPart_setInventoryLevel;

    @FXML
    private TextField addPart_setMax;

    @FXML
    private TextField addPart_setMin;

    @FXML
    private TextField addPart_setPriceUnit;

    @FXML
    private Button addPart_cancelBtn;

    @FXML
    private TextField addPart_partIDTextField;

    private ObservableList partsIdList = FXCollections.observableArrayList();

    /**
     * Void addPart_cancelBtnAction() method is used to go back to the landing page while still working on adding a new part to the database.
     * @param event represents the event that triggers the action.
     * A confirmation alert will be shown when the user clicks the cancel button. If the user clicks OK, then the addPart Page will be hidden, and the user will be redirected to the landing page, unless an exception is caught. If the user press cancel, then it will return to the addPart page to keep working on the data part input.
     */
    @FXML
    void addPart_cancelBtnAction(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("New part hasn't been saved yet. Are you sure that you want to leave the window?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)) {
                //go back to the landing page by doing ...
                addPart_cancelBtn.getScene().getWindow().hide();
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
     * When the modify part button is clicked, an error alert will be displayed.
     * @param event represents the event that triggers the action.
     */
    @FXML
    void addPart_modifyPartBtnAction_Error(ActionEvent event) {
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
     * @param event represents the event that triggers the action.
     */
    @FXML
    void addPart_modifyProductBtnAction_Error(ActionEvent event) {
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
     * Void addPart_addProductBtnAction() method is used to call the addPartRedirectsToAddProductPage(), unless an exception is caught.
     * @param event represents the event that triggers the action.
     * A confirmation alert is displayed.
     */
    @FXML
    void addPart_addProductBtnAction(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("New part hasn't been saved yet. Are you sure that you want to switch to the Add Product window?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)) {
                addPartRedirectsToAddProductPage ();
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
     * Public void addPartRedirectsToEMIMSHomePage() method called after the new part is successfully registered into the database, and no exceptions were caught.
     * The add Part page is hided, and the user is redirected to the homepage, where it can see the new part displaying on the parts table.
     * @throws IOException if an input or output error occurs
     * @see IOException
     */
    public void addPartRedirectsToEMIMSHomePage() throws IOException {
        startBtn.getScene().getWindow().hide();

        //create new stage
        Stage ppMainWindow = new Stage();
        ppMainWindow.setTitle("EM Inventory Management System");

        //create view for FXML
        FXMLLoader ppMainLoader = new FXMLLoader(getClass().getResource("home_page-parts&products.fxml"));

        //set view in ppMainWindow
        ppMainWindow.setScene(new Scene(ppMainLoader.load(), 800, 400));

        //launch
        ppMainWindow.show();
    }

    /**
     * Void addPartRedirectsToAddProductPage() method is called by the addPart_addProductBtnAction; and it is used to open Add Product Page, unless an exception is caught.
     * @throws IOException if an input or output error occurs
     * @see IOException
     */
    public void addPartRedirectsToAddProductPage() throws IOException {
        addProductPageBtn.getScene().getWindow().hide();
        //create new stage
        Stage addProductPageWindow = new Stage();
        addProductPageWindow.setTitle("Add Product - EM Inventory Management System");

        //create view for FXML
        FXMLLoader addProductPageLoader = new FXMLLoader(getClass().getResource("addProduct_page.fxml"));

        //set view in ppMainWindow
        addProductPageWindow.setScene(new Scene(addProductPageLoader.load(), 800, 610));

        //launch
        addProductPageWindow.show();
    }


    /**
     * Void clickSavePartBtn() method is used to validate that none of the fields are empty, and that the correct data types have been used.
     * @param event represents the event that triggers the action.
     * If all validations pass, then the validatePartName() method will be called; otherwise an error alert will be displayed.
     */
    @FXML
    void clickSavePartBtn(ActionEvent event) throws IOException {

        //retrieve variables for max, min, and inventory validation.
        String min = addPart_setMin.getText().trim();
        String max = addPart_setMax.getText().trim();
        String stock = addPart_setInventoryLevel.getText().trim();
        int min_check;
        int max_check;
        int stock_check;

        //Part Category Selection Validation - No null Accepted ~ it has to select inHouse or Outsourced
        if(inHouseRadioBtn.isSelected() || outsourcedRadioBtn.isSelected()) {
            //Not null accepted Input validation checks that none of the fields are blank or empty...
            if(!addPart_setPartName.getText().trim().isEmpty() || !addPart_setInventoryLevel.getText().trim().isEmpty() || !addPart_setPriceUnit.getText().trim().isEmpty() || !addPart_setMax.getText().trim().isEmpty() || !addPart_setMin.getText().trim().isEmpty() || !inputCompanyOrMachineInputField.getText().trim().isEmpty()) {
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
                                //check if the part name is available or if it already exists using the validatePartName method
                                validatePartName();
//                                addPartRedirectsToEMIMSHomePage();
                            } else{
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
            alert.setContentText("Please select the Part Category: In-House or Outsourced.");
            alert.showAndWait();
        }
    }

    /**
     * Public void validatePartName() method is used to validate that the new part name does not exist in the EM database.
     * When the validation passes, the method registerNewPart() is called, unless an Exception is caught.
     * When the validation does not pass, an error alert will show up, and the user will be requested to use a different name for the new part.
     */
    public void validatePartName() {
        System.out.println("we are into validatePartName() method on line 300!!");

        Inventory inventory = new Inventory();
        HelloController helloController = new HelloController();

        String verifyPartName = addPart_setPartName.getText().trim().toLowerCase();
        String partName = "";
        System.out.println("the name of the part on line 305 is: " + verifyPartName);

        if(!verifyPartName.isEmpty() && inventory.getAllParts().isEmpty()) {
            partName = verifyPartName;
            System.out.println("the partName on line 306 is: " + partName);
            generatePartId(partName);
        }
        else if(!verifyPartName.isEmpty() && !inventory.getAllParts().isEmpty()){
            System.out.println("we are into validatePartName() method with no empty inventory on line 314!!");
            partName = verifyPartName;
            for(int i = 0; i < Inventory.allParts.size(); i++) {
                if(Inventory.allParts.get(i).getName() == partName) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Part Name already exists. Please try again.");
                    alert.showAndWait();
                }
            }
            generatePartId(partName);
//            for(Part partsName : inventory.getAllParts()) {
//                if(partsName.getName().toLowerCase().contains(verifyPartName)) {
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Part Name already exists. Please try again.");
//                    alert.showAndWait();
//                } else if(!partsName.getName().toLowerCase().contains(verifyPartName)) {
//                    partName = verifyPartName;
//                    System.out.println("the partName on line 320 is: " + partName);
//                    generatePartId(partName);
//                }
//            }
        }
//        else if(!verifyPartName.isEmpty() && inventory.getAllParts().isEmpty()) {
//            partName = verifyPartName;
//            System.out.println("the partName on line 322 is: " + partName);
//            generatePartId(partName);
//        }

//        if(inventory.getAllParts().isEmpty() && !verifyPartName.isEmpty()) {
//            registerNewPart();
//        } else if(!inventory.getAllParts().isEmpty()) {
//            for(Part existentParts : inventory.getAllParts()) {
//                if(existentParts.getName().trim().toLowerCase().contains(verifyPartName)) {
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Part Name already exists. Please try again.");
//                    alert.showAndWait();
//                } else if(!existentParts.getName().trim().toLowerCase().contains(verifyPartName)) {
//                    registerNewPart();
//                }
//            }
//        }
    };

    public void generatePartId(String partName) {
        System.out.println("we are into generatePartId() method on line 365!! and the partName value we are working with is: " + partName);

        Inventory inventory = new Inventory();
        Integer setPartID = 1;
        Integer partID = 0;

        if(inventory.getAllParts().isEmpty()) {
            partID = setPartID;
            System.out.println("the PartID value on line 373 when inventory is empty is: " + partID);
            registerNewPart(partName, partID);
        } else if(!inventory.getAllParts().isEmpty()){
            partID = 1;
            for(int i = 0; i < Inventory.allParts.size(); i++) {
                if(Inventory.allParts.get(i).getId() == partID) {
                    //partID = setPartID++;
                    partID = setPartID + 1;
                    System.out.println("the PartID value on line 380 is: " + partID);
                    setPartID = partID;
                    System.out.println("the setPartID value on line 382 is: " + setPartID);
                }

            }
            registerNewPart(partName, partID);
            System.out.println("line 387 -- the partID value on line 386 is: " + partID);
            System.out.println("line 387 -- the partID and partName value on line 392 is: " + partID  + " " + partName);
        }
    }

    private void registerNewPart(String partName, Integer partID) {
        System.out.println("we are into private void registerNewPart method on line 402!! and the partID and partName values we are working with is: " + partID  + " " + partName);

        String inventoryLevel = addPart_setInventoryLevel.getText().trim().toLowerCase();
        String priceUnit = addPart_setPriceUnit.getText().trim();
        String max= addPart_setMax.getText().trim();
        String min = addPart_setMin.getText().trim();
        String machineID = inputCompanyOrMachineInputField.getText().trim();
        String companyName = inputCompanyOrMachineInputField.getText().trim();

        if(inHouseRadioBtn.isSelected()) {
            Inventory.allParts.add(new InHouse(
                    partID,
                    partName,
                    Double.parseDouble(priceUnit),
                    Integer.parseInt(inventoryLevel),
                    Integer.parseInt(min),
                    Integer.parseInt(max),
                    Integer.parseInt(machineID)
            ));
            System.out.println("a new in house part has been saved");
            try {
                addPartRedirectsToEMIMSHomePage();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if(outsourcedRadioBtn.isSelected()) {
            Inventory.allParts.add(new Outsourced(
                    partID,
                    partName,
                    Double.parseDouble(priceUnit),
                    Integer.parseInt(inventoryLevel),
                    Integer.parseInt(min),
                    Integer.parseInt(max),
                    companyName
            ));
            System.out.println("a new outsourced part has been saved");
//            addPartRedirectsToEMIMSHomePage();
        }
    }


    public void registerNewPart(){
        System.out.println("we are into registerNewPart() method!!");
        Inventory inventory = new Inventory();
        String partID = addPart_partIDTextField.getText().trim();

        boolean verifyIdMatch;
        Integer setPartID;
        Integer idCounter = 0;
        Integer latestID = 0;

        //create and add the new inHouse or outsourced part
        if(inHouseRadioBtn.isSelected()) {
            //create ID
            if(partID == null || partID.isEmpty() || partID.equals(0)){
                if(inventory.getAllParts().isEmpty()){
                    setPartID = idCounter + 1;
                    partID = setPartID.toString();
                    System.out.println("the id value assigned to the new inHouse part on line 336 is: " + partID);

                    addInHousePart(partID);
                } else if(!inventory.getAllParts().isEmpty()) {
                    for(Part partsIDs : inventory.getAllParts()) {

                            partsIdList.add(partsIDs);

                            latestID = (Integer) Collections.max(partsIdList);
                            System.out.println("the latestID value on line 345 is: " + latestID);
                            idCounter = latestID;
                            setPartID = idCounter + 1;
                            partID = setPartID.toString();

                            System.out.println("the id value assigned to the new inHouse part on line 350 is: " + partID);

                            addInHousePart(partID);
                    }

                }
            }
        } else if(outsourcedRadioBtn.isSelected()) {
            //create ID
            if(partID == null || partID.isEmpty() || partID.equals(0)){
                if(inventory.getAllParts().isEmpty()){
                    setPartID = idCounter + 1;
                    partID = setPartID.toString();
                    System.out.println("the id value assigned to the new outsourced part on line 368 is: " + partID);
                    addOutsourcedPart(partID);

                } else if(!inventory.getAllParts().isEmpty()) {
                    for(Part partsIDs : inventory.getAllParts()) {
                            partsIdList.add(partsIDs);

                            latestID = (Integer) Collections.max(partsIdList);
                            idCounter = latestID;
                            setPartID = idCounter + 1;
                            partID = setPartID.toString();

                            System.out.println("the id value assigned to the new outsourced part on line 378 is: " + partID);

                            addOutsourcedPart(partID);
                    }

                }
            }
        }
    }

    public void addInHousePart(String partID) {
        Inventory inventory = new Inventory();

        String partName = addPart_setPartName.getText().trim().toLowerCase();
        String inventoryLevel = addPart_setInventoryLevel.getText().trim().toLowerCase();
        String priceUnit = addPart_setPriceUnit.getText().trim();
        String max= addPart_setMax.getText().trim();
        String min = addPart_setMin.getText().trim();
        String machineID = inputCompanyOrMachineInputField.getText().trim();

//        inventory.addPart(new InHouse(
//                Integer.parseInt(partID),
//                partName,
//                Double.parseDouble(priceUnit),
//                Integer.parseInt(inventoryLevel),
//                Integer.parseInt(min),
//                Integer.parseInt(max),
//                Integer.parseInt(machineID)
//        ));

    }

    public void addOutsourcedPart(String partID) {
        Inventory inventory = new Inventory();

        String partName = addPart_setPartName.getText().trim().toLowerCase();
        String inventoryLevel = addPart_setInventoryLevel.getText().trim().toLowerCase();
        String priceUnit = addPart_setPriceUnit.getText().trim();
        String max= addPart_setMax.getText().trim();
        String min = addPart_setMin.getText().trim();
        String companyName = inputCompanyOrMachineInputField.getText().trim();

//        inventory.addPart(new Outsourced(
//                Integer.parseInt(partID),
//                partName,
//                Double.parseDouble(priceUnit),
//                Integer.parseInt(inventoryLevel),
//                Integer.parseInt(min),
//                Integer.parseInt(max),
//                companyName
//        ));
    }

    @FXML
    void displayMachineIDOrCompanyName(ActionEvent event) {
        if(inHouseRadioBtn.isSelected()) {
            displayCompanyOrMachineLabel.setText("Machine ID:");
            inputCompanyOrMachineInputField.setPromptText("machine ID");
        } else if(outsourcedRadioBtn.isSelected()) {
            displayCompanyOrMachineLabel.setText("Company Name:");
            inputCompanyOrMachineInputField.setPromptText("company name");
        }
    }

    /**
     * Public void initialize() method called to initialize a controller after its root element has been completely processed.
     * @param url is used to resolve relative paths for the root object. It is null if the url is not known.
     * @param rb is used to localize the root object, and it is null if the root object is not located.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inHouseRadioBtn.setSelected(true);
    }
}
