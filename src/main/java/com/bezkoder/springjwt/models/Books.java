package com.bezkoder.springjwt.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Books {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "author")
	private String author;

//    @Column(name = "category_id")
//    private String categoryId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", columnDefinition = "id", insertable = false, updatable = true)
	private Category category;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", columnDefinition = "id", insertable = false, updatable = false)
	@JsonIgnoreProperties({"email","password","roles","blockUser"})
	private User user;

	@Column(name = "created_at", nullable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	private Date createat;

	@Column(name = "update_at", nullable = false, updatable = true)
	@Temporal(TemporalType.DATE)
	private Date updateat;

//	@JsonIgnore
	@JsonIgnoreProperties({"username","email","password","roles","blockUser","id"})
	public void setUser(User user) {
		this.user = user;
	}

	public void setCreateat(Date createat) {
		this.createat = createat;
	}
}
