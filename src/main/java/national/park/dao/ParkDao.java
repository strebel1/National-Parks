package national.park.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import national.park.entity.Park;

public interface ParkDao extends JpaRepository<Park, Long> {

}
