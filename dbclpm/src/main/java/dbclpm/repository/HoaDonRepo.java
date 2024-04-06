package dbclpm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dbclpm.entity.HoaDon;


public interface HoaDonRepo extends JpaRepository<HoaDon, Long> {
	List<HoaDon> findByKhachHangXaIdAndLuongDienTieuThuThangId(long xaId, long thangId);

	List<HoaDon> findByKhachHangXaHuyenIdAndLuongDienTieuThuThangId(long huyenId, long thangId);


	List<HoaDon> findByKhachHangXaHuyenTinhIdAndLuongDienTieuThuThangId(long tinhId, long thangId);
  
  @Query(value = "SELECT SUM(hoa_don.total) FROM dbclpm.hoa_don;", nativeQuery = true)
    Long getSumDoanhThu();
}
