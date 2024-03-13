package dbclpm.api;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbclpm.entity.khachHang;
import dbclpm.entity.luongDienTieuThu;
import dbclpm.entity.thang;
import dbclpm.repository.khachHangRepo;
import dbclpm.repository.luongDienTieuThuRepo;
import dbclpm.service.namService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ExcelController {
	@Autowired
	private namService namSe;
	
	@Autowired
	private khachHangRepo khachHangRe;
	
	@Autowired
	private luongDienTieuThuRepo ldttRe;

    @GetMapping("/thang/export-excel")
    public ResponseEntity<byte[]> exportExcel() throws IOException {
       
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        List<thang> thangs = namSe.getListThangByNam(1L);
        Row row0 = sheet.createRow(0);
        Cell cellRow0 = row0.createCell(0);
        cellRow0.setCellValue("id");
        
        Cell cellRow1 = row0.createCell(1);
        cellRow1.setCellValue("description");
        
        Cell cellRow2 = row0.createCell(2);
        cellRow2.setCellValue("name");
        
        int rowIndex = 1;
        for(thang x : thangs) {
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
    @GetMapping("/khachhang/export")
    public ResponseEntity<byte[]> exportKhachHangExcel() throws IOException {
       
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");
        
        List<khachHang> khachHangs = khachHangRe.findAll();
        String[] title ={"id","name","email","address", "numberphone","note"};
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
        for(khachHang x : khachHangs) {
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
        
        List<Object[]> ob = ldttRe.getKhachHangctt();
        
        List<khachHang> khachHangs = new ArrayList<>();
        for(Object[] x :ob) {
        	khachHang kh = new khachHang(x);
        	if(!khachHangs.contains(kh)) {
        		khachHangs.add(kh);
        	}       	
        }
        
        String[] title ={"id","name","email","address", "numberphone","note"};
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
        for(khachHang x : khachHangs) {
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
        headers.setContentDispositionFormData("attachment", "danhsach.xlsx");
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/user/export/{id}")
    public ResponseEntity<?> exportKhachHangInfor(@PathVariable Long id) throws IOException {
       
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");
        
        
        
        List<luongDienTieuThu> ldtts = ldttRe.findByKhachHangId(id);
       
        
        String[] title ={"id","Chi so cu","Chi so moi","Thang", "Bac","Trang thai"};
        int cellIndex = 0;
        Row rowTitle = sheet.createRow(0);
        Cell cellTitle = rowTitle.createCell(2);
        cellTitle.setCellValue("Thông tin sử dụng điện của khách hàng");
        Row rowFirst = sheet.createRow(1);
        for(String x : title) {     	
        	Cell cell = rowFirst.createCell(cellIndex);
        	cell.setCellValue(x);
        	cellIndex++;
        	
        }
        int rowIndex = 2;
        for(luongDienTieuThu x : ldtts) {
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
    		cell4.setCellValue(x.getBacDien().getName());
    		
    		Cell cell5 = row.createCell(5);
    		cell5.setCellValue(x.getState() == "0" ? "Chua thanh toan" : " Da thanh toan");
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "thongtinsudung.xlsx");
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
//    @GetMapping("/sodien/export")
//    public ResponseEntity<?> exportSoDien() throws IOException {
//       
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Data");
//        
//        
//        
//      
//       
//        
//        String[] title ={"id","Chi so cu","Chi so moi","Thang", "Bac","Trang thai"};
//        int cellIndex = 0;
//        Row rowTitle = sheet.createRow(0);
//        Cell cellTitle = rowTitle.createCell(2);
//        cellTitle.setCellValue("Thông tin sử dụng điện của khách hàng");
//        Row rowFirst = sheet.createRow(1);
//        for(String x : title) {     	
//        	Cell cell = rowFirst.createCell(cellIndex);
//        	cell.setCellValue(x);
//        	cellIndex++;
//        	
//        }
//        int rowIndex = 2;
//        for(luongDienTieuThu x : ldtts) {
//        	Row row = sheet.createRow(rowIndex++);
//        	Cell cell = row.createCell(0);
//    		cell.setCellValue(x.getId());
//    		
//    		Cell cell1 = row.createCell(1);
//    		cell1.setCellValue(x.getCsc());
//    		
//    		Cell cell2 = row.createCell(2);
//    		cell2.setCellValue(x.getCsm()); 
//    		
//    		Cell cell3 = row.createCell(3);
//    		cell3.setCellValue(x.getThang().getName());
//    		
//    		Cell cell4 = row.createCell(4);
//    		cell4.setCellValue(x.getBacDien().getName());
//    		
//    		Cell cell5 = row.createCell(5);
//    		cell5.setCellValue(x.getState() == "0" ? "Chua thanh toan" : " Da thanh toan");
//        }
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        workbook.write(outputStream);
//        workbook.close();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
//        headers.setContentDispositionFormData("attachment", "thongtinsudung.xlsx");
//        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
//    }


}

