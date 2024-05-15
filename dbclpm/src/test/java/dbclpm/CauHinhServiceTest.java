package dbclpm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import dbclpm.entity.BacDien;
import dbclpm.repository.BacDienRepo;
import dbclpm.service.BacDienService;
import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback(true)
public class CauHinhServiceTest {
	@Autowired
	private BacDienService bacDienService;
//	@Autowired
//	private BacDienRepo repo;

	@Test
	public void testAllBacDien() throws Exception {
		// Kiểm tra lấy ra danh sách khách hàng
		List<BacDien> bacdiens = bacDienService.getAllBacDien();

		assertEquals(bacdiens.size() > 0, true);

	}

	@Test
	public void testIdBacDien() throws Exception {
		BacDien bacdien = bacDienService.findBacDienById(1);

		assertEquals(bacdien != null, true);

	}

	// loi test voi gia tri bac dien khong dung TODO: sua thanh 100
	@Test
	public void testfindPreviousBacDien() throws Exception {
		// Kiểm tra lấy ra danh sách khách hàng
		BacDien bacdien = bacDienService.findPreviousBacDien(50);

		assertEquals(bacdien != null, true);

	}

	@Test
	public void testFindNextBacDien() throws Exception {
		// Kiểm tra lấy ra danh sách khách hàng
		BacDien bacdien = bacDienService.findNextBacDien(1);

		assertEquals(bacdien != null, true);

	}

	@Test
	public void testAddNewBacDien() {
		// Tạo đối tượng BacDien để thêm vào cơ sở dữ liệu
		BacDien newBacDien = new BacDien();
		newBacDien.setStartValue(0);
		newBacDien.setEndValue(100);
		newBacDien.setName("Bac moi");
		newBacDien.setPrice(1000L);
		newBacDien.setDescription("Bac moi mo ta");

		// Gọi phương thức để thêm bậc điện mới và kiểm tra kết quả
		assertTrue(bacDienService.addNewBacDien(newBacDien));

		// Lấy ID của bậc điện sau khi thêm vào cơ sở dữ liệu
		BacDien maxIdBacDien = bacDienService.getMaxBacDien();
		Long newBacDienId = newBacDien.getId();

		assertEquals(maxIdBacDien.getStartValue(), newBacDien.getStartValue());
		assertEquals(maxIdBacDien.getEndValue(), newBacDien.getEndValue());
		assertEquals(maxIdBacDien.getName(), newBacDien.getName());
		assertEquals(maxIdBacDien.getPrice(), newBacDien.getPrice());
		assertEquals(maxIdBacDien.getDescription(), newBacDien.getDescription());

		// @Rollback(true): da co ham nay de k lưu lại csdl
	}

	/// Test bậc điện khi thêm bậc mới thì số tiền bậc mới cần lớn hơn số tiền bậc
	/// cao nhất và
	/// chỉ số
	@Test
	public void testAddNewBacDienChiSoThapHonChiSoCu() {
		BacDien maxIdBacDien = bacDienService.getMaxBacDien();
		System.out.println(maxIdBacDien);
		// Tạo đối tượng BacDien để thêm vào cơ sở dữ liệu
		BacDien newBacDien = new BacDien();
		newBacDien.setStartValue(0);
		newBacDien.setEndValue(100);
		newBacDien.setName("Bac moi");
		newBacDien.setPrice(1000L);
		newBacDien.setDescription("Bac moi mo ta");

		// Gọi phương thức để thêm bậc điện mới và kiểm tra kết quả
		assertTrue(bacDienService.addNewBacDien(newBacDien));

		// Lấy ID của bậc điện sau khi thêm vào cơ sở dữ liệu

		Long newBacDienId = newBacDien.getId();

		assertEquals(newBacDien.getStartValue() - maxIdBacDien.getEndValue() > 0, true);
		assertEquals(newBacDien.getPrice() - maxIdBacDien.getPrice() > 0, true);

		// @Rollback(true): da co ham nay de k lưu lại csdl
	}

//	Test chỉ số bậc điện thêm vào end nhỏ hơn start
	@Test
	public void testAddNewBacDienChiSoStartLonHonChiSoEnd() {
		// Tạo đối tượng BacDien để thêm vào cơ sở dữ liệu
		BacDien newBacDien = new BacDien();
		newBacDien.setStartValue(100);
		newBacDien.setEndValue(50);
		newBacDien.setName("Bac moi");
		newBacDien.setPrice(1000L);
		newBacDien.setDescription("Bac moi mo ta");

		// Gọi phương thức để thêm bậc điện mới và kiểm tra kết quả
		assertTrue(bacDienService.addNewBacDien(newBacDien));

		// Lấy ID của bậc điện sau khi thêm vào cơ sở dữ liệu

		Long newBacDienId = newBacDien.getId();

		assertEquals(newBacDien.getStartValue() - newBacDien.getEndValue() > 0, false);

		// @Rollback(true): da co ham nay de k lưu lại csdl
	}

//	Test delete bậc điện, UC: chỉ được delete bậc điện có ID lớn nhất
	@Test
	public void testDeleteBacDien() {
		// Tạo đối tượng BacDien để thêm vào cơ sở dữ liệu
		BacDien newBacDien = new BacDien();
		newBacDien.setStartValue(0);
		newBacDien.setEndValue(100);
		newBacDien.setName("Bac moi");
		newBacDien.setPrice(1000L);
		newBacDien.setDescription("Bac moi mo ta");

		// Gọi phương thức để thêm bậc điện mới và kiểm tra kết quả
		assertTrue(bacDienService.addNewBacDien(newBacDien));

//        // Lấy bậc điện có ID lớn nhất sau khi thêm vào cơ sở dữ liệu
		BacDien maxIdBacDien = bacDienService.getMaxBacDien();

		// Kiểm tra điều kiện trước khi xóa

		// Thử xóa bậc điện có ID lớn nhất và kiểm tra kết quả
		assertTrue(bacDienService.deleteBacDien(maxIdBacDien.getId()));

		// Kiểm tra lại xem bậc điện có ID lớn nhất đã bị xóa chưa
		BacDien getMaxBacDien = bacDienService.getMaxBacDien();
//        System.out.println(getMaxBacDien);
//        System.out.println(newBacDien);
		assertNotEquals(maxIdBacDien.getId(), getMaxBacDien.getId());
	}

//	Test delete bậc điện, UC: delete bậc điện có id nhỏ hơn max
	@Test
	public void testDeleteBacDienId() {
		// Tạo đối tượng BacDien để thêm vào cơ sở dữ liệu
		BacDien newBacDien = new BacDien();
		newBacDien.setStartValue(0);
		newBacDien.setEndValue(100);
		newBacDien.setName("Bac moi");
		newBacDien.setPrice(1000L);
		newBacDien.setDescription("Bac moi mo ta");

		// Gọi phương thức để thêm bậc điện mới và kiểm tra kết quả
		assertTrue(bacDienService.addNewBacDien(newBacDien));

//        // Lấy bậc điện có ID lớn nhất sau khi thêm vào cơ sở dữ liệu
		BacDien maxIdBacDien = bacDienService.getMaxBacDien();

		// Kiểm tra điều kiện trước khi xóa

		// Thử xóa bậc điện có ID lớn nhất và kiểm tra kết quả
		assertFalse(bacDienService.deleteBacDien(maxIdBacDien.getId() - 1));

	}

//	Test thay đổi số tiền và chỉ số cuối của bậc cao nhất
	@Test
	public void testUpdateMaxIdBacDien() {

		BacDien newBacDien1 = new BacDien();
		newBacDien1.setStartValue(1001);
		newBacDien1.setEndValue(1010);
		newBacDien1.setName("Bac 7");
		newBacDien1.setPrice(4000L);
		newBacDien1.setDescription("Bac moi mo ta");
		BacDien newBacDien2 = new BacDien();
		newBacDien2.setStartValue(1011);
		newBacDien2.setEndValue(1100);
		newBacDien2.setName("Bac 8");
		newBacDien2.setPrice(5000L);
		newBacDien2.setDescription("Bac moi mo ta");

		assertTrue(bacDienService.addNewBacDien(newBacDien1));
		assertTrue(bacDienService.addNewBacDien(newBacDien2));
		// sua thang 2
		newBacDien2.setDescription("Mo ta moi");
		newBacDien2.setEndValue(1101);
		newBacDien2.setPrice(6000L);
		// xu ly update thang bac 2

		assertTrue(bacDienService.validateAndUpdateBacDien(newBacDien2, newBacDien1, null, newBacDien2));
	}

//	 Test thay đổi số tiền thấp hơn bậc cũ
	@Test
	public void testUpdateMaxIdBacDienThapHonBacCu() {

		BacDien newBacDien1 = new BacDien();
		newBacDien1.setStartValue(1001);
		newBacDien1.setEndValue(1010);
		newBacDien1.setName("Bac 7");
		newBacDien1.setPrice(4000L);
		newBacDien1.setDescription("Bac moi mo ta");
		BacDien newBacDien2 = new BacDien();
		newBacDien2.setStartValue(1011);
		newBacDien2.setEndValue(1100);
		newBacDien2.setName("Bac 8");
		newBacDien2.setPrice(5000L);
		newBacDien2.setDescription("Bac moi mo ta");

		assertTrue(bacDienService.addNewBacDien(newBacDien1));
		assertTrue(bacDienService.addNewBacDien(newBacDien2));
		// sua thang 2
		newBacDien2.setDescription("Mo ta moi");
		newBacDien2.setEndValue(1101);
		newBacDien2.setPrice(4000L);
		// xu ly update thang bac 2

		assertFalse(bacDienService.validateAndUpdateBacDien(newBacDien2, newBacDien1, null, newBacDien2));
	}

//	 Test thay đổi startValue bậc cao thấp hơn endValue bậc cũ
	@Test
	public void testUpdateMaxIdBacDienStartValueThapHonEndValue() {

		BacDien newBacDien1 = new BacDien();
		newBacDien1.setStartValue(1001);
		newBacDien1.setEndValue(1010);
		newBacDien1.setName("Bac 7");
		newBacDien1.setPrice(4000L);
		newBacDien1.setDescription("Bac moi mo ta");
		BacDien newBacDien2 = new BacDien();
		newBacDien2.setStartValue(1011);
		newBacDien2.setEndValue(1100);
		newBacDien2.setName("Bac 8");
		newBacDien2.setPrice(5000L);
		newBacDien2.setDescription("Bac moi mo ta");

		assertTrue(bacDienService.addNewBacDien(newBacDien1));
		assertTrue(bacDienService.addNewBacDien(newBacDien2));
		// sua thang 2
		newBacDien2.setDescription("Mo ta moi");
		newBacDien2.setEndValue(1009);
		newBacDien2.setPrice(6000L);
		// xu ly update thang bac 2

		assertFalse(bacDienService.validateAndUpdateBacDien(newBacDien2, newBacDien1, null, newBacDien2));
	}

//	 Test thay đôi giá trị bat dau bac giua nho hon gia tri ket thuc bac truoc no
	@Test
	public void testUpdateMaxIdBacDienStart2NhoHonEnd1() {

		BacDien newBacDien1 = new BacDien();
		newBacDien1.setStartValue(1001);
		newBacDien1.setEndValue(1010);
		newBacDien1.setName("Bac 7");
		newBacDien1.setPrice(4000L);
		newBacDien1.setDescription("Bac moi mo ta");
		BacDien newBacDien2 = new BacDien();
		newBacDien2.setStartValue(1011);
		newBacDien2.setEndValue(1100);
		newBacDien2.setName("Bac 8");
		newBacDien2.setPrice(5000L);
		newBacDien2.setDescription("Bac moi mo ta");
		BacDien newBacDien3 = new BacDien();
		newBacDien3.setStartValue(1101);
		newBacDien3.setEndValue(1200);
		newBacDien3.setName("Bac 9");
		newBacDien3.setPrice(6000L);
		newBacDien3.setDescription("Bac moi mo ta");

		assertTrue(bacDienService.addNewBacDien(newBacDien1));
		assertTrue(bacDienService.addNewBacDien(newBacDien2));
		assertTrue(bacDienService.addNewBacDien(newBacDien3));
		// sua thang 2
		newBacDien2.setDescription("Mo ta moi");
		newBacDien2.setStartValue(1009);

		// xu ly update thang bac 2

		assertFalse(bacDienService.validateAndUpdateBacDien(newBacDien2, newBacDien1, newBacDien3, newBacDien2));
	}

//	 Test thay đôi giá trị ket thuc bac 2 lon hon gia tri bat dau bac 3
	@Test
	public void testUpdateMaxIdBacDienEnd2LonHonStart3() {

		BacDien newBacDien1 = new BacDien();
		newBacDien1.setStartValue(1001);
		newBacDien1.setEndValue(1010);
		newBacDien1.setName("Bac 7");
		newBacDien1.setPrice(4000L);
		newBacDien1.setDescription("Bac moi mo ta");
		BacDien newBacDien2 = new BacDien();
		newBacDien2.setStartValue(1011);
		newBacDien2.setEndValue(1100);
		newBacDien2.setName("Bac 8");
		newBacDien2.setPrice(5000L);
		newBacDien2.setDescription("Bac moi mo ta");
		BacDien newBacDien3 = new BacDien();
		newBacDien3.setStartValue(1101);
		newBacDien3.setEndValue(1200);
		newBacDien3.setName("Bac 9");
		newBacDien3.setPrice(6000L);
		newBacDien3.setDescription("Bac moi mo ta");

		assertTrue(bacDienService.addNewBacDien(newBacDien1));
		assertTrue(bacDienService.addNewBacDien(newBacDien2));
		assertTrue(bacDienService.addNewBacDien(newBacDien3));
		// sua thang 2
		newBacDien2.setDescription("Mo ta moi");
		newBacDien2.setEndValue(1102);

		// xu ly update thang bac 2

		assertFalse(bacDienService.validateAndUpdateBacDien(newBacDien2, newBacDien1, newBacDien3, newBacDien2));
	}

//	 Test thay đôi giá trị ket thuc bac 2 bang gia tri bat dau 3
	@Test
	public void testUpdateMaxIdBacDienEnd2BangStart3() {

		BacDien newBacDien1 = new BacDien();
		newBacDien1.setStartValue(1001);
		newBacDien1.setEndValue(1010);
		newBacDien1.setName("Bac 7");
		newBacDien1.setPrice(4000L);
		newBacDien1.setDescription("Bac moi mo ta");
		BacDien newBacDien2 = new BacDien();
		newBacDien2.setStartValue(1011);
		newBacDien2.setEndValue(1100);
		newBacDien2.setName("Bac 8");
		newBacDien2.setPrice(5000L);
		newBacDien2.setDescription("Bac moi mo ta");
		BacDien newBacDien3 = new BacDien();
		newBacDien3.setStartValue(1101);
		newBacDien3.setEndValue(1200);
		newBacDien3.setName("Bac 9");
		newBacDien3.setPrice(6000L);
		newBacDien3.setDescription("Bac moi mo ta");

		assertTrue(bacDienService.addNewBacDien(newBacDien1));
		assertTrue(bacDienService.addNewBacDien(newBacDien2));
		assertTrue(bacDienService.addNewBacDien(newBacDien3));
		// sua thang 2
		newBacDien2.setDescription("Mo ta moi");
		newBacDien2.setEndValue(1101);

		// xu ly update thang bac 2

		assertFalse(bacDienService.validateAndUpdateBacDien(newBacDien2, newBacDien1, newBacDien3, newBacDien2));
	}

//	 Test thay đôi giá tien bac 2 lon hon gia tien bac 3
	@Test
	public void testUpdateMaxIdBacDienPrice2LonPrice3() {

		BacDien newBacDien1 = new BacDien();
		newBacDien1.setStartValue(1001);
		newBacDien1.setEndValue(1010);
		newBacDien1.setName("Bac 7");
		newBacDien1.setPrice(4000L);
		newBacDien1.setDescription("Bac moi mo ta");
		BacDien newBacDien2 = new BacDien();
		newBacDien2.setStartValue(1011);
		newBacDien2.setEndValue(1100);
		newBacDien2.setName("Bac 8");
		newBacDien2.setPrice(5000L);
		newBacDien2.setDescription("Bac moi mo ta");
		BacDien newBacDien3 = new BacDien();
		newBacDien3.setStartValue(1101);
		newBacDien3.setEndValue(1200);
		newBacDien3.setName("Bac 9");
		newBacDien3.setPrice(6000L);
		newBacDien3.setDescription("Bac moi mo ta");

		assertTrue(bacDienService.addNewBacDien(newBacDien1));
		assertTrue(bacDienService.addNewBacDien(newBacDien2));
		assertTrue(bacDienService.addNewBacDien(newBacDien3));
		// sua thang 2
		newBacDien2.setDescription("Mo ta moi");
		newBacDien2.setPrice(6500L);

		// xu ly update thang bac 2

		assertFalse(bacDienService.validateAndUpdateBacDien(newBacDien2, newBacDien1, newBacDien3, newBacDien2));
	}

	// Test thay đôi giá tien bac 2 bang gia tien 3
	@Test
	public void testUpdateMaxIdBacDienPrice2BangPrice3() {

		BacDien newBacDien1 = new BacDien();
		newBacDien1.setStartValue(1001);
		newBacDien1.setEndValue(1010);
		newBacDien1.setName("Bac 7");
		newBacDien1.setPrice(4000L);
		newBacDien1.setDescription("Bac moi mo ta");
		BacDien newBacDien2 = new BacDien();
		newBacDien2.setStartValue(1011);
		newBacDien2.setEndValue(1100);
		newBacDien2.setName("Bac 8");
		newBacDien2.setPrice(5000L);
		newBacDien2.setDescription("Bac moi mo ta");
		BacDien newBacDien3 = new BacDien();
		newBacDien3.setStartValue(1101);
		newBacDien3.setEndValue(1200);
		newBacDien3.setName("Bac 9");
		newBacDien3.setPrice(6000L);
		newBacDien3.setDescription("Bac moi mo ta");

		assertTrue(bacDienService.addNewBacDien(newBacDien1));
		assertTrue(bacDienService.addNewBacDien(newBacDien2));
		assertTrue(bacDienService.addNewBacDien(newBacDien3));
		// sua thang 2
		newBacDien2.setDescription("Mo ta moi");
		newBacDien2.setPrice(6000L);

		// xu ly update thang bac 2

		assertFalse(bacDienService.validateAndUpdateBacDien(newBacDien2, newBacDien1, newBacDien3, newBacDien2));
	}

	// Test thay đôi giá tien bac 2 bang gia tien 1
	@Test
	public void testUpdateMaxIdBacDienPrice2BangPrice1() {

		BacDien newBacDien1 = new BacDien();
		newBacDien1.setStartValue(1001);
		newBacDien1.setEndValue(1010);
		newBacDien1.setName("Bac 7");
		newBacDien1.setPrice(4000L);
		newBacDien1.setDescription("Bac moi mo ta");
		BacDien newBacDien2 = new BacDien();
		newBacDien2.setStartValue(1011);
		newBacDien2.setEndValue(1100);
		newBacDien2.setName("Bac 8");
		newBacDien2.setPrice(5000L);
		newBacDien2.setDescription("Bac moi mo ta");
		BacDien newBacDien3 = new BacDien();
		newBacDien3.setStartValue(1101);
		newBacDien3.setEndValue(1200);
		newBacDien3.setName("Bac 9");
		newBacDien3.setPrice(6000L);
		newBacDien3.setDescription("Bac moi mo ta");

		assertTrue(bacDienService.addNewBacDien(newBacDien1));
		assertTrue(bacDienService.addNewBacDien(newBacDien2));
		assertTrue(bacDienService.addNewBacDien(newBacDien3));
		// sua thang 2
		newBacDien2.setDescription("Mo ta moi");
		newBacDien2.setPrice(4000L);

		// xu ly update thang bac 2

		assertFalse(bacDienService.validateAndUpdateBacDien(newBacDien2, newBacDien1, newBacDien3, newBacDien2));
	}

	@Test
	public void testUpdateMaxIdBacDienPrice2NhoPrice1() {

		BacDien newBacDien1 = new BacDien();
		newBacDien1.setStartValue(1001);
		newBacDien1.setEndValue(1010);
		newBacDien1.setName("Bac 7");
		newBacDien1.setPrice(4000L);
		newBacDien1.setDescription("Bac moi mo ta");
		BacDien newBacDien2 = new BacDien();
		newBacDien2.setStartValue(1011);
		newBacDien2.setEndValue(1100);
		newBacDien2.setName("Bac 8");
		newBacDien2.setPrice(5000L);
		newBacDien2.setDescription("Bac moi mo ta");
		BacDien newBacDien3 = new BacDien();
		newBacDien3.setStartValue(1101);
		newBacDien3.setEndValue(1200);
		newBacDien3.setName("Bac 9");
		newBacDien3.setPrice(6000L);
		newBacDien3.setDescription("Bac moi mo ta");

		assertTrue(bacDienService.addNewBacDien(newBacDien1));
		assertTrue(bacDienService.addNewBacDien(newBacDien2));
		assertTrue(bacDienService.addNewBacDien(newBacDien3));
		// sua thang 2
		newBacDien2.setDescription("Mo ta moi");
		newBacDien2.setPrice(3000L);

		// xu ly update thang bac 2

		assertFalse(bacDienService.validateAndUpdateBacDien(newBacDien2, newBacDien1, newBacDien3, newBacDien2));
	}
	// Test thay đổi thông tin thằng giữa
	@Test
	public void testUpdateMaxIdBacDienChangeInfoMiddel() {

		BacDien newBacDien1 = new BacDien();
		newBacDien1.setStartValue(1001);
		newBacDien1.setEndValue(1010);
		newBacDien1.setName("Bac 7");
		newBacDien1.setPrice(4000L);
		newBacDien1.setDescription("Bac moi mo ta");
		BacDien newBacDien2 = new BacDien();
		newBacDien2.setStartValue(1011);
		newBacDien2.setEndValue(1100);
		newBacDien2.setName("Bac 8");
		newBacDien2.setPrice(5000L);
		newBacDien2.setDescription("Bac moi mo ta");
		BacDien newBacDien3 = new BacDien();
		newBacDien3.setStartValue(1101);
		newBacDien3.setEndValue(1200);
		newBacDien3.setName("Bac 9");
		newBacDien3.setPrice(6000L);
		newBacDien3.setDescription("Bac moi mo ta");

		assertTrue(bacDienService.addNewBacDien(newBacDien1));
		assertTrue(bacDienService.addNewBacDien(newBacDien2));
		assertTrue(bacDienService.addNewBacDien(newBacDien3));
		// sua thang 2
		newBacDien2.setDescription("Mo ta moi 2");
		

		// xu ly update thang bac 2

		assertTrue(bacDienService.validateAndUpdateBacDien(newBacDien2, newBacDien1, newBacDien3, newBacDien2));
	}

}
