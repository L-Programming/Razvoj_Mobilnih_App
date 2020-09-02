package barcic.funpark.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import barcic.funpark.dao.StrojBaza;
import barcic.funpark.dao.StrojDAO;
import barcic.funpark.model.Stroj;

public class StrojViewModel extends AndroidViewModel {

    StrojDAO strojDAO;
    private Stroj stroj;
    private LiveData<List<Stroj>> strojevi;

    public void setStroj(Stroj stroj) {
        this.stroj = stroj;
    }

    public Stroj getStroj() {
        return stroj;
    }

    public StrojViewModel(Application application) {
        super(application);
        strojDAO = StrojBaza.getBaza(application.getApplicationContext()).StrojDAO();

    }
    public LiveData<List<Stroj>> dohvatiStrojeve(){
        strojevi = strojDAO.dohvatiStrojeve();
        return strojevi;
    }
    public void dodajNoviStroj() {

        strojDAO.dodajNoviStroj(stroj);
    }

    public void promjeniStroj() {

        strojDAO.promjeniStroj(stroj);
    }

    public void obrisiStroj() {

        strojDAO.obrisiStroj(stroj);
    }





}
