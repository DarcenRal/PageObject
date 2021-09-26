package ru.netology.page;


import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;


import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private  SelenideElement amountInput = $("[data-test-id=amount] input");
    private  SelenideElement cardInput = $("[data-test-id=from] input");
    private  SelenideElement transferButton = $("[data-test-id=action-transfer]");



    public void transferOnCard(DataGenerator.CardInfo info, int amountToAddForTest) {

        amountInput.setValue(String.valueOf(amountToAddForTest));
        cardInput.setValue(info.getCardNumber());
        transferButton.click();
    }




}
