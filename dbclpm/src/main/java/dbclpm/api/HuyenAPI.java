package dbclpm.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dbclpm.entity.Xa;
import dbclpm.repository.XaRepo;
import jakarta.websocket.server.PathParam;

@RestController
public class HuyenAPI {
	
	private final XaRepo xaRepo;
	
	public HuyenAPI(XaRepo xaRepo) {
		this.xaRepo = xaRepo;
	}
	
	/**
	 * Lấy danh sách xã/phường trong một quận/huyện
	 * @param huyenID
	 * @return
	 */
	@GetMapping("/api/huyen/{xaID}/xa")
	public ResponseEntity<List<Xa>> getDsXaByHuyenId(@PathParam("huyenId") long huyenID) {
		/* TODO
		 * Xử lý case: một vài huyện có thể không có xã
		 */
		List<Xa> dsXaTheoHuyen = xaRepo.findByHuyenId(huyenID);
		return ResponseEntity.ok(dsXaTheoHuyen);
	}
	
}
