package searchitems;

import util.DataReader;

import java.io.IOException;

/**
 * Created by rrt on 4/30/2016.
 */
public class ProductsToBeSearched {

    DataReader dataReader = new DataReader();

    public String[] getProducts()throws IOException {
        String path = System.getProperty("user.dir")+"\\data\\file1.xls";
        String [] products = dataReader.fileReader(path);

        return products;
    }
}
