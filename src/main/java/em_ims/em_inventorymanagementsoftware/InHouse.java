package em_ims.em_inventorymanagementsoftware;

/**
 * @author Evelyn G Morrow.
 * @version 1.1.
 * Public class InHouse is an extension of the Part class used to display the in-house parts on the parts tableview.
 * RUNTIME ERROR: I was trying to add the in house part by just the id and the machine number. I ended up modifying the inHouse class, and the inventory to be able to extend the part class to inHouse or Outsourced, depending on the type of part.
 * FUTURE ENHANCEMENT: Creating the filter to insert the in house or outsourced part into the mysql database.
 */
public class InHouse extends Part{
    public int machineID;

    /**
     * Public InHouse Constructor is called when the Product object is instantiated.
     * It has the function of initialize the newly in-house created object before it is used.
     * The list of parameters that the public Part constructor can take are declared into the parenthesis, and listed below:
     * @param id is taken by public Part Constructor, and initializes the private Integer partID variable.
     * @param name is taken by public Part Constructor, and initializes the private String part_name variable.
     * @param stock is taken by public Part Constructor, and initializes the private Integer stock variable.
     * @param price is taken by public Part Constructor, and initializes the private BigDecimal price_unit variable.
     * @param max is taken by public Part Constructor, and initializes the private Integer max variable.
     * @param min is taken by public Part Constructor, and initializes the private Integer min variable.
     * @param machineID is taken by public Part Constructor, and initializes the private String machineID variable.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID){
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
//        setId(id);
//        setName(name);
//        setPrice(price);
//        setStock(stock);
//        setMin(min);
//        setMax(max);
//        setMachineID(machineID);
    }

    /**
     * Public void addInHouse() method is used to add a new in-house part to out EM database.
     * @param id is taken by public Part Constructor, and initializes the private Integer partID variable.
     * @param name is taken by public Part Constructor, and initializes the private String part_name variable.
     * @param stock is taken by public Part Constructor, and initializes the private Integer stock variable.
     * @param price is taken by public Part Constructor, and initializes the private BigDecimal price_unit variable.
     * @param max is taken by public Part Constructor, and initializes the private Integer max variable.
     * @param min is taken by public Part Constructor, and initializes the private Integer min variable.
     * @param machineID is taken by public Part Constructor, and initializes the private String machineID variable.
     */
    public void addInHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        Part inHousePart = new InHouse(id, name, price, stock, min, max, machineID);
        Inventory.allParts.add(inHousePart);
    }

    /**
     * @return the machineID.
     */
    public int getMachineID(){
        return machineID;
    }

    /**
     * @param machineID the machineID to set
     */
    public void setMachineID(int machineID) {
       this.machineID = machineID;
    }
}
