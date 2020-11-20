package tests.lesson3;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AlfabankTests {
    @BeforeAll
    static public void config() {
        Configuration.startMaximized = true;
    }

    @Test
    void checkDepositTest() {
        open("https://alfabank.ru/make-money/");

        // Go to Deposits tab
        $$(byText("Депозиты")).find(visible).parent().click();

        // Go to Archived debts & deposits tab
        $$(byText("Архивные счета и депозиты")).find(visible).parent().click();

        // Choose Deposits tab
        $$(byText("Депозиты")).find(visible).parent().click();

        // Check that Deposits = 5
        $("#filter").$$("[data-widget-name=CatalogCard]").shouldHaveSize(5);

    }

    @Test
    void siblingXpathTest() {
        open("https://alfabank.ru");

        // Go to "Вклады" tab
        $$(byText("Вклады")).find(visible).click();

        // Go to "Страхование вкалдов" tab используя Sibling
        $x("//button[@data-test-id='tabs-list-tabTitle-0']/following-sibling::*[1]"
        ).click();

        // Assert
        $$x("//div[@class='a1Etq03']").shouldHaveSize(4);
        $("[data-widget-uid=c755ade9e2] span")
                .shouldHave(text("Альфа-Банк является участником системы обязательного страхования вкладов"));

    }

    @Test
    void precendingXpathTest() {
        open("https://alfabank.ru");

        // Go to "Вклады" tab
        $$(byText("Вклады")).find(visible).click();

        // Go to "Страхование вкалдов" tab используя Preceding
        $x("//button[@data-test-id='tabs-list-tabTitle-2']/preceding::button[1]").click();

        // Assert
        $("[data-widget-uid=ed114143b4] span")
                .shouldHave(text("Страхованию подлежат"));

    }

    @Test
    void parentXpathTest() {
        open("https://alfabank.ru");

        // Go to "Вклады" tab
        $$(byText("Вклады")).find(visible).click();

        // Go to "Страхование вкалдов" tab используя Parent
        $x("//*[contains(text(),'Страхование вкладов')]//parent::button[1]").click();

        // Assert
        $$x("//div[@class='a1Etq03']").shouldHaveSize(4);
    }

    @Test
    void closestXpathTest() {
        open("https://alfabank.ru");

        // Go to "Вклады" tab
        $$(byText("Вклады")).find(visible).click();

        // Go to "Страхование вкалдов" tab используя Closest
        $x("//*[contains(text(),'Страхование вкладов')]").closest("button").click();
    }

}
