
// Button chi tiết và thu gon 
const btnDown = document.querySelectorAll('.btn-down');

btnDown.forEach( btn =>{
   btn.addEventListener('click',() =>{
      const detaiRow = btn.closest('tr').nextElementSibling;
      const isActive = btn.classList.contains('active');
      const allDetails = document.querySelectorAll('.detail-row');
      // đóng tất cả trước khi mở tr mới 
      allDetails.forEach( detail =>{
         detail.classList.remove('active');
      });

      document.querySelectorAll('.btn-down').forEach( b =>{
         b.classList.remove('active');
         b.innerHTML = '<i class="fa-solid fa-eye-slash"></i> thu gọn';         
      })

      if(!isActive){
         detaiRow.classList.add('active');
         btn.classList.add('active');
         btn.innerHTML = '<i class="fa-solid fa-eye"></i>Chi Tiết ';
      }
   })
})


// button reset 
function resetForm(){
     window.location.href="/admin/manageProduct"
}


// Hàm tái sử dụng update size and stock
function ViewSizeAnhdStock(detailCard){
    const detaiTr = detailCard.closest('tr');
    const productTr = detaiTr.previousElementSibling;
    
    let total = 0;
    detailCard.querySelectorAll('.stock-span').forEach(
        s => total += parseInt(s.textContent) || 0
    );

    const stockCell = productTr.querySelector('.product-stock');
    if (stockCell){
        stockCell.textContent = total;
    }

    // Cập nhật size
    const sizeList = [];
    detailCard.querySelectorAll('.size-span').forEach(
        s => sizeList.push(s.textContent.trim())
    );
    const  sizeDisplay = productTr.querySelector('.product-size-list');
    if(sizeDisplay){
        sizeDisplay.textContent = sizeList.join(',');
    }

}


// trường hợp cho chỉnh sửa size và stock
document.addEventListener('click', (e) => {

    const btnAddProductV = e.target.closest('.btn-search-detail');
    if (btnAddProductV) {
        console.log("click");
        const formProductId = btnAddProductV.closest('.add-size-box');
        const detailCard = formProductId.closest('.detail-card');
        const detailTr = detailCard.closest('tr');
        const detailRow = detailTr.previousElementSibling;
        if (!formProductId) {
            alert("Không tìm thấy form");
            return;
        }
        
        const addProductId = formProductId.dataset.id;
        const newSize = formProductId.querySelector('.newSize')?.value.trim();
        const newStock = parseInt(formProductId.querySelector('.newStock')?.value);
        const price = document.querySelector('.product-price').dataset.price;
        if (!newSize || isNaN(newStock) || newStock <= 0) {
            alert("Nhập đúng dữ liệu (Size và Stock > 0)!");
            return;
        }

        fetch('/admin/manageProduct/addVariants', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({productId: addProductId, size: newSize, stock: newStock, price: price})
        })
        .then(r => r.json())
        .then(data => {
            if (data.success) {
                 const tbody = detailCard.querySelector('.detail-table tbody');
                if (tbody) {
                    const newRow = document.createElement('tr');
                    newRow.classList.add('size-stock-row');
                    newRow.dataset.id = data.variantId
                    newRow.innerHTML = `
                        <td><span class="size-span">${newSize}</span><input type="text" class="edit-size-input" style="display:none"></td>
                        <td><span class="stock-span">${newStock}</span><input type="number" class="edit-stock-input" style="display:none"></td>
                        <td class="button-action-size">
                            <button class="edit-size-stock" data-id="${data.variantId}"><i class="fa-solid fa-pen edit"></i></button>
                            <button class="delete-size-stock" data-mode="delete"><i class="fa-solid fa-trash-can trash"></i></button>
                        </td>
                    `;
                    tbody.appendChild(newRow);
                    formProductId.querySelector('.newSize').value = "";
                    formProductId.querySelector('.newStock').value = "";
                    alert("Thêm thành công");
                }
            } else {
                alert("Lỗi: " + data.message);
            }
        })
        .catch(() => alert('Không thể kết nối server'));
    }

    // ===== Xử lý nút Edit =====
    const btnEdit = e.target.closest('.edit-size-stock');
    if (btnEdit) {
        const row        = btnEdit.closest('.size-stock-row');
        const btnDelete  = row.querySelector('.delete-size-stock');
        const inputSize  = row.querySelector('.edit-size-input');
        const inputStock = row.querySelector('.edit-stock-input');
        const spanSize   = row.querySelector('.size-span');
        const spanStock  = row.querySelector('.stock-span');

        if (!btnEdit.classList.contains('editing')) {
            inputSize.value  = spanSize.textContent.trim();
            inputStock.value = spanStock.textContent.trim();
            spanSize.style.display   = 'none';
            spanStock.style.display  = 'none';
            inputSize.style.display  = 'block';
            inputStock.style.display = 'block';
            btnEdit.classList.add('editing');
            btnEdit.innerHTML      = '<i class="fa-solid fa-check"></i>';
            btnDelete.innerHTML    = '<i class="fa-solid fa-xmark"></i>';
            btnDelete.dataset.mode = 'cancel';
        } else {
            const variantId = btnEdit.dataset.id;
            const newSize   = inputSize.value.trim();
            const newStock  = parseInt(inputStock.value);
            fetch('/admin/manageProduct/updateVariant', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ id: variantId, size: newSize, stock: newStock })
            })
            .then(r => r.json())
            .then(data => {
                if (data.success) {
                    spanSize.textContent     = newSize;
                    spanStock.textContent    = newStock;
                    spanSize.style.display   = 'block';
                    spanStock.style.display  = 'block';
                    inputSize.style.display  = 'none';
                    inputStock.style.display = 'none';
                    btnEdit.classList.remove('editing');
                    btnEdit.innerHTML      = '<i class="fa-solid fa-pen edit"></i>';
                    btnDelete.innerHTML    = '<i class="fa-solid fa-trash-can trash"></i>';
                    btnDelete.dataset.mode = 'delete';

                    alert("cập nhật thành công");
                    //   tính tổng sau khi cập nhât

                    const detailCard = row.closest('.detail-card');
                    ViewSizeAnhdStock(detailCard);
                } else {
                    alert('Lỗi: ' + data.message);
                }
            })
            .catch(() => alert('Không thể kết nối server'));
        }
        return; // ← quan trọng: dừng, không chạy xuống phần delete
    }

    // ===== Xử lý nút Delete/Cancel =====
    const btnDelete = e.target.closest('.delete-size-stock');
    if (!btnDelete){
        return;
    } 

    if (btnDelete.dataset.mode === 'cancel') {
        const row        = btnDelete.closest('.size-stock-row');
        const btnEdit    = row.querySelector('.edit-size-stock');
        const inputSize  = row.querySelector('.edit-size-input');
        const inputStock = row.querySelector('.edit-stock-input');
        const spanSize   = row.querySelector('.size-span');
        const spanStock  = row.querySelector('.stock-span');

        spanSize.style.display   = 'block';
        spanStock.style.display  = 'block';
        inputSize.style.display  = 'none';
        inputStock.style.display = 'none';
        btnEdit.classList.remove('editing');
        btnEdit.innerHTML      = '<i class="fa-solid fa-pen edit"></i>';
        btnDelete.innerHTML    = '<i class="fa-solid fa-trash-can trash"></i>';
        btnDelete.dataset.mode = 'delete';
    } else {
        const row = btnDelete.closest('.size-stock-row');
        const variantId = row.dataset.id;
        console.log(variantId);
        if(!confirm("Bạn có chắc muốn xóa không ")){
            return;
        }
        fetch('/admin/manageProduct/deleteStockAndSize',{
            method:'POST',
            headers: {'Content-Type' : 'application/json'},
            body: JSON.stringify({id: variantId})
        })
        .then(r => r.json())
        .then(data =>{
            if(data.success){
                const detailCard = row.closest('.detail-card');
                row.remove();
                ViewSizeAnhdStock(detailCard);
            }else{

            }
        })
        .catch(() => alert("không thể kết nối"));
    }

    

});

