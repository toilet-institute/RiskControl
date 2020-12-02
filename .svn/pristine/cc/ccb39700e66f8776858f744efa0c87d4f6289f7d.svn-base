package com.jxlt.udic.riskcontrol.website.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TargetFile
 *
 * @author zhout26
 * @version 1.0
 * 2020/11/20 15:51
 **/
@Data
@Table(name = "dic_target_file")
public class TargetFile {
    /**  风险点id */
    @Id
    @Column(name="target_id")
    private String target_id;
    /**  文件id 用于访问*/
    @Column(name="fileId")
    private String fileId;
    public TargetFile(){}

    public TargetFile(String target_id, String fileId) {
        this.target_id = target_id;
        this.fileId = fileId;
    }
}
