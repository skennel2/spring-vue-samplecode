package org.almansa.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 30)
	private String subject;

	@Column
	@Lob
	private String contents;

	public Post(String subject, String contents) {
		super();
		this.subject = subject;
		this.contents = contents;
	}
	
	protected Post() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
