package hu.elte.NewReddit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.elte.NewReddit.model.enums.UserRole;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reddit_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	@Column(name = "registration_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date registrationDate;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
			orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Comment> comments;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
			orphanRemoval = true, fetch = FetchType.LAZY)
	private List<RedditPost> posts;

	@JsonIgnore
	@ManyToMany(mappedBy = "owners")
	private Set<Subreddit> ownedSubbredits;

	@JsonIgnore
	@ManyToMany(mappedBy = "subscribers")
	private Set<Subreddit> subscribedSubbredits;

}
