package com.nero.jandan.util;

/**
 * Created by lishiyan on 17/1/10.
 */

public class HtmlUtil{

	public static String getHtml(String content,String title,String author) {
		final StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html>");
		sb.append("<html dir=\"ltr\" lang=\"zh\">");
		sb.append("<head>");
		sb.append("<meta name=\"viewport\" content=\"width=100%; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;\" />");
		sb.append("<link rel=\"stylesheet\" href='file:///android_asset/style.css' type=\"text/css\" media=\"screen\" />");
		sb.append("</head>");
		sb.append("<body style=\"padding:0px 8px 8px 8px;\">");
		sb.append("<div id=\"pagewrapper\">");
		sb.append("<div id=\"mainwrapper\" class=\"clearfix\">");
		sb.append("<div id=\"maincontent\">");
		sb.append("<div class=\"post\">");
		sb.append("<div class=\"posthit\">");
		sb.append("<div class=\"postinfo\">");
		sb.append("<h2 class=\"thetitle\">");
		sb.append("<a>");
		sb.append(title);
		sb.append("</a>");
		sb.append("</h2>");
		sb.append(author);
		sb.append("</div>");
		sb.append("<div class=\"entry\">");
		sb.append(content);
		sb.append("</div>");
		sb.append("</div>");
		sb.append("</div>");
		sb.append("</div>");
		sb.append("</div>");
		sb.append("</div>");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
}
