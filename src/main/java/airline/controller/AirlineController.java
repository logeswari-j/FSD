package airline.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import airline.exceptionHandling.ResourceNotFoundException;
import airline.main_package.Airline;
import airline.repository.AirlineRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class AirlineController {

	@Autowired
	private AirlineRepository airlineRepository;

	@GetMapping("/saveDetails")
	public List<Airline> getAllAirline() {
		return airlineRepository.findAll();
	}

	@PostMapping("/readAllDetails")
	public Airline createDetails(@RequestBody Airline Details) {
		return airlineRepository.save(Details);
	}

	@GetMapping("/readDetails/{id}")
	public ResponseEntity<Airline> getAirlineById(@PathVariable int id) {
		Airline Details = airlineRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Airline not exist with id :" + id));
		return ResponseEntity.ok(Details);
	}

	@PutMapping("/updateDetails/{id}")
	public ResponseEntity<Airline> updateDetails(@PathVariable int id, @RequestBody Airline updateDetails) {
		Airline airline = airlineRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Airline not exist with id :" + id));

		airline.setAirlineName(updateDetails.getAirlineName());
		airline.setSource(updateDetails.getSource());
		airline.setDestination(updateDetails.getDestination());
		airline.setSeatingCapacity(updateDetails.getSeatingCapacity());
		airline.setCost(updateDetails.getCost());

		Airline updatedDetails = airlineRepository.save(airline);
		return ResponseEntity.ok(updatedDetails);
	}

	@DeleteMapping("/deleteDetails/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteDetails(@PathVariable int id) {
		Airline airline = airlineRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Airline not exist with id :" + id));

		airlineRepository.delete(airline);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted successfully", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
