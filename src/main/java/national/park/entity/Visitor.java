package national.park.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data

public class Visitor {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	
	private Long visitorId; 
	
	@Column(unique = true)
	private String visitorEmail;
	
	
	private String visitorName;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "visitor", cascade = CascadeType.PERSIST)
	private Set<Park> parks = new HashSet<>();

}
