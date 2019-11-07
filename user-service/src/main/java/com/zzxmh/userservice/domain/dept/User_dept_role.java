package com.zzxmh.userservice.domain.dept;

import java.util.Date;

public class User_dept_role {
    private Integer id;

    private String userId;

    private Integer deptRoleId;

    private Date entryTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getDeptRoleId() {
        return deptRoleId;
    }

    public void setDeptRoleId(Integer deptRoleId) {
        this.deptRoleId = deptRoleId;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }
}