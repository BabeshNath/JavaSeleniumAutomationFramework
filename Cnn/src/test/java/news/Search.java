package news;

import base.CommonAPI;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import reader.ReadNewsData;

import java.io.IOException;

/**
 * Created by rrt on 4/23/2016.
 */
public class Search extends CommonAPI{
    ReadNewsData readNewsData = new ReadNewsData();
    @Test
    public void searchNews() throws InterruptedException,IOException{
        clickByCss("#search-button");
        Thread.sleep(1000);
        typeByCss("#search-input-field", readNewsData.getData());
        takeEnterKeys("#search-input-field");
        Thread.sleep(3000);
    }
}
