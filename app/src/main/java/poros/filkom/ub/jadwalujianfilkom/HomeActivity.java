package poros.filkom.ub.jadwalujianfilkom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import poros.filkom.ub.jadwalujianfilkom.adapter.JadwalAdapter;
import poros.filkom.ub.jadwalujianfilkom.model.Cell;
import poros.filkom.ub.jadwalujianfilkom.model.DetailJadwal;
import poros.filkom.ub.jadwalujianfilkom.model.JadwalResponse;
import poros.filkom.ub.jadwalujianfilkom.network.ApiGenerator;
import poros.filkom.ub.jadwalujianfilkom.network.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener{

    public static final String TAG = "hmm";
    private ArrayList<DetailJadwal> jadwals;
    private String[] daftarHari = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Senin", "Rabu", "Kamis"};
    static String ruangan = "";
    static String prodi = "";

    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private JadwalAdapter jadwalAdapter;
    private static ArrayList<DetailJadwal> jadwalKu =  new ArrayList<>();

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        jadwalAdapter = new JadwalAdapter(getApplicationContext());

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        jadwals = (ArrayList<DetailJadwal>) args.getSerializable("ARRAYLIST");

        sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        prodi = sharedPreferences.getString("prodi", "");

        for (int i = 0; i < jadwals.size(); i++) {
            //Log.d(TAG+"home", "onCreate: "+jadwals.get(i).getMatkul());
            //Toast.makeText(getBaseContext(), jadwals.get(i).getMatkul(), Toast.LENGTH_SHORT).show();
        }

        getJadwal();

    }

    private void getJadwalKu() {
        for (int i = 0; i < jadwalKu.size(); i++) {
            Log.d(TAG, "getJadwalKu: ==========================");
            Log.d(TAG, "getJadwalKu: "+jadwalKu.get(i).getHari());
            Log.d(TAG, "getJadwalKu: "+jadwalKu.get(i).getJam());
            Log.d(TAG, "getJadwalKu: "+jadwalKu.get(i).getKelas());
            Log.d(TAG, "getJadwalKu: "+jadwalKu.get(i).getMatkul());
            Log.d(TAG, "getJadwalKu: "+jadwalKu.get(i).getRuang());
        }
        jadwalAdapter = new JadwalAdapter(this);
        jadwalAdapter.addItem(jadwalKu);
        recyclerView.setAdapter(jadwalAdapter);
    }

    private void getJadwal() {
        Service service = ApiGenerator.createService(Service.class);

        Call<JadwalResponse> call = service.getJadwalUAS();

        call.enqueue(new Callback<JadwalResponse>() {
            @Override
            public void onResponse(Call<JadwalResponse> call, Response<JadwalResponse> response) {
                JadwalResponse jadwalResponse = response.body();
                int ke = 0;
                for (int h = 0; h < jadwalResponse.getPages().size(); h++) {
                    //Log.d(TAG, "Hari "+h+": ========================");
                    for (int i = 1; i < jadwalResponse.getPages().get(h).getTables().get(0).getCells().size(); i++) {
                        ArrayList<Cell> cell = (ArrayList<Cell>) response.body().getPages().get(h).getTables().get(0).getCells();
                        if (cell.get(i).getI() <= 2) {

                        } else {
                            if (cell.get(i).getJ().toString().equals("1")) {
                                ruangan = cell.get(i).getContent();
//                            //Log.d(TAG, "onResponse: ==============================");
//                            //Log.d(TAG, "JA Ruangan: "+cell.get(i).getContent());
                            }
                            switch (prodi) {
                                case "Magister Ilmu Komputer":
                                    prodi = "MIK";
                                    break;
                                case "Teknik Informatika":
                                    prodi = "TIF";
                                    break;
                                case "Teknik Komputer":
                                    prodi = "TEKOM";
                                    break;
                                case "Pendidikan Teknologi Informasi":
                                    prodi = "PTI";
                                    break;
                                case "Sistem Informasi":
                                    prodi = "SI";
                                    break;
                                case "Teknologi Informasi":
                                    prodi = "TI";
                                    break;
                            }

                            switch (cell.get(i).getJ().toString()) {
                                default:
                                    //ruangan = cell.get(i).getContent();
//                            case "2":
//                                Log.d(TAG, "JA Prodi: "+cell.get(i).getContent());
//                                break;
                                case "3":
                                    for (int j = 0; j < jadwals.size(); j++) {
                                        if (
                                                prodi.equals(cell.get(i-1).getContent().trim()) &&
                                                        jadwals.get(j).getMatkul().trim().equals(cell.get(i).getContent().trim()) &&
                                                        jadwals.get(j).getKelas().trim().equals(cell.get(i+1).getContent().trim())) {
                                            Log.d(TAG, "JA Ruangan: "+ruangan);
                                            Log.d(TAG, "JA Matkul: "+cell.get(i).getContent());
                                            Log.d(TAG, "JA kelas: "+cell.get(i+1).getContent());
                                            DetailJadwal detailJadwal = new DetailJadwal();
                                            detailJadwal.setHari(daftarHari[h]);
                                            detailJadwal.setJam("pagi");
                                            detailJadwal.setKelas(cell.get(i+1).getContent());
                                            detailJadwal.setMatkul(cell.get(i).getContent());
                                            detailJadwal.setRuang(ruangan);
                                            jadwalKu.add(ke, detailJadwal);
                                            ke++;
                                        }
                                    }
                                    break;
//                            case "4":
//                                Log.d(TAG, "JA kelas: "+cell.get(i).getContent());
//                                break;
//                            case "5":
//                                Log.d(TAG, "JA Prodi: "+cell.get(i).getContent());
//                                break;
                                case "6":
                                    // 6 = matkul, 7 = kelas
                                    //int cases = 7;

//                                    Log.d(TAG, "onResponse: ------------------------------");
//                                                                            Log.d(TAG, "JA siam : "+jadwals.get(j).getMatkul());
//                                        Log.d(TAG, "JA siamk: "+jadwals.get(j).getKelas());
//                                    Log.d(TAG, "onResponse: 99999999999999999");
                                    //        Log.d(TAG, "JA Matkul: "+cell.get(i).getContent());
                                    //Log.d(TAG, "JA kelas: "+cell.get(i+1).getContent());
//                                    Log.d(TAG, "onResponse: ------------------------------");

                                    for (int j = 0; j < jadwals.size(); j++) {
                                        if (
                                                prodi.equals(cell.get(i-1).getContent().trim()) &&
                                                        jadwals.get(j).getMatkul().trim().equals(cell.get(i).getContent().trim()) &&
                                                        jadwals.get(j).getKelas().trim().equals(cell.get(i+1).getContent().trim())) {
                                            Log.d(TAG, "JA Ruangan: "+ruangan);
                                            Log.d(TAG, "JA Matkul: "+cell.get(i).getContent());
                                            Log.d(TAG, "JA kelas: "+cell.get(i+1).getContent());
                                            DetailJadwal detailJadwal = new DetailJadwal();
                                            detailJadwal.setHari(daftarHari[h]);
                                            detailJadwal.setJam("siang");
                                            detailJadwal.setKelas(cell.get(i+1).getContent());
                                            detailJadwal.setMatkul(cell.get(i).getContent());
                                            detailJadwal.setRuang(ruangan);
                                            jadwalKu.add(ke, detailJadwal);
                                            ke++;
                                        }
                                    }
                                    break;
//                            case "7":
//                                Log.d(TAG, "JA kelas: "+cell.get(i).getContent());
//                                break;
//                            case "8":
//                                Log.d(TAG, "JA Prodi: "+cell.get(i).getContent());


//                                break;
                                case "9":
                                    //Log.d(TAG, "JA Matkul: "+cell.get(i).getContent());
                                    for (int j = 0; j < jadwals.size(); j++) {
                                        if (
                                                prodi.equals(cell.get(i-1).getContent().trim()) &&
                                                        jadwals.get(j).getMatkul().trim().equals(cell.get(i).getContent().trim()) &&
                                                        jadwals.get(j).getKelas().trim().equals(cell.get(i+1).getContent().trim())) {
                                            Log.d(TAG, "JA Ruangan: "+ruangan);
                                            Log.d(TAG, "JA Matkul: "+cell.get(i).getContent());
                                            Log.d(TAG, "JA kelas: "+cell.get(i+1).getContent());
                                            DetailJadwal detailJadwal = new DetailJadwal();
                                            detailJadwal.setHari(daftarHari[h]);
                                            detailJadwal.setJam("sore");
                                            detailJadwal.setKelas(cell.get(i+1).getContent());
                                            detailJadwal.setMatkul(cell.get(i).getContent());
                                            detailJadwal.setRuang(ruangan);
                                            jadwalKu.add(ke, detailJadwal);
                                            ke++;
                                        }
                                    }
                                    break;
//                            case "10":
//                                Log.d(TAG, "JA Kelas: "+cell.get(i).getContent());
//                                break;
                            }
                        }

//                    if (cell.get(i).getJ().equals("3") || cell.get(i).getJ().equals("6") || cell.get(i).getJ().equals("9")) {
//                    }
//                    if (cell.get(i).getJ().equals("4")) {
//                        Log.d(TAG, "JA kelas: "+cell.get(i).getContent());
//                    }
                        //Log.d("zxcv", "onResponse: " + response.body().getPages().get(0).getTables().get(0).getCells().get(i).getContent());
                    }

                }

                getJadwalKu();
            }

            @Override
            public void onFailure(Call<JadwalResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, String.valueOf(item), Toast.LENGTH_SHORT).show();
        return true;
    }
}
