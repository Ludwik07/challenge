package commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StorageFunctions {
    private HashMap storage = new HashMap<String, String>();
    private final String leftBoundary = "${{";
    private final String rightBoundary = "}}";

    public void add(String variable, String value) throws Exception {
        storage.put(variable, value);
    }

    public String get(String variable) throws Exception {
        return (String) storage.get(variable);
    }

    /**
     * @param variable string target where an existing key in storage is going to be replaced with.
     * @return string with replaced stored value or original value if no stored value is found.
     * @throws Exception
     */
    public String replaceVar(String variable) throws Exception {
        String tempLeft= null;
        String tempRight= null;
        String tempVar= null;
        if (variable.contains(leftBoundary)){
            tempLeft=variable.substring(0,variable.indexOf(leftBoundary));
            tempRight=variable.substring(variable.indexOf(leftBoundary), variable.length());
            tempVar= tempRight.substring(tempRight.indexOf(leftBoundary)+leftBoundary.length(), tempRight.indexOf(rightBoundary));
            tempRight=tempRight.substring(tempRight.indexOf(rightBoundary)+rightBoundary.length(), tempRight.length());
            if (get(tempVar)==null){
                throw new Exception("Storage reference '" + tempVar + "' not found.");
            }
            return tempLeft + get(tempVar) + tempRight;
        }
        return variable;
    }
}
