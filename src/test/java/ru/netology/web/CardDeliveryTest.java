package ru.netology.web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.ownText;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
	
	  public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void shouldTest() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String planningDate = generateDate(4);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $x("//*[text()=\"Забронировать\"]").click();
        $(byClassName("notification__title")).should(ownText("Успешно!"), Duration.ofSeconds(15));
        $(byClassName("notification__content")).should(ownText("Встреча успешно забронирована на " + date), Duration.ofSeconds(15));

    }
}
