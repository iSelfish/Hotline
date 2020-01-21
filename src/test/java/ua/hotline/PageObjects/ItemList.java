package ua.hotline.PageObjects;

import core.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ItemList {
    private By firstItem = By.xpath("//li[@class='product-item'][1]/div[@class='item-info']/p/a");

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
        Driver.getDriver().findElement(By.xpath("//ul[@class='sorting-in']/li/select[@class='field']")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        return new ItemList();
    }

    public int countExpensiveItems(int maxPrice, int amountOfPages) {
        int page = 1;
        String priceString;
        int price;
        int expensiveItemsCounter = 0;
        do {
            System.out.println("current page = " + page);
            for (int i = 1; i < 21; i++) {
                try {
                    System.out.println("Item = " + i);
                    priceString = Driver.getDriver().findElement(By.xpath("//li[@class='product-item'][" + i + "]//span[@class='value']")).getText();
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
            new ItemList().nextPage();
            page++;
        } while (page < (amountOfPages + 1));
        return expensiveItemsCounter;
    }

    private ItemList nextPage() {
        Driver.getDriver().findElement(By.xpath("//a[@class='next']")).click();
        return new ItemList();
    }
}
