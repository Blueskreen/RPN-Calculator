package edu.usm.cos470.rpncalc;
import edu.usm.cos470.rpncalc.exceptions.VariableNotFound;

import java.util.HashMap;
import java.util.Map;

public class Variables {
    Map<String, Long> variableMap;

    public Variables(){
        variableMap = new HashMap<String, Long>();
    }

    public long get(String key) throws VariableNotFound {
        if(variableMap.containsKey(key)){
            return variableMap.get(key);
        } else {
            throw new VariableNotFound("nothing assigned to this variable");
        }
    }

    public void assign(String key, long value){
        variableMap.put(key, value);
    }

    public boolean checkAssignment(String key){
        return variableMap.containsKey(key);
    }
}
