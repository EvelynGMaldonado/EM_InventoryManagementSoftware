package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Public class Inventory() holds the data for products, parts, and associated parts. It contains methods to retrieve and modify data.
 * RUNTIME ERROR: I was trying to add the in house part by just the id and the machine number. I ended up modifying the inHouse class, and the inventory to be able to extend the part class to inHouse or Outsourced, depending on the type of part.
 * FUTURE ENHANCEMENT: Creating the filter to insert the in house or outsourced part into the mysql database.
 * @author Evelyn G Morrow.
 * @version 1.1.
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

}
