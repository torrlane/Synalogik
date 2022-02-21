package synalogik;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class WordCounter {

	private static final String UTF8 = "UTF-8";
	private static final String DIGIT_REGEX = ".*\\d.*";
	private static final String SPECIAL_CHAR_REGEX = "[^a-zA-Z0-9&/]";

	private Map<Integer, Integer> countMap;
	private long wordCount = 0; // use long for safety incase of very large files
	private double charCount = 0;
	private double avgLength;
	
	public WordCounter(File file) {
		try {
			countMap = fileWordCount(file);
		} catch (FileNotFoundException e) {
			System.out.println("File Not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Exception");
			e.printStackTrace();
		}

		avgLength = charCount / wordCount;
	}

	private Map<Integer, Integer> fileWordCount(File file) throws FileNotFoundException, IOException {
		Map<Integer, Integer> countMap = new HashMap<>();
		LineIterator iterator = FileUtils.lineIterator(file, UTF8);
		try {
			while (iterator.hasNext()) {
				String line = iterator.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(line);
				while (tokenizer.hasMoreTokens()) {
					wordCount++; // increase word count
					String word = tokenizer.nextToken();
					// only remove non-letter, number, &, and / characters if the word isn't a
					// number
					if (!word.matches(DIGIT_REGEX)) {
						word = word.replaceAll(SPECIAL_CHAR_REGEX, "");
					}
					int length = word.length();
					charCount += length; // increase character count for calculating average word length
					Integer lengthCount = countMap.get(length);
					if (lengthCount == null) {
						countMap.put(length, 1);
					} else {
						countMap.put(length, lengthCount + 1);
					}

				}
			}
		} finally {
			iterator.close();
		}
		return countMap;
	}

	public String print() {
		int mostFrequentLength = 0;
		Set<Integer> mostFrequentWord = new HashSet<Integer>(); 
		
		DecimalFormat format = new DecimalFormat("0.###");
		
		StringBuilder sb = new StringBuilder();
		sb.append("Word count = " + wordCount + System.getProperty("line.separator"));
		sb.append("Average word length = " + format.format(avgLength) + System.getProperty("line.separator"));

		for (Integer length : countMap.keySet()) {
			int lengthCount = countMap.get(length);
			sb.append("Number of words of length " + length + " is " + lengthCount + System.getProperty("line.separator"));
			if(lengthCount == mostFrequentLength) {
				mostFrequentWord.add(length);
			}else if(lengthCount > mostFrequentLength) {
				mostFrequentLength = lengthCount;
				mostFrequentWord.clear();
				mostFrequentWord.add(length);
			}
			
		}
		sb.append("The most frequently occurring word length is " + mostFrequentLength + ", for word lengths of ");
		
		Iterator<Integer> length = mostFrequentWord.iterator();
		while(length.hasNext()) {
			sb.append(length.next());
			if(length.hasNext()) {
				sb.append(" & ");
			}
		}

		return sb.toString();
	}

}
