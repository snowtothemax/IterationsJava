import java.util.Iterator;
import java.util.function.Function;

/**
 * A General class
 * 
 * @author Max Johnson
 *
 * @param <T> general type object
 */
public class Generator<T> implements Iterable<T> {
  // Instance variables

  T firstValue;// general object
  Function<T, T> generateNextFromLast;// a general type Function object
  int length;// amount of iterations

  // Constructors
  /**
   * Constructs a Generator object of general type to construct a certain Iterator (Infinite)
   * 
   * @param firstValue
   * @param generateNextFromLast
   */
  public Generator(T firstValue, Function<T, T> generateNextFromLast) {
    this.firstValue = firstValue;
    this.generateNextFromLast = generateNextFromLast;
    length = 0;
  }

  /**
   * Constructs a Generator object of general type to construct a certain Iterator (Finite)
   * 
   * @param firstValue
   * @param generateNextFromLast
   * @param length
   */
  public Generator(T firstValue, Function<T, T> generateNextFromLast, int length) {
    this.firstValue = firstValue;
    this.generateNextFromLast = generateNextFromLast;
    this.length = length;
  }

  // methods

  /**
   * If the length > 0, creates a FiniteIterator with given instance fields, otherwise creates an
   * Infinite Iterator
   * 
   * @return Iterator<T> a general Iterator object.
   */
  public Iterator<T> iterator() {
    InfiniteIterator<T> iterator = new InfiniteIterator<T>(firstValue, generateNextFromLast);// creates
                                                                                             // an
                                                                                             // InfiniteIterator
    if (length == 0) {// checks if instance length is 0
      return iterator;// returns the Infinite Iterator
    }
    return new FiniteIterator<T>(iterator, length);// returns a newly constructed FiniteIterator
                                                   // from the InfiniteIterator
  }
}
