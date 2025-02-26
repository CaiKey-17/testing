var cart = [];

function loadCartFromLocalStorage() {
    const cartData = localStorage.getItem('cart');
    if (cartData) {
        cart = JSON.parse(cartData);
        updateCartView();
    }
}

function saveCartToLocalStorage() {
    localStorage.setItem('cart', JSON.stringify(cart));
}

function addToCart(button) {
    var productId = button.getAttribute("data-variantid");
    console.log("Product ID:", productId);
    var productBlock = button.closest('.product-block');
    var productName = productBlock.querySelector('.pro-name a').innerText;
    var productPrice = productBlock.querySelector('.pro-price').innerText;
    var productImg = productBlock.querySelector('img.img-loop').src;

    var productColors = productBlock.querySelectorAll('.product-loop-variant-span-ck');

    var productColor = productColors.length > 0 ? productColors[0].classList[1] : "Màu sắc không xác định";

    var size = 'S';


    var existingProduct = cart.find(product => product.id === productId);

    if (existingProduct) {

        existingProduct.quantity += 1;
    } else {

        var product = {
            id: productId,
            name: productName,
            price: productPrice,
            img: productImg,
            quantity: 1,
            variant: {
                color: productColor,
                size: size
            }
        };
        cart.push(product);
    }

    updateCartView();
    saveCartToLocalStorage();
}



function updateCartView() {
    var cartContainer = document.querySelector("#cart-view tbody");
    cartContainer.innerHTML = ""; 

    var total = 0;
    var totalQuantity = 0;

    cart.forEach(function(product) {
        totalQuantity += product.quantity;

        var color = product.variant && product.variant.color ? product.variant.color : "Màu sắc không xác định";
        var size = product.variant && product.variant.size ? product.variant.size : "Kích thước không xác định";

        var newRow = document.createElement("tr");
        newRow.classList.add("item_" + product.id);

        newRow.innerHTML = `
            <td class="img">
                <a th:href="'/detail/' + ${product.id}" title="${product.name}">
                    <img src="${product.img}" alt="${product.name}">
                </a>
            </td>
            <td>
                <a class="pro-title-view" href="/products/${product.id}" title="${product.name}">${product.name}</a>
                <span class="variant">${color} / ${size}</span>
                <span class="pro-quantity-view" onclick="increaseQuantity('${product.id}')">Số lượng: ${product.quantity}</span>
                <span class="pro-price-view">${product.price}</span>
                <span class="remove_link remove-cart">
                    <a href="javascript:void(0);" onclick="deleteCart('${product.id}')"><i class="fa fa-times"></i></a>
                </span>
            </td>
        `;

        cartContainer.appendChild(newRow);

        total += parseInt(product.price.replace(/[₫,]/g, '')) * product.quantity;
    });

    document.querySelector('.count').innerText = `(${totalQuantity})`;
    document.getElementById("total-view-cart").innerText = total.toLocaleString('vi-VN') + '₫';
}

function increaseQuantity(productId) {
    var product = cart.find(item => item.id === productId);
    if (product) {
        product.quantity += 1;
        updateCartView();
        saveCartToLocalStorage(); 
    }
}
function deleteCart(productId) {
    cart = cart.filter(item => item.id !== productId);
    updateCartView();
    saveCartToLocalStorage();
}

document.addEventListener("DOMContentLoaded", function() {
    loadCartFromLocalStorage();
});
