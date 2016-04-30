package search;

import base.CommonAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import searchitems.ProductsToBeSearched;
import searchitems.Search;

import java.io.IOException;

/**
 * Created by rrt on 4/30/2016.
 */
public class Products extends CommonAPI {

    @Test
    public void searchItems()throws IOException, InterruptedException{
        Search search = PageFactory.initElements(driver, Search.class);
        ProductsToBeSearched productsToBeSearched = new ProductsToBeSearched();
        String [] products = productsToBeSearched.getProducts();
        for(String product:products){
            search.searchFor(product);
            sleepFor(2);
            search.clearSearch();
        }

    }
}
