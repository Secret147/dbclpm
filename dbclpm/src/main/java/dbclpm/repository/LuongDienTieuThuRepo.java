package dbclpm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dbclpm.entity.LuongDienTieuThu;

public interface LuongDienTieuThuRepo extends JpaRepository<LuongDienTieuThu, Long> {
	@Query(value = "SELECT sum(luong_dien_tieu_thu.csm - luong_dien_tieu_thu.csc) \r\n"
			+ "FROM dbclpm.luong_dien_tieu_thu \r\n" + "WHERE thang_id = ?;", nativeQuery = true)
	Long getLuongdienTieuThuByThang(Long thang_id);

	@Query(value = "SELECT * FROM dbclpm.luong_dien_tieu_thu \r\n" + "where state = 0;", nativeQuery = true)
	List<LuongDienTieuThu> getKhachHangctt();

	List<LuongDienTieuThu> findByKhachHangId(Long id);

	List<LuongDienTieuThu> findByKhachHangXaId(long xaId);

	List<LuongDienTieuThu> findByKhachHangXaHuyenId(long huyenId);

	List<LuongDienTieuThu> findByKhachHangXaHuyenTinhId(long tinhId);

	List<LuongDienTieuThu> findByThangId(Long id);

}
