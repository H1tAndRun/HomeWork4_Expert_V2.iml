public class Shop {
    double Outcom=0;
    String name;

    public  double getOutcom() {
        return Outcom;
    }

    public void setOutcom(double outcom) {
        Outcom = outcom;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;

    }
}
