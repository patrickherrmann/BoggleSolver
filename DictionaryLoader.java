import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DictionaryLoader
{

   public static TrieNode loadDictionary(String filename, int minWordLen, int maxWordLen) throws IOException
   {
      TrieNode root = new TrieNode("");
      BufferedReader br = new BufferedReader(new FileReader(filename));
      String word;
      while ((word = br.readLine()) != null)
      {
         if (word.length() >= minWordLen && word.length() <= maxWordLen)
         {
            root.addWord(word.toLowerCase().replaceAll("qu", "&"));
         }
      }
      return root;
   }

   public static TrieNode loadDictionary(String filename) throws IOException
   {
      return DictionaryLoader.loadDictionary(filename, 1, Integer.MAX_VALUE);
   }
}
