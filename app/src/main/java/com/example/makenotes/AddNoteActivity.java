package com.example.makenotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE="com.example.makenotes.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION="com.example.makenotes.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY="com.example.makenotes.EXTRA_PRIORITY";
    private EditText editTextTitle,editTextDescription;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        editTextDescription=findViewById(R.id.edit_text_description);
        editTextTitle=findViewById(R.id.edit_text_title);
        numberPicker=findViewById(R.id.numberPicker);

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close);
        setTitle("Add Note");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                saveNote();
                return true;

             default:
                 return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        String title=editTextTitle.getText().toString();
        String description=editTextDescription.getText().toString();
        int priority=numberPicker.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty() ){
            Toast.makeText(this,"Insert Title and description",Toast.LENGTH_LONG).show();
            return;
        }

        Intent data=new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);

        setResult(RESULT_OK,data);
        finish();
    }
}
