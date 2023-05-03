package term.project;

public class Product {
    String Name;
    String SKU;
    String Color;
    String Quantity;
    String DateAdded;
    String Size;

    public Product(String name, String sku, String color, String quantity, String dateAdded, String size){
        this.Name = name;
        this.SKU = sku;
        this.Color = color;
        this.Quantity = quantity;
        this.DateAdded = dateAdded;
        this.Size = size;
    }
    
    public String getName(){
        return this.Name;
    }

    public String getSKU(){
        return this.SKU;
    }

    public String getColor(){
        return this.Color;
    }

    public String getQuantity(){
        return this.Quantity;
    }

    public String getDateAdded(){
        return this.DateAdded;
    }

    public String getSize(){
        return this.Size;
    }
    
    @Override
    public String toString(){
        return "Name: " + this.Name + ", SKU: " + this.SKU + ", Color: " + this.Color + ", Quantity: " + this.Quantity + ", Date Added: " + this.DateAdded + ", Size: " + this.Size;
    }
}
