package strenja.filmapp.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import strenja.filmapp.R;
import strenja.filmapp.viewmodel.FilmViewModel;

public class CUDFragment extends Fragment {

    static final int SLIKANJE =1;

    private String trenutnaPutanjaSlike;

    @BindView(R.id.naziv_stroja)
    EditText naziv_stroja;
    @BindView(R.id.godina)
    EditText godina;
    @BindView(R.id.slika)
    ImageView slika;

    @BindView(R.id.noviFilm)
    Button noviFilm;
    @BindView(R.id.uslikaj)
    Button uslikaj;
    @BindView(R.id.promjenaFilma)
    Button promjenaFilma;
    @BindView(R.id.obrisiFilm)
    Button obrisiFilm;

    FilmViewModel modelFilm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cud,
                container, false);
        ButterKnife.bind(this, view);
        modelFilm = ((MainActivity) getActivity()).getModel();

        if (modelFilm.getFilm().getId() == 0) {
            definirajNoviFilm();
            return view;
        }
        definirajPromjenaBrisanjeFilm();

        return view;
    }

    private void definirajPromjenaBrisanjeFilm() {
        noviFilm.setVisibility(View.GONE);
        naziv_stroja.setText(modelFilm.getFilm().getNaziv_stroja());
        godina.setText(modelFilm.getFilm().getGodina());


        uslikaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void