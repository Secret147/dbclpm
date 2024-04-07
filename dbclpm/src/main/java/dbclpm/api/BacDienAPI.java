package dbclpm.api;

import java.util.List;

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

@RestController
@CrossOrigin
@RequestMapping("/bacdien")
public class BacDienAPI {
	@Autowired
	private BacDienRepo bacDienRepo;
	@Autowired
	private EntityManager entityManager;
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

	@Transactional
	@DeleteMapping("/api/bac-dien/{id}")
	public ResponseEntity<String> deleteBacDien(@PathVariable("id") Long idToDelete) {
        Query getMaxIdQuery = entityManager.createNativeQuery("SELECT MAX(id) AS id FROM bac_dien");
        Long maxId = (Long) getMaxIdQuery.getSingleResult();
        System.out.println("bac cua toi");
        System.out.println(maxId);
        if (idToDelete.equals(maxId)) {
            Query deleteQuery = entityManager.createNativeQuery("DELETE FROM bac_dien WHERE id = :id");
            deleteQuery.setParameter("id", idToDelete);
            int rowsAffected = deleteQuery.executeUpdate();
            if (rowsAffected > 0) {
                return ResponseEntity.ok("Record deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting record");
            }
        } else {
            return ResponseEntity.badRequest().body("Cannot delete record because it is not the latest one");
        }
    }
	@Transactional
	@PostMapping("/api/bac-dien")
	public ResponseEntity<String> addNewBacDien(@RequestBody BacDien request) {
		System.out.println(request);
		String insertQuery = "INSERT INTO bac_dien (price, name, start_value, end_value, description) VALUES ( :price,:name, :startValue, :endValue, :description)";
		Query query = entityManager.createNativeQuery(insertQuery);
		query.setParameter("price", request.getPrice());
		query.setParameter("name", request.getName());
		query.setParameter("startValue", request.getStartValue());
		query.setParameter("endValue", request.getEndValue());
		query.setParameter("description", request.getDescription());

		try {
			query.executeUpdate();
			return ResponseEntity.status(HttpStatus.CREATED).body("Bậc mới đã được thêm");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi thêm bậc mới");
		}
	}
}
