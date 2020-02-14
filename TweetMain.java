package assign_1.Assign_1;

/**
  @author: Jesse Shinol
  TweetMain is the main function to call buildTables and inputReader and 
  tweetParse and TweetCategorizer. It will then get the classified and 
  misclassified values and print them.
*/

public class TweetMain
{
 public static void main(String[] args)
 {
   TweetCategorizer i = new TweetCategorizer();
   i.inputReader();

   try
   {
   i.buildTables(i.getPositiveWords(), i.getNegativeWords());
   }
   catch (Exception e)
   {
     System.out.println("Caught exception" + e);
   }

   try
   {
   i.tweetParse();
   }
   catch (Exception e)
   {
     System.out.println("Caught exception" + e);
   }

   System.out.println("Correctly Classified: " + i.getClassified());
   System.out.println("Incorrectly Classified: " + i.getMisclassified());
 }
}

