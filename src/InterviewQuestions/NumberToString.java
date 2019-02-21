package InterviewQuestions;

public class NumberToString {
    public static void main(String[] args) {
        System.out.println(intToString(1));
        System.out.println(intToString(29));
        System.out.println(intToString(339));
        System.out.println(intToString(2339));
    }

    public static String intToString(int number) {
        String numberString = "" + number;
        String translated = "";

        if(numberString.length() == 4) {
            if(numberString.charAt(0) == '2') {
                translated += "twothousand";
            }
            numberString = numberString.substring(1);
        }

        if(numberString.length() == 3) {
            if(numberString.charAt(0) == '3') {
                translated += "threehundred";
            } else if(numberString.charAt(0) == '4') {
                translated += "fourhundred";
            }
            numberString = numberString.substring(1);
        }

        if(numberString.length() == 2) {
            if(numberString.charAt(0) == '2') {
                translated += "twenty";
            } else if(numberString.charAt(0) == '3') {
                translated += "thirty";
            } else if(numberString.charAt(0) == '4') {
                translated += "fourty";
            } else if(numberString.charAt(0) == '5') {
                translated += "fifty";
            }
            numberString = numberString.substring(1);
        }


        if(numberString.equals("0")) {
        } else if(numberString.equals("1")) {
            translated += "one";
        } else if(numberString.equals("2")) {
            translated += "two";
        } else if(numberString.equals("3")) {
            translated += "three";
        } else if(numberString.equals("4")) {
            translated += "four";
        } else if(numberString.equals("9")) {
            translated += "nine";
        } else if(numberString.equals("19")) {
            translated += "nineteen";
        }

        return translated;
    }

    public static String NumberToText(int n) {
        return "";
    }
}
