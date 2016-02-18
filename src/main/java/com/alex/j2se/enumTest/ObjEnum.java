package com.alex.j2se.enumTest;


/**
 * Created by gaojun on 16/2/18.
 */
public enum ObjEnum {

    ObjA(new AbastractObj() {
        @Override
        void print() {
            System.out.println("ObjA...");
        }
    }),

    ObjB(new AbastractObj() {
        @Override
        void print() {
            System.out.println("ObjB...");
        }
    });

    private AbastractObj obj;

    ObjEnum(AbastractObj obj){
        this.obj = obj;
    }

    public AbastractObj getObj() {
        return obj;
    }
}
