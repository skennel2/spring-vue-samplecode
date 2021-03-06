package org.almansa.app.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Comment extends EntityBase {

	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	private UserAccount writer;

	@Lob
	private String contents;

	public Comment(Post post, UserAccount writer, String contents) {
		super();
		this.post = post;
		this.writer = writer;
		this.contents = contents;
	}

	protected Comment() {
		super();
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
