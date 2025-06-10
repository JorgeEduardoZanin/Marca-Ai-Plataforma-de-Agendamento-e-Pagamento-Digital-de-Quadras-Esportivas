package com.marcaai.adapter.in.http.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ResponseError {

	private List<String> messages = new ArrayList<>();
	private String erro;
	private int codigo;
	private LocalDateTime timestemp;
	private String path;
	
	public ResponseError(List<String> messages, String erro, int codigo, LocalDateTime timestemp, String path) {
		this.messages = messages;
		this.erro = erro;
		this.codigo = codigo;
		this.timestemp = timestemp;
		this.path = path;
	}
	
	public ResponseError() {
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public LocalDateTime getTimestemp() {
		return timestemp;
	}

	public void setTimestemp(LocalDateTime timestemp) {
		this.timestemp = timestemp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}

