package JavaKeywords;

import java.util.Optional;

public class Practice {
    public static void main(String[] args) {
        Customer cos = new Customer(1,"Mohit");

      Optional<String> nameOptional = Optional.ofNullable(cos.getName());
//      if(nameOptional.isPresent()) {
//          System.out.println(nameOptional.get());
//      }
//        Optional<?> emptyOptional = Optional.empty();

      //  System.out.println(emptyOptional);

        System.out.println(nameOptional.orElseThrow(()->new IllegalArgumentException("Name not found")));
    }
}
