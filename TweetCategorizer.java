package assign_1.Assign_1;
import java.io.*;
import java.util.Scanner;
import java.util.*;

/**
  @author Jesse Shinol
 The purpose of this class is to read in the data from each file provided: 
positive-tweets.txt,negative-tweets.txt, and the csv file. It will also parse 
all the data in the files required for sentiment analysis.The set of 
tweets to be classified, will be also given as an input to the program in a 
CSV (comma separated values) file: testdata.manual.2009.06.14.csv
*/


public class TweetCategorizer
{
  private String positiveWords;
  private String negativeWords;
  private String testData;
  HashSet<String> positive = new HashSet<String>();
  HashSet<String> negative = new HashSet<String>();
  private int classified = 0;
  private int misclassified = 0;

  

/**
 * inputReader will read in the users input from console, and store the file
 * paths as a String to be used later in the program.
 */
  public void inputReader()
  {
    Scanner scan = new Scanner(System.in);
    System.out.println("Provide the positive words file path.");
    positiveWords = scan.nextLine();
         

    System.out.println("Provide the negative words file path.");
    negativeWords = scan.nextLine();
        

    System.out.println("Provide the twitter data file path.");
    testData = scan.nextLine();
       
    scan.close();
  }

/**
 * buildTable will receive file paths and populate two HashSets.
 * @param  good    file path for positive words.
 * @param  bad    file path for negative words.
 * @throws Exception   general exceptions
 * @throws IOException input/output exceptions
 */
  public void buildTables(String good, String bad) throws Exception, IOException
  {
   
      File goodFile = new File(good);
      Scanner scan = new Scanner(goodFile);
      while (scan.hasNextLine())
      {
        positive.add(scan.nextLine());
      }
      scan.close();
      
      File badFile = new File(bad);
      scan = new Scanner(badFile);
      while(scan.hasNextLine())
      {
          negative.add(scan.nextLine());
      } 
      scan.close();
    
  }

/**
 * tweetParse reads in the csv file line by line. Each line is separated into
 * individual strings. These elements are then categorized as 
 * positive, negative, or neutral.
 * @throws Exception   general exception
 * @throws IOException input/output exceptions.
 */
  public void tweetParse() throws Exception, IOException
  {
   
    File tweets = new File(testData);
    
    String tweetLine = null;
    String tweetValue = null;
    int negCounter = 0;
    int posCounter = 0;
    int j = 0;

    Scanner scan = new Scanner(tweets);
    while (scan.hasNextLine())
    {
      tweetLine = scan.nextLine();
      String[] strArray = tweetLine.split("\",\"");
      strArray[0] = strArray[0].replaceAll("\"", "");
      String text_Processed = strArray[5].replaceAll("\\p{Punct}","").toLowerCase();
      String[] words = text_Processed.split("\\s+");

      j = 0;
      while (j < words.length)
      {
        if (positive.contains(words[j]))
        {
          posCounter++;
        }
        if (negative.contains(words[j]))
        {
          negCounter++;
        }
        j++;
      }
      

      if (strArray[0].equals("0"))
        tweetValue = "Negative";
      if (strArray[0].equals("2"))
        tweetValue = "Neutral";
      if (strArray[0].equals("4"))
        tweetValue = "Positive";

      if ((posCounter > negCounter) && (posCounter + negCounter > 0))
      {
        System.out.println("Tweet below is predicted to be positive.");
        System.out.println(text_Processed);
        System.out.println("Tweet value is " + tweetValue + ".");
        System.out.println();
        

        if (tweetValue.equals("Positive"))
          classified++;
        else
          misclassified++;
      }
      if ((negCounter >= posCounter) && (posCounter + negCounter > 0))
      {
        System.out.println("Tweet below is predicted to be negative.");
        System.out.println(text_Processed);
        System.out.println("Tweet value is " + tweetValue + ".");
        System.out.println();
        if (tweetValue.equals("Negative"))
          classified++;
        else
          misclassified++;
      }
      if (negCounter + posCounter == 0)
      {
        System.out.println("Tweet below is predicted to be neutral");
        System.out.println(text_Processed);
        System.out.println("Tweet value is " + tweetValue + ".");
        System.out.println();
        if (tweetValue.equals("Neutral"))
          classified++;
        else
          misclassified++;
      } 
      
      posCounter = 0;
      negCounter = 0;
    }
    scan.close();
  }

  /**
   * standard getter for table
   * @return the positiveWords table
   */
  public String getPositiveWords()
  {
    return positiveWords;
  }
  /**
   * standard getter for table
   * @return the negativeWords table
   */
  public String getNegativeWords()
  {
    return negativeWords;
  }
  /**
   * standard getter for classified counter
   * @return the counter for classified tweets
   */
  public int getClassified()
  {
    return classified;
  }
  /**
   * standard getter for misclassified counter
   * @return the misclassified tweets
   */
  public int getMisclassified()
  {
    return misclassified;
  }

}


    

