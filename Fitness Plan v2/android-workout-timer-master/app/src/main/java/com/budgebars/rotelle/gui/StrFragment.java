package com.budgebars.rotelle.gui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.budgebars.rotelle.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StrFragment extends Fragment {

    String[] listviewitemsStr = {"Alternating spider push ups", "Body weight squat", "Downward Facing Dog", "Push ups"};
    String[] listviewitemsStrGym = {"Alternating Dumbbell Bicep Curl", "Bent Over Roll - Overhand", "Bent Over Roll - Reverse Grip", "Cable Tricep Pull Down", "Decline Push up", "Incline Dumbbell Bench Press",  "Seated Dumbbell Shoulder Press",
            "Standing Cable Reverse Fly", "Standing Dumbbell Lateral Raise"};
    ListView absList;
    ListAdapter adapter;
    int finalmode;

    public StrFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_recommended_abs_list, container, false);
        ListHome parent = (ListHome)getActivity();
        finalmode = parent.getMode();
       // final int finalmode;

        absList = view.findViewById(R.id.abslist);


        if(finalmode==1) {
            adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,listviewitemsStr);
            finalmode=2;
        }
        else {
            finalmode = 5;
            adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,listviewitemsStrGym);
        }


        absList.setAdapter(adapter);
        absList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String TemplistView = listviewitemsStrGym[position].toString();

                if(finalmode==2) {
                    TemplistView = listviewitemsStr[position].toString();

                }
                else if(finalmode ==5) {
                    TemplistView = listviewitemsStrGym[position].toString();

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
