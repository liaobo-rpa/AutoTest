package BodySplicing;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class bodesplicing {
    public String Name;
    public Integer type;
    public String Value;

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }

    public String getValue() {
        return Value;
    }
    public static Object jsonsplicing(String Name, Integer type, String Value){
        JSONObject jobj = new JSONObject();
        jobj.put("Name",Name);
        jobj.put("Type",type);
        jobj.put("Value",Value);
        System.out.println(jobj);
        return jobj;
    }

    public String Typemode(String key){
        Map<String,String> map = new HashMap<String,String>();
        map.put("Integer","0");
        map.put("Boolean","1");
        map.put("Float","3");
        map.put("String","4");
        map.put("","5");
        map.put("","8");
        map.put("","16");
        map.put("","19");
        String type1 = map.get(key).toString();

        return type1;
    }

}
