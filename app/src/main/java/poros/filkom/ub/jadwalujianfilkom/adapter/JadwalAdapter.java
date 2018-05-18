package poros.filkom.ub.jadwalujianfilkom.adapter;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import poros.filkom.ub.jadwalujianfilkom.HomeActivity;
import poros.filkom.ub.jadwalujianfilkom.LoginActivity;
import poros.filkom.ub.jadwalujianfilkom.R;
import poros.filkom.ub.jadwalujianfilkom.model.DetailJadwal;

import static java.security.AccessController.getContext;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvHari.setText(jadwalku.get(position).getHari());
        holder.tvKelas.setText(jadwalku.get(position).getKelas());
        holder.tvMatkul.setText(jadwalku.get(position).getMatkul());
        holder.tvJam.setText(jadwalku.get(position).getJam());
        holder.tvRuang.setText(jadwalku.get(position).getRuang());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Berhasil ditambahkan ke notifikasi", Toast.LENGTH_SHORT).show();
                notification(jadwalku.get(position).getMatkul(),
                        jadwalku.get(position).getKelas(),
                        jadwalku.get(position).getHari(),
                        jadwalku.get(position).getJam(),
                        jadwalku.get(position).getRuang());
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
        }
    }



    @SuppressLint("WrongConstant")
    private void notification(String matkul, String kelas, String hari, String jam, String ruang) {
        Intent intent = new Intent(context, LoginActivity.class);
        @SuppressLint("WrongConstant") PendingIntent pendingIntent = PendingIntent.getActivity(context, 01, intent, Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle(matkul + " " + kelas);
        builder.setContentText(jam + " " + ruang);
        builder.setSubText(hari);
        builder.setNumber(101);
        builder.setContentIntent(pendingIntent);
        builder.setTicker("Kuy belajar");
        builder.setSmallIcon(R.drawable.ic_home_black_24dp);
        //builder.setLargeIcon(bm);
        builder.setAutoCancel(true);
        builder.setPriority(0);
        builder.setOngoing(true);
        Notification notification = builder.build();
        NotificationManager notificationManger =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManger.notify(01, notification);
    }
}
