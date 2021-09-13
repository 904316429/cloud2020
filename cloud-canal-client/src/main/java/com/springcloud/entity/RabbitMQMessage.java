package com.springcloud.entity;

import java.util.List;
import java.util.Map;

/**
 * @description: 消息实体类 RabbitMq
 * @author: Jiang
 * @create: 2021-09-09 15:44
 */
public class RabbitMQMessage implements java.io.Serializable {

    private static final long serialVersionUID = -5219854747443596916L;
    // 新的数据
    private List<Map<String, String>> data;
    // 数据库名称
    private String database;
    // es 不知道是啥
    private Long es;
    // messageId
    private Long id;
    // isDdl 不知道是啥
    private Boolean isDdl;
    // 字段数据类型
    private Map<String, String> mysqlType;
    // 旧的数据只针对盗修改字段
    private List<Map<String, String>> old;
    // 主键名称
    private List<String> pkNames;
    // sql
    private String sql;
    // sql 类型
    private Map<String, String> sqlType;
    // 数据库表
    private String table;
    // ts 时间?
    private Long ts;
    // 增删改类型
    private String type;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Map<String, String>> getData() {
        return data;
    }

    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Long getEs() {
        return es;
    }

    public void setEs(Long es) {
        this.es = es;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDdl() {
        return isDdl;
    }

    public void setDdl(Boolean ddl) {
        isDdl = ddl;
    }

    public Map<String, String> getMysqlType() {
        return mysqlType;
    }

    public void setMysqlType(Map<String, String> mysqlType) {
        this.mysqlType = mysqlType;
    }

    public List<Map<String, String>> getOld() {
        return old;
    }

    public void setOld(List<Map<String, String>> old) {
        this.old = old;
    }

    public List<String> getPkNames() {
        return pkNames;
    }

    public void setPkNames(List<String> pkNames) {
        this.pkNames = pkNames;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<String, String> getSqlType() {
        return sqlType;
    }

    public void setSqlType(Map<String, String> sqlType) {
        this.sqlType = sqlType;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RabbitMQMessage{" +
                "data=" + data +
                ", database='" + database + '\'' +
                ", es=" + es +
                ", id=" + id +
                ", isDdl=" + isDdl +
                ", mysqlType=" + mysqlType +
                ", old=" + old +
                ", pkNames=" + pkNames +
                ", sql='" + sql + '\'' +
                ", sqlType=" + sqlType +
                ", table='" + table + '\'' +
                ", ts=" + ts +
                ", type='" + type + '\'' +
                '}';
    }
}
