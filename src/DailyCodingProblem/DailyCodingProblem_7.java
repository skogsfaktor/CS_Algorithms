/*
Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.

For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.

You can assume that the messages are decodable. For example, '001' is not allowed.
*/
package DailyCodingProblem;
import java.util.*;

public class DailyCodingProblem_7 {
    public static void main(String args[]) {
        String input = "321";

        DecodeCounter decodeCounter = new DecodeCounter(input);
        System.out.println("Count: " + decodeCounter.getCount());

        SuckyDick suckyDick = new SuckyDick(input);
        System.out.println("Sucky Count: " + suckyDick.getCount());
    }
}

//Codex
class DecodeCounter {

    private int count;

    public DecodeCounter(String message) {
        countSubstrings("", message);
    }

    public int getCount() {
        return count;
    }

    private void countSubstrings(String left, String right) {
        int val = Integer.valueOf(right);
        System.out.println(val);
        if (val < 26) {
            count++;
        }
        if (right.length() > 1) {
            for (int i = 1; i < right.length(); i++) {
                countSubstrings(left + right.substring(0, i), right.substring(i));
            }
        }
    }
}

//Skogsfaktor
class SuckyDick {
    private int count;

    public SuckyDick(String input) {
        char[] alphabet = new char[] {'a','b','c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for(int i = 1; i <= input.length(); i++) {
            possibleStrings(i, alphabet, "", input);
        }
    }

    public int getCount() {
        return count;
    }

    private void possibleStrings(int maxLength, char[] alphabet, String curr, String input) {
        // If the current string has reached it's maximum length
        if(curr.length() == maxLength) {
            //System.out.println(curr);
            StringBuilder sc = new StringBuilder();
            for(int i = 0; i < curr.length(); i++) {
                sc.append((int)(curr.charAt(i)-'a'+1));
            }
            //System.out.println(curr + ": " + sc.toString());
            if(sc.toString().equals(input)) {
                //System.out.println("Match");
                count++;
            }

            // Else add each letter from the alphabet to new strings and process these new strings again
        } else {
            for(int i = 0; i < alphabet.length; i++) {
                String oldCurr = curr;
                curr += alphabet[i];
                possibleStrings(maxLength,alphabet,curr, input);
                curr = oldCurr;
            }
        }
    }
}
