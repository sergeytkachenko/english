package ua.com.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the words_description database table.
 * 
 */
@Entity
@Table(name="words_description")
@NamedQuery(name="WordsDescription.findAll", query="SELECT w FROM WordsDescription w")
public class WordsDescription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String description;

	private String title;

	public WordsDescription() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}