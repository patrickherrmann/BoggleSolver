/**
 *
 * Instances of this class are chained together to form a compact dictionary
 *
 * @author patrick
 * @version Scramble
 */

import java.util.ArrayList;

public class TrieNode
{

   private String value;
   private boolean terminates;
   private ArrayList<TrieNode> children;

   public TrieNode(String value)
   {
      this.value = value;
      terminates = false;
      children = new ArrayList<TrieNode>();
   }

   public void addWord(String word)
   {
      if (word.length() == 0)
      {
         terminates = true;
         return;
      }
      for (int i = 0; i < this.children.size(); i++)
      {
         if (children.get(i).value.equals(word.substring(0, 1)))
         {
            children.get(i).addWord(word.substring(1));
            return;
         }
      }

      TrieNode firstLetter = new TrieNode(word.substring(0, 1));
      children.add(firstLetter);
      firstLetter.addWord(word.substring(1));
   }

   public ArrayList<String> getWords()
   {
      ArrayList<String> words = new ArrayList<String>();
      this.processNode(words, "");
      return words;
   }

   private void processNode(ArrayList<String> words, String fragment)
   {
      fragment += value;
      if (this.terminates)
      {
         words.add(fragment);
      }
      for (int i = 0; i < children.size(); i++)
      {
         children.get(i).processNode(words, fragment);
      }
   }

   @Override
   public String toString()
   {
      String output = value;
      if (this.terminates)
      {
         output += "*";
      }
      return output;
   }

   /**
    * @return the value
    */
   public String getValue()
   {
      return value;
   }

   /**
    * @return the terminates
    */
   public boolean isTerminates()
   {
      return terminates;
   }

   /**
    * @return the children
    */
   public ArrayList<TrieNode> getChildren()
   {
      return children;
   }
}
