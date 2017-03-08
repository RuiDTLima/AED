package series.serie2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Iterables {

	public static <T> Iterable<T> flatten( final Iterable< Iterable<T> > src) {
        return new Iterable<T>() {

            // Iterator's fields
            //
            private T current;

            private Iterator<T> currIterator;
            /**
             * Current iterator to phrases collection
             */
            private Iterator<Iterable<T>> it = src.iterator();
            /**
             * Current iterable
             */
            private Iterable<T> currIterable;
            /**
             * Variable used in the hasNext method
             */
            private boolean consumed = true;
            private boolean end = false;
            private boolean consumedIterable = true;

            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    @Override
                    public boolean hasNext() {
                        if(end) return false;
                        if(consumed) {
                            while (!consumedIterable || it.hasNext()){
                                if(consumedIterable){
                                    currIterable = it.next();
                                    currIterator = currIterable.iterator();
                                    consumedIterable = false;
                                }
                                while (currIterator.hasNext()) {
                                    current = currIterator.next();
                                    consumed = false;
                                    return true;
                                }
                                consumedIterable = true;
                            }
                            end = true;
                            return false;
                        }
                        else return true;
                    }

                    @Override
                    public T next() {
                        if (!hasNext()) throw new NoSuchElementException();
                        consumed = true;
                        return current;
                    }
                };
            }
        };
	}



	public static  Iterable<Integer> alternateEvenOdd(final Iterable<Integer> src){
        return new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    // Fields
                    //
                    /**
                     * Iterator to the given iterable
                     */
                    Iterator<Integer> it = src.iterator();
                    /**
                     * Variables used in the hasNext method
                     */
                    private boolean consumed = true;
                    private boolean end = false;
                    private boolean odd = true;
                    /**
                     *
                     */
                    Integer i;

                    @Override
                    public boolean hasNext() {
                        if (end) return false;
                        if (consumed) {
                            // Get next integer if it exists one
                            while (it.hasNext()) {
                                // Get next integer
                                i = it.next();
                                if (i % 2 == ((odd) ? 1 : 0)) {
                                    consumed = false;
                                    return true;
                                }
                            }
                            end = true;
                            return false;
                        } else
                            // Next integer exists but it was not consumed yet, so return true
                            return true;
                    }

                    @Override
                    public Integer next() {
                        if (!hasNext()) throw new NoSuchElementException("alternateEvenoOdd: no more elements");
                        odd = !odd;
                        // Set this integer to the state 'consumed'
                        consumed = true;
                        // Return integer
                        return i;
                    }

                    public void remove(){
                        throw new UnsupportedOperationException("alternateEvenoOdd: remove not supported");
                    }
                };
            }
        };
    }
}

