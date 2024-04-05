package dbclpm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dbclpm.entity.HoaDon;

public interface HoaDonRepo extends JpaRepository<HoaDon, Long> {
	List<HoaDon> findByKhachHangXaIdAndLuongDienTieuThuThangId(long xaId, long thangId);

	List<HoaDon> findByKhachHangXaHuyenIdAndLuongDienTieuThuThangId(long huyenId, long thangId);

	List<HoaDon> findByKhachHangXaHuyenTinhIdAndLuongDienTieuThuThangId(long tinhId, long thangId);
}
