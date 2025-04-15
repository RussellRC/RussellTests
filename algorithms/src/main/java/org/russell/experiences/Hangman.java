package org.russell.experiences;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class Hangman {

  /**
   * Given a mistery word, guessed chars and alternative words, guess the next character with the highest probability of appearing,
   * if there is a tie, resolve by alphabetical order.
   *
   */
  public String guessNext(
      final String misteryWord, final List<String> guessedChars, final List<String> alternatives) {
    final Set<String> guessedLetters = new HashSet<>(guessedChars);

    final Map<String, Integer> charFreqs = new HashMap<>();
    for (final String word : alternatives) {
      if (isValidAlternative(misteryWord, guessedLetters, word)) {
        for (int i = 0; i < word.length(); i++) {
          final String letter = String.valueOf(word.charAt(i));
          if (!guessedLetters.contains(letter)) {
            // Only suggest letters that have not been guessed already
            charFreqs.compute(letter, (key, value) -> value == null ? 1 : value + 1);
          }
        }
      }
    }

    final Comparator<Integer> comparator = Integer::compareTo;
    final PriorityQueue<Integer> topFreqs = new PriorityQueue<>(comparator.reversed());
    final Map<Integer, Set<String>> freqToLetters = new HashMap<>();
    for (final Map.Entry<String, Integer> entry : charFreqs.entrySet()) {
      topFreqs.add(entry.getValue());
      freqToLetters.computeIfAbsent(entry.getValue(), k -> new TreeSet<>()).add(entry.getKey());
    }

    return freqToLetters.get(topFreqs.poll()).iterator().next();
  }

  private boolean isValidAlternative(String mysteryWord, Set<String> guessedLetters, String word) {
    // Discard the alternative if length doesn't match
    if (word.length() != mysteryWord.length()) {
      return false;
    }

    // Discard the alternative if the letters that have been guessed are not in the same position as the mysteryWord
    // e.g. Given "__g", [g, ...], [bag, hug, gun] could match "bag" and "hug" but does not match "gun"
    for (int i = 0; i < mysteryWord.length(); i++) {
      if (guessedLetters.contains(String.valueOf(mysteryWord.charAt(i)))) {
        if (mysteryWord.charAt(i) != word.charAt(i)) {
          return false;
        }
      }
    }
    return true;
  }
}
