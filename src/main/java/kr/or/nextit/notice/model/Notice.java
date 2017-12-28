package kr.or.nextit.notice.model;

public class Notice {
	private int notice_seq_no;
	private String title;
	private String name;
	private String content;
	private String reg_date;
	private String uploadfile;

	public Notice() {}

	public int getNotice_seq_no() {
		return notice_seq_no;
	}

	public void setNotice_seq_no(int notice_seq_no) {
		this.notice_seq_no = notice_seq_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(String uploadfile) {
		this.uploadfile = uploadfile;
	}
	
	
	
}
