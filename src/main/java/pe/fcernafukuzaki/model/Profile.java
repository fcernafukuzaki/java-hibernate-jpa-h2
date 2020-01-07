package pe.fcernafukuzaki.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROFILE")
public class Profile implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "uid_profile")
	private Long uid_profile;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid_user")
	private User user;
	
	/* This constructor is used by default in Hibernate */
	public Profile() {
		
	}
	
	public Profile(Long uid_profile, String name, User user) {
		this.uid_profile = uid_profile;
		this.name = name;
		this.user = user;
	}

	public Long getUid_profile() {
		return uid_profile;
	}
	public void setUid_profile(Long uid_profile) {
		this.uid_profile = uid_profile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Profile [uid_profile=" + uid_profile + ", name=" + name + ", uid_user=" + user.getUid() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((uid_profile == null) ? 0 : uid_profile.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (uid_profile == null) {
			if (other.uid_profile != null)
				return false;
		} else if (!uid_profile.equals(other.uid_profile))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}
