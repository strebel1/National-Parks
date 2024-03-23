package national.park.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import national.park.controller.model.VisitorData;
import national.park.service.ParkService;

@RestController
@RequestMapping("/park")
@Slf4j
public class ParkController {
	@Autowired
	private ParkService parkService;
	@PostMapping("/visitor")
	@ResponseStatus(code =HttpStatus.CREATED)
	public VisitorData insertVisitor(
			@RequestBody VisitorData visitorData) {
		log.info("Creating visitor{}", visitorData);
		return parkService.saveVisitor(visitorData);
	}
	
	@PutMapping("/visitor/{visitorId}")
	public VisitorData updateVisitor (@PathVariable Long visitorId,
			@RequestBody VisitorData visitorData) {
		visitorData.setVisitorId(visitorId);
		log.info("Updating visitor {}", visitorData);
		return parkService.saveVisitor(visitorData);
	}
			
	@GetMapping("/visitor")
	public List<VisitorData> retrieveAllVisitors(){
		log.info("Retrieve all visitors called.");
		return parkService.retrieveAllVisitors();
	
	}
	@GetMapping ("/visitor/{visitorId}")
	public VisitorData retrieveVisitorById(@PathVariable Long visitorId) {
		log.info("Retrieving visitor with ID={}", visitorId);
		return parkService.retrieveVisitorById(visitorId);
		
	}
	
	@DeleteMapping("/visitor/{visitorId}")
	public Map<String, String> deleteVisitorById(
			@PathVariable Long visitorId){
		log.info("Deleting visitor with ID ={}", visitorId);
		parkService.deleteVisitorById(visitorId);
		return Map.of("message", "Deletion of visitor with ID=" + visitorId + " was successful.");
		
}
	
	@DeleteMapping("/visitor")
	public void deleteAllVisitors() {
	log.info("Attempting to delete all contributors");
	throw new UnsupportedOperationException(
			"Deleting all contributors is not allowed");	
	}
}
