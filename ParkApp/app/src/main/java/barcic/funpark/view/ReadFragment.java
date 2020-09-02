package barcic.funpark.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import barcic.funpark.R;
import barcic.funpark.model.Stroj;
import barcic.funpark.view.adapter.StrojAdapter;
import barcic.funpark.view.adapter.StrojClickListener;
import barcic.funpark.viewmodel.StrojViewModel;

public class ReadFragment extends Fragment {

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.lista)
    ListView listView;

    StrojViewModel modelStroj;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read,
                container, false);
        ButterKnife.bind(this,view);

        modelStroj = ((MainActivity)getActivity()).getModel();

        definirajListu();
        definirajSwipe();
        osvjeziPodatke();


        return view;
    }

    private void osvjeziPodatke(){
        Log.wtf("kreuno","osvježi podatke");
        modelStroj.dohvatiStrojeve().observe(this, new Observer<List<Stroj>>() {
            @Override
            public void onChanged(@Nullable List<Stroj> strojList) {
                Log.wtf("završio","osvježi podatke");
                swipeRefreshLayout.setRefreshing(false);
                ((StrojAdapter)listView.getAdapter()).setPodaci(strojList);
                ((StrojAdapter) listView.getAdapter()).notifyDataSetChanged();

            }
        });
    }
    private void definirajSwipe() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                osvjeziPodatke();
            }
        });

    }

    private void definirajListu() {
        listView =(ListView) listView.findViewById(R.id.lista);
        listView.setAdapter(new StrojAdapter(getActivity(), new StrojClickListener() {
            @Override
            public void onItemClick(Stroj stroj) {
                modelStroj.setStroj(stroj);
                ((MainActivity)getActivity()).cud();
            }
        }));
    }

    @OnClick(R.id.fab)
    public void noviStroj(){
        modelStroj.setStroj(new Stroj());
        ((MainActivity)getActivity()).cud();
    }


}

