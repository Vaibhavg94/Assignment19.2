package com.vaibhav.assignment192.network;


import com.vaibhav.assignment192.utils.CommonUtilities;

public interface OnWebServiceResult {
	void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type);
}
