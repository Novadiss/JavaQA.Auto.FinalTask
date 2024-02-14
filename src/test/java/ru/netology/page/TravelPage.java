package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import java.security.PrivateKey;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$$;

public class TravelPage {

    private final ElementsCollection formForInsert = $$(".input__inner");
    private final ElementsCollection buttonToClick = $$(".button__text");
    private final SelenideElement confirmedMessage = $(".notification_status_ok");
    private final SelenideElement errorMessage = $(".notification_status_error");


    public void clickButton(String button) {
        buttonToClick.findBy(text(button)).click();
    }

    public void setForm(String form, String value){
        formForInsert.findBy(text(form)).$("input").setValue(value);
    }

    public void cleanForm(String form) {
        formForInsert.findBy(text(form)).$("input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }

    public void findInvalidFormat(String form, String text) {
        formForInsert.findBy(text(form)).shouldHave(text(text), Duration.ofSeconds(15)).shouldBe(visible);
    }

    public void findErrorMessage(String expectedText) {
        errorMessage.shouldHave(text(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }

    public void findConfirmedMessage(String expectedText) {
        confirmedMessage.shouldHave(text(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
