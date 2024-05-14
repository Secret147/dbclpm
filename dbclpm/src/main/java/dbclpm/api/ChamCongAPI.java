package dbclpm.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dbclpm.entity.ChamCong;
import dbclpm.entity.NhanVien;
import dbclpm.entity.PhieuThanhToan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@RestController
@CrossOrigin
@RequestMapping("/cham-cong")
public class ChamCongAPI {
	@Autowired
	private EntityManager entityManager;

	@GetMapping("/api/cham-cong/{nhanVienID}")
	public ResponseEntity<List<ChamCong>> getChamCong(@PathVariable Long nhanVienID) {
		try {
			// Tạo truy vấn để lấy tất cả các phiếu thanh toán cho khách hàng cụ thể
			String queryStr = "SELECT * FROM cham_cong WHERE nhanvien_id = :nhanVienID";
			Query query = entityManager.createNativeQuery(queryStr, ChamCong.class);
			query.setParameter("nhanVienID", nhanVienID);

			// Thực hiện truy vấn
			List<ChamCong> list = query.getResultList();

			// Trả về danh sách các phiếu thanh toán
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/api/cham-cong/date/{nhanVienID}")
	public ResponseEntity<List<ChamCong>> getChamCongByDateRange(@PathVariable Long nhanVienID,
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
		try {
			// khoảng thời gian đã cho
			String queryStr = "SELECT * FROM cham_cong WHERE nhanvien_id = :nhanVienID AND date BETWEEN :startDate AND :endDate";
			Query query = entityManager.createNativeQuery(queryStr, ChamCong.class);
			query.setParameter("nhanVienID", nhanVienID);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);

			// Thực hiện truy vấn
			List<ChamCong> list = query.getResultList();

			// Trả về danh sách các phiếu thanh toán
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
