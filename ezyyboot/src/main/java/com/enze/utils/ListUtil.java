package com.enze.utils;

import java.util.List;

public class ListUtil {
public static String listToJsonString(List<String> list) {
	String jsonstr="";
	StringBuilder sb=new StringBuilder("[");
	for(int i=0;i<list.size();i++) {
		sb.append(list.get(i));
		if(i==list.size()-1) {
			sb.append("]");
		}else {
			sb.append(",");
		}
	}
	if(list==null || list.size()==0) {
		jsonstr="[]";
	}else {
		jsonstr=sb.toString();
	}
	return jsonstr;
}
}
