package dbclpm;

import dbclpm.entity.*;
import dbclpm.repository.*;
import dbclpm.service.KhachHangService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ThongBaoTest {
    @Autowired
    private KhachHangService khachhangSe;
    @Autowired
    private KhachHangRepo khachHangRepo;
    @Autowired
    private LuongDienTieuThuRepo luongDienTieuThuRepo;
    @Autowired
    private NamRepo namRepo;
    @Autowired
    private ThangRepo thangRepo;
    @Autowired
    private TinhRepo tinhRepo;
    @Autowired
    private HuyenRepo huyenRepo;
    @Autowired
    private XaRepo xaRepo;

    private Tinh tinh;
    private Huyen huyen;
    private Xa xa;
    private Nam nam;
    private Thang thang;
    private KhachHang khachHang1, khachHang2;
    private LuongDienTieuThu luongDienTieuThu1, luongDienTieuThu2;

    @BeforeEach
    public void setUp(){
        tinh = new Tinh();
        tinh.setName("Tinh 1");
        tinhRepo.save(tinh);

        huyen = new Huyen();
        huyen.setName("Huyen 1");
        huyen.setTinh(tinh);
        huyenRepo.save(huyen);

        xa = new Xa();
        xa.setName("Xa 1");
        xa.setHuyen(huyen);
        xaRepo.save(xa);

        nam = new Nam();
        nam.setName("2024");
        namRepo.save(nam);

        thang = new Thang();
        thang.setName("3");
        thang.setNam(nam);
        thangRepo.save(thang);

        khachHang1 = new KhachHang();
        khachHang1.setName("Nguyen Van A");
        khachHang1.setXa(xa);
        khachHangRepo.save(khachHang1);

        khachHang2 = new KhachHang();
        khachHang2.setName("Nguyen Van B");
        khachHang2.setXa(xa);
        khachHangRepo.save(khachHang2);

        luongDienTieuThu1 = new LuongDienTieuThu();
        luongDienTieuThu1.setCsc(100L);
        luongDienTieuThu1.setCsm(150L);
        luongDienTieuThu1.setThang(thang);
        luongDienTieuThu1.setKhachHang(khachHang1);
        luongDienTieuThu1.setState("0");
        luongDienTieuThuRepo.save(luongDienTieuThu1);

        luongDienTieuThu2 = new LuongDienTieuThu();
        luongDienTieuThu2.setCsc(134L);
        luongDienTieuThu2.setCsm(160L);
        luongDienTieuThu2.setThang(thang);
        luongDienTieuThu2.setKhachHang(khachHang2);
        luongDienTieuThu1.setState("1");
        luongDienTieuThuRepo.save(luongDienTieuThu2);
    }

    @Test
    public void testFindByXaId_found(){
        //Xã tồn tại
        List<KhachHang> khachHangs = khachHangRepo.findByXaId(xa.getId());
        assertThat(khachHangs).isNotEmpty();
        assertThat(khachHangs.size()).isEqualTo(2);
    }

    @Test
    public void testFindByXaId_notFound(){
        // Xã không tồn tại
        List<KhachHang> khachHangs = khachHangRepo.findByXaId(999L);
        assertThat(khachHangs).isEmpty();
    }

    @Test
    public void testFindByXaId_isNull(){
        //Xã id có giá trị null
        List<KhachHang> khachHangs = khachHangRepo.findByXaId(null);
        assertThat(khachHangs).isEmpty();
    }

    @Test
    public void testFindByHuyenId_found(){
        List<KhachHang> khachHangs = khachHangRepo.findByHuyenId(huyen.getId());
        assertThat(khachHangs).isNotEmpty();
        assertThat(khachHangs.size()).isEqualTo(2);
    }

    @Test
    public void testFindByHuyenId_notFound(){
        List<KhachHang> khachHangs = khachHangRepo.findByHuyenId(999L);
        assertThat(khachHangs).isEmpty();
    }

    @Test
    public void testFindByHuyenId_isNull(){
        List<KhachHang> khachHangs = khachHangRepo.findByHuyenId(null);
        assertThat(khachHangs).isEmpty();
    }

    @Test
    public void testFindByTinhId_found(){
        List<KhachHang> khachHangs = khachHangRepo.findByTinhId(tinh.getId());
        assertThat(khachHangs).isNotEmpty();
        assertThat(khachHangs.size()).isEqualTo(2);
    }

    @Test
    public void testFindByTinhId_notFound(){
        List<KhachHang> khachHangs = khachHangRepo.findByTinhId(999L);
        assertThat(khachHangs).isEmpty();
    }

    @Test
    public void testFindByTinhId_isNull(){
        List<KhachHang> khachHangs = khachHangRepo.findByTinhId(null);
        assertThat(khachHangs).isEmpty();
    }

    @Test
    public void testFindByThangIdAndState_ValidThangIdAndState() {
        //Tháng id tồn tại và state hợp lệ (0, 1, -1)
        List<LuongDienTieuThu> results = luongDienTieuThuRepo.findByThangIdAndState(thang.getId(), "0");
        assertThat(results).isNotEmpty();
        assertThat(results.size()).isEqualTo(1);
    }

    @Test
    public void testFindByThangIdAndState_InvalidThangId() {
        //Tháng id không tồn tai và state hợp lệ
        List<LuongDienTieuThu> results = luongDienTieuThuRepo.findByThangIdAndState(999L, "0");
        assertThat(results).isEmpty();
    }
    @Test
    public void testFindByThangIdAndState_InvalidState() {
        //Tháng id tồn tại và state không hợp lệ
        String state = "INVALID_STATE";
        List<LuongDienTieuThu> results = luongDienTieuThuRepo.findByThangIdAndState(thang.getId(), state);
        assertThat(results).isEmpty();
    }

    @Test
    public void testFindByXaOrHuyenOrTinh_ValidXa_Huyen_Tinh() {
        Map<String, Object> params = new HashMap<>();
        params.put("xaId", xa.getId());
        params.put("huyenId", huyen.getId());
        params.put("tinhId", tinh.getId());

        List<KhachHang> results = khachhangSe.findByXaOrHuyenOrTinh(params);

        assertThat(results).isNotNull();
        assertThat(results).isNotEmpty();
        assertThat(results.size()).isEqualTo(2);
    }

    @Test
    public void testFindByXaOrHuyenOrTinh_ValidHuyen_Tinh() {
        Map<String, Object> params = new HashMap<>();
        params.put("huyenId", huyen.getId());
        params.put("tinhId", tinh.getId());

        List<KhachHang> results = khachhangSe.findByXaOrHuyenOrTinh(params);

        assertThat(results).isNotNull();
        assertThat(results).isNotEmpty();
        assertThat(results.size()).isEqualTo(2);
    }

    @Test
    public void testFindByXaOrHuyenOrTinh_ValidTinh() {
        Map<String, Object> params = new HashMap<>();
        params.put("tinhId", tinh.getId());

        List<KhachHang> results = khachhangSe.findByXaOrHuyenOrTinh(params);

        assertThat(results).isNotNull();
        assertThat(results).isNotEmpty();
        assertThat(results.size()).isEqualTo(2);
    }

    @Test
    public void testFindByXaOrHuyenOrTinh_NoParams() {
        Map<String, Object> params = new HashMap<>();

        List<KhachHang> results = khachhangSe.findByXaOrHuyenOrTinh(params);

        assertThat(results).isNull();
    }


}
