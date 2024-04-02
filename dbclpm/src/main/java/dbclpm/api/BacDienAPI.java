package dbclpm.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbclpm.entity.BacDien;
import dbclpm.repository.BacDienRepo;

@RestController
@CrossOrigin
@RequestMapping("/bacdien")
public class BacDienAPI {

	private final BacDienRepo bacDienRepo;

	public BacDienAPI(BacDienRepo bacDienRepo) {
		this.bacDienRepo = bacDienRepo;
	}
	/**
	 * Lấy danh sách bậc điện hiện tại
	 * @return
	 */
	@GetMapping("/api/bac-dien")
	public ResponseEntity<List<BacDien>> getDSBacDien() {
		List<BacDien> dsBacDien = bacDienRepo.findAll();
		return ResponseEntity.ok(dsBacDien);
	}

}
