package dbclpm.dto;

import lombok.Data;

@Data
public class khachHangDTO {
    private Long id;
	
	private String address;
	
	private String email;
	
	private String name;
	
	private String numberPhone;
	
	private String note;
	


	public khachHangDTO() {
		// TODO Auto-generated constructor stub
	}

}
