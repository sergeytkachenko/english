package ua.com.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the words database table.
 * 
 */
@Entity
@Table(name="words")
@NamedQuery(name="Word.findAll", query="SELECT w FROM Word w")
public class Word implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String title;

	//bi-directional many-to-one association to Leng
	@ManyToOne
	private Leng leng;

	public Word() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Leng getLeng() {
		return this.leng;
	}

	public void setLeng(Leng leng) {
		this.leng = leng;
	}

}