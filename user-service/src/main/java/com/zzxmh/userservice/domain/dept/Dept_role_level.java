package com.zzxmh.userservice.domain.dept;

public class Dept_role_level {
    private Integer id;

    private Integer deptRoleId;

    private Integer levelId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeptRoleId() {
        return deptRoleId;
    }

    public void setDeptRoleId(Integer deptRoleId) {
        this.deptRoleId = deptRoleId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }
}