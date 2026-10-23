package algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Accepts ASCII letters and digits, including an empty string. */
public final class LetterCasePermutation {
    public static List<String> solve(String text) {
        Objects.requireNonNull(text, "text");
        for (char c : text.toCharArray()) {
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')
                    || (c >= '0' && c <= '9'))) {
                throw new IllegalArgumentException("ASCII letters and digits only");
            }
        }
        List<String> result = new ArrayList<>();
        dfs(text.toCharArray(), 0, result);
        return result;
    }

    private static void dfs(char[] chars, int index, List<String> result) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }
        char original = chars[index];
        dfs(chars, index + 1, result);
        if ((original >= 'a' && original <= 'z') || (original >= 'A' && original <= 'Z')) {
            chars[index] = (char) (original ^ 32);
            dfs(chars, index + 1, result);
            chars[index] = original;
        }
    }
}
