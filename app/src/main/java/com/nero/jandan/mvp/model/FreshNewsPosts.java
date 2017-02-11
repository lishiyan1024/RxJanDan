package com.nero.jandan.mvp.model;

import java.util.List;

/**
 * Created by lishiyan on 16/12/14.
 */

public class FreshNewsPosts{

	private String status;
	private int count;
	private int count_total;
	private int pages;
	private List<FreshNewsPost> posts;

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public int getCount(){
		return count;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount_total(){
		return count_total;
	}

	public void setCount_total(int count_total){
		this.count_total = count_total;
	}

	public int getPages(){
		return pages;
	}

	public void setPages(int pages){
		this.pages = pages;
	}

	public List<FreshNewsPost> getPosts(){
		return posts;
	}

	public void setPosts(List<FreshNewsPost> posts){
		this.posts = posts;
	}
}
