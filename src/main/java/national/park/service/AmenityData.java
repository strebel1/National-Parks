package national.park.service;

import org.springframework.data.jpa.repository.JpaRepository;

import national.park.entity.Amenity;

public class AmenityData implements JpaRepository<Amenity, Long> {

	public AmenityData(Amenity amenity) {
		// TODO Auto-generated constructor stub
	}

}
