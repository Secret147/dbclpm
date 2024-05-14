package dbclpm.api;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dbclpm.entity.BacDien;
import dbclpm.entity.DichVuPhieuThanhToan;
import dbclpm.entity.PhieuThanhToan;
import dbclpm.repository.DichVuPhieuThanhToanRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
@RestController
@CrossOrigin
@RequestMapping("/dich-vu-phieu-thanh-toan")
public class DichVuPhieuThanhToanAPI {
	@Autowired
	private DichVuPhieuThanhToanRepo dichVuPhieuThanhToanRepo;
	@Autowired
	private EntityManager entityManager;
	@GetMapping("api/dich-vu-phieu-thanh-toan")
	public ResponseEntity<List<DichVuPhieuThanhToan>> getDs() {
	    try {
	        List<DichVuPhieuThanhToan> dsDichVuPhieuThanhToan = dichVuPhieuThanhToanRepo.findAll();
	        if (dsDichVuPhieuThanhToan.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        }
	        return ResponseEntity.ok(dsDichVuPhieuThanhToan); 
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	@GetMapping("/api/dich-vu-phieu-thanh-toan/{phieuId}")
	public ResponseEntity<List<DichVuPhieuThanhToan>> getAllPhieuThanhToanByKhachHangIdAndPhieuId(@PathVariable Long phieuId) {
	    try {
	        // Tạo truy vấn để lấy tất cả các phiếu thanh toán cho khách hàng và phiếu dịch vụ cụ thể
	        String queryStr = "SELECT * FROM dich_vu_phieu_thanh_toan WHERE ptt_id = :phieuId";
	        Query query = entityManager.createNativeQuery(queryStr, DichVuPhieuThanhToan.class);
	        query.setParameter("phieuId", phieuId);

	        // Thực hiện truy vấn
	        List<DichVuPhieuThanhToan> phieuThanhToanList = query.getResultList();

	        // Trả về danh sách các phiếu thanh toán
	        return ResponseEntity.ok().body(phieuThanhToanList);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
	@Transactional
	@PostMapping("/api/dich-vu-phieu-thanh-toan")
	public ResponseEntity<String> addDichVuPhieuThanhToan(@RequestBody DichVuPhieuThanhToan request, @RequestParam("idPhieu") Long idPhieu, @RequestParam("idDV") Long idDV ) {
		System.out.println(request);
		String insertQuery = "INSERT INTO dich_vu_phieu_thanh_toan (amount, quality, dichvu_id, ptt_id) VALUES (:amount, :quality, :dichvuId, :pttId)";
		Query query = entityManager.createNativeQuery(insertQuery);
		query.setParameter("amount", request.getAmount());
		query.setParameter("quality", request.getQuality());
		query.setParameter("dichvuId", idDV);
		query.setParameter("pttId", idPhieu);

		try {
		    query.executeUpdate();
		    
		    // Tính toán tổng giá trị amount của các bản ghi trong dich_vu_phieu_thanh_toan có ptt_id = id của phiếu thanh toán
		    String sum = "SELECT SUM(amount) FROM dich_vu_phieu_thanh_toan WHERE ptt_id = :pttId";
		    Query sumQuery = entityManager.createNativeQuery(sum);
		    sumQuery.setParameter("pttId", idPhieu);
		    BigDecimal totalAmount = (BigDecimal) sumQuery.getSingleResult();

		    // Cập nhật bản ghi của phieu_thanh_toan với total_mount mới tính toán được
		    String update = "UPDATE phieu_thanh_toan SET total_mount = :totalAmount WHERE id = :pttId";
		    Query updateQuery = entityManager.createNativeQuery(update);
		    updateQuery.setParameter("totalAmount", totalAmount);
		    updateQuery.setParameter("pttId", idPhieu);
		    int rowsAffected = updateQuery.executeUpdate();

		    if (rowsAffected > 0) {
		        // Cập nhật thành công
		        return ResponseEntity.status(HttpStatus.CREATED).body("Phiếu thanh toán mới đã được thêm và total_mount đã được cập nhật");
		    } else {
		        // Lỗi khi cập nhật
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi cập nhật total_mount");
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi thêm phiếu thanh toán mới");
		}

	}
	
}
