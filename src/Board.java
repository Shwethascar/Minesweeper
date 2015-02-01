import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 6/14/13
 * Time: 6:33 PM
 */
public class Board {
    public int rows = 0;
    public int columns = 0;
    public int numberOfBombs = 0;
    private int[][] boardMatrix;
    private char[][] displayMatrix;
    public boolean [][] show;
    private boolean lose = false;

    public Board(int r, int c, int n)
    {
        rows = r;
        columns = c;
        numberOfBombs = n;
        boardMatrix  = new int[rows][columns];
        displayMatrix = new char[rows][columns];
        show = new boolean[rows][columns];

        for (int i = 0; i<rows; i++)
        {
            for (int j = 0; j<columns; j++)
            {
                boardMatrix[i][j] = 1;
                displayMatrix[i][j] = '?';
                show[i][j] = false;
            }
        }
        this.randomizedBombPlace();
        this.placeHints();
    }

    private void randomizedBombPlace()
    {
        Random rand = new Random();
        int bombCount = numberOfBombs;
        while (bombCount > 0)
        {
        int pickedNumberRow = rand.nextInt(rows);
        int pickedNumberColumn = rand.nextInt(columns);

        if (boardMatrix[pickedNumberRow][pickedNumberColumn] == 0)
            continue;
        boardMatrix[pickedNumberRow][pickedNumberColumn] = 0;
        bombCount--;
        }

    }

    private void placeHints()
    {
        for (int i = 0; i< rows; i++)
        {
            for (int j=0; j<columns; j++)
            {
                int count = 0;
                if (boardMatrix[i][j] == 0)
                    continue;

                if (((i - 1) >= 0) && ((j - 1) >= 0) && (boardMatrix[i - 1][j - 1] == 0))
                     count++;
                if (((i - 1) >= 0) && (boardMatrix[i - 1][j] == 0))
                    count++;
                if (((i - 1) >= 0) && ((j + 1) < columns) && (boardMatrix[i - 1][j + 1] == 0))
                    count++;
                if (((j + 1) < columns) && (boardMatrix[i][j + 1] == 0))
                    count++;
                if (((i + 1) < columns) && ((j + 1) < columns) && (boardMatrix[i + 1][j + 1] == 0))
                    count++;
                if (((i + 1) < columns) && boardMatrix[i+1][j] == 0)
                    count++;
                if (((i + 1) < columns) && ((j - 1) >= 0) && boardMatrix[i+1][j-1] == 0)
                    count++;
                if (((j - 1) >= 0) && (boardMatrix[i][j - 1] == 0))
                    count++;

                if (count >0)
                    boardMatrix[i][j] = count;
                else
                    boardMatrix[i][j] = 999;
            }
        }
    }

   public void displayBoard()
    {
       for (int i = 0; i < rows ; i++)
       {
           for (int j = 0; j< columns; j++)
           {
             if(show[i][j])
             {
                if (boardMatrix[i][j] == 0)
                {
                   displayBombsAndLose();
                   return;
                }
                if (boardMatrix[i][j] != 999)
                {
                    displayMatrix[i][j] = Character.forDigit(boardMatrix[i][j], 10);
                }
                 else
                    displayMatrix[i][j] = '_';
             }

           }
       }
       checkIfWon();

    }

    private void checkIfWon()
    {
        boolean winFlag = false;
        int count = 0;
        for (int i=0; i< rows; i++)
        {
            for (int j=0; j< columns; j++)
            {
               if(boardMatrix[i][j]!= 0 && show[i][j])
               {
                   count++;
               }
               if (count == (rows*columns)-numberOfBombs)
               {
                   winFlag = true;
                   break;
               }
            }
        }
        if(winFlag)
        {
            printDisplayMatrix();
            System.out.println("You Win!!!");
            System.exit(0);
        }
        else
            return;
    }

    public void displayBombsAndLose()
    {
        for (int i = 0; i < rows ; i++)
        {
            for (int j = 0; j< columns; j++)
            {
                if (boardMatrix[i][j] == 0)
                    displayMatrix[i][j] = '*';
            }
        }
        System.out.flush();
        printDisplayMatrix();
        System.out.println("You Lose!!!");
        System.exit(0);
    }

    public void printDisplayMatrix()
    {
        System.out.print("+");
        for (int k = 0; k<columns*2; k++)
            System.out.print("-");
        System.out.print("+");
        System.out.println();

        for (int i = 0; i < rows ; i++)
        {
            System.out.print("|");
            for (int j = 0; j< columns; j++)
            {
                System.out.print(displayMatrix[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.print("+");
        for (int k = 0; k<columns*2; k++)
            System.out.print("-");
        System.out.print("+");
        System.out.println();

    }
}
