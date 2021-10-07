
package com.example.radio.Model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class CustomModel {

    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("template_id")
    @Expose
    private String templateId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("template_params")
    @Expose
    private TemplateParams templateParams;


    public CustomModel(String serviceId, String templateId, String userId, TemplateParams templateParams) {
        this.serviceId = serviceId;
        this.templateId = templateId;
        this.userId = userId;
        this.templateParams = templateParams;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public TemplateParams getTemplateParams() {
        return templateParams;
    }

    public void setTemplateParams(TemplateParams templateParams) {
        this.templateParams = templateParams;
    }

}
