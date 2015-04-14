package org.appverse.web.framework.backend.frontfacade.rest.remotelog.model.presentation;

import org.appverse.web.framework.backend.core.beans.AbstractPresentationBean;

public class RemoteLogResponseVO extends AbstractPresentationBean{

	private static final long serialVersionUID = 7731734462869651852L;
	// Valid statuses
    public final static String OK = "OK";
    public final static String WARN = "WARN";
    public final static String ERROR = "ERROR";

    String status;

    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
