package pe.fcernafukuzaki.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_CREDENTIAL")
public class UserCredential implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "uid_credential", unique = true)
	private Long uid;
	
	@Column(name = "password")
	private String password;

	/* Bidirectional relation */
	@OneToOne(mappedBy = "userCredential", fetch = FetchType.LAZY)
	private User user;
	
	/* This constructor is used by default in Hibernate */
	public UserCredential() {
		
	}

	public UserCredential(Long uid, String password, User user) {
		this.uid = uid;
		this.password = password;
		this.user = user;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserCredential [uid=" + uid + ", password=" + password + ", user=" + user.getUid() + "]";
	}
	
}
