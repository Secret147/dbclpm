package dbclpm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbclpm.repository.NamRepo;
import dbclpm.service.NamService;

@RestController
@CrossOrigin
@RequestMapping("/nam")
public class NamAPI {

	public NamAPI() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
	private NamService namSe;
	
	@Autowired
	private NamRepo namRe;
	
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
