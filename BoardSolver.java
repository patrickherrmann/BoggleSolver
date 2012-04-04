/**
 *
 * Finds all the words in a given Scramble board
 *
 * @author patrick
 * @version ProgramVersion
 */


import java.awt.Point;
import java.util.ArrayList;

public class BoardSolver
{

   private Board board;

   public BoardSolver(Board board)
   {
      this.board = board;
   }

   public ArrayList<String> findSolutions(TrieNode wordList)
   {
      ArrayList<String> solutions = new ArrayList<String>();
      for (int i = 0; i < wordList.getChildren().size(); i++)
      {
         for (int row = 0; row < board.getRows(); row++)
         {
            for (int col = 0; col < board.getCols(); col++)
            {
               this.processNode(solutions, "", new ArrayList<Point>(), new Point(row, col), wordList.getChildren().get(i));
            }
         }
      }
      return solutions;
   }

   private void processNode(ArrayList<String> words, String fragment, ArrayList<Point> visited, Point point, TrieNode node)
   {
      Point attempt = new Point(point);
      boolean nearbyLetter = false;

      for (int i = 0; i < Board.DIRECTIONS.length; i++)
      {
         attempt = new Point(point.x + Board.DIRECTIONS[i].x, point.y + Board.DIRECTIONS[i].y);
         
         if (board.isInBounds(attempt.x, attempt.y) && !visited.contains(attempt))
         {
            if (board.getLetters()[attempt.x][attempt.y].equals(node.getValue()))
            {
               nearbyLetter = true;
               break;
            }
         }
      }

      if (!nearbyLetter)
      {
         return;
      }

      fragment += node.getValue();
      visited.add(attempt);
      

      if (node.isTerminates())
      {
         fragment = fragment.replaceAll("&", "qu");
         if (!words.contains(fragment))
         {
            words.add(fragment);
         }
      }

      for (int i = 0; i < node.getChildren().size(); i++)
      {
         processNode(words, fragment, visited, new Point(attempt), node.getChildren().get(i));
      }

   }

   /**
    * @return the board
    */
   public Board getBoard()
   {
      return board;
   }
}
