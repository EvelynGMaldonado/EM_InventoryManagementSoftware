package Model;

import em_ims.em_inventorymanagementsoftware.AddProductController;
import em_ims.em_inventorymanagementsoftware.HelloController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Evelyn G Morrow.
 * @version 1.1.
 * Public class Inventory() holds the data for products, parts, and associated parts. It contains methods to retrieve and modify data.
 * RUNTIME ERROR: I was trying to add the in house part by just the id and the machine number. I ended up modifying the inHouse class, and the inventory to be able to extend the part class to inHouse or Outsourced, depending on the type of part.
 * FUTURE ENHANCEMENT: Creating the filter to insert the in house or outsourced part into the mysql database.
 */
public class Inventory {
    public static ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Public  static void addPart(Part part) method adds a part to allParts list.
     * @param part the part to be added
     */
    public static void addPart(Part part) {
        if(part != null) {
            allParts.add(part);
        }
    }

    /**
     * Public  static void addProduct(Product product) method adds a product to allProducts list.
     * @param product the product to be added
     */
    public static void addProduct(Product product) {
        if(product != null) {
            allProducts.add(product);
        }
    }

    /**
     * public static Part lookupPart(int partID) method finds the part based on the partID the user is looking for.
     * @param partID the key-search string the user enter into the search bar box
     * @return findPartByID the matched part
     */
    public static Part lookupPart(int partID) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        for(int i = 0; i < allParts.size(); i++){
            Part findPart = allParts.get(i);
            if(findPart.getId() == partID){
                return findPart;
            }
        }
        return null;
    }

    /**
     * public static ObservableList<Part> lookupPart(String partName) finds the part based on the partName the user is looking for.
     * @param partName the key-search string the user enter into the search bar box
     * @return foundedPartNameList observable array list
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList <Part> findPartByName = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();
        for (Part findPart : allParts) {
            if(findPart.getName().toLowerCase().contains(partName.toLowerCase())){
                findPartByName.add(findPart);
            }
        }
//        if(findPartByName.isEmpty()){
//            return getAllParts();
//        }
        return findPartByName;
    }

    /**
     * public static Product lookupProduct(int productID) method finds the part based on the productID the user is looking for.
     * @param productID the key-search string the user enter into the search bar box
     * @return findProductByID the matched product.
     */
    public static Product lookupProduct(int productID) {
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for(int i = 0; i < allProducts.size(); i++){
            Product findProduct = allProducts.get(i);
            if(findProduct.getProductID() == productID){
                return findProduct;
            }
        }
        return null;
    }

    /**
     * public static ObservableList<Product> lookupProduct(String productName) finds the product based on the productName the user is looking for.
     * @param productName the key-search string the user enter into the search bar box
     * @return foundedProductNameList observable array list
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList <Product> findProductByName = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for(Product findProduct : allProducts) {
            if(findProduct.getProduct_name().toLowerCase().contains(productName.toLowerCase())){
                findProductByName.add(findProduct);
            }
        }
//        if(findProductByName.isEmpty()){
//            return getAllProducts();
//        }
        return findProductByName;
    }

    /**
     * Public void updatePart() method is used to replace an old part with the updated part.
     * @param index the index of the old part.
     * @param selectedPart the updated part.
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * Public void updateProduct() method is used to replace an old product with the updated product.
     * @param index the index of the old product.
     * @param selectedProduct the updated product.
     */
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    /**
     * public static boolean deletePart(Part part) method used to delete a selected part
     * @param part selected part to be deleted
     * @return updated inventory of parts
     */
    public static boolean deletePart(Part part) {
        return Inventory.getAllParts().remove(part);
    }

    /**
     * public static boolean deleteProduct(Product product) method used to delete a selected product
     * @param product selected product to be deleted
     * @return updated inventory of products
     */
    public static boolean deleteProduct(Product product) {
        return Inventory.getAllProducts().remove(product);
    }

    /**
     * public static ObservableList<Part> getAllParts() method returns allParts updated list
     * @return allParts list
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * public static ObservableList<Product> getAllProducts() method returns the allProducts updated list.
     * @return allProducts list.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }




//    /**
//     * Creates a new empty observable list that is backed by an arraylist.
//     */
//    private ObservableList<Part> partInventorySearch = FXCollections.observableArrayList();
//
//    /**
//     * Method to return partInventorySearch updated list.
//     * @return partInventorySearch list.
//     */
//    public ObservableList<Part> getPartInventorySearch() {
//        return partInventorySearch;
//    }
//


//    /**
//     * Method to return allAssociatedParts updated list.
//     * @return allAssociatedParts list.
//     */
//    public static ObservableList<Part> getAllAssociatedParts() {
//        return allAssociatedParts;
//    }

//    /**
//     * Creates a new empty observable list that is backed by an arraylist.
//     */
//    private ObservableList<Product> productInventorySearch = FXCollections.observableArrayList();



//    /**
//     * @param getSingleAssociatedPartID the id of the selected associated part.
//     * @return the all associated parts.
//     */
//    public ObservableList<Part> associatedPartDetails(String getSingleAssociatedPartID) {
//        System.out.println("line 55 --- the value of getSingleAssociatedPartID is: " + getSingleAssociatedPartID);
//
//        Integer associatedPartID = Integer.valueOf(getSingleAssociatedPartID);
//        String foundName = "";
//        Integer foundID = 0;
//
//        for (int i = 0; i < allParts.size(); i++) {
//            if(allParts.get(i).getId() == associatedPartID) {
//                foundID = allParts.get(i).getId();
//                System.out.println("the foundID value is: " + foundID);
//                foundName = allParts.get(i).getName();
//                System.out.println("the foundName value is: " + foundName);
//
//                for(Part p : getAllAssociatedParts()) {
////                for(Part p : getAllParts()) {
//                    if(p.getId() != foundID) {
////                    if(p.getId() == foundID) {
//                        allAssociatedParts.add(p);
//                        System.out.println("we are at line 70 under allAssociatedParts.add(p)");
//                        System.out.println("the allAssociatedParts value for foundID on line 69 is: " + allAssociatedParts);
//                    }
//                }
//                return allAssociatedParts;
//            }
//        }
//        return null;
//
//    }

//    /**
//     * @param partSearchByKey the characters on key release
//     * @return the partInventorySearch
//     */
//    public ObservableList<Part> keySearchPart(String partSearchByKey){
//        System.out.println("the value of partSearchByKey is: " + partSearchByKey);
//        getAllParts();
//        System.out.println("the value of getAllParts is: " + allParts.size());
//
//        String foundName = "";
//        String searchedPart = partSearchByKey.toLowerCase();
//        Integer foundID = 0;
//
//        for(int i = 0; i < allParts.size(); i++) {
//            if(allParts.get(i).getName().toLowerCase().contains(searchedPart) || Integer.toString(allParts.get(i).getId()).equals(searchedPart) ) {
//                foundName = allParts.get(i).getName().toLowerCase();
//                System.out.println("The value of foundName on line 53 is: " + foundName);
//
//                foundID = Integer.parseInt(Integer.toString(allParts.get(i).getId()));
//                System.out.println("The value of foundID on line 56 is: " + foundID);
//
////                partInventorySearch.clear();
//                for(Part p : getAllParts()) {
//                    if(p.getName().contains(foundName)) {
//                        partInventorySearch.add(p);
//                        System.out.println("we are at line 62 under partInventorySearch.add(p)");
//                        System.out.println("the partInventorySearch value for foundName on line 63 is: " + partInventorySearch);
//                    }
//                    else if(p.getId() == foundID) {
//                        partInventorySearch.add(p);
//                        System.out.println("we are at line 67 under partInventorySearch.add(p)");
//                        System.out.println("the partInventorySearch value for foundID on line 68 is: " + partInventorySearch);
//                    }
//                }
//                return partInventorySearch;
//            }
//            //else {
//                //Alert alert = new Alert(Alert.AlertType.ERROR);
//                //alert.setTitle("Error message");
//                //alert.setHeaderText(null);
//                //alert.setContentText("No matches have been found. Please try again.");
//                //alert.showAndWait();
//            //}
//        }
//        return null;
//    }



//    /**
//     * Public void addPart() method adds a new part to allParts list.
//     * @param partID the partID to be added in the inventory.
//     * @param partName the partName to be added in the inventory.
//     * @param priceUnit the price of the part to be added in the inventory.
//     * @param inventoryLevel the number of parts in the inventory.
//     * @param min the min number of parts allowed in the inventory.
//     * @param max the max number of parts allowed in the inventory.
//     * @param machineID the machineID of the part to be added in the inventory.
//     */
//    public void addPart(int partID, String partName, Double priceUnit, int inventoryLevel, int min, int max, int machineID) {
//        Part newPart = new InHouse(partID, partName, priceUnit, inventoryLevel, min, max, machineID);
//        allParts.add(newPart);
//    }

//    /**
//     * Public void addProduct() method adds a new product to allProducts list.
//     * @param productID the productID to be added in the inventory.
//     * @param product_name the product_name to be added in the inventory.
//     * @param price_unit the price of the product to be added in the inventory.
//     * @param stock the number of products in the inventory.
//     * @param min the min number products allowed in the inventory.
//     * @param max the max number products allowed in the inventory.
//     */
//    public void addProduct(int productID, String product_name, double price_unit, int stock, int min, int max) {
//        Product newProduct = new Product(productID, product_name, price_unit, stock, min, max);
//        allProducts.add(newProduct);
//    }
//
//
//
}
