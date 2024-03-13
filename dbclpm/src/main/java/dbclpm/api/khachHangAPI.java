package dbclpm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbclpm.repository.khachHangRepo;

@RestController
@CrossOrigin
@RequestMapping("/khachhang")
public class khachHangAPI {
	
	@Autowired
	private khachHangRepo khachHangRepo;
	
	

	public khachHangAPI() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> getkh(){
		return ResponseEntity.ok(khachHangRepo.findAll());
	}

}
