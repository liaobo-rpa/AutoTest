package HttpRequestor;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.PropertyConfigurator;

public class PostHttpRequestor {
    static{
        PropertyConfigurator.configure("log4j.properties");
    }
    private static HttpClient httpClient = new DefaultHttpClient();
    private static HttpPost httppost;
    private static HttpResponse response;
    private HttpEntity entity;

    public static String posthttprequest(String url, String body){
        String postResult = "";
        httppost = new HttpPost(url);
        try{
            StringEntity body1 = new StringEntity(body);
            httppost.setEntity(body1);
            response = httpClient.execute(httppost);
            postResult = EntityUtils.toString(response.getEntity());

        }catch (Exception e){}


        return postResult;
    }

}
