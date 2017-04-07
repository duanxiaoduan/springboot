 try {
            if (accesslogLogger.isInfoEnabled()) {
                AccessLog accessLog = new AccessLog();

                Cookie cookie = WebUtils.getCookie(request, "token");
                String token =  "游客";
                Long userId = 0L;
                if (null!=cookie){
                    token = cookie.getValue();
                    String userKey = (String) tokenOps.get("token:" + token);
                    String memberIdStr = userKey.substring("cache:bimt:userinfo:".length());
                    userId  = Longs.tryParse(memberIdStr);
                }
                accessLog.setToken(token);
                accessLog.setSessionId(WebUtils.getSessionId(request));
                accessLog.setIp(getRemoteAddr(request));
                accessLog.setUrl(request.getRequestURL().toString() + (request.getQueryString() == null ? "" : ("?" + request.getQueryString())));
                accessLog.setUserId(userId);
              //  accessLog.setExpertId((Long) WebUtils.gsetSessionAttribute(request, Constant.EXPERT_ID));
                accessLog.setReferrer(request.getHeader("Referer"));
                accessLog.setAjax("XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
                accessLog.setUserAgent(request.getHeader("User-Agent"));
                accesslogLogger.info(accessLog.toString());
            }
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
	}
