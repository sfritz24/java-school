package com.lambdaschool.schools.exceptions;

import com.lambdaschool.schools.services.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomErrorDetails extends DefaultErrorAttributes
{
    @Autowired
    HelperFunctions helperFunctions;

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace)
    {
        Map<String, Object> errorAtteributes = super.getErrorAttributes(webRequest, includeStackTrace);

        Map<String, Object> errorDetails = new LinkedHashMap<>();
        errorDetails.put("title", errorAtteributes.get("error"));
        errorDetails.put("status", errorAtteributes.get("status"));
        errorDetails.put("detail", errorAtteributes.get("message"));
        errorDetails.put("timestamp", errorAtteributes.get("timestamp"));
        errorDetails.put("developerMessage", "path " + errorAtteributes.get("path"));

        errorDetails.put("errors", helperFunctions.getConstraintViolation(this.getError(webRequest)));

        return errorDetails;
    }
}
