package em_ims.em_inventorymanagementsoftware;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Evelyn G Morrow.
 * @version 1.1.
 * Public class Inventory() holds the data for products, parts, and associated parts. It contains methods to retrieve and modify data.
 * RUNTIME ERROR:
 * FUTURE ENHANCEMENT:
 */
public class Inventory {
    HelloController helloController;

    public static Part selectedPart;
    public static Product selectedProduct;
    public static ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Creates a new empty observable list that is backed by an arraylist.
     */
    private ObservableList<Part> partInventorySearch = FXCollections.observableArrayList();

    /**
     * Method to return partInventorySearch updated list.
     * @return partInventorySearch list.
     */
    public ObservableList<Part> getPartInventorySearch() {
        return partInventorySearch;
    }

    /**
     * Creates a new empty observable list that is backed by an arraylist.
     */
    private static ObservableList<Part> allAssociatedParts = FXCollections.observableArrayList();

    /**
     * Method to return allAssociatedParts updated list.
     * @return allAssociatedParts list.
     */
    public static ObservableList<Part> getAllAssociatedParts() {
        return allAssociatedParts;
    }

    /**
     * Creates a new empty observable list that is backed by an arraylist.
     */
    private ObservableList<Product> productInventorySearch = FXCollections.observableArrayList();

    /**
     * Method to return allParts updated list
     * @return allParts list
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Method to return the allProducts updated list.
     * @return allProducts list.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * @param getSingleAssociatedPartID the id of the selected associated part.
     * @return the all associated parts.
     */
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

    /**
     * @param partSearchByKey the characters on key release
     * @return the partInventorySearch
     */
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
            //else {
                //Alert alert = new Alert(Alert.AlertType.ERROR);
                //alert.setTitle("Error message");
                //alert.setHeaderText(null);
                //alert.setContentText("No matches have been found. Please try again.");
                //alert.showAndWait();
            //}
        }
        return null;
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
     * Public void addPart() method adds a new part to allParts list.
     * @param partID the partID to be added in the inventory.
     * @param partName the partName to be added in the inventory.
     * @param priceUnit the price of the part to be added in the inventory.
     * @param inventoryLevel the number of parts in the inventory.
     * @param min the min number of parts allowed in the inventory.
     * @param max the max number of parts allowed in the inventory.
     * @param machineID the machineID of the part to be added in the inventory.
     */
    public void addPart(int partID, String partName, Double priceUnit, int inventoryLevel, int min, int max, int machineID) {
        Part newPart = new InHouse(partID, partName, priceUnit, inventoryLevel, min, max, machineID);
        allParts.add(newPart);
    }

    /**
     * Public void addProduct() method adds a new product to allProducts list.
     * @param productID the productID to be added in the inventory.
     * @param product_name the product_name to be added in the inventory.
     * @param price_unit the price of the product to be added in the inventory.
     * @param stock the number of products in the inventory.
     * @param min the min number products allowed in the inventory.
     * @param max the max number products allowed in the inventory.
     */
    public void addProduct(int productID, String product_name, double price_unit, int stock, int min, int max) {
        Product newProduct = new Product(productID, product_name, price_unit, stock, min, max);
        allProducts.add(newProduct);
    }

    /**
     * Public void updateProduct() method is used to replace an old product with the updated product.
     * @param index index of old product
     * @param product updated product
     */
    public static void updateProduct(int index, Product product) {
        allProducts.set(index, product);
    }

}
