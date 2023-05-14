package em_ims.em_inventorymanagementsoftware;

public class InHouse extends Part{
//    private int machineID;
    public int machineID;

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

    public void addInHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        Part inHousePart = new InHouse(id, name, price, stock, min, max, machineID);
        Inventory.allParts.add(inHousePart);
    }

    public int getMachineID(){
        return machineID;
    }

    public void setMachineID(int machineID) {
       this.machineID = machineID;
    }
}
