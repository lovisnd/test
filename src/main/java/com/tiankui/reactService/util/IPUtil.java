package com.tiankui.reactService.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class IPUtil {
	public static Long getIPNum(final String IP) {
		Long IPNum = 0l;
		final String IPStr = IP.trim();
		if (IP != null && IPStr.length() != 0) {
			final String[] subips = IPStr.split("\\.");
			for (final String str : subips) {
				// 向左移8位
				IPNum = IPNum << 8;
				IPNum += Integer.parseInt(str);
			}
		}
		return IPNum;
	}

	public static String getIPString(final Long IPNum) {
		final Long andNumbers[] = { 0xff000000L, 0x00ff0000L, 0x0000ff00L, 0x000000ffL };
		final StringBuilder IPStrSb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			IPStrSb.append(String.valueOf((IPNum & andNumbers[i]) >> 8 * (3 - i)));
			if (i != 3) {
				IPStrSb.append(".");
			}
		}
		return IPStrSb.toString();
	}
	
	/**
     * 获取Ip地址
     * @param request
     * @return
     */
    public static String getIpAdrress(HttpServletRequest request) {
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if(index != -1){
                return XFor.substring(0,index);
            }else{
                return XFor;
            }
        }
        XFor = Xip;
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            return XFor;
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }

}