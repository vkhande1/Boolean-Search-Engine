package Project_Part_2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class IndexCreator {

	private static Map<String, DictionayObject> dictionary = new HashMap<String, DictionayObject>();
	// private static Map<String, Integer> dictionaryDocumentCount = new
	// HashMap<String, Integer>();
	private static List<PostingObject> postingTable = new ArrayList<PostingObject>();
	private static Map<String, String> docTitle = new HashMap<String, String>();
	private static long offset = 0;
	private static String docName;
	private static String title;

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < args.length; i++) {
			File directory = new File(args[i]);
			File[] files = directory.listFiles();
			for (File file : files) {
				docName = file.getName();
				if (docName.indexOf(".") > 0) {
					docName = docName.substring(0, docName.lastIndexOf("."));}
				title = ExtractTitles.getTitle(file);
				docTitle.put(docName, title);
				Map<String, Integer> docTokenCount = PostingCreator
						.tokenMap(file);
				for (Map.Entry<String, Integer> entry : docTokenCount
						.entrySet()) {
					// System.out.println(entry.getKey() + " " +
					// entry.getValue());
					if (dictionary.containsKey(entry.getKey())) {
						DictionayObject obj = dictionary.get(entry.getKey());
						obj.setDocumentCount(obj.getDocumentCount() + 1);
						List<PostingObject> postList = obj.getPostingObjects();
						postList.add(getPostingObject(entry));
						obj.setPostingObjects(postList);
						dictionary.put(entry.getKey(), obj);
					} else {
						DictionayObject obj = new DictionayObject(1, 0);
						List<PostingObject> postList = new ArrayList<PostingObject>();
						postList.add(getPostingObject(entry));
						obj.setPostingObjects(postList);
						dictionary.put(entry.getKey(), obj);
					}

					// if (dictionaryDocumentCount.containsKey(entry.getKey()))
					// {
					// dictionaryDocumentCount.put(entry.getKey(),
					// (dictionaryDocumentCount.get(entry.getKey()) + 1));
					// } else {
					// dictionaryDocumentCount.put(entry.getKey(), 1);
					// }
				}

			}

			dictionary.remove("");
			dictionary.remove(" ");

			for (Map.Entry<String, DictionayObject> entry : dictionary
					.entrySet()) {
				List<PostingObject> docList = entry.getValue()
						.getPostingObjects();
				entry.getValue().setOffset(offset);
				for (PostingObject obj : docList) {
					postingTable.add(obj);
					offset++;
				}
			}
			// System.out.println(dictionaryDocumentCount);
			// System.out.println(docTitle);
			// System.out.println(dictionary);
			// System.out.println(postingTable);
			createIndex();

		}
	}

	private static void createIndex() throws IOException {
		File titleFile = new File("DocsTable.txt");
		File dictionaryFile = new File("Dictionary.txt");
		File postingFile = new File("Postings.txt");
		FileWriter f1 = new FileWriter(titleFile);
		FileWriter f2 = new FileWriter(dictionaryFile);
		FileWriter f3 = new FileWriter(postingFile);

		BufferedWriter b1 = new BufferedWriter(f1);
		BufferedWriter b2 = new BufferedWriter(f2);
		BufferedWriter b3 = new BufferedWriter(f3);

		for (Entry<String, String> entry : docTitle.entrySet()) {
			b1.write(entry.getKey() + "||" + entry.getValue());
			b1.newLine();
		}

		for (Entry<String, DictionayObject> entry : dictionary.entrySet()) {
			b2.write(entry.getKey() + "||" + entry.getValue().toString());
			b2.newLine();
		}

		for (PostingObject po : postingTable) {
			b3.write(po.toString());
			b3.newLine();
		}

		b1.close();
		b2.close();
		b3.close();

		f1.close();
		f2.close();
		f3.close();

	}

	private static PostingObject getPostingObject(
			Map.Entry<String, Integer> entry) {
		PostingObject postObj = new PostingObject();
		postObj.setDocumentId(docName);
		long documentWordCount = entry.getValue();
		if (title.contains(entry.getKey())) {
			documentWordCount++;
		}
		postObj.setDocumentWordCount(documentWordCount);
		return postObj;
	}
}
