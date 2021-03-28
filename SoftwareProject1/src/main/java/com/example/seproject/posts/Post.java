package com.example.seproject.posts;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.seproject.accounts.Account;

@Entity
@Table(name = "T_POST")
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	public static Long nextId = 0L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;

	protected String subject;

	@Column(name = "body")
	protected String body;
	
	protected static Long getNextId() {
		synchronized (nextId) {
			return nextId++;
		}
	}

	public Post(){}

	public Post(String subject, String body) {
		this.id = getNextId();
		this.subject = subject;
		this.body = body;
	}

	public Long getId() {
		return this.id;
	}

	protected void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return this.subject;
	}

	protected void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return this.body;
	}

	protected void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return this.subject + " [" + this.body + "]: $";
	}

}