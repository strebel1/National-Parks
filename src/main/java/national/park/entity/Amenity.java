package national.park.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data

public class Amenity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long amenityId;
	@Column(name= "amenityId")
	private String amenityType;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade =CascadeType.PERSIST)
	@JoinColumn(name = "park_id")
	
	private Park park; 
	
}