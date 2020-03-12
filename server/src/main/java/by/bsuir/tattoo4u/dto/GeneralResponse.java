package by.bsuir.tattoo4u.dto;

import java.util.HashMap;
import java.util.Map;

public class GeneralResponse <O1, O2> {

    private Map<O1, O2> responseMap;

    public GeneralResponse() {
        this.responseMap = new HashMap<>();
    }

    public void add(O1 key, O2 value){
        responseMap.put(key, value);
    }
}
