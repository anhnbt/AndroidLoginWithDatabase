package com.example.loginwithdatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText editTextUsername = null;
	private EditText editTextPassword = null;
	private Button buttonLogin = null;
	private Bundle bundle = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editTextUsername = (EditText) findViewById(R.id.editTextUsername);
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		buttonLogin = (Button) findViewById(R.id.buttonLogin);

		buttonLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				bundle = new Bundle();
				Intent i = new Intent(MainActivity.this, ResultActivity.class);
				bundle.putString("username", editTextUsername.getText()
						.toString());
				bundle.putString("password", editTextPassword.getText()
						.toString());
				i.putExtras(bundle);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
