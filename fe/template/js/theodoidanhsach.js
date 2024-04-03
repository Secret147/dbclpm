//===========================================start==================================================================================
function start() {
  getDSNam();
}
//===========================================Các func populate cho bảng thống kê====================================================
/**
 * Hiện bảng thống kê
 * @param {*} dsTK 
 */
function populateTKTable(dsTK) {
  //Tạo table
  const tdds_table = document.querySelector("#tdds_table");
  let html_table =
    `<table> 
    <tr>
      <th>Mã khách hàng</th>
      <th>Địa chỉ</th>
      <th>Chỉ số cũ</th>
      <th>Chỉ số mới</th>
      `
  for (let i = 0; i < dsTK[0].dsTieuThuTheoBac.length; i++) {
    html_table += `<th>Bậc ${i + 1}</th>`;
  }

  html_table +=
    `<th> Tiền điện</th>
    <th>Trạng thái</th>
    </tr>
    <tbody id="#tdds_table_content">
    </tbody>
    </table> `

  tdds_table.innerHTML = html_table;

  //Tạo nội dung cho table
  const tdds_table_content = document.querySelector(".tdds_table_content");
  const html = dsTK.map(function (tk) {

    let trangThai = "";
    if (tk.trangThai === "-1") {
      trangThai = "Chưa có hóa đơn";
    } else if (tk.trangThai === "0") {
      trangThai = "Chưa thanh toán"
    } else {
      trangThai = "Đã thanh toán";
    }

    let rowHtml = `
    <tr>
      <td>${tk.khachHangId}</td>
      <td>${tk.khachHangDiaChi}</td>
      <td>${tk.oldValue}</td>
      <td>${tk.newValue}</td>`;

    for (let i = 0; i < tk.dsTieuThuTheoBac.length; i++) {
      rowHtml += `<td>${tk.dsTieuThuTheoBac[i].value}</td>`;
    }

    rowHtml += `
      <td>${tk.tienDien}</td>
      <td>${trangThai}</td>
    </tr>`;

    return rowHtml;
  });

  tdds_table_content.innerHTML = html.join('');
}
/**
 * Lấy danh sách thống kê theo thời gian và khu vực
 */
function getDSTK() {
  const namSelect = document.getElementById('nam');
  const thangSelect = document.getElementById('thang');
  const tinhSelect = document.getElementById('tinh');
  const huyenSelect = document.getElementById('huyen');
  const xaSelect = document.getElementById('xa');

  if (namSelect.value && thangSelect.value && tinhSelect.value && huyenSelect.value && xaSelect.value) {
    try {
      const response = fetch('api/list', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          tinhId: tinhSelect.value,
          huyenId: huyenSelect.value,
          xaId: xaSelect.value,
          tinhId: namSelect.value,
          thangId: thangSelect.value,
        }),
      });

      if (response.ok) {
        const data = response.json();
        populateTKTable(dsTK);
      } else {
        console.error('Error fetching data:', response.status);
      }
    } catch (error) {
      console.error('Error:', error);
    }
  } else {
    alert("Xin hãy chọn đủ thông tin");
  }
}

//===========================================func populate for time select======================================================
/**
 * Hiện các lựa chọn cho select năm
 * @param {} dsNam 
 */
function populateNamSelect(dsNam) {
  const namSelect = document.getElementById("namSelect");
  const html = dsNam.map(function (nam) {
    return `<option value="${nam.id}">Năm ${nam.name}</option>`;
  });

  //Lấy các lựa chọn tháng tương ứng với năm vừa chọn
  namSelect.addEventListener('change', function () {
    getDSThangByNam(namSelect.value);
  });

  namSelect.innerHTML = html.join('');
  getDSThangByNam(namSelect.value);
}

/**
 * Hiện các lựa chọn cho select tháng
 * @param {} dsThang 
 */
function populateThangSelect(dsThang) {
  const listThang = document.getElementById("thangSelect");
  const html = dsThang.map(function (thang) {
    return `<option value="${thang.id}">Tháng ${thang.name}</option>`;
  });
  listThang.innerHTML = html.join('');
}

/**
 * Lấy danh sách năm từ API
 */
function getDSNam() {
  fetch("http://localhost:8080/nam/all")
    .then(function (response) {
      return response.json();
    })
    .then(function (dsNam) {
      populateNamSelect(dsNam);
    })
    .catch((err) => {
      console.log(err);
    });
}

/**
 * Lấy danh sách các tháng trong năm đã chọn từ API
 * @param {*} namID 
 */
function getDSThangByNam(namID) {
  fetch(`http://localhost:8080/thang/all/${namID}`)
    .then(function (response) {
      return response.json();
    })
    .then(function (dsThang) {
      populateThangSelect(dsThang);
    })
    .catch((err) => {
      console.log(err);
    });
}
//===========================================Các func populate cho select location====================================================
/**
 * TODO:
 * - populateDSTinh
 * - populateDSHuyen
 * - populateDSXa
 */

/**
 * Hiện các lựa chọn cho select tỉnh/thành phố
 * @param {} dsNam 
 */
function populateTinhSelect(dsTinh) {
  const tinhSelect = document.getElementById("tinhSelect");
  const html = dsTinh.map(function (tinh) {
    return `<option value="${tinh.id}">Tỉnh ${tinh.name}</option>`;
  });

  //Lấy các lựa chọn quận/huyện tương ứng với năm vừa chọn
  tinhSelect.addEventListener('change', function () {
    getDSHuyenByTinh(tinhSelect.value);
  });

  tinhSelect.innerHTML = html.join('');
  getDSThangByNam(tinhSelect.value);
}


/**
 * Lấy danh sách tỉnh/thành phố từ API
 */
function getDSTinh(tinhID) {
  fetch("http://localhost:8080/api/${tinhID}")
    .then(function (response) {
      return response.json();
    })
    .then(function (dsTinh) {
      populateDSTinh(dsTinh);
    })
    .catch((err) => {
      console.log(err);
    });
}

/**
 * Hiện các lựa chọn cho select quận/huyện theo tỉnh/thành phố vừa chọn
 * @param {*} dsHuyen 
 */
function populateDSHuyen(dsHuyen) {
  const huyenSelect = document.getElementById("huyenSelect");
  const html = dsHuyen.map(function (huyen) {
    return `<option value="${huyen.id}">Huyện ${huyen.name}</option>`;
  });

  //Lấy các lựa chọn phường/xã tương ứng với năm vừa chọn
  huyenSelect.addEventListener('change', function () {
    getDSXaByHuyen(huyenSelect.value);
  });

  huyenSelect.innerHTML = html.join('');
  getDSXaByHuyen(huyenSelect.value);
}


/**
 * Lấy danh sách quận/huyện theo tỉnh/thành phố từ API
 * @param {*} tinhID 
 */
function getDSHuyenByTinh(tinhID) {
  fetch(`http://localhost:8080/api/tinh/${tinhID}/huyen`)
    .then(function (response) {
      return response.json();
    })
    .then(function (dsHuyen) {
      populateDSHuyen(dsHuyen);
    })
    .catch((err) => {
      console.log(err);
    });
}

/**
 * Hiện các lựa chọn xã của huyện vừa chọn
 * @param {} dsXa 
 */
function populateDSXa(dsXa) {
  const xaSelect = document.getElementById("xaSelect");
  const html = dsXa.map(function (xa) {
    return `<option value="${xa.id}">Xã ${xa.name}</option>`;
  });
  xaSelect.innerHTML = html.join('');
}

/**
 * Lấy danh sách phường/xã theo quận/huyện từ API
 * @param {*} huyenID 
 */
function getDSXaByHuyen(huyenID) {
  fetch(`http://localhost:8080/api/huyen/${huyenID}/xa`)
    .then(function (response) {
      return response.json();
    })
    .then(function (dsXa) {
      populateDSXa(dsXa);
    })
    .catch((err) => {
      console.log(err);
    });
}
