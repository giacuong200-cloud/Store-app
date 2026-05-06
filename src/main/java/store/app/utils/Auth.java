package store.app.utils;

import store.app.dao.NhanVienDao;
import store.app.entity.NhanVien;

public class Auth {

    public static NhanVien user = null;

    // ================= LOGIN =================
   public static boolean login(String username, String password) {

    // 🔥 BẮT BUỘC
    user = null;

    if (username == null || password == null) {
        return false;
    }

    NhanVienDao dao = new NhanVienDao();
    NhanVien nv = dao.selectById(username);

    if (nv == null) {
        return false;
    }

    if (!password.equals(nv.getMatKhau())) {
        return false;
    }

    user = nv;
    return true;
}

    // ================= LOGOUT =================
    public static void logout() {
        user = null;
    }

    public static void clear() {
        user = null;
    }

    public static boolean isLogin() {
        return user != null;
    }

    public static boolean isManager() {
        return isLogin() && user.isVaiTro();
    }

    public static String getCurrentUser() {
        return user != null ? user.getMaNV() : null;
    }
}