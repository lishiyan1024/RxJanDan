package com.nero.jandan.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.nero.jandan.R;
import com.nero.jandan.mvp.model.FreshNewsPosts;
import com.nero.jandan.mvp.model.FreshNewsPost;
import com.nero.jandan.ui.adapter.ViewHolder.FreshNewsViewHolder;
import com.nero.jandan.util.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishiyan on 16/12/14.
 */

public class FreshNewsPostsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

	public static final String TAG = FreshNewsPostsAdapter.class.getSimpleName();
	private List<FreshNewsPost> posts;
	private List<FreshNewsPost> tmpPosts;

	public FreshNewsPostsAdapter(){
		posts = new ArrayList<>();
		tmpPosts = new ArrayList<>();
	}

	public void setList(FreshNewsPosts freshNewsPosts){
		posts.clear();
		appendList(freshNewsPosts);
	}

	public void appendList(FreshNewsPosts freshNewsPosts){

		int positionStart = freshNewsPosts.getPosts().size();
		int itemCount = freshNewsPosts.getPosts().size() - positionStart;

		posts.addAll(freshNewsPosts.getPosts());

		if (positionStart == 0) {
			notifyDataSetChanged();
		}else{
			notifyItemRangeChanged(positionStart, itemCount);
		}
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent,final int viewType){
		View itemView = UIUtil.inflate(R.layout.recycler_item_fresh_news,parent);
		return new FreshNewsViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position){
		((FreshNewsViewHolder)holder).bindNewsView(posts.get(position));
	}

	@Override
	public int getItemCount(){
		return posts.size();
	}
}
