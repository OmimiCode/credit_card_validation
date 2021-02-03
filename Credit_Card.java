package Credit_Card_Number_Validation;

public class  Credit_Card {


    public boolean isValid(long credit_card_number) {
        if ((Long.toString(credit_card_number).length() >= 13
                && Long.toString(credit_card_number).length() <= 16)
                && (Long.toString(credit_card_number).charAt(0) == '4'
                || Long.toString(credit_card_number).charAt(0) == '5'
                || Long.toString(credit_card_number).charAt(0) == '6'
                || (Long.toString(credit_card_number).charAt(0) == '3'
                && Long.toString(credit_card_number).charAt(1) == '7'))
        ) {
            return true;
        } else {
            throw new IllegalArgumentException("""
                    credit card number must have between 13 and 16 digits\s
                    and the number must start with 4 for Visa cards,
                    5 for MasterCard Credit cards,
                    6 for Discover cards\s
                    and 37 for American Express cards""");
        }
    }

    public int sumOfDoubleEvenPlace(long credit_card_number) {
        int sumOfDoubledEvenPlace = 0;
        int counterLimit = Long.toString(credit_card_number).length();
        sumOfDoubledEvenPlace = loopThroughCreditCardNumberForSumOfEvenPlaceDigits(credit_card_number, sumOfDoubledEvenPlace, counterLimit);
        return sumOfDoubledEvenPlace;
    }

    private int loopThroughCreditCardNumberForSumOfEvenPlaceDigits(long credit_card_number, int sumOfDoubledEvenPlace, int counterLimit) {
        for (int counter = 1; counter <= counterLimit; counter++) {
            int lastDigit = getLastDigit(credit_card_number);
            sumOfDoubledEvenPlace = ifNumberIsAtEvenPlace(sumOfDoubledEvenPlace, counter, lastDigit);
            credit_card_number = truncateLastDigit(credit_card_number);
        }
        return sumOfDoubledEvenPlace;
    }

    private int getLastDigit(long credit_card_number) {
        return (int) (credit_card_number % 10);
    }

    private long truncateLastDigit(long credit_card_number) {
        credit_card_number /= 10;
        return credit_card_number;
    }

    private int ifNumberIsAtEvenPlace(int sumOfDoubledEvenPlace, int counter, int lastDigit) {
        if (counter % 2 == 0) {
           lastDigit *=2;
            sumOfDoubledEvenPlace = ifDoubledLastDigitIsTwoDigitForEvenPlaces(sumOfDoubledEvenPlace, lastDigit);
        }
        return sumOfDoubledEvenPlace;
    }

    private int ifDoubledLastDigitIsTwoDigitForEvenPlaces(int sumOfDoubledEvenPlace, int lastDigit) {
        if (lastDigit >= 10) {
            sumOfDoubledEvenPlace += lastDigit - 9;
        } else {
            sumOfDoubledEvenPlace += lastDigit;
        }
        return sumOfDoubledEvenPlace;
    }


    public int sumOfOddPlace(long credit_card_number) {
        int sumOfOddPlace = 0;
        int counterLimit = Long.toString(credit_card_number).length();
        sumOfOddPlace = loopThroughCreditCardNumberForSumOfOddPlaceDigits(credit_card_number, sumOfOddPlace, counterLimit);
        return sumOfOddPlace;
    }

    private int loopThroughCreditCardNumberForSumOfOddPlaceDigits(long credit_card_number, int sumOfOddPlace, int counterLimit) {
        for (int counter = 1; counter <= counterLimit; counter++) {
            int lastDigit = getLastDigit(credit_card_number);
            sumOfOddPlace = ifNumberIsAtOddPlace(sumOfOddPlace, counter, lastDigit);
            credit_card_number = truncateLastDigit(credit_card_number);
        }
        return sumOfOddPlace;
    }


    private int ifNumberIsAtOddPlace(int sumOfOddPlace, int counter, int lastDigit) {
        if (counter % 2 != 0) {
            sumOfOddPlace += lastDigit;
        }
        return sumOfOddPlace;
    }


    public int addSumOfDoubledEvenPlaceAndSumOfOddPlace(int sumOfDoubledEvenPlace, int sumOfOddPlace) {
        return sumOfDoubledEvenPlace + sumOfOddPlace;
    }
}










