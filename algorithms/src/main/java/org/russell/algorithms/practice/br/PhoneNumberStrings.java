package org.russell.algorithms.practice.br;/*
 * Click `Run` to execute the snippet below!
 */

import java.util.ArrayList;
import java.util.List;

/*


If you're familiar with television advertisements, they often post phone numbers with letters on them on the screen, 
for example: 1-800-FOO-BAR or 1-800-CAT-DOG. Of course we only have 10 digits on a dial pad so there's a mapping of 
multiple characters to a single digit that's used to deal with this (If they're not familiar with this I walk them 
through it here, usually I don't have to though). What's I'd like you to do implement this function, which given an 
input string (a phone number), returns a list of strings that this number could represent.


Telephone Keypad

   1     2     3
       [ABC] [DEF]

   4     5     6
 [GHI] [JKL] [MNO]

   7     8     9
[PQRS] [TUV] [WXYZ]

         0

Phone Number: 
22

Output:
[ AA, AB, AC, BA, BB, BC, CA, CB, CC ]


Phone Number:
234

Output
[
  ADG,
  ADH,
  ADI,
  AEG,
  AEH,
  AEI,
  AFG,
  AFH,
  AFI,
  BDG,
  BDH,
  BDI,
  BEG,
  BEH,
  BEI,
  BFG,
  BFH,
  BFI,
  CDG,
  CDH,
  CDI,
  CEG,
  CEH,
  CEI,
  CFG,
  CFH,
  CFI
]

*/


class PhoneNumberStrings {

    // Already provided
    public static List<String> getCharsForDigit(int i) {
        List<String> result = new ArrayList<>();
        if (i == 2) {
            result.add("A");
            result.add("B");
            result.add("C");
        } else if (i == 3) {
            result.add("D");
            result.add("E");
            result.add("F");
        } else if (i == 4) {
            result.add("G");
            result.add("H");
            result.add("I");
        } else if (i == 5) {
            result.add("J");
            result.add("K");
            result.add("L");
        } else if (i == 6) {
            result.add("M");
            result.add("N");
            result.add("O");
        } else if (i == 7) {
            result.add("P");
            result.add("Q");
            result.add("R");
            result.add("S");
        } else if (i == 8) {
            result.add("T");
            result.add("U");
            result.add("V");
        } else if (i == 9) {
            result.add("W");
            result.add("X");
            result.add("Y");
            result.add("Z");
        } else {
            result.add("-");
        }

        return result;


    }


    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        // Int number = 223;

        //[ AA, AB, AC, BA, BB, BC, CA, CB, CC ]
        // A -> AA, AB, AC
        // B -> BA

        int number = 22;
        String numString = String.valueOf(number);
        List<String> permutations = permutations(numString);
        System.out.println(permutations);
    }

    public static List<String> permutations(final String numberStr) {
        if (numberStr.length() == 1) {
            final int digit = Integer.parseInt(numberStr);
            return getCharsForDigit(digit);
        }

        final String remainder = numberStr.substring(1);
        final List<String> permutations = permutations(remainder);
        final List<String> result = new ArrayList<>();
        final String firstCharacter = String.valueOf(numberStr.charAt(0));
        final int digit = Integer.parseInt(firstCharacter);
        final List<String> characters = getCharsForDigit(digit);
        for (final String character : characters) {
            for (final String permutation : permutations) {
                result.add(character + permutation);
            }
        }

        return result;
    }

}
