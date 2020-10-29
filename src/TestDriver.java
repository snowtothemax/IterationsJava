import java.util.Iterator;
import java.util.function.Function;

/**
 * Class that tests all implemented iteration classes and their methods
 * 
 * @author Max Johnson
 *
 */
public class TestDriver {

  /**
   * Main method that runs all tests
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("testEvenNumbers(): " + testEvenNumbers());
    System.out.println("testPowersOfTwo(): " + testPowersOfTwo());
    System.out.println("testAddExtraSmile(): " + testAddExtraSmile());
    System.out.println("testFiniteIterator(): " + testFiniteIterator());
    System.out.println("testGenerator(): " + testGenerator());
  }

  /**
   * Tests the EvenNumbers class
   * 
   * @return boolean true if works correctly, false otherwise
   */
  public static boolean testEvenNumbers() {
    EvenNumbers it = new EvenNumbers(44);
    if (it.next() != 44) {
      System.out.println("The first call of EvenNumbers.next() "
          + "did not return the value passed into its constructor.");
      return false;
    }
    if (it.next() != 46) {
      System.out.println(
          "The second call of EvenNumbers.next() " + "did not return the smallest even number, "
              + "that is larger than the previously returned number.");
      return false;
    }
    if (it.hasNext() != true) {
      System.out.println("EvenNumbers.next() returned false, " + "but should always return true.");
      return false;
    }
    return true;
  }

  /**
   * Tests the PowersOfTwo Function class
   * 
   * @return boolean true if it works, false otherwise
   */
  public static boolean testPowersOfTwo() {
    InfiniteIterator<Integer> it = new InfiniteIterator<Integer>(8, new NextPowerOfTwo());// defines
                                                                                          // an
                                                                                          // Infinite
                                                                                          // iterator
    if (it.next() != 8) {// checks if the next number to iterate through is correct
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the number passed into its constructor.");
      return false;// returns false and prints an error message if not true
    }
    if (it.next() != 16) {// checks if the next number to iterate through is equal to 16
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the smallest power of two number, "
          + "that is larger than the previously returned number.");
      return false;// returns false and prints an error message if not true
    }
    if (it.hasNext() != true) {// checks to see if hasNext() method returns true
      System.out
          .println("InfiniteIterator.next() returned false, " + "but should always return true.");
      return false;// returns false if hasNext() does not return true and prints an error message
    }
    return true;// returns true if all other tests averted
  }

  /**
   * Tests the AddExtraSmile Function class
   * 
   * @return boolean true if works correctly, false otherwise
   */
  public static boolean testAddExtraSmile() {
    InfiniteIterator<String> it = new InfiniteIterator<>("Hello", new AddExtraSmile());// creates a
                                                                                       // new
                                                                                       // InfiniteIterator
                                                                                       // with
                                                                                       // ExtraSmile
                                                                                       // as the
                                                                                       // Function
    if (!it.next().equals("Hello")) {// checks if the next() returns ""hello"
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the string passed into its constructor.");
      return false;// returns false if not true and prints an error message
    }
    if (!it.next().contains(" :)")) {// checks if the next() returns "Hello"
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the a string with one more :), "
          + "than the previously returned string.");
      return false;// returns false if not true and prints an error message
    }
    if (it.hasNext() != true) {// checks if hasNext() returns false
      System.out
          .println("InfiniteIterator.next() returned false, " + "but should always return true.");
      return false;// this shouldn't happen
    }
    return true;// returns true if all tests passed
  }

  /**
   * Tests the FiniteIterator class
   * 
   * @return boolean true if it works correctly
   */
  public static boolean testFiniteIterator() {
    InfiniteIterator<Integer> infinite = new InfiniteIterator<>(2, new NextPowerOfTwo());// Creates
                                                                                         // an
                                                                                         // InfiniteIterator
    FiniteIterator<Integer> it = new FiniteIterator<>(infinite, 8);// Creates a FiniteIterator
    String s = "";
    while (it.hasNext())// checks if hasNext() returns true and loops if true
      s += " " + it.next();// adds the next String to the String s
    if (!s.equals(" 2 4 8 16 32 64 128 256")) {// checks if s equals the expected string
      System.out.println("Repeatedly called the next() method of a FiniteIterator,"
          + "and the incorrect valuese were returned:" + s);
      return false;// returns false and prints an
    }
    return true;// returns true otherwise
  }

  /**
   * Tests the Generator class
   * 
   * @return
   */
  public static boolean testGenerator() {
    Generator<Integer> gen = new Generator<Integer>(2, new NextPowerOfTwo(), 3);// creates a new
                                                                                // Generator using
                                                                                // the
                                                                                // NextPowerOfTwo
                                                                                // function
    Iterator<Integer> it = gen.iterator();// creates a new iterator using gen iterator() method
    String s = "";
    while (it.hasNext()) {// While loop that loops while hasNext() returns true
      s += " " + it.next();
    }
    if (!s.equals(" 2 4 8")) {// checks if the string equals the expected string
      System.out.println(
          "The FiniteIterator created from the Generator iterator() method returned the incorrect values: "
              + s);
      return false;// returns false and prints an error message if false
    }
    return true;// returns true if all tests are passed
  }
}


/**
 * Class NextPowerOfTwo that implements a Function for Integer objects
 * 
 * @author Max Johnson
 *
 */
class NextPowerOfTwo implements Function<Integer, Integer> {
  @Override
  /**
   * returns the input number previous by 2
   * 
   * @param previous the previous integer iterated that is multiplied by 2
   * @return Integer previous multiplied by 2
   */
  public Integer apply(Integer previous) {
    return 2 * previous;
  }
}


/**
 * Class AddExtraSmile that is a Function that adds a smiley to the input String
 * 
 * @author front
 *
 */
class AddExtraSmile implements Function<String, String> {
  @Override
  /**
   * Adds ":)" to the input string
   * 
   * @param t input string to apply the function to
   * @return String which is the input string with an added ":)"
   */
  public String apply(String t) {
    return t + " :)";
  }
}
