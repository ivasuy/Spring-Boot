package demo;

import org.springframework.stereotype.Component;
/*Annotation with bean Config*/
@Component
public class ShoppingCart {
    public void checkout(String status){
        /*These are cross-cutting concerns that we need to seperate,
        All cross-cutting concerns that we want to seperate are called Aspects
        */
//        Logging
//        Authentication and Authorization
//        Santize the Data

        /*Business Logic*/
        System.out.println("Checkout method from ShoppingCart is Called");
    }

    public int quantity(){
        return 2;
    }
}
