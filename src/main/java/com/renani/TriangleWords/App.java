package com.renani.TriangleWords;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {

	private static final Pattern COMMA_DELIMITER = Pattern.compile(",");
	private static Logger LOG = LogManager.getLogger(App.class);

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	public void run(String... args) throws Exception {
		try {
			List<Word> wordsFromFile = getWordsFromFile(args[0]);
			Map<Integer, List<Word>> collect = wordsFromFile.stream().filter(e->e.isTriangleWord()).collect(
					Collectors.groupingBy(wrd->wrd.findTriangleNumber().get()));
			collect.keySet().stream().sorted().forEach(e->{
				List<Word> list = collect.get(e);
				LOG.debug("KEY " + e);
				list.stream().forEach(w->LOG.debug("Current word " +w));
				LOG.debug("KEY END " + e);
			});
			
		}catch(FileNotFoundException e){
			LOG.error("File could not be read. Exiting ", e);
			System.exit(1);//Expecting this error could occur
		}catch (Throwable e) {
			LOG.error("Unexpected critical error", e);
			System.exit(-1);//shit oh shit.. what went wrong?
		}
		
		
		
		
	}

	private List<Word> getRecordFromLine(String line) {
		List<Word> values = new ArrayList<Word>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(COMMA_DELIMITER);
			while (rowScanner.hasNext()) {
				values.add(new Word(rowScanner.next()));
			}
		}
		return values;
	}

	private List<Word> getWordsFromFile(String fileName) throws FileNotFoundException {
		List<List<Word>> records = new ArrayList<List<Word>>();
		
		try (Scanner scanner = new Scanner(new File(fileName));) {
			while (scanner.hasNextLine()) {
				records.add(getRecordFromLine(scanner.nextLine()));
			}
		}
		 return  records.stream().flatMap(Collection::stream).toList();
	}

}