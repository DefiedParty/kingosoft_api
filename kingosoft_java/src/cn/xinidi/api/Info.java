package cn.xinidi.api;

import cn.xinidi.model.UserJSON;
import com.sun.istack.internal.NotNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * 所有用到的信息
 *
 * @author Gang
 */
public class Info {

    /**
     * 该内部类应该实时同步app
     */
    public static class AppInfo {
        // *重要！当前 喜鹊儿App 的版本号
        public static String appver = "android2.5.101";
        // *重要！加密参数用到的Key值
        public static String encodeKey = "op5nb9";
    }

    /**
     * 请求需要用到的所有URl以及参数
     * 至于参数列表的组合顺序，emm..我好想是发过了修改后的App了
     */
    public static class URLS {
        //请求接口（旧版）
        // public static String URL = "http://www.xiqueer.com:8080/manager/wap/wapController.jsp";
        // public static String URL_WEBVIEW = "http://www.xiqueer.com:8080/manager/wap/jwApi.jsp";

        public static String URL = "http://api.xiqueer.com/manager/wap/wapController.jsp";
        public static String URL_WEBVIEW = "http://api.xiqueer.com/manager/wap/jwApi.jsp";

        /**
         * 学校列表参数
         *
         * @return 组合后的参数
         */
        public static String getSchoolListParams() {

            //参数列表顺序需要强行如下，不能更改
            StringBuffer buffer = new StringBuffer();
            buffer.append("appver=").append(AppInfo.appver).append("&");
            buffer.append("action=").append("getAgent").append("&"); //默认值
            buffer.append("xxmc=").append("");

            return buffer.toString();
        }

        /**
         * 登录参数
         *
         * @param xxdm     学校代码
         * @param sjxh     手机型号，默认：MI 8
         * @param os       手机系统，默认：android
         * @param xtbb     android版本,默认：10
         * @param loginId  账号
         * @param password 登录密码
         * @return 组合后的参数
         */
        public static String getLoginParams(String xxdm, String sjxh, String os, String xtbb, String loginId, String password) {
            sjxh = sjxh == null || (sjxh.trim().equals("")) ? "MI 8" : sjxh;
            os = os == null || (os.trim().equals("")) ? "android" : os;
            xtbb = xtbb == null || (xtbb.trim().equals("")) ? "10" : xtbb;

            if (xxdm == null || xxdm.trim().equals("")) return null;
            if (loginId == null || loginId.trim().equals("")) return null;
            if (password == null || password.trim().equals("")) return null;


            //参数列表顺序需要强行如下，不能更改
            StringBuffer buffer = new StringBuffer();
            buffer.append("xxdm=").append(xxdm).append("&");
            buffer.append("sjxh=").append(sjxh).append("&");
            buffer.append("loginId=").append(loginId).append("&");
            buffer.append("sswl=").append("55555").append("&"); //默认值
            buffer.append("os=").append(os).append("&");
            buffer.append("xtbb=").append(xtbb).append("&");
            buffer.append("appver=").append(AppInfo.appver).append("&"); //软件版本号
            buffer.append("action=").append("getLoginInfoNew").append("&");
            buffer.append("isky=").append("1").append("&");//默认值
            buffer.append("sjbz=").append("").append("&"); //默认为空
            buffer.append("pwd=").append(password).append("&");
            buffer.append("loginmode=").append("0"); //默认值

            return buffer.toString();
        }

        /**
         * 取今日课表
         *
         * @param jsdm     未知，根据词义应该是 教室代码
         * @param week     欲获取第n周的课表
         * @param xnxq     学年学期,由 [年份+学期] 构成,例1:20200 --第一学期，列2:20201 --第二学期
         * @param bjdm     未知，根据词义应该是 班级代码
         * @param userJSON 该对象由登录后封装而来，详情请看{@linkplain cn.xinidi.Apis# 登录接口},对象结构请看{@link UserJSON}
         * @return 组合后的参数
         */
        public static String getCourseParams(String jsdm, String bjdm, String week, String xnxq, UserJSON userJSON) {

            jsdm = (jsdm == null || jsdm.trim().equals("")) ? "" : jsdm;
            bjdm = (bjdm == null || bjdm.trim().equals("")) ? "" : bjdm;

            if (week == null || "".equals(week.trim())) return null;
            if (xnxq == null || "".equals(xnxq.trim())) return null;
            if (userJSON == null) return null;
            //if (userJSON == null) return null;

            //参数列表顺序需要强行如下，不能更改
            StringBuffer buffer = new StringBuffer();
            buffer.append("jsdm=").append(jsdm).append("&");
            buffer.append("week=").append(week).append("&");
            buffer.append("xnxq=").append(xnxq).append("&");
            buffer.append("channel=").append("jrkb").append("&"); //默认值 代表今日课表
            buffer.append("usertype=").append(userJSON.getUserType()).append("&");
            buffer.append("action=").append("getKb").append("&"); //默认值
            buffer.append("step=").append("kbdetail_bz").append("&"); //默认值
            buffer.append("userId=").append(userJSON.getUserId()).append("&"); //由 学校代码_账号 构成
            buffer.append("bjdm=").append(bjdm).append("&");
            buffer.append("sfid=").append(userJSON.getUserId()).append("&"); //由 学校代码_账号 构成
            buffer.append("uuid=").append(userJSON.getUuid());

            return buffer.toString();
        }

        /**
         * 取成绩
         *
         * @param flag     flag可取[0,1]两个值，分别代表[原始成绩,有效成绩]
         * @param xnxq     学年学期,由 [年份+学期] 构成,例1:20200 --第一学期，列2:20201 --第二学期
         * @param userJSON 该对象由登录后封装而来，详情请看{@linkplain cn.xinidi.Apis#login 登录接口},对象结构请看{@link UserJSON}
         * @return 组合后的参数
         */
        public static String getGradeParams(String flag, String xnxq, UserJSON userJSON) {

            flag = (flag == null || flag.trim().equals("")) ? "0" : flag;
            if (xnxq == null || "".equals(xnxq.trim())) return null;
            if (userJSON == null) return null;

            //强制排列
            StringBuffer buffer = new StringBuffer();
            buffer.append("flag=").append(flag).append("&");
            buffer.append("xnxq=").append(xnxq).append("&");
            buffer.append("action=getStucj&");
            buffer.append("usertype=").append(userJSON.getUserType()).append("&");
            buffer.append("step=detail&");
            buffer.append("userId=").append(userJSON.getUserId()).append("&");
            buffer.append("sfid=").append(userJSON.getUserId()).append("&");
            buffer.append("uuid=").append(userJSON.getUuid());

            return buffer.toString();
        }

        /**
         * 该方法用作于喜鹊儿内嵌webview访问的加密参数
         *
         * @param userJSON 该对象由登录后封装而来，详情请看{@linkplain cn.xinidi.Apis#login 登录接口},对象结构请看{@link UserJSON}
         * @param type     请求方式，抓包后会有对应代码
         * @param step     未知，抓包后会有对应代码
         * @return 组合后的参数
         */
        public static String getWebCommParams(@NotNull UserJSON userJSON, String type, String step) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("user=").append(userJSON.getUserId()).append("&");
            buffer.append("usertype=").append(userJSON.getUserType()).append("&");
            buffer.append("uuid=").append(userJSON.getUuid());

            //第一次加密
            String encode1 = EncodeAPI.webEncodeing(buffer.toString(), userJSON.getXxdm());

            //在第一次加密的基础上添加学校代码
            encode1 = encode1 + "&xxdm=" + userJSON.getXxdm();

            //第二次加密
            String encode2 = EncodeAPI.webEncodeing(encode1, Info.AppInfo.encodeKey);

            //参数组合
            StringBuffer buffer1 = new StringBuffer();
            try {
                buffer1.append(URLEncoder.encode("head[us]", "utf-8"))
                        .append("=&");
                buffer1.append(URLEncoder.encode("head[version]", "utf-8"))
                        .append('=').append("1.0.0")
                        .append("&");
                buffer1.append(URLEncoder.encode("head[ct]", "utf-8"))
                        .append('=').append("3")
                        .append("&");

                //注意
                buffer1.append(URLEncoder.encode("head[time]", "utf-8"))
                        .append('=')
                        .append(String.valueOf(new Date().getTime()).substring(0, 10))
                        .append("&");

                buffer1.append(URLEncoder.encode("head[sign]", "utf-8"))
                        .append('=')
                        .append("&");

                //注意
                buffer1.append("sign=")
                        .append(URLEncoder.encode(encode2, "utf-8"))
                        .append(URLEncoder.encode("&token=", "utf-8"))
                        .append(userJSON.getToken())
                        .append(URLEncoder.encode("&appinfo=", "utf-8"))
                        .append(AppInfo.appver)
                        .append("&");

                buffer1.append("action=")
                        .append("jw_apply")
                        .append("&");
                buffer1.append("type=")
                        .append(type)
                        .append("&");
                buffer1.append("step=")
                        .append(step);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return buffer1.toString();
        }
    }

}
