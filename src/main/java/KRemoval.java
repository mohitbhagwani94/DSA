import java.util.*;

public class KRemoval {
    class CharCount{
        char ch;
        int count;

        CharCount(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }


    public String removeDuplicates(String s, int k) {
        Deque<CharCount> stack = new ArrayDeque<>();

        for(char letter: s.toCharArray()){
            if(!stack.isEmpty() && stack.peek().ch == letter){
                stack.peek().count++;
                if(stack.peek().count == k){
                    stack.pop();
                }
            }else{
                stack.push(new CharCount(letter,1));
            }

        }

        StringBuilder sb = new StringBuilder();
        for(CharCount cp: stack){
            sb.append(String.valueOf(cp.ch).repeat(cp.count));
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        KRemoval sol = new KRemoval();

        // Test case 1
        String s1 = "deeedbbcccbdaa";
        int k1 = 3;
        System.out.println("Input: " + s1 + ", k = " + k1);
        System.out.println("Output: " + sol.removeDuplicates(s1, k1)); // Expected "aa"

        // Test case 2
        String s2 = "pbbcggttciiippooaais";
        int k2 = 2;
        System.out.println("Input: " + s2 + ", k = " + k2);
        System.out.println("Output: " + sol.removeDuplicates(s2, k2)); // Expected "ps"

        // Test case 3 (edge case - no duplicates)
        String s3 = "abcdef";
        int k3 = 3;
        System.out.println("Input: " + s3 + ", k = " + k3);
        System.out.println("Output: " + sol.removeDuplicates(s3, k3)); // Expected "abcdef"

        // Test case 4 (all duplicates removed)
        String s4 = "aaaa";
        int k4 = 2;
        System.out.println("Input: " + s4 + ", k = " + k4);
        System.out.println("Output: " + sol.removeDuplicates(s4, k4)); // Expected ""
    }
}
