package com.truecaller.demo.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "User_Details")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Users implements Serializable {

	private static final long serialVersionUID = -7839660508630997566L;

	@NotNull(message = "{user.name.null}")
	@NotEmpty(message = "{user.name.empty}")
	@Size(max = 20, min = 3, message = "{user.name.size}")
	@Column(name = "user_name")
	private String name;

	@NotNull(message = "{user.number.null}")
	@Id
	@Column(name = "phone_number")
	private Long phoneNumber;
	
	@ElementCollection
	@Column(name = "personal_contacts")
	private List<Long> personalContacts=new ArrayList<Long>();
	
	@Pattern(message = "{user.email.pattern.message}", regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
	@Column(name = "email_id")
	private String emailId;
}
