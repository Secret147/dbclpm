package dbclpm.api;

import java.util.List;

import dbclpm.service.BacDienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import dbclpm.entity.BacDien;
import dbclpm.repository.BacDienRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Transactional
@RestController
@CrossOrigin
@RequestMapping("/bacdien")
public class BacDienAPI {
	@Autowired
	private BacDienRepo bacDienRepo;
	@Autowired
	private BacDienService bacDienService;
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
    public void setBacDienRepo(BacDienRepo bacDienRepo) {
        this.bacDienRepo = bacDienRepo;
    }
	@Autowired
	public void setEntityManager(EntityManager entityManager) {
	    this.entityManager = entityManager;
	}
//	private final BacDienRepo bacDienRepo;

//	public BacDienAPI(BacDienRepo bacDienRepo) {
//		this.bacDienRepo = bacDienRepo;
//	}
	/**
	 * Lấy danh sách bậc điện hiện tại
	 * 
	 * @return
	 */
	@GetMapping("/api/bac-dien")
	public ResponseEntity<List<BacDien>> getDSBacDien() {
		List<BacDien> dsBacDien = bacDienRepo.findAll();

		return ResponseEntity.ok(dsBacDien);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllBacDien(){
		List<BacDien> bacDiens = bacDienService.getAllBacDien();
		return ResponseEntity.ok(bacDiens);
	}

	@DeleteMapping("/api/bac-dien/{id}")
	 public ResponseEntity<?> deleteBacDien(@PathVariable long id) {
        boolean isDeleted = bacDienService.deleteBacDien(id);

        if (isDeleted) {
            return ResponseEntity.ok("Record deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Cannot delete record because it is not the latest one");
        }
    }
	@Transactional
	@PostMapping("/api/bac-dien")
	public ResponseEntity<?> addNewBacDien(@RequestBody BacDien request) {
	    boolean isAdded = bacDienService.addNewBacDien(request);
	    if (isAdded) {
	        return ResponseEntity.status(HttpStatus.CREATED).body("Bậc mới đã được thêm");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi thêm bậc mới");
	    }
	}

	
	@Transactional
	@PutMapping("/api/bac-dien/{id}")
	public ResponseEntity<String> updateBacDien(@RequestBody BacDien request) {
		  BacDien currentBacDien = bacDienService.findBacDienById(request.getId());
	        BacDien previousBacDien = bacDienService.findPreviousBacDien(currentBacDien.getStartValue());
	        BacDien nextBacDien = bacDienService.findNextBacDien(currentBacDien.getEndValue());

	        boolean isUpdated = bacDienService.validateAndUpdateBacDien(currentBacDien, previousBacDien, nextBacDien, request);
	        if (isUpdated) {
	            return ResponseEntity.ok("Bậc đã được cập nhật");
	        } else {
	            return ResponseEntity.badRequest().body("Các ràng buộc không hợp lệ hoặc có lỗi khi cập nhật");
	        }
	}
}
