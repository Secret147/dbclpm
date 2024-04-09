package dbclpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dbclpm.entity.KhachHang;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KhachHangRepo extends JpaRepository<KhachHang, Long> {
    List<KhachHang> findAll();
    @Query("SELECT kh FROM KhachHang kh WHERE kh.xa.id = :xaId")
    List<KhachHang> findByXaId(@Param("xaId") Long xaId);
    @Query("SELECT kh FROM KhachHang kh WHERE kh.xa.huyen.id = :huyenId")
    List<KhachHang> findByHuyenId(@Param("huyenId") Long huyenId);

    @Query("SELECT kh FROM KhachHang kh WHERE kh.xa.huyen.tinh.id = :tinhId")
    List<KhachHang> findByTinhId(@Param("tinhId") Long tinhId);
}
