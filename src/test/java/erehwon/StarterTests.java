package erehwon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class StarterTests {

    @Test
    public void testCorrectCreation() {
        RedBlueGrid rbGrid = new RedBlueGrid(10, 1, 0.3, 0.4, 0.35);
        // complete the test by verifying that the grid has the correct number
        // of vacant, red, and blue cells
    }

    @Test
    public void testSetColor() {
        RedBlueGrid rbGrid = new RedBlueGrid(10, 1, 0.3, 0.4, 0.35);
        // complete the test by setting a cell's colour and verifying that
        // the colour was correctly changed
        rbGrid.setColor(1,1, Color.RED);
        rbGrid.oneTimeStep();
    }

    //tests for happiness levels

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


    @Test
    public void testSetColor2() {
        RedBlueGrid rbGrid = new RedBlueGrid(11, 1, 0.3, 0.4, 0.35);
        rbGrid.setColor(2, 2, Color.RED);
        assertEquals(Color.RED, rbGrid.getColor(2, 2));
        rbGrid.setColor(5, 5, Color.BLUE);
        assertEquals(Color.BLUE, rbGrid.getColor(5, 5));
        rbGrid.setColor(0, 0, Color.RED);
        assertEquals(Color.RED, rbGrid.getColor(0, 0));
//        rbGrid.setColor(12, 12, Color.BLUE);
//        assertNotEquals(Color.BLUE, rbGrid.getColor(12, 12));
    }

    @Test
    public void testShiftColor() {
        RedBlueGrid rbGrid = new RedBlueGrid(3, 1, 0.5, 0.5, 0);

        // Set specific colors in the grid
        rbGrid.setColor(0, 0, Color.RED);
        rbGrid.setColor(1, 1, Color.BLUE);
        rbGrid.setColor(2, 2, Color.WHITE);

        // Shift the colors
        rbGrid.shiftColor(0,0);
        rbGrid.shiftColor(1,1);
        rbGrid.shiftColor(2,2);

        // Verify the shifted colors
        assertEquals(Color.BLUE, rbGrid.getColor(0, 0));
        assertEquals(Color.WHITE, rbGrid.getColor(1, 1));
        assertEquals(Color.RED, rbGrid.getColor(2, 2));
    }

    //reset ? tbd

    @Test
    public void testFractionHappy_AllHappy() {
        RedBlueGrid rbGrid = new RedBlueGrid(10, 1, 0.3, 0.4, 0);

        rbGrid.reset(0.2, 0.6, 0);  // Reset with new parameters if needed
        double expectedFractionHappy = 1.0;  // All residents are happy
        assertEquals(expectedFractionHappy, rbGrid.fractionHappy(), 0.001);
    }

    @Test
    public void testFractionNotHappy() {
        RedBlueGrid rbGrid = new RedBlueGrid(10, 1, 0.3, 0.4, 1);

        rbGrid.reset(0.2, 0.6, 1);  // Reset with new parameters if needed
        double expectedFractionHappy = 1;
        assertNotEquals(expectedFractionHappy, rbGrid.fractionHappy(), 0.001);
     }

     @Test
    public void testEmptyHappinessCoordinate() {
        EmptyHappinessCoordinate test = new EmptyHappinessCoordinate(3,2,0.7,0.5);
        assertEquals(3,test.getX());
        assertEquals(2,test.getY());
        assertEquals(Color.BLUE,test.getColour());
        assertEquals(0.7,test.getEmptyHappinessCoordinate());
     }

     @Test
    public void testEmptyHappinessCoordinate2() {
         EmptyHappinessCoordinate test = new EmptyHappinessCoordinate(4,10,0.55,1.0);
         assertEquals(0.55,test.getBlueHappiness());
         assertEquals(1.0,test.getRedHappiness());
     }

     @Test
    public void testEmptyHappinessCoordinate3() {
         EmptyHappinessCoordinate test = new EmptyHappinessCoordinate(3,2,0.7,0.5);
         test.setColor(Color.BLUE);
         assertEquals(Color.BLUE,test.getColour());
     }

     @Test
    public void testEmptyHappinessCoordinate4() {
        EmptyHappinessCoordinate test = new EmptyHappinessCoordinate(3,2,0.7,0.5);
        test.modifyEmptyHappiness(0.1);
        assertEquals(0.1,test.getEmptyHappinessCoordinate());
     }
}
