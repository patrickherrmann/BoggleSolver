/**
 *
 * Scramble board
 *
 * @author patrick
 * @version Scramble
 */

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board
{

    private int rows;
    private int cols;
    private String[][] letters;
    public static final int DEFAULT_SIZE = 4;
    public static final String[] CUBES =
    {
        "aaciot", "abilty", "abjmo&", "acdemp",
        "acelsr", "adenvz", "ahmors", "bfiorx",
        "snowed", "dknotu", "eefhiy", "egintv",
        "egkluy", "ehinps", "elpstu", "gilruw"
    };
    public static final Point[] DIRECTIONS =
    {
        new Point(0, -1),
        new Point(1, -1),
        new Point(1, 0),
        new Point(1, 1),
        new Point(0, 1),
        new Point(-1, 1),
        new Point(-1, 0),
        new Point(-1, -1)
    };

    public Board()
    {
        this.rows = DEFAULT_SIZE;
        this.cols = DEFAULT_SIZE;
        randomize();
    }

    public Board(int size)
    {
        this.rows = size;
        this.cols = size;
        randomize();
    }

    public Board(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        randomize();
    }

    public Board(int size, String letters)
    {
        this.rows = size;
        this.cols = size;
        fill(letters);
    }

    public Board(int rows, int cols, String letters)
    {
        this.rows = rows;
        this.cols = cols;
        fill(letters);
    }

    public Board(Board board)
    {
        this.rows = board.rows;
        this.cols = board.cols;
        this.letters = board.getLetters();
    }

    private void fill(String letters)
    {
        this.letters = new String[rows][cols];
        int index;
        for (int row = 0; row < this.rows; row++)
        {
            for (int col = 0; col < this.cols; col++)
            {
                index = (row * rows + col) % letters.length();
                this.letters[row][col] = letters.substring(index, index + 1);
            }
        }
    }

    private void randomize()
    {
        this.letters = new String[this.rows][this.cols];
        Random gen = new Random();
        ArrayList<String> cubeList = new ArrayList<String>();
        int side;
        int cube;

        for (int row = 0; row < this.rows; row++)
        {
            for (int col = 0; col < this.cols; col++)
            {
                /* If we run out of cubes, load another set */
                if (cubeList.isEmpty())
                {
                    cubeList.addAll(Arrays.asList(Board.CUBES));
                }

                cube = gen.nextInt(cubeList.size());
                side = gen.nextInt(cubeList.get(cube).length());
                this.letters[row][col] = cubeList.get(cube).substring(side, side + 1);
                cubeList.remove(cube);
            }
        }
    }

    public boolean isInBounds(int row, int col)
    {
        return (row >= 0) && (row < this.rows) && (col >= 0) && (col < this.cols);
    }

    @Override
    public String toString()
    {
        String output = "";
        String cube;
        for (int i = 0; i < getRows(); i++)
        {
            for (int j = 0; j < getCols(); j++)
            {
                cube = this.letters[i][j].replaceAll("&", "qu");
                output += cube;
                if (cube.length() == 1)
                {
                    output += " ";
                }

            }
            output += "\n";
        }
        return output;
    }

    /**
     * @return the rows
     */
    public int getRows()
    {
        return rows;
    }

    /**
     * @return the cols
     */
    public int getCols()
    {
        return cols;
    }

    /**
     * @return the letters
     */
    public String[][] getLetters()
    {
        return letters;
    }

    public static int getPoints(int wordLen)
    {
        switch (wordLen)
        {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                return 1;
            case 5:
                return 2;
            case 6:
                return 3;
            case 7:
                return 5;
            default:
                return 11;
        }
    }
}
