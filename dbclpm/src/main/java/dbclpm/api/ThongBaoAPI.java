package dbclpm.api;

import dbclpm.dto.EmailDetailDTO;
import dbclpm.dto.LuongDienTieuThuDTO;
import dbclpm.entity.BacDien;
import dbclpm.entity.KhachHang;
import dbclpm.service.BacDienService;
import dbclpm.service.EmailService;
import dbclpm.service.KhachHangService;
import dbclpm.service.LuongDienTieuThuService;
import dbclpm.ultilities.tien_dien.BacDienUtil;
import dbclpm.ultilities.tien_dien.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/notice")
@CrossOrigin
public class ThongBaoAPI {
    @Autowired
    private LuongDienTieuThuService luongDienTieuThuService;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private BacDienService bacDienService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private DateUtil dateUtil;
    @Autowired
    private BacDienUtil bacDienUtil;

    @GetMapping("/pay_bill/")
    public ResponseEntity<?> getListCustomerAndLdtt(@RequestParam Long id){
        List<LuongDienTieuThuDTO> luongDienTieuThuDTOS = luongDienTieuThuService.findLdttByThang(id);
        return ResponseEntity.ok(luongDienTieuThuDTOS);
    }
    @PostMapping("/pay_bill/send")
    public void sendMailPayBill(@RequestBody List<LuongDienTieuThuDTO> luongDienTieuThuDTOS){
        for(LuongDienTieuThuDTO l : luongDienTieuThuDTOS){
            String recipientEmail = l.getEmail();
            String subject = "Thông báo đóng tiền điện tháng " + l.getMonth() + "/" + l.getYear();
            String body = "Xin chào " + l.getCustomerName() + ",\n\n" +
                          "Đây là thông báo về việc đóng tiền điện của bạn.\n" +
                          "Tháng này hộ gia đình của bạn đã tiêu thụ " + l.getCstt() + " kwh (Csc : " + l.getCsc() + ", Csm : " + l.getCsm() + ").\n" +
                          "Số tiền cần thanh toán là: " + l.getTienDien() + " đồng\n"+
                          "Xin vui lòng thanh toán trước ngày " + dateUtil.setDueDate(10, Integer.valueOf(l.getMonth()), Integer.valueOf(l.getYear()))+ ".\n" +
                          "Trân trọng!";
            EmailDetailDTO emailDetailDTO = new EmailDetailDTO();
            emailDetailDTO.setRecipient(recipientEmail);
            emailDetailDTO.setSubject(subject);
            emailDetailDTO.setMsgBody(body);

            emailService.sendSimpleMail(emailDetailDTO);
        }
    }

    @PostMapping("/stop_power/send")
    public void  sendMailStopPower(@RequestParam Map<String, Object> params){
        List<KhachHang> khachHangs = khachHangService.findByXaOrHuyenOrTinh(params);
        String startDate = params.get("startDate").toString();
        String startTime = params.get("startTime").toString();
        String endDate = params.get("endDate").toString();
        String endTime = params.get("endTime").toString();
        String reason = params.get("reason").toString();

        for(KhachHang kh : khachHangs){
            String recipientEmail = kh.getEmail();
            String subject = "Thông báo ngừng cấp điện tạm thời";
            String body = "Ngừng cấp điện tạm thời tại khu vực xã " + kh.getXa().getName() + " huyện " + kh.getXa().getHuyen().getName() + " tỉnh " + kh.getXa().getHuyen().getTinh().getName() + ".\n" +
                          "Thời gian: từ " + startTime + " ngày " + startDate + " đến " + endTime + " ngày " + endDate + ".\n" +
                          "Lý do: " + reason + ".\n" +
                          "Kính mong quý khách hàng thông cảm!!!";
            EmailDetailDTO emailDetailDTO = new EmailDetailDTO();
            emailDetailDTO.setRecipient(recipientEmail);
            emailDetailDTO.setSubject(subject);
            emailDetailDTO.setMsgBody(body);

            emailService.sendSimpleMail(emailDetailDTO);
        }
    }

    @PostMapping("/change_price/send")
    public String sendMailChangePrice(@RequestParam String applyDate){
        List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
        List<BacDien> bacDiens = bacDienService.getAllBacDien();
        if(bacDiens == null) return "Sent fail";
        String tableBacDien = bacDienUtil.generateElectricityTierText(bacDiens);
        for(KhachHang kh : khachHangs){
            String recipientEmail = kh.getEmail();
            String subject = "Thông báo thay đổi giá cả điện.";
            String body = "Bảng giá điện mới.\n" +
                          "Áp dùng từ ngày " + applyDate + ".\n\n" + tableBacDien;

            EmailDetailDTO emailDetailDTO = new EmailDetailDTO();
            emailDetailDTO.setRecipient(recipientEmail);
            emailDetailDTO.setSubject(subject);
            emailDetailDTO.setMsgBody(body);

            emailService.sendSimpleMail(emailDetailDTO);
        }
        return "Sent mail successfully!!!";
    }
}
