    var swiperOptions = {
        loop: true,
        freeMode: true,
        spaceBetween: 0,
        grabCursor: true,
        slidesPerView: window.screen.width > 1024 ? 3.5 : 1,
        autoplay: {
            delay: 1,
            disableOnInteraction: false
        },
        speed: 5000,
        freeModeMomentum: false
    };

    var swiper = new Swiper(".swiper-container-noti", swiperOptions);
