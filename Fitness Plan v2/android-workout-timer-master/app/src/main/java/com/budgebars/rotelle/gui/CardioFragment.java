package com.budgebars.rotelle.gui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.budgebars.rotelle.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardioFragment extends Fragment {

    String[] listviewitemsCardio = {"Burpee Tuck Jumps", "Half Burpee", "Jump Rope - Alternating foot", "Jump Rope- Two Feet", "Jumping Jacks", "Lateral Jumps", "Lunge Jumps", "Lunge Stretch Left", "Lunge Stretch Right",
            "Mountian Climbers", "Side Burpees", "Sumo Squat"};
    String[] listviewitemsCardioGym = {"Elliptical", "Treadmill Jogging"};
    ListView absList;
    ListAdapter adapter;
    int finalmode;


    public CardioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.activity_recommended_abs_list, container, false);
        ListHome parent = (ListHome)getActivity();
        finalmode = parent.getMode();

        absList = view.findViewById(R.id.abslist);

        if(finalmode==1) {
            adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,listviewitemsCardio);
            finalmode=3;
        }
        else {
            finalmode = 6;
            adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,listviewitemsCardioGym);
        }


        absList.setAdapter(adapter);
        absList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String TemplistView = listviewitemsCardio[position].toString();

                if(finalmode==3) {
                    TemplistView = listviewitemsCardio[position].toString();

                }
                else if(finalmode ==6) {
                    TemplistView = listviewitemsCardioGym[position].toString();

                }


                Intent intent = new Intent(getActivity(), ViewExercises.class);
                intent.putExtra("list",finalmode);
                intent.putExtra("name", TemplistView);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
        return view;
    }

}
