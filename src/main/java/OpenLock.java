import java.util.*;

class OpenLock {

    public int openLock(String[] deadends, String target) {
        String baseCase = "0000";
        Set<String> vis = new HashSet<>();
        for(String deadend:deadends)
            vis.add(deadend);

        if(vis.contains(baseCase)) return -1;

        Queue<String> queue = new LinkedList<>();
        queue.offer(baseCase);
        vis.add(baseCase);
        int step = 0;
        while(!queue.isEmpty()){
            int qsize = queue.size();
            for(int i = 0; i < qsize; i++){
                String curr = queue.poll();
                if(curr.equals(target)) return step;
                for(String str: getNeigh(curr)){
                    if(!vis.contains(str)){
                        queue.offer(str);
                        vis.add(str);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public List<String> getNeigh(String str){
        List<String> ans = new ArrayList<>();
        char arr[] = str.toCharArray();
        for(int i=0;i<4;i++){
            char c = arr[i];
            arr[i] = c ==  '9'? '0' : (char)(c + 1);
            ans.add(new String(arr));
            arr[i] = c == '0' ?  '9' : (char)(c - 1);
            ans.add(new String(arr));
            arr[i] = c;
        }
        return ans;
    }

    public static void main(String[] args) {
        OpenLock solver = new OpenLock();

        System.out.println(solver.openLock(
                new String[]{"0201","0101","0102","1212","2002"}, "0202")); // 6

        System.out.println(solver.openLock(
                new String[]{"8888"}, "0009")); // 1

        System.out.println(solver.openLock(
                new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"}, "8888")); // -1
    }
}
