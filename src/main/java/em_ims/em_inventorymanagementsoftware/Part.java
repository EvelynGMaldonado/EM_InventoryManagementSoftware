package em_ims.em_inventorymanagementsoftware;

/**
 * Supplied class Part.java
 */

/**
 * @author Evelyn G Morrow.
 * @version 1.1.
 * Public class PartData used to display the parts tableview on the dashboard at the Home Page, the founded parts at the Homepage, the available parts at the Add Product Page and at the Modify Product Page .
 * RUNTIME ERROR:
 * FUTURE ENHANCEMENT:
 */
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;


    public Part() {
    }

    /**
     * Public PartData Constructor is called when the ProductData object is instantiated.
     * It has the function of initialize the newly created object before it is used.
     * The list of parameters that the public PartData constructor can take are declared into the parenthesis, and listed below:
     * @param id is taken by public PartData Constructor, and initializes the private Integer partID variable.
     * @param name is taken by public PartData Constructor, and initializes the private String part_name variable.
     * @param stock is taken by public PartData Constructor, and initializes the private Integer stock variable.
     * @param price is taken by public PartData Constructor, and initializes the private BigDecimal price_unit variable.
     * @param max is taken by public PartData Constructor, and initializes the private Integer max variable.
     * @param min is taken by public PartData Constructor, and initializes the private Integer min variable.
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

}