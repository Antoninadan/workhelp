package ua.i.mail100.test_inheritance;

public class TestInheritanceApp {
    public static void main(String[] args) {
        ParentClass parentClass1;
        ChildClass childClass = new ChildClass();
        parentClass1=childClass;
        parentClass1.method1();

        if (parentClass1 instanceof ChildClass) System.out.println("parentClass1 has type ChildClass");;
    }

}
