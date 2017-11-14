package com.alex.unitTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by gaojun on 2017/6/6.
 */
@RunWith(MockitoJUnitRunner.class)
public class MyServiceTest {

    @InjectMocks
    private MyService godService = new MyService();

    @Mock
    private OtherService otherService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveTheWorldSuccess() {
        // stub
        Mockito.when(otherService.doSomething()).thenReturn(true);
        godService.saveTheWorld();
    }

    @Test
    public void testSaveTheWorldFail() {
        Mockito.when(otherService.doSomething()).thenReturn(false);
        godService.saveTheWorld();
    }

    @Test
    public void testGetMarvelHeroNames() {
        Assert.assertThat(godService.getMarvelHeroNames(), containsString("Hulk"));
        Assert.assertEquals("IronMan&SpiderMan&Hulk", godService.getMarvelHeroNames());
    }

    @Test
    public void testGetDCHeroNames() {
        Assert.assertEquals("BatMan&SuperMan&WonderWoman", godService.getDCHeroNames());
    }
}
