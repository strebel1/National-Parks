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
public class VisitorData {

	private Long visitorId;
	private String visitorEmail;
	private String visitorName;
	private Set<ParkResponse> parks = new HashSet<>();

	public VisitorData(Visitor visitor) {
		visitorName = visitor.getVisitorName();
		visitorEmail = visitor.getVisitorEmail();

		for (Park park : visitor.getParks()) {
			parks.add(new ParkResponse(park));
		}

	}

	@Data
	@NoArgsConstructor
	static class ParkResponse {

		
		private Long parkId;
		private String parkName;
		private String parkState;
		private GeoLocation geoLocation;
		private Visitor visitorId;
		private Set<String> amenities = new HashSet<>();

		ParkResponse(Park park) {
			parkId = park.getParkId();
			parkName =park.getParkName();
			geoLocation = new GeoLocation(park.getGeoLocation());

			for (Amenity amenity : park.getAmenities()) {
				amenities.add(amenity.getAmenityType());
			}
		}
		

	}
	
	@Data
	@NoArgsConstructor
	
	static class AmenityData{
		private Long amenityId;
		private String amenityType;
	AmenityData(Amenity amenity){
		amenityId = amenity.getAmenityId();
		amenityType= amenity.getAmenityType();
	}
	}
}
