package dbclpm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbclpm.service.ThangService;

@RestController
@CrossOrigin
@RequestMapping("/thang")
public class ThangAPI {
	@Autowired
	private ThangService thangSe;
	
	

	
	@GetMapping("/all/{nam_id}")
	public ResponseEntity<?> getThangByNam(@PathVariable Long nam_id){
		return ResponseEntity.ok(thangSe.getThangByNam(nam_id));
	}

}
