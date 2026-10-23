package algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Permutations of distinct integers. Does not modify the input. */
public final class Permutations {
    public static List<List<Integer>> solve(int[] nums) {
        Objects.requireNonNull(nums, "nums");
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    private static void dfs(int[] nums, boolean[] used, List<Integer> path,
                            List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            path.add(nums[i]);
            dfs(nums, used, path, result);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
