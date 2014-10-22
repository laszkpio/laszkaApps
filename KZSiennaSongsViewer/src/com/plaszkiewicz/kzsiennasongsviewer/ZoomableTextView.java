package com.plaszkiewicz.kzsiennasongsviewer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.TextView;

public class ZoomableTextView extends TextView {
	
	private static float scale = 1f;
	private ScaleGestureDetector SGD;

	public ZoomableTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init(){
		SGD = new ScaleGestureDetector(this.getContext(), new ScaleListener());
		initTextSize();
	}
	
	private void initTextSize(){
		setTextSize(scale * 20);
	}
	
	@Override
	   public boolean onTouchEvent(MotionEvent ev) {
	      SGD.onTouchEvent(ev);
	      return true;
	   }
	
	private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
		   @Override
		   public boolean onScale(ScaleGestureDetector detector) {
		      scale *= detector.getScaleFactor();
		      scale = Math.max(0.5f, Math.min(scale, 2.0f));	      
		      initTextSize();
		      return true;
		   }
	   }

}
