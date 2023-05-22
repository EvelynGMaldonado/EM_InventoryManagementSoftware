package em_ims.em_inventorymanagementsoftware;

/**
 * @author Evelyn G Morrow.
 * @version 1.1.
 * Public class Outsourced is an extension of the Part class used to display the outsourced parts on the parts tableview.
 * RUNTIME ERROR:
 * FUTURE ENHANCEMENT:
 */
public class Outsourced extends Part {
    public String company_name;

    /**
     * Public Outsourced Constructor is called when the Product object is instantiated.
     * It has the function of initialize the newly outsourced created object before it is used.
     * The list of parameters that the public Part constructor can take are declared into the parenthesis, and listed below:
     * @param id is taken by public Part Constructor, and initializes the private Integer partID variable.
     * @param name is taken by public Part Constructor, and initializes the private String part_name variable.
     * @param stock is taken by public Part Constructor, and initializes the private Integer stock variable.
     * @param price is taken by public Part Constructor, and initializes the private BigDecimal price_unit variable.
     * @param max is taken by public Part Constructor, and initializes the private Integer max variable.
     * @param min is taken by public Part Constructor, and initializes the private Integer min variable.
     * @param company_name is taken by public Part Constructor, and initializes the private String company_name variable.
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String company_name){
        super(id, name, price, stock, min, max);
        this.company_name = company_name;
//        setId(id);
//        setName(name);
//        setPrice(price);
//        setStock(stock);
//        setMin(min);
//        setMax(max);
//        setCompany_name(company_name);
    }

    /**
     * Public void addOutsourced() method is used to add a new outsourced part to out EM database.
     * @param id is taken by public Part Constructor, and initializes the private Integer partID variable.
     * @param name is taken by public Part Constructor, and initializes the private String part_name variable.
     * @param stock is taken by public Part Constructor, and initializes the private Integer stock variable.
     * @param price is taken by public Part Constructor, and initializes the private BigDecimal price_unit variable.
     * @param max is taken by public Part Constructor, and initializes the private Integer max variable.
     * @param min is taken by public Part Constructor, and initializes the private Integer min variable.
     * @param company_name is taken by public Part Constructor, and initializes the private String company_name variable.
     */
    public void addOutsourced(int id, String name, double price, int stock, int min, int max, String company_name) {
        Part outsourcedPart = new Outsourced(id, name, price, stock, min, max, company_name);
        Inventory.allParts.add(outsourcedPart);
    }

    /**
     * @return the company name.
     */
    public String getCompany_name(){
        return company_name;
    }

    /**
     * @param company_name the company name to set.
     */
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
