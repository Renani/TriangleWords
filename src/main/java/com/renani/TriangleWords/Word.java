package com.renani.TriangleWords;

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
	 * [...]So an integer x is triangular if and only if 8x + 1 is a square
	 * https://en.wikipedia.org/wiki/Triangular_number
	 * 
	 * @return
	 */
	public boolean isTriangleWord1() {
		boolean isTriangle=false;
		int candidate = wordValue * 8 + 1;
		if ( Math.sqrt(candidate)%1==0) {
			isTriangle=true;
		}
		return isTriangle;
	}
	
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

}
