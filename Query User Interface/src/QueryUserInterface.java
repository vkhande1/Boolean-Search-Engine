package IR_Project_part_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class QueryUserInterface {

	private static Map<String, DictionayObject> dictionary = new HashMap<String, DictionayObject>();
	private static List<PostingObject> postingTable = new ArrayList<PostingObject>();
	private static Map<String, String> docTitle = new HashMap<String, String>();

	public static void main(String[] args) throws IOException {
		List<String> queries = new ArrayList<String>();
		System.out.println("Please enter the query terms");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputQuery = null;
		BufferedWriter bf = new BufferedWriter(new FileWriter("Output.txt"));
		do {
			inputQuery = br.readLine();
			queries.add(inputQuery.toLowerCase());
		} while (!inputQuery.equals("exit"));
		queries.remove(queries.size() - 1);
		System.out.println(queries);

		readDictionary();

		for (String query : queries) {
			processQuery(query, bf);
		}

		bf.close();

	}

	private static void processQuery(String query, BufferedWriter bf)
			throws IOException {
		String[] queryTokens = getQueryTokens(query);
		bf.write(query);
		bf.newLine();
		List<String> docTitlesForQuery;
		if (queryTokens[0].equals("and")) {
			docTitlesForQuery = processAndQuery(queryTokens);

		} else if (queryTokens[0].equals("or")) {
			docTitlesForQuery = processOrQuery(queryTokens);
		} else {
			docTitlesForQuery = processSingleWordQuery(queryTokens[0]
					.toLowerCase());
		}
		for (String title : docTitlesForQuery) {
			bf.write(title);
			bf.newLine();
		}
	}

	private static List<String> processAndQuery(String[] queryTokens)
			throws IOException {
		List<List<String>> documentTitleList = getAllDocumentTitleForToken(queryTokens);
		Set<String> andSet = new HashSet<String>();
		for (List<String> docTitleList : documentTitleList) {
			// for (String title : docTitleList) {
			andSet.addAll(docTitleList);
			// }
		}
		for (List<String> docTitleList : documentTitleList) {
			andSet.retainAll(docTitleList);
		}
		return new ArrayList<String>(andSet);
	}

	private static List<String> processOrQuery(String[] queryTokens)
			throws IOException {
		List<List<String>> documentTitleList = getAllDocumentTitleForToken(queryTokens);
		List<String> orList = new ArrayList<String>();
		Set<String> orSet = new HashSet<String>();
		for (List<String> docTitleList : documentTitleList) {
			for (String title : docTitleList) {
				orSet.add(title);
			}
		}
		orList.addAll(orSet);
		return orList;
	}

	private static List<List<String>> getAllDocumentTitleForToken(
			String[] queryTokens) throws IOException {
		List<List<String>> documentTitleList = new ArrayList<List<String>>();
		for (int i = 1; i < queryTokens.length; i++) {
			documentTitleList.add(processSingleWordQuery(queryTokens[i]
					.toLowerCase()));
		}
		return documentTitleList;
	}

	private static List<String> processSingleWordQuery(String queryToken)
			throws IOException {
		DictionayObject queryObj = dictionary.get(queryToken);
		List<String> docName = new ArrayList<String>();
		if (queryObj != null) {
			for (PostingObject pos : queryObj.getPostingObjects()) {
				docName.add(pos.getDocumentId() + "  "
						+ docTitle.get(pos.getDocumentId()));
				
				// System.out.println(pos.getDocumentId());
				// System.out.println(docTitle.get(pos.getDocumentId()));
			}

		} else {docName.add("No Results for this Query");}

		return docName;
	}

	private static String[] getQueryTokens(String query) {
		return query.split(" ");
	}

	private static void readDictionary() throws IOException {
		File titleFile = new File("DocsTable.txt");
		File dictionaryFile = new File("Dictionary.txt");
		File postingFile = new File("Postings.txt");

		BufferedReader br1 = new BufferedReader(new FileReader(dictionaryFile));

		// read Dictionary File
		String line;
		while ((line = br1.readLine()) != null) {
			String[] lineItems = line.split("\\|\\|");
			String token = lineItems[0];
			long docCount = lineItems[1] == " " ? 0 : Long
					.parseLong(lineItems[1]);
			long offset = lineItems[2] == " " ? 0 : Long
					.parseLong(lineItems[2]);
			DictionayObject p = new DictionayObject(docCount, offset);
			dictionary.put(token, p);
		}

		// read Title file

		BufferedReader br2 = new BufferedReader(new FileReader(titleFile));
		line = null;
		while ((line = br2.readLine()) != null) {
			String[] lineItems = line.split("\\|\\|");
			String fileName = lineItems[0];
			String title = lineItems[1];
			docTitle.put(fileName, title);
		}

		// read Posting file
		BufferedReader br3 = new BufferedReader(new FileReader(postingFile));
		line = null;
		while ((line = br3.readLine()) != null) {
			String[] lineItems = line.split("\\|\\|");
			String fileName = lineItems[0];
			Long docWordCount = lineItems[1] == "" ? 0 : Long
					.parseLong(lineItems[1]);
			postingTable.add(new PostingObject(fileName, docWordCount));
		}

		// populate dictionay objects with posting entries
		for (Entry<String, DictionayObject> dicEntry : dictionary.entrySet()) {
			DictionayObject dic = dicEntry.getValue();
			List<PostingObject> dicPosting = new ArrayList<PostingObject>();
			for (long i = dic.getOffset(); i < dic.getDocumentCount()
					+ dic.getOffset(); i++) {
				dicPosting.add(postingTable.get((int) i));
			}
			dic.setPostingObjects(dicPosting);
		}

		br1.close();
		br2.close();
		br3.close();
	}
}
