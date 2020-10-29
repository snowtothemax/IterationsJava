import java.util.Iterator;
import java.util.function.Function;

/**
 * Class for an InfiniteIterator object of generic type that iterates given a certain function.
 * 
 * @author Max Johnson
 *
 * @param <T> general type object
 */
public class InfiniteIterator<T> implements Iterator<T> {
  // instance fields
  T generic; // generic type object
  Function<T, T> function; // generic type Function object.
  int count;// counts the amount of iterations

  // constructor
  /**
   * Constructs and InfiniteIterator object
   * 
   * @param generic
   * @param function
   */
  InfiniteIterator(T generic, Function<T, T> function) {
    this.generic = generic;
    this.function = function;
    count = 0;
  }

  // methods

  /**
   * Returns the next object to be iterated.
   * 
   * @return object that is either instantiated or given from the function
   */
  public T next() {
    if (hasNext()) {// checks if hasNext() is true
      if (count == 0) {// checks if count == 0
        count++;// increments count if so
        return generic;// returns the generic object if true;
      }
      count++;// else still increments count
      generic = function.apply(generic);// sets generic equal to what the function returns
      return generic;// returns the generic object
    }
    return null;// else returns null
  }

  /**
   * Checks if there is a next object to be iterated
   * 
   * @return true
   */
  public boolean hasNext() {
    return true;
  }
}
