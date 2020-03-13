package com.alex.soko;

import org.junit.*;


public class MenuPaneTest {
    MenuPane menuPane;

    @Before
    public void init(){
        menuPane = new MenuPane();
    }

    @Test
    public void testStartable(){
        menuPane.setStartable(true);
        Assert.assertEquals(true, menuPane.isStartable());
    }

    @Test
    public void testResumableByButton(){
        menuPane.setResumableByButton(true);
        Assert.assertEquals(true, menuPane.isResumableByButton());
        menuPane.setResumableByButton(false);
        Assert.assertEquals(false, menuPane.isResumableByButton());
    }

    @Test
    public void testUserLoeaded(){
        menuPane.setUserLoaded(false);
        Assert.assertEquals(false, menuPane.isUserLoaded());
        menuPane.setUserLoaded(true);
        Assert.assertEquals(true, menuPane.isUserLoaded());
    }

    @Test
    public void testAcquiredMapNumber(){
        menuPane.setAcquiredMapNumber(2);
        Assert.assertEquals("map2.txt", menuPane.getFileNameOfAcquiredMap());
    }

}
