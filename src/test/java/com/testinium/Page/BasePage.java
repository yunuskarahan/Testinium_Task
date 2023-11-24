package com.testinium.Page;

import com.testinium.Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public abstract class BasePage {

    @FindBy(id = "onetrust-reject-all-handler")
    public WebElement cookiesButton;


    public BasePage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }





}

