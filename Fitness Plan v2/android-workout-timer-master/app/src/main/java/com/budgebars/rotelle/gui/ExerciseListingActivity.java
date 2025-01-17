package com.budgebars.rotelle.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.budgebars.rotelle.R;
import com.budgebars.rotelle.files.ExerciseFile;
import com.budgebars.rotelle.files.InternalFileManager;
import com.budgebars.rotelle.gui.adapters.FileAdapter;
import com.budgebars.rotelle.workouts.editable.EditableExercise;

import java.io.File;
import java.util.List;

public class ExerciseListingActivity extends AppCompatActivity {

  public static final String EXERCISE_FILE = "EXERCISE_FILE";

  public static final String EDITABLE_EXERCISE = "EDITABLE_EXTRA";

  public static final String EXERCISE_TO_RUN = "EXERCISE_TO_RUN";

  private Toolbar toolbar;

  private FileAdapter adapter;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.activity_exercise_listing);

      toolbar = (Toolbar) findViewById(R.id.exercise_listingbar);

      setSupportActionBar(toolbar);

      getSupportActionBar().setTitle("Fitness Plan - Interval Training List");

    InternalFileManager files = new InternalFileManager(this);
    if (!files.hasExercisesDirectory()) {
      files.createExercisesDirectory();
    }

    if (!files.hasExercises()) {
      files.addSampleExerciseFile(this);
    }

    File[] exerciseFiles = files.getExerciseFiles();
    final List<ExerciseFile> exercises = ExerciseFile.fromFiles(exerciseFiles);

    ListView listing = this.findViewById(R.id.FileListView);
    this.adapter = new FileAdapter(exercises, this);
    listing.setAdapter(this.adapter);

    listing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(final AdapterView<?> parent,
                                final View view,
                                final int position,
                                final long id) {
            ExerciseFile exerciseFile = (ExerciseFile) parent.getItemAtPosition(position);
            Intent intent = new Intent(ExerciseListingActivity.this, ExerciseActivity.class);
            intent.putExtra(ExerciseListingActivity.EXERCISE_FILE, exerciseFile);
            com.budgebars.rotelle.gui.ExerciseListingActivity.this.startActivity(intent);
        }
    });

    Button createButton = this.findViewById(R.id.CreateExerciseButton);
    createButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            ExerciseListingActivity.this.createExercise();
            finish();
        }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    this.populateExerciseListing();
  }

  private void populateExerciseListing() {
    InternalFileManager files = new InternalFileManager(this);

    File[] exerciseFiles = files.getExerciseFiles();
    final List<ExerciseFile> exercises = ExerciseFile.fromFiles(exerciseFiles);

    this.adapter.updateFileList(exercises);
  }

  private void createExercise() {
    EditableExercise blank = EditableExercise.blankEditableExercise();

    Intent intent = new Intent(ExerciseListingActivity.this, EditExerciseActivity.class);
    intent.putExtra(ExerciseListingActivity.EDITABLE_EXERCISE, blank);
    this.startActivity(intent);
  }
}
