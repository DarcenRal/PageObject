package ru.netology.test;

import com.codeborne.selenide.Condition;
import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;


import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {


    @Test
    void verificationTransactionFromSecondToFirst() {
        int amount = 300;
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataGenerator.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataGenerator.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceFirstCardBefore = DashboardPage.getCurrentBalanceOfFirstCard();
        val balanceSecondCardBefore = DashboardPage.getCurrentBalanceOfSecondCard();
        val transferPage = dashboardPage.firstCardToRecharge();
        val cardInfo = DataGenerator.getSecondCardInfo();
        transferPage.transferOnCard(cardInfo, amount);
        val balanceAfterTransactionOnRecharged = DataGenerator.checkBalanceCardPlus(balanceFirstCardBefore, amount);
        val balanceAfterTransaction = DataGenerator.checkBalanceCardMinus(balanceSecondCardBefore, amount);
        val balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
        val balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();
        assertEquals(balanceAfterTransactionOnRecharged, balanceOfFirstCardAfter);
        assertEquals(balanceAfterTransaction, balanceOfSecondCardAfter);

    }

    @Test
    void verificationTransactionFromFirstToSecond() {
        int amount = 700;
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataGenerator.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataGenerator.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceFirstCardBefore = DashboardPage.getCurrentBalanceOfFirstCard();
        val balanceSecondCardBefore = DashboardPage.getCurrentBalanceOfSecondCard();
        val transferPage = dashboardPage.secondCardToRecharge();
        val cardInfo = DataGenerator.getFirstCardInfo();
        transferPage.transferOnCard(cardInfo, amount);
        val balanceAfterTransactionOnRecharged = DataGenerator.checkBalanceCardPlus(balanceSecondCardBefore, amount);
        val balanceAfterTransaction = DataGenerator.checkBalanceCardMinus(balanceFirstCardBefore, amount);
        val balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();
        val balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
        assertEquals(balanceAfterTransactionOnRecharged, balanceOfFirstCardAfter);
        assertEquals(balanceAfterTransaction, balanceOfSecondCardAfter);

    }

    @Test
    void transferMoreThanBalanceFirstTest () {
        int amount = 20000;
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataGenerator.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataGenerator.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val transferPage = dashboardPage.secondCardToRecharge();
        val cardInfo = DataGenerator.getFirstCardInfo();
        transferPage.transferOnCard(cardInfo, amount);
        $(withText("На балансе недостаточно средств")).shouldBe(Condition.visible);
    }

    @Test
    void transferMoreThanBalanceSecondTest() {
        int amount = 20000;
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataGenerator.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataGenerator.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceFirstCardBefore = DashboardPage.getCurrentBalanceOfFirstCard();
        val balanceSecondCardBefore = DashboardPage.getCurrentBalanceOfSecondCard();
        val transferPage = dashboardPage.firstCardToRecharge();
        val cardInfo = DataGenerator.getSecondCardInfo();
        transferPage.transferOnCard(cardInfo, amount);
        val balanceAfterTransactionOnRecharged = DataGenerator.checkBalanceCardPlus(balanceFirstCardBefore, amount);
        val balanceAfterTransaction = DataGenerator.checkBalanceCardMinus(balanceSecondCardBefore, amount);
        val balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
        val balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();
        assertEquals(balanceAfterTransactionOnRecharged, balanceOfFirstCardAfter);
        assertEquals(balanceAfterTransaction, balanceOfSecondCardAfter);
    }
}
