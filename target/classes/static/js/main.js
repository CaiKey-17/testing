 $(document).ready(function () {
        $('.slider-owl').owlCarousel({
            loop: true,
            margin: 10,
            nav: true,
            items: 1,
            autoplay: true,
            autoplayTimeout: 5000,
            autoplayHoverPause: true,
        });
    });


    var arrLanguage = {
            en: {
            phan_loai_theo_size:"Filter by size",
            hong_nhat:"Pinkkk",
            phan_loai_theo_mau:"Filter by color",
                mua_ngay: "BUY NOW",
                them_vao_gio: "ADD TO CART",
                thong_tin:"INFORMATION",
                san_pham_vua_xem:"RECENTLY VIEWED PRODUCTS",
                san_pham_lien_quan:"RELATED PRODUCTS",
                kich_thuoc:"Size",
                mau_sac:"Color",
                het_hang:"Sold out",
                dang_nhap:"Login",
                dang_ky:"Register",
                tim_kiem:"Search",
                gio_hang:"Cart",
                tim_kiem_san_pham:"Search in HADES...",
                tong_tien:"Total",
                thanh_toan:"Payment Options",
                xem_gio_hang:"View cart",
                he_thong_cua_hang_hades:"HADES STORE SYSTEM",
                chinh_sach:"POLICY",
                thong_tin_lien_he:"CONTACT INFO",
                chinh_sach_bao_mat:"Privacy Policy",
                chinh_sach_su_dung_website:"Website usage policy",
                phuong_thuc_thanh_toan:"Payment Options",
                chinh_sach_doi_tra:"Returns & Exchanges",
                chinh_sach_giao_nhan_van_chuyen:"Shipping Services",
                dieu_khoan_dich_vu:"Terms of Service",
                huong_dan_mua_hang:"Shopping Guide",
                cong_ty_tnhh_hadesdia_chi_45_phan_chu_trinh_p_ben_thanh_quan_1_tp_ho_chi_minh:"HADES COMPANY LIMITED Add: 45 Phan Chu Trinh, Ben Thanh, Q1, Ho Chi Minh City",
                so_cskh02873011021_10h_18h:"Hotline: 02873011021 (10h -18h)",
                ngay_cap20_07_2020:"Date granted: 20/07/2020",
                tuyen_dunghr_hades_vn:"Recruitment: hr@hades.vn",
                lien_he_cskhsupport_hades_vn:"Customer care: support@hades.vn",
                hien_chua_co_san_pham:"There are currently no products",
                xem_them:"Load more",
                san_pham:"products",
                chua_co_sp_nao_trong_danh_muc_nay:"No products in this category",
                sp_noi_bat:"Featured",
                cu_nhat:"OLDEST",
                moi_nhat:"LATEST",
                ban_chay_nhat:"BEST SELLER",
                gia_giam_dan:"PRICE HIGH TO LOW",
                gia_tang_dan:"PRICE LOW TO HIGH",
                hoac:"or",
                quen_mat_khau:"Forgot password?",
                ho:"Last name",
                ten:"First name",
                nu:"Female",
                nam:"Male",
                quay_lai_home:"Back to home",
                mat_khau:"Password",
                tai_khoan_cua_ban:"Your account",
                tai_khoan:"Account",
                danh_sach_dia_chi:"Address List",
                thong_tin_tai_khoan:"Account information",
                dang_xuat:"Logout",
                xem_dia_chi:"View Address",
                danh_sach_moi_nhat:"LATEST ORDER LIST",
                ngay_dat:"Order date",
                thanh_tien:"Into Money",
                trang_thai_thanh_toan:"Payment Status",
                van_chuyen:"Transport",
                bang_size:"SIZE CHART",
                phan_loai_theo_gia: "Filter by price",
                duoi_500k: "Under 500k",
                 duoi_1_trieu: "Under 1M",
                mot_trieu_st: "Above 1M",
                tat_ca: "All",
                hong_nhat:"Pink",
                xanh:"Blue",
                xam:"Grey",
                den:"Black",
                vang:"Yellow"
            },
            vi: {
             hong_nhat:"Hồng nhạt",
                                     xanh:"Xanh dương",
                                     xam:"Xám",
                                     den:"Đen",
                                     vang:"Vàng",
                            tat_ca: "Tất cả",
phan_loai_theo_size:"Phân loại theo kích cỡ",
phan_loai_theo_mau:"Phân loại theo màu",
            phan_loai_theo_gia: "Phân theo loại giá",
            duoi_500k: "Dưới 500k",
            duoi_1_trieu: "Dưới 1 triệu",
            mot_trieu_st: "1 triệu trở lên",
                mua_ngay: "MUA NGAY",
                them_vao_gio: "THÊM VÀO GIỎ",
                thong_tin: "THÔNG TIN",
                san_pham_vua_xem:"SẢN PHẨM VỪA XEM",
                san_pham_lien_quan:"SẢN PHẨM LIÊN QUAN",
                kich_thuoc:"Kích thước",
                mau_sac:"Màu sắc",
                het_hang:"Hết hàng",
                dang_nhap:"Đăng nhập",
                dang_ky:"Đăng ký",
                tim_kiem:"Tìm kiếm",
                gio_hang:"Giỏ hàng",
                tim_kiem_san_pham:"Tìm kiếm sản phẩm...",
                tong_tien:"Total",
                thanh_toan:"Thanh toán",
                xem_gio_hang:"Xem giỏ hàng",
                he_thong_cua_hang_hades:"HỆ THỐNG CỬA HÀNG HADES",
                chinh_sach:"CHÍNH SÁCH",
                thong_tin_lien_he: "THÔNG TIN LIÊN HỆ",
                chinh_sach_bao_mat:"Chính sách bảo mật",
                chinh_sach_su_dung_website:"Chính sách sử dụng website",
                phuong_thuc_thanh_toan:"Phương thức thanh toán",
                chinh_sach_doi_tra:"Chính sách đổi trả",
                chinh_sach_giao_nhan_van_chuyen:"Chính sách giao nhận - vận chuyển",
                dieu_khoan_dich_vu:"Điều khoản dịch vụ",
                huong_dan_mua_hang:"Hướng dẫn mua hàng",
                cong_ty_tnhh_hadesdia_chi_45_phan_chu_trinh_p_ben_thanh_quan_1_tp_ho_chi_minh:"CÔNG TY TNHH HADES Địa chỉ: 45 Phan Chu Trinh, P. Bến Thành, Quận 1, TP. Hồ Chí Minh",
                so_cskh02873011021_10h_18h:"SỐ CSKH: 02873011021 (10h -18h)",
                ngay_cap20_07_2020:"Ngày cấp: 20/07/2020",
                tuyen_dunghr_hades_vn:"Tuyển dụng: hr@hades.vn",
                lien_he_cskhsupport_hades_vn:"Liên hệ CSKH: support@hades.vn",
                hien_chua_co_san_pham:"Hiện chưa có sản phẩm",
                xem_them:"Xem thêm",
                san_pham:"sản phẩm",
                chua_co_sp_nao_trong_danh_muc_nay:"Chưa có sản phẩm nào trong danh mục này",
                sp_noi_bat:"Sản phẩm nổi bật",
                cu_nhat:"Cũ nhất",
                moi_nhat:"Mới nhất",
                ban_chay_nhat:"Bán chạy nhất",
                gia_giam_dan:"GIÁ: GIẢM DẦN",
                gia_tang_dan:"GIÁ: TĂNG DẦN",
                hoac:"hoặc",
                quen_mat_khau:"Quên mật khẩu?",
                mat_khau:"Mật khẩu",
                ho:"Họ",
                ten:"Tên",
                nu:"Nữ",
                nam:"Nam",
                quay_lai_home:"Quay lại trang chủ",
                tai_khoan_cua_ban:"Tài khoản của bạn",
                tai_khoan:"Tài khoản",
                danh_sach_dia_chi:"Danh sách địa chỉ",
                thong_tin_tai_khoan:"Thông tin tài khoản",
                dang_xuat:"Đăng xuất",
                xem_dia_chi:"Xem địa chỉ",
                danh_sach_moi_nhat:"Danh sách mới nhất",
                ngay_dat:"Ngày đặt",
                thanh_tien:"Thành tiền",
                trang_thai_thanh_toan:"Trạng thái thanh toán",
                van_chuyen:"Vận chuyển",
                bang_size:"BẢNG SIZE",
            }
        }


        $(document).ready(function(){
            if(window.localStorage.getItem('language')){
                window.location.hash = window.localStorage.getItem('language');
            }
                if(window.location.hash && window.location.hash == '#l=en'){
                    let element = document.getElementById("select-language");
                element.value = 'en';
                    $('.lang').each(function(index,element){
                        $(this).text(arrLanguage["en"][$(this).attr('keyLanguage')])
                    })
                    $('.langplaceholder').each(function(index,element){
                        $(this).attr('placeholder',arrLanguage["en"][$(this).attr('keyLanguage')])
                    })
                    $('.langValue').each(function(index,element){
                        $(this).attr('value',arrLanguage["en"][$(this).attr('keyLanguage')])
                    })
                }else{
                    let element = document.getElementById("select-language");
                element.value = 'vi';
                    $('.lang').each(function(index,element){
                            $(this).text(arrLanguage["vi"][$(this).attr('keyLanguage')])
                        })
                    $('.langplaceholder').each(function(index,element){
                        $(this).attr('placeholder',arrLanguage["vi"][$(this).attr('keyLanguage')])
                    })
                    $('.langValue').each(function(index,element){
                        $(this).attr('value',arrLanguage["vi"][$(this).attr('keyLanguage')])
                    })
                }
        })

        $(function(){
            $('#select-language-mobile').change(function(){
                console.log('test');
                let id  = document.getElementById("select-language-mobile").value;
                window.location.hash = `#l=${id}`;
                window.localStorage.setItem('language',`#l=${id}`);
                $('.lang').each(function(index,element){
                    $(this).text(arrLanguage[id][$(this).attr('keyLanguage')])
                })
                $('.langplaceholder').each(function(index,element){
                        $(this).attr('placeholder',arrLanguage[id][$(this).attr('keyLanguage')])
                })
                $('.langValue').each(function(index,element){
                    $(this).attr('value',arrLanguage[id][$(this).attr('keyLanguage')])
                })
            })
            $('#select-language').change(function(){
                let id  = document.getElementById("select-language").value;
                window.location.hash = `#l=${id}`;
                window.localStorage.setItem('language',`#l=${id}`);
                $('.lang').each(function(index,element){
                    $(this).text(arrLanguage[id][$(this).attr('keyLanguage')])
                })
                $('.langplaceholder').each(function(index,element){
                        $(this).attr('placeholder',arrLanguage[id][$(this).attr('keyLanguage')])
                })
                $('.langValue').each(function(index,element){
                    $(this).attr('value',arrLanguage[id][$(this).attr('keyLanguage')])
                })
            })
        })
 document.getElementById('site-cart-handle').addEventListener('click', function(e) {
    e.preventDefault();

    var cartElement = document.getElementById('site-nav--mobile');

    if (cartElement.classList.contains('active')) {
        cartElement.classList.remove('active');
    } else {
        cartElement.classList.add('active');
    }
});
  document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('site-cart-ck').addEventListener('click', function(e) {
            e.preventDefault();

            var cartElement = document.getElementById('site-nav--mobile');

            if (cartElement.classList.contains('active')) {
                cartElement.classList.remove('active');
            } else {
                cartElement.classList.add('active');
            }
        });

        document.getElementById('site-close-ck').addEventListener('click', function() {
            var cartElement = document.getElementById('site-nav--mobile');
            cartElement.classList.remove('active');
        });
    });

    document.getElementById('site-close-ck').addEventListener('click', function() {
    console.log("Đã nhấp vào nút đóng");
    var cartElement = document.getElementById('site-nav--mobile');
    cartElement.classList.remove('active');
});
