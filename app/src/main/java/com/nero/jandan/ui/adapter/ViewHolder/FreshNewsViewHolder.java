package com.nero.jandan.ui.adapter.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nero.jandan.R;
import com.nero.jandan.mvp.model.FreshNewsPost;
import com.nero.jandan.util.IntentUtil;
import com.squareup.picasso.Picasso;

/**
 * Created by lishiyan on 16/12/14.
 */

public class FreshNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

	private final CardView mCardView;
	private final TextView tvTitle;
	private final ImageView ivNewsImage;
	private final TextView tvAuthor;
	private FreshNewsPost mPost;
	private Context mContext;

	public FreshNewsViewHolder(final View itemView){
		super(itemView);
		mCardView = (CardView) itemView.findViewById(R.id.card);
		tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
		tvAuthor = (TextView)itemView.findViewById(R.id.tvAuthor);
		ivNewsImage = (ImageView) itemView.findViewById(R.id.ivPost);
		mCardView.setOnClickListener(this);
		mContext = itemView.getContext();
	}

	@Override
	public void onClick(final View view){
		IntentUtil.intentToFreshNewsPostActivity((Activity)view.getContext(),mPost);
	}

	public void bindNewsView(FreshNewsPost post){
		mPost = post;
		tvTitle.setText(post.getTitle());
		tvAuthor.setText(post.getAuthor().getName()+" @"+post.getTags().get(0).getTitle());

		String imageUrl = post.getCustom_fields().getThumb_c().get(0);
		Picasso.with(mContext).load(imageUrl).into(ivNewsImage);
	}
}
