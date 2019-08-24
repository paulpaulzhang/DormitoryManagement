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
public class Visitor {
    @Id
    private long id;

    private String visitor;

    private String reason;

    private String tel;

    public Visitor(String visitor, String reason, String tel) {
        this.visitor = visitor;
        this.reason = reason;
        this.tel = tel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
