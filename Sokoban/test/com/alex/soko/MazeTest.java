package com.alex.soko;

import org.junit.*;

public class MazeTest {
    Maze maze;

    @Before
    public void init(){
        maze = new Maze(2, 10, 12, 3);
    }

    @Test
    public void testGetHeight(){
        Assert.assertEquals(10, maze.getHeight());
    }

    @Test
    public void testGetWidth(){
        Assert.assertEquals(12, maze.getWidth());
    }

    @Test
    public void testGetMapNumber(){
        Assert.assertEquals(2, maze.getMapNumber());
    }

    @Test
    public void testIsComplete() {
        Assert.assertEquals(false, maze.isComplete());
    }
}
