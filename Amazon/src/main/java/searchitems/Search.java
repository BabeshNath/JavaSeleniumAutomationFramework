package searchitems;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by rrt on 4/30/2016.
 */
public class Search {
    @FindBy(how = How.CSS, using="#twotabsearchtextbox")
    public static WebElement searchInput;

    @FindBy(how=How.CSS, using=".nav-input")
    public WebElement searchSubmit;

    public void searchFor(String item){
        searchInput.sendKeys(item, Keys.ENTER);
    }
    public void clearSearch(){
        searchInput.clear();
    }
}
