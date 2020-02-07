package ua.hotline.PageObjects;

import core.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import static core.Driver.getDriver;


public class ItemList {
    private By firstItem = By.xpath("//li[@class='product-item'][1]/div[@class='item-info']/p/a");
    private By nextPageButton = By.xpath("//a[@class='next']");
    private By sortButton = By.xpath("//ul[@class='sorting-in']/li/select[@class='field']");
    private By allPricesOnCurrentPage = By.xpath("//div[@class='price-md']//span[@class='value']");

    public ItemList waitForProductsListPage() {
        Driver.getDriverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class='products-list cell-list']")));
        return new ItemList();
    }

    public Item openFirstItem() {
        WebElement firstItemElement = getDriver().findElement(firstItem);
        firstItemElement.click();
        return new Item();
    }

    public ItemList sortByPrice() {
        WebElement sortButtonElement = getDriver().findElement(sortButton);
        sortButtonElement.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        return new ItemList();
    }

    public List<Integer> pagePriceList() {
        List<Integer> priceList = new ArrayList<>();
        for (WebElement element : getDriver().findElements(allPricesOnCurrentPage)) {
            String elementText = element.getText().replaceAll(" ", "");
            priceList.add(Integer.parseInt(elementText));
        }
        System.out.println("price list = " + priceList);
        return priceList;
    }

    public int countItemsMoreExpensiveThan(int price, int amountOfPages) {
        int page = 1;
        int expensiveItemsCounter = 0;
        WebElement nextPageButtonElement;
        do {
            System.out.println("current page = " + page);
            for (int itemPrice : pagePriceList()) {
                if (itemPrice > price) {
                    expensiveItemsCounter++;
                }
            }
            System.out.println("expensiveItemsCounter = " + expensiveItemsCounter);
            nextPageButtonElement = getDriver().findElement(nextPageButton);
            nextPageButtonElement.click();
            page++;
        } while (page < (amountOfPages + 1));
        return expensiveItemsCounter;
    }
}
