package cn.enilu.flash.bean.enumeration;

/**
 * Created by deanyule on 17/4/13.
 * 系统各种编号枚举
 */
public enum SerialNumberEnum {

    //交易编号
    TR_DFYW_KLT("TRDFYWKLT", "代付-开联通"),
    TR_DFYW_CJ0("TRDFYWCJ0", "代付-畅捷"),
    TR_DFYW_LL0("TRDFYWLL0", "代付-连连"),
    TR_DFYW_RB0("TRDFYWRB0", "代付-融宝"),
    TR_DFYW_YSB("TRDFYWYSB", "代付-银生宝"),
    TR_DSYW_CJ0("TRDSYWCJ0", "代收-畅捷"),
    TR_DSYW_YSB("TRDSYWYSB", "代收-银生宝"),
    TR_DSYW_CPCN("TRDSYWCPC", "代收-中金"),
    TR_DSYW_BAOFU("TRDSYWBF", "代收-宝付"),
    TR_DSYW_FY0("TRDSYWFY0", "代收-富友"),
    TR_KJWG_CJ0("TRKJWGCJ0", "快捷-畅捷"),
    TR_KJWG_FY0("TRFY", "快捷-富友"),
    TR_KJWG_LL0("TRKJWGLL0", "快捷-连连"),
    TR_KJWG_WX0("TRKJWGWX0", "快捷-微信"),
    TR_KJTK_CJ0("TRKJTKCJ0", "快捷-退款-畅捷"),
    TR_KJZL_KLT("TRKJZLKLT", "快捷-直连-开联通"),
    /**第三方抽成*/
    TR_CCSN_000("TRCCSN000", "第三方抽成"),
    TR_MANUL_000("TRMANUL00", "手工还款记账"),

    //借款申请编号
    JK_SQC("JKSQC", "借款申请客户为Company"),
    JK_SQP("JKSQP", "借款申请客户为PC"),
    JK_SQS("JKSQS", "借款申请客户为ShareTransfer"),
    JK_SQM("JKSQM", "借款申请客户为Mobile"),
    JK_SQH("JKSQH", "借款申请客户为person on H5"),

    //协议模板编号
    XDXY_L_C("XDXYLC","借款项目Lending Company"),
    XDXY_L_P("XDXYLP", "借款项目Lending Person"),
    XDXY_M_J("XDXYMJ", "居间协议M 借款居间J"),
    XDXY_R("XDXYR0", "注册协议R 0补位"),
    XDXY_Z("XDXYZ0", "征信协议Z 0补位"),

    //协议子编号
    XDXY_L_P_0001("XDXYLP0001", "协议编号,借款项目Lending Person"),
    XDXY_R_P_0001("XDXYRP0001", "协议编号,代收协议签约"),

    //消息发送编号
    XX_E("XXE", "发送方式Email"),
    XX_S("XXS", "发送方式SMS"),
    XX_L("XXL", "发送方式Letter"),
    XX_W("XXW", "发送方式微信"),

    //消息模板编号
    XXMB_CL("XXMBCL","credit lending个人信贷"),
    XXMB_MC("XXMBMC", "小贷"),
    XXMB_YX("XXMBYX", "营销类"),

    //用户ID编号
    US_JK_C("USJKC", "借款JK来源Company"),
    US_JK_P("USJKP", "借款JK 来源Person on PC"),
    US_JK_S("USJKS", "借款JK 来源Share transfer"),
    US_JK_M("USJKM", "借款JK 来源person on Mobile"),
    US_JK_H("USJKH", "借款JK 来源person on H5")
    ;


    private String code;

    private String desc;

    private SerialNumberEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
