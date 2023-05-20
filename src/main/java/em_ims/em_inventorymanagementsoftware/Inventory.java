package em_ims.em_inventorymanagementsoftware;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Inventory {
    HelloController helloController;

    public static Part selectedPart;
    public static Product selectedProduct;
    public static ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private ObservableList<Part> partInventorySearch = FXCollections.observableArrayList();



    public ObservableList<Part> getPartInventorySearch() {
        return partInventorySearch;
    }

    private static ObservableList<Part> allAssociatedParts = FXCollections.observableArrayList();
    public static ObservableList<Part> getAllAssociatedParts() {
        return allAssociatedParts;
    }

    private ObservableList<Product> productInventorySearch = FXCollections.observableArrayList();

//    public ObservableList<Part> getAllParts() {
//        return allParts;
//    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

//    public int partListSize() {
//        return allParts.size();
//    }
//
//    public int productListSize() {
//        return allProducts.size();
//    }

    public ObservableList<Part> associatedPartDetails(String getSingleAssociatedPartID) {
        System.out.println("line 55 --- the value of getSingleAssociatedPartID is: " + getSingleAssociatedPartID);

        Integer associatedPartID = Integer.valueOf(getSingleAssociatedPartID);
        String foundName = "";
        Integer foundID = 0;

        for (int i = 0; i < allParts.size(); i++) {
            if(allParts.get(i).getId() == associatedPartID) {
                foundID = allParts.get(i).getId();
                System.out.println("the foundID value is: " + foundID);
                foundName = allParts.get(i).getName();
                System.out.println("the foundName value is: " + foundName);

                for(Part p : getAllAssociatedParts()) {
//                for(Part p : getAllParts()) {
                    if(p.getId() != foundID) {
//                    if(p.getId() == foundID) {
                        allAssociatedParts.add(p);
                        System.out.println("we are at line 70 under allAssociatedParts.add(p)");
                        System.out.println("the allAssociatedParts value for foundID on line 69 is: " + allAssociatedParts);
                    }
                }
                return allAssociatedParts;
            }
        }
        return null;

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
                        System.out.println("the partInventorySearch value for foundID on line 68 is: " + partInventorySearch);
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

    public  static void addProduct(Product product) {
        if(product != null) {
            allProducts.add(product);
        }
    }

    public void addPart(int partID, String partName, Double priceUnit, int inventoryLevel, int min, int max, int machineID) {
        Part newPart = new InHouse(partID, partName, priceUnit, inventoryLevel, min, max, machineID);
        allParts.add(newPart);
    }

    public void addProduct(int productID, String product_name, double price_unit, int stock, int min, int max) {
        Product newProduct = new Product(productID, product_name, price_unit, stock, min, max);
        allProducts.add(newProduct);
    }

//    public void validateAssociatedPart(String getSingleAssociatedPartID) {
//        System.out.println("line 170 we are into the validateAssociatedPart(String getSingleAssociatedPartID) method --- the value of getSingleAssociatedPartID is: " + getSingleAssociatedPartID);
//
//        Integer associatedPartID = Integer.valueOf(getSingleAssociatedPartID);
//
//        if(!allAssociatedParts.isEmpty()) {
//            for (int i = 0; i < allAssociatedParts.size(); i++) {
//                if(allAssociatedParts.get(i).getId() != associatedPartID) {
//                    System.out.println("the selected part is not part of our product yet.");
//                    associatedPartDetails(getSingleAssociatedPartID);
//                    System.out.println("Calling the associatedPartDetails() method.");
//                }
//            }
//        }
//    }

    /**
     * Public void updateProduct() method is used to replace an old product with the updated product.
     * @param index index of old product
     * @param product updated product
     */
    public static void updateProduct(int index, Product product) {
        allProducts.set(index, product);
    }

}
