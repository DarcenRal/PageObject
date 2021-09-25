package ru.netology.data;


import lombok.Value;

public class DataGenerator {
    @Value
    public static class Authorisation {
        public String login;
        public String password;
    }

    public static Authorisation getAuthInfo() {
        return new Authorisation("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(Authorisation authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String cardBalance;
    }

    public static CardInfo getFirstCardInfo() {
        return new CardInfo("5559 0000 0000 0001", "10000");
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo("5559 0000 0000 0002", "10000");
    }

    public static int checkBalanceCardPlus(int balance, int amountToAddForTest) {
        int cardBalancePlus = balance + amountToAddForTest;
        return cardBalancePlus;
    }

    public static int checkBalanceCardMinus(int balance, int amountToAddForTest) {
        int cardBalanceMinus = balance - amountToAddForTest;
        if (cardBalanceMinus < 0) {
            return balance;
        }
        return cardBalanceMinus;

    }
}