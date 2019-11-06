package hu.elte.NewReddit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "REDDIT_POST")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RedditPost implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "POST_ID")
	private Long id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POSTER_ID", nullable = false)
	private User user;

	@JsonIgnore
	@OneToOne(mappedBy = "post", cascade = CascadeType.ALL,
			orphanRemoval = true, fetch = FetchType.LAZY)
	private PostContent content;

	@JsonIgnore
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL,
			orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Comment> comments;

	@Column(name = "BOO")
	private Long boo;

	@Column(name = "YAAY")
	private Long yaay;
}
