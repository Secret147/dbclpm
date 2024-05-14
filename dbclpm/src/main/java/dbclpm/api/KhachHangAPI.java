package dbclpm.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbclpm.entity.BacDien;
import dbclpm.entity.KhachHang;
import dbclpm.repository.BacDienRepo;
import dbclpm.repository.KhachHangRepo;
import jakarta.persistence.EntityManager;

@RestController
@CrossOrigin
@RequestMapping("/khachhang")
public class KhachHangAPI {
	@Autowired
	private KhachHangRepo khachhangRepo;
	@Autowired
	private EntityManager entityManager;
//	private final BacDienRepo bacDienRepo;

//	public BacDienAPI(BacDienRepo bacDienRepo) {
//		this.bacDienRepo = bacDienRepo;
//	}
	@GetMapping("/api/khach-hang")
	public ResponseEntity<List<KhachHang>> getDSKhachHang() {
		List<KhachHang> dsKH = khachhangRepo.findAll();

		return ResponseEntity.ok(dsKH);
	}

}
