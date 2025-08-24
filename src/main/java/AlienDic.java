import java.util.*;

class AlienDic {

    Map<Character, List<Character>> ad = new HashMap<>();
    Map<Character,Boolean> vis = new HashMap<>();
    String res="";
    public String foreignDictionary(String[] words) {
        int len = words.length;

        for(int i = 0;i < len; i++){
            for(char w: words[i].toCharArray()){
                ad.computeIfAbsent(w, k -> new ArrayList<>());
            }
        }

        for(int i = 0; i < len-1; i++) {
            String word1 = words[i];
            String word2 = words[i+1];

            int shortlen = Math.min(word1.length(), word2.length());

            for(int j = 0; j < shortlen; j++) {
                if(word1.charAt(j) != word2.charAt(j)) {
                    ad.computeIfAbsent(word1.charAt(j), k -> new ArrayList<>()).add(word2.charAt(j));
                    break;
                }
            }
        }

        for(char c:ad.keySet()){
            if(dfs(c)){
                return "";
            }
        }

        if(res == null)
            return "";
        StringBuilder sb = new StringBuilder();
        for(char s: res.toCharArray())
            sb.append(s);
        return sb.reverse().toString();
    }

    public boolean dfs(char c){

        if(vis.containsKey(c))
            return vis.get(c);

        vis.put(Character.valueOf(c),true);
        for(char neig: ad.getOrDefault(c, Collections.emptyList())){
            if(dfs(neig)){
                return true;
            }
        }
        vis.put(Character.valueOf(c),false);
        res += c;
        return false;
    }

    public static void main(String[] args) {
        AlienDic ad  = new AlienDic();
        String words[] = new String[]{"abc","bcd","cde"};
        System.out.println(ad.foreignDictionary(words));
    }
}
/*
* Test case 1
* words=["abc","bcd","cde"]
* expected output abcde
*
* Test case 2
* ["wrtkj","wrt"]
*
* Test case 3
* String words[] = new String[]{"hrn","hrf","er","enn","rfnn"};
* */

