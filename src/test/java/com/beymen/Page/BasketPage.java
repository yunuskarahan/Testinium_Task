package com.beymen.Page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends BasePage{


    @FindBy(className = "m-productPrice__salePrice")
    public WebElement basketProductPrice;

   // @FindBy(id = "quantitySelect0-key-0")
    @FindBy(id = "quantitySelect0-key-0")
    public WebElement quantityOptions;

    @FindBy(id = "removeCartItemBtn0-key-0")
    public WebElement basketRemove;

    @FindBy(xpath = "//div[@id='emtyCart']/div/strong")
    public WebElement basketMessageText;

    @FindBy(id = "notifyTitle")
    public WebElement productDeleteMessage;



}
