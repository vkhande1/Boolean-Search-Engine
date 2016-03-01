package Project_Part_2;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostingCreator {

	public static Map<String, Integer> tokenMap(File file) throws IOException {
		List<String> tokens = ExtractTokens.parse(file);
		Map<String, Integer> tokenCount = new HashMap<String, Integer>();
		for (String tokenString : tokens) {
			if (tokenCount.containsKey(tokenString)) {
				tokenCount.put(tokenString, tokenCount.get(tokenString) + 1);
			} else {
				tokenCount.put(tokenString, 1);
			}
		}

		for (Map.Entry<String, Integer> entry : tokenCount.entrySet()) {
			// System.out.println(entry.getKey() + " " + entry.getValue());
		}
		return tokenCount;
	}

}
