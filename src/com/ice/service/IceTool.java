package com.ice.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

/**
 * @author ice-drinker
 * @date 2014-3-17
 */
public class IceTool {

	static class IceUIToolHolder {
		static IceTool iceTool = new IceTool();
	}

	private IceTool() {
		super();
	}

	public static IceTool get() {
		return IceUIToolHolder.iceTool;
	}

	/**
	 * get all the ice components on view
	 * 
	 * @param parentView
	 * @return list
	 */
	public List<IiceUI> getAllMViews(View parentView) {
		List<IiceUI> ices = new ArrayList<IiceUI>();

		if (parentView instanceof ViewGroup) {
			ViewGroup vp = (ViewGroup) parentView;

			for (int i = 0; i < vp.getChildCount(); i++) {
				View viewchild = vp.getChildAt(i);
				if (viewchild instanceof IiceUI) {
					IiceUI iView = (IiceUI) viewchild;
					
					if(viewchild.getTag() != null)
						ices.add(iView);

					if (iView instanceof ViewGroup) {
						ViewGroup v = (ViewGroup) iView;
						ices.addAll(getAllMViews(v));
					}
				} else if (viewchild instanceof AbsListView) {
//					Log.v("ice getAllMViews", "ignore ice components in the AbsListView");
				} else {
					if (viewchild instanceof ViewGroup) {
						ViewGroup vg = (ViewGroup) viewchild;
						ices.addAll(getAllMViews(vg));
					}
				}
			}
		}

		return ices;
	}

	/**
	 * get all the ice component value on view
	 * 
	 * @param parentView
	 * @return map
	 */
	public ContentValues getContentValues(View parentView) {
		if (parentView == null)
			return null;

		ContentValues values = new ContentValues();
		List<IiceUI> iceUIs = getAllMViews(parentView);

		for (IiceUI ice : iceUIs) {
			String k = ice.getKey();
			String v = ice.getValue();

			if (ice.isPollute())
				values.put(k, v);
		}

		Log.v("ice get values", values.toString());
		return values;
	}

	/**
	 * get all the ice component value on view
	 * 
	 * @param parentView
	 * @return map
	 */
	public JSONObject getJSONValues(View parentView) {
		if (parentView == null)
			return null;

		JSONObject values = new JSONObject();
		List<IiceUI> iceUIs = getAllMViews(parentView);

		for (IiceUI ice : iceUIs) {
			String k = ice.getKey();
			String v = ice.getValue();

			if (ice.isPollute()) {
				try {
					values.put(k, v);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}

		Log.v("ice get values", values.toString());
		return values;
	}

	/**
	 * assign all the ice components on view
	 * 
	 * @param view
	 * @param ContentValues
	 */
	public void setMViewValues(View view, ContentValues values) {
		Log.v("ice set values", values.toString());
		List<IiceUI> iceUIs = getAllMViews(view);
		for (IiceUI v : iceUIs) {
			String k = v.getKey();

			if (values.containsKey(k))
				v.setValue(values.getAsString(k));
		}
	}

}
