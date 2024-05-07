package dbclpm.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dbclpm.dto.ThongKeDTO;
import dbclpm.entity.HoaDon;
import dbclpm.entity.LuongDienTieuThu;
import dbclpm.entity.Nam;
import dbclpm.entity.Thang;
import dbclpm.repository.HoaDonRepo;
import dbclpm.repository.LuongDienTieuThuRepo;
import dbclpm.repository.NamRepo;
import dbclpm.repository.ThangRepo;
import dbclpm.ultilities.tien_dien.TienDienUltility;

@RestController
@CrossOrigin
public class ListMonitorController {

	private final LuongDienTieuThuRepo luongDienTieuThuRepo;
	private final HoaDonRepo hoaDonRepo;
	private final ThangRepo thangRepo;
	private final NamRepo namRepo;
	private final TienDienUltility tienDienUltility;

	public ListMonitorController(LuongDienTieuThuRepo luongDienTieuThuRepo, ThangRepo thangRepo, NamRepo namRepo,
			HoaDonRepo hoaDonRepo, TienDienUltility tienDienUltility) {
		this.luongDienTieuThuRepo = luongDienTieuThuRepo;
		this.hoaDonRepo = hoaDonRepo;
		this.thangRepo = thangRepo;
		this.namRepo = namRepo;
		this.tienDienUltility = tienDienUltility;
	}

	/**
	 * Lấy thông tin thống kê về lượng tiêu thụ điện của gia đình theo thời gian &
	 * địa điểm
	 * 
	 * @param requestParams
	 * @return
	 */
	@PostMapping("api/list")
	public ResponseEntity<List<ThongKeDTO>> getThongKe(@RequestBody HashMap<String, Integer> requestParams) {
		long tinhId = requestParams.get("tinhId");
		long huyenId = requestParams.get("huyenId");
		long xaId = requestParams.get("xaId");
		long namId = requestParams.get("tinhId");
		long thangId = requestParams.get("thangId");

		Thang thang = thangRepo.findById(thangId).orElse(null);
		Nam nam = namRepo.findById(namId).orElse(null);
		LocalDate currentTime = LocalDate.now();

		List<ThongKeDTO> dsThongKe = new ArrayList<>();

		if (currentTime.getMonthValue() > Integer.parseInt(thang.getName())
				&& currentTime.getYear() >= Integer.parseInt(nam.getName())) {
			// Lấy danh sách thống kê trong quá khứ => Đã có hóa đơn
			List<HoaDon> dsHoaDon = dsHoaDon = hoaDonRepo.findByKhachHangXaIdAndLuongDienTieuThuThangId(xaId, thangId);
			for (HoaDon hoaDon : dsHoaDon) {
				dsThongKe.add(new ThongKeDTO(hoaDon.getLuongDienTieuThu(), hoaDon, 0));
			}
		} else {
			// Lấy danh sách thống kê trong tháng này => Chưa có hóa đơn => Tính lại tiền điện
			List<LuongDienTieuThu> dsLuongDienTieuThu = luongDienTieuThuRepo.findByKhachHangXaIdAndThangId(xaId, thangId);
			for (LuongDienTieuThu luongDienTieuThu : dsLuongDienTieuThu) {
				dsThongKe.add(new ThongKeDTO(luongDienTieuThu, null,
						tienDienUltility.tinhTienDien(luongDienTieuThu.getCsc(), luongDienTieuThu.getCsm())));
			}
		}

		return ResponseEntity.ok(dsThongKe);
	}

}
