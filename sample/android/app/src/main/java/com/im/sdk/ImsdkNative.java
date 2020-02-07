package com.im.sdk;

public class ImsdkNative {

    public static void loadImsdkLibrary(){
        try {
            System.loadLibrary("imsdk");
            System.out.println("222");
        }
        catch (Throwable e) {
            System.out.println("imsdklog: " + e);
        }
    }

    static {
        ImsdkNative.loadImsdkLibrary();
    }

	/*业务接口(异步调用)
    @param[in] jstr_key	操作键，json格式：
                        {
                            "reqid": 123, //流水号：每一次调用的异步请求ID，必须大于0
                            "opcode": 101 //操作码：区分不同的请求接口
                        }
    @param[in] jstr_req	APP请求内容，json格式，见protocol
    @return true = 成功，false = 失败: 失败原因为json格式不匹配
    */
	public final native boolean AsyncCall(String jstr_key, String jstr_req);

	/*============================== 初始化配置相关 ==============================*/
	/*初始化IM: 在所有其他im接口调用之前调用，且仅需调用一次，一般程序启动初始化时调用
	@param [in] cb_instance		回调对象: 由APP负责具体实现
	@param [in] basepath		sdk内部db存放的路径：sdk内部会在此路径下创建imsdk_data目录，此目录将作为sdk内部存储数据的目录
	@return true=成功，false=失败
	*/
    public final native boolean InitImClient(Object cb_instance, String basepath);

	/*反初始化IM: 与InitImClient对应，在程序退出前调用
	@return true=成功，false=失败
	*/
	public final native boolean UnInitImClient();

	/*设置当前设备类型[可选接口, 仅供调试]
	@param [in] dev_type		设备类型[不传则由SDK内部判断取值]：	1=Android，2=iOS，3=windows
	@return true=成功，false=失败
	*/
	public final native boolean SetDevType(long dev_type);
	
	/*设置sdk显示和记录日志的等级[可选接口, 仅供调试]
	@param [in] show			是否输出verbose日志, 默认等级为warn（true: 开启verbose日志等级; false: 开启warn日志等级）, 生产环境建议设置为false
	@param [in] location		日志输出位置：0=全部(默认)，1=屏幕，2=文件；支持位或操作，如: 1|2=全部
	@return true=成功，false=失败
	*/

    public final native boolean ShowLog(boolean show, long location);
	
	/** 切换sdk内部运行环境[可选接口, 仅供调试]
	@param [in] flag			标识：1=生产环境(默认)，2=test环境，3=release环境，4=beta环境，5=develop环境
	@return true=成功，false=失败
	*/
    public final native boolean SwitchRunEnvironment(long flag);

	/*============================== 上层通知sdk ==============================*/
	/** 网络类型切换
	 @param [in] type		类型：0=未知的网络类型，1=没有网络连接，2=wifi，3=2G，4=3G，5=4G，6=5G
	 @return true = 成功，false = 失败
	 */
	public final native void OnNetTypeChange(long type);

	/** app状态变更
	 @param [in] type		类型：0=其他，1=后台，2=前台，3=进程结束
	 @return true = 成功，false = 失败
	 */
	public final native void OnAppStatusChange(long status);

	/** 上层请求完http，传递http返回的数据给sdk
	 @param [in] resp_type	http响应的类型：0=Get, 1=Post, 2=文件上传
	 @param [in] respid		sdk调用app的http接口时传递的reqid
	 @param [in] resp_code	本地失败时返回-1，请求成功时返回http状态码
	 @param [in] resp_data	http返回的数据，失败时传空字符串
	 @param [in] resp_size	http返回的数据缓冲区大小
	 @return true = 成功，false = 失败
	 */
	public final native void OnHttpResponse(long resp_type, long respid, int resp_code, String resp_data);


	/*============================== 工具类接口 ==============================*/
	/** 生成一个client msgid
	 @return client msgid
	 */
	public final native String GetCliMsgId();

	/** 生成一个seqid
	 @return seqid
	 */
	public final native long GetSeqId();

	/** 获取系统支持的最小msgid
	 @return 最小msgid
	 */
	public final native String GetMinMsgId();

	/** 获取系统支持的最大msgid
	 @return 最大msgid
	 */
	public final native String GetMaxMsgId();
}
