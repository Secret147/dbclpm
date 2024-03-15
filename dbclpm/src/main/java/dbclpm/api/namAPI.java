package dbclpm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbclpm.repository.namRepo;
import dbclpm.service.namService;

@RestController
@CrossOrigin
@RequestMapping("/nam")
public class namAPI {

	public namAPI() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
	private namService namSe;
	
	@Autowired
	private namRepo namRe;
	
	@GetMapping("/{namid}")
	public ResponseEntity<?> getThangByNam(@PathVariable Long namid){
		return ResponseEntity.ok(namSe.getListThangByNam(namid));
	}
	
	@GetMapping("infor/{namid}")
	public ResponseEntity<?> getNam(@PathVariable Long namid){
		return ResponseEntity.ok(namRe.findById(namid));
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getNamAll(){
		return ResponseEntity.ok(namSe.getListNam());
	}

}
