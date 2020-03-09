package com.sshfortress.common.huanxinutil;

import java.util.List;



public class Reg {
	
//	{
//		  "action": "post",
//		  "application": "4e725a30-676e-11e7-9676-efea1f1d9655",
//		  "path": "/users",
//		  "uri": "https://a1.easemob.com/1144170713115876/test/users",
//		  "entities": [
//		    {
//		      "uuid": "b6715b10-677b-11e7-b9d4-f903d66c0788",
//		      "type": "user",
//		      "created": 1499916669761,
//		      "modified": 1499916669761,
//		      "username": "a123",
//		      "activated": true,
//		      "nickname": "ccccccc"
//		    }
//		  ],
//		  "timestamp": 1499916669767,
//		  "duration": 0,
//		  "organization": "1144170713115876",
//		  "applicationName": "test"
//		}
	private String action;
	private String application;
	private String path;
	private String uri;
	private String timestamp;
	private String duration;
	private String organization;
	private String applicationName;
	private List<entity> entities;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public List<entity> getEntities() {
		return entities;
	}
	public void setEntities(List<entity> entities) {
		this.entities = entities;
	}
	
}
