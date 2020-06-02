package com.truecaller.demo.exception.handler;

import java.util.List;

import lombok.Data;

@Data
public class ExceptionMessage {
	private List<String> message;
	private String path;
	private String error;
}
