package org.almansa.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Post extends EntityBase{

	@Column(nullable = false, length = 30)
	private String subject;

	@Column
	@Lob
	private String contents;

	@ManyToOne
	private UserAccount writer;

	protected Post() {
		super();
	}
	
	public Post(String subject, String contents, UserAccount writer) {
		super();
		this.subject = subject;
		this.contents = contents;
		this.writer = writer;
	}
	
	public Post(String subject, UserAccount writer, String contents) {
		super();
		this.subject = subject;
		this.contents = contents;
		this.writer = writer;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
