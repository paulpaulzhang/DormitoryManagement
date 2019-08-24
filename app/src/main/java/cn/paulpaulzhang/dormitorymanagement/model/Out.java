package cn.paulpaulzhang.dormitorymanagement.model;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.database.model
 * 创建时间: 8/3/2019
 * 创建人: zlm31
 * 描述:
 */
@Entity
public class Out {
    @Id
    private long id;

    private long studentId;

    private Date date;

    private String remark;

    public Out(long studentId, Date date, String remark) {
        this.studentId = studentId;
        this.date = date;
        this.remark = remark;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
