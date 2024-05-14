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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dbclpm.entity.BacDien;
import dbclpm.entity.PhieuThanhToan;
import dbclpm.repository.BacDienRepo;
import dbclpm.repository.PhieuThanhToanRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
@RestController
@CrossOrigin
@RequestMapping("/phieu-thanh-toan")
public class PhieuThanhToanAPI {
//	@Autowired
//	private PhieuThanhToanRepo phieuThanhToanRepo;
	@Autowired
	private EntityManager entityManager;
	@Transactional
	@PostMapping("/api/phieu-thanh-toan")
	public ResponseEntity<String> addNewPhieuThanhToan(@RequestBody PhieuThanhToan request, @RequestParam("id") Long id) {
	    System.out.println(request);
	    String insertQuery = "INSERT INTO phieu_thanh_toan (date, total_mount, khachhang_id) VALUES (:date, :totalMount, :khachhangId)";
	    Query query = entityManager.createNativeQuery(insertQuery);
	    query.setParameter("date", request.getDate());
	    query.setParameter("totalMount", request.getTotalMount());
	    query.setParameter("khachhangId", id);

	    try {
	        query.executeUpdate();
	        Query idQuery = entityManager.createNativeQuery("SELECT MAX(id) FROM phieu_thanh_toan");
            Long maxId = (Long) idQuery.getSingleResult();
            System.out.println(maxId);
            return ResponseEntity.status(HttpStatus.CREATED).body(maxId.toString());
	       
	        		
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi thêm phiếu thanh toán mới");
	    }
	}
	
	@GetMapping("/api/phieu-thanh-toan/{khachhangId}")
	public ResponseEntity<List<PhieuThanhToan>> getAllPhieuThanhToanByKhachHangId(@PathVariable Long khachhangId) {
	    try {
	        // Tạo truy vấn để lấy tất cả các phiếu thanh toán cho khách hàng cụ thể
	        String queryStr = "SELECT * FROM phieu_thanh_toan WHERE khachhang_id = :khachhangId";
	        Query query = entityManager.createNativeQuery(queryStr, PhieuThanhToan.class);
	        query.setParameter("khachhangId", khachhangId);

	        // Thực hiện truy vấn
	        List<PhieuThanhToan> phieuThanhToanList = query.getResultList();

	        // Trả về danh sách các phiếu thanh toán
	        return ResponseEntity.ok().body(phieuThanhToanList);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
	@Transactional
	@DeleteMapping("/api/phieu-thanh-toan/{id}")
	public ResponseEntity<String> deleteBacDien(@PathVariable("id") Long idToDelete) {
		
		
		// Xây dựng câu lệnh SQL để xóa các dòng trong các bảng khác có khóa ngoại trỏ đến bảng bac_dien
		String deleteRelatedData = "DELETE FROM dich_vu_phieu_thanh_toan WHERE ptt_id = :id";
		Query deleteRelatedDataQuery = entityManager.createNativeQuery(deleteRelatedData);
		deleteRelatedDataQuery.setParameter("id", idToDelete);
		int relatedRowsAffected = deleteRelatedDataQuery.executeUpdate();

		// Kiểm tra số dòng đã bị ảnh hưởng bởi câu lệnh DELETE
		if (relatedRowsAffected >= 0) {
		    // Nếu có dòng nào bị ảnh hưởng, tiếp tục xóa bản ghi từ bảng bac_dien
		    Query deletePhieuThanhToanQuery = entityManager.createNativeQuery("DELETE FROM phieu_thanh_toan WHERE id = :id");
		    deletePhieuThanhToanQuery.setParameter("id", idToDelete);
		    int phieuThanhToanRowsAffected = deletePhieuThanhToanQuery.executeUpdate();

		    if (phieuThanhToanRowsAffected > 0) {
		        return ResponseEntity.ok("All related records and bac_dien record deleted successfully");
		    } else {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting bac_dien record");
		    }
		} else {
		    // Nếu không có dòng nào bị ảnh hưởng, trả về lỗi
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting related records");
		}

		
	}
}
