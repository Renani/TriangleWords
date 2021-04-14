package com.renani.TriangleWords;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.google.common.collect.Lists;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class WordTest {
	
	private static Logger LOG = getLogger(WordTest.class);
/**
 * Test if sum of  alphabeting order is calculated correctly 
 */
	@Test
	void testCalculateWordValue() {
		Word word = new Word("aaa");
		assertEquals(3, word.getWordValue());
		 word = new Word("ZZZ");
		 assertEquals(26*3, word.getWordValue());
		 
		 word = new Word("aBc");
		 assertEquals(1+2+3, word.getWordValue());

		 word = new Word("SKY");
		 assertEquals(55, word.getWordValue());
	}
	
	/**
	 * Testing with triangle numbers.
	 */
	@Test
	void TestIsTriangleWord () {
		Word word = new Word("SKY");
		assertTrue(word.isTriangleWord());
		

		ArrayList<Integer> triangleNumbers = Lists.newArrayList(1, 3, 6, 10, 15, 21, 28, 36, 45, 55);
		
		List<String> triangleWords = triangleNumbers.stream().map(e->createWordFromSum(e)).collect(Collectors.toList());
		
		triangleWords.stream().forEach(e->assertIfWordIsTriangle(e));
	}
	/**
	 * Testing with array of non-trianglenumbers
	 */
	@Test
	void TestIsTriangleIsNOTAWord(){
		Word word = new Word ("TKY");//Is there a triangle word where its sum is one more than closest previous triangle word? 
		assertFalse(word.isTriangleWord(),"T=S+1");
		
		ArrayList<Integer> triangleNumbers = Lists.newArrayList(2,4,5,7,8,9,11,12,13,14,16);
		List<String> triangleWords = triangleNumbers.stream().map(e->createWordFromSum(e)).collect(Collectors.toList());
		
		triangleWords.stream().forEach(e->assertIfWordIsNotTriangle(e));
		
	}
 /**
  * Creating random "words" from String.
  * 
  * @param sumValue
  * @return
  */
	private String createWordFromSum( double sumValue) {
		int totLetters = Word.ASCII_END-Word.ASCII_OFFSET;
		StringBuffer buffer = new StringBuffer();
		 
		 while(sumValue+Word.ASCII_OFFSET>Word.ASCII_END) {
			 int randomChar = ((int)(Math.random()*(totLetters-1)))+1;
			 randomChar = (int) Math.min(randomChar,sumValue);
			 
			 buffer.append((char) (randomChar+Word.ASCII_OFFSET));
			 sumValue-=randomChar;
		 }
		 
		 if (sumValue>0) {
			 buffer.append((char) (sumValue+Word.ASCII_OFFSET));
		 }
		 
		 return buffer.toString();
	}
	
	private void assertIfWordIsTriangle(String variable) {
		Word word = new Word(variable);
		assertTrue(word.isTriangleWord(), "This word is NOT Triangle " + word.getValue()  + ". ");
	}
	
	private void assertIfWordIsNotTriangle(String variable) {
		Word word = new Word(variable);
		assertFalse(word.isTriangleWord(), "This word IS triangle " + word.getValue()  + ". ");
	}

}
