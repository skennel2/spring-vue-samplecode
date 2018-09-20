package org.almansa.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

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

	@ManyToOne
	private User writer;

	public Post(String subject, String contents, User writer) {
		super();
		this.subject = subject;
		this.contents = contents;
		this.writer = writer;
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
