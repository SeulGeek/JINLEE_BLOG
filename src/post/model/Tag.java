package post.model;

public class Tag {

	private Integer tagId;
	private String tagName;
	private int tagSearchNum;
	
	public Tag(Integer tagId, String tagName, int tagSearchNum) {
		this.tagId = tagId;
		this.tagName = tagName;
		this.tagSearchNum = tagSearchNum;
	}
	
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public int getTagSearchNum() {
		return tagSearchNum;
	}
	public void setTagSearchNum(int tagSearchNum) {
		this.tagSearchNum = tagSearchNum;
	}
	
	

}
