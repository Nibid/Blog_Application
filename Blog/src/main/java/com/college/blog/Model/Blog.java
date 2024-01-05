package com.college.blog.Model;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="Blogs")
public class Blog {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	private String content;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String thumbnail;
	private String author;
	private String category;
	private String tags;
	private String timestamps;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String images;
	
	//Default Constructor
	public Blog() {
		super();
	}
	
	//Parameterized Constructor
	public Blog(int id, String title, String content, String thumbnail, String author, String category,
			String tags, String timestamps, String images) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.thumbnail = thumbnail;
		this.author = author;
		this.category = category;
		this.tags = tags;
		this.timestamps = timestamps;
		this.images = images;
	}

	//Getters and Setters
	public List<String> getImages() {
		return Arrays.asList(images);
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getTags() {
		return Arrays.asList(tags.split(","));
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getTimestamps() {
		return timestamps;
	}

	public void setTimestamps(String timestamps) {
		this.timestamps = timestamps;
	}

	//String toString
	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", content=" + content + ", thumbnail=" + thumbnail + ", author="
				+ author + ", category=" + category + ", tags=" + tags + ", timestamps=" + timestamps + ", getId()="
				+ getId() + ", getTitle()=" + getTitle() + ", getContent()=" + getContent() + ", getThumbnail()="
				+ getThumbnail() + ", getAuthor()=" + getAuthor() + ", getCategory()=" + getCategory() + ", getTags()="
				+ getTags() + ", getTimestamps()=" + getTimestamps() + ", getImages()=" + getImages() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
