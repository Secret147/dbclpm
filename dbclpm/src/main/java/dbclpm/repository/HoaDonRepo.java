package dbclpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dbclpm.entity.HoaDon;

public interface HoaDonRepo  extends JpaRepository<HoaDon, Long>{
	
	@Query(value = "SELECT SUM(hoa_don.total) FROM dbclpm.hoa_don;", nativeQuery = true)
    Long getSumDoanhThu();
	
	

}
