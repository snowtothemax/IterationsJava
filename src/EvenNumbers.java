import java.util.Iterator;

/**
 * Class of EvenNumbers Object that implements an Iterator of Integer type.
 * 
 * @author front
 *
 */
public class EvenNumbers implements Iterator<Integer> {
  // instance fields
  Integer num;
  int count;

  // constructor
  /**
   * Constructs and EvenNumbers object
   * 
   * @param num number to start iterating from
   */
  EvenNumbers(Integer num) {
    this.num = num;
    count = 0;
  }

  // methods
  /**
   * Iterates to the next even integer from the current integer.
   */
  public Integer next() {
    if (hasNext()) {// checks if hasNext() is true
      if (num % 2 != 0) {// checks if the current number is even or not
        num += 1;// increments by one if that is the case
      }
      if (count != 0) {// then checks the count is equal to 0 or not
        num += 2;// sets num equal to the next even number
      }
      count++;
      return num;
    }
    count++;
    return -1;
  }

  /**
   * Checks if there is a next iterable number
   * 
   * @return true
   */
  public boolean hasNext() {
    return true;
  }
}
