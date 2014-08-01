package com.pearson.classroomcommunity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pearson.classroomcommunity.data.ClassPickerAdapter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class ClassPickerActivity extends ActionBarActivity {

	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_picker);

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.exClassView);

		// preparing list data
		prepareListData();

		listAdapter = new ClassPickerAdapter(this, listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Expanded",
						Toast.LENGTH_SHORT).show();
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Collapsed",
						Toast.LENGTH_SHORT).show();

			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Toast.makeText(
						getApplicationContext(),
						listDataHeader.get(groupPosition)
								+ " : "
								+ listDataChild.get(
										listDataHeader.get(groupPosition)).get(
										childPosition), Toast.LENGTH_SHORT)
						.show();
				return false;
			}
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.class_picker, menu);
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
	


	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Math 101");
		listDataHeader.add("History or the American Civil War");
		listDataHeader.add("Basket Weaving 101");

		// Adding child data
		List<String> math101 = new ArrayList<String>();
		math101.add("Basic Math Text");
		math101.add("Video of guy doing basic math");
		math101.add("Supplemetary excercises");

		List<String> cwHistory = new ArrayList<String>();
		cwHistory.add("The American Civil War");
		cwHistory.add("The Civil War: A Documentary by Ken Burns");
		cwHistory.add("How do dress like a Union Soldier");
		cwHistory.add("How do dress like a Conderate Soldier");

		List<String> bw101 = new ArrayList<String>();
		bw101.add("Basket Weaving 101: The Ultimate Beginner's Guide...");
		bw101.add("Basket Tales of the Grandmothers: American Indian Baskets in Myth and Legend");
		bw101.add("Songs to Weave To by Ima Slacker");

		listDataChild.put(listDataHeader.get(0), math101); // Header, Child data
		listDataChild.put(listDataHeader.get(1), cwHistory);
		listDataChild.put(listDataHeader.get(2), bw101);
	}
}
