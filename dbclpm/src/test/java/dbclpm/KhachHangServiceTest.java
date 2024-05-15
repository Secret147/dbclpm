package dbclpm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

import dbclpm.entity.*;
import dbclpm.repository.KhachHangRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import dbclpm.service.KhachHangService;

@SpringBootTest
@AutoConfigureMockMvc
public class KhachHangServiceTest {
	@Autowired
	private KhachHangService khachhangSe;

	@Mock
	private KhachHangRepo khachHangRepo;

	@Test
    public void testGetKhPayment() throws Exception {
		//Lấy ra danh sách khách hàng chưa thanh toán 
		List<LuongDienTieuThu> ldtts = khachhangSe.getListKhPayment();
	
		for(LuongDienTieuThu x : ldtts) {
			assertEquals(x.getState(), "0");
		}
	}
	
	@Test
	public void testGetAllKhachHang() throws Exception {
		//Kiểm tra lấy ra danh sách khách hàng
		List<KhachHang> khachhangs = khachhangSe.getAllKhachHang();
	
		assertEquals(khachhangs.size()>0, true);
    

	}


}
