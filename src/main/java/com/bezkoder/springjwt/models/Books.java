package com.bezkoder.springjwt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Books {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "books")
	private String book;

	@Column(name = "author")
	private String author;

//    @Column(name = "category_id")
//    private String categoryId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", columnDefinition = "id", insertable = false, updatable = true)
	private Category category;

	public Books(Long id, String book, String author, Category category) {
		this.id = id;
		this.book = book;
		this.author = author;
		this.category = category;
	}

	public Books() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

//    public String getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(String categoryId) {
//        this.categoryId = categoryId;
//    }

	@Override
	public String toString() {
		return "Books{" + "id=" + id + ", book='" + book + '\'' + ", author='" + author + '\'' + ", category="
				+ category + '}';
	}
}
