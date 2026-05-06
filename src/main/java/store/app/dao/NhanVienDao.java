package store.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import store.app.entity.NhanVien;
import store.app.utils.XJDBC;

public class NhanVienDao extends storeAppDAO<NhanVien, String> {

    @Override
    public void insert(NhanVien model) {
        String sql = """
            INSERT INTO NhanVien(MaNV, TenNV, GioiTinh, DiaChi, DienThoai, NgaySinh, MatKhau, VaiTro)
            VALUES ([dbo].[AUTO_MaNV](), ?, ?, ?, ?, ?, ?, ?)
        """;
        XJDBC.update(sql,
                model.getTenNV(),
                model.isGioiTinh(),
                model.getDiaChi(),
                model.getSdt(),
                model.getNgaySinh(),
                model.getMatKhau(),
                model.isVaiTro()
        );
    }

    @Override
    public void update(NhanVien model) {
        String sql = """
            UPDATE NhanVien 
            SET TenNV=?, GioiTinh=?, DiaChi=?, DienThoai=?, NgaySinh=?, MatKhau=?, VaiTro=? 
            WHERE MaNV=?
        """;
        XJDBC.update(sql,
                model.getTenNV(),
                model.isGioiTinh(),
                model.getDiaChi(),
                model.getSdt(),
                model.getNgaySinh(),
                model.getMatKhau(),
                model.isVaiTro(),
                model.getMaNV()
        );
    }

    @Override
    public void delete(String maNV) {
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        XJDBC.update(sql, maNV);
    }

    @Override
    public NhanVien selectById(String maNV) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> list = selectBySql(sql, maNV);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<NhanVien> selectAll() {
        String sql = "SELECT * FROM NhanVien";
        return selectBySql(sql);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = XJDBC.query(sql, args);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSdt(rs.getString("DienThoai"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setVaiTro(rs.getBoolean("VaiTro"));
                list.add(nv);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.getStatement().getConnection().close();
            } catch (Exception e) {}
        }
        return list;
    }

    public void qmk(NhanVien model) {
        String sql = "UPDATE NhanVien SET MatKhau=? WHERE MaNV=?";
        XJDBC.update(sql, model.getMatKhau(), model.getMaNV());
    }
}