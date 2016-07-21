package com.hxq.mobile.util.api;

import java.net.BindException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.wxcommon.http.AjaxUtils;
import com.wxcommon.util.JsonUtil;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

/**
 * 定义前端APP调用时返回异常信息
 *
 */
public class ApiExceptionResolver extends DefaultHandlerExceptionResolver {
	Logger log = LoggerFactory.getLogger(ApiExceptionResolver.class);

	@Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		String accept = StringUtils.trimToEmpty(request.getHeader("accept"));
		accept = ObjectUtils.isEmpty(accept) != true ? accept.toLowerCase()
				: StringUtils.trimToEmpty(request.getHeader("Accept")).toLowerCase();
		if (accept.indexOf("application/json") > -1 || AjaxUtils.isAjaxRequest(request)) {
			AjaxUtils.sendJson(response, convertExceptionToJson(ex));
			log.error("Call API Error!", ex);
            return null;
		} else if (accept.indexOf("application/xml") > -1) {
			AjaxUtils.sendXml(response, convertExceptionToXml(ex));
			log.error("Call API Error!", ex);
            return null;
		}
		return super.doResolveException(request, response, handler, ex);
    }

	private String convertExceptionToJson(Exception ex) {
        if(ex instanceof NoSuchRequestHandlingMethodException
        		|| ex instanceof HttpRequestMethodNotSupportedException
        		|| ex instanceof HttpMediaTypeNotSupportedException)
			return JsonUtil.toJSONString(ApiResult.error(ApiCode.NOT_FOUND, ex.getMessage()));

        if(ex instanceof HttpMediaTypeNotAcceptableException
        		|| ex instanceof MissingServletRequestParameterException
        		|| ex instanceof ServletRequestBindingException
        		|| ex instanceof ConversionNotSupportedException
        		|| ex instanceof TypeMismatchException
        		|| ex instanceof HttpMessageNotReadableException
        		|| ex instanceof HttpMessageNotWritableException
        		|| ex instanceof MethodArgumentNotValidException
        		|| ex instanceof MissingServletRequestPartException
        		|| ex instanceof BindException)
        	return JsonUtil.toJSONString(ApiResult.error(ApiCode.SERVER_ERROR, ex.getMessage()));

        try {
            if(ex instanceof NoHandlerFoundException)
            	return JsonUtil.toJSONString(ApiResult.error(ApiCode.NOT_FOUND, ex.getMessage()));
        } catch(Exception handlerException) {}
		return JsonUtil.toJSONString(ApiResult.error(ApiCode.SERVER_ERROR, ex.getMessage()));
	}

	private String convertExceptionToXml(Exception ex) {
		//TODO
		return null;
	}
}
