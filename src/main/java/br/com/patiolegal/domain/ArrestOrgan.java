package br.com.patiolegal.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "arrestOrgan")
@TypeAlias("arrestOrgan")
public class ArrestOrgan {
	
	@Id
	private String id;
	private String initials;
	private String description;
	
	public String getInitials() {
		return initials;
	}
	
	public String getDescription() {
		return description;
	}

}
