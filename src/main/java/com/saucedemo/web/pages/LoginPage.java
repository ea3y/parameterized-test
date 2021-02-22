package com.saucedemo.web.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.saucedemo.web.model.User;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private final SelenideElement userName = $x("//input[@data-test='username']");

    public void login(User user) {
        setUserName(user.getName());
        setPassword(user.getPassword());
        clickLoginButton();
    }

    public static LoginPage open() {
        return Selenide.open("https://www.saucedemo.com/", LoginPage.class);
    }

    public String setIncorrectCredentialsAndGetErrorMessage(String name, String password) {
        setUserName(name).setPassword(password).clickLoginButton();
        return getErrorText();
    }

    public LoginPage setUserName(String name) {
        userName.setValue(name);
        return this;
    }

    public LoginPage setPassword(String password) {
        $x("//input[@data-test='password']").setValue(password);
        return this;
    }

    public void clickLoginButton() {
        $x("//input[@id='login-button']").click();
    }

    public LoginPage clearUsernameField() {
        userName.sendKeys(Keys.CONTROL + "a");
        userName.sendKeys(Keys.DELETE);
        return this;
    }

    public String getErrorText() {
        System.out.println($x("//h3[@data-test='error']").getText());
        return $x("//h3[@data-test='error']").getText();
    }
}
