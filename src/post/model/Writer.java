package post.model;

public class Writer {

	private Integer writerId;
	private String writerNick;
	
	public Writer(Integer writerId, String writerNick) {
		this.writerId = writerId;
		this.writerNick = writerNick;
	}

	public Integer getWriterId() {
		return writerId;
	}

	public void setWriterId(Integer writerId) {
		this.writerId = writerId;
	}

	public String getWriterNick() {
		return writerNick;
	}

	public void setWriterNick(String writerNick) {
		this.writerNick = writerNick;
	}

	

}
