package national.park.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import national.park.controller.model.ParkData;
import national.park.controller.model.VisitorData;
import national.park.dao.AmenityDao;
import national.park.dao.ParkDao;
import national.park.dao.VisitorDao;
import national.park.entity.Amenity;
import national.park.entity.Park;
import national.park.entity.Visitor;

@Service
public class ParkService<AmenityType> {
@Autowired
	private VisitorDao visitorDao;

@Autowired
private AmenityDao amenityDao;

@Autowired 
private ParkDao parkDao;

	
	@Transactional(readOnly = false)
	public VisitorData saveVisitor(VisitorData visitorData) {
		Long visitorId = visitorData.getVisitorId();
		Visitor visitor = findOrCreateVisitor(visitorId, visitorData.getVisitorEmail());

		setFieldsInVisitor(visitor, visitorData);
		return new VisitorData(visitorDao.save(visitor));
	}

	private void setFieldsInVisitor(Visitor visitor, VisitorData visitorData) {
		visitor.setVisitorId(visitorData.getVisitorId());
		visitor.setVisitorName(visitorData.getVisitorName());
		visitor.setVisitorEmail(visitorData.getVisitorEmail());
	}

	private Visitor findOrCreateVisitor(Long visitorId, String visitorEmail) {
		Visitor visitor;

		if (Objects.isNull(visitorId)) {
			Optional<Visitor> opVisitor = 
					visitorDao.findByVisitorEmail(visitorEmail);
			
			if (opVisitor.isPresent()) {
				
			throw new DuplicateKeyException("Visitor with email" + visitorEmail +" already exists.");
			}
			
			visitor = new Visitor();
			
		}

		else {
			visitor = findVisitorById(visitorId);
		}

		return visitor;
	}

	private Visitor findVisitorById(Long visitorId) {
		return visitorDao.findById(visitorId)
				.orElseThrow(() -> new NoSuchElementException("Visitor with ID=" + visitorId + "was not found."));
	}

	@Transactional(readOnly = true)
	public List<VisitorData> retrieveAllVisitors() {
		List<Visitor> visitors = visitorDao.findAll();
		List<VisitorData> response = new ArrayList<>();

		for (Visitor visitor : visitors) {
			response.add(new VisitorData(visitor));
		}
		return response;
	}

	@Transactional(readOnly =true)
	public VisitorData retrieveVisitorById(Long visitorId) {
		Visitor visitor = findVisitorById(visitorId);
		return new VisitorData(visitor);
	}
	
	@Transactional(readOnly = false)
	public void deleteVisitorById(Long visitorId) {
		Visitor visitor = findVisitorById(visitorId);
		visitorDao.delete(visitor);
		
	}
	

	@Transactional(readOnly= false)
	public ParkData savePark(Long visitorId, 
			ParkData parkData) {
	Visitor visitor = findVisitorById(visitorId);
		
	Park park = findOrCreatePark(visitorId, parkData.getParkId());
	setParkFields(park, parkData);
	
	
	park.getVisitor().add(visitor);
	visitor.getParks().add(park);

	
	for(Amenity amenity: amenity) {
		amenity.setPark(park);
		park.getAmenities().add(amenity);
	}
	Park dbPark =parkDao.save(park);
	return new ParkData(dbPark);
	}

	private void setParkFields(Park park, ParkData parkData) {
		park.setParkId(parkData.getParkId());
		park.setParkName(parkData.getParkName());
		park.setParkState(parkData.getParkState());
		park.setGeoLocation(parkData.getGeoLocation());
		
	}

	private Park findOrCreatePark(Long visitorId, Long parkId) {
		Park park;
		if(Objects.isNull(parkId)) {
			park= new Park();
		}
		
		else {
			
			park = findParkById(parkId);
	
		}
		
		return park;
	}

	private Park findParkById(Long parkId) {
		return parkDao.findById(parkId).orElseThrow(() -> new NoSuchElementException(
				"Park with ID=" + parkId + " does not exist." ));
	}
	
	@Transactional(readOnly=true)
	public List<AmenityType> retrieveAllAmenities(){
		List<AmenityType> Amenities = null;
		try {
			Amenities = retrieveAllAmenities();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  List<AmenityType> result = new LinkedList<>();
		
		for(AmenityType amenityType: Amenities) {
			Amenity adata = new Amenity();
		
			adata.getAmenities();
			
			result.add(amenityType);
		}
		
		
	return Amenities;
	
	}

}
