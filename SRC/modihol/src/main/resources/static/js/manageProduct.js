
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


// update size and stock
// trường hợp cho chỉnh sửa size và stock
const editSizeStockButtons = document.querySelectorAll('.edit-size-stock');
editSizeStockButtons.forEach(btnEdit =>{
   btnEdit.addEventListener('click', ()=>{
      const rowEdit = btnEdit.closest('.size-stock-row');
      const btnDelete = rowEdit.querySelector('.delete-size-stock');
      const inputSize = rowEdit.querySelector('.edit-size-input');
      const inputStock = rowEdit.querySelector('.edit-stock-input');
      const spanSize = rowEdit.querySelector('.size-span');
      const spanStock = rowEdit.querySelector('.stock-span');
      const isEditing = btnEdit.classList.contains('editing');

      if(!isEditing){
         spanSize.style.display ='none';
         spanStock.style.display ='none';
         inputSize.value = spanSize.textContent.trim();
         inputStock.value = spanStock.textContent.trim();
         inputSize.style.display ='block';
         inputStock.style.display ='block';
         btnEdit.classList.add('editing');
         btnEdit.innerHTML = '<i class="fa-solid fa-check"></i>';
         btnDelete.innerHTML = '<i class="fa-solid fa-xmark"></i>';
      }else{
         // cách lưu - gửi Ajax
         
      }
   })
})

// trường hợp cho hủy không  chỉnh sửa size và stock nữa 
const  deleteSizeAndStockButtons = document.querySelectorAll('.delete-size-stock');
deleteSizeAndStockButtons.forEach(btnDelete =>{
   btnDelete.addEventListener('click',()=>{
      const rowCancel = btnDelete.closest('.size-stock-row');
      const btnEdit = rowCancel.querySelector('.edit-size-stock');
      const btnreturn = rowCancel.querySelector('.delete-size-stock');
      const inputSize = rowCancel.querySelector('.edit-size-input');
      const inputStock = rowCancel.querySelector('.edit-stock-input');
      const spanSize = rowCancel.querySelector('.size-span');
      const spanStock = rowCancel.querySelector('.stock-span');
      const isReturning = btnEdit.classList.contains('editing');

      if(isReturning){
         spanSize.style.display='block';
         spanStock.style.display='block';
         inputSize.style.display='none';
         inputStock.style.display='none';
         btnEdit.classList.remove('editing');
         btnEdit.innerHTML = '<i class="fa-solid fa-pen edit"></i>';
         btnreturn.innerHTML = '<i class="fa-solid fa-trash-can trash"></i>';
      }
   })
})

// button 