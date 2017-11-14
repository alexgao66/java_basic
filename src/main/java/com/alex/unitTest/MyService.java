package com.alex.unitTest;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by gaojun on 2017/6/6.
 */
@Service
public class MyService implements GodService {

    @Resource
    private OtherService otherService;

    @Override
    public void saveTheWorld() {
        String heroNames = getDCHeroNames();
        if (otherService.doSomething()) {
            System.out.println(String.format("%s saveTheWorld success...", heroNames));
            if (showMuscle()) {
                System.out.println("strong");
            }else {
                System.out.println("weak");
            }
        }else {
            System.out.println(String.format("%s saveTheWorld fail...", heroNames));
        }
    }

    String getMarvelHeroNames() {
        return "IronMan&SpiderMan&Hulk";
    }

    protected String getDCHeroNames() {
        return "BatMan&SuperMan&WonderWoman";
    }

    private boolean showMuscle() {
        return true;
    }
}
