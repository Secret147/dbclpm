package dbclpm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbclpm.service.HoaDonService;

@RestController
@CrossOrigin
@RequestMapping("/hoadon")
public class HoaDonAPI {
	@Autowired
	private HoaDonService hoaDonSe;

	public HoaDonAPI() {
		// TODO Auto-generated constructor stub
	}
	@GetMapping("/total")
	public ResponseEntity<?> getTotal(){
		return ResponseEntity.ok(hoaDonSe.getTotal() );
	}
	
	@GetMapping("/bill")
	public ResponseEntity<?> getListBill(){
		return ResponseEntity.ok(hoaDonSe.getListHoaDon() );
	}
	
	

}
