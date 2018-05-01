package com.test.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * This class returns a Iterator which allows iteration over the provided
 * iterator and skips the objects which don't pass IObjectTest test method
 * 
 * @author Milind
 *
 * @param <E>
 */
public class FilteringIterator<E> implements Iterator<E> {
	private Iterator<E> myIterator;
	private IObjectTest<E> myTest;
	private E nextObject;

	/**
	 * Parameterized constructor for FilteringIterator
	 * 
	 * @param myIterator
	 *            - Iterator<E>
	 * @param myTest
	 *            - IObjectTest<E>
	 */
	public FilteringIterator(Iterator<E> myIterator, IObjectTest<E> myTest) {
		this.myIterator = Objects.requireNonNull(myIterator);
		this.myTest = Objects.requireNonNull(myTest);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns true if the iteration has more elements. (In other words, returns
	 * true if next would return an element rather than throwing an exception.)
	 *
	 * @return true if the iteration has more elements
	 */
	@Override
	public boolean hasNext() {
		if (nextObject == null) {
			fetchNext();
		}
		return nextObject != null;
	}

	/**
	 * Returns the next element in the iteration.
	 *
	 * @return the next element in the iteration
	 * @throws NoSuchElementException
	 *             if the iteration has no more elements
	 */
	@Override
	public E next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		E retObject = nextObject;
		nextObject = null;
		return retObject;
	}

	private void fetchNext() {
		nextObject = null;
		while (myIterator.hasNext()) {
			E obj = myIterator.next();
			if (myTest.test(obj)) {
				nextObject = obj;
				break;
			}
		}
	}
}
