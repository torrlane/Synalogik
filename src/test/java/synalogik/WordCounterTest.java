package synalogik;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class WordCounterTest {
	
	File synalogik_example = new File("src/test/resources/files/synalogik_example.txt");
	File contraction = new File("src/test/resources/files/contraction.txt");
	File number = new File("src/test/resources/files/number.txt");
	
	File synalogik_example_resut = new File("src/test/resources/results/synalogik_result.txt");
	File contraction_result = new File("src/test/resources/results/contraction_result.txt");
	File number_result = new File("src/test/resources/results/number_result.txt");

	@Test
	public void testWordCounter() throws IOException {
		WordCounter wc = new WordCounter(synalogik_example);
		assertEquals("The files differ!", wc.print(), FileUtils.readFileToString(synalogik_example_resut, "utf-8"));
	}
	
	@Test
	public void testContraction() throws IOException {
		WordCounter wc = new WordCounter(contraction);
		assertEquals("The files differ!", wc.print(), FileUtils.readFileToString(contraction_result, "utf-8"));
	}
	
	@Test
	public void testNumber() throws IOException {
		WordCounter wc = new WordCounter(number);
		assertEquals("The files differ!", wc.print(), FileUtils.readFileToString(number_result, "utf-8"));
	}

}
