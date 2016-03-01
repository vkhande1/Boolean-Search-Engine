package Project_Part_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractTitles {

	public static String getTitle(File file) throws IOException {
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);
		StringBuilder builder = new StringBuilder();
		String line;
		String titleStart = "\\<TITLE.*?>";
		String titleEnd = "\\</TITLE>";
		Pattern b1 = Pattern.compile(titleStart);
		Pattern b2 = Pattern.compile(titleEnd);
		boolean title = false;
		while ((line = br.readLine()) != null) {

			Matcher m1 = b1.matcher(line);
			Matcher m2 = b2.matcher(line);
			if (m1.find()) {
				title = true;
			}
			if (title == true) {
				builder.append(line);
				builder.append(" ");
			}
			if (m2.find()) {
				title = false;
			}
		}
		// System.out.println(builder.toString());
		String titleData = builder.toString().replaceAll("\\<.*?>", "");
		fileReader.close();
		br.close();
		return titleData;
	}
}