package com.alex.j2se.exceptionProcess;

/**
 * Created by gaojun on 2017/1/24.
 */
public class ThrowsTest {

    public static void main(String[] args) {
        try {
            throwParent(1);
        } catch (Exception e) {
            if (e instanceof ChildrenException) {
                System.out.println("success");
            }
        }
    }


    public static void throwParent(int i) throws ParentException{
        if (i == 0){
            throw new ParentException();
        }else {
            throw new ChildrenException();
        }
    }

    public static void throwChildren() throws ChildrenException {

    }
}

class ParentException extends Exception {

}

class ChildrenException extends  ParentException {

}
