package dbclpm.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class KhachHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;


	    private String name;

	 
	    private String phone;

	
	    private String email;

	  
	    private String address;
	    
	    @OneToMany(mappedBy = "khachhang")
		@JsonIgnore
		List<PhieuThanhToan> ptts = new ArrayList<>();
}
