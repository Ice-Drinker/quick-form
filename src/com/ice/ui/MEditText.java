package com.ice.ui;

import com.ice.service.IiceUI;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class MEditText extends EditText implements IiceUI {
	private String value;

	public MEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public String getValue() {
		return getText().toString();
	}

	@Override
	public void setValue(String value) {
		setText(value);
		this.value = value;
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
