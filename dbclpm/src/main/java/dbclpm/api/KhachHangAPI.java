package dbclpm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbclpm.repository.KhachHangRepo;
import dbclpm.repository.LuongDienTieuThuRepo;
import dbclpm.service.KhachHangService;

@RestController
@CrossOrigin
@RequestMapping("/khachhang")
public class KhachHangAPI {
	
	@Autowired
	private KhachHangService khachHangSe;
	
	@Autowired
	private KhachHangRepo khachHangRepo;
	
	@Autowired
	private LuongDienTieuThuRepo ldttRe;
	
	

	public KhachHangAPI() {
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
