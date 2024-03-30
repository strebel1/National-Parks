package national.park.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import national.park.entity.Amenity;
import national.park.entity.GeoLocation;
import national.park.entity.Park;
import national.park.entity.Visitor;

@Data
@NoArgsConstructor
public class ParkData {
	private Long parkId;
	private String parkName;
	private String parkState;
	private GeoLocation geoLocation;
	private Set<String> amenities = new HashSet<>();
	
	public ParkData(Park park) {
		parkId = park.getParkId();
		parkName= park.getParkName();
		parkState= park.getParkState();
		geoLocation= park.getGeoLocation();
		
		for(Amenity amenity: park.getAmenities()) {
			amenities.add(amenity.getAmenityType());
		}

	}

	@Data
	@NoArgsConstructor
	public static class ParkVisitor{
		private Long visitorId; 
		private String visitorEmail;
		private String visitorName;
		
		public ParkVisitor(Visitor visitor) {
			visitorId =visitor.getVisitorId();
			visitorName = visitor.getVisitorName();
			visitorEmail= visitor.getVisitorEmail();
		
			
		}
	}
}
