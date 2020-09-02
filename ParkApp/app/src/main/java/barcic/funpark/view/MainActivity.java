package barcic.funpark.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import barcic.funpark.R;
import barcic.funpark.viewmodel.StrojViewModel;

public class MainActivity extends AppCompatActivity {

    private StrojViewModel modelStroj;

    public StrojViewModel getModel(){
        return this.modelStroj;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        modelStroj = ViewModelProviders.of(this).get(StrojViewModel.class);
        read();
    }

    public void read(){
        setFragment( new ReadFragment());
    }

    public void cud(){
        setFragment(new CUDFragment());
    }

    private void setFragment(Fragment fragment){
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }
}

