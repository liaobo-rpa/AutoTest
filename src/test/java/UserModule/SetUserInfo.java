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
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Tools.CommonUtil;

import java.io.IOException;
import java.nio.charset.Charset;

import static Tools.GreatToken.gettonken;

public class SetUserInfo {
    static{
        PropertyConfigurator.configure("log4j.properties");
    }
    private static HttpClient httpClient = new DefaultHttpClient();
    private String postResult = null;
    private static final String ModuleName = CommonUtil.getProperity(CommonUtil.config_file, "ModuleName");
    private static final String MethodName = CommonUtil.getProperity(CommonUtil.config_file, "MethodName");
    private static final String url = CommonUtil.getProperity(CommonUtil.config_file, "url");
    private static final String Mname = "TUserDM";
    private static final String Fname = "SetUserInfo";
    private static final String ResponseResult = CommonUtil.getProperity(CommonUtil.config_file, "ResponseResult");
    private String token = gettonken("admin","1","TBaseDM","Test1");

    @BeforeTest
    public void setup(){
        httpClient = HttpClients.createDefault();
    }

    @Test
    public void userinfo() throws ClientProtocolException, IOException{

    }



}
