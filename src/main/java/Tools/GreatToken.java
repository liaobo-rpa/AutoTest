package Tools;

import HttpRequestor.PostHttpRequestor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.PropertyConfigurator;

import Tools.CommonUtil;
import Tools.MD5Util;
import Tools.Addjosn;

public class GreatToken {
    static{
        PropertyConfigurator.configure("log4j.properties");
    }
    private static HttpClient httpClient = new DefaultHttpClient();
    private static HttpPost httppost;
    private static HttpResponse response;
    private HttpEntity entity;
    private String postResult = null;
    private static final String host = CommonUtil.getProperity(CommonUtil.config_file, "proxy_host");
    private static final int port = Integer.parseInt(CommonUtil.getProperity(CommonUtil.config_file, "proxy_port"));
    private static final String ModuleName = CommonUtil.getProperity(CommonUtil.config_file, "ModuleName");
    private static final String MethodName = CommonUtil.getProperity(CommonUtil.config_file, "MethodName");


    public static String gettonken( String user,String pass,String Mname,String Fname) {

        String token = "";
        JSONArray formparams = new JSONArray();
        formparams.add(Addjosn.addjson1(ModuleName,4,Mname));
        formparams.add(Addjosn.addjson1("User",4,user));
        formparams.add(Addjosn.addjson1("pass",4,MD5Util.MD5(pass)));
        formparams.add(Addjosn.addjson1(MethodName,4,Fname));
        String abc = PostHttpRequestor.posthttprequest("http://192.168.10.113:886/CallFunc.aom",formparams.toString());
        System.out.println("请求返回的结果是： "+abc);
        JSONArray json = JSONArray.fromObject(abc);
        for(int i =0 ; i < json.size() ; i++){
            JSONObject job = json.getJSONObject(i);
            if(job.get("Name").equals("Token")){
                token = job.get("Value").toString();
                System.out.println(token);
            }
        }
        return token;
    }


}
