package com.beymen.Page;

import com.beymen.Utilities.Driver;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;


public abstract class BasePage {

    @FindBy(id = "onetrust-reject-all-handler")
    public WebElement cookiesButton;


    public BasePage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }





}

