package dbclpm;

import dbclpm.entity.HoaDon;
import dbclpm.entity.LuongDienTieuThu;
import dbclpm.entity.Nam;
import dbclpm.entity.Thang;
import dbclpm.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestTheoDoiDanhSach {

    @Autowired
    private ThangRepo thangRepo;
    @Autowired
    private NamRepo namRepo;
    @Autowired
    private HoaDonRepo hoaDonRepo;
    @Autowired
    private LuongDienTieuThuRepo luongDienTieuThuRepo;
    @Autowired
    private KhachHangRepo khachHangRepo;
    @Autowired
    private NhanVienRepo nhanVienRepo;

    // Test phương thức findById()
    /**
     * Test trường hợp tháng có tồn tại
     */
    @Test
    public void testThangTonTai() {
        //Input & Expected Output
        long thangId = 1;
        Thang expectedThang = new Thang();
        expectedThang.setId(thangId);
        expectedThang.setName("1");
        expectedThang.setDescription("January");
        expectedThang.setNam(namRepo.findById(1L).orElse(null)); //Giả sử luôn đúng

        //Test
        Thang thang = thangRepo.findById(thangId).orElse(null);
        assertThat(thang).isEqualTo(expectedThang);
    }

    /**
     * Test trường hợp tháng không tồn tại
     */
    @Test
    public void testThangKhongTonTai() {
        //Input & Expected Output
        long thangId = 1000;

        //Test
        Thang thang = thangRepo.findById(thangId).orElse(null);
        assertThat(thang).isNull();
    }

    // Test phương thức findByNamId()
    /**
     * Test trường hợp năm có tồn tại
     */
    @Test
    public void testNamTonTai() {
        //Input & Expected Output
        long namId = 1;
        Nam expectedNam = new Nam();
        expectedNam.setId(namId);
        expectedNam.setName("2021");
        expectedNam.setDescription("Year 2021");

        //Test
        Nam nam = namRepo.findById(namId).orElse(null);
        assertThat(nam).isEqualTo(expectedNam);
    }

    /**
     * Test trường hợp năm không tồn tại
     */
    @Test
    public void testNamKhongTonTai() {
        //Input & Expected Output
        long namId = 1000;

        //Test
        Nam nam = namRepo.findById(namId).orElse(null);
        assertThat(nam).isNull();
    }

    // Test phương thức findByKhachHangXaIdAndLuongDienTieuThuThangId()
    /**
     * Test trường hợp có một hóa đơn tồn tại
     */
    @Test
    public void testMotHoaDonTonTai() {
        //Input & Expected Output
        long xaId = 7; //Mo Lao
        long thangId = 1;
        HoaDon expectedHoaDon = new HoaDon();
        expectedHoaDon.setId(10);
        expectedHoaDon.setCreatedAt(Date.valueOf(LocalDate.of(2021, 1, 30)));
        expectedHoaDon.setTotal(92400);
        expectedHoaDon.setLuongDienTieuThu(luongDienTieuThuRepo.findById(13L).orElse(null)); // Giả sử luôn đúng
        expectedHoaDon.setKhachHang(khachHangRepo.findById(1L).orElse(null)); // Giả sử luôn đúng
        expectedHoaDon.setNhanVien(nhanVienRepo.findById(1L).orElse(null)); // Giả sử luôn đúng
        expectedHoaDon.setDescription(null);



        //Test
        List<HoaDon> hoaDonList = hoaDonRepo.findByKhachHangXaIdAndLuongDienTieuThuThangId(xaId, thangId);
        assertThat(hoaDonList.size()).isEqualTo(1);
        HoaDon hoaDon = hoaDonList.get(0);
        assertThat(hoaDon.getId()).isEqualTo(expectedHoaDon.getId());
        assertThat(hoaDon.getCreatedAt()).isCloseTo(expectedHoaDon.getCreatedAt(), 1000);
        assertThat(hoaDon.getTotal()).isEqualTo(expectedHoaDon.getTotal());
        assertThat(hoaDon.getLuongDienTieuThu().getId()).isEqualTo(expectedHoaDon.getLuongDienTieuThu().getId());
        assertThat(hoaDon.getKhachHang().getId()).isEqualTo(expectedHoaDon.getKhachHang().getId());
        assertThat(hoaDon.getNhanVien().getId()).isEqualTo(expectedHoaDon.getNhanVien().getId());
        assertThat(hoaDon.getDescription()).isEqualTo(expectedHoaDon.getDescription());
    }

    /**
     * Test trường hợp có nhiều hóa đơn tồn tại
     */
    @Test
    public void testNhieuHoaDonTonTai() {
        //Input & Expected Output
        long xaId = 7; //Mo Lao
        long thangId = 2;

        HoaDon expectedHoaDon1 = new HoaDon();
        expectedHoaDon1.setId(11);
        expectedHoaDon1.setCreatedAt(Date.valueOf(LocalDate.of(2021, 2, 28)));
        expectedHoaDon1.setTotal(92400);
        expectedHoaDon1.setLuongDienTieuThu(luongDienTieuThuRepo.findById(14L).orElse(null)); // Giả sử luôn đúng
        expectedHoaDon1.setKhachHang(khachHangRepo.findById(1L).orElse(null)); // Giả sử luôn đúng
        expectedHoaDon1.setNhanVien(nhanVienRepo.findById(1L).orElse(null)); // Giả sử luôn đúng
        expectedHoaDon1.setDescription(null);

        HoaDon expectedHoaDon2 = new HoaDon();
        expectedHoaDon2.setId(12);
        expectedHoaDon2.setCreatedAt(Date.valueOf(LocalDate.of(2021, 2, 28)));
        expectedHoaDon2.setTotal(92400);
        expectedHoaDon2.setLuongDienTieuThu(luongDienTieuThuRepo.findById(15L).orElse(null)); // Giả sử luôn đúng
        expectedHoaDon2.setKhachHang(khachHangRepo.findById(2L).orElse(null)); // Giả sử luôn đúng
        expectedHoaDon2.setNhanVien(nhanVienRepo.findById(1L).orElse(null)); // Giả sử luôn đúng
        expectedHoaDon2.setDescription(null);

        //Test
        List<HoaDon> hoaDonList = hoaDonRepo.findByKhachHangXaIdAndLuongDienTieuThuThangId(xaId, thangId);
        assertThat(hoaDonList.size()).isEqualTo(2);

        //hoaDon1
        HoaDon hoaDon1 = hoaDonList.get(0);
        assertThat(hoaDon1.getId()).isEqualTo(expectedHoaDon1.getId());
        assertThat(hoaDon1.getCreatedAt()).isCloseTo(expectedHoaDon1.getCreatedAt(), 1000);
        assertThat(hoaDon1.getTotal()).isEqualTo(expectedHoaDon1.getTotal());
        assertThat(hoaDon1.getLuongDienTieuThu().getId()).isEqualTo(expectedHoaDon1.getLuongDienTieuThu().getId());
        assertThat(hoaDon1.getKhachHang().getId()).isEqualTo(expectedHoaDon1.getKhachHang().getId());
        assertThat(hoaDon1.getNhanVien().getId()).isEqualTo(expectedHoaDon1.getNhanVien().getId());
        assertThat(hoaDon1.getDescription()).isEqualTo(expectedHoaDon1.getDescription());

        //hoaDon2
        HoaDon hoaDon2 = hoaDonList.get(1);
        assertThat(hoaDon2.getId()).isEqualTo(expectedHoaDon2.getId());
        assertThat(hoaDon2.getCreatedAt()).isCloseTo(expectedHoaDon2.getCreatedAt(), 1000);
        assertThat(hoaDon2.getTotal()).isEqualTo(expectedHoaDon2.getTotal());
        assertThat(hoaDon2.getLuongDienTieuThu().getId()).isEqualTo(expectedHoaDon2.getLuongDienTieuThu().getId());
        assertThat(hoaDon2.getKhachHang().getId()).isEqualTo(expectedHoaDon2.getKhachHang().getId());
        assertThat(hoaDon2.getNhanVien().getId()).isEqualTo(expectedHoaDon2.getNhanVien().getId());
        assertThat(hoaDon2.getDescription()).isEqualTo(expectedHoaDon2.getDescription());
    }

    /**
     * Test xã tồn tại, tháng không tồn tại
     */
    @Test
    public void testHoaDonXaTonTaiThangKhongTonTai() {
        //Input & Expected Output
        long xaId = 7; //Mo Lao
        long thangId = 1000;

        //Test
        List<HoaDon> hoaDonList = hoaDonRepo.findByKhachHangXaIdAndLuongDienTieuThuThangId(xaId, thangId);
        assertThat(hoaDonList.size()).isEqualTo(0);
    }

    /**
     * Test xã không tồn tại, tháng tồn tại
     */
    @Test
    public void testHoaDonXaKhongTonTaiThangTonTai() {
        //Input & Expected Output
        long xaId = 1000;
        long thangId = 1;

        //Test
        List<HoaDon> hoaDonList = hoaDonRepo.findByKhachHangXaIdAndLuongDienTieuThuThangId(xaId, thangId);
        assertThat(hoaDonList.size()).isEqualTo(0);
    }

    /**
     * Test xã không tồn tại, tháng không tồn tại
     */
    @Test
    public void testHoaDonXaKhongTonTaiThangKhongTonTai() {
        //Input & Expected Output
        long xaId = 1000;
        long thangId = 1000;

        //Test
        List<HoaDon> hoaDonList = hoaDonRepo.findByKhachHangXaIdAndLuongDienTieuThuThangId(xaId, thangId);
        assertThat(hoaDonList.size()).isEqualTo(0);
    }

    // Test phương thức findByKhachHangXaIdAndThangId()

    /**
     * Test trường hợp có một lượng điện tiêu thụ tồn tại
     */
    @Test
    public void testMotLuongDienTieuThuTonTai() {
        //Input & Expected Output
        long xaId = 7; //Mo Lao
        long thangId = 41;
        LuongDienTieuThu expectedLuongDienTieuThu = new LuongDienTieuThu();
        expectedLuongDienTieuThu.setId(16L);
        expectedLuongDienTieuThu.setCsc(100L);
        expectedLuongDienTieuThu.setCsm(150L);
        expectedLuongDienTieuThu.setThang(thangRepo.findById(41L).orElse(null));
        expectedLuongDienTieuThu.setKhachHang(khachHangRepo.findById(1L).orElse(null));
        expectedLuongDienTieuThu.setState("-1");

        //Test
        List<LuongDienTieuThu> luongDienTieuThuList = luongDienTieuThuRepo.findByKhachHangXaIdAndThangId(xaId, thangId);
        assertThat(luongDienTieuThuList.size()).isEqualTo(1);
        LuongDienTieuThu luongDienTieuThu = luongDienTieuThuList.get(0);
        assertThat(luongDienTieuThu.getId()).isEqualTo(expectedLuongDienTieuThu.getId());
        assertThat(luongDienTieuThu.getCsc()).isEqualTo(expectedLuongDienTieuThu.getCsc());
        assertThat(luongDienTieuThu.getCsm()).isEqualTo(expectedLuongDienTieuThu.getCsm());
        assertThat(luongDienTieuThu.getThang().getId()).isEqualTo(expectedLuongDienTieuThu.getThang().getId());
        assertThat(luongDienTieuThu.getKhachHang().getId()).isEqualTo(expectedLuongDienTieuThu.getKhachHang().getId());
        assertThat(luongDienTieuThu.getState()).isEqualTo(expectedLuongDienTieuThu.getState());
    }

    /**
     * Test trường hợp có nhiều lượng điện tiêu thụ tồn tại
     */
    @Test
    public void testNhieuLuongDienTieuThuTonTai() {
        //Input & Expected Output
        long xaId = 7; //Mo Lao
        long thangId = 42;

        LuongDienTieuThu expectedLuongDienTieuThu1 = new LuongDienTieuThu();
        expectedLuongDienTieuThu1.setId(17L);
        expectedLuongDienTieuThu1.setCsc(100L);
        expectedLuongDienTieuThu1.setCsm(150L);
        expectedLuongDienTieuThu1.setThang(thangRepo.findById(42L).orElse(null));
        expectedLuongDienTieuThu1.setKhachHang(khachHangRepo.findById(1L).orElse(null));
        expectedLuongDienTieuThu1.setState("-1");

        LuongDienTieuThu expectedLuongDienTieuThu2 = new LuongDienTieuThu();
        expectedLuongDienTieuThu2.setId(18L);
        expectedLuongDienTieuThu2.setCsc(100L);
        expectedLuongDienTieuThu2.setCsm(150L);
        expectedLuongDienTieuThu2.setThang(thangRepo.findById(42L).orElse(null));
        expectedLuongDienTieuThu2.setKhachHang(khachHangRepo.findById(2L).orElse(null));
        expectedLuongDienTieuThu2.setState("-1");

        //Test
        List<LuongDienTieuThu> luongDienTieuThuList = luongDienTieuThuRepo.findByKhachHangXaIdAndThangId(xaId, thangId);
        assertThat(luongDienTieuThuList.size()).isEqualTo(2);

        //luongDienTieuThu1
        LuongDienTieuThu luongDienTieuThu1 = luongDienTieuThuList.get(0);
        assertThat(luongDienTieuThu1.getId()).isEqualTo(expectedLuongDienTieuThu1.getId());
        assertThat(luongDienTieuThu1.getCsc()).isEqualTo(expectedLuongDienTieuThu1.getCsc());
        assertThat(luongDienTieuThu1.getCsm()).isEqualTo(expectedLuongDienTieuThu1.getCsm());
        assertThat(luongDienTieuThu1.getThang().getId()).isEqualTo(expectedLuongDienTieuThu1.getThang().getId());
        assertThat(luongDienTieuThu1.getKhachHang().getId()).isEqualTo(expectedLuongDienTieuThu1.getKhachHang().getId());
        assertThat(luongDienTieuThu1.getState()).isEqualTo(expectedLuongDienTieuThu1.getState());

        //luongDienTieuThu2
        LuongDienTieuThu luongDienTieuThu2 = luongDienTieuThuList.get(1);
        assertThat(luongDienTieuThu2.getId()).isEqualTo(expectedLuongDienTieuThu2.getId());
        assertThat(luongDienTieuThu2.getCsc()).isEqualTo(expectedLuongDienTieuThu2.getCsc());
        assertThat(luongDienTieuThu2.getCsm()).isEqualTo(expectedLuongDienTieuThu2.getCsm());
        assertThat(luongDienTieuThu2.getThang().getId()).isEqualTo(expectedLuongDienTieuThu2.getThang().getId());
        assertThat(luongDienTieuThu2.getKhachHang().getId()).isEqualTo(expectedLuongDienTieuThu2.getKhachHang().getId());
        assertThat(luongDienTieuThu2.getState()).isEqualTo(expectedLuongDienTieuThu2.getState());
    }

    /**
     * Test xã không tồn tại, tháng không tồn tại
     */
    @Test
    public void testLDTTXaTonTaiThangTonTai() {
        //Input & Expected Output
        long xaId = 7; //Mo Lao
        long thangId = 1000;

        //Test
        List<LuongDienTieuThu> luongDienTieuThuList = luongDienTieuThuRepo.findByKhachHangXaIdAndThangId(xaId, thangId);
        assertThat(luongDienTieuThuList.size()).isEqualTo(0);
    }

    /**
     * Test xã không tồn tại, tháng tồn tại
     */
    @Test
    public void testLDTTXaKhongTonTaiThangTonTai() {
        //Input & Expected Output
        long xaId = 1000;
        long thangId = 41;

        //Test
        List<LuongDienTieuThu> luongDienTieuThuList = luongDienTieuThuRepo.findByKhachHangXaIdAndThangId(xaId, thangId);
        assertThat(luongDienTieuThuList.size()).isEqualTo(0);
    }

    /**
     * Test xã tồn tại, tháng không tồn tại
     */
    @Test
    public void testLDTTXaTonTaiThangKhongTonTai() {
        //Input & Expected Output
        long xaId = 7; //Mo Lao
        long thangId = 1000;

        //Test
        List<LuongDienTieuThu> luongDienTieuThuList = luongDienTieuThuRepo.findByKhachHangXaIdAndThangId(xaId, thangId);
        assertThat(luongDienTieuThuList.size()).isEqualTo(0);
    }
}
