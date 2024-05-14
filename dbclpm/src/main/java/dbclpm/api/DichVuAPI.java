package dbclpm.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbclpm.entity.BacDien;
import dbclpm.entity.DichVu;
import dbclpm.repository.BacDienRepo;
import dbclpm.repository.DichVuRepo;
import jakarta.persistence.EntityManager;

@RestController
@CrossOrigin
@RequestMapping("/dich-vu")
public class DichVuAPI {
	@Autowired
	private DichVuRepo dichVurepo;
	@Autowired
	private EntityManager entityManager;

	
	@GetMapping("/api/dich-vu")
	public ResponseEntity<List<DichVu>> getDSDichVu() {
		List<DichVu> ds = dichVurepo.findAll();

		return ResponseEntity.ok(ds);
	}

}
