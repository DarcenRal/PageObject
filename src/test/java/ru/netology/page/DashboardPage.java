package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement firstCard= $$("[data-test-id=action-deposit").first();
    private SelenideElement secondCard= $$("[data-test-id=action-deposit").last();
    private SelenideElement upButton = $$("[data-test-id=action-deposit]").last();
    private static SelenideElement balanceCard0001 = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]");
    private static SelenideElement balanceCard0002 = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]");

    public TransferPage firstCardToRecharge() {
        firstCard.click();
        return new TransferPage();
    }

    public TransferPage secondCardToRecharge() {
        secondCard.click();
        return new TransferPage();
    }

    public static int getCurrentBalanceOfFirstCard() {
        String selectedValue = balanceCard0001.getText();
        String balanceFirstCard = selectedValue.substring(29, selectedValue.indexOf(" ", 29));
        return Integer.parseInt(balanceFirstCard);
    }

    public static int getCurrentBalanceOfSecondCard() {
        String selectedValue = balanceCard0002.getText();
        String balanceFirstCard = selectedValue.substring(29, selectedValue.indexOf(" ", 29));
        return Integer.parseInt(balanceFirstCard);
    }



}
