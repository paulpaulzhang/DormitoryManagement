package cn.paulpaulzhang.dormitorymanagement.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.database.model
 * 创建时间: 8/3/2019
 * 创建人: zlm31
 * 描述:
 */
@Entity
public class TroubleShooting {
    @Id
    private long id;

    private String register;

    private String detail;

    private boolean isFix;

    public TroubleShooting(String register, String detail, boolean isFix) {
        this.register = register;
        this.detail = detail;
        this.isFix = isFix;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isFix() {
        return isFix;
    }

    public void setFix(boolean fix) {
        isFix = fix;
    }
}
