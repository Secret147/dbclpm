package dbclpm.dto;

import lombok.Data;

@Data
public class KhachHangDTO {
    private Long id;
	
	private String address;
	
	private String email;
	
	private String name;
	
	private String numberPhone;
	
	private String note;
	


	public KhachHangDTO() {
		// TODO Auto-generated constructor stub
	}

}
