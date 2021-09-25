package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {




    @Test
    void verificationTransactionFromFirstToSecond() {
        int amountToAddForTest = 300;
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataGenerator.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataGenerator.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceFirstCardBefore = DashboardPage.getCurrentBalanceOfFirstCard();
        val balanceSecondCardBefore = DashboardPage.getCurrentBalanceOfSecondCard();
        val TransferPage = dashboardPage.firstCardToRecharge();
        val cardInfo = DataGenerator.getSecondCardInfo();
        TransferPage.transferOnCard(cardInfo);
        val balanceAfterTransactionOnRecharged = DataGenerator.checkBalanceCardPlus(balanceFirstCardBefore, amountToAddForTest);
        val balanceAfterTransaction = DataGenerator.checkBalanceCardMinus(balanceSecondCardBefore, amountToAddForTest);
        val balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
        val balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();
        assertEquals(balanceAfterTransactionOnRecharged, balanceOfFirstCardAfter);
        assertEquals(balanceAfterTransaction, balanceOfSecondCardAfter);

    }

    @Test
    void verificationTransactionFromSecondToFirst() {
        int amountToAddForTest = 700;
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataGenerator.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataGenerator.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceFirstCardBefore = DashboardPage.getCurrentBalanceOfFirstCard();
        val balanceSecondCardBefore = DashboardPage.getCurrentBalanceOfSecondCard();
        val TransferPage = dashboardPage.firstCardToRecharge();
        val cardInfo = DataGenerator.getFirstCardInfo();
        TransferPage.transferOnCard(cardInfo);
        val balanceAfterTransactionOnRecharged = DataGenerator.checkBalanceCardPlus(balanceSecondCardBefore, amountToAddForTest);
        val balanceAfterTransaction = DataGenerator.checkBalanceCardMinus(balanceFirstCardBefore, amountToAddForTest);
        val balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();
        val balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
        assertEquals(balanceAfterTransactionOnRecharged, balanceOfFirstCardAfter);
        assertEquals(balanceAfterTransaction, balanceOfSecondCardAfter);

    }
}
