
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: A Program that iterates through a given amount of Wikipedia pages inputted by the user
//////////////////// from a certain input topic
// Files: Generator.java, FiniteIterator.java, InfiniteIterator.java, EvenNumbers.java,
//////////////////// TestDriver.java
// Course: CS 300, Spring, 2019
//
// Author: Maxwell Johnson
// Email: mkjohnson9@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NONE
// Partner Email: NONE
// Partner Lecturer's Name: NONE
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.io.IOException;
import java.util.Scanner;
import java.util.function.Function;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * A class that implements the Function interface with a function that clicks the first link on a
 * Wiki page.
 * 
 * @author Max Johnson
 *
 */
public class NextWikiLink implements Function<String, String> {

  @Override
  /**
   * Clicks the first link on a wiki page and returns it's address.
   * 
   * @param String t input Wiki address
   * @return String address of next wiki page.
   */
  public String apply(String t) {
    try {
      // Download a Wikipedia page, using t in their internal link format: /wiki/Some_Subject
      Document doc = Jsoup.connect("https://en.wikipedia.org" + t).get();
      // Use .css selector to retrieve a collection of links from this page's description
      // "p a" selects links within paragraphs
      // ":not([title^='Help'])" skips pronunciations
      // ":not(sup a)" skips citations
      Elements links = doc.select("p a:not([title^='Help']):not(sup a)");
      // return the link attribute from the first element of this list
      return links.get(0).attr("href");
      // Otherwise return an appropriate error message:
    } catch (IOException | IllegalArgumentException e) {
      return "FAILED to find wikipedia page: " + t;
    } catch (IndexOutOfBoundsException e) {
      return "FAILED to find a link in wikipedia page: " + t;
    }
  }

  /**
   * The main driver method for the application, which iterates through wikipedia pages for a given
   * amount of pages from the user.
   * 
   * @param args
   */
  public static void main(String[] args) {
    String topic = "";// the topic to be inputed by the user
    int length = 0;// the length to be inputed by the user
    Scanner sc = new Scanner(System.in);// creates a new scanner
    System.out.println("Please enter a Wikipedia topic: ");
    topic = sc.nextLine();// inputs the wikipedia topic
    System.out.println("Please enter the number of Wiki pages to step through: ");
    length = sc.nextInt();// inputs the amount of iterations


    String newTopic = "";
    for (int i = 0; i < topic.length(); i++) {
      if (Character.isWhitespace(topic.charAt(i))) {// iterates through the input topic and changes
                                                    // all whitespace to "_"
        newTopic += "_";
      } else {
        newTopic += topic.charAt(i);
      }
    }

    int count = 0;// a counter to be used to count amount of outputs for an unfound topic
    newTopic = "/wiki/" + newTopic;
    Generator<String> gen = new Generator<String>(newTopic, new NextWikiLink(), length);// creates a
                                                                                        // new
                                                                                        // Generator
                                                                                        // to create
                                                                                        // a
                                                                                        // FiniteIterator
                                                                                        // of String
                                                                                        // type
    for (String s : gen) {// enhanced for-loop to iterate through the iterable generator
      if (s.startsWith("FAILED") && count < 1) {// checks if the next topic was a failed
        System.out.println(s);// prints the error message
        count++;
      } else if (!s.startsWith("FAILED")) {// else
        System.out.println(s);// prints the next wiki topic and address
      }
    }
    sc.close();// closes the scanner
  }
}
