package cn.enilu.flash.bean.exception;

public class ParamException extends Exception {

	public ParamException(String msg) {
		super(msg);
	}

	public ParamException(String msg, Throwable e) {
		super(msg, e);
	}
}
