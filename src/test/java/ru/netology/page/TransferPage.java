package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private static SelenideElement amountInput = $("[data-test-id=amount] input");
    private static SelenideElement cardInput = $("[data-test-id=from] input");
    private static SelenideElement transferButton = $("[data-test-id=action-transfer]");



    public static void transferOnCard(DataGenerator.CardInfo info) {
        String amountToAddForTest = "300";
        amountInput.setValue(String.valueOf(amountToAddForTest));
        cardInput.setValue(info.getCardNumber());
        transferButton.click();
    }




}
