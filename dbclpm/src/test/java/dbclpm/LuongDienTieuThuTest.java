package dbclpm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import dbclpm.entity.LuongDienTieuThu;
import dbclpm.service.LuongDienTieuThuService;

@SpringBootTest
@AutoConfigureMockMvc
public class LuongDienTieuThuTest {

	@Autowired
	LuongDienTieuThuService ldttSe;
	
	@Test
    public void testLdttByThang() throws Exception {
		//Trường hợp tháng đã tồn tại và đã có thông tin sử dụng điện
		Long id = 1L;
		List<LuongDienTieuThu> ldtts = ldttSe.getLdttByThang(id);
		assertEquals(!ldtts.isEmpty(), true);
	
	}
	@Test
    public void testLdttByThang2() throws Exception {
		//Trường hợp tháng đã tồn tại và chưa có thông tin sử dụng điện
		Long id = 1L;
		List<LuongDienTieuThu> ldtts = ldttSe.getLdttByThang(id);
		assertEquals(ldtts.isEmpty(), true);
	
	}
	@Test
    public void testLdttByThang3() throws Exception {
		//Trường hợp tháng chưa tồn tại
		Long id = 1L;
		List<LuongDienTieuThu> ldtts = ldttSe.getLdttByThang(id);
		assertEquals(ldtts.isEmpty(), true);
	
	}
	
	

}
