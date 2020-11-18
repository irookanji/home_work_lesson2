package tests.lesson3;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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


        sleep(5000);

    }

    @Test
    void goToDepositsThenToInsuranceTest() {
        open("https://alfabank.ru");

        // Go to "Вклады" tab
        $$(byText("Вклады")).find(visible).click();

    }

}
