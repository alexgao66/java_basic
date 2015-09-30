package com.alex.j2se.oop;

/**
 * 测试了继承、多态、成员变量覆盖、方法重写、对象创建时父类和子类之间构造方法的调用顺序
 * @author alex
 *
 */
public class ExtendTest {

	public static void main(String[] args) {
		Base b = new Sub();    
        System.out.println(b.x);  
	}

}


class Base {    
    int x = 10;    
    
    public Base() {    
        this.printMessage();  
        this.printMsgInBase();
        x = 20;    
    }    
    
    public void printMessage() {    
        System.out.println("Base.x = " + x);    
    }
    
    public void printMsgInBase() {
    	System.out.println("print in base,x = " + x);
    }
}  


class Sub extends Base {    
    int x = 30;    
    
    public Sub() {    
        this.printMessage();    
        x = 40;    
    }    
    
    public void printMessage() {    
        System.out.println("Sub.x = " + x);    
    }    
}    