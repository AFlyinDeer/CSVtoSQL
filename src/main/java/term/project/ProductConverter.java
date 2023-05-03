package term.project;

import java.util.List;
import java.util.ArrayList;

public class ProductConverter {
    
    public static ArrayList<Product> GetProducts(String TestURL) {
        List<List<String>> rawCsv = CSVReader.readCSVFile(TestURL);

        // The first line of a CSV typically is the names of the columns,
        // I removed this line because I only want the product data 
        rawCsv.remove(0);
        
        ArrayList<Product> Products = new ArrayList<Product>();
        for (List<String> product : rawCsv) {
            String ProductName = product.get(0);
            String ProductSKU = product.get(1);
            String ProductQuantity = product.get(2);
            String ProductDate = product.get(3);
            String ProductColor = product.get(4);
            String ProductSize = product.get(5);
            Product p = new Product(ProductName, ProductSKU, ProductColor, ProductQuantity, ProductDate, ProductSize);
            Products.add(p);
        }

        return Products;
    }

}
