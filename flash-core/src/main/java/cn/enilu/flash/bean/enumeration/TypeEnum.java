package cn.enilu.flash.bean.enumeration;

public class TypeEnum {

	/**
	 * 输入数据的html类型
	 */
	public enum DataItemShowType {
		/**
		 * 0为文本框；1为下拉框；2为日期框
		 */
		TEXT(0), SELECT(1), DATE(2);

		private int value;

		private DataItemShowType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	/**
	 * 调用类型枚举类
	 */
	public enum InvokeTypeEnum {

		/**
		 * 1:本地；1：远程
		 */
		LOCAL("local"), REMOTE("remote");

		private String value;

		private InvokeTypeEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	/**
	 * 调用结果枚举类
	 */
	public enum InvokeStateEnum {

		/**
		 * 1:查询成功；-1：查询无数据；2：查询异常
		 */
		SUCCESS(1, "0000"), NO_DATA(-1, "0001"), ERROR(2, "9999"), ERROR_NO_WARN(2, "9999");

		private int value;

		private String code;

		private InvokeStateEnum(int value, String code) {
			this.value = value;
			this.code = code;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public static boolean isError(InvokeStateEnum invokeState) {
			return InvokeStateEnum.ERROR == invokeState || InvokeStateEnum.ERROR_NO_WARN == invokeState;
		}

		public static boolean isNeedWarning(InvokeStateEnum invokeState) {
			return InvokeStateEnum.ERROR == invokeState;
		}

		public static InvokeStateEnum getNoData() {
			return InvokeStateEnum.NO_DATA;
		}

		public static InvokeStateEnum getSuccess() {
			return InvokeStateEnum.SUCCESS;
		}

		public static InvokeStateEnum getError() {
			return InvokeStateEnum.ERROR;
		}

		public static InvokeStateEnum getErrorNoWarn() {
			return InvokeStateEnum.ERROR_NO_WARN;
		}
	}

	/**
	 * 订单来源枚举类
	 */
	public enum OrderChannelEnum {
		WEB(0, "web"), WS(1, "接口");

		private int value;

		private String name;

		private OrderChannelEnum(int value, String name) {
			this.value = value;
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public enum AdminType {
		/**
		 * 0为普通用户；1为超级管理员；2为机构管理员
		 */
		NORMAL(0), SUPERADMIN(1), ORGADMIN(2);

		private int value;

		private AdminType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	public enum MatchType {
		/**
		 * 0为模糊匹配;1为精确匹配;
		 */
		FUZZY(0), EXACT(1);

		private int value;

		private MatchType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	public enum MessageType {
		/**
		 * 0为系统通知;1为订阅通知;2为用户发送
		 */
		NOTIFY(0), SUBSCRIBE(1), SEND(2);

		private int value;

		private MessageType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	public enum Aspect {

		AFTERPAY("afterPay"),
		EVENTTRACK("eventTrack"),
		SNOWLOG("snowlog"),
		AFTERACTION("afterAction"),
		ENTERFINAL("enterFinal"),
		QUARTZJOB("quartzJob"),
		AUTOTRIGGER("autoTrigger"),
		PAYLIMIT("payLimit");

		private String value;

		private Aspect(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public enum JobStatus {
		NONE("NONE", "未知"),
		NORMAL("NORMAL", "正常运行"),
		PAUSED("PAUSED", "暂停状态"),
		COMPLETE("COMPLETE", "运行完成"),
		ERROR("ERROR", "错误状态"),
		BLOCKED("BLOCKED", "锁定状态");

		private JobStatus(String index, String name) {
			this.name = name;
			this.index = index;
		}

		private String index;
		private String name;

		public String getName() {
			return name;
		}

		public String getIndex() {
			return index;
		}
	}

}
