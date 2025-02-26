
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
    var cartTable = document.querySelector("#cart-view tbody");
    cartTable.innerHTML = "";

    var total = 0;
    var totalQuantity = 0;

    cart.forEach(function(product) {
        totalQuantity += product.quantity;

        var newRow = document.createElement("tr");
        newRow.classList.add("item_" + product.id);

        var color = product.variant && product.variant.color ? product.variant.color : "Màu sắc không xác định";
        var size = product.variant && product.variant.size ? product.variant.size : "Kích thước không xác định";

        newRow.innerHTML = `
            <td class="img">
                <a href="/products/${product.id}" title="${product.name}">
                    <img src="${product.img}" alt="${product.name}">
                </a>
            </td>
            <td>
                <a class="pro-title-view" href="/products/${product.id}" title="${product.name}">${product.name}</a>
                <span class="variant">${color} / ${size}</span>
                <span class="pro-quantity-view">Số lượng: ${product.quantity}</span>
                <span class="pro-price-view">${product.price}</span>
                <span class="remove_link remove-cart">
                    <a href="javascript:void(0);" onclick="deleteCart('${product.id}')"><i class="fa fa-times"></i></a>
                </span>
            </td>
        `;

        cartTable.appendChild(newRow);

        total += parseInt(product.price.replace(/[₫,]/g, '')) * product.quantity;
    });

    document.querySelector('.count').innerText = `(${totalQuantity})`;

    document.getElementById("total-view-cart").innerText = total.toLocaleString('vi-VN') + '₫';
}


function deleteCart(productId) {
    cart = cart.filter(item => item.id !== productId);
    updateCartView();
    saveCartToLocalStorage();
}

document.addEventListener("DOMContentLoaded", function() {
    loadCartFromLocalStorage();
});
