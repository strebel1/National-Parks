package national.park.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import national.park.controller.model.VisitorData;
import national.park.dao.VisitorDao;
import national.park.entity.Visitor;

@Service
public class ParkService {

	private VisitorDao visitorDao;

	
	@Transactional(readOnly = false)
	public VisitorData saveVisitor(VisitorData visitorData) {
		Long visitorId = visitorData.getVisitorId();
		Visitor visitor = findOrCreateVisitor(visitorId, visitorData.getVisitorEmail());

		setFieldsInVisitor(visitor, visitorData);
		return new VisitorData(visitorDao.save(visitor));
	}

	private void setFieldsInVisitor(Visitor visitor, VisitorData visitorData) {
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

}
