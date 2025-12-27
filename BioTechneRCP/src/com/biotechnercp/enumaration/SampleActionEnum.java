package com.biotechnercp.enumaration;

public enum SampleActionEnum {

	ADD("Add","Add Sample", "com.biotechnercp.command.addSample"),

	EDIT("Edit","Edit Sample", "com.biotechnercp.command.editSample"),
	
	DELETE("Delete", "Delete Sample", "com.biotechnercp.command.deleteSample");
	
	private final String action;
	
	private final String title;
	
	private final String commandId;

	private SampleActionEnum(String action, String title, String commandId) {
		this.action = action;
		this.title = title;
		this.commandId = commandId;
	}

	public String getAction() {
		return action;
	}

	public String getTitle() {
		return title;
	}

	public String getCommandId() {
		return commandId;
	}
	
	
	
}
