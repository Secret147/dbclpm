package dbclpm.api;

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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ExcelController {
	@Autowired
	private NamService namSe;
	
	@Autowired
	private KhachHangRepo khachHangRe;
	
	@Autowired
	private KhachHangService khachhangSe;
	
	@Autowired
	private LuongDienTieuThuRepo ldttRe;
	
	@Autowired
	private LuongDienTieuThuService ldttSe;
	
	@Autowired
	private HoaDonService billSe;

    @GetMapping("/thang/export-excel")
    public ResponseEntity<byte[]> exportExcel() throws IOException {
       
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        List<Thang> thangs = namSe.getListThangByNam(1L);
        Row row0 = sheet.createRow(0);
        Cell cellRow0 = row0.createCell(0);
        cellRow0.setCellValue("id");
        
        Cell cellRow1 = row0.createCell(1);
        cellRow1.setCellValue("description");
        
        Cell cellRow2 = row0.createCell(2);
        cellRow2.setCellValue("name");
        
        int rowIndex = 1;
        for(Thang x : thangs) {
        	Row row = sheet.createRow(rowIndex++);
        	Cell cell = row.createCell(0);
    		cell.setCellValue(x.getName());
    		
    		Cell cell1 = row.createCell(1);
    		cell1.setCellValue(x.getDescription());
    		
    		Cell cell2 = row.createCell(2);
    		cell2.setCellValue(x.getId());   	
        }

        // Ghi workbook vào ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // Tạo HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "data.xlsx");
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
    
    @GetMapping("ldtt/export/{id}")
     public ResponseEntity<byte[]> exportLdttExcel(@PathVariable Long id) throws IOException {
        
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");
        
        List<LuongDienTieuThu> khachHangs = ldttSe.getLdttByThang(id);
        String[] title ={"id","Tên khách hàng","Chỉ số cũ","Chỉ số mới","Tháng","Trạng thái"};
        int cellIndex = 0;
        Row rowTitle = sheet.createRow(0);
        Cell cellTitle = rowTitle.createCell(2);
        cellTitle.setCellValue("Báo cáo sử dụng điện tháng " + khachHangs.get(0).getThang().getName());
        Row rowFirst = sheet.createRow(1);
        for(String x : title) {     	
        	Cell cell = rowFirst.createCell(cellIndex);
        	cell.setCellValue(x);
        	cellIndex++;
        	
        }
        int rowIndex = 2;
        for(LuongDienTieuThu x : khachHangs) {
        	Row row = sheet.createRow(rowIndex++);
        	Cell cell = row.createCell(0);
    		cell.setCellValue(x.getId());
    		
    		Cell cell1 = row.createCell(1);
    		cell1.setCellValue(x.getKhachHang().getName());
    		
    		Cell cell2 = row.createCell(2);
    		cell2.setCellValue(x.getCsc());
    		
    		Cell cell3 = row.createCell(3);
    		cell3.setCellValue(x.getCsm()); 
    		
    		Cell cell4 = row.createCell(4);
    		cell4.setCellValue(x.getThang().getName());
    		

    		
    		Cell cell5 = row.createCell(5);
    		cell5.setCellValue(x.getState() == "0" ? "Chua thanh toan" : " Da thanh toan");
        }
        Row rowSum = sheet.createRow(rowIndex+1);
        Cell cellSum = rowSum.createCell(9);
        Cell cellTitleSum = rowSum.createCell(8);
        Long sum = ldttSe.getLuongdienTieuthuTheoThang(id);
        cellTitleSum.setCellValue("Tổng lượng điện tiêu thụ(Kwh)");
        cellSum.setCellValue(sum);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "bcldtt.xlsx");
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
    
    
    @GetMapping("/khachhang/export")
    public ResponseEntity<byte[]> exportKhachHangExcel() throws IOException {
       
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");
        
        List<KhachHang> khachHangs = khachHangRe.findAll();
        String[] title ={"id","Tên khách hàng","Email","Địa chỉ", "Số điện thoại","Ghi chú"};
        int cellIndex = 0;
        Row rowTitle = sheet.createRow(0);
        Cell cellTitle = rowTitle.createCell(2);
        cellTitle.setCellValue("Danh sách hộ cá nhân sử dụng điện");
        Row rowFirst = sheet.createRow(1);
        for(String x : title) {     	
        	Cell cell = rowFirst.createCell(cellIndex);
        	cell.setCellValue(x);
        	cellIndex++;
        	
        }
        int rowIndex = 2;
        for(KhachHang x : khachHangs) {
        	Row row = sheet.createRow(rowIndex++);
        	Cell cell = row.createCell(0);
    		cell.setCellValue(x.getId());
    		
    		Cell cell1 = row.createCell(1);
    		cell1.setCellValue(x.getName());
    		
    		Cell cell2 = row.createCell(2);
    		cell2.setCellValue(x.getEmail()); 
    		
    		Cell cell3 = row.createCell(3);
    		cell3.setCellValue(x.getAddress());
    		
    		Cell cell4 = row.createCell(4);
    		cell4.setCellValue(x.getNumberPhone());
    		
    		Cell cell5 = row.createCell(5);
    		cell5.setCellValue(x.getNote());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "khachhang.xlsx");
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/payment/export")
    public ResponseEntity<?> exportKhachHangStateExcel() throws IOException {
       
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");
        
        
        
        List<LuongDienTieuThu> khachHangs = khachhangSe.getListKhPayment();
        
        String[] title ={"id","Tên khách hàng","Email","Địa chỉ", "Số điện thoại","Ghi chú","Tháng"};
        int cellIndex = 0;
        Row rowTitle = sheet.createRow(0);
        Cell cellTitle = rowTitle.createCell(2);
        cellTitle.setCellValue("Danh sách hộ cá nhân chưa thanh toán hóa đơn");
        Row rowFirst = sheet.createRow(1);
        for(String x : title) {     	
        	Cell cell = rowFirst.createCell(cellIndex);
        	cell.setCellValue(x);
        	cellIndex++;
        	
        }
        int rowIndex = 2;
        for(LuongDienTieuThu x : khachHangs) {
        	Row row = sheet.createRow(rowIndex++);
        	Cell cell = row.createCell(0);
    		cell.setCellValue(x.getId());
    		
    		Cell cell1 = row.createCell(1);
    		cell1.setCellValue(x.getKhachHang().getName());
    		
    		Cell cell2 = row.createCell(2);
    		cell2.setCellValue(x.getKhachHang().getEmail()); 
    		
    		Cell cell3 = row.createCell(3);
    		cell3.setCellValue(x.getKhachHang().getAddress());
    		
    		Cell cell4 = row.createCell(4);
    		cell4.setCellValue(x.getKhachHang().getNumberPhone());
    		
    		Cell cell5 = row.createCell(5);
    		cell5.setCellValue(x.getKhachHang().getNote());
    		
    		Cell cell6 = row.createCell(6);
    		cell6.setCellValue(x.getThang().getName());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "payment.xlsx");
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/user/export/{id}")
    public ResponseEntity<?> exportKhachHangInfor(@PathVariable Long id) throws IOException {
       
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");
        
        
        
        List<LuongDienTieuThu> ldtts = ldttRe.findByKhachHangId(id);
       
        
        String[] title ={"id","Chỉ số cũ","Chỉ số mới","Tháng","Trạng thái"};
        int cellIndex = 0;
        Row rowTitle = sheet.createRow(0);
        Cell cellTitle = rowTitle.createCell(2);
        cellTitle.setCellValue("Thông tin sử dụng điện của khách hàng " + ldtts.get(0).getKhachHang().getName());
        Row rowFirst = sheet.createRow(1);
        for(String x : title) {     	
        	Cell cell = rowFirst.createCell(cellIndex);
        	cell.setCellValue(x);
        	cellIndex++;
        	
        }
        int rowIndex = 2;
        for(LuongDienTieuThu x : ldtts) {
        	Row row = sheet.createRow(rowIndex++);
        	Cell cell = row.createCell(0);
    		cell.setCellValue(x.getId());
    		
    		Cell cell1 = row.createCell(1);
    		cell1.setCellValue(x.getCsc());
    		
    		Cell cell2 = row.createCell(2);
    		cell2.setCellValue(x.getCsm()); 
    		
    		Cell cell3 = row.createCell(3);
    		cell3.setCellValue(x.getThang().getName());
    		
    		
    		Cell cell4 = row.createCell(4);
    		cell4.setCellValue(x.getState() == "0" ? "Chua thanh toan" : " Da thanh toan");
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "thongtinsudung.xlsx");
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
    
    
    @GetMapping("/total")
    public ResponseEntity<?> exportSoDien() throws IOException {
       
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");
        List<HoaDon> bills = billSe.getListHoaDon();
        
        String[] title ={"id","Tên khách hàng","Nhân viên tạo", "Tổng","Mô tả"};
        int cellIndex = 0;
        Row rowTitle = sheet.createRow(0);
        Cell cellTitle = rowTitle.createCell(2);
        cellTitle.setCellValue("Thông tin doanh thu");
        Row rowFirst = sheet.createRow(1);
        for(String x : title){     	
        	Cell cell = rowFirst.createCell(cellIndex);
        	cell.setCellValue(x);
        	cellIndex++;
        	
        }
        int rowIndex = 2;
        for(HoaDon x : bills) {
        	Row row = sheet.createRow(rowIndex++);
        	Cell cell = row.createCell(0);
    		cell.setCellValue(x.getId());
    		
    		Cell cell1 = row.createCell(1);
    		cell1.setCellValue(x.getKhachHang().getName());
    		
    		Cell cell2 = row.createCell(2);
    		cell2.setCellValue(x.getNhanVien().getName()); 
    		
    		Cell cell3 = row.createCell(3);
    		cell3.setCellValue(x.getTotal());
    		
    		Cell cell4 = row.createCell(4);
    		cell4.setCellValue(x.getDescription());
    		
    	
        }
        
        Row rowSum = sheet.createRow(rowIndex+1);
        Cell cellSum = rowSum.createCell(9);
        Cell cellTitleSum = rowSum.createCell(8);
        Long sum = billSe.getTotal();
        cellTitleSum.setCellValue("Tổng doanh thu(VND)");
        cellSum.setCellValue(sum);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "thongtinsudung.xlsx");
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }


}

