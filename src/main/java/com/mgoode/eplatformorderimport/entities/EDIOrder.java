package com.mgoode.eplatformorderimport.entities;


import java.io.Serializable;
import java.util.List;


//@Entity
//@Table(name="edi_order_header")
public class EDIOrder implements Serializable {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    String orderUUID;
    Long FK_FILECONFIG_FILECONFIGID;
    String STATUS;
    String cont_no;
    String SysVar0;
    String SysVar1;

    //@OneToMany(mappedBy = "ediOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SkuLine> skuLineList;

    public EDIOrder() {
        super();
    }

    public String getOrderUUID() {
        return orderUUID;
    }

    public void setOrderUUID(String orderUUID) {
        this.orderUUID = orderUUID;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getFK_FILECONFIG_FILECONFIGID() {
        return FK_FILECONFIG_FILECONFIGID;
    }

    public void setFK_FILECONFIG_FILECONFIGID(Long FK_FILECONFIG_FILECONFIGID) {
        this.FK_FILECONFIG_FILECONFIGID = FK_FILECONFIG_FILECONFIGID;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getCont_no() {
        return cont_no;
    }

    public void setCont_no(String cont_no) {
        this.cont_no = cont_no;
    }

    public String getSysVar0() {
        return SysVar0;
    }

    public void setSysVar0(String sysVar0) {
        SysVar0 = sysVar0;
    }

    public String getSysVar1() {
        return SysVar1;
    }

    public void setSysVar1(String sysVar1) {
        SysVar1 = sysVar1;
    }

    public List<SkuLine> getSkuLineList() {
        return skuLineList;
    }

    public void setSkuLineList(List<SkuLine> skuLineList) {
        this.skuLineList = skuLineList;
    }
}
