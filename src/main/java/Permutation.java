import java.util.ArrayList;
import java.util.List;

class Permutation {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        backtracking(nums, new ArrayList<>());
        return res;
    }

    public void backtracking(int nums[], List<Integer> ans) {
        //System.out.println("ans : "+ans);
        if(ans.size() >= nums.length){
            res.add(new ArrayList<>(ans));
            return;
        }
        //System.out.println("ind :"+ ind);
        for(int i = 0; i < nums.length; i++) {
            if(ans.contains(nums[i])){
                continue;
            }
            ans.add(nums[i]);
            backtracking(nums, ans);
            ans.remove(ans.size()-1);
        }
    }
    public static void main(String arg[]) {
        int arr[] = new int[]{1,2,3};
        Permutation p = new Permutation();
        System.out.println(p.permute(arr));

    }
}
