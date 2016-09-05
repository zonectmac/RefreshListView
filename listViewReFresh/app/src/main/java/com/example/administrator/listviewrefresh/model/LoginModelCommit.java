package com.example.administrator.listviewrefresh.model;


        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class LoginModelCommit {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("warehouseId")
    @Expose
    private String warehouseId;

    /**
     *
     * @return
     * The userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     * The userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     * The password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     * The warehouseId
     */
    public String getWarehouseId() {
        return warehouseId;
    }

    /**
     *
     * @param warehouseId
     * The warehouseId
     */
    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public LoginModelCommit(String userId, String password, String warehouseId) {
        this.userId = userId;
        this.password = password;
        this.warehouseId = warehouseId;
    }
}
