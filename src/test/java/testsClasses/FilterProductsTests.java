package testsClasses;

import config.SetupTearDown;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.ShopPage;

public class FilterProductsTests extends SetupTearDown {
    @Parameters({"minPrice","maxPrice"})
    @Test
    public void filterByPrice(int minimumPrice,int maximumPrice){
        // Open Shop page
        ShopPage shopPage = new ShopPage(driver);
        shopPage.openPage();
        // Filter products by price
        shopPage.filterProductsByPrice(minimumPrice,maximumPrice);
        // verify only filtered products are displayed
        shopPage.verifyFilteredProducts(minimumPrice,maximumPrice);
    }

    @Test
    public void filterByCategory(){
        // Open Shop page
        ShopPage shopPage = new ShopPage(driver);
        shopPage.openPage();
        // Filter products by category
        shopPage.filterProductsByCategory();
    }
}
