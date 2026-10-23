package algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Subsets of distinct integers. Does not modify the input. */
public final class Subsets {
    public static List<List<Integer>> solve(int[] nums) {
        Objects.requireNonNull(nums, "nums");
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void dfs(int[] nums, int start, List<Integer> path,
                            List<List<Integer>> result) {
        result.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }
}
