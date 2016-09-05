package com.example.administrator.listviewrefresh.model;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

 class Data {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("key")
    @Expose
    private String key;

    /**
     *
     * @return
     * The token
     */
    public String getToken() {
        return token;
    }

    /**
     *
     * @param token
     * The token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     *
     * @return
     * The key
     */
    public String getKey() {
        return key;
    }

    /**
     *
     * @param key
     * The key
     */
    public void setKey(String key) {
        this.key = key;
    }

     @Override
     public String toString() {
         return "Data{" +
                 "token='" + token + '\'' +
                 ", key='" + key + '\'' +
                 '}';
     }
 }


public class LoginModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("systime")
    @Expose
    private String systime;
    @SerializedName("data")
    @Expose
    private Data data;

    /**
     *
     * @return
     * The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     *
     * @param msg
     * The msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     *
     * @return
     * The systime
     */
    public String getSystime() {
        return systime;
    }

    /**
     *
     * @param systime
     * The systime
     */
    public void setSystime(String systime) {
        this.systime = systime;
    }

    /**
     *
     * @return
     * The data
     */
    public Data getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", systime='" + systime + '\'' +
                ", data=" + data.toString() +
                '}';
    }
}
