package em_ims.em_inventorymanagementsoftware;

public class Outsourced extends Part {
    private String company_name;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String company_name){
        setId(id);
        setName(name);
        setPrice(price);
        setMin(min);
        setMax(max);
        setCompany_name(company_name);
    }

    public String getCompany_name(){
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
