package com.alex.soko;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapNamesTest {
    MapNames mapNamesEntry;

    @Before
    public void init(){
        mapNamesEntry = new MapNames("First map", "map2.txt");
    }

    @Test
    public void testGetMenuLabelText(){
        Assert.assertEquals("First map", mapNamesEntry.getMenuLabelText());
    }

    @Test
    public void testGetFileName(){
        Assert.assertEquals("map2.txt", mapNamesEntry.getFileName());
    }
}
