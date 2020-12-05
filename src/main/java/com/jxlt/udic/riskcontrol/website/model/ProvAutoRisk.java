package com.jxlt.udic.riskcontrol.website.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProvAutoRisk
 *
 * @author zhout26
 * @version 1.0
 * 2020/12/5 8:58
 **/
@Data
@Table(name = "dic_prov_auto_temp_risk")
public class ProvAutoRisk {

    @Id
    @Column(name = "id")
    Integer id;

    @Column(name = "targetId")
    Integer targetId;

    @Column(name = "temp_id")
    Integer temp_id;

    public ProvAutoRisk(Integer autoRiskId, Integer temp_id, String type, String dataValue, String stat_date) {
        this.targetId = autoRiskId;
        this.temp_id = temp_id;
        this.type = type;
        this.dataValue = dataValue;
        this.stat_date = stat_date;
    }
    public ProvAutoRisk(Integer Id,Integer autoRiskId, Integer temp_id, String type, String dataValue, String stat_date) {
        this.id=Id;
        this.targetId = autoRiskId;
        this.temp_id = temp_id;
        this.type = type;
        this.dataValue = dataValue;
        this.stat_date = stat_date;
    }
    @Column(name = "type")
    String type;
    @Column(name = "dataValue")
    String dataValue;
    @Column(name = "stat_date")
    String stat_date;
}
