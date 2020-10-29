import java.util.Iterator;

/**
 * Class for a FiniteIterator to iterate through given amount of iterations.
 * 
 * @author Max Johnson
 *
 * @param <T> General class
 */
public class FiniteIterator<T> implements Iterator<T> {
  // Instance fields
  InfiniteIterator<T> iterator;// InfiniteIterator object input to iterate from
  int length;// given amount of iterations
  int count;// counts the amount of iterations

  // constructor
  /**
   * Constructs a FiniteIterator object
   * 
   * @param iterator
   * @param length
   */
  public FiniteIterator(InfiniteIterator<T> iterator, int length) {
    this.iterator = iterator;
    this.length = length;
    count = 0;
  }

  // methods
  /**
   * If hasNext(), increases the count and returns to the iterators next() method
   * 
   * @return T generic object
   */
  public T next() {
    if (hasNext()) {// checks if hasNext() is true
      count++;// increments count
      return iterator.next();// returns the InfiniteIterator's next()
    }
    return null;// else returns null
  }

  /**
   * Checks if the amount of current iterations exceeds the limit of iterations
   * 
   * @return boolean true if count < length, false otherwise
   */
  public boolean hasNext() {
    if (count < length) {// checks if count < length
      return true;// returns true
    }
    return false;// else returns false
  }
}
