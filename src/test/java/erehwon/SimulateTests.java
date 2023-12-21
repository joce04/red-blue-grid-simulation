package erehwon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class SimulateTests {
    @Test void simulateSmallSteps() {
        RedBlueGrid rbGrid = new RedBlueGrid(4, 2, 0.5, 0.5, 0.2);
        rbGrid.simulate(3);
        assertTrue(rbGrid.fractionHappy() > 0.8);
    }

    @Test void simulateSteps() {
        RedBlueGrid rbGrid = new RedBlueGrid(4, 2, 0.5, 0.5, 0.2);
        rbGrid.simulate(10);
        assertTrue(rbGrid.fractionHappy() > 0.95);
    }

    @Test
    public void testBubbleSort() {
        RedBlueGrid rbGrid = new RedBlueGrid(4, 2, 0.5, 0.5, 0.2);
        List<EmptyHappinessCoordinate> list = new ArrayList<>();
        list.add(new EmptyHappinessCoordinate(0,1, 0.3, 0.6));
        list.add(new EmptyHappinessCoordinate(0,2, 0.7, 0.6));
        list.add(new EmptyHappinessCoordinate(0,3, 0.1, 0.8));
        list.add(new EmptyHappinessCoordinate(0,4, 0.2, 0.9));
        list.add(new EmptyHappinessCoordinate(0,5, 0.1, 1.0));
        list.add(new EmptyHappinessCoordinate(0,6, 0.24, 0.22));

        List<EmptyHappinessCoordinate> sortedList = new ArrayList<>();
        sortedList.add(new EmptyHappinessCoordinate(0,5, 0.1, 1.0));
        sortedList.add(new EmptyHappinessCoordinate(0,4, 0.2, 0.9));
        sortedList.add(new EmptyHappinessCoordinate(0,3, 0.1, 0.8));
        sortedList.add(new EmptyHappinessCoordinate(0,2, 0.7, 0.6));
        sortedList.add(new EmptyHappinessCoordinate(0,1, 0.3, 0.6));
        sortedList.add(new EmptyHappinessCoordinate(0,6, 0.24, 0.22));
        assertEquals(sortedList, rbGrid.testBubbleSort(list));
    }

    @Test
    public void testBubbleSortParameter() {
        RedBlueGrid rbGrid = new RedBlueGrid(4, 2, 0.5, 0.5, 0.2);
        List<EmptyHappinessCoordinate> list = new ArrayList<>();
        list.add(new EmptyHappinessCoordinate(0,1, 0.3, 0.6));
        list.add(new EmptyHappinessCoordinate(0,2, 0.7, 0.6));
        list.add(new EmptyHappinessCoordinate(0,3, 0.15, 0.8));
        list.add(new EmptyHappinessCoordinate(0,4, 0.2, 0.9));
        list.add(new EmptyHappinessCoordinate(0,5, 0.0, 1.0));
        list.add(new EmptyHappinessCoordinate(0,6, 0.24, 0.22));

        List<EmptyHappinessCoordinate> sortedList = new ArrayList<>();
        sortedList.add(new EmptyHappinessCoordinate(0,2, 0.7, 0.6));
        sortedList.add(new EmptyHappinessCoordinate(0,1, 0.3, 0.6));
        sortedList.add(new EmptyHappinessCoordinate(0,6, 0.24, 0.22));
        sortedList.add(new EmptyHappinessCoordinate(0,4, 0.2, 0.9));
        sortedList.add(new EmptyHappinessCoordinate(0,3, 0.15, 0.8));
        sortedList.add(new EmptyHappinessCoordinate(0,5, 0.0, 1.0));

        assertEquals(sortedList, rbGrid.testBubbleSortParameter(list, Color.BLUE));
    }

    @Test
    public void testBubbleSortParameterSmall() {
        RedBlueGrid rbGrid = new RedBlueGrid(4, 2, 0.5, 0.5, 0.2);
        List<EmptyHappinessCoordinate> list = new ArrayList<>();
        list.add(new EmptyHappinessCoordinate(0,1, 0.3, 0.6));

        List<EmptyHappinessCoordinate> sortedList = new ArrayList<>();
        sortedList.add(new EmptyHappinessCoordinate(0,1, 0.3, 0.6));

        assertEquals(sortedList, rbGrid.testBubbleSortParameter(list, Color.BLUE));
    }
}
