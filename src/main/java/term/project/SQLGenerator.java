package term.project;

import java.util.ArrayList;

public class SQLGenerator {
    
    public static String ConvertProductToSQL(ArrayList<Product> Products) {
        String FullSql = "INSERT INTO\nProdcuts\n(name, sku, color, quantity, dateAdded, size)\nVALUES\n";
        for (Product product : Products) {
            String SqlLine = GetSQLString(product);
            FullSql = FullSql + SqlLine;
        }
        FullSql = FullSql.substring(0, FullSql.length() -2);
        FullSql = FullSql + ";";
        return FullSql;
    }
    
    public static String GetSQLString(Product product) {
        String sql = "('" + product.getName() + "', '" + product.getSKU() + "', '" + product.getColor() + "', " + product.getQuantity() + ", '" + product.getDateAdded() + "','" + product.getSize() + "'),\n";
        return sql;
    }
}
