package NestedClasses;

/**
 * Created by ladislav on 14.4.17.
 */
public class Outer_Demo {
        private String outerstring = "This is private string in outer class";

        public class Inner_Demo {
            private String innerstring= "This is private string in inner class";

            public void print() {
                System.out.println("This is an inner class");
                System.out.println("Access to containing class variables: "+ outerstring);
            }
        }

        // Accessing he inner class from the method within
        void display_Inner() {
            Inner_Demo inner = new Inner_Demo();
            inner.print();
            System.out.println("Access to innter class private variables: "+ inner.innerstring);
            //reference na obalujici instanci
            Outer_Demo.this.greet();
        }
        void greet(){
            System.out.println("greet");
        }
}


class OuterStatic_Demo{
      private String outerstring = "This is private string in outer class";
      private InnerStatic_Demo demo;


        public static class InnerStatic_Demo {
            private String innerstring= "This is private string in inner class";

            public void print() {
                System.out.println("This is an inner class");
                //not possible to get the variable of outer class
                //System.out.println("Access to containing class variables: "+ outerstring);
            }
        }

        // Accessing he inner class from the method within
        void display_Inner() {
            InnerStatic_Demo inner = new InnerStatic_Demo();
            inner.print();
            System.out.println("Access to innter class private variables: "+ inner.innerstring);
        }

}
class My_class {

    public static void main(String args[]) {

        // --- innner class --- demo//
        // Instantiating the outer class
        Outer_Demo outer = new Outer_Demo();
        outer.display_Inner();
        System.out.println();

        //does not work - creation only within an instance of outer class
        //Outer_Demo.Inner_Demo  separate = Outer_Demo.Inner_Demo();

        // --- static class --- demo//
        //can create separate inner class
        OuterStatic_Demo outerS = new OuterStatic_Demo();
        OuterStatic_Demo.InnerStatic_Demo separate2 = new OuterStatic_Demo.InnerStatic_Demo();
        outerS.display_Inner();




    }
}