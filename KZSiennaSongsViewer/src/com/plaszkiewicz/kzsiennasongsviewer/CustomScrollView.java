package com.plaszkiewicz.kzsiennasongsviewer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class CustomScrollView extends ScrollView {

	public CustomScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
	    return super.onTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
	    //Call super first because it does some hidden motion event handling
	    boolean result = super.onInterceptTouchEvent(ev);
	    int pointerCount = ev.getPointerCount();
	    return result && pointerCount == 1;
	}
}
