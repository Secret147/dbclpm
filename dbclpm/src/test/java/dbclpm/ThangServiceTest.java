package dbclpm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import dbclpm.entity.LuongDienTieuThu;
import dbclpm.entity.Thang;
import dbclpm.service.ThangService;

@SpringBootTest
@AutoConfigureMockMvc
public class ThangServiceTest {

	@Autowired
	ThangService thangSe;
	
	
	@Test
    public void testGetThangByNam() throws Exception {
		//Trường hợp năm đã tồn tại và đã có thông tin tháng
		Long id = 1L;
		List<Thang> thangs = thangSe.getThangByNam(id);
		assertEquals(!thangs.isEmpty(), true);
	
	}
	@Test
    public void testGetThangByNam2() throws Exception {
		//Trường hợp năm đã tồn tại và chưa có thông tin tháng
		Long id = 1L;
		List<Thang> thangs = thangSe.getThangByNam(id);
		assertEquals(thangs.isEmpty(), true);
	
	}
	@Test
    public void testGetThangByNam3() throws Exception {
		//Trường hợp năm chưa tồn tại
		Long id = 1L;
		List<Thang> thangs = thangSe.getThangByNam(id);
		assertEquals(thangs.isEmpty(), true);
	
	}

}
