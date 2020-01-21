package ua.hotline.PageObjects;

import core.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ItemList {
    private By firstItem = By.xpath("//li[@class='product-item'][1]/div[@class='item-info']/p/a");
    private By nextPageButton = By.xpath("//a[@class='next']");
    private By sortButton = By.xpath("//ul[@class='sorting-in']/li/select[@class='field']");

    public ItemList waitForProductsListPage() {
        Driver.getDriverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class=\"products-list cell-list\"]")));
        return new ItemList();
    }

    public Item openFirstItem() {
        WebElement firstItemElement = Driver.getDriver().findElement(firstItem);
        firstItemElement.click();
        return new Item();
    }

    public ItemList sortByPrice() {
        WebElement sortButtonElement = Driver.getDriver().findElement(sortButton);
        sortButtonElement.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        return new ItemList();
    }

    public int countExpensiveItems(int maxPrice, int amountOfPages) {
        int page = 1;
        String priceString;
        int price;
        int expensiveItemsCounter = 0;
        WebElement nextPageButtonElement;
        do {
            System.out.println("current page = " + page);
            for (int i = 1; i < 21; i++) {
                try {
                    System.out.println("Item = " + i);
                    priceString = Driver.getDriver()
                            .findElement(By.xpath("//li[@class='product-item'][" + i + "]//span[@class='value']")).getText();
                    priceString = priceString.replaceAll(" ", "");
                    price = Integer.parseInt(priceString);
                    System.out.println("price = " + price);
                    if (price > maxPrice) {
                        expensiveItemsCounter++;
                    }
                } catch (NoSuchElementException ignored) {
                }
            }
            System.out.println("expensiveItemsCounter = " + expensiveItemsCounter);
            nextPageButtonElement = Driver.getDriver().findElement(nextPageButton);
            nextPageButtonElement.click();
            page++;
        } while (page < (amountOfPages + 1));
        return expensiveItemsCounter;
    }
}
