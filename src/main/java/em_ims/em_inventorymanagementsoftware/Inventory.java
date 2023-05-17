package em_ims.em_inventorymanagementsoftware;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Inventory {
    HelloController helloController;

    public static Part selectedPart;
    public static Product selectedProduct;
    public static ObservableList<Part> allParts = FXCollections.observableArrayList();
//    private ObservableList<Part> allParts = FXCollections.observableArrayList();
//    private ObservableList<Product> allProducts = FXCollections.observableArrayList();
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private ObservableList<Part> partInventorySearch = FXCollections.observableArrayList();
    private ObservableList<Product> productInventorySearch = FXCollections.observableArrayList();

    public ObservableList<Part> getPartInventorySearch() {
        return partInventorySearch;
    }

//    public ObservableList<Part> getAllParts() {
//        return allParts;
//    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
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
            if(allParts.get(i).getName().toLowerCase().contains(searchedPart) || Integer.toString(allParts.get(i).getId()).equals(searchedPart) ) {
                foundName = allParts.get(i).getName().toLowerCase();
                System.out.println("The value of foundName on line 53 is: " + foundName);

                foundID = Integer.parseInt(Integer.toString(allParts.get(i).getId()));
                System.out.println("The value of foundID on line 56 is: " + foundID);

//                partInventorySearch.clear();
                for(Part p : getAllParts()) {
                    if(p.getName().contains(foundName)) {
                        partInventorySearch.add(p);
                        System.out.println("we are at line 62 under partInventorySearch.add(p)");
                        System.out.println("the partInventorySearch value for foundName on line 63 is: " + partInventorySearch);
                    }
                    else if(p.getId() == foundID) {
                        partInventorySearch.add(p);
                        System.out.println("we are at line 67 under partInventorySearch.add(p)");
                        System.out.println("the partInventorySearch value for foundName on line 68 is: " + partInventorySearch);
                    }
                }

                return partInventorySearch;

            }
//            else {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error message");
//                alert.setHeaderText(null);
//                alert.setContentText("No matches have been found. Please try again.");
//                alert.showAndWait();
//            }
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

//    public void addPart(Part addNewPart) {
//        if(addNewPart != null) {
//            allParts.add(addNewPart);
//        }
//    }

    public void addPart(int partID, String partName, Double priceUnit, int inventoryLevel, int min, int max, int machineID) {
        Part newPart = new InHouse(partID, partName, priceUnit, inventoryLevel, min, max, machineID);
        allParts.add(newPart);
    }

    public void addProduct(int productID, String product_name, double price_unit, int stock, int min, int max) {
        Product newProduct = new Product(productID, product_name, price_unit, stock, min, max);
        allProducts.add(newProduct);
    }

    public static ObservableList<Part> allAssociatedParts = FXCollections.observableArrayList();

    public static ObservableList<Part> getAllAssociatedParts() {
        return allAssociatedParts;
    }

    public Part validatePart(int partID) {
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

//    public ObservableList<Part> validatePart(String partName) {
//        if(!allParts.isEmpty()) {
//            ObservableList searchPartList = FXCollections.observableArrayList();
//            for(Part part : getAllParts()) {
//                if(part.getName().contains(partName)) {
//                    searchPartList.add(part);
//                }
//            }
//            return searchPartList;
//        }
//        return  null;
//    }



}
