/**
/*
 * @author patrick
 */

import java.util.ArrayList;
import java.io.IOException;

public class Main
{

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args)
   {
   
      Board board;

      switch (args.length) {
         case 1:
            board = new Board(Integer.parseInt(args[0])); break;
         case 2:
            board = new Board(Integer.parseInt(args[0]), Integer.parseInt(args[1])); break;
         default:
            board = new Board(5, 5);
      }
      
      System.out.println(board);

      try 
      {
         TrieNode root = DictionaryLoader.loadDictionary("twl.txt", 3, board.getRows() * board.getCols());
         
         BoardSolver bs = new BoardSolver(board);

         long start, end;

         start = System.currentTimeMillis();
         ArrayList<String> solutions = bs.findSolutions(root);
         end = System.currentTimeMillis();

         int points = 0;
         for (int len = 3; len <= board.getRows() * board.getCols(); len++)
         {
            for (int i = 0; i < solutions.size(); i++)
            {
               if (solutions.get(i).length() == len)
               {
                  System.out.println(solutions.get(i));
                  points += Board.getPoints(solutions.get(i).length());
               }
            }
         }

         System.out.println(solutions.size() + " words for " + points + " points found in " + ((end - start) / 1000.0) + " seconds");
      }
      catch (IOException e)
      {
         System.out.println(e);
      }
   }
}
