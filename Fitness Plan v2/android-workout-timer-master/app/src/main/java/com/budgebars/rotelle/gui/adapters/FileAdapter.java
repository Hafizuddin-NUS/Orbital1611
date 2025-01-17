package com.budgebars.rotelle.gui.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.budgebars.rotelle.R;
import com.budgebars.rotelle.files.ExerciseFile;

import java.util.List;

/**
 * Created by Jules on 10/18/2017.
 *
 */
public class FileAdapter extends BaseAdapter {
  private final List<ExerciseFile> exercise;

  private final Activity activity;

  /**
   * Creates an adapter that can display a list of exercise files.
   * @param exercise The exercise files to display.
   * @param parent The parent activity.
   */
  public FileAdapter(final List<ExerciseFile> exercise, final Activity parent) {
    super();

    this.exercise = exercise;
    this.activity = parent;
  }

  @Override
  public int getCount() {
    return this.exercise.size();
  }

  @Override
  public Object getItem(final int position) {
    return this.exercise.get(position);
  }

  @Override
  public long getItemId(final int position) {
    return position;
  }

  @Override
  public View getView(final int position, final View convertView, final ViewGroup parent) {
    View inflated = convertView;
    if (inflated == null) {
      LayoutInflater inflater = this.activity.getLayoutInflater();
      inflated = inflater.inflate(R.layout.item_file_list, parent, false);
    }

    final ExerciseFile current = (ExerciseFile) this.getItem(position);

    TextView nameView = inflated.findViewById(R.id.ExerciseFileName);
    nameView.setText(current.name());

    ImageButton deleteButton = inflated.findViewById(R.id.DeleteExerciseFileButton);
    deleteButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(final View view) {
        current.delete();
        FileAdapter.this.exercise.remove(position);
        FileAdapter.this.notifyDataSetChanged();
      }
    });

    return inflated;
  }

  /**
   * Updates the file list in this adapter to be the newly provided file list.
   * @param exerciseFiles The new list to display.
   */
  public void updateFileList(final List<ExerciseFile> exerciseFiles) {
    this.exercise.clear();
    this.exercise.addAll(exerciseFiles);

    this.notifyDataSetChanged();
  }
}