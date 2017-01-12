package com.theinhtikeaung.yangonbuses.models;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import pasca.common.lib.helper.BaseConstants;
import pasca.common.lib.helper.BaseKeys;
import pasca.common.lib.helper.HTTPRetroFitTask;

public class Response {

	private int statusCode = 400;
	private JSONObject content;
	public String oricontent;

	private String tag;
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

	private HTTPRetroFitTask task;
	public HTTPRetroFitTask getTask() {
		return task;
	}
	public void setTask(HTTPRetroFitTask task) {
		this.task = task;
	}

	private boolean updateResponse = false;
	public boolean isUpdateResponse() {
		return updateResponse;
	}
	public void setUpdateResponse(boolean updateResponse) {
		this.updateResponse = updateResponse;
	}

	public InputStream inputstream;
	public InputStream getInputstream() {
		return inputstream;
	}
	public void setInputstream(InputStream inputstream) {
		this.inputstream = inputstream;
	}

	private Map<String, List<String>> headerContent;

	public static final Response getDummyResponse() {
		return new Response(200, "");
	}

	public Response(int statusCode, String content) {
		oricontent = content;
		this.statusCode = statusCode;
		try {
			this.content = new JSONObject(new JSONTokener(content));
		} catch (JSONException e) {
			try {
				JSONArray jAr = new JSONArray(new JSONTokener(content));
				this.content = new JSONObject();
				this.content.put(BaseKeys.RESULTS, jAr);
			} catch (JSONException ex) {
				this.content = new JSONObject();
				try {
					this.content.put(BaseKeys.RESULT, oricontent);
				} catch (Exception e1) {}
			}
		}
	}

	public int getStatusCode() {
		return statusCode;
	}

	public JSONObject getContent() {
		return content;
	}

	public DateTime getHeaderTime() {
		if (headerContent == null)
			return DateTime.now();

		try {
			for (Map.Entry<String, List<String>> k : headerContent.entrySet()) {
				if (k.getKey() != null && k.getKey().equals(BaseKeys.DATE_))
					for (String v : k.getValue()) {
						v = v.substring(0, v.trim().lastIndexOf(" "));
						return DateTime.parse(v,
								DateTimeFormat.forPattern(BaseConstants.DATE_EDMYHMS));
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return DateTime.now();
	}

	public void setHeaderContent(Map<String, List<String>> headerContent) {
		this.headerContent = headerContent;
	}

}
