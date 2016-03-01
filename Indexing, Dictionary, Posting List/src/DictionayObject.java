package Project_Part_2;

import java.util.ArrayList;
import java.util.List;

public class DictionayObject {

	long documentCount;
	long offset;
	List<PostingObject> postingObjects = new ArrayList<PostingObject>();

	public List<PostingObject> getPostingObjects() {
		return postingObjects;
	}

	public void setPostingObjects(List<PostingObject> postingObjects) {
		this.postingObjects = postingObjects;
	}

	public DictionayObject(long docCount, long off) {
		this.documentCount = docCount;
		this.offset = off;
	}

	public long getDocumentCount() {
		return documentCount;
	}

	public void setDocumentCount(long documentCount) {
		this.documentCount = documentCount;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return documentCount +"||"+offset;
	}

}
