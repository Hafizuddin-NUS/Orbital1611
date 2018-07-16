package com.budgebars.rotelle.gui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
public class AbsFragment extends Fragment {

    String[] listviewitemsAbs = {"Abs Exercise 1", "Abs Exercise 2", "Abs Exercise 3"};
    ListView absList;
    ListAdapter adapter;

    public AbsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_recommended_abs_list, container, false);

        absList = view.findViewById(R.id.abslist);
        adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,listviewitemsAbs);

        absList.setAdapter(adapter);
        absList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String TemplistView = listviewitemsAbs[position].toString();

                TemplistView = listviewitemsAbs[position].toString();


                Intent intent = new Intent(getActivity(), ViewExercises.class);
                intent.putExtra("list",1);
                intent.putExtra("name", TemplistView);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });


        return view;
    }

}
