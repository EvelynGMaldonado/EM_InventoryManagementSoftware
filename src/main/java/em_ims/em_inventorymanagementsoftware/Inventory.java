package em_ims.em_inventorymanagementsoftware;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Inventory {
    HelloController helloController;

    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private ObservableList<Part> partInventorySearch = FXCollections.observableArrayList();

    public ObservableList<Part> getPartInventorySearch() {
        return partInventorySearch;
    }

    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public int partListSize() {
        return allParts.size();
    }

    public int productListSize() {
        return allProducts.size();
    }

    public ObservableList<Part> keySearchPart(String partSearchByKey){
        System.out.println("the value of partSearchByKey is: " + partSearchByKey);
        getAllParts();
        System.out.println("the value of getAllParts is: " + allParts.size());

        String foundName = "";
        String searchedPart = partSearchByKey.toLowerCase();
        Integer foundID = 0;

        for(int i = 0; i < allParts.size(); i++) {
            if(allParts.get(i).getName().toLowerCase().contains(searchedPart)) {
                foundName = allParts.get(i).getName();
                partInventorySearch.clear();
                for(Part p : getAllParts()) {
                    if(p.getName().contains(foundName)) {
                        partInventorySearch.add(p);
                    }
                }

                return partInventorySearch;

            } else if(Integer.toString(allParts.get(i).getId()).equals(searchedPart)) {
                foundID = Integer.parseInt(Integer.toString(allParts.get(i).getId()));
                partInventorySearch.clear();
                for(Part p : getAllParts()) {
                    if(p.getId() == foundID) {
                        partInventorySearch.add(p);
                    }
                }

                return partInventorySearch;
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("No matches have been found. Please try again.");
                alert.showAndWait();

//                HelloController helloController = new HelloController();
//                helloController.refreshTables();
            }
        }

        return null;
    }

    public void viewEMInventoryManagementSystem() throws IOException {
//        startBtn.getScene().getWindow().hide();
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

    public void addPart(Part addNewPart) {
        if(addNewPart != null) {
            allParts.add(addNewPart);
        }
    }

    public void addProduct(Product addNewProduct) {
        if(addNewProduct != null) {
            this.allProducts.add(addNewProduct);
        }
    }

    public Part searchPart(int partID) {
        if(!allParts.isEmpty()) {
            for (int i = 0; i < allParts.size(); i++) {
                if(allParts.get(i).getId() == partID) {
                    return allParts.get(i);
                }
            }
        }
        return null;
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Error message");
//        alert.setHeaderText(null);
//        alert.setContentText("No matches have been found. Please try again.");
//        alert.showAndWait();
    }

    public ObservableList<Part> searchPart(String partName) {
        if(!allParts.isEmpty()) {
            ObservableList searchPartList = FXCollections.observableArrayList();
            for(Part part : getAllParts()) {
                if(part.getName().contains(partName)) {
                    searchPartList.add(part);
                }
            }
            return searchPartList;
        }
        return  null;
    }



}
