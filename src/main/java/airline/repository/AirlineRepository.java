package airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
import airline.main_package.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Integer> {

}
