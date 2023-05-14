package em_ims.em_inventorymanagementsoftware;

public class Outsourced extends Part {
//    private String company_name;
    public String company_name;


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

    public void addOutsourced(int id, String name, double price, int stock, int min, int max, String company_name) {
        Part outsourcedPart = new Outsourced(id, name, price, stock, min, max, company_name);
        Inventory.allParts.add(outsourcedPart);
    }


    public String getCompany_name(){
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
