package JPractice;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String arg[]){

        List<Integer> num = Arrays.asList(200,300,400,500,600,700,800,400,500,601,702);
        System.out.println(num.stream().filter(n -> n>600).count());

    }
}
