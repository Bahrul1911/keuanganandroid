package com.example.aturgajian.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.aturgajian.entities.Keuangan;
import com.example.aturgajian.helper.SqliteHelper;

import java.util.ArrayList;
import java.util.List;

public class AturGajianModel {

    private Context context;
    private SqliteHelper db;

    public AturGajianModel(Context context) {
        this.context = context;
        this.db = new SqliteHelper(context);
    }

    public List<Keuangan> allTransaksi(){
        List<Keuangan> keuangans = new ArrayList<>();
        String selectQuery = "SELECT *, strftime('$d/%m/%Y', tanggal) AS tgl FROM transaksi ORDER BY transaksi_id DESC";

        SQLiteDatabase db = this.db.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        int i;
        for (i=0; i<cursor.getCount(); i++){
            cursor.moveToPosition(i);
            Keuangan keuangan = new Keuangan();

            keuangan.setId(cursor.getInt(0));
            keuangan.setStatus(cursor.getString(1));
            keuangan.setJumlah(cursor.getLong(2));
            keuangan.setKeterangan(cursor.getString(3));
            keuangan.setDate(cursor.getString(4));
            keuangans.add(keuangan);
        }

        db.close();
        return keuangans;

    }

    public void insert(Keuangan keuanganBaru) {
        String status = keuanganBaru.getStatus();
        Long jumlah = keuanganBaru.getJumlah();
        String keterangan = keuanganBaru.getKeterangan();

        String sql = "INSERT INTO transaksi (status,jumlah,keterangan) VALUES ('"+status+"','"+jumlah+"','"+keterangan+"')";
        this.db.executeWrite(sql);
    }

    public void delete(String transaksi) {
        String sql = "DELETE FROM transaksi WHERE transaksi_id = '" + transaksi + "'";
        this.db.executeWrite(sql);
    }

}
