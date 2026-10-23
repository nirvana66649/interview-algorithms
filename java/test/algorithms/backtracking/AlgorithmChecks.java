package algorithms.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Independent small-input enumeration checks, no external test framework. */
public final class AlgorithmChecks {
    private static int cases;

    public static void main(String[] args) {
        for (int n = 0; n <= 10; n++) {
            int[] nums = input(n);
            int[] before = nums.clone();
            Set<String> expected = new HashSet<>();
            for (int mask = 0; mask < (1 << n); mask++) {
                List<Integer> one = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    if ((mask & (1 << i)) != 0) one.add(nums[i]);
                }
                expected.add(one.toString());
            }
            check(expected, encode(Subsets.solve(nums)), "subsets n=" + n);
            require(Arrays.equals(nums, before), "subsets modified input");
        }
        for (int n = 0; n <= 7; n++) {
            int[] nums = input(n);
            int[] before = nums.clone();
            Set<String> expected = new HashSet<>();
            int[] order = nums.clone();
            do {
                expected.add(Arrays.toString(order));
            } while (nextPermutation(order));
            check(expected, encode(Permutations.solve(nums)), "permutations n=" + n);
            require(Arrays.equals(nums, before), "permutations modified input");
        }
        char[] alphabet = {'a', 'B', '0', '9'};
        for (int n = 0; n <= 5; n++) {
            int count = 1 << (2 * n);
            for (int id = 0; id < count; id++) {
                StringBuilder text = new StringBuilder();
                int digits = id;
                for (int i = 0; i < n; i++) {
                    text.append(alphabet[digits % alphabet.length]);
                    digits /= alphabet.length;
                }
                String value = text.toString();
                List<Integer> letters = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    if (Character.isLetter(value.charAt(i))) letters.add(i);
                }
                Set<String> expected = new HashSet<>();
                for (int mask = 0; mask < (1 << letters.size()); mask++) {
                    char[] chars = value.toCharArray();
                    for (int i = 0; i < letters.size(); i++) {
                        int at = letters.get(i);
                        chars[at] = (mask & (1 << i)) == 0
                                ? Character.toLowerCase(chars[at])
                                : Character.toUpperCase(chars[at]);
                    }
                    expected.add(new String(chars));
                }
                check(expected, LetterCasePermutation.solve(value), "letter case " + value);
            }
        }
        check(Set.of("[]", "[7]"), encode(Subsets.solve(new int[]{7})), "repeat subsets");
        check(Set.of("[7]"), encode(Permutations.solve(new int[]{7})), "repeat permutations");
        check(Set.of("a", "A"), LetterCasePermutation.solve("a"), "repeat letter case");
        boolean rejected = false;
        try {
            LetterCasePermutation.solve("a!");
        } catch (IllegalArgumentException expected) {
            rejected = true;
        }
        require(rejected, "non-alphanumeric input accepted");
        System.out.println("Passed " + cases + " enumeration cases and input-contract checks.");
    }

    private static int[] input(int n) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = i * 2 - 5;
        return nums;
    }

    private static List<String> encode(List<List<Integer>> values) {
        List<String> result = new ArrayList<>();
        for (List<Integer> value : values) result.add(value.toString());
        return result;
    }

    private static void check(Set<String> expected, List<String> actual, String name) {
        require(expected.equals(new HashSet<>(actual)), "wrong results: " + name);
        require(expected.size() == actual.size(), "duplicate results: " + name);
        cases++;
    }

    private static void require(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    private static boolean nextPermutation(int[] values) {
        int i = values.length - 2;
        while (i >= 0 && values[i] >= values[i + 1]) i--;
        if (i < 0) return false;
        int j = values.length - 1;
        while (values[j] <= values[i]) j--;
        swap(values, i, j);
        for (int l = i + 1, r = values.length - 1; l < r; l++, r--) swap(values, l, r);
        return true;
    }

    private static void swap(int[] values, int i, int j) {
        int temporary = values[i];
        values[i] = values[j];
        values[j] = temporary;
    }
}
