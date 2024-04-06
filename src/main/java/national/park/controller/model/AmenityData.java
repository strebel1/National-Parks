package national.park.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import national.park.entity.Amenity;

@Data
@NoArgsConstructor
public class AmenityData {
	private Long amenityId;
	private String amenityType;
	
	public AmenityData(Amenity amenity) {
		amenityId = amenity.getAmenityId();
		amenityType= amenity.getAmenityType();

}
	
	
}
