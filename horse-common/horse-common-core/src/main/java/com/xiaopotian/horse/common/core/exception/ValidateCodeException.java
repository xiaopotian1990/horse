package com.xiaopotian.horse.common.core.exception;

/**
 * ==========================================
 * Created with IntelliJ IDEA.
 * User: 小破天
 * Date: 2019-04-11
 * Time: 14:02
 * 博客园：http://www.cnblogs.com/xiaopotian/
 * ===========================================
 */
public class ValidateCodeException extends Exception {
    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException() {
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
