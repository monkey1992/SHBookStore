package com.xiaoyu.shbookstore.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.xiaoyu.shbookstore.R;

public class CommentView extends BaseView {
	
	private ListView lvComment;
	
	public CommentView(Context context) {
		super(context);
	}

	@Override
	public void setListener() {

	}

	@Override
	public void init() {
		showInMiddle = (ViewGroup) View.inflate(context, R.layout.prod_comments_activity, null);
		lvComment = (ListView) findViewById(R.id.listComments);
	}

	@Override
	public int getIdentifier() {
		return 0;
	}

}
