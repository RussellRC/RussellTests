package russell.tests.algorithms.unsorted;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class StringsArrays {

    public static void main(String[] args) {
        
        testCountAndSay();
    }
    
    private static void testCountAndSay() {
        countAndSay(1, 5);
        countAndSay(2, 5);
    }
    
    private static void countAndSay(int number, int sequence) {
        final List<String> sayList = new ArrayList<>();
        sayList.add(String.valueOf(number));
        
        for (int i = 1; i <= sequence; i++) {
            sayList.add(say(sayList.get(i-1)));
        }
        
        System.out.println(sayList.stream().collect(Collectors.joining(",")));
    }

    private static String say(final String str) {
        final StringBuilder result = new StringBuilder();
        
        char character = str.charAt(0);
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != character) {
                result.append(count).append(character);
                count = 0;
                character = str.charAt(i);
            }
            count++;
        }
        result.append(count).append(character);
        
        return result.toString();
    }

    /**
     * 
     */
    public static void testMergeSortedArrays() {
        int[] b = {0, 10, 15, 16, 17, 20, 25, 26, 27, 28, 29};
        int[] a = new int[8 + b.length];
        a[0] = 1;
        a[1] = 5;
        a[2] = 11;
        a[3] = 13;
        a[4] = 18;
        a[5] = 21;
        a[6] = 22;
        a[7] = 30;
        
        
        int indexA = 7;
        int indexB = b.length - 1;
        int i = a.length-1;
        while (i >= 0) {
            if (a[indexA] > b[indexB]) {
                a[i--] = a[indexA--];
            } else {
                a[i--] = b[indexB--];
            }
            if (indexA < 0 || indexB < 0) {
                break;
            }
        }
        if (indexA < 0) {
            while (indexB >= 0) {
                a[i--] = b[indexB--];
            }
        }
        if (indexB < 0) {
            while (indexA >= 0) {
                a[i--] = a[indexA--];
            }
        }
        
        
        System.out.println(Arrays.toString(a));
    }
    
    /**
     * 
     * @param array
     * @param steps
     * @return
     */
    public static void testRotateArray() {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(rotateArray(array, 17)));
    }
    
    public static int[] rotateArray(int[] array, int steps) {
        if (array == null) {
            return null;
        }
        if (steps == 0) {
            return array;
        }
        if (steps > array.length) {
            steps = steps % array.length;
        }
        
        int[] result = new int[array.length];
        int index = 0;
        int pivot = array.length - steps;
        for (int i = pivot; i < array.length; i++) {
            result[index++] = array[i];
        }
        for (int i = 0; i < pivot; i++) {
            result[index++] = array[i];
        }
        
        return result;
    }
    
    
    public static void testSearchWordMatrix() {
        final char[][] matrix = new char[4][4];
        matrix[0] = new char[] { 'G', 'A', 'B', 'C' };
        matrix[1] = new char[] { 'O', 'O', 'E', 'F' };
        matrix[2] = new char[] { 'O', 'H', 'O', 'I' };
        matrix[3] = new char[] { 'J', 'K', 'L', 'D' };
        
        
        System.out.println(search("JOOG", matrix)); //N
        System.out.println(search("BEOL", matrix)); //S
        System.out.println(search("AB", matrix));   //E
        System.out.println(search("FE", matrix));   //W
        System.out.println(search("HEC", matrix));  //NE
        System.out.println(search("DOOG", matrix)); //NW
        System.out.println(search("GOOD", matrix)); //SE
        System.out.println(search("FOK", matrix));  //SW        
        System.out.println(search("HO", matrix));
    }
    
    /**
     * 
     * @param word
     * @param matrix
     * @return
     */
    public static boolean search(final String word, char[][] matrix) {
        
        final char firstLetter = word.charAt(0);
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] == firstLetter) {
                    final Set<String> words = readInAllDirections(word.length(), x, y, matrix);
                    if (words.contains(word)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    enum Direction {
        NORTH, SOUTH,
        EAST, WEST,
        NORTH_EAST, NORTH_WEST,
        SOUTH_EAST, SOUTH_WEST
    }

    private static Set<String> readInAllDirections(final int length, final int x, final int y, final char[][] matrix) {
        final Set<String> words = new LinkedHashSet<>();
        for (final Direction direction : Direction.values()) {
            words.add(readWord(length, x, y, matrix, direction));
        }
        return words;
    }

    private static String readWord(final int length, final int xBegin, final int yBegin, final char[][] matrix, final Direction direction) {
        final int xEnd = getXEnd(xBegin, length, direction);
        final int yEnd = getYEnd(yBegin, length, direction);
        int x;
        int y;
        
        final StringBuilder matrixWord = new StringBuilder();
        
        if (direction == Direction.SOUTH) {
            if (yEnd > matrix.length-1) {
                return null;
            }
            for (y = yBegin; y <= yEnd; y++) {
                matrixWord.append(matrix[y][xBegin]);
            }
        }
        if (direction == Direction.NORTH) {
            if (yEnd < 0) {
                return null;
            }
            for (y = yBegin; y >= yEnd; y--) {
                matrixWord.append(matrix[y][xBegin]);
            }
        }
        if (direction == Direction.EAST) {
            if (xEnd > matrix[yBegin].length-1) {
                return null;
            }
            for (x = xBegin; x <= xEnd; x++) {
                matrixWord.append(matrix[yBegin][x]);
            }
        }
        if (direction == Direction.WEST) {
            if (xEnd < 0) {
                return null;
            }
            for (x = xBegin; x >= xEnd; x--) {
                matrixWord.append(matrix[yBegin][x]);
            }
        }
        if (direction == Direction.SOUTH_EAST) {
            if (yEnd > matrix.length-1 || xEnd > matrix[yBegin].length-1) {
                return null;
            }
            x = xBegin;
            y = yBegin;
            while (y <= yEnd && x <= xEnd) {
                matrixWord.append(matrix[y][x]);
                y++;
                x++;
            }
        }
        if (direction == Direction.SOUTH_WEST) {
            if (yEnd > matrix.length-1 || xEnd < 0) {
                return null;
            }
            x = xBegin;
            y = yBegin;
            while (y <= yEnd && x >= xEnd) {
                matrixWord.append(matrix[y][x]);
                y++;
                x--;
            }
        }
        if (direction == Direction.NORTH_EAST) {
            if (yEnd < 0 || xEnd > matrix[yBegin].length-1) {
                return null;
            }
            x = xBegin;
            y = yBegin;
            while (y >= yEnd && x <= xEnd) {
                matrixWord.append(matrix[y][x]);
                y--;
                x++;
            }
        }
        if (direction == Direction.NORTH_WEST) {
            if (yEnd < 0 || xEnd < 0) {
                return null;
            }
            x = xBegin;
            y = yBegin;
            while (y >= yEnd && x >= xEnd) {
                matrixWord.append(matrix[y][x]);
                y--;
                x--;
            }
        }

        return matrixWord.toString();
    }

    private static int getYEnd(final int y, final int length, final Direction direction) {
        if (direction == Direction.SOUTH || direction == Direction.SOUTH_EAST || direction == Direction.SOUTH_WEST) {
            // y0 + length + ? = y1
            return y + length - 1;
        }
        if (direction == Direction.NORTH || direction == Direction.NORTH_EAST || direction == Direction.NORTH_WEST) {
            // y0 - length + ? = y1
            return y - length + 1; 
        }
        
        // direction == Direction.EAST || direction == Direction.WEST)
        return y;
    }

    private static int getXEnd(final int x, final int length, final Direction direction) {
        if (direction == Direction.EAST || direction == Direction.NORTH_EAST || direction == Direction.SOUTH_EAST) {
            // x0 + length + ? = x1
            return x + length - 1;
        }
        if (direction == Direction.WEST || direction == Direction.NORTH_WEST || direction == Direction.SOUTH_WEST) {
            // x0 - length + ? = x1
            return x - length + 1;
        }
        
        // direction == Direction.NORTH || direction == Direction.SOUTH)
        return x;
    }


    public static String compressStringJackie(String s) {

        if (s == null || s.length() < 2) {
            return s;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            int j = i + 1;
            int count = 1;
            while (j < s.length() && s.charAt(i) == s.charAt(j)) {
                count++;
                j++;
            }
            sb.append(s.charAt(i) + "" + count);
            i = i + (count - 1);
        }
        return sb.length() >= s.length() ? s : sb.toString();
    }

    /**
     * 
     * @param str
     * @return
     */
    public static String compressString(final String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        final StringBuilder result = new StringBuilder();

        int i = 0;
        int count = 0;
        char current = str.charAt(i);
        while (i < str.length()) {

            if (str.charAt(i) != current) {
                result.append(current);
                result.append(count);
                current = str.charAt(i);
                count = 0;
            }

            i++;
            count++;
        }

        result.append(current);
        result.append(count);

        return str.length() < result.length() ? str : result.toString();
    }

    /**
     * 
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isARotationOfString(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        int index = s2.indexOf(s1.charAt(0));
        StringBuffer sb = new StringBuffer();
        if (index >= 0) {
            sb.append(s2.substring(index, s2.length()));
            sb.append(s2.substring(0, index - 1));
        }
        return s1.contains(sb.toString());

    }

}
