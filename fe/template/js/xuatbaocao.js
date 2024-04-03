const baocaotype = localStorage.getItem("baocao");

function showNam(nams) {
    const listNam = document.getElementById("nam");
    
    const html = nams.map(function(nam) {
        return `<option value="${nam.id}">Năm ${nam.name}</option>`;                     
    });
    listNam.addEventListener('change', function() {
        handleNam(listNam.value); // Gọi lại hàm handleNam với giá trị mới của năm
    });
    listNam.innerHTML = html.join('');
    handleNam(listNam.value);
}
function showUser(users) {
    const listUser = document.getElementById("khachhang");
    
    const html = users.map(function(user) {
        return `<option value="${user.id}">${user.name}</option>`;                     
    });
    listUser.addEventListener('change', function() {
        getListUserDetail(listUser.value);
        localStorage.setItem("user_id",listUser.value) // Gọi lại hàm handleNam với giá trị mới của năm
    });
    listUser.innerHTML = html.join('');
    // handleNam(listNam.value);
}

function showThang(thangs) {
    const listThang = document.getElementById("thang");
    const html = thangs.map(function(thang) {
        return `<option value="${thang.id}">Tháng ${thang.name}</option>`;                     
    });
    listThang.addEventListener('change', function() {
        getListLdtt(listThang.value); 
        getSumLdtt(listThang.value)
        localStorage.setItem("thang_id",listThang.value)
    });
    listThang.innerHTML = html.join('');
}

function handleNam(selectedValue) {
    // Gửi request để lấy danh sách các tháng trong năm đã chọn
    fetch(`http://localhost:8080/thang/all/${selectedValue}` )
        .then(function(response) {
            return response.json();
        })
        .then(function(thangs) {
            showThang(thangs);
        })
        .catch((err) => {
            console.log(err);
        });
}



function getNams() {
    fetch("http://localhost:8080/nam/all")
        .then(function(response) {
            return response.json();
        })
        .then(function(nams) {
            showNam(nams);
        })
        .catch((err) => {
            console.log(err);
        });
}

function getUsers() {
    fetch("http://localhost:8080/khachhang/user")
        .then(function(response) {
            return response.json();
        })
        .then(function(users) {
            showUser(users);
        })
        .catch((err) => {
            console.log(err);
        });
}

function showTableUser(users){
    const listUser = document.querySelector(".baocao_main_back");
   
    const html1 = `
    <table> 
    <tr>
      <th>ID</th>
      <th>Tên khách hàng</th>
      <th>Email</th>
      <th>Địa chỉ</th>
      <th>Số điện thoại</th>   
      <th>Ghi chú</th>
    </tr>
    <tbody class="table_user">
    </tbody>
    </table>`
    listUser.innerHTML = html1;
    const listTable = document.querySelector(".table_user");
    const html = users.map(function(user) {
        return `
        <tr>
          <td>${user.id}</td>
          <td>${user.name}</td>
          <td>${user.email}</td>
          <td>${user.address}</td>
          <td>${user.numberPhone}</td>    
          <td>${user.note}</td>

        </tr>
        
      `;                     
    });
   
    listTable.innerHTML = html.join('');
    
}

function showTableUserPayment(users){
  const listUser = document.querySelector(".baocao_main_back");
 
  const html1 = `
  <table> 
  <tr>
    <th>ID</th>
    <th>Tên khách hàng</th>
    <th>Email</th>
    <th>Địa chỉ</th>
    <th>Số điện thoại</th>   
    <th>Ghi chú</th>
    <th>Tháng</th>

  </tr>
  <tbody class="table_user">
  </tbody>
  </table>`
  listUser.innerHTML = html1;
  const listTable = document.querySelector(".table_user");
  const html = users.map(function(user) {
      return `
      <tr>
        <td>${user.khachHang.id}</td>
        <td>${user.khachHang.name}</td>
        <td>${user.khachHang.email}</td>
        <td>${user.khachHang.address}</td>
        <td>${user.khachHang.numberPhone}</td>    
        <td>${user.khachHang.note}</td>
        <td>${user.thang.name}</td>

      </tr>
      
    `;                     
  });
 
  listTable.innerHTML = html.join('');
  
}


function showTableUserDetail(users){
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
    </table>`
    listUser.innerHTML = html1;
    const listTable = document.querySelector(".table_user");
    const html = users.map(function(user) {
        let state = "";
        if(user.state === "0"){
            state = "Chưa thanh toán";
        }
        else{
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
   
    listTable.innerHTML = html.join('');
    
}

function showLdtt(users){
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
    </table>`
    listUser.innerHTML = html1;
    
    const listTable = document.querySelector(".table_user");
    const html = users.map(function(user) {
        let state = "";
        if(user.state === "0"){
            state = "Chưa thanh toán";
        }
        else{
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
   
    listTable.innerHTML = html.join('');
    
}
function sumLdtt(sum){
    const listUser = document.querySelector(".sum_text");
    console.log(listUser)
    listUser.innerHTML = `Tổng lượng điện tiêu thụ: ${sum} KWH`
    
}
function getListUserInfor(){
    fetch("http://localhost:8080/khachhang/user")
        .then(function(response) {
            return response.json();
        })
        .then(function(users) {
            showTableUser(users);
        })
        .catch((err) => {
            console.log(err);
        });
}
function getListUserPayment(){
    fetch("http://localhost:8080/khachhang/user/payment")
        .then(function(response) {
            return response.json();
        })
        .then(function(users) {
            showTableUserPayment(users);
        })
        .catch((err) => {
            console.log(err);
        });
}
function getListUserDetail(id){
    fetch(`http://localhost:8080/khachhang/detail/${id}`)
        .then(function(response) {
            return response.json();
        })
        .then(function(users) {
            showTableUserDetail(users);
        })
        .catch((err) => {
            console.log(err);
        });
}
function getListLdtt(id){
    fetch(`http://localhost:8080/ldtt/thang/${id}`)
        .then(function(response) {
            return response.json();
        })
        .then(function(users) {
            showLdtt(users);
        })
        .catch((err) => {
            console.log(err);
        });
}
function getSumLdtt(id){
    fetch(`http://localhost:8080/ldtt/sum/thang/${id}`)
        .then(function(response) {
            return response.json();
        })
        .then(function(users) {
            sumLdtt(users);
        })
        .catch((err) => {
            console.log(err);
        });
}
function exportSumLdtt() { 
    const id = localStorage.getItem("thang_id");
    const user_id = localStorage.getItem("user_id")
    if(baocaotype === "baocao1"){
        exportThongtinsudung(id);

    }
    else if(baocaotype === "baocao3"){
        exportListKh();

    }

    else if(baocaotype === "baocao4"){
        exportThongtinKhCTT();

    }
    else{
        exportThongtinsudungkh(user_id);
    }

}
function exportThongtinsudungkh(id){
    fetch(`http://localhost:8080/api/user/export/${id}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      // Có thể cần thêm các headers khác tùy thuộc vào yêu cầu của server
    },
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    return response.blob(); // Lấy dữ liệu dưới dạng Blob
  })
  .then(blob => {
    // Tạo một object URL từ Blob để tạo link tải về
    const url = window.URL.createObjectURL(new Blob([blob]));
  
    // Tạo một element <a> để tạo link tải về
    const a = document.createElement('a');
    a.href = url;
    a.download = 'thongtinsudungkh.xlsx'; // Tên file khi tải về
    document.body.appendChild(a); // Thêm element vào DOM
    a.click(); // Tự động click vào element để bắt đầu tải về
  })
  .catch(error => {
    console.error('There was an error!', error);
  });
}

function exportThongtinKhCTT(){
    fetch(`http://localhost:8080/api/payment/export`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      // Có thể cần thêm các headers khác tùy thuộc vào yêu cầu của server
    },
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    return response.blob(); // Lấy dữ liệu dưới dạng Blob
  })
  .then(blob => {
    // Tạo một object URL từ Blob để tạo link tải về
    const url = window.URL.createObjectURL(new Blob([blob]));
  
    // Tạo một element <a> để tạo link tải về
    const a = document.createElement('a');
    a.href = url;
    a.download = 'thongtinkhchuathanhtoan.xlsx'; // Tên file khi tải về
    document.body.appendChild(a); // Thêm element vào DOM
    a.click(); // Tự động click vào element để bắt đầu tải về
  })
  .catch(error => {
    console.error('There was an error!', error);
  });
}

function exportThongtinsudung(id){
    fetch(`http://localhost:8080/api/ldtt/export/${id}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      // Có thể cần thêm các headers khác tùy thuộc vào yêu cầu của server
    },
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    return response.blob(); // Lấy dữ liệu dưới dạng Blob
  })
  .then(blob => {
    // Tạo một object URL từ Blob để tạo link tải về
    const url = window.URL.createObjectURL(new Blob([blob]));
  
    // Tạo một element <a> để tạo link tải về
    const a = document.createElement('a');
    a.href = url;
    a.download = 'thongtinsudung.xlsx'; // Tên file khi tải về
    document.body.appendChild(a); // Thêm element vào DOM
    a.click(); // Tự động click vào element để bắt đầu tải về
  })
  .catch(error => {
    console.error('There was an error!', error);
  });
}

function exportThongtinKhCTT(){
    fetch(`http://localhost:8080/api/payment/export`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      // Có thể cần thêm các headers khác tùy thuộc vào yêu cầu của server
    },
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    return response.blob(); // Lấy dữ liệu dưới dạng Blob
  })
  .then(blob => {
    // Tạo một object URL từ Blob để tạo link tải về
    const url = window.URL.createObjectURL(new Blob([blob]));
  
    // Tạo một element <a> để tạo link tải về
    const a = document.createElement('a');
    a.href = url;
    a.download = 'thongtinkhchuathanhtoan.xlsx'; // Tên file khi tải về
    document.body.appendChild(a); // Thêm element vào DOM
    a.click(); // Tự động click vào element để bắt đầu tải về
  })
  .catch(error => {
    console.error('There was an error!', error);
  });
}

function exportListKh(){
    fetch(`http://localhost:8080/api/khachhang/export`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      // Có thể cần thêm các headers khác tùy thuộc vào yêu cầu của server
    },
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    return response.blob(); // Lấy dữ liệu dưới dạng Blob
  })
  .then(blob => {
    // Tạo một object URL từ Blob để tạo link tải về
    const url = window.URL.createObjectURL(new Blob([blob]));
  
    // Tạo một element <a> để tạo link tải về
    const a = document.createElement('a');
    a.href = url;
    a.download = 'listkh.xlsx'; // Tên file khi tải về
    document.body.appendChild(a); // Thêm element vào DOM
    a.click(); // Tự động click vào element để bắt đầu tải về
  })
  .catch(error => {
    console.error('There was an error!', error);
  });
}

function start() {
    getNams();
    getUsers();
}

start();

function nextPage(baocao){
    window.location.href = "xuatbaocao.html";
    localStorage.setItem("baocao",baocao);
}
if(baocaotype == "baocao1"){
    document.querySelector(".baocao_text").innerHTML="Báo cáo lượng điện tiêu thụ";
    document.getElementById("khachhang").classList.add("display_none")
}
else if(baocaotype == "baocao2"){
    document.querySelector(".baocao_text").innerHTML="Báo cáo tổng doanh thu";
    document.getElementById("khachhang").classList.add("display_none")
}
else if(baocaotype == "baocao3"){
    document.querySelector(".baocao_text").innerHTML="Báo cáo danh sách các hộ cá nhân sử dụng điện";
    document.getElementById("khachhang").classList.add("display_none")
    document.getElementById("nam").classList.add("display_none")
    document.getElementById("thang").classList.add("display_none")
    getListUserInfor();
}
else if(baocaotype == "baocao4"){
    document.querySelector(".baocao_text").innerHTML="Báo cáo danh sách các hộ cá nhân chưa thanh toán hóa đơn";
    document.getElementById("khachhang").classList.add("display_none")
    document.getElementById("nam").classList.add("display_none")
    document.getElementById("thang").classList.add("display_none")
    getListUserPayment();
}
else{
    document.querySelector(".baocao_text").innerHTML="Báo cáo thông tin tiêu thụ, sử dụng điện hộ cá nhân";
    document.getElementById("nam").classList.add("display_none")
    document.getElementById("thang").classList.add("display_none")
}



