package com.xiaopotian.horse.common.core.exception;

import lombok.NoArgsConstructor;

/**
 * ==========================================
 * Created with IntelliJ IDEA.
 * User: 小破天
 * Date: 2019-04-11
 * Time: 14:02
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
@NoArgsConstructor
public class CheckedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CheckedException(String message) {
		super(message);
	}

	public CheckedException(Throwable cause) {
		super(cause);
	}

	public CheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public CheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
