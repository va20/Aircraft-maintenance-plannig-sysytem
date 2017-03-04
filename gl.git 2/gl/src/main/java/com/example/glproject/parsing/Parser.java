package com.example.glproject.parsing;

import java.util.List;

import com.example.glproject.businessobjects.GenericTask;

public interface Parser {
	
	public List<GenericTask> parse(String file);
}
