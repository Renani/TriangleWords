package com.renani.TriangleWords;

import java.util.Optional;

public class Word {
	private final String value;
	public static final int ASCII_OFFSET = 96;
	public static final int ASCII_END =122;
	private final int wordValue;
	private final double triangleNumber;
	
	public Word(String value) {
		this.value = value.toLowerCase();
		this.wordValue = calculateWordValue();
		this.triangleNumber = calculateTriangleNumber();
	}

	public String getValue() {
		return new String(value);
	}

	/**
	 * Sums up the alphabetic values
	 * 
	 */
	private int calculateWordValue() {
		return value.chars().reduce(0, (s, e) -> s + e - ASCII_OFFSET);
	}

 
	/**
	 * if the Triangle number is an integer, then it is a triangleword.
	 * @return
	 */
	public boolean isTriangleWord() {
		return this.calculateTriangleNumber()%1==0;
	}

	public int getWordValue() {
		return this.wordValue;
	}
	/**
	 *  [...]if the positive triangular root n of x is an integer, then x is the nth triangular number.
	 *  https://en.wikipedia.org/wiki/Triangular_number
	 * @return
	 */
	private double calculateTriangleNumber() {
		return (Math.sqrt(wordValue*8 +1)-1)/2;
	}
	
	/**
	 * The triangle number can be non-existent. 
	 * We mark this by naming the method with "find" instead of "get" and return an optional.
	 * We cannot assume the word will be tested for whether it is triangeword or not
	 * 
	 * @return
	 */
	public Optional<Integer> findTriangleNumber() {
		if(isTriangleWord()) {
			return Optional.of( (int) triangleNumber);
		}else {
			return Optional.ofNullable(null);
		}
	}

}
