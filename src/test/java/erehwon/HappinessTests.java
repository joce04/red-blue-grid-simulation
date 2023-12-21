package erehwon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class HappinessTests {
    /**
     * everyone is happy
     */
    @Test
    public void allHappy() {
        RedBlueGrid rbGrid = new RedBlueGrid(7, 1, 0, 1, 0.1);
        assertTrue(rbGrid.isHappy(6, 6));
        assertTrue(rbGrid.isHappy(1, 1));
        assertTrue(rbGrid.isHappy(0, 0));
        assertTrue(rbGrid.isHappy(6, 0));
    }

    /**
     * no one is happy due to a really high happiness threshold
     */
    @Test
    public void zeroHappy() {
        RedBlueGrid rbGrid = new RedBlueGrid(7, 1, 0.8, 0.2, 1);
        //all of them are unhappy
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                if(rbGrid.getColor(i,j) == Color.RED) {
                    assertFalse(rbGrid.isHappy(i, j));
                }
            }
        }
    }

    /**
     * happiness threshold of 0 so everyone should be happy
     */
    @Test
    public void lonersHappy() {
        RedBlueGrid rbGrid = new RedBlueGrid(7, 1, 0.8, 0.2, 0);
        //all of them are happy
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                if(rbGrid.getColor(i,j) == Color.RED) {
                    assertTrue(rbGrid.isHappy(i, j));
                }
            }
        }
    }

    /**
     * huge neighbourhood
     */
    @Test
    public void bigNeighbourhood() {
        RedBlueGrid rbGrid = new RedBlueGrid(7, 10, 0, 1, 1);
        //all of them are unhappy
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                if(rbGrid.getColor(i,j) == Color.RED) {
                    assertTrue(rbGrid.isHappy(i, j));
                }
            }
        }
    }

    @Test
    public void specificNeighbourhood() {
        //initialize a blank grid
        RedBlueGrid rbGrid = new RedBlueGrid(4, 1, 1, 0, 0.3);

        rbGrid.setColor(0, 1, Color.RED);
        rbGrid.setColor(0,3, Color.RED);
        rbGrid.setColor(1,0, Color.BLUE);
        rbGrid.setColor(1, 1, Color.RED);
        rbGrid.setColor(1,2, Color.BLUE);
        rbGrid.setColor(1, 3, Color.BLUE);
        rbGrid.setColor(2, 1, Color.BLUE);
        rbGrid.setColor(2,2, Color.BLUE);
        rbGrid.setColor(3,0, Color.RED);
        rbGrid.setColor(3,1, Color.RED);

        assertEquals(0.2, rbGrid.happyNeighbours(0,1, Color.RED), 0.005);
        assertEquals(0.0, rbGrid.happyNeighbours(0,3, Color.RED), 0.005);
        assertEquals(0.2, rbGrid.happyNeighbours(1, 0, Color.BLUE), 0.005);
        assertEquals(0.125, rbGrid.happyNeighbours(1,1, Color.RED), 0.005);
        assertEquals(0.375, rbGrid.happyNeighbours(1,2, Color.BLUE), 0.005);
        assertEquals(0.4, rbGrid.happyNeighbours(1,3, Color.BLUE), 0.005);
        assertEquals(0.375, rbGrid.happyNeighbours(2,1, Color.BLUE), 0.005);
        assertEquals(0.375, rbGrid.happyNeighbours(2,2, Color.BLUE), 0.005);
        assertEquals((double) 1/3, rbGrid.happyNeighbours(3,0, Color.RED), 0.005);
        assertEquals(0.2, rbGrid.happyNeighbours(3,1, Color.RED), 0.005);
    }

    @Test
    public void specificBigNeighbourhood() {
        //initialize a blank grid
        RedBlueGrid rbGrid = new RedBlueGrid(4, 2, 1, 0, 0.3);

        rbGrid.setColor(0, 1, Color.RED);
        rbGrid.setColor(0,3, Color.RED);
        rbGrid.setColor(1,0, Color.BLUE);
        rbGrid.setColor(1, 1, Color.RED);
        rbGrid.setColor(1,2, Color.BLUE);
        rbGrid.setColor(1, 3, Color.BLUE);
        rbGrid.setColor(2, 1, Color.BLUE);
        rbGrid.setColor(2,2, Color.BLUE);
        rbGrid.setColor(3,0, Color.RED);
        rbGrid.setColor(3,1, Color.RED);

        assertEquals(0.182, rbGrid.happyNeighbours(0,1, Color.RED), 0.005);
        assertEquals(0.25, rbGrid.happyNeighbours(0,3, Color.RED), 0.005);
        assertEquals(0.273, rbGrid.happyNeighbours(1, 0, Color.BLUE), 0.005);
        assertEquals(0.267, rbGrid.happyNeighbours(1,1, Color.RED), 0.005);
        assertEquals(0.267, rbGrid.happyNeighbours(1,2, Color.BLUE), 0.005);
        assertEquals(0.273, rbGrid.happyNeighbours(1,3, Color.BLUE), 0.005);
        assertEquals(0.267, rbGrid.happyNeighbours(2,1, Color.BLUE), 0.005);
        assertEquals(0.267, rbGrid.happyNeighbours(2,2, Color.BLUE), 0.005);
        assertEquals(0.25, rbGrid.happyNeighbours(3,0, Color.RED), 0.005);
        assertEquals(0.182, rbGrid.happyNeighbours(3,1, Color.RED), 0.005);
    }
}
