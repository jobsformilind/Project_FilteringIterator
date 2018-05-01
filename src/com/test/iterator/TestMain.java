package com.test.iterator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestMain {
	public static void main(String[] args) {
		
		List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
		
		IObjectTest<Integer> test = new IObjectTest<Integer>() {
			public boolean test(Integer obj) {
				return obj % 2 == 0;
			}
		};

		FilteringIterator<Integer> itr = new FilteringIterator<Integer>(list.iterator(), test);
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
	}
}
