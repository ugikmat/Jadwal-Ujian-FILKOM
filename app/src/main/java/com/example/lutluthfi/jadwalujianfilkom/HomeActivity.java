package com.example.lutluthfi.jadwalujianfilkom;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.lutluthfi.jadwalujianfilkom.model.Cell;
import com.example.lutluthfi.jadwalujianfilkom.model.DetailJadwal;
import com.example.lutluthfi.jadwalujianfilkom.model.JadwalResponse;
import com.example.lutluthfi.jadwalujianfilkom.network.ApiGenerator;
import com.example.lutluthfi.jadwalujianfilkom.network.Service;
import com.example.lutluthfi.jadwalujianfilkom.network.SiamGenerator;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    public static final String TAG = "hmm";
    private ArrayList<DetailJadwal> jadwals;
    static String ruangan = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        jadwals = (ArrayList<DetailJadwal>) args.getSerializable("ARRAYLIST");


        for (int i = 0; i < jadwals.size(); i++) {
            //Log.d(TAG+"home", "onCreate: "+jadwals.get(i).getMatkul());
            //Toast.makeText(getBaseContext(), jadwals.get(i).getMatkul(), Toast.LENGTH_SHORT).show();
        }

        getJadwal();
    }

    public void getJadwal() {
        Service service = ApiGenerator.createService(Service.class);

        Call<JadwalResponse> call = service.getJadwalUAS();

        call.enqueue(new Callback<JadwalResponse>() {
            @Override
            public void onResponse(Call<JadwalResponse> call, Response<JadwalResponse> response) {
                JadwalResponse jadwalResponse = response.body();
                for (int h = 0; h < jadwalResponse.getPages().size(); h++) {
                    Log.d(TAG, "Hari "+h+": ========================");
                    for (int i = 1; i < jadwalResponse.getPages().get(h).getTables().get(0).getCells().size(); i++) {
                    ArrayList<Cell> cell = (ArrayList<Cell>) response.body().getPages().get(h).getTables().get(0).getCells();
                    if (cell.get(i).getI() <= 2) {

                    } else {
                        if (cell.get(i).getJ().toString().equals("1")) {
                            ruangan = cell.get(i).getContent();
//                            //Log.d(TAG, "onResponse: ==============================");
//                            //Log.d(TAG, "JA Ruangan: "+cell.get(i).getContent());
                        }
                        switch (cell.get(i).getJ().toString()) {
                            default:
                                //ruangan = cell.get(i).getContent();
//                            case "2":
//                                Log.d(TAG, "JA Prodi: "+cell.get(i).getContent());
//                                break;
                            case "3":
                                for (int j = 0; j < jadwals.size(); j++) {
                                    if (jadwals.get(j).getMatkul().trim().equals(cell.get(i).getContent().trim()) &&
                                            jadwals.get(j).getKelas().trim().equals(cell.get(i+1).getContent().trim())) {
                                        Log.d(TAG, "JA Ruangan: "+ruangan);
                                        Log.d(TAG, "JA Matkul: "+cell.get(i).getContent());
                                        Log.d(TAG, "JA kelas: "+cell.get(i+1).getContent());
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
                                    if (jadwals.get(j).getMatkul().trim().equals(cell.get(i).getContent().trim()) &&
                                            jadwals.get(j).getKelas().trim().equals(cell.get(i+1).getContent().trim())) {
                                        Log.d(TAG, "JA Ruangan: "+ruangan);
                                        Log.d(TAG, "JA Matkul: "+cell.get(i).getContent());
                                        Log.d(TAG, "JA kelas: "+cell.get(i+1).getContent());
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
                                    if (jadwals.get(j).getMatkul().trim().equals(cell.get(i).getContent().trim()) &&
                                            jadwals.get(j).getKelas().trim().equals(cell.get(i+1).getContent().trim())) {
                                        Log.d(TAG, "JA Ruangan: "+ruangan);
                                        Log.d(TAG, "JA Matkul: "+cell.get(i).getContent());
                                        Log.d(TAG, "JA kelas: "+cell.get(i+1).getContent());
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

            }

            @Override
            public void onFailure(Call<JadwalResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
