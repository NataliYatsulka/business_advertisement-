package com.yatsulka.training;

public class TestObjectInit {

    public static void main(String[] args) {
        new One();
        new One();
        new One();
        new One();
        new One();
        new One();
        
        System.out.println(One.count);
    }
    
    
    static class One {
        static int count = 0;
        
        static {
            System.out.println("Static");
        }
        
        public One() {
            System.out.println("start constructor");
            System.out.println("B in constructor: " + b);
            System.out.println("D in constructor: " + d);
            a = "a";
            System.out.println("end constructorn");
            System.out.println("A in constructor: " + a);
            System.out.println("========================");
            
            count++;
        }
        
        String a;
        String b = "b";
        
        String c;
        String d = "ddd";
        

       {
            System.out.println("A before: " + a);
            System.out.println("B before: " + b);
            System.out.println("D before: " + d);
            System.out.println("C before: " + c);
            c = "c";
            d= "d";
            System.out.println("A after: " + a);
            System.out.println("B after: " + b);
            System.out.println("D after: " + d);
            System.out.println("C after: " + c);
            System.out.println("========================");
        }
       


    }

}
