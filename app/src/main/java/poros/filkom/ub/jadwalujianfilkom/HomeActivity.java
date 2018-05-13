package poros.filkom.ub.jadwalujianfilkom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import poros.filkom.ub.jadwalujianfilkom.fragment.FeedbackFragment;
import poros.filkom.ub.jadwalujianfilkom.fragment.JadwalFragment;
import poros.filkom.ub.jadwalujianfilkom.fragment.SettingFragment;

public class HomeActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener{

    public static final String TAG = "hmm";


    private BottomNavigationView bottomNavigationView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, JadwalFragment.newInstance());
        transaction.commit();
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //Toast.makeText(this, String.valueOf(item), Toast.LENGTH_SHORT).show();
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.home:
                selectedFragment = JadwalFragment.newInstance();
                break;
            case R.id.dashboard:
                selectedFragment = FeedbackFragment.newInstance();
                break;
            case R.id.profile:
                selectedFragment = SettingFragment.newInstance();
                break;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();
        return true;
    }
}
