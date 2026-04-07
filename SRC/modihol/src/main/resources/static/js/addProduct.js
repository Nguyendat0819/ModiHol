// xem trước ảnh 
function previewImage(input){
   const preview = input.closest('.form-right-image').querySelector('.preview-image');
   const file = input.files[0];
   if(file){
    preview.src=URL.createObjectURL(file);
    preview.style.display ="block";
   }
}

//  Thêm size và số lượng 
const btnAdd = document.querySelector('.add-quatity');
const btnDelete = document.querySelector('.btn-delete');
const contain = document.querySelector('.form-left-downUp');

// thêm
btnAdd.addEventListener('click',() =>{
   const newRow = document.createElement('div');
   newRow.classList.add('form-left-content');
   newRow.innerHTML = `
      <input type="text" name="size" autocomplete="off">
      <input type="number" name="stock" autocomplete="off">
      <button type="button" class="btn-delete">Xóa</button>
   `;
   contain.appendChild(newRow);
})

// Xóa 
contain.addEventListener('click', (e) =>{
   if(e.target.classList.contains('btn-delete')){
      const allRow = contain.querySelectorAll('.form-left-content');
      if(allRow.length === 1){
         alert('Không được xóa');
         return;
      }
      e.target.closest('.form-left-content').remove();
   }
})