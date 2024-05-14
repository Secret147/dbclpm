package dbclpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dbclpm.entity.BacDien;
import dbclpm.entity.PhieuThanhToan;

public interface PhieuThanhToanRepo extends JpaRepository<PhieuThanhToan, Long>{

}
