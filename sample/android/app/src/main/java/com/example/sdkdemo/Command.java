package com.example.sdkdemo;

//import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;

//import com.im.sdk.IMSdkNotice;
import static com.im.sdk.ImsdkOpcode.*;
import com.im.sdk.ImsdkCallback;
import com.im.sdk.ImsdkNative;
import com.im.sdk.ImsdkOpcode;

public class Command {
    public Command(MainActivity activity, ImsdkNative imsdk_native){
        activity_ = activity;
        imsdk_native_ = imsdk_native;
    }

    private ImsdkNative imsdk_native_;
    private MainActivity activity_;
    private int title_index_;
    private int collect_index_;

    public void init() {
        title_index_ = 0;
        collect_index_ = 0;
    }

    public  void destroy() {
        imsdk_native_.UnInitImClient();
    }

    public void login() {
        setTitleCollect("login");
////        imsdk_native_.SetLoginParameters(imsdk_native_.GetSeqId(), "aaaaa", "a@test.com", "1111", "2222", "3333", "app_key", "ticket___");
//        setTitle("设置登录参数");     callImSdk(ImsdkOpcode.kOPLoginParams.getCode(), "{\"corp_account\": \"1\",\"user_account\": \"3\",\"gcid\": \"1\",\"cid\": \"1\",\"uid\": \"5@k.com\", \"appkey\": \"a111111\", \"ticket\": \"imei_test\"}");
//        setTitle("切换环境");         imsdk_native_.SwitchRunEnvironment(3);//release 环境
        setTitle("设置登录参数");     callImSdk(ImsdkOpcode.kOPLoginParams.getCode(),"{\"corp_account\" : \"tqjmgpgvjg\",\"user_account\" : \"5@k.com\",\"gcid\" : 833629691783938048,\"cid\" : \"1103482089937829888\",\"uid\" : 1565192966082663263,\"appkey\" : \"eteams\",\"ticket\" : \"S6SCWLBacohD0xSAOpTWgIYY+CqpyTLlQiTSoIvCQJq8f/rt6clKWi8NWKFGnpYCTr+SJlG37HwAPZ63XKqh4CxsLTt7Hh7hy92wlQfT8JOh4ZvJZ0Aq4qHhm8lnQCrioeGbyWdAKuKh4ZvJZ0Aq4qHhm8lnQCrioeGbyWdAKuKh4ZvJZ0Aq4qHhm8lnQCrioeGbyWdAKuKh4ZvJZ0Aq4qHhm8lnQCrioeGbyWdAKuKh4ZvJZ0Aq4qHhm8lnQCrioeGbyWdAKuKh4ZvJZ0Aq4qHhm8lnQCrioeGbyWdAKuKh4ZvJZ0Aq4qHhm8lnQCrioeGbyWdAKuKh4ZvJZ0Aq4g==\"}");
        setTitle("切换test环境");         imsdk_native_.SwitchRunEnvironment(2);
        setTitle("登录");             callImSdk(ImsdkOpcode.kOPLogin.getCode(), "{\"type\":1, \"extends\":\"android_extends\"}");
    }

    public void logout() {
//        setTitle("logout"); imsdk_native_.LogoutIm(imsdk_native_.GetSeqId());
    }

    public void next() {
//        setTitleCollect("login");           loginTest();          //登录相关
//        setTitleCollect("group");           groupTest();          //群组相关
//        setTitleCollect("chat");            ChatTest();           //聊天相关
//        setTitleCollect("groupchat");       groupchatTest();      //群聊相关
//        setTitleCollect("system");          systemTest();           //系统消息相关
//        setTitleCollect("session");          sessionTest();         //会话相关
//        setTitleCollect("user");            userTest();             //用户信息相关
//        setTitleCollect("external");            externalTest();       //外部联系人相关
//        setTitleCollect("remind");            remindTest();       //消息免打扰相关
//        setTitleCollect("topic");            topicTest();           //话题相关
//        setTitleCollect("user sound");        usersoundTest();        //声音提醒

//        setTitleCollect("other");           otherTest();          //其他
    }

    public void reset() {
        setTitleCollect("reset");
//        setTitle("ClearCache");      imsdk_native_.ClearCache(imsdk_native_.GetSeqId(), 0);
    }

    public void send(String msg) {

//        char a[] = {'h','e','l','l','o', '|', 97, '|', 0xF0, 0x9F, 0x98, 0xA2, '|', 0xE2, 0x98, 0xBA, '|', 0xF0, 0x9F, 0x98, 0x8C, '!'};
//        String msg = new String(a);
//        String msg = "Hello everybody!";
//        setTitle("发送表情消息: " + msg); imsdk_native_.SendChatMsg(imsdk_native_.GetSeqId(), imsdk_native_.GetCliMsgId(), "{\"key\": { \"type\": 2, \"group_id\": 1571281460272114}, \"kind\": 1001}", msg, "group_title");

        String wrap_msg = "{\\\"ver\\\": \\\"1.0\\\",\\\"dev\\\" : \\\"5\\\",\\\"guid\\\" : \\\"0\\\",\\\"cnt\\\" : \\\"0\\\",\\\"idx\\\" : \\\"0\\\",\\\"type\\\" : \\\"1\\\",\\\"suid\\\" : \\\"5815186667396611840\\\",\\\"scid\\\" : \\\"1103234746952974336\\\",\\\"sname\\\" : \\\"chu five\\\",\\\"dt\\\" : [{\\\"txt\\\": {\\\"v\\\": \\\""
                        + msg
                        + "\\\",\\\"next\\\" : \\\"0\\\",\\\"ft\\\" : \\\"china\\\"}}]}";
        String jstr_out = "{\"cli_msgid\": " + imsdk_native_.GetCliMsgId()
                        + ", \"user\": {\"cid\": 1103482089937829888, \"uid\":1565192560718062634}"
                        + ", \"kind\": 1001"
                        + ", \"msg\": \"" + wrap_msg + "\", \"title\":\"\""
                        + "}";

        setTitle("发送单聊消息"); callImSdk(ImsdkOpcode.kOPSendSingleMsg.getCode(), jstr_out);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //登录相关
    protected void loginTest() {
//        setTitle("SetMobileInfo");      imsdk_native_.SetMobileInfo(imsdk_native_.GetSeqId(), "mobile information");
//        setTitle("SetStatusChange");    imsdk_native_.SetStatusChange(imsdk_native_.GetSeqId(), 1);
//        setTitle("SetDevStatus");       imsdk_native_.SetOtherDevOffline(imsdk_native_.GetSeqId(), 1, "extend");
//
//        String jstr_userlist = "[{\"cid\": " + get_own_cid() + ",\"uid\": " + get_own_uid() + "},{\"cid\": \"1057527575997317120\",\"uid\": 1564097016562146043},{\"cid\": \"920838052455120896\",\"uid\": 1560838007299478803}]";
//        setTitle("GetUserOnlineStatus");imsdk_native_.GetUserOnlineStatus(imsdk_native_.GetSeqId(), jstr_userlist);
    }

    //单聊相关
    protected void ChatTest() {
//        String friend_cid = "1103234746952974336";
//        String friend_uid = "5815186667396611840";
//        String start_msgid = "1562745576250070";
//
////        imsdk_native_.SendSingleMsg(imsdk_native_.GetSeqId(), imsdk_native_.GetCliMsgId(), friend_cid, friend_uid, "hello, my friend", 1001);
//
//        String jstr_cond_list = "[{\"target_cid\": " + get_own_cid() + ",\"target_uid\":" + get_own_uid() + ", \"start_msgid\": " + start_msgid + ",\"end_msgid\": \"0\",\"max_num\": 3}," +
//                "{\"target_cid\": \"920838052455120896\",\"target_uid\": \"1560838033771579374\",\"start_msgid\": \"18446744073709551615\",\"end_msgid\": 0,\"max_num\": 2}]";
//        setTitle("GetSingleMsgForMultiUser"); imsdk_native_.GetSingleMsgForMultiUser(imsdk_native_.GetSeqId(), jstr_cond_list);
//        setTitle("GetSingleMsgForSingleUser"); imsdk_native_.GetSingleMsgForSingleUser(imsdk_native_.GetSeqId(), friend_cid, friend_uid, start_msgid, "0", 1000000);
//        setTitle("GetSingleMsgByKind"); imsdk_native_.GetSingleMsgByKind(imsdk_native_.GetSeqId(), friend_cid, friend_uid, imsdk_native_.GetMaxMsgId(), imsdk_native_.GetMinMsgId(), 12, 1001);
//        setTitle("GetSingleMsgByPage"); imsdk_native_.GetSingleMsgByPage(imsdk_native_.GetSeqId(), friend_cid, friend_uid, start_msgid, "0", 350, 30);
//        setTitle("GetSingleMsgByMsgid"); imsdk_native_.GetSingleMsgByMsgid(imsdk_native_.GetSeqId(),friend_cid, friend_uid, start_msgid);
//
//        setTitle("SetSingleMsgReadStatusByMsgid"); imsdk_native_.SetSingleMsgReadStatusByMsgid(imsdk_native_.GetSeqId(), friend_cid, friend_uid, "[1562728570347000000, 54321，32145]");
//        setTitle("GetSingleMsgReadStatus"); imsdk_native_.GetSingleMsgReadStatus(imsdk_native_.GetSeqId(), friend_cid, friend_uid, "[1562745576250070, 54321，32145]", "[1562745576250070, 54321，32145]");
//        setTitle("WithdrawSingleMsg"); imsdk_native_.WithdrawSingleMsg(imsdk_native_.GetSeqId(), friend_cid, friend_uid, start_msgid, "{hello, my friend}", 1001, "title");
//        setTitle("SaveSingleChatDraftMsg"); imsdk_native_.SaveSingleChatDraftMsg(imsdk_native_.GetSeqId(), friend_cid, friend_uid, "draft content");
//        setTitle("GetSingleChatDraftMsg"); imsdk_native_.GetSingleChatDraftMsg(imsdk_native_.GetSeqId(), friend_cid, friend_uid);
//        setTitle("DeleteSingleChatDraftMsg"); imsdk_native_.DeleteSingleChatDraftMsg(imsdk_native_.GetSeqId(), friend_cid, friend_uid);
//        setTitle("GetLocalHistorySingleMsg"); imsdk_native_.GetLocalHistorySingleMsg(imsdk_native_.GetSeqId(), friend_cid, friend_uid, start_msgid, "0", 100, 1001);
//        setTitle("ClearSigMsg"); imsdk_native_.ClearSigMsg(imsdk_native_.GetSeqId(), friend_cid, friend_uid);
//        setTitle("InitLocalSingleMsgHistoryView"); imsdk_native_.InitLocalSingleMsgHistoryView(imsdk_native_.GetSeqId(), friend_cid, friend_uid, 5, "[1001, 1002]");
//        setTitle("GetLocalSingleMsgByPageIndexView"); imsdk_native_.GetLocalSingleMsgByPageIndexView(imsdk_native_.GetSeqId(), friend_cid, friend_uid, 1);
//        setTitle("GetLocalSingleMsgPageIndexByMsgidView"); imsdk_native_.GetLocalSingleMsgPageIndexByMsgidView(imsdk_native_.GetSeqId(), friend_cid, friend_uid, start_msgid);
//        setTitle("SearchLocalSingleMsgView"); imsdk_native_.SearchLocalSingleMsgView(imsdk_native_.GetSeqId(), friend_cid, friend_uid, start_msgid, 1, "friend");
//        setTitle("SaveFailedSingleMsg"); imsdk_native_.SaveFailedSingleMsg(imsdk_native_.GetSeqId(), "1234567", friend_cid, friend_uid, "failed to send msg", 1001);
//        setTitle("DeleteSavedFailedSingleMsg"); imsdk_native_.DeleteSavedFailedSingleMsg(imsdk_native_.GetSeqId(), "1234567", friend_cid, friend_uid);

//        setTitle("GetGroupInfo"); imsdk_native_.GetGroupInfo(imsdk_native_.GetSeqId(), "[1571281460272114]", 0);
//        setTitle("GetChatMsg"); imsdk_native_.GetChatMsg(imsdk_native_.GetSeqId(), "{\"id\": { \"type\": 2, \"group_id\":  1571281460272114}, \"start_msgid\": 1571281461272115, \"end_msgid\": 0, \"max_num\": 10, \"page_size\": 10}");
    }

    //群组相关
    protected void groupTest() {
        String group_id = "1562746982270103";
        String friend_cid = "1103234746952974336";
        String friend_uid = "5815186667396611840";

//        setTitle("CreateGroup"); imsdk_native_.CreateGroup(imsdk_native_.GetSeqId(), "g32", 0, "");
//        setTitle("InviteToGroup"); imsdk_native_.InviteToGroup(imsdk_native_.GetSeqId(), group_id, "[{\"cid\": " + friend_cid + ", \"uid\": " + friend_uid + "}]");
//        setTitle("ExitGroup"); imsdk_native_.ExitGroup(imsdk_native_.GetSeqId(), group_id);
//        setTitle("KickGroupMember"); imsdk_native_.KickGroupMember(imsdk_native_.GetSeqId(),  group_id, "[{\"cid\": " + friend_cid + ", \"uid\": " + friend_uid + "}]");
//        setTitle("ModifyGroupInfo"); imsdk_native_.ModifyGroupInfo(imsdk_native_.GetSeqId(),  group_id, "{" +
//                                                                                                                            "\"announce\": \"aaa\", " +
//                                                                                                                            "\"display\": 33," +
//                                                                                                                            "\"groupname\": \"g32_a\"," +
//                                                                                                                            "\"msg_setting\": 33," +
//                                                                                                                            "\"host\": {" +
//                                                                                                                                    "\"cid\": 811160604843704320," +
//                                                                                                                                    "\"uid\": 555555" +
//                                                                                                                                "}" +
//                                                                                                                        "}");
//        setTitle("SetGroupAdmin"); imsdk_native_.SetGroupAdmin(imsdk_native_.GetSeqId(),  group_id, "[{\"cid\": " + friend_cid + ", \"uid\": " + friend_uid + "}]", 1);
//        setTitle("ModifyGroupAvatar"); imsdk_native_.ModifyGroupAvatar(imsdk_native_.GetSeqId(),  group_id, "avatar");
//        setTitle("SetGroupRSOnOff"); imsdk_native_.SetGroupRSOnOff(imsdk_native_.GetSeqId(),  group_id,1);
//        setTitle("GetGroupInfo"); imsdk_native_.GetGroupInfo(imsdk_native_.GetSeqId(),  "[" + group_id +",\"123456\", \"654321\"]", 0);
//        setTitle("IsGroupMember"); imsdk_native_.IsGroupMember(imsdk_native_.GetSeqId(), "[" + group_id +",\"123456\", \"654321\"]");
//        setTitle("GetGroupOperNote"); imsdk_native_.GetGroupOperNote(imsdk_native_.GetSeqId(),  group_id, 100);
//        setTitle("GetGroupOperRel"); imsdk_native_.GetGroupOperRel(imsdk_native_.GetSeqId(),  group_id, 110);
//        setTitle("SearchGroup"); imsdk_native_.SearchGroup(imsdk_native_.GetSeqId(), "{\"group_type\" : 0,\"name\" : \"g\",\"group_id\" : 1}");
//        setTitle("GetGroupList"); imsdk_native_.GetGroupList(imsdk_native_.GetSeqId(), 0);
//        setTitle("GetGroupMemberList"); imsdk_native_.GetGroupMemberList(imsdk_native_.GetSeqId(), group_id,0);
//        String search_groupid = "{\"group_id\" : 1562746982270103}";
//        String search_name = "{\"name\" : \"五五群\"}";
//        setTitle("SearchGlobalGroup"); imsdk_native_.SearchGlobalGroup(imsdk_native_.GetSeqId(), search_name, "0", 5);
//        setTitle("JoinGroup"); imsdk_native_.JoinGroup(imsdk_native_.GetSeqId(), "1562746982270103", "Hello any group, I am Bob!");
//        setTitle("ReplyGroupApply"); imsdk_native_.ReplyGroupApply(imsdk_native_.GetSeqId(), "1562746982270103", 0, "0", "0", "I agree", "2222");//已被ApplyRely代替
//        setTitle("ApplyRely"); imsdk_native_.ApplyRely(imsdk_native_.GetSeqId(), "1564382408270048", "1", "I agree");//因未实现http接口，该测试无效
//        setTitle("SetGroupOnOff"); imsdk_native_.SetGroupOnOff(imsdk_native_.GetSeqId(), "1562746982270103", 1, 1);
    }

    //群聊相关
    protected void groupchatTest(){
//        String group_id = "1562746982270103";
//        String friend_cid = "1103234746952974336";
//        String friend_uid = "5815186667396611840";
//        String msgid = "1562750305280108";
//
//        setTitle("SendGroupMsg"); imsdk_native_.SendGroupMsg(imsdk_native_.GetSeqId(),  group_id, "1", 1001, "group_msg_title", "hello, every body!");
////        setTitle("MakeGroupMsgWithdraw"); imsdk_native_.MakeGroupMsgWithdraw(imsdk_native_.GetSeqId(),  group_id, msgid, "{hello, every body!}", 1001, "withdraw_group_msg_title");
////        setTitle("GroupMsgScreen"); imsdk_native_.GroupMsgScreen(imsdk_native_.GetSeqId(),  group_id, msgid, "{hello, every body!}", 1001, "screen_group_msg_title");
////        setTitle("SaveGroupChatDraftMsg");imsdk_native_.SaveGroupChatDraftMsg(imsdk_native_.GetSeqId(),  group_id, "group draft");
////        setTitle("GetGroupChatDraftMsg"); imsdk_native_.GetGroupChatDraftMsg(imsdk_native_.GetSeqId(),  group_id);
////        setTitle("DeleteGroupChatDraftMsg");    imsdk_native_.DeleteGroupChatDraftMsg(imsdk_native_.GetSeqId(), group_id);
////        setTitle("SetGroupMsgReadStatusByMsgid");   imsdk_native_.SetGroupMsgReadStatusByMsgid(imsdk_native_.GetSeqId(),  group_id, "[1562750305280108, 54321, 32145]");
////        setTitle("GetGroupMsgReadStatus");  imsdk_native_.GetGroupMsgReadStatus(imsdk_native_.GetSeqId(),  group_id, "[1562750305280108, 54321，32145]", "[1562750305280108, 54321, 32145]");
////        setTitle("GetGroupMsgReadDetail");  imsdk_native_.GetGroupMsgReadDetail(imsdk_native_.GetSeqId(),  group_id, msgid);
////        setTitle("SetGroupAtInfo"); imsdk_native_.SetGroupAtInfo(imsdk_native_.GetSeqId(), group_id, msgid, "[{\"cid\": 1103234746952974336, \"uid\": 5815186667396611840}" +
////                ",{\"cid\": 2354354345, \"uid\": 345345345}]", 1);
////        setTitle("GetGroupMsgForMultiGroup");   imsdk_native_.GetGroupMsgForMultiGroup(imsdk_native_.GetSeqId(), "[" +
////                "{\"group_id\": 1562746982270103,\"start_msgid\": 1562750305280108, \"end_msgid\": 0,\"max_num\": 100 }," +
////                "{\"group_id\": 123456,\"start_msgid\": 321, \"end_msgid\": 123,\"max_num\": 100 }]");
////        setTitle("GetGroupMsgForSingleGroup");  imsdk_native_.GetGroupMsgForSingleGroup(imsdk_native_.GetSeqId(),  group_id, msgid, "0", 100);
////        setTitle("GetGroupMsgByMsgid"); imsdk_native_.GetGroupMsgByMsgid(imsdk_native_.GetSeqId(), group_id, msgid);
////        setTitle("GetGroupMsgByKind");  imsdk_native_.GetGroupMsgByKind(imsdk_native_.GetSeqId(), group_id, msgid, "0", 100, 1001);
////        setTitle("GetGroupMsgByPage");  imsdk_native_.GetGroupMsgByPage(imsdk_native_.GetSeqId(), group_id, msgid, "0", 100, 1);
////        setTitle("GetLocalHistoryGroupMsg");    imsdk_native_.GetLocalHistoryGroupMsg(imsdk_native_.GetSeqId(), group_id, msgid, "0", 100, 1001);
////        setTitle("SaveFailedGroupMsg"); imsdk_native_.SaveFailedGroupMsg(imsdk_native_.GetSeqId(), msgid, group_id, "fail to send group msg!", 1001);
////        setTitle("DeleteSavedFailedGroupMsg");  imsdk_native_.DeleteSavedFailedGroupMsg(imsdk_native_.GetSeqId(), msgid, group_id);
////        setTitle("ClearLocalGroupMsgHistory");  imsdk_native_.ClearLocalGroupMsgHistory(imsdk_native_.GetSeqId(), group_id);
//        setTitle("InitLocalGroupMsgHistoryView");   imsdk_native_.InitLocalGroupMsgHistoryView(imsdk_native_.GetSeqId(), group_id, 10, "[1001, 1002]");
//        setTitle("GetLocalGroupMsgByPageIndexView");    imsdk_native_.GetLocalGroupMsgByPageIndexView(imsdk_native_.GetSeqId(),  group_id, 1);
//        setTitle("GetLocalGroupMsgPageIndexByMsgidView");   imsdk_native_.GetLocalGroupMsgPageIndexByMsgidView(imsdk_native_.GetSeqId(),  group_id, msgid);
//        setTitle("SearchLocalGroupMsgView");    imsdk_native_.SearchLocalGroupMsgView(imsdk_native_.GetSeqId(),  group_id, msgid, 10, "g");
    }

    //系统消息相关
    protected void systemTest() {
//        long group = 110;
//        long type = 137;
//        String msgid = "1561510432235815";
//        String taskid = "10003";
//
////        setTitle("GetSysMsg");    imsdk_native_.GetSysMsg(imsdk_native_.GetSeqId(), 0, 0, "[" +
////                "{\"msg_type\": {\"group\": 110,\"type\": 137},\"max_num\": 100,  \"start_msgid\": \"1561510432235815\",\"end_msgid\": \"0\"}," +
////                "{\"msg_type\": {\"group\": 111,\"type\": 137},\"max_num\": 100,\"start_msgid\": \"1561510432235815\",\"end_msgid\": \"0\"}]");
////        setTitle("GetSysMsgForSingleGroupType");    imsdk_native_.GetSysMsgForSingleGroupType(imsdk_native_.GetSeqId(), 0, 0
////                , "[{\"msg_type\": {\"group\": 110,\"type\": 137},\"max_num\": 100,  \"start_msgid\": \"1561510432235815\",\"end_msgid\": \"0\"}]");
////        setTitle("GetSysMsgByMsgid");    imsdk_native_.GetSysMsgByMsgid(imsdk_native_.GetSeqId(), group, type, msgid);
////        setTitle("GetLocalHistorySysMsgByGroup");    imsdk_native_.GetLocalHistorySysMsgByGroup(imsdk_native_.GetSeqId(), group, msgid, "0", 100, 0, 0);
////        setTitle("GetLocalHistorySysMsgByGroupType");    imsdk_native_.GetLocalHistorySysMsgByGroupType(imsdk_native_.GetSeqId(), group, type, msgid, "0", 100, 0, 0);
////        setTitle("GetLocalHistorySysMsgByMsgid");    imsdk_native_.GetLocalHistorySysMsgByMsgid(imsdk_native_.GetSeqId(), msgid);
////        setTitle("SetSysMsgReadStatusByGroup");    imsdk_native_.SetSysMsgReadStatusByGroup(imsdk_native_.GetSeqId(), group);
////        setTitle("SetSysMsgReadStatusByGroupType");    imsdk_native_.SetSysMsgReadStatusByGroupType(imsdk_native_.GetSeqId(), group, type);
////        setTitle("SetSysMsgReadStatusByMsgid");    imsdk_native_.SetSysMsgReadStatusByMsgid(imsdk_native_.GetSeqId(), group, type, msgid);
////        setTitle("SetSysMsgReadStatusByBatchMsgid");    imsdk_native_.SetSysMsgReadStatusByBatchMsgid(imsdk_native_.GetSeqId(), group, "[" +
////                "{\"type\": \"111\",\"msgids\": [12345, 54321, 32145]}," +
////                "{\"type\": \"112\", \"msgids\": [123456, 654321，321456]}]");
////        setTitle("SetSysMsgReadStatusByTaskid");    imsdk_native_.SetSysMsgReadStatusByTaskid(imsdk_native_.GetSeqId(), taskid);
////        setTitle("SetSysMsgUnReadStatus");    imsdk_native_.SetSysMsgUnReadStatus(imsdk_native_.GetSeqId(), group, "[1561510432235815,1561371134231848]");
//        setTitle("SendSysMsg");    imsdk_native_.SendNoticeMsg(imsdk_native_.GetSeqId(), "\"type\": 1, \"to_dev\": 0, \"users\": [{\"cid\": 123, \"uid\": 321}"
//                                                                                                    + ", {\"cid\": 12345, \"uid\": 54321}"
//                                                                                                    + "], \"offline\": 0, \"msg_type\": 0, \"ispush\": 0, \"time\": 0 }"
//                , "common message");
////        setTitle("GetSysMsgGroupList");    imsdk_native_.GetSysMsgGroupList(imsdk_native_.GetSeqId(), "[110,111]", 0);
////        setTitle("GetSysMsgTypeList");    imsdk_native_.GetSysMsgTypeList(imsdk_native_.GetSeqId(), "[137,138]", 0);
////        setTitle("GetSysMsgTypeListByGroup");    imsdk_native_.GetSysMsgTypeListByGroup(imsdk_native_.GetSeqId(), "[110,111]", 0);
////        setTitle("GetSysMsgTypeInfoListByGroup");    imsdk_native_.GetSysMsgTypeInfoListByGroup(imsdk_native_.GetSeqId(), "[110,111]", 0);
    }

    //会话相关
    protected void sessionTest() {
        String last_msgid = "1557984286260012";

////        setTitle("GetSession");    imsdk_native_.GetSession(imsdk_native_.GetSeqId(), 1);
////        setTitle("GetSessionByPage");    imsdk_native_.GetSessionByPage(imsdk_native_.GetSeqId(), last_msgid, 100);
////        setTitle("SetSigMsgReadStatus");    imsdk_native_.SetSigMsgReadStatus(imsdk_native_.GetSeqId(), get_friend_cid(), get_friend_uid(), true);
////        setTitle("SetGroupMsgReadStatus");    imsdk_native_.SetGroupMsgReadStatus(imsdk_native_.GetSeqId(), get_groupid(), false);
////        setTitle("SetSysMsgReadStatus");    imsdk_native_.SetSysMsgReadStatus(imsdk_native_.GetSeqId(), get_group(), true);
////        setTitle("SetCustomerFolderReadStatus");    imsdk_native_.SetCustomerFolderReadStatus(imsdk_native_.GetSeqId(), false);
////        setTitle("DeleteSigChatSession");    imsdk_native_.DeleteSigChatSession(imsdk_native_.GetSeqId(), get_friend_cid(), get_friend_uid());
////        setTitle("DeleteGroupChatSession");    imsdk_native_.DeleteGroupChatSession(imsdk_native_.GetSeqId(), get_groupid());
////        setTitle("DeleteSysMsgSession");    imsdk_native_.DeleteSysMsgSession(imsdk_native_.GetSeqId(), get_group());
////        setTitle("SetSigChatSessionTopStatus");    imsdk_native_.SetSigChatSessionTopStatus(imsdk_native_.GetSeqId(), get_friend_cid(), get_friend_uid(),1);
////        setTitle("SetGroupChatSessionTopStatus");    imsdk_native_.SetGroupChatSessionTopStatus(imsdk_native_.GetSeqId(), get_groupid(), 0);
////        setTitle("SetSysMsgSessionTopStatus");    imsdk_native_.SetSysMsgSessionTopStatus(imsdk_native_.GetSeqId(), get_group(), 1);
////        setTitle("GetCustomerSession");    imsdk_native_.GetCustomerSession(imsdk_native_.GetSeqId(), 0);
////        setTitle("GetOtherSession");    imsdk_native_.GetOtherSession(imsdk_native_.GetSeqId(), 0);
////        setTitle("SearchSession");    imsdk_native_.SearchSession(imsdk_native_.GetSeqId(), 31, "", "g", 10, 1);
////        setTitle("SearchSession chat");    imsdk_native_.SearchSession(imsdk_native_.GetSeqId(), 32, "zh_CN", "h", 10, 1);
//        setTitle("SearchMsg");    imsdk_native_.SearchMsg(imsdk_native_.GetSeqId(), "h", "\"id\":{ \"type\": 2, \"group_id\": \" 1561362279270770\"}, \"i18n\":\"zh_CN\", \"page_size\":10, \"page_num\":1 }");
    }

    //用户信息相关
    protected void userTest() {
////       setTitle("GetUserInfo");      imsdk_native_.GetUserInfo(imsdk_native_.GetSeqId(), "[" +
////                "{\"cid\": 1103234746952974336,\"uid\": 5815186667656411846}, " +
////                "{\"cid\": 1103234746952974336, \"uid\": 5815186667656411846}]", 0);
////       setTitle("GetCustomInfo");      imsdk_native_.GetCustomInfo(imsdk_native_.GetSeqId(), "[" +
////                "{\"cid\": 1103234746952974336,\"uid\": 5815186667656411846}, " +
////                "{\"cid\": 1103234746952974336, \"uid\": 5815186667656411846}]");
////       setTitle("GetCorpInfo");      imsdk_native_.GetCorpInfo(imsdk_native_.GetSeqId(), "[\"1103234746952974336\"]", 0);
//       setTitle("GetAnonymous");      imsdk_native_.GetAnonymous(imsdk_native_.GetSeqId(), "[\"1562746982270103\"]");
    }

    //外部信息相关
    protected void externalTest() {
////        setTitle("UserApplyFriend");      imsdk_native_.UserApplyFriend(imsdk_native_.GetSeqId(), get_externfriend_cid(), get_externfriend_uid(), "apply friend!");
////        setTitle("UserReplyFriend");      imsdk_native_.UserReplyFriend(imsdk_native_.GetSeqId(), get_externfriend_cid(), get_externfriend_uid(), "1562750305280108", 0, "agree");
////        setTitle("GetUserFriend");      imsdk_native_.GetUserFriend(imsdk_native_.GetSeqId());
////        setTitle("SearchUserInfo");      imsdk_native_.SearchUserInfo(imsdk_native_.GetSeqId(), 0, "15821725796", "3333@qq.com", "zhang3");
////        setTitle("UserRemoveFriend");      imsdk_native_.UserRemoveFriend(imsdk_native_.GetSeqId(), get_externfriend_cid(), get_externfriend_uid());
//        setTitle("GetFriendRelation");      imsdk_native_.GetFriendRelation(imsdk_native_.GetSeqId(), get_externfriend_cid(), get_externfriend_uid(), "[" +
//                "{\"cid\": 1103234746952974336,\"uid\": 5815186667656411846}, " +
//                "{\"cid\": 1103234746952974336, \"uid\": 5815186667656411846}]");
    }

    //消息免打扰相关
    protected void remindTest() {
////        setTitle("SetSingleMsgRemind");      imsdk_native_.SetSingleMsgRemind(imsdk_native_.GetSeqId(), get_friend_cid(), get_friend_uid(), 0);
////        setTitle("SetGroupMsgRemind");      imsdk_native_.SetGroupMsgRemind(imsdk_native_.GetSeqId(), get_groupid(), 0);
////        setTitle("SetSysMsgRemind");      imsdk_native_.SetSysMsgRemind(imsdk_native_.GetSeqId(), get_group(), get_type(), 0);
////        setTitle("SetSigTaskSysMsgRemindCfg");      imsdk_native_.SetSigTaskSysMsgRemindCfg(imsdk_native_.GetSeqId(), get_group(), get_type(), get_entityid(), 1);
//        setTitle("GetSigTaskSysMsgRemindCfg");      imsdk_native_.GetSigTaskSysMsgRemindCfg(imsdk_native_.GetSeqId(), get_group(), get_type(), get_entityid());
////        setTitle("SetMsgRemindTime");      imsdk_native_.SetMsgRemindTime(imsdk_native_.GetSeqId(), 0, 602, 1, "");
////        setTitle("GetMsgRemind");      imsdk_native_.GetMsgRemind(imsdk_native_.GetSeqId(), 0);
    }

    //话题相关
    protected void topicTest() {
//        setTitle("SendTopicMsg");       imsdk_native_.SendTopicMsg(imsdk_native_.GetSeqId(), "send topic msg", "send_topic_title", "{\"flag\": 1, \"group_id\": 1557196924270299}", "0");
//        setTitle("ReplyTopicMsg");      imsdk_native_.ReplyTopicMsg(imsdk_native_.GetSeqId(), "0", "reply topic msg", "reply_topic_title", "1568173986543000000");
//        setTitle("GetTopicInfoByMsgid");imsdk_native_.GetTopicInfoByMsgid(imsdk_native_.GetSeqId(), "[1568173799710020]");
//        setTitle("GetTopicInfoByTid");  imsdk_native_.GetTopicInfoByTid(imsdk_native_.GetSeqId(), "[1568173799710019]");
//        setTitle("GetRelateTopic");     imsdk_native_.GetRelateTopic(imsdk_native_.GetSeqId(), "0", "{\"flag\": 1, \"group_id\": 1557196924270299}");
//        setTitle("SyncTopic");          imsdk_native_.SyncTopic(imsdk_native_.GetSeqId(), "0", 10, 0);
//        setTitle("FocusTopic");         imsdk_native_.FocusTopic(imsdk_native_.GetSeqId(), "[1568171688710014,1568171585710012,1568171573710011,1568171173710010,1568171173710009]", 0);
//        setTitle("FocusTopic");         imsdk_native_.FocusTopic(imsdk_native_.GetSeqId(), "[1568171688710014,1568171585710012,1568171573710011,1568171173710010,1568171173710009]", 1);
//        setTitle("SetTopicCount");      imsdk_native_.SetTopicCount(imsdk_native_.GetSeqId(), "[1568173799710019, 1568173799710020]");
////        setTitle("DelTopic");           imsdk_native_.DelTopic(imsdk_native_.GetSeqId(), "[1568173799710019, 1568173799710020]");
    }

    //声音提醒
    protected void usersoundTest() {
//        setTitle("SyncSoundList");      imsdk_native_.SyncSoundList(imsdk_native_.GetSeqId(), "", 0);
//        setTitle("AddSoundUsers");      imsdk_native_.AddSoundUsers(imsdk_native_.GetSeqId(), "[{ \"cid\": 1103482089937829888,\"uid\": 1565192560718062634}]");
//        setTitle("SyncSoundUsers");     imsdk_native_.SyncSoundUsers(imsdk_native_.GetSeqId(), 0);
//        setTitle("SetSound");           imsdk_native_.SetSound(imsdk_native_.GetSeqId(), "10000001");
//        setTitle("DelSoundUsers");      imsdk_native_.DelSoundUsers(imsdk_native_.GetSeqId(), "[{ \"cid\": 1103482089937829888,\"uid\": 1565192560718062634}]");
    }

    //其他
    protected void otherTest() {
//        setTitle("GetSdkInfo");      imsdk_native_.GetSdkInfo(imsdk_native_.GetSeqId());
//        setTitle("UploadUserOper");  imsdk_native_.UploadUserOper(imsdk_native_.GetSeqId(), 1, 12);
////        setTitle("ApplyRely");    imsdk_native_.ApplyRely(imsdk_native_.GetSeqId(), "1564382408270048", "1", "I agree");//因未实现http接口，该测试无效
//        setTitle("TransportTech");   imsdk_native_.TransportTech(imsdk_native_.GetSeqId(), "{\"id\": { \"type\": 2, \"group_ids\": [1567042794270095,1567043036270097,1567048225270108]}, \"content\": \"the content of groups\", \"flag\": 1, \"push\": 0}");
////        setTitle("ClearCache");      imsdk_native_.ClearCache(imsdk_native_.GetSeqId());

        setTitle("GetCliMsgId");     activity_.addText("GetCliMsgId:" + imsdk_native_.GetCliMsgId());
        setTitle("GetSeqId");        activity_.addText("GetSeqId:" + imsdk_native_.GetSeqId());
        setTitle("GetMaxMsgId");     activity_.addText("GetMaxMsgId:" + imsdk_native_.GetMaxMsgId());
        setTitle("GetMinMsgId");     activity_.addText("GetMinMsgId:" + imsdk_native_.GetMinMsgId());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    protected String get_own_cid() { return  "1103234746952974336"; }
    protected String get_own_uid() { return  "5815186667656411846"; }
    protected String get_friend_cid() { return  "1103234746952974336"; }
    protected String get_friend_uid() { return  "5815186667396611840"; }
    protected String get_externfriend_cid() { return  "1128474726004424704"; }
    protected String get_externfriend_uid() { return  "5815788443829623523"; }
    protected String get_groupid() { return  "1562746982270103"; }
    protected long get_group() { return  110; }
    protected long get_type() { return  137; }
    protected String get_entityid() { return  "5815971594928052450"; }


    protected void setTitleCollect(String title){
        title_index_ = 0;
        activity_.addText("========= " + (++collect_index_) + "." + title + " =========");
    }

    protected void setTitle(String title){
        activity_.addText("--------- (" + (++title_index_) + ")." + title + " ---------");
    }

    protected boolean callImSdk(int opcode, String req) {
        String key = "{ \"reqid\": \"" + imsdk_native_.GetSeqId() + "\", \"opcode\": \"" + opcode + "\"}";
        if (!imsdk_native_.AsyncCall(key, req)) {
            activity_.addText("error: invalid key:'" + key + "', or req:'" + req + "'!");
            return false;
        }
        return  true;
    }
}

