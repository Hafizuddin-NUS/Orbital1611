package com.budgebars.rotelle.gui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.budgebars.rotelle.R;
import com.budgebars.rotelle.files.ExerciseFile;
import com.budgebars.rotelle.gui.adapters.IntervalAdapter;
import com.budgebars.rotelle.workouts.Exercise;
import com.budgebars.rotelle.workouts.editable.EditableExercise;

import java.io.File;

public class ExerciseActivity extends AppCompatActivity {
  private static final String INTENT_TYPE = "text/plain";

  private static final String FILE_PROVIDER_AUTHORITY = "com.budgebars.rotelle.fileprovider";

  private static final String INTENT_TITLE = "Share exercise file:";

  private static final String NO_APP_MESSAGE = "No sharing app found.";

  private ExerciseFile exerciseFile;

  private Exercise exercise;
  private Toolbar toolbar;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    this.setContentView(R.layout.activity_exercise);

    this.exerciseFile = (ExerciseFile)
        this.getIntent().getSerializableExtra(ExerciseListingActivity.EXERCISE_FILE);
    this.exercise = this.exerciseFile.getExercise();

    this.setTitle(this.exercise.name() + " (" + this.exercise.totalLength() + ") ");

    toolbar = (Toolbar) findViewById(R.id.exercise_startbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("Fitness Plan - Interval Training");

    ListView list = this.findViewById(R.id.ExerciseDisplayView);
    list.setAdapter(new IntervalAdapter(this.exercise, this));

    Button startExerciseButton = this.findViewById(R.id.StartExerciseButton);
    startExerciseButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(final View view) {
        Intent intent = new Intent(ExerciseActivity.this, RunningTimerActivity.class);
        intent.putExtra(ExerciseListingActivity.EXERCISE_TO_RUN, ExerciseActivity.this.exercise);
          ExerciseActivity.this.startActivity(intent);
      }
    });

    Button editExerciseButton = this.findViewById(R.id.EditExerciseButton);
    editExerciseButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
          Intent intent =
              new Intent(ExerciseActivity.this, EditExerciseActivity.class);
          intent.putExtra(ExerciseListingActivity.EDITABLE_EXERCISE,
              new EditableExercise(ExerciseActivity.this.exercise));
          ExerciseActivity.this.startActivity(intent);
        }
    });

  }


}
