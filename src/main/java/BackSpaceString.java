import java.util.Stack;

class BackSpaceString {
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> s1 = new Stack<>();
        Stack<Character> s2 = new Stack<>();

        for(char c: s.toCharArray()){
            if(c == '#'){
                if(!s1.isEmpty())
                    s1.pop();
            }else{
                s1.push(c);
            }
        }

        StringBuilder finalS = new StringBuilder();
        while(!s1.isEmpty()){
            finalS.append(s1.pop());
        }


        for(char c: t.toCharArray()){
            if(c == '#'){
                if(!s2.isEmpty())
                    s2.pop();
            }else{
                s2.push(c);
            }
        }

        StringBuilder finalT = new StringBuilder();
        while(!s2.isEmpty()){
            finalT.append(s2.pop());
        }

        System.out.println("finalT : "+finalT);
        System.out.println("finalS : "+ finalS);
        return finalS.toString().equals(finalT.toString());

    }
}