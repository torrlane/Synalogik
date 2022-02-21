package synalogik;

import java.io.File;

public class SynalogikExercise {

	private static final String NO_PARAM_ERROR = "Please provide a file location";

	public static void main(String[] args) {
		if (args.length == 0 || args[0] == null || args[0] == "") {
			System.out.print(NO_PARAM_ERROR);
		} else {
			File file = new File(args[0]);
			WordCounter wordCounter = new WordCounter(file);
			System.out.print(wordCounter.print());
		}

	}
}
