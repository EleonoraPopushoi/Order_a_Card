package ru.netology.rest;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderACardTest {

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Тамара Петровна");
        $("[data-test-id=phone] input").setValue("+79267406485");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(By.className("paragraph")).shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotSendInvalidName() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Tamara Petrovna");
        $("[data-test-id=phone] input").setValue("+79267406485");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(".input_theme_alfa-on-white.input_invalid .input__sub").shouldHave(exactText("Имя или Фамилия были указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSendOverMaxPhoneNumber() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Тамара Петровна");
        $("[data-test-id=phone] input").setValue("+7926740648500");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(".input_theme_alfa-on-white.input_invalid .input__sub").shouldHave(exactText("Номер телефона указан неверно. Допустимо только 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldNotSendAboveMinPhoneNumber() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Тамара Петровна");
        $("[data-test-id=phone] input").setValue("+7926740");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(".input_theme_alfa-on-white.input_invalid .input__sub").shouldHave(exactText("Номер телефона указан неверно. Допустимо только 11 цифр, например, +79267406485."));

    }

    @Test
    void shouldNotSendWithoutCheckbox() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Тамара Петровна");
        $("[data-test-id=phone] input").setValue("+79267406485");
        $(By.className("button")).click();
        $(".checkbox__text").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));

    }

    @Test
    void shouldNotSendEmptyName() {
        open("http://localhost:9999");
        $("[data-test-id=phone] input").setValue("+79267406485");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(".input_theme_alfa-on-white.input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSendEmptyPhone() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Тамара Петровна");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(".input_theme_alfa-on-white.input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

}
