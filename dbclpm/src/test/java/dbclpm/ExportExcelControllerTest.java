package dbclpm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbclpm.entity.HoaDon;
import dbclpm.entity.KhachHang;
import dbclpm.entity.LuongDienTieuThu;
import dbclpm.entity.Thang;
import dbclpm.repository.KhachHangRepo;
import dbclpm.repository.LuongDienTieuThuRepo;
import dbclpm.service.HoaDonService;
import dbclpm.service.KhachHangService;
import dbclpm.service.LuongDienTieuThuService;
import dbclpm.service.NamService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExportExcelControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private NamService namSe;
    
    @Autowired
    private LuongDienTieuThuService ldttSe;
    
    @Autowired
    private KhachHangRepo khachHangRe;
    @Autowired 
    private KhachHangService khachhangSe;
    @Autowired
    private HoaDonService billSe;
    @Autowired
    private LuongDienTieuThuRepo ldttRe;
    
    /**
     * Trường hợp tháng đã tồn tại và đã có dữ liệu sử dụng điện
     */
    
    @Test
    public void testExportLdttExcel1() throws Exception {
        Long id = 37L; // Đặt ID của tháng bạn muốn kiểm tra
        MvcResult result = mockMvc.perform(get("/api/ldtt/export/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        byte[] response = result.getResponse().getContentAsByteArray();

        try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(response))) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(1);

            // Kiểm tra tiêu đề
            assertEquals("id", row.getCell(0).getStringCellValue());
            assertEquals("Tên khách hàng", row.getCell(1).getStringCellValue());
            assertEquals("Chỉ số cũ", row.getCell(2).getStringCellValue());
            assertEquals("Chỉ số mới", row.getCell(3).getStringCellValue());
            assertEquals("Tháng", row.getCell(4).getStringCellValue());
            assertEquals("Trạng thái", row.getCell(5).getStringCellValue());

            // Kiểm tra dữ liệu
            List<LuongDienTieuThu> ldttList = ldttSe.getLdttByThang(id);
            for (int i = 0; i < ldttList.size(); i++) {
                LuongDienTieuThu ldtt = ldttList.get(i);
                Row dataRow = sheet.getRow(i + 2);

                assertEquals(ldtt.getId(), (long)dataRow.getCell(0).getNumericCellValue());
                assertEquals(ldtt.getKhachHang().getName(), dataRow.getCell(1).getStringCellValue());
                assertEquals(ldtt.getCsc(), (long)dataRow.getCell(2).getNumericCellValue(), 0.01);
                assertEquals(ldtt.getCsm(),(long) dataRow.getCell(3).getNumericCellValue(), 0.01);
                assertEquals(ldtt.getThang().getName(), dataRow.getCell(4).getStringCellValue());
                assertEquals(ldtt.getState().equals("0") ? "Chưa thanh toán" : " Đã thanh toán", dataRow.getCell(5).getStringCellValue());
            }
        }
    }
    /**
     * Trường hợp tháng đã tồn tại nhưng chưa có dữ liệu sử dụng điện
     */
    
    @Test
    public void testExportLdttExcel2() throws Exception {
        Long id = 1L; // Đặt ID của tháng bạn muốn kiểm tra
        MvcResult result = mockMvc.perform(get("/api/ldtt/export/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        byte[] response = result.getResponse().getContentAsByteArray();

        try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(response))) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(1);

            // Kiểm tra tiêu đề
            assertEquals("id", row.getCell(0).getStringCellValue());
            assertEquals("Tên khách hàng", row.getCell(1).getStringCellValue());
            assertEquals("Chỉ số cũ", row.getCell(2).getStringCellValue());
            assertEquals("Chỉ số mới", row.getCell(3).getStringCellValue());
            assertEquals("Tháng", row.getCell(4).getStringCellValue());
            assertEquals("Trạng thái", row.getCell(5).getStringCellValue());

            // Kiểm tra dữ liệu
            List<LuongDienTieuThu> ldttList = ldttSe.getLdttByThang(id);
            for (int i = 0; i < ldttList.size(); i++) {
                LuongDienTieuThu ldtt = ldttList.get(i);
                Row dataRow = sheet.getRow(i + 2);

                assertEquals(ldtt.getId(), (long)dataRow.getCell(0).getNumericCellValue());
                assertEquals(ldtt.getKhachHang().getName(), dataRow.getCell(1).getStringCellValue());
                assertEquals(ldtt.getCsc(), (long)dataRow.getCell(2).getNumericCellValue(), 0.01);
                assertEquals(ldtt.getCsm(),(long) dataRow.getCell(3).getNumericCellValue(), 0.01);
                assertEquals(ldtt.getThang().getName(), dataRow.getCell(4).getStringCellValue());
                assertEquals(ldtt.getState().equals("0") ? "Chưa thanh toán" : " Đã thanh toán", dataRow.getCell(5).getStringCellValue());
            }
        }
    }
    
    /**
     * Truờng hợp tháng chưa tồn tại
     */
    
    
    @Test
    public void testExportLdttExcel3() throws Exception {
        Long id = 100L; // Đặt ID của tháng bạn muốn kiểm tra
        MvcResult result = mockMvc.perform(get("/api/ldtt/export/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        byte[] response = result.getResponse().getContentAsByteArray();

        try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(response))) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(1);

            // Kiểm tra tiêu đề
            assertEquals("id", row.getCell(0).getStringCellValue());
            assertEquals("Tên khách hàng", row.getCell(1).getStringCellValue());
            assertEquals("Chỉ số cũ", row.getCell(2).getStringCellValue());
            assertEquals("Chỉ số mới", row.getCell(3).getStringCellValue());
            assertEquals("Tháng", row.getCell(4).getStringCellValue());
            assertEquals("Trạng thái", row.getCell(5).getStringCellValue());

            // Kiểm tra dữ liệu
            List<LuongDienTieuThu> ldttList = ldttSe.getLdttByThang(id);
            for (int i = 0; i < ldttList.size(); i++) {
                LuongDienTieuThu ldtt = ldttList.get(i);
                Row dataRow = sheet.getRow(i + 2);

                assertEquals(ldtt.getId(), (long)dataRow.getCell(0).getNumericCellValue());
                assertEquals(ldtt.getKhachHang().getName(), dataRow.getCell(1).getStringCellValue());
                assertEquals(ldtt.getCsc(), (long)dataRow.getCell(2).getNumericCellValue(), 0.01);
                assertEquals(ldtt.getCsm(),(long) dataRow.getCell(3).getNumericCellValue(), 0.01);
                assertEquals(ldtt.getThang().getName(), dataRow.getCell(4).getStringCellValue());
                assertEquals(ldtt.getState().equals("0") ? "Chưa thanh toán" : " Đã thanh toán", dataRow.getCell(5).getStringCellValue());
            }
        }
    }
    
    
    /**
     * Export danh sách khách hàng
     */
    
    @Test
    public void testExportKhachHangExcel() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/khachhang/export")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        byte[] response = result.getResponse().getContentAsByteArray();

        try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(response))) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(1);

            // Kiểm tra tiêu đề
            assertEquals("id", row.getCell(0).getStringCellValue());
            assertEquals("Tên khách hàng", row.getCell(1).getStringCellValue());
            assertEquals("Email", row.getCell(2).getStringCellValue());
            assertEquals("Địa chỉ", row.getCell(3).getStringCellValue());
            assertEquals("Số điện thoại", row.getCell(4).getStringCellValue());
            assertEquals("Ghi chú", row.getCell(5).getStringCellValue());

            // Kiểm tra dữ liệu
            List<KhachHang> khachHangList = khachHangRe.findAll();
            for (int i = 0; i < khachHangList.size(); i++) {
                KhachHang khachHang = khachHangList.get(i);
                Row dataRow = sheet.getRow(i + 2);

                assertEquals(khachHang.getId(), (long)dataRow.getCell(0).getNumericCellValue());
                assertEquals(khachHang.getName(), dataRow.getCell(1).getStringCellValue());
                assertEquals(khachHang.getEmail(), dataRow.getCell(2).getStringCellValue());
                assertEquals(khachHang.getAddress(), dataRow.getCell(3).getStringCellValue());
                assertEquals(khachHang.getTel(), dataRow.getCell(4).getStringCellValue());
                assertEquals(khachHang.getNote(), dataRow.getCell(5).getStringCellValue());
            }
        }
    }
    
    /**
     * Export danh sách khách hàng chưa thanh toán hóa đơn
     */
    
    @Test
    public void testExportKhachHangStateExcel() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/payment/export")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        byte[] response = result.getResponse().getContentAsByteArray();

        try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(response))) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(1);

            // Kiểm tra tiêu đề
            assertEquals("id", row.getCell(0).getStringCellValue());
            assertEquals("Tên khách hàng", row.getCell(1).getStringCellValue());
            assertEquals("Email", row.getCell(2).getStringCellValue());
            assertEquals("Địa chỉ", row.getCell(3).getStringCellValue());
            assertEquals("Số điện thoại", row.getCell(4).getStringCellValue());
            assertEquals("Ghi chú", row.getCell(5).getStringCellValue());
            assertEquals("Tháng", row.getCell(6).getStringCellValue());

            // Kiểm tra dữ liệu
            List<LuongDienTieuThu> ldttList = khachhangSe.getListKhPayment();
            for (int i = 0; i < ldttList.size(); i++) {
                LuongDienTieuThu ldtt = ldttList.get(i);
                Row dataRow = sheet.getRow(i + 2);

                assertEquals(ldtt.getId(), (long)dataRow.getCell(0).getNumericCellValue());
                assertEquals(ldtt.getKhachHang().getName(), dataRow.getCell(1).getStringCellValue());
                assertEquals(ldtt.getKhachHang().getEmail(), dataRow.getCell(2).getStringCellValue());
                assertEquals(ldtt.getKhachHang().getAddress(), dataRow.getCell(3).getStringCellValue());
                assertEquals(ldtt.getKhachHang().getTel(), dataRow.getCell(4).getStringCellValue());
                assertEquals(ldtt.getKhachHang().getNote(), dataRow.getCell(5).getStringCellValue());
                assertEquals(ldtt.getThang().getName(), dataRow.getCell(6).getStringCellValue());
            }
        }
    }
    
    /**
     * Export thông tin sử dụng và tổng số điện tiêu thụ
     */
    
    @Test
    public void testExportSoDien() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/total")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        byte[] response = result.getResponse().getContentAsByteArray();

        try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(response))) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(1);

            // Kiểm tra tiêu đề
            assertEquals("id", row.getCell(0).getStringCellValue());
            assertEquals("Tên khách hàng", row.getCell(1).getStringCellValue());
            assertEquals("Nhân viên tạo", row.getCell(2).getStringCellValue());
            assertEquals("Tổng", row.getCell(3).getStringCellValue());
            assertEquals("Mô tả", row.getCell(4).getStringCellValue());

            // Kiểm tra dữ liệu
            List<HoaDon> hoaDonList = billSe.getListHoaDon();
            for (int i = 0; i < hoaDonList.size(); i++) {
                HoaDon hoaDon = hoaDonList.get(i);
                Row dataRow = sheet.getRow(i + 2);

                assertEquals(hoaDon.getId(), (long)dataRow.getCell(0).getNumericCellValue());
                assertEquals(hoaDon.getKhachHang().getName(), dataRow.getCell(1).getStringCellValue());
                assertEquals(hoaDon.getNhanVien().getName(), dataRow.getCell(2).getStringCellValue());
                assertEquals(hoaDon.getTotal(), (long)dataRow.getCell(3).getNumericCellValue());
                assertEquals(hoaDon.getDescription(), dataRow.getCell(4).getStringCellValue());
            }
        }
    }
    
    /**
     * Khách hàng đã tồn tại và đã có thông tin sử dụng điện
     */
    
    @Test
    public void testExportKhachHangInfor1() throws Exception {
        Long id = 1L; // Đặt ID của khách hàng bạn muốn kiểm tra
        MvcResult result = mockMvc.perform(get("/api/user/export/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        byte[] response = result.getResponse().getContentAsByteArray();

        try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(response))) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(1);

            // Kiểm tra tiêu đề
            assertEquals("id", row.getCell(0).getStringCellValue());
            assertEquals("Chỉ số cũ", row.getCell(1).getStringCellValue());
            assertEquals("Chỉ số mới", row.getCell(2).getStringCellValue());
            assertEquals("Tháng", row.getCell(3).getStringCellValue());
            assertEquals("Trạng thái", row.getCell(4).getStringCellValue());

            // Kiểm tra dữ liệu
            List<LuongDienTieuThu> ldttList = ldttRe.findByKhachHangId(id);
            for (int i = 0; i < ldttList.size(); i++) {
                LuongDienTieuThu ldtt = ldttList.get(i);
                Row dataRow = sheet.getRow(i + 2);

                assertEquals(ldtt.getId(), (long)dataRow.getCell(0).getNumericCellValue());
                assertEquals(ldtt.getCsc(), (long)dataRow.getCell(1).getNumericCellValue());
                assertEquals(ldtt.getCsm(),(long)dataRow.getCell(2).getNumericCellValue());
                assertEquals(ldtt.getThang().getName(), dataRow.getCell(3).getStringCellValue());
                assertEquals(ldtt.getState().equals("0") ? "Chua thanh toan" : " Da thanh toan", dataRow.getCell(4).getStringCellValue());
            }
        }
    }
    
    /**
     * Khách hàng đã tồn tại và chưa có thông tin sử dụng điện
     */
    
    @Test
    public void testExportKhachHangInfor2() throws Exception {
        Long id = 10L; // Đặt ID của khách hàng bạn muốn kiểm tra
        MvcResult result = mockMvc.perform(get("/api/user/export/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        byte[] response = result.getResponse().getContentAsByteArray();

        try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(response))) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(1);

            // Kiểm tra tiêu đề
            assertEquals("id", row.getCell(0).getStringCellValue());
            assertEquals("Chỉ số cũ", row.getCell(1).getStringCellValue());
            assertEquals("Chỉ số mới", row.getCell(2).getStringCellValue());
            assertEquals("Tháng", row.getCell(3).getStringCellValue());
            assertEquals("Trạng thái", row.getCell(4).getStringCellValue());

            // Kiểm tra dữ liệu
            List<LuongDienTieuThu> ldttList = ldttRe.findByKhachHangId(id);
            for (int i = 0; i < ldttList.size(); i++) {
                LuongDienTieuThu ldtt = ldttList.get(i);
                Row dataRow = sheet.getRow(i + 2);

                assertEquals(ldtt.getId(), (long)dataRow.getCell(0).getNumericCellValue());
                assertEquals(ldtt.getCsc(), (long)dataRow.getCell(1).getNumericCellValue());
                assertEquals(ldtt.getCsm(),(long)dataRow.getCell(2).getNumericCellValue());
                assertEquals(ldtt.getThang().getName(), dataRow.getCell(3).getStringCellValue());
                assertEquals(ldtt.getState().equals("0") ? "Chua thanh toan" : " Da thanh toan", dataRow.getCell(4).getStringCellValue());
            }
        }
    }
    
    /**
     * Khách hàng chưa tồn tại 
     */
 
    @Test
    public void testExportKhachHangInfor3() throws Exception {
        Long id = 100L; // Đặt ID của khách hàng bạn muốn kiểm tra
        MvcResult result = mockMvc.perform(get("/api/user/export/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        byte[] response = result.getResponse().getContentAsByteArray();

        try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(response))) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(1);

            // Kiểm tra tiêu đề
            assertEquals("id", row.getCell(0).getStringCellValue());
            assertEquals("Chỉ số cũ", row.getCell(1).getStringCellValue());
            assertEquals("Chỉ số mới", row.getCell(2).getStringCellValue());
            assertEquals("Tháng", row.getCell(3).getStringCellValue());
            assertEquals("Trạng thái", row.getCell(4).getStringCellValue());

            // Kiểm tra dữ liệu
            List<LuongDienTieuThu> ldttList = ldttRe.findByKhachHangId(id);
            for (int i = 0; i < ldttList.size(); i++) {
                LuongDienTieuThu ldtt = ldttList.get(i);
                Row dataRow = sheet.getRow(i + 2);

                assertEquals(ldtt.getId(), (long)dataRow.getCell(0).getNumericCellValue());
                assertEquals(ldtt.getCsc(), (long)dataRow.getCell(1).getNumericCellValue());
                assertEquals(ldtt.getCsm(),(long)dataRow.getCell(2).getNumericCellValue());
                assertEquals(ldtt.getThang().getName(), dataRow.getCell(3).getStringCellValue());
                assertEquals(ldtt.getState().equals("0") ? "Chua thanh toan" : " Da thanh toan", dataRow.getCell(4).getStringCellValue());
            }
        }
    }
   

}
