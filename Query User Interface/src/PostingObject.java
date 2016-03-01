package IR_Project_part_3;

public class PostingObject {
	private String documentId;
	private long documentWordCount;

	public PostingObject() {
	}

	public PostingObject(String docName, long docWordCount) {
		this.documentId = docName;
		this.documentWordCount = docWordCount;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public long getDocumentWordCount() {
		return documentWordCount;
	}

	public void setDocumentWordCount(long documentWordCount) {
		this.documentWordCount = documentWordCount;
	}

	@Override
	public String toString() {

		return documentId + "||" + documentWordCount;
	}

}
