package dbclpm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dbclpm.dto.khachHangDTO;
import dbclpm.entity.khachHang;
import dbclpm.entity.luongDienTieuThu;



public interface luongDienTieuThuRepo extends JpaRepository<luongDienTieuThu, Long> {
	@Query(value = "SELECT sum(luong_dien_tieu_thu.csm - luong_dien_tieu_thu.csc) \r\n"
			+ "FROM dbclpm.luong_dien_tieu_thu \r\n"
			+ "WHERE thang_id = ?;", nativeQuery = true)
    Long getLuongdienTieuThuByThang(Long thang_id);
	
	@Query(value = "SELECT kh.*\r\n"
			+ "FROM khach_hang kh\r\n"
			+ "INNER JOIN luong_dien_tieu_thu ldtt ON kh.id = ldtt.khach_hang_id\r\n"
			+ "WHERE ldtt.state = 0;", nativeQuery = true)
    List<Object[]> getKhachHangctt();
    
    List<luongDienTieuThu> findByKhachHangId(Long id);
	
    List<luongDienTieuThu> findByThangId(Long id);
	
}
