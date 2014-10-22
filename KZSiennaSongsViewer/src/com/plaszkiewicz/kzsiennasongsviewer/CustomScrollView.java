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
		int pointerCount = ev.getPointerCount();
		boolean shouldIntercept = ( pointerCount == 1);
	    boolean result = super.onInterceptTouchEvent(ev) && shouldIntercept;
	    
	    // System.out.println("custom scroll view onIntercept result: "+result);
	    
	    return result;
	}
}
