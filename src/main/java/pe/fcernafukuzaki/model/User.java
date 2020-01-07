package pe.fcernafukuzaki.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "uid_user", unique = true)
	private Long uid;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "registered_date")
	private LocalDate registeredDate;
	
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "uid_credential")
	private UserCredential userCredential;
	
	@OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private List<Profile> profiles = new ArrayList<Profile>();

	/* This constructor is used by default in Hibernate */
	public User() {

	}

	public User(Long uid, String name, boolean active, LocalDate registeredDate) {
		this.uid = uid;
		this.name = name;
		this.active = active;
		this.registeredDate = registeredDate;
	}

	public User(long uid, String name, boolean active, LocalDate registeredDate, 
			UserCredential userCredential) {
		this.uid = uid;
		this.name = name;
		this.active = active;
		this.registeredDate = registeredDate;
		this.userCredential = userCredential;
	}
	
	public User(long uid, String name, boolean active, LocalDate registeredDate, 
			UserCredential userCredential, List<Profile> profiles) {
		this.uid = uid;
		this.name = name;
		this.active = active;
		this.registeredDate = registeredDate;
		this.userCredential = userCredential;
		this.profiles = profiles;
	}
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDate getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(LocalDate registeredDate) {
		this.registeredDate = registeredDate;
	}

	public UserCredential getUserCredential() {
		return userCredential;
	}

	public void setUserCredential(UserCredential userCredential) {
		this.userCredential = userCredential;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}
	
	public void addProfile(Profile profile) {
		if(!profiles.contains(profile)) {
			profiles.add(profile);
			profile.setUser(this);
		}
	}
	
	public void removeProfile(Profile profile) {
		if(profiles.contains(profile)) {
			profiles.remove(profile);
			profile.setUser(null);
		}
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", active=" + active + ", registeredDate=" + registeredDate
				+ ", userCredential=" + userCredential + ", profiles=" + profiles + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((profiles == null) ? 0 : profiles.hashCode());
		result = prime * result + ((registeredDate == null) ? 0 : registeredDate.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + ((userCredential == null) ? 0 : userCredential.hashCode());
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
		User other = (User) obj;
		if (active != other.active)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (profiles == null) {
			if (other.profiles != null)
				return false;
		} else if (!profiles.equals(other.profiles))
			return false;
		if (registeredDate == null) {
			if (other.registeredDate != null)
				return false;
		} else if (!registeredDate.equals(other.registeredDate))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (userCredential == null) {
			if (other.userCredential != null)
				return false;
		} else if (!userCredential.equals(other.userCredential))
			return false;
		return true;
	}

}
