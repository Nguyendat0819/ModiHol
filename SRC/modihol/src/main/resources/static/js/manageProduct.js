
// Button chi tiết và thu gon 
const btnDown = document.querySelectorAll('.btn-down');

btnDown.forEach( btn =>{
   btn.addEventListener('click',() =>{
      btn.classList.toggle('active');
      btn.innerHTML = btn.classList.contains('active') 
      ? `<i class="fa-solid fa-chevron-up"></i> Thu gọn`
      :`<i class="fa-solid fa-chevron-down"></i> Chi Tiết`;  
   })
})


// button reset 
function resetForm(){
     window.location.href="/admin/manageProduct"
}