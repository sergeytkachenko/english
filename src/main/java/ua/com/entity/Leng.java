package ua.com.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lengs database table.
 * 
 */
@Entity
@Table(name="lengs")
@NamedQuery(name="Leng.findAll", query="SELECT l FROM Leng l")
public class Leng implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String prefix;

	//bi-directional many-to-one association to Word
	@OneToMany(mappedBy="leng")
	private List<Word> words;

	public Leng() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public List<Word> getWords() {
		return this.words;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}

	public Word addWord(Word word) {
		getWords().add(word);
		word.setLeng(this);

		return word;
	}

	public Word removeWord(Word word) {
		getWords().remove(word);
		word.setLeng(null);

		return word;
	}

}