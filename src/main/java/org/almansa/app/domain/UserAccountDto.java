package org.almansa.app.domain;

import java.util.Date;

public class UserAccountDto {
	private Long id;

	private String email;

	private Date creationDate;

	public UserAccountDto(Long id, String email, Date creationDate) {
		this.id = id;
		this.email = email;
		this.creationDate = creationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
