package com.example.aturgajian.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aturgajian.R;
import com.example.aturgajian.entities.Keuangan;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class KeuanganAdapter extends RecyclerView.Adapter<KeuanganAdapter.MyViewHolder> {

    private Context context;
    private List<Keuangan> transaksiList;


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tvTransaksi;
        public TextView tvKeterangan;
        public TextView tvJumlah;
        public TextView tvStatus;
        public TextView tvTanggal;
        public TextView circleTextDivisi;

        public MyViewHolder(View view) {
            super(view);
            tvTransaksi = view.findViewById(R.id.text_transaksi_id);
            tvKeterangan = view.findViewById(R.id.text_keterangan);
            tvJumlah = view.findViewById(R.id.text_jumlah);
            tvStatus = view.findViewById(R.id.text_status);
            tvTanggal = view.findViewById(R.id.text_tanggal);
            circleTextDivisi = view.findViewById(R.id.circleTextDivisi);

        }
    }

    public KeuanganAdapter(Context context, List<Keuangan>noteslist){
        this.context = context;
        this.transaksiList = noteslist;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_keuangan,parent,false);


        return new MyViewHolder(itemView);
    }


    private int[] colors = {R.color.circle_color_1, R.color.circle_color_2, R.color.circle_color_3, R.color.circle_color_4, R.color.circle_color_5};
    private String getFirstLetter(String name) {
        if (name != null || name != "") {
            return name.substring(0, 1).toUpperCase();
        } else {
            return "";
        }

    }
    private int[] colorText =  {R.color.text_color_1, R.color.text_color_2, R.color.text_color_3, R.color.text_color_4, R.color.text_color_5};

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Keuangan keuangan = transaksiList.get(position);

        NumberFormat rupiahFormat = NumberFormat.getInstance(Locale.GERMANY);



        String firstLetter = getFirstLetter(keuangan.getKeterangan());

        // Menentukan index secara acak
        int randomIndex = new Random().nextInt(colors.length);

        // Set warna circleTextView dan textColor dengan warna yang dipilih
        int circleColor = ContextCompat.getColor(holder.itemView.getContext(), colors[randomIndex]);
        int textColor = ContextCompat.getColor(holder.itemView.getContext(), colorText[randomIndex]);



        // Set the background color of the circleTextView to the random color
        holder.circleTextDivisi.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.shapecircle));
        holder.circleTextDivisi.getBackground().setColorFilter(circleColor, PorterDuff.Mode.SRC_OVER);
        holder.circleTextDivisi.setTextColor(textColor);
        holder.circleTextDivisi.setText(firstLetter);

        holder.tvTransaksi.setText(String.valueOf(keuangan.getId()));
        holder.tvKeterangan.setText(keuangan.getKeterangan());
        holder.tvJumlah.setText("Rp."+rupiahFormat.format(keuangan.getJumlah()));
        holder.tvStatus.setText(keuangan.getStatus());
        holder.tvTanggal.setText(keuangan.getDate());

    }

    @Override
    public int getItemCount() {
        return transaksiList.size();
    }
}
