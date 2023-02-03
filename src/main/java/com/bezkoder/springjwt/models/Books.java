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

import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Books {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "author")
	private String author;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", columnDefinition = "id", insertable = false, updatable = true)
	private Category category;

//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "user_id", columnDefinition = "id", insertable = false, updatable = true)
//	private User user;

	@Column(name = "created_at", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createat;

}
