package ua.hotline.PageObjects;

import core.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Item {


    private WebElement type = Driver.getDriver().findElement(By.xpath("//tr[2]/td[@class='cell-8 cell-xs-6']/div[@class='p_l-10']/span/a"));

    public Item waitForSpecificationPage() {
        Driver.getDriverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class=\"specification-table viewbox\"]")));
        return new Item();
    }

    public String getType() {
        return type.getText();
    }
}
