import java.util.Arrays;

public class MakeSquare {
    public boolean makesquare(int[] matchsticks) {
        if (matchsticks == null || matchsticks.length < 4) return false;

        int sum = 0;
        for (int stick : matchsticks) {
            sum += stick;
        }

        if (sum % 4 != 0) return false;
        int sideLength = sum / 4;

        Arrays.sort(matchsticks);
        // Reverse to descending order (helps pruning)
        for (int i = 0; i < matchsticks.length / 2; i++) {
            int temp = matchsticks[i];
            matchsticks[i] = matchsticks[matchsticks.length - 1 - i];
            matchsticks[matchsticks.length - 1 - i] = temp;
        }

        int[] sides = new int[4];
        return backtrack(matchsticks, 0, sides, sideLength);
    }

    private boolean backtrack(int[] matchsticks, int index, int[] sides, int target) {
        if (index == matchsticks.length) {
            // Check all sides equal
            return sides[0] == target && sides[1] == target &&
                    sides[2] == target && sides[3] == target;
        }

        for (int i = 0; i < 4; i++) {
            if (sides[i] + matchsticks[index] > target) continue;

            sides[i] += matchsticks[index];
            if (backtrack(matchsticks, index + 1, sides, target)) return true;
            sides[i] -= matchsticks[index]; // backtrack
        }

        return false;
    }

    // Example usage
    public static void main(String[] args) {
        MakeSquare sol = new MakeSquare();
        int[] matchsticks1 = {1,1,2,2,2};
        int[] matchsticks2 = {3,3,3,3,4};
        System.out.println(sol.makesquare(matchsticks1)); // true
        System.out.println(sol.makesquare(matchsticks2)); // false
    }
}

