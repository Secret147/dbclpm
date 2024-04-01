package dbclpm.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dbclpm.entity.Huyen;
import dbclpm.entity.Tinh;
import dbclpm.repository.HuyenRepo;
import dbclpm.repository.TinhRepo;
import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin
public class TinhAPI {

	private final TinhRepo tinhRepo;

	private final HuyenRepo huyenRepo;

	public TinhAPI(TinhRepo tinhRepo, HuyenRepo huyenRepo) {
		this.tinhRepo = tinhRepo;
		this.huyenRepo = huyenRepo;
	}

	/**
	 * Lấy danh sách tất cả các tỉnh/thành phố
	 * 
	 * @return
	 */
	@GetMapping("/api/tinh")
	public ResponseEntity<List<Tinh>> getAllTinh() {
		List<Tinh> dsTinh = tinhRepo.findAll();
		return ResponseEntity.ok(dsTinh);
	}

	/**
	 * Lấy danh sách quận/huyện trong một tỉnh/thành phố
	 * 
	 * @param tinhID
	 * @return
	 */
	@GetMapping("api/tinh/{tinhID}/huyen")
	public ResponseEntity<List<Huyen>> getDsHuyenByTinhId(@PathParam("tinhId") long tinhID) {
		/*
		 * TODO Xử lý case: một vài tỉnh có thể không có quận/huyện
		 */
		List<Huyen> dsHuyenTheoTinh = huyenRepo.findByTinhId(tinhID);
		return ResponseEntity.ok(dsHuyenTheoTinh);
	}
}
