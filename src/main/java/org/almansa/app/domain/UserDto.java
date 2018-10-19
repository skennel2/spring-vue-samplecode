package org.almansa.app.domain;

public class UserDto {
	private Long id;

	private String email;

	public UserDto(Long id, String email) {
		this.id = id;
		this.email = email;
	}

	public UserDto(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
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

}
