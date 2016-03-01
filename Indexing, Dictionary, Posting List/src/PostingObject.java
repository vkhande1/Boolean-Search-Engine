package Project_Part_2;

public class PostingObject {
	private String documentId;
	private long documentWordCount;
	
	public PostingObject(){
	}
	
	public PostingObject(String docName, long docWordCount){
		this.documentId=docName;
		this.documentWordCount=docWordCount;
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
		// TODO Auto-generated method stub
		return documentId +"||"+documentWordCount;
	}

}
