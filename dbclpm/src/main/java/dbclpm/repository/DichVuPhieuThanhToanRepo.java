package dbclpm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dbclpm.entity.BacDien;
import dbclpm.entity.DichVuPhieuThanhToan;

public interface DichVuPhieuThanhToanRepo extends JpaRepository<DichVuPhieuThanhToan, Long>{
	 List<DichVuPhieuThanhToan> findAll();
}
