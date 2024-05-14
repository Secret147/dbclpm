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
public class DichVu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;


	    private String name;

	 
	    private String address;

	  
	    private String price;
	    
	    @OneToMany(mappedBy = "dichvu")
		@JsonIgnore
		List<DichVuPhieuThanhToan> dichvuptts = new ArrayList<>();
}
