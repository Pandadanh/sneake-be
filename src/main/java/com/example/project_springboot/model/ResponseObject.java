package com.example.project_springboot.model;

import java.util.List;
import java.util.Map;

public class ResponseObject {
    private String status;
    private String mes;
    private String trans_page;
    private Object data;
    private Map<String, List<?>> list_data;

    public ResponseObject() {
    }

    public ResponseObject(String status, String mes) {
        this.status = status;
        this.mes = mes;
    }
    public ResponseObject(String status, String mes, String trans_page, Object data) {
        this.status = status;
        this.mes = mes;
        this.trans_page = trans_page;
        this.data = data;
    }

    public ResponseObject(String status, String mes, String trans_page,  Map<String, List<?>> list_data) {
        this.status = status;
        this.mes = mes;
        this.trans_page = trans_page;
        this.list_data = list_data;
    }

    public  Map<String, List<?>>getList_data() {
        return list_data;
    }

    public void setList_data( Map<String, List<?>> list_data) {
        this.list_data = list_data;
    }

    public String getTrans_page() {
        return trans_page;
    }

    public void setTrans_page(String trans_page) {
        this.trans_page = trans_page;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
