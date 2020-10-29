package UserModule;

import HttpRequestor.PostHttpRequestor;
import Tools.Addjosn;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.PropertyConfigurator;
import org.junit.runners.Suite;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Tools.CommonUtil;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import java.io.IOException;
import java.nio.charset.Charset;

import static Tools.GreatToken.gettonken;

public class GetUserList {
    static{
        PropertyConfigurator.configure("log4j.properties");
    }
    private static HttpClient httpClient = new DefaultHttpClient();
    private String postResult = null;
    private static final String ModuleName = CommonUtil.getProperity(CommonUtil.config_file, "ModuleName");
    private static final String MethodName = CommonUtil.getProperity(CommonUtil.config_file, "MethodName");
    private static final String url = CommonUtil.getProperity(CommonUtil.config_file, "url");
    private static final String Mname = "TUserDM";
    private static final String Fname = "GetUserList";
    private static final String ResponseResult = CommonUtil.getProperity(CommonUtil.config_file, "ResponseResult");
    private String token = gettonken("admin","1","TBaseDM","Test1");


//    public void  unicodeToGBK (String s){
//        byte[] GBKBytes = s.getBytes("GBK");
//        return new String( s.getBytes("GBK") , "GBK");
//    }


    @BeforeTest
    public void setup(){
        httpClient = HttpClients.createDefault();
    }


    @Test
    public void userlist()throws ClientProtocolException, IOException {
//        String token = gettonken("admin","1","TBaseDM","Test1");
        JSONArray formparams = new JSONArray();
        formparams.add(Addjosn.addjson1(ModuleName,4,Mname));
        formparams.add(Addjosn.addjson1(MethodName,4,Fname));
        formparams.add(Addjosn.addjson1("Token",4,token));
        String response = PostHttpRequestor.posthttprequest(url,formparams.toString());
        response = response.replaceAll("\r|\n","");//去除换行符号
        String response1 = new String(response.getBytes("ISO8859-1"),"utf-8");
        System.out.println(response1);
        JSONArray json = JSONArray.fromObject(response1);
        System.out.println(json);
        for(int i = 0; i < json.size(); i++){
            JSONObject job = json.getJSONObject(i);
            if(job.get("Name").equals(ResponseResult)) {
                Assert.assertEquals(job.get("Value"),"","返回正确");
            }
        }
    }

    //错误json格式请求
    @Test
    public void userlist1()throws ClientProtocolException, IOException{
//        String token = gettonken("admin","1","TBaseDM","Test1") + "1111";
        JSONArray formparams = new JSONArray();
        formparams.add(Addjosn.addjson1(ModuleName,4,Mname));
        formparams.add(Addjosn.addjson1(MethodName,4,Fname));
        formparams.add(Addjosn.addjson1("Token",4,token+"1111"));
        String response = PostHttpRequestor.posthttprequest(url,formparams.toString());
//        System.out.println(response);
        response = response.replaceAll("\r|\n","");//去除换行符号
        String response1 = new String(response.getBytes("ISO8859-1"),"utf-8");
        System.out.println(response1);
        JSONArray json = JSONArray.fromObject(response1);
        for(int i = 0; i < json.size(); i++){
            JSONObject job = json.getJSONObject(i);
            if(job.get("Name").equals(ResponseResult)) {
                Assert.assertTrue(job.getString("Value").indexOf("无效令牌")==0,job.getString("Value"));
                System.out.println(job.getString("Value"));
            }
        }
    }
    @Test
    public void userlist2()throws ClientProtocolException, IOException{
        //模块名错误
        JSONArray formparams = new JSONArray();
        formparams.add(Addjosn.addjson1(ModuleName,4,Mname+"1"));
        formparams.add(Addjosn.addjson1(MethodName,4,Fname));
        formparams.add(Addjosn.addjson1("Token",4,token));
        String response = PostHttpRequestor.posthttprequest(url,formparams.toString());
//        System.out.println(response);
        response = response.replaceAll("\r|\n","");//去除换行符号
        String response1 = new String(response.getBytes("ISO8859-1"),"utf-8");
        System.out.println(response1);

        JSONArray json = JSONArray.fromObject(response1);
        for(int i = 0; i < json.size(); i++){
            JSONObject job = json.getJSONObject(i);
            if(job.get("Name").equals(ResponseResult)) {
                Assert.assertTrue(job.getString("Value").indexOf("没有查找到")==0,job.getString("Value"));
                System.out.println(job.getString("Value"));
            }
        }

    }

    @Test
    public void userlist3()throws ClientProtocolException, IOException{
        //方法名错误
        JSONArray formparams = new JSONArray();
        formparams.add(Addjosn.addjson1(ModuleName,4,Mname));
        formparams.add(Addjosn.addjson1(MethodName,4,Fname+"1"));
        formparams.add(Addjosn.addjson1("Token",4,token));
        String response = PostHttpRequestor.posthttprequest(url,formparams.toString());
//        System.out.println(response);
        response = response.replaceAll("\r|\n","");//去除换行符号
        String response1 = new String(response.getBytes("ISO8859-1"),"utf-8");
        System.out.println(response1);

        JSONArray json = JSONArray.fromObject(response1);
        for(int i = 0; i < json.size(); i++){
            JSONObject job = json.getJSONObject(i);
            if(job.get("Name").equals(ResponseResult)) {
                Assert.assertTrue(job.getString("Value").indexOf("方法")==0,job.getString("Value"));
                System.out.println(job.getString("Value"));
            }
        }

    }

}
