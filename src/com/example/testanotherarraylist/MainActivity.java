package com.example.testanotherarraylist;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener {
	Button button;
	ListView listView;
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		button = (Button) findViewById(R.id.testbutton);
		listView = (ListView) findViewById(R.id.list);
		String[] sports = getResources().getStringArray(R.array.sports_array);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, sports);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listView.setAdapter(adapter);

		button.setOnClickListener(this);

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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		  SparseBooleanArray checked = listView.getCheckedItemPositions();
	        ArrayList<String> selectedItems = new ArrayList<String>();
	        for (int i = 0; i < checked.size(); i++) {
	            // Item position in adapter
	            int position = checked.keyAt(i);
	            // Add sport if it is checked i.e.) == TRUE!
	            if (checked.valueAt(i))
	                selectedItems.add(adapter.getItem(position));
	        }
	 
	        String[] outputStrArr = new String[selectedItems.size()];
	 
	        for (int i = 0; i < selectedItems.size(); i++) {
	            outputStrArr[i] = selectedItems.get(i);
	        }
	 
	        Intent intent = new Intent(getApplicationContext(),
	                ResultActivity.class);
	 
	        // Create a bundle object
	        Bundle b = new Bundle();
	        b.putStringArray("selectedItems", outputStrArr);
	 
	        // Add the bundle to the intent.
	        intent.putExtras(b);
	 
	        // start the ResultActivity
	        startActivity(intent);

	}
}
