package dbclpm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbclpm.repository.khachHangRepo;
import dbclpm.repository.luongDienTieuThuRepo;
import dbclpm.service.khachHangService;

@RestController
@CrossOrigin
@RequestMapping("/khachhang")
public class khachHangAPI {
	
	@Autowired
	private khachHangService khachHangSe;
	
	@Autowired
	private khachHangRepo khachHangRepo;
	
	@Autowired
	private luongDienTieuThuRepo ldttRe;
	
	

	public khachHangAPI() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> getkh(){
		return ResponseEntity.ok(khachHangRepo.findAll());
	}
	
	@GetMapping("/user/payment")
	public ResponseEntity<?> getUserPayment(){
		return ResponseEntity.ok(khachHangSe.getListKhPayment());
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<?> getUserDetail(@PathVariable Long id){
		return ResponseEntity.ok(ldttRe.findByKhachHangId(id));
	}

}
