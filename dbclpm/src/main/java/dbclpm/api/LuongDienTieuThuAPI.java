package dbclpm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbclpm.service.LuongDienTieuThuService;

@RestController
@CrossOrigin
@RequestMapping("/ldtt")
public class LuongDienTieuThuAPI {
	@Autowired
	private LuongDienTieuThuService ldttSe;
	
	

	public LuongDienTieuThuAPI() {
		// TODO Auto-generated constructor stub
	}
	
	
	@GetMapping("/sum/thang/{thang_id}")
	public ResponseEntity<?> getSumLdttByThang(@PathVariable Long thang_id){		
		return ResponseEntity.ok(ldttSe.getLuongdienTieuthuTheoThang(thang_id));		
	}
	@GetMapping("thang/{id}")
	public ResponseEntity<?> getLdttByThang(@PathVariable Long id){
		return ResponseEntity.ok(ldttSe.getLdttByThang(id));
	}

}
