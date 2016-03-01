package IR_Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HTMLParserOptimized {
	public static void main(String[] args) throws IOException {
		for (int i = 0; i < args.length; i++) {
			File file = new File(args[i]);
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			StringBuilder builder = new StringBuilder();
			String line;
			boolean body = false;
			while ((line = br.readLine()) != null) {
				if (body == true) {
					builder.append(line);
					builder.append(" ");
				}
				if (line.matches("\\<BODY.*?>")) {
					body = true;
				}
				if (line.matches("\\</BODY>")) {
					body = false;
				}
			}
			String bodyData = builder.toString().replaceAll("\\<.*?>", "");
			Set<String> allWords = new HashSet<String>();
			String[] tokens = bodyData.split(" ");
			String startsWith = "^\\[|\\(|\"";
			String endsWith = "\\]|\\)|\"|,|\\?|!|\\.$|:|";
			for (String token : tokens) {
				String tokenToAdd = token.toLowerCase();
				tokenToAdd = tokenToAdd.replaceAll(startsWith, "");
				tokenToAdd = tokenToAdd.replaceAll(endsWith, "");
				String[] parts = tokenToAdd.split("-");
				allWords.addAll(Arrays.asList(parts));
			}
			List<String> allWordList = new ArrayList<String>(allWords);
			Collections.sort(allWordList);
			for (String val : allWordList) {
				PrintStream console = System.out;
				File file1 = new File("output" + i + ".txt");
				FileOutputStream fos = new FileOutputStream(file1, true);
				PrintStream ps = new PrintStream(fos);
				System.setOut(ps);
				System.out.println(val);
			}
			fileReader.close();
			br.close();
		}
	}
}
