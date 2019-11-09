package hu.elte.NewReddit.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subbreddits")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
class Subreddit implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "subreddit_id")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column
	@ManyToMany
	@JoinTable(
			joinColumns = @JoinColumn(name = "subreddit_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> owners;

	@Column
	@ManyToMany
	@JoinTable(
			joinColumns = @JoinColumn(name = "subreddit_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> subscribers;

}
