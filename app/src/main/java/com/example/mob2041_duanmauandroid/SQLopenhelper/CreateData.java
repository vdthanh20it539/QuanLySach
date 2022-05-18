package com.example.mob2041_duanmauandroid.SQLopenhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateData extends SQLiteOpenHelper {
    private static final String DB_NAME = "PNLINB.db";
    private static final int VERSION = 2;

    public CreateData(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Bảng Nhân Viên
        String tb_nv =
                "create table NhanVien (" +
                        "maNV TEXT PRIMARY KEY UNIQUE, " +
                        "hoTen TEXT NOT NULL, " +
                        "matKhau TEXT NOT NULL)";
        db.execSQL(tb_nv);
        //Bảng Thành viên
        String tb_tv =
                "create table ThanhVien (" +
                        "maTV INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "hoTen TEXT NOT NULL, " +
                        "namSinh TEXT NOT NULL)";
        db.execSQL(tb_tv);
        //Bảng Loại sách
        String tb_ls =
                "create table LoaiSach (" +
                        "maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nhacc TEXT NOT NULL, " +
                        "tenLoai TEXT UNIQUE NOT NULL)";
        db.execSQL(tb_ls);
        //Bảng Sách
        String tb_S =
                "create table Sach (" +
                        "maSach INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tenSach TEXT NOT NULL, " +
                        "giaThue INTEGER NOT NULL, " +
                        "tacgia TEXT NOT NULL, " +
                        "maLoai INTEGER REFERENCES LoaiSach(maLoai))";
        db.execSQL(tb_S);
        //Bảng Phiếu Mượn
        String tb_pm =
                "create table PhieuMuon (" +
                        "maPM INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "maNV TEXT REFERENCES NhanVien(maNV), " +
                        "maTV INTEGER REFERENCES ThanhVien(maTV), " +
                        "maSach INTEGER REFERENCES Sach(maSach), " +
                        "tienThue INTEGER NOT NULL, " +
                        "ngay DATE NOT NULL, " +
                        "traSach INTEGER NOT NULL)";
        db.execSQL(tb_pm);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa khi update phiên bản
        String dropTableThuThu = "drop table if exists NhanVien";
        db.execSQL(dropTableThuThu);
        String dropTableThanhVien = "drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);

        onCreate(db);
    }


}
