package com.example.lutluthfi.jadwalujianfilkom;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.lutluthfi.jadwalujianfilkom.model.DetailJadwal;
import com.example.lutluthfi.jadwalujianfilkom.model.JadwalResponse;
import com.example.lutluthfi.jadwalujianfilkom.network.ApiGenerator;
import com.example.lutluthfi.jadwalujianfilkom.network.Service;
import com.example.lutluthfi.jadwalujianfilkom.network.SiamGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    public static final String TAG = "hmm";
    private ArrayList<DetailJadwal> jadwals;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        jadwals = (ArrayList<DetailJadwal>) args.getSerializable("ARRAYLIST");


        for (int i = 0; i < jadwals.size(); i++) {
            Log.d(TAG+"home", "onCreate: "+jadwals.get(i).getMatkul());
            Toast.makeText(getBaseContext(), jadwals.get(i).getMatkul(), Toast.LENGTH_SHORT).show();
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
                for (int i = 0; i < jadwalResponse.getPages().get(0).getTables().get(0).getCells().size(); i++) {
                    //if ()
                    Log.d("zxcv", "onResponse: " + response.body().getPages().get(0).getTables().get(0).getCells().get(i).getContent());
                }
            }

            @Override
            public void onFailure(Call<JadwalResponse> call, Throwable t) {

            }
        });
    }
}
