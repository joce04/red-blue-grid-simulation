package erehwon;

import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;


public class RedBlueGrid {
    private static final Color[] COLORS = {Color.WHITE, Color.RED, Color.BLUE};
    private Color[][] grid;
    private int size;
    private int neighborhoodDistance;
    private int numVacant;
    private int numRed;
    private int numBlue;
    private double happinessThreshold;

    /**
     * Constructor for the function, generates a random grid filled with
     * the specified fraction of red occupants and fraction of blue occupants
     *
     * @param size the length of one side of the square grid, size >= 1.
     * @param neighborhoodDistance the distance that one square considers its neighbours, neighbourhoodDistance >= 1
     * @param fractionVacant the fraction of vacant squares in the grid, 0 <= fractionVacant <= 1.
     * @param fractionRed the fraction of red squares in the grid, 0 <= fractionRed  <= 1
     * @param happinessThreshold the minimum happiness level required for an occupant to be considered happy,
     *                           0 <= happinessThreshold <= 1.
     */
    public RedBlueGrid(int size,
                       int neighborhoodDistance,
                       double fractionVacant,
                       double fractionRed,
                       double happinessThreshold) {

        greaterThanOne(size);
        this.size = size;

        greaterThanOne(neighborhoodDistance);
        this.neighborhoodDistance = neighborhoodDistance;

        this.grid = new Color[size][size];

        //generates a random grid with the given fractions
        reset(fractionVacant, fractionRed, happinessThreshold);
    }

    /**
     * Evaluate if the size of the grid is greater than or equal to 1.
     * @param number the number we are evaluating to see if it's greater than or equal to 1.
     * @throws IllegalArgumentException if the requirement is not satisfied.
     */
    private void greaterThanOne(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Your input for size or neighbourhoodDistance must be greater than or equal to 1.");
        }
    }

    /**
     * Evaluates if a number is a decimal between zero and one, inclusive.
     * @param number the number we are evaluating to see if it's between zero and one, inclusive.
     * @throws IllegalArgumentException if the requirement is not satisfied.
     */
    private void isFraction(double number) {
        if (number > 1 || number < 0) {
            throw new IllegalArgumentException("Your number"
                    + " must be a decimal between 0 and 1, inclusive.");
        }
    }

    /**
     * Gets the colour of a square on the grid given a row and a column
     * @param row an integer defining the row of the grid (row < size)
     * @param col an integer defining the column of the grid (col < size)
     * @return the colour of the square, if the colour is not Red or Blue, then it will return White.
     */
    public Color getColor(int row, int col) {
        if (this.grid[row][col] == COLORS[1]) {
            return COLORS[1];
        } else if (this.grid[row][col] == COLORS[2]) {
            return COLORS[2];
        }

        return COLORS[0];
    }

    /**
     * checks to see if a number is positive
     * @throws IllegalArgumentException if the number is negative
     * @param number the number we are evaluating
     */
    private void isPositive(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("The number must be a positive integer."
                    + "Your argument is negative");
        }
    }

    /**
     * evaluates if the number exceed the total capacity
     * @throws IllegalArgumentException if the number exceeds total capacity of Erehwon (i.e. more people than grid spots)
     * @param number the number we will compare to the capacity of Erehwon.
     * @param limit The max capacity of Erewhon
     */
    private void enoughSpace(int number, int limit) {
        if (number > limit) {
            throw new IllegalArgumentException("ERROR: the number of red and blue people"
                    + "cannot be greater than number of people that can fit in the grid");
        }
    }

    /**
     * Generates an array with red, blue and white corresponding to numRed, numBlue, numVacant
     * @param colorList an array of colours with the number of red elements corresponding to numRed,
     *                  the number of blue elements corresponding to numBlue and
     *                  the number of white elements corresponding to numVacant
     */
    private void allColoursArray(ArrayList<Color> colorList) {
        for (int i = 0; i < numRed; i++){
            colorList.add(COLORS[1]);
        }
        for (int i = 0; i < numBlue; i++){
            colorList.add(COLORS[2]);
        }
        for (int i = 0; i < numVacant; i++){
            colorList.add(COLORS[0]);
        }
    }

    /**
     * Colors this.grid in with red, blue and white corresponding to numRed, numBlue, numVacant
     * @param colorList an array of colours with the number of red elements corresponding to numRed,
     *                  the number of blue elements corresponding to numBlue and
     *                  the number of white elements corresponding to numVacant
     */
    private void colorGrid(ArrayList<Color> colorList) {
        Random rand = new Random();

        for (int row = 0; row < size; row++ ){
            for (int col = 0; col < size; col++) {
                int randColor = rand.nextInt(colorList.size()); //we have to recalculate because we are removing elements
                setColor(row, col, colorList.get(randColor));
                colorList.remove(randColor);  //remove that specific color we used
            }
        }
    }

    /**
     * initializes the grid to be a random combination of the numRed, numBlue and numVacant values
     * and colours the values in
     */
    private void gridInitializer() {

//        System.out.println("GRID INITIALIZER");
//        System.out.println(numBlue);
//        System.out.println(numRed);

        isPositive(numRed);
        isPositive(numBlue);

        enoughSpace(numRed + numBlue, size * size);

        //making an arraylist with all the colours;
        ArrayList<Color> colorList = new ArrayList<>();
        allColoursArray(colorList);

        //go to each cell and generate a random index to color it out
        colorGrid(colorList);
    }

    /**
     * determines whether the color provided is one of the ones in the COLORS array
     * @param color the color to match
     * @return true if the color is in the COLORS array, false otherwise.
     */
    private boolean notValidColor(Color color) {
        return (color != COLORS[0] && color != COLORS[1] && color != COLORS[2]);
    }

    /**
     * determines whether the column or row specified is within the grid
     * @param location the row or column index
     * @return true if the location is within the grid, false otherwise.
     */
    private boolean notValidLocation(int location) {
        return (location < 0 || location >= this.size );
    }

    /**
     * setColor method
     * @param row - the number of rows inside the grid
     * @param col - the number of columns inside the grid
     * @param color - the color we want the entry at this.grid[row][col] to be.
     *
     * @return returns true if the grid is successfully set, otherwise returns false
     */
    public boolean setColor(int row, int col, Color color) {

        if (notValidColor(color)) {
            return false;
        }
        if (notValidLocation(row)) {
            return false;
        } else if (notValidLocation(col)) {
            return false;
        }

        this.grid[row][col] = color;

        return true;
    }

    /**
     * Shifts the colours in the order WHITE -> RED -> BLUE -> WHITE -> ...
     *
     * @param row the row of the grid (row < size)
     * @param col the column of the grid (col < size)
     */
    public void shiftColor(int row, int col) {
        if (grid[row][col] == COLORS[0]) {
            grid[row][col] = COLORS[1];
        } else if (grid[row][col] == COLORS[1]) {
            grid[row][col] = COLORS[2];
        } else {
            grid[row][col] = COLORS[0];
        }
    }

    /**
     * Recolours the cells with the given fraction of vacant cells,
     * the fraction of non-vacant cells that are red (the rest are
     * blue cells) and possibly a new happiness threshold.
     *
     * @param fractionVacant fraction of vacant cells, 0 <= fractionVacant <= 1
     * @param fractionRed the fraction of red cells, 0 <= fractionRed <= 1
     * @param happinessThreshold, the minimum happiness level for a resident to be
     *                            considered happy, 0<= happinessThreshold <= 1
     */
    public void reset(double fractionVacant,
                      double fractionRed,
                      double happinessThreshold) {

        //checks to see if fractionVacant, fractionRed and happiness Threshold are decimals
        //between 0 and 1, inclusive.
        isFraction(fractionVacant);
        isFraction(fractionRed);
        isFraction(happinessThreshold);

        this.happinessThreshold = happinessThreshold;

        int totalNum = this.size * this.size;

        //uses floor to determine numVacant
        this.numVacant = (int) (fractionVacant * totalNum);

        //uses floor to determine numRed
        this.numRed = (int) (fractionRed * (totalNum - this.numVacant));

        //numBlue is the remainder
        this.numBlue = totalNum - numVacant - numRed;

        //initializes a new grid
        gridInitializer();
    }

    /** returns whether the given resident at grid[row][col] is happy
     * a resident is happy if the fraction of neighbours with the same
     * color is more than the happiness threshold
     * @throws IllegalArgumentException error if the selected color block is white.
     * @throws IllegalArgumentException if row or column is not within the bounds.
     *
     * @param row the current row location of the resident. 0<=row<=size
     * @param col the current column location of the resident 0<=col<=size
     *            NOTE: row and col should already be within the domain, as it has been checked by other functions already.
     * @return true if the resident's happiness exceeds the happiness threshold, false otherwise.
     */
    public boolean isHappy(int row, int col) {
        if (notValidLocation(row) || notValidLocation(col)) {
            throw new IllegalArgumentException("The resident's location exceeds the bounds of the grid");
        }

        if (getColor(row, col) != COLORS[0]) {
            return happyNeighbours(row, col, getColor(row, col)) >= happinessThreshold;
        } else {
            throw new IllegalArgumentException("Selected color block is white!");
        }
    }

    /** returns whether the given resident at grid[row][col] is happy
     * a resident is happy if the fraction of neighbours with the same
     * color is more than the happiness threshold
     * public for testing reasons
     *
     * @param row the current row location of the resident 0<=row<=size
     * @param col the current column location of the resident 0<=col<=size
     *            NOTE: row and col should already be within the domain, as it has been checked by other functions already.
     * @param color the current color of the resident. Should be either BLUE, WHITE, or RED.
     *              NOTE: Should already be the right color, as it has been filtered already by other methods.
     * @return the fraction of neighbours with the same color
     */
    public double happyNeighbours(int row, int col, Color color) {
        int happyNeighbours = 0;
        int totalNeighbours = 0;

        for (int rowNum = row - neighborhoodDistance; rowNum <= row + neighborhoodDistance; rowNum++) {
            //check if the square is outside the row limit
            if (rowNum >= size) {
                break;
            }

            if (rowNum >= 0) {
                for (int colNum = col - neighborhoodDistance; colNum <= col + neighborhoodDistance; colNum++) {
                    //check if the square is outside the column limit
                    if (colNum >= size) {
                        break;
                    }

                    if (colNum >= 0) {
                        //check each grid in the row
                        if (rowNum != row || colNum != col) {
                            totalNeighbours++;

                            if (color == grid[rowNum][colNum]) {
                                happyNeighbours++;
                            }
                        }
                    }
                }
            }
        }
        return happyNeighbours / (double) totalNeighbours;

    }

    /**
     * Determines the fraction of Erewhon residents who are happy
     *
     * @return the fraction of Erewhon residents that are happy
     */
    public double fractionHappy() {

        //numHappyResidents is the number of happy residents within the grid counted so far.
        int numHappyResidents = 0;
        //fractionHappy is the decimal value representing the percent of residents who are happy.
        //Calculated at the end of the fxn.
        double fractionHappy = 0;

        for (int rowNum = 0; rowNum < size; rowNum++ ){
            for (int colNum = 0; colNum < size; colNum++ ){
                if (grid[rowNum][colNum] != COLORS[0]) {
                    if (isHappy(rowNum, colNum)) {
                        numHappyResidents++;
                    }
                }

            }
        }

        fractionHappy = (double) numHappyResidents / (size * size - numVacant);

        isFraction(fractionHappy);

        return fractionHappy;
    }

    /**
     * adds the coordinates of empty space and occupied unhappy spaces to two separate arrays
     *
     * @param emptyCoordinates the arrayList holding the empty coordinates
     * @param occupiedCoordinates the arrayList holding the occupied coordinates of the unhappy people
     */
    private void fillCoordinates(List<Coordinate> emptyCoordinates, List<Coordinate> occupiedCoordinates) {
        for (int i = 0; i < this.size; i++){
            for (int j = 0; j < this.size; j++){
                if (grid[i][j] == Color.WHITE){
                    emptyCoordinates.add(new Coordinate(i,j));
                }
                else {
                    if (!isHappy(i, j)){
                        occupiedCoordinates.add(new Coordinate(i, j));
                    }
                }
            }
        }
    }

    /**
     * simulates exactly one time step of movement where all unhappy reds and unhappy blues simultaneously move into
     * vacant (white) spots on the grid. If there is not enough vacant spots left on the grid, the unhappy reds
     * or unhappy blues will stop moving.
     *
     * Movement is random.
     */
    public void oneTimeStep() {

        //Initializing two array lists, one for empty coordinates and one for occupied coordinates
        List<Coordinate> emptyCoordinates = new ArrayList<>();
        List<Coordinate> occupiedCoordinates = new ArrayList<>();

        fillCoordinates(emptyCoordinates, occupiedCoordinates);


        //compute the number of changes necessary
        int sizeEmpty = emptyCoordinates.size();
        int sizeOccupied = occupiedCoordinates.size();
        int numChanges = Math.min(sizeEmpty, sizeOccupied);

        //move elements NumOfChanges times
        for (int i = 0; i < numChanges; i++) {

            Random rand = new Random();
            int randOccp = rand.nextInt(sizeOccupied - i);  // -i here is because we shrink the size at each iteration
            int randEmpt = rand.nextInt(sizeEmpty - i);

            //swap the two values
            setColor(emptyCoordinates.get(randEmpt).getX(), emptyCoordinates.get(randEmpt).getY(), grid[occupiedCoordinates.get(randOccp).getX()][occupiedCoordinates.get(randOccp).getY()]);
            setColor(occupiedCoordinates.get(randOccp).getX(), occupiedCoordinates.get(randOccp).getY(), COLORS[0]);

            //removing the coordinates from the arrayList
            emptyCoordinates.remove(randEmpt);
            occupiedCoordinates.remove(randOccp);
        }
    }

    /**
     * Sorts array list of type empty Happiness Coordinate in ascending order of happiness.
     * @param locHapOfColour Unsorted list of all vacant grid locations, of type EmptyHappinessCoordinate.
     *                       - see class EmptyHappinessCoordinate for more info.
     */
    private void bubbleSort(List<EmptyHappinessCoordinate> locHapOfColour) {
        int i;
        int j;
        EmptyHappinessCoordinate temp;
        int n = locHapOfColour.size();
        for (i = 0; i < n - 1; i++) {
            for (j = 0; j < n - i - 1; j++) {
                if (locHapOfColour.get(j).getEmptyHappinessCoordinate() < locHapOfColour.get(j + 1).getEmptyHappinessCoordinate()) {

                    // Swap arr[j] and arr[j+1]
                    temp = locHapOfColour.get(j);
                    locHapOfColour.set(j, locHapOfColour.get(j + 1));
                    locHapOfColour.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * sorts an array in ascending order of happiness to prioritize a specific color of person in the red blue grid
     * @param emptyCoordinates an arrayList of type Empty Happiness Coordinate that holds the List you want to
     *                         sort favouring colour. emptyCoordinates cannot be null. emptyCoordinates
     *                         will be modified into a sorted array within this method.
     * @param colour must be a color in the COLORS array and cannot be the first colour in the COLORS array,
     *               COLORS[0].
     */
    private void bubbleSortBasedOffParameter(List<EmptyHappinessCoordinate> emptyCoordinates, Color colour) {
        EmptyHappinessCoordinate temp;
        int n = emptyCoordinates.size();
        double happiness;

        for (EmptyHappinessCoordinate emptyCoordinate : emptyCoordinates) {
            emptyCoordinate.setColor(colour);
            //we want to optimize for red
            if (colour == COLORS[1]) {
                happiness = emptyCoordinate.getRedHappiness();

            } else {
                //we want to optimize for blue
                happiness = emptyCoordinate.getBlueHappiness();
            }

            //set the empty happiness to be the current happiness
            emptyCoordinate.modifyEmptyHappiness(happiness);
        }

        bubbleSort(emptyCoordinates);
    }

    /**
     * used to test the bubbleSort function
     * @param list the arrayList of type EmptyHappinessCoordinate that we are sorting, cannot be null
     * @return a sorted arrayList of type EmptyHappinessCoordinate in ascending order, will not be null
     */
    public List <EmptyHappinessCoordinate> testBubbleSort(List <EmptyHappinessCoordinate> list) {
        List <EmptyHappinessCoordinate> tempList = list;
        bubbleSort(tempList);
        return tempList;
    }
    /**
     * used to test the bubbleSort function
     * @param list the arrayList of type EmptyHappinessCoordinate that we are sorting, cannot be null
     * @return a sorted arrayList of type EmptyHappinessCoordinate in ascending order, will not be null
     */
    public List <EmptyHappinessCoordinate> testBubbleSortParameter(List <EmptyHappinessCoordinate> list, Color color) {
        List <EmptyHappinessCoordinate> tempList = list;
        bubbleSortBasedOffParameter(tempList, color);
        return tempList;
    }


    /**
     * Optimized function to reaching maximum happiness in the least number of time-steps
     *
     * @param numSteps the maximum number of time steps we should take before accepting defeat
     *                 that not everyone will be happy, numSteps > 0.
     * NOTE: cannot be tested as it heavily depends on the UI and the initial random generation of the grid. We cannot predict
     * the randomization of the grid, thus we cannot predict the arrangement of the grid after one directed time step
     */
    public void simulate(int numSteps) {
        double currentHappiness = 0;
        int consecutive = 0;

        //each iteration of the loop is one time step
        for (int i = 0; i < numSteps; i++) {
            directedSteps();

            //happiness is
            if (fractionHappy() >= 1.0) {
                System.out.println("HOORAY! HAPPINESS IS 100%");
                break;
            }

            //if we get the same happiness levels twice in a row
            if (currentHappiness == fractionHappy() ){
                //try randomizing the map
                if (i < numSteps - 15) {
                    //call oneTimeStep to move all the unhappy people to new random spots
                    System.out.println("one time step to randomize");
                    oneTimeStep();
                } else {
                    consecutive++;
                }
            }
            //if there are more than 10 consecutive happiness levels in a row, then accept
            //defeat, not everyone can be happy, and break out of the for loop
            if (consecutive >= 10) {
                System.out.println("Everyone cannot be happy in this configuration! "
                        + "Happiness percentage: " + currentHappiness * 100 + "%");
                break;
            }

            //set the current happiness to fraction happy
            currentHappiness = fractionHappy();
            System.out.println("Happiness percentage: " + currentHappiness * 100 + "%");
        }
    }

    /**
     * Swaps a colour person into a vacant cell if the colour person will be more happy in that vacant cell
     * Effects: the first element of unhappy and rankedVacant are both removed from their respective arrays.
     * @param unhappy contains a complete list of all currently unhappy squares of a given colour, the
     *                list cannot be null and must have one or more elements.
     * @param rankedVacant contains a complete list of all currently vacant locations of type EmptyHappinessCoordinate.
     *                     Each entry of EmptyHappinessCoordinate is ranked by the happiness field. Must have at least
     *                     one element, and cannot be null.
     *                     See **class EmptyHappinessCoordinate for more information.
     * @param colour the colour of the current unhappy square that the function may move, must be either COLORS[1] or COLORS[2].
     */
    private void checkRemainingUnhappy(List <Coordinate> unhappy, List <EmptyHappinessCoordinate> rankedVacant, Color colour) {
        int xCoordsWhite = rankedVacant.get(0).getX();
        int yCoordsWhite = rankedVacant.get(0).getY();
        int xCoordsColoured = unhappy.get(0).getX();
        int yCoordsColoured = unhappy.get(0).getY();

        //need to make sure that happy neighbours isn't counting the square that it's about to move
        setColor(xCoordsColoured, yCoordsColoured, COLORS[0]);
        if (happyNeighbours(xCoordsWhite, yCoordsWhite, colour) > happyNeighbours(xCoordsColoured, yCoordsColoured, colour)) {
            grid[xCoordsWhite][yCoordsWhite] = colour;
        } else {
            //set the colour back to its original colour
            setColor(xCoordsColoured, yCoordsColoured, colour);
        }

        rankedVacant.remove(0);
        unhappy.remove(0);
    }

    /**
     * effects: initializes 3 array lists with their respective values as follows:
     * unhappyBlues - An array list which contains a complete list of all unhappy blue blobs, and their coordinates (x,y).
     * unhappyReds - An array list which contains a complete list of all unhappy red blobs, and their coordinates (x,y)
     * rankedVacant - An array list which contains a complete list of all vacant squares and their coordinates, (x,y), as well
     *                as a color, red or blue, which would be happier if it were to move to the position (x,y). It also contains
     *                how happy this coloured blob would be if it were to move to position (x,y).
     * NOTE: cannot be tested as it heavily depends on the UI and the initial random generation of the grid. We cannot predict
     * the randomization of the grid, thus we cannot predict the arrangement of the grid after one directed time step
     */
    public void directedSteps() {

        List<Coordinate> unhappyBlues = new ArrayList<>();
        List<Coordinate> unhappyReds = new ArrayList<>();
        List<EmptyHappinessCoordinate> rankedVacant = new ArrayList<>();

        //fills each of the Array Lists
        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {
                if (grid[row][col] == COLORS[0]) {
                    //if the spot is vacant (white) add it to the rankedVacant array
                    rankedVacant.add(new EmptyHappinessCoordinate(row, col, happyNeighbours(row, col, COLORS[2]), happyNeighbours(row, col, COLORS[1])));
                } else if (!isHappy(row, col)) {
                    //if the current blue is unhappy, add to array unhappyBlues
                    if (grid[row][col] == COLORS[2]) {
                        unhappyBlues.add(new Coordinate(row, col));
                    } else if (grid[row][col] == COLORS[1]) {
                        //the current red is unhappy, add to array unhappyReds
                        unhappyReds.add(new Coordinate(row, col));
                    }
                }
            }
        }
        //Calls the bubbleSortEmptyHappinessCoordinate method. This method will rank all items in rankedVacant by
        //hypothetical happiness.
        //sorts the rankedVacant list
        bubbleSort(rankedVacant);

        //Determines if there are fewer unhappy people combined or vacant spaces.
        int minimum = Math.min(unhappyBlues.size() + unhappyReds.size(), rankedVacant.size());
        boolean arrayIsEmpty = false;
        //Calls checkRemainingUnahppy to modify the grid according to which colour would be happiest if it were to move to
        //a given location in the rankedVacant arraylist. 
        for (int i = 0; i < minimum; i++) {


            //if there are no more unhappyReds or unhappyBlues
            //we want to reorganize the rankedVacant array to optimize
            //for the remaining unhappy people
            //but we only want to do this ONCE (not every time we loop through
            //the array
            if (!arrayIsEmpty) {
                if (unhappyReds.isEmpty()) {
                    //reorganize rankedVacant array to optimize for the remaining unhappy people
                    //optimize for blue
                    bubbleSortBasedOffParameter(rankedVacant, COLORS[2]);
                    //set arrayIsEmpty to be true so that we never sort the array again
                    arrayIsEmpty = true;
                } else if (unhappyBlues.isEmpty()) {
                    //optimize for red
                    bubbleSortBasedOffParameter(rankedVacant, COLORS[1]);
                    //set arrayIsEmpty to be true so that we never sort the array again
                    arrayIsEmpty = true;
                }
            }

            Color preferedColor = rankedVacant.get(0).getColour();

            if (preferedColor == COLORS[1]) {
                    //check if there are any unhappy reds left
                    if (!unhappyReds.isEmpty()) {
                        checkRemainingUnhappy(unhappyReds, rankedVacant, COLORS[1]);
                    }
                    //check to see if there are any unhappy blues left
                    else if (!unhappyBlues.isEmpty()) {
                        checkRemainingUnhappy(unhappyBlues, rankedVacant, COLORS[2]);
                    }
            } else if (preferedColor == COLORS[2]) {
                //check if there are any unhappy blues left
                if (!unhappyBlues.isEmpty()) {
                    checkRemainingUnhappy(unhappyBlues, rankedVacant, COLORS[2]);
                }
                //check if there are any unhappy reds left
                else if (!unhappyReds.isEmpty()) {
                    checkRemainingUnhappy(unhappyReds, rankedVacant, COLORS[1]);
                }
            } else {
                throw new IllegalArgumentException("Something went wrong in line 527. Not sure what");
            }

        }

    }
}
/**
 * New data-type Coordinate.
 * Simply a pair of x and y coordinates, referencing a location on the grid (if it were a cartesian coordinate
 * grid).
 */
@SuppressWarnings("CheckStyle")
class Coordinate {
    //x coordinate on grid
    //y coordinate on grid
    private int x;
    private int y;

    /**
     * Constructor - constructs type coordinate object consisting of 2 parameters
     * @param x x coordinate. 0<=x<=size
     * @param y y coordinate. 0<=y<=size
     */
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

/**
 *  EmptyHappinessCoordinate - special data type with 4 fields. These are:
 *                            1. x coordinate : x coordinate of vacant location, 0<=x<=size
 *                            2. y coordinate: y coordinate of vacant location, 0<=y<=size
 *                            3. Color: The color that would be happiest if it were to move to the position (x,y), either WHITE, BLUE or RED
 *                            4. Happiness: how happy the blob of color would be if it moved to (x,y). 0<=Happiness<=1
 */
@SuppressWarnings("CheckStyle")
class EmptyHappinessCoordinate {
    //This is the x coordinate on the grid
    private int x;
    //This is the y coordinate on the grid
    private int y;
    //This is the colour that would be happiest at point (x,y) if it were to move there

    /**
     * blueHappiness - the happiness of blue
     * redHappiness - the happiness of red
     */
    private final double blueHappiness;
    private final double redHappiness;

    //The colour of the blob in the emptyHappinessCoordinate
    private Color colour;
    //This is how happy a square of colour "colour" would be if it were to move to point (x,y)
    private double emptyHappinessCoordinate;

    /**
     * This constructor builds our EmptyHappinessCoordinate object. It needs to compare the hypothetical happiness of a
     * blue and the hypothetical happiness of a red at point (x,y) to determine which would be happier if it were at this
     * location
     * @param x the x coordinate, must be greater than 0
     * @param y the y coordinate, must be greater than 0
     * @param blueHappinessAtCoord How happy a blue would be at point (x,y), must be less than 1 and greater than 0.
     * @param redHappinessAtCoord How happy a red would be at point (x,y), must be less than 1 and greater than 0.
     */
    EmptyHappinessCoordinate(int x, int y, double blueHappinessAtCoord, double redHappinessAtCoord) {
        this.x = x;
        this.y = y;
        this.blueHappiness = blueHappinessAtCoord;
        this.redHappiness = redHappinessAtCoord;
        if (blueHappinessAtCoord > redHappinessAtCoord) {
            this.emptyHappinessCoordinate = blueHappinessAtCoord;
            this.colour = Color.BLUE;
        } else {
            this.emptyHappinessCoordinate = redHappinessAtCoord;
            this.colour = Color.RED;
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Color getColour() {
        return this.colour;
    }

    public double getEmptyHappinessCoordinate() {
        return this.emptyHappinessCoordinate;
    }

    public double getBlueHappiness() {return this.blueHappiness; }
    public double getRedHappiness() {return this.redHappiness;}

    public void setColor(Color color) {
        this.colour = color;
    }

    //Modifies the empty happiness coordinate after a time step. This is because the empty happiness coordinate will change
    //as blobs move.
    public void modifyEmptyHappiness(double emptyHappinessCoordinate) {
        this.emptyHappinessCoordinate = emptyHappinessCoordinate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) { return false; }
        EmptyHappinessCoordinate vacant = (EmptyHappinessCoordinate) obj;
        if (vacant.getBlueHappiness() == this.blueHappiness
                && vacant.getRedHappiness() == this.redHappiness
                && vacant.getX() == this.x
                && vacant.getY() == this.y) {
            return true;
        } else {
            return false;
        }
    }
}