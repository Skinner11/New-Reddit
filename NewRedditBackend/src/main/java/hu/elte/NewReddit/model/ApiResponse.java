package hu.elte.NewReddit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class ApiResponse {

	private int statusCode;
	private String message;
	private String timestamp;

}
