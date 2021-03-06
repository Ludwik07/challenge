package jsonFunctions;

import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class JsonFunctions {

    JSONObject jObj=null;
    JSONArray jArr=null;
    JSONParser parser = new org.json.simple.parser.JSONParser();

    public void setObject(String jsonString) throws Exception {
        jObj= (JSONObject) parser.parse(jsonString);
    }

    public void setArray(String jsonString) throws Exception {
        jArr= (JSONArray) parser.parse(jsonString);
    }

    public void readFromFile(String path) throws Exception {
        Object obj= readJson(path);
        if (obj instanceof JSONObject){
            this.jObj = (JSONObject) readJson(path);
        }
        if (obj instanceof JSONArray){
            this.jArr = (JSONArray) readJson(path);
        }
    }

    /**
     * Reads json file, parses it and a return json object
     *
     * @param filename json path location
     * @return json object
     * @throws Exception
     */
    private Object readJson(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }

    /**
     * @return value of a given key
     */
    public HashMap<String, String> getPairedValues(int index, String keyLocator, String keyValue) {
        return (getPairedValues(getArray(index), keyLocator, keyValue));
    }

    /**
     * @return value of a given key
     */
    public HashMap<String, String> getPairedValues(JSONArray array, String keyLocator, String keyValue) {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < array.size(); i++) {
            map.put(getKeyValue((JSONObject) array.get(i), keyLocator), getKeyValue((JSONObject) array.get(i), keyValue));
        }
        return map;
    }

    public String getKeyValue(JSONObject obj, String key) {
        return obj.get(key).toString();
    }

    public JSONArray getArray(int index) {
        return (JSONArray) this.jObj.get(index);
    }

    public JSONArray getArray(JSONObject obj, int index) {
        return (JSONArray) obj.get(index);
    }

    public Object[][] getTableFromJArray() throws Exception {
        {
            JSONObject temp;
            Object[][] map = null;
            int rows=jArr.size();
            int cols;
            int y;
            if (rows>0) {
                temp =(JSONObject) jArr.get(0);
                cols=temp.size();
                map = new Object[rows][cols];
                for (int x=0; x<rows; x++){
                    temp =(JSONObject) jArr.get(x);
                    y=0;
                    for(Iterator iterator = temp.keySet().iterator(); iterator.hasNext();) {
                        String key = (String) iterator.next();
                        map[x][y]=temp.get(key);
                        y++;
                    }
                }
            }
            return map;
        }
    }

    public String getPathKey(String jsonString, String jsonExp) {
        return JsonPath.read(jsonString, jsonExp);
    }
}
