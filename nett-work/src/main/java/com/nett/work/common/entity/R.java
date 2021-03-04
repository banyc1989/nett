package com.nett.work.common.entity;

import java.util.HashMap;

import com.nett.work.common.container.ResponseMsgNametContainer;

/**
 * 返回数据
 * 
 * @author chenxc
 * @email 
 * @date 
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

	/**
	 * 返回空数据 但是成功
	 */
	public R() {
		put("errcode", ResponseMsgNametContainer.SUCCESS_RESPONSE_CODE);
		put("errmsg", ResponseMsgNametContainer.SUCCESS_RESPONSE_MSG);
	}

	/**
	 * 返回数据 成功
	 * @param data
	 */
	public R(Object data) {
		put("errcode", ResponseMsgNametContainer.SUCCESS_RESPONSE_CODE);
		put("errmsg", ResponseMsgNametContainer.SUCCESS_RESPONSE_MSG);
		put("data",data);
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put("errcode", code);
		r.put("errmsg", msg);
		r.put("data","");
		return r;
	}

	public static R ok() {
		return new R();
	}

	public static R ok(Object data) {
		return new R(data);
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}


}