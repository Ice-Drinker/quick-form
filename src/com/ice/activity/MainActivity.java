package com.ice.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ice.service.IceTool;

/**
 * @author ice-drinker
 * @date 2014-3-17
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onSetValues(View view) {
		ContentValues values = new ContentValues();
		values.put("info", "User Infomation:");
		values.put("username", "tom");
		values.put("sex", "man");
		
		//view of activity
		View parentView = getWindow().getDecorView();
		//based on the entire activity page, achieve rapid filling data
		IceTool.get().setMViewValues(parentView, values);
	}

	public void onGetValues(View view) {
		View parentView = getWindow().getDecorView();
		//quick access to activity data
		ContentValues values = IceTool.get().getContentValues(parentView);
		
		String valueString = values.toString();
		if(valueString == null || valueString.length() == 0 || values.equals("")) {
			valueString = "No ice ui are assigned";
		}
		Toast.makeText(this, valueString, Toast.LENGTH_SHORT).show();
	}
}
