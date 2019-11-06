/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "POST_CONTENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostContent implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CONTENT_ID")
	private Long id;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_ID", nullable = false, referencedColumnName = "POST_ID")
	private RedditPost post;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "TEXT_CONTENT", nullable = true)
	private String text;

	@Column(name = "IMAGE_URL", nullable = true)
	private String imageUrl;

}
