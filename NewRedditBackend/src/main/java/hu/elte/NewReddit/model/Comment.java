package hu.elte.NewReddit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Comment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	private RedditPost post;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	private User user;

	@Column(name = "text", nullable = true)
	private String text;

	@Column(name = "votes", nullable = true)
	private Integer votes;

	public Comment(Comment other) {
		this.id = other.id;
		this.post = other.post;
		this.user = other.user;
		this.text = other.text;
		this.votes = other.votes;
	}

}
