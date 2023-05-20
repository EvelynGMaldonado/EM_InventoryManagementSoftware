package em_ims.em_inventorymanagementsoftware;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * @author Evelyn G Morrow.
 * @version 1.0.
 * Public class Product used to display the products tableview on the dashboard at the Home Page.
 * RUNTIME ERROR:
 * FUTURE ENHANCEMENT:
 */
public class Product {
    private ObservableList<Part> pAssociatedParts = FXCollections.observableArrayList();
    private int productID;
    private String product_name;
    private double price_unit = 0.0;
    private int stock = 0;
    private int min;
    private int max;

//    private ArrayList<Part> associatedParts = new ArrayList<Part>();

//    public ObservableList<Part> allProductAssociatedParts = FXCollections.observableArrayList();
//
    public ObservableList<Part> getPassociatedParts() {
        return pAssociatedParts;
    }

    public void setpAssociatedParts(Part part) {
        pAssociatedParts.add(part);
    }

    public void removepAssociatedParts(Part part) {pAssociatedParts.remove(part);}

    public Product() {

    }

    /**
     * Public ProductData Constructor is called when the ProductData object is instantiated.
     * It has the function of initialize the newly created object before it is used.
     * The list of parameters that the public ProductData constructor can take are declared into the parenthesis, and listed below:
     * @param productID is taken by public ProductData Constructor, and initializes the private Integer productID variable.
     * @param product_name is taken by public ProductData Constructor, and initializes the private String product_name variable.
     * @param stock is taken by public ProductData Constructor, and initializes the private Integer stock variable.
     * @param price_unit is taken by public ProductData Constructor, and initializes the private BigDecimal price_unit variable.
     */
    public Product(int productID, String product_name, double price_unit, int stock, int min, int max) {
        setProductID(productID);
        setProduct_name(product_name);
        setPrice_unit(price_unit);
        setStock(stock);
        setMin(min);
        setMax(max);
    }

    /**
     * public Integer getProductID() returns the ID of the product.
     * @return productID
     */
    public Integer getProductID() {
        return productID;
    }

    /**
     * public void setProductID() accepts the productID and sets it to the ProductData object.
     * @param productID is the ID of the product
     */
    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    /**
     * public String getProduct_name() returns the name of the product.
     * @return product_name
     */
    public String getProduct_name() {
        return product_name;
    }

    /**
     * public void setProduct_name() accepts the product_name and sets it to the ProductData object
     * @param product_name is the name of the product
     */
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    /**
     * public Integer getStock() returns the number of products available in the inventory
     * @return stock is the inventory number of the product
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * public void setStock() accepts the stock and sets it to the ProductData object
     * @param stock is the inventory number of the product
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * public BigDecimal getPrice_unit() returns the price per unit of that product
     * @return price_unit is the price per unit of the product
     */
    public double getPrice_unit() {
        return price_unit;
    }

    /**
     *public void setPrice_unit() accepts the price_unit and sets it to the oductData Probject
     * @param price_unit is the price per unit of the product
     */
    public void setPrice_unit(double price_unit) {
        this.price_unit = price_unit;
    }

    /**
     *public Integer getMin() returns the min inventory allowed for that product
     * @return min is the min inventory allowed for that product
     */
    public Integer getMin() {
        return min;
    }

    /**
     *public void setMin() accepts the min inventory allowed for that product and sets it to the ProductData object.
     * @param min is the min inventory allowed for that product
     */
    public void setMin(Integer min) {
        this.min = min;
    }

    /**
     *public Integer getMax() returns the max inventory allowed for that product.
     * @return max is the max inventory allowed for that product
     */
    public Integer getMax() {
        return max;
    }

    /**
     *public void setMax() accepts the max inventory allowed for that product and sets it to the ProductData object.
     * @param max is the max inventory allowed for that part
     */
    public void setMax(Integer max) {
        this.max = max;
    }

//    public ArrayList<Part> getAssociatedParts() {
//        return associatedParts;
//    }
//
//    public void setAssociatedParts(ArrayList<Part> associatedParts) {
//        this.associatedParts = associatedParts;
//    }
}
