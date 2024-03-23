package national.park.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Park {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long parkId;
	private String parkName;
	private String parkState;
	
	@Embedded
	private GeoLocation geoLocation;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy= "park", cascade = CascadeType.ALL)
	@JoinColumn(name = "park_id", nullable = false)
	private Set<Amenity> amenities = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "park_visitor", 
			joinColumns = @JoinColumn (name = "visitor_id"),
			inverseJoinColumns =@JoinColumn (name = "park_id"))
	private Set<Visitor> visitor = new HashSet<>();
	
	
	
	
	

}
