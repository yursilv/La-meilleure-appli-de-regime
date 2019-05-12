package com.example.regime_app.MenuFragments.ObjectifsFragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.regime_app.Adapters.ListObjectifsAdapter;
import com.example.regime_app.Commom.Commom;
import com.example.regime_app.Interface.ObjectifInterface;
import com.example.regime_app.Models.ObjectifsModel.NewObjectif;
import com.example.regime_app.Models.ObjectifsModel.Objectif;
import com.example.regime_app.Models.ObjectifsModel.TypeObjectif;
import com.example.regime_app.R;

import java.util.ArrayList;
import java.util.Date;

public class ListObjectifsFragment extends Fragment {

    private ArrayList<ObjectifInterface> objectifs = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.list_objectifs, container, false);
        this.objectifs.add(new NewObjectif());
        this.objectifs.add(new Objectif(TypeObjectif.AmeliorationSilhouette, new Date(), false));
        this.objectifs.add(new Objectif(TypeObjectif.MangerSain, new Date(), false));
        this.objectifs.add(new Objectif(TypeObjectif.PriseDeMuscle, new Date(), false));
        this.objectifs.add(new Objectif(TypeObjectif.PerteDePoids, new Date(), false));

        initRecyclerView(view);

        return view;
    }

    private void initRecyclerView(View view){

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        ListObjectifsAdapter adapter = new ListObjectifsAdapter(view.getContext(), this.objectifs);
        recyclerView.setAdapter(adapter);

        if (Commom.objectifSelected != null) {
            TextView textBut = (TextView) view.findViewById(R.id.butObjectif);
            textBut.setText(Commom.objectifSelected.getBut());

            TextView textConseil = (TextView) view.findViewById(R.id.conseilCoach);
            textConseil.setText(Commom.objectifSelected.getConseilCoach());
        }
    }
}