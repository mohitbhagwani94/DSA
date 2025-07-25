public class ArielStringSolver {
    public static int arielString(String str){
        int len = str.length();
        char minChar = 'z';
        int minpos = -1;
        char maxChar = 'a';
        int maxpos = -1;
        int pos =-1;

        for(char ch : str.toCharArray()) {
            pos++;
            if(ch < minChar ) {
                minChar = ch;
                minpos = pos;
            }

            if(ch > maxChar) {
                maxChar = ch;
                maxpos = pos;
            }
        }

        int swaps = (minpos-0)+(len-maxpos-1);

        if(minpos>maxpos)
            swaps--;

        return swaps;
    }

    public static void main(String[] args){
        String str = "bciafghe";
        System.out.println(arielString(str));
    }
}
