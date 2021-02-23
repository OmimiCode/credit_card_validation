package CreditCardNumberValidation;

import java.security.SecureRandom;

public class CreditCard {
    private static final int divisor = 10;
    private String cardType = "";

    public static boolean isValid(long creditCardNumber) {
        if (isValidLength(creditCardNumber) && isValidFirstDigit(creditCardNumber)) {
            return true;
        } else {
            String e = """
                    credit card number must have between 13 and 16 digits\s
                    and the number must start with 4 for Visa cards,
                    5 for MasterCard Credit cards,
                    6 for Discover cards\s
                    and 37 for American Express cards""";
            throw new IllegalArgumentException(e);
        }
    }

    private static boolean isValidLength(long creditCardNumber) {
        String creditCardNumberAsStrings = Long.toString(creditCardNumber);
        return (creditCardNumberAsStrings.length() >= 13) &&
                (creditCardNumberAsStrings.length() <= 16);
    }

    private static boolean isValidFirstDigit(long creditCardNumber) {
        String creditCardNumberAsStrings = Long.toString(creditCardNumber);
        return creditCardNumberAsStrings.charAt(0) == '4'
                || creditCardNumberAsStrings.charAt(0) == '5'
                || creditCardNumberAsStrings.charAt(0) == '6'
                || creditCardNumberAsStrings.charAt(0) == '3'
                && creditCardNumberAsStrings.charAt(1) == '7';
    }

    public static int sumOfDoubleEvenPlace(long creditCardNumber) {
        int sumOfDoubledEvenPlace = 0;
        int counterLimit = Long.toString(creditCardNumber).length();
        sumOfDoubledEvenPlace = loopThroughCreditCardNumberForSumOfEvenPlaceDigits(creditCardNumber, sumOfDoubledEvenPlace, counterLimit);
        return sumOfDoubledEvenPlace;
    }

    private static int loopThroughCreditCardNumberForSumOfEvenPlaceDigits(long creditCardNumber, int sumOfDoubledEvenPlace, int counterLimit) {
        for (int counter = 1; counter <= counterLimit; counter++) {
            int lastDigit = getLastDigit(creditCardNumber);
            sumOfDoubledEvenPlace = ifNumberIsAtEvenPlace(sumOfDoubledEvenPlace, counter, lastDigit);
            creditCardNumber = truncateLastDigit(creditCardNumber);
        }
        return sumOfDoubledEvenPlace;
    }

    private static int getLastDigit(long creditCardNumber) {
        return (int) (creditCardNumber % divisor);
    }

    private static long truncateLastDigit(long creditCardNumber) {
        creditCardNumber /= divisor;
        return creditCardNumber;
    }

    private static int ifNumberIsAtEvenPlace(int sumOfDoubledEvenPlace, int counter, int lastDigit) {
        if (counter % 2 == 0) {
            lastDigit *= 2;
            sumOfDoubledEvenPlace = ifDoubledLastDigitIsTwoDigitForEvenPlaces(sumOfDoubledEvenPlace, lastDigit);
        }
        return sumOfDoubledEvenPlace;
    }

    private static int ifDoubledLastDigitIsTwoDigitForEvenPlaces(int sumOfDoubledEvenPlace, int lastDigit) {
        if (lastDigit >= divisor) {
            sumOfDoubledEvenPlace += lastDigit - 9;
        } else {
            sumOfDoubledEvenPlace += lastDigit;
        }
        return sumOfDoubledEvenPlace;
    }

    public int sumOfOddPlace(long creditCardNumber) {
        int sumOfOddPlace = 0;
        int counterLimit = Long.toString(creditCardNumber).length();
        sumOfOddPlace = loopThroughCreditCardNumberForSumOfOddPlaceDigits(creditCardNumber, sumOfOddPlace, counterLimit);
        return sumOfOddPlace;
    }

    private int loopThroughCreditCardNumberForSumOfOddPlaceDigits(long creditCardNumber, int sumOfOddPlace, int counterLimit) {
        for (int counter = 1; counter <= counterLimit; counter++) {
            int lastDigit = getLastDigit(creditCardNumber);
            sumOfOddPlace = ifNumberIsAtOddPlace(sumOfOddPlace, counter, lastDigit);
            creditCardNumber = truncateLastDigit(creditCardNumber);
        }
        return sumOfOddPlace;
    }

    private static int ifNumberIsAtOddPlace(int sumOfOddPlace, int counter, int lastDigit) {
        if (counter % 2 != 0) {
            sumOfOddPlace += lastDigit;
        }
        return sumOfOddPlace;
    }

    public static int addSumOfDoubledEvenPlaceAndSumOfOddPlace(int sumOfDoubledEvenPlace, int sumOfOddPlace) {
        return sumOfDoubledEvenPlace + sumOfOddPlace;
    }

    public boolean isValidationVerificationChecker(Long creditCardNumber) {
        isValid(creditCardNumber);
        return (sumOfDoubleEvenPlace(creditCardNumber) + sumOfOddPlace(creditCardNumber)) % divisor == 0;
    }

    public String returnCardType(long creditCardNumber) {
        isValid(creditCardNumber);
        isValidationVerificationChecker(creditCardNumber);
        firstDigitAssertion(creditCardNumber);
        return cardType;
    }

    private void firstDigitAssertion(long creditCardNumber) {
        if (Long.toString(creditCardNumber).charAt(0) == '4') {
           this.cardType= "visa card";
        } else if (Long.toString(creditCardNumber).charAt(0) == '5') {
            this.cardType="master card";
        } else if (Long.toString(creditCardNumber).charAt(0) == '6') {
            this.cardType = "discover card";
        } else if (Long.toString(creditCardNumber).charAt(0) == '3'
                && Long.toString(creditCardNumber).charAt(1) == '7') {
            this.cardType = "american express card";
        }
    }

//    public Long generateCreditCardNumber() {
//        SecureRandom secureRandom = new SecureRandom();
//        Long cardNumber = 999999999999999L + secureRandom.nextLong(9999999999999999L)
//
//    }
}










