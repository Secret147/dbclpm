const baocaotype = localStorage.getItem("baocao");
let total_export = 0;

function showNam(nams) {
  const listNam = document.getElementById("nam");
  const html1 = ``;
  const html = nams.map(function (nam) {
    return `<option value="${nam.id}">Năm ${nam.name}</option>`;
  });
  listNam.addEventListener("change", function () {
    handleNam(listNam.value);
  });
  const result = html1 + html;
  console.log(result);
  listNam.innerHTML = result;
  handleNam(listNam.value);
}
function showUser(users) {
  const listUser = document.getElementById("khachhang");
  const html1 = `<option value="">Chọn khách hàng</option>`;
  const html = users.map(function (user) {
    return `<option value="${user.id}">${user.name}</option>`;
  });
  listUser.addEventListener("change", function () {
    getListUserDetail(listUser.value);
    localStorage.setItem("user_id", listUser.value);
  });
  const result = html1 + html;
  console.log(result);
  listUser.innerHTML = result;
  // handleNam(listNam.value);
}

function showThang(thangs) {
  const listThang = document.getElementById("thang");
  const html1 = `<option value="">Chọn tháng</option>`;

  const html = thangs.map(function (thang) {
    return `
        <option value="${thang.id}">Tháng ${thang.name}</option>`;
  });
  listThang.addEventListener("change", function () {
    getListLdtt(listThang.value);
    getSumLdtt(listThang.value);
    localStorage.setItem("thang_id", listThang.value);
  });
  const result = html1 + html;
  console.log(result);

  listThang.innerHTML = result;
}

function handleNam(selectedValue) {
  // Gửi request để lấy danh sách các tháng trong năm đã chọn
  fetch(`http://localhost:8080/thang/all/${selectedValue}`)
    .then(function (response) {
      return response.json();
    })
    .then(function (thangs) {
      showThang(thangs);
    })
    .catch((err) => {
      console.log(err);
    });
}

function getNams() {
  fetch("http://localhost:8080/nam/all")
    .then(function (response) {
      return response.json();
    })
    .then(function (nams) {
      showNam(nams);
    })
    .catch((err) => {
      console.log(err);
    });
}

function getUsers() {
  fetch("http://localhost:8080/khachhang/user")
    .then(function (response) {
      return response.json();
    })
    .then(function (users) {
      showUser(users);
    })
    .catch((err) => {
      console.log(err);
    });
}

function showTableUser(users) {
  const listUser = document.querySelector(".baocao_main_back");

  const html1 = `
    <table> 
    <tr>
      <th>ID Khách hàng</th>
      <th>Tên khách hàng</th>
      <th>Email</th>
      <th>Địa chỉ</th>
      <th>Số điện thoại</th>   
      <th>Ghi chú</th>
    </tr>
    <tbody class="table_user">
    </tbody>
    </table>`;
  listUser.innerHTML = html1;
  const listTable = document.querySelector(".table_user");
  const html = users.map(function (user) {
    return `
        <tr>
          <td>${user.id}</td>
          <td>${user.name}</td>
          <td>${user.email}</td>
          <td>${user.address}</td>
          <td>${user.tel}</td>    
          <td>${user.note}</td>

        </tr>
        
      `;
  });

  listTable.innerHTML = html.join("");
}

function showTableTotal(bills) {
  const listUser = document.querySelector(".baocao_main_back");

  const html1 = `
  <table> 
  <tr>
    <th>ID Hóa đơn</th>
    <th>Tên khách hàng</th>
  
    <th>Tổng</th>
    <th>Ghi chú</th>   
   
  </tr>
  <tbody class="table_user">
  </tbody>
  </table>`;
  listUser.innerHTML = html1;
  const listTable = document.querySelector(".table_user");
  const html = bills.map(function (bill) {
    return `
      <tr>
        <td>${bill.id}</td>
        <td>${bill.khachHang.name}</td>
     
        <td>${bill.total}</td>
        <td>${bill.description}</td>    
      </tr>
      
    `;
  });

  listTable.innerHTML = html.join("");
}

function showTableUserPayment(users) {
  const listUser = document.querySelector(".baocao_main_back");

  const html1 = `
  <table> 
  <tr>
    <th>ID Khách hàng</th>
    <th>Tên khách hàng</th>
    <th>Email</th>
    <th>Địa chỉ</th>
    <th>Số điện thoại</th>   
    <th>Ghi chú</th>
    <th>Tháng</th>

  </tr>
  <tbody class="table_user">
  </tbody>
  </table>`;
  listUser.innerHTML = html1;
  const listTable = document.querySelector(".table_user");
  const html = users.map(function (user) {
    return `
      <tr>
        <td>${user.khachHang.id}</td>
        <td>${user.khachHang.name}</td>
        <td>${user.khachHang.email}</td>
        <td>${user.khachHang.address}</td>
        <td>${user.khachHang.tel}</td>    
        <td>${user.khachHang.note}</td>
        <td>${user.thang.name}</td>

      </tr>
      
    `;
  });

  listTable.innerHTML = html.join("");
}

function showTableUserDetail(users) {
  const listUser = document.querySelector(".baocao_main_back");

  const html1 = `
    <table> 
    <tr>
      <th>ID</th>
      <th>Khách hàng</th>
      <th>Chỉ số cũ</th>
      <th>Chỉ số mới</th>
      <th>Tháng</th>
      <th>Trạng thái</th>
    </tr>
    <tbody class="table_user">
    </tbody>
    </table>`;
  listUser.innerHTML = html1;
  const listTable = document.querySelector(".table_user");
  const html = users.map(function (user) {
    let state = "";
    if (user.state === "0") {
      state = "Chưa thanh toán";
    } else {
      state = "Đã thanh toán";
    }

    return `
        <tr>
          <td>${user.id}</td>
          <td>${user.khachHang.name}</td>
          <td>${user.csc}</td>
          <td>${user.csm}</td>
          <td>${user.thang.name}</td>
          <td>${state}</td>
        </tr>
        
      `;
  });

  listTable.innerHTML = html.join("");
}

function showLdtt(users) {
  const listUser = document.querySelector(".baocao_main_back");

  const html1 = `
    <table> 
    <tr>
      <th>ID</th>
      <th>Khách hàng</th>
      <th>Chỉ số cũ</th>
      <th>Chỉ số mới</th>
      <th>Tháng</th>
      
      <th>Trạng thái</th>
    </tr>
    <tbody class="table_user">
    </tbody>
    </table>`;
  listUser.innerHTML = html1;

  const listTable = document.querySelector(".table_user");
  const html = users.map(function (user) {
    let state = "";
    if (user.state === "0") {
      state = "Chưa thanh toán";
    } else {
      state = "Đã thanh toán";
    }

    return `
        <tr>
          <td>${user.id}</td>
          <td>${user.khachHang.name}</td>
          <td>${user.csc}</td>
          <td>${user.csm}</td>
          <td>${user.thang.name}</td>
          <td>${state}</td>
        </tr>
        
      `;
  });

  listTable.innerHTML = html.join("");
}
function sumLdtt(sum) {
  const listUser = document.querySelector(".sum_text");
  console.log(listUser);
  listUser.innerHTML = `Tổng lượng điện tiêu thụ: ${sum} KWH`;
}

function getListUserInfor() {
  fetch("http://localhost:8080/khachhang/user")
    .then(function (response) {
      return response.json();
    })
    .then(function (users) {
      showTableUser(users);
    })
    .catch((err) => {
      console.log(err);
    });
}

function getListTotal() {
  fetch("http://localhost:8080/hoadon/bill")
    .then(function (response) {
      return response.json();
    })
    .then(function (bills) {
      showTableTotal(bills);
    })
    .catch((err) => {
      console.log(err);
    });
}
function getListUserPayment() {
  fetch("http://localhost:8080/khachhang/user/payment")
    .then(function (response) {
      return response.json();
    })
    .then(function (users) {
      showTableUserPayment(users);
    })
    .catch((err) => {
      console.log(err);
    });
}
function getListUserDetail(id) {
  fetch(`http://localhost:8080/khachhang/detail/${id}`)
    .then(function (response) {
      return response.json();
    })
    .then(function (users) {
      showTableUserDetail(users);
    })
    .catch((err) => {
      console.log(err);
    });
}
function getListLdtt(id) {
  fetch(`http://localhost:8080/ldtt/thang/${id}`)
    .then(function (response) {
      return response.json();
    })
    .then(function (users) {
      showLdtt(users);
    })
    .catch((err) => {
      console.log(err);
    });
}
function getSumLdtt(id) {
  fetch(`http://localhost:8080/ldtt/sum/thang/${id}`)
    .then(function (response) {
      return response.json();
    })
    .then(function (users) {
      sumLdtt(users);
    })
    .catch((err) => {
      console.log(err);
    });
}

function getSumTotal() {
  fetch(`http://localhost:8080/hoadon/total`)
    .then(function (response) {
      return response.json();
    })
    .then(function (total) {
      const listUser = document.querySelector(".total_text");
      listUser.innerHTML = `Tổng doanh thu: ${total} VND`;
    })
    .catch((err) => {
      console.log(err);
    });
}
function exportSumLdtt() {
  const id = localStorage.getItem("thang_id");
  const user_id = localStorage.getItem("user_id");
  if (baocaotype === "baocao1") {
    exportThongtinsudung(id);
  } else if (baocaotype === "baocao2") {
    exportTotal();
  } else if (baocaotype === "baocao3") {
    exportListKh();
  } else if (baocaotype === "baocao4") {
    exportThongtinKhCTT();
  } else {
    exportThongtinsudungkh(user_id);
  }
}
function exportThongtinsudungkh(id) {
  if (id === "" || localStorage.getItem("user_id") === null) {
    alert("Vui lòng chọn khách hàng khác");
  } else {
    fetch(`http://localhost:8080/api/user/export/${id}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.blob();
      })
      .then((blob) => {
        const url = window.URL.createObjectURL(new Blob([blob]));

        const a = document.createElement("a");
        a.href = url;
        a.download = "thongtinsudungkh.xlsx";
        localStorage.removeItem("user_id");
        document.body.appendChild(a);
        a.click();
      })
      .catch((error) => {
        console.error("There was an error!", error);
        alert("Khách hàng chưa có thông tin sử dụng điện để xuất báo cáo");
      });
  }
}

function exportTotal() {
  fetch(`http://localhost:8080/api/total`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.blob();
    })
    .then((blob) => {
      const url = window.URL.createObjectURL(new Blob([blob]));

      const a = document.createElement("a");
      a.href = url;
      a.download = "thongtindoanhthu.xlsx";
      document.body.appendChild(a);
      a.click();
    })
    .catch((error) => {
      console.error("There was an error!", error);
    });
}

function exportThongtinKhCTT() {
  fetch(`http://localhost:8080/api/payment/export`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.blob();
    })
    .then((blob) => {
      const url = window.URL.createObjectURL(new Blob([blob]));

      const a = document.createElement("a");
      a.href = url;
      a.download = "thongtinkhchuathanhtoan.xlsx";
      document.body.appendChild(a);
      a.click();
    })
    .catch((error) => {
      console.error("There was an error!", error);
    });
}

function exportThongtinsudung(id) {
  if (id === "" || localStorage.getItem("thang_id") === null) {
    alert("Vui lòng chọn tháng khác");
  } else {
    fetch(`http://localhost:8080/api/ldtt/export/${id}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.blob();
      })
      .then((blob) => {
        const url = window.URL.createObjectURL(new Blob([blob]));
        const a = document.createElement("a");
        a.href = url;
        a.download = "thongtinsudung.xlsx";
        localStorage.removeItem("thang_id");
        document.body.appendChild(a);
        a.click();
      })
      .catch((error) => {
        console.error("There was an error!", error);
        alert("Tháng chưa có dữ liệu sử dụng điện để xuất báo cáo");
      });
  }
}

function exportThongtinKhCTT() {
  fetch(`http://localhost:8080/api/payment/export`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.blob();
    })
    .then((blob) => {
      const url = window.URL.createObjectURL(new Blob([blob]));

      const a = document.createElement("a");
      a.href = url;
      a.download = "thongtinkhchuathanhtoan.xlsx";
      document.body.appendChild(a);
      a.click();
    })
    .catch((error) => {
      console.error("There was an error!", error);
    });
}

function exportListKh() {
  fetch(`http://localhost:8080/api/khachhang/export`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.blob();
    })
    .then((blob) => {
      const url = window.URL.createObjectURL(new Blob([blob]));

      const a = document.createElement("a");
      a.href = url;
      a.download = "listkh.xlsx";
      document.body.appendChild(a);
      a.click();
    })
    .catch((error) => {
      console.error("There was an error!", error);
    });
}

function start() {
  getNams();
  getUsers();
}

start();

function nextPage(baocao) {
  window.location.href = "xuatbaocao.html";
  localStorage.setItem("baocao", baocao);
}
if (baocaotype == "baocao1") {
  document.querySelector(".baocao_text").innerHTML =
    "Báo cáo lượng điện tiêu thụ";
  document.getElementById("khachhang").classList.add("display_none");
} else if (baocaotype == "baocao2") {
  document.querySelector(".baocao_text").innerHTML = "Báo cáo tổng doanh thu";
  document.getElementById("khachhang").classList.add("display_none");
  document.getElementById("nam").classList.add("display_none");
  document.getElementById("thang").classList.add("display_none");
  getSumTotal();
  getListTotal();
} else if (baocaotype == "baocao3") {
  document.querySelector(".baocao_text").innerHTML =
    "Báo cáo danh sách các hộ cá nhân sử dụng điện";
  document.getElementById("khachhang").classList.add("display_none");
  document.getElementById("nam").classList.add("display_none");
  document.getElementById("thang").classList.add("display_none");
  getListUserInfor();
} else if (baocaotype == "baocao4") {
  document.querySelector(".baocao_text").innerHTML =
    "Báo cáo danh sách các hộ cá nhân chưa thanh toán hóa đơn";
  document.getElementById("khachhang").classList.add("display_none");
  document.getElementById("nam").classList.add("display_none");
  document.getElementById("thang").classList.add("display_none");
  getListUserPayment();
} else {
  document.querySelector(".baocao_text").innerHTML =
    "Báo cáo thông tin tiêu thụ, sử dụng điện hộ cá nhân";
  document.getElementById("nam").classList.add("display_none");
  document.getElementById("thang").classList.add("display_none");
}
