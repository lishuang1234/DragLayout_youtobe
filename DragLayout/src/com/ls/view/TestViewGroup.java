package com.ls.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class TestViewGroup extends ViewGroup {

	public TestViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	static class LayoutParams extends ViewGroup.LayoutParams {
		public LayoutParams(Context context, AttributeSet attr) {
			super(context, attr);
			// TODO Auto-generated constructor stub

		}

		public int leftMargin;
		public int rightMargin;
		public int topMargin;
		public int bottomMargin;

	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		// TODO Auto-generated method stub
		return new TestViewGroup.LayoutParams(getContext(), attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		measureChildren(widthMeasureSpec, heightMeasureSpec);// 测量孩子大小
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		int mGroupWidth = getMeasuredWidth();// 当前ViewGroup宽度
		int count = getChildCount();// 孩子数目
		int x = l;// 放置子孩子x坐标
		int y = t;
		for (int i = 0; i < count; i++) {
			View view = getChildAt(i);// 获得子孩子
			TestViewGroup.LayoutParams layoutParams = (LayoutParams) view
					.getLayoutParams();

			System.out.println(count + "   " + layoutParams.leftMargin);
			int childWidth = view.getMeasuredWidth() + layoutParams.leftMargin
					+ layoutParams.rightMargin;// 不包括marging
			int childHeight = view.getMeasuredHeight();
			if (x + childWidth > mGroupWidth) {
				x = l;
				y += childHeight + layoutParams.topMargin
						+ layoutParams.bottomMargin;
			}
			view.layout(x, y, x + childWidth, y + childHeight);
			x += childWidth;
		}

	}
}
