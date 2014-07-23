package com.ice.ui;

import com.ice.service.IiceUI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MRadioGroup extends RadioGroup implements IiceUI {
	private String value;

	public MRadioGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public String getValue() {
		String value = null;
		int cid = this.getCheckedRadioButtonId();
		if (cid != -1)
			value = ((RadioButton) findViewById(cid)).getTag().toString();

		return value;
	}

	@Override
	public void setValue(String value) {
		if (value == null || value.length() == 0) {
			this.value = getValue();
			return;
		}

		this.value = value;
		int count = getChildCount();
		
		for (int i = 0; i < count; i++) {
			View v = getChildAt(i);
			if (v instanceof RadioButton) {
				if (v.getTag().toString().equals(value)) {
					this.check(v.getId());
					break;
				}
			}
		}
	}

	@Override
	public String getKey() {
		return getTag().toString();
	}

	@Override
	public boolean isPollute() {
		return !getValue().equals(value);
	}
}
