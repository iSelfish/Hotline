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

    /*private int countExpensiveItemsOnCurrentPage(int maxPrice) {
        int expensiveItemsCount = 0;
        String priceString;
        String checkClass;
        String checkPrice;
        int price;
        for (int i = 1; i < 21; i++) {
            try {
                checkClass = Driver.getDriver().findElement(By.xpath("//li[@class='product-item'][" + i + "]/div[3]")).getAttribute("class");
                System.out.println("Item = " + i);
                if (checkClass.equals("item-price stick-bottom")) {
                    checkPrice = Driver.getDriver().findElement(By.xpath("//li[@class='product-item'][" + i + "]/div[3]/div[@class='stick-pull cell-xs-6'][1]/div[@class='price-md']/span[1]")).getAttribute("class");
                    if (checkPrice.equals("price-format")) {
                        priceString = Driver.getDriver().findElement(By.xpath("//li[@class='product-item'][" + i + "]/div[@class='item-price stick-bottom']/div[@class='stick-pull cell-xs-6'][1]/div[@class='price-md']/span[@class='price-format']/span[@class='value']")).getText();
                    } else {
                        priceString = Driver.getDriver().findElement(By.xpath("//li[@class='product-item'][" + i + "]/div[@class='item-price stick-bottom']/div[@class='stick-pull cell-xs-6'][1]/div[@class='price-md']/span[@class='value']")).getText();
                    }
                    priceString = priceString.replaceAll(" ", "");
                    price = Integer.parseInt(priceString);
                    System.out.println("price = " + price);
                    if (price > maxPrice) {
                        expensiveItemsCount++;
                    }
                }
            } catch (NoSuchElementException ignored) {
            }
        }
        return expensiveItemsCount;
    }*/
    private int countExpensiveItemsOnCurrentPage(int maxPrice) {
        int expensiveItemsCount = 0;
        String priceString;
        String checkClass;
        int price;
        for (int i = 1; i < 21; i++) {
            try {
                checkClass = Driver.getDriver().findElement(By.xpath("//li[@class='product-item'][" + i + "]/div[3]")).getAttribute("class");
                System.out.println("Item = " + i);
                if (checkClass.equals("item-price stick-bottom")) {
                    priceString = Driver.getDriver().findElement(By.xpath("//li[@class='product-item'][" + i + "]//span[@class='value']")).getText();
                    priceString = priceString.replaceAll(" ", "");
                    price = Integer.parseInt(priceString);
                    System.out.println("price = " + price);
                    if (price > maxPrice) {
                        expensiveItemsCount++;
                    }
                }
            } catch (NoSuchElementException ignored) {
            }
        }
        return expensiveItemsCount;
    }

    public int countExpensiveItems(int maxPrice, int amountOfPages) {
        int page = 1;
        int expansiveItemsCounter = 0;
        do {
            System.out.println("current page = " + page);
            expansiveItemsCounter += new ItemList().countExpensiveItemsOnCurrentPage(maxPrice);
            System.out.println("expensiveItemsCounter = " + expansiveItemsCounter);
            new ItemList().nextPage();
            page++;
        } while (page < (amountOfPages + 1));
        return expansiveItemsCounter;
    }

    private ItemList nextPage() {
        Driver.getDriver().findElement(By.xpath("//a[@class='next']")).click();
        return new ItemList();
    }
}
