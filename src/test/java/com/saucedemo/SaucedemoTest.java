package com.saucedemo;

import com.saucedemo.web.model.User;
import com.saucedemo.web.pages.LoginPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

import static com.saucedemo.web.pages.LoginPage.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaucedemoTest {

    @ParameterizedTest(name = "#{index} - Test with String : {0}")
    @MethodSource("loginDataProvider")
    void authError(User user, String message) {
        var loginPage = new LoginPage();

        open();
        loginPage.login(user);
        assertEquals(message, loginPage.getErrorText());
    }

    private static Stream<Arguments> loginDataProvider() {
        return Stream.of(
                Arguments.of(new User().setName(UUID.randomUUID().toString()).setPassword(""),
                        "Epic sadface: Password is required"),
                Arguments.of(new User().setName("").setPassword(UUID.randomUUID().toString()),
                        "Epic sadface: Username is required"),
                Arguments.of(new User().setName(UUID.randomUUID().toString()).setPassword(UUID.randomUUID().toString()),
                        "Epic sadface: Username and password do not match any user in this service"));
    }
}
