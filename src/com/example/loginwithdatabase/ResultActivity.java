package com.example.loginwithdatabase;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ResultActivity extends Activity {
	private TextView textViewResult = null;
	private Button buttonBack = null;
	private ListView listViewPerson = null;
	private List<Person> persons = null;
	private ArrayAdapter<String> arrayAdapter = null;
	private PersonSQLiteHelper db = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		textViewResult = (TextView) findViewById(R.id.textViewResult);
		Intent intent = getIntent();
		String username = intent.getStringExtra("username");
		Log.d("ANHNBT", username);
		textViewResult.setText("Xin chào " + username);
		
		buttonBack = (Button) findViewById(R.id.buttonBack);
		buttonBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		db = new PersonSQLiteHelper(getApplicationContext());
		
		db.onUpgrade(db.getWritableDatabase(), 1, 2);
		db.save(new Person(1, "AnhNBT", "M"));
		db.save(new Person(2, "Khoai Tay", "M"));
		db.save(new Person(3, "Rose", "F"));
		
		persons = db.findAll();
		List<String> listPersonName = new ArrayList<String>();
		
		for (int i = 0; i < persons.size(); i++) {
			listPersonName.add(persons.get(i).getName());
		}
		
		arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listPersonName);
		listViewPerson = (ListView) findViewById(R.id.listViewPerson);
		listViewPerson.setAdapter(arrayAdapter);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
