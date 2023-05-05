package em_ims.em_inventorymanagementsoftware;

public class InHouse extends Part{
    private int machineID;

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID){
        setId(id);
        setName(name);
        setPrice(price);
        setMin(min);
        setMax(max);
        setMachineID(machineID);
    }

    public int getMachineID(){
        return machineID;
    }

    public void setMachineID(int machineID) {
       this.machineID = machineID;
    }
}
