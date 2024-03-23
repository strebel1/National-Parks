package national.park.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import national.park.entity.Visitor;

public interface VisitorDao extends JpaRepository<Visitor, Long> {

Optional<Visitor> findByVisitorEmail(String visitorEmail);

}
