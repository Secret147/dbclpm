package dbclpm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import dbclpm.entity.HoaDon;
import dbclpm.entity.LuongDienTieuThu;
import dbclpm.repository.HoaDonRepo;
import dbclpm.service.HoaDonService;

@SpringBootTest
@AutoConfigureMockMvc
public class HoaDonServiceTest {
	
	@Autowired
	private HoaDonRepo hoadonRe;
	
	@Autowired
	private HoaDonService hoadonSe;
	
	@Test
    public void testGetTotal() throws Exception {
		List<HoaDon> hoadons = hoadonRe.findAll();
		long sumTotal = 0;
		for(HoaDon x : hoadons) {
			sumTotal+= x.getTotal();
		}
		//Lấy ra chính xác tổng total
        assertEquals(hoadonSe.getTotal(), sumTotal);

	}
	
	@Test
	public void testGetListHoaDon() throws Exception {
		List<HoaDon> hoadons = hoadonSe.getListHoaDon();
		
        assertEquals(hoadons.size()>0, true);

	}

}
