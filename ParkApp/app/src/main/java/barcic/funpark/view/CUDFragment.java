package barcic.funpark.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import barcic.funpark.R;
import barcic.funpark.viewmodel.StrojViewModel;

import static android.R.layout.simple_spinner_item;

public class CUDFragment extends Fragment {

    static final int SLIKANJE =1;

    private String trenutnaPutanjaSlike;

    @BindView(R.id.nazivStroja)
    EditText nazivStroja;
    @BindView(R.id.mjestoProizvodnje)
    EditText mjestoProizvodnje;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    @BindView(R.id.godinaProizvodnje)
    Spinner dropDownMenu;
    @BindView(R.id.slikaStroja)
    ImageView slika;
    @BindView(R.id.noviStroj)
    Button noviStroj;
    @BindView(R.id.uslikaj)
    Button uslikaj;
    @BindView(R.id.promjenaStroja)
    Button promjenaStroja;
    @BindView(R.id.obrisiStroj)
    Button obrisiStroj;


    StrojViewModel modelStroj;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cud,
                container, false);
        ButterKnife.bind(this, view);
        modelStroj = ((MainActivity) getActivity()).getModel();


        if (modelStroj.getStroj().getId() == 0) {
            definirajNoviStroj();
            return view;
        }
        definirajPromjenaBrisanjeStroj();

        return view;
    }

    private void definirajSpinnerDetalja(){


        List<String> typeList=new ArrayList<>();
        typeList.add("2020");
        typeList.add("2019");
        typeList.add("2018");
        typeList.add("2017");
        typeList.add("2016");
        typeList.add("2015");
        typeList.add("2014");
        typeList.add("2013");
        typeList.add("2012");
        typeList.add("2011");
        typeList.add("2010");



        String godinaProizvodnje = modelStroj.getStroj().getGodinaProizvodnje();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), simple_spinner_item,typeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownMenu.setAdapter(adapter);

        if (godinaProizvodnje != null) {
            int spinnerPosition = adapter.getPosition(godinaProizvodnje);
            dropDownMenu.setSelection(spinnerPosition);
        }

        dropDownMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemSelected=adapterView.getItemAtPosition(i).toString();
                modelStroj.getStroj().setGodinaProizvodnje(itemSelected);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void definirajPromjenaBrisanjeStroj() {
        noviStroj.setVisibility(View.GONE);
        nazivStroja.setText(modelStroj.getStroj().getNazivStroja());
        mjestoProizvodnje.setText(modelStroj.getStroj().getMjestoProizvodnje());
        definirajSpinnerDetalja();

        uslikaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uslikaj();
            }
        });

        promjenaStroja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promjenaStroja();
            }
        });

        obrisiStroj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obrisiStroj();
            }
        });



        if(modelStroj.getStroj().getPutanjaSlika()==null){
            Picasso.get().load(R.drawable.placeholder).fit().centerCrop().into(slika);
            return;
        }
        Picasso.get().load(modelStroj.getStroj().getPutanjaSlika()).fit().centerCrop().into(slika);

    }

    private void promjenaStroja() {
        modelStroj.getStroj().setNazivStroja(nazivStroja.getText().toString());
        modelStroj.getStroj().setMjestoProizvodnje(mjestoProizvodnje.getText().toString());
        modelStroj.promjeniStroj();
        nazad();
    }

    private void definirajNoviStroj() {
        promjenaStroja.setVisibility(View.GONE);
        obrisiStroj.setVisibility(View.GONE);
        uslikaj.setVisibility(View.GONE);

        definirajSpinnerDetalja();
        noviStroj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noviStroj();
            }
        });
    }

    private void noviStroj() {
        modelStroj.getStroj().setNazivStroja(nazivStroja.getText().toString());
        modelStroj.getStroj().setMjestoProizvodnje(mjestoProizvodnje.getText().toString());
        modelStroj.dodajNoviStroj();
        nazad();
    }

    private void obrisiStroj() {
        modelStroj.getStroj().setNazivStroja(nazivStroja.getText().toString());
        modelStroj.getStroj().setMjestoProizvodnje(mjestoProizvodnje.getText().toString());
        modelStroj.obrisiStroj();
        nazad();
    }

    @OnClick(R.id.nazad)
    public void nazad() {
        ((MainActivity) getActivity()).read();
    }

    private void uslikaj() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) == null) {
            Toast.makeText(getActivity(), "Problem kod kreiranja slike", Toast.LENGTH_LONG).show();
            return;

        }
        // Create the File where the photo should go
        File slika = null;
        try {
            slika = kreirajDatotekuSlike();
        } catch (IOException ex) {
            Toast.makeText(getActivity(), "Problem kod kreiranja slike", Toast.LENGTH_LONG).show();
            return;
        }

        if (slika == null) {
            Toast.makeText(getActivity(), "Problem kod kreiranja slike", Toast.LENGTH_LONG).show();
            return;
        }

        Uri slikaURI = FileProvider.getUriForFile(getActivity(),
                "barcic.funpark.provider",
                slika);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, slikaURI);
        startActivityForResult(takePictureIntent, SLIKANJE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SLIKANJE && resultCode == Activity.RESULT_OK) {

            modelStroj.getStroj().setPutanjaSlika("file://" + trenutnaPutanjaSlike);
            modelStroj.promjeniStroj();
            Picasso.get().load(modelStroj.getStroj().getPutanjaSlika()).into(slika);

        }
    }




    private File kreirajDatotekuSlike() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imeSlike = "OSOBA_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imeSlike,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        trenutnaPutanjaSlike = image.getAbsolutePath();
        return image;
    }


}



