package poros.filkom.ub.jadwalujianfilkom.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import poros.filkom.ub.jadwalujianfilkom.R;
import poros.filkom.ub.jadwalujianfilkom.model.DetailJadwal;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<DetailJadwal> jadwalku;

    public JadwalAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    public void addItem(ArrayList<DetailJadwal> jadwalku) {
        this.jadwalku = jadwalku;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.row_menu, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvHari.setText(jadwalku.get(position).getHari());
        holder.tvKelas.setText(jadwalku.get(position).getKelas());
        holder.tvMatkul.setText(jadwalku.get(position).getMatkul());
        holder.tvJam.setText(jadwalku.get(position).getJam());
        holder.tvRuang.setText(jadwalku.get(position).getRuang());
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Detail jadwal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return jadwalku.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvHari, tvKelas, tvMatkul, tvJam, tvRuang;
        private Button btnDetail;

        public ViewHolder(View itemView) {
            super(itemView);

            tvHari = (TextView) itemView.findViewById(R.id.tv_hari);
            tvJam = (TextView) itemView.findViewById(R.id.tv_jam);
            tvKelas = (TextView) itemView.findViewById(R.id.tv_kelas);
            tvMatkul = (TextView) itemView.findViewById(R.id.tv_matkul);
            tvRuang = (TextView) itemView.findViewById(R.id.tv_ruang);
            btnDetail = (Button) itemView.findViewById(R.id.btn_detail);
        }
    }
}
