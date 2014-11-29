package com.ls.view;

import android.R.integer;
import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.ls.draglayout.R;

public class DragLayout extends LinearLayout {

	private final ViewDragHelper helper;
	private View mDragView;

	public DragLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub

		helper = ViewDragHelper.create(this, 1.0f, new MyCallBacak());

	}

	private void initView() {
		// TODO Auto-generated method stub
		mDragView = findViewById(R.id.text);
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		initView();
		super.onFinishInflate();
	}

	class MyCallBacak extends ViewDragHelper.Callback {

		@Override
		public boolean tryCaptureView(View view, int arg1) {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			// TODO Auto-generated method stub
			final int groupWidth = getWidth();
			final int groupLeft = getPaddingLeft();
			final int groupRight = getPaddingRight();

			if (left < groupLeft)
				return groupLeft;
			if (left > (groupWidth - groupRight - child.getWidth())) {
				return groupWidth - groupRight - child.getWidth();
			}

			return left;

		}

		@Override
		public void onViewPositionChanged(View changedView, int left, int top,
				int dx, int dy) {
			// TODO Auto-generated method stub
			System.out.println("left:" + left + ":" + " top:" + top);
			System.out.println("dx:" + dx + ":" + " dy:" + dy);
			super.onViewPositionChanged(changedView, left, top, dx, dy);
		}

		@Override
		public int clampViewPositionVertical(View child, int top, int dy) {
			// TODO Auto-generated method stub
			return top;
		}

	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		final int action = MotionEventCompat.getActionMasked(ev);
		System.out.println(action);
		if (action == MotionEvent.ACTION_CANCEL
				|| action == MotionEvent.ACTION_UP) {
			helper.cancel();
			return false;
		}
		return helper.shouldInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		System.out.println("touch:" + event.getAction());
		helper.processTouchEvent(event);
		return true;
	}

}
