    document.addEventListener("DOMContentLoaded", function() {
     const searchInput = document.getElementById('search-product');
     const productItems = document.querySelectorAll('.pro-loop');

     searchInput.addEventListener('input', function() {
         const searchTerm = searchInput.value.toLowerCase();

         productItems.forEach(function(item) {
             const productName = item.querySelector('.pro-name a').textContent.toLowerCase();

             if (productName.includes(searchTerm)) {
                 item.style.display = '';
             } else {
                 item.style.display = 'none';
             }
         });
     });
 });


 function filterByPrice() {
     const priceFilter = document.getElementById('price-filter').value;
     let url = `/tops?priceRange=${priceFilter}`;
     window.location.href = url;
 }

 function sortProducts() {
     const sortOrder = document.querySelector('.sort-by').value;
     let url = `/tops?sortOrder=${sortOrder}`;
     window.location.href = url;
 }

 function buy_now(productId) {
     console.log(`Buying product ID: ${productId}`);
 }
