package qrcode.generate.bean;

public class ShopInfo{

    private String name;
    private String id;
    private String order;
    private String city;

    public String getOrder(){
        return order;
    }

    public void setOrder(String order){
        this.order = order;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    @Override
    public boolean equals(Object object){
        return (this == object) ||
                ((object instanceof ShopInfo)
                        && equals(name,((ShopInfo)object).name)
                        && equals(id,((ShopInfo)object).id));
    }

    private boolean equals(String s1,String s2){
        return (s1 == null && s2 == null)
                || ((s1 != null) && s1.equals(s2));
    }

    @Override
    public String toString(){
        return name + "\t" + id + "\t";
    }
}
