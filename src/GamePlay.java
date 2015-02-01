import java.io.IOException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 6/14/13
 * Time: 8:57 PM
 */
public class GamePlay
{
    public static void main(String[] args) throws IOException {
        int rows, columns, bombs;
        boolean lose = false;
        System.out.println("New User: Enter the number of rows\n");
        Scanner br = new Scanner(System.in);
        rows = br.nextInt();
        System.out.println("New User: Enter the number of columns\n");
        columns = br.nextInt();
        System.out.println("New User: Enter the number of bombs\n");
        bombs = br.nextInt();

        Board gameBoard = new Board(rows, columns, bombs);
        gameBoard.printDisplayMatrix();

        while(true)
        {
            System.out.println("Make your move.Select row and column\n");
            System.out.println("Row:\t");
            int i = br.nextInt();
            System.out.println("Column:\t");
            int j = br.nextInt();
            if (i < 0 || i > rows - 1 || j <0 || j > columns - 1)
            {
                System.out.println("Please enter valid values");
                continue;
            }
            gameBoard.show[i][j] = true;
            gameBoard.displayBoard();
            gameBoard.printDisplayMatrix();

        }
    }
}
