package Project_Part_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractTokens {
	public static List<String> parse(File file) throws IOException {
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);
		StringBuilder builder = new StringBuilder();
		String line;
		String bodyStart = "\\<BODY.*?>";
		String bodyEnd= "\\</BODY>";
		Pattern b1= Pattern.compile(bodyStart);
		Pattern b2=Pattern.compile(bodyEnd);
		boolean body = false;
		while ((line = br.readLine()) != null) {
			if (body == true) {
				builder.append(line);
				builder.append(" ");
			}
			Matcher m1 = b1.matcher(line);
			Matcher m2 = b2.matcher(line);
			if (m1.find()) {
				body = true;
			}
			if (m2.find()) {
				body = false;
			}
		}
		//System.out.println(builder.toString());
		String bodyData = builder.toString().replaceAll("\\<.*?>", "");
		List<String> allWords = new ArrayList<String>();
		String[] tokens = bodyData.split(" ");
		String startsWith = "^\\[|\\(|\"";
		String endsWith = "\\]|\\)|\"|,|\\?|!|\\.$";
		for (String token : tokens) {
			String tokenToAdd = token.toLowerCase();
			tokenToAdd=tokenToAdd.replaceAll(startsWith, "");
			tokenToAdd=tokenToAdd.replaceAll(endsWith, "");
			String[] parts = tokenToAdd.split("-");
			allWords.addAll(Arrays.asList(parts));
		}
		for (String val : allWords) {
			//System.out.println(val);
		}
		fileReader.close();
		br.close();
		return allWords;
	}
}
