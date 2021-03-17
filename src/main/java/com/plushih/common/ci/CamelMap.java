package com.plushih.common.ci;

import com.plushih.common.utils.StringUtils;
import org.apache.commons.collections.map.ListOrderedMap;

import java.util.HashMap;
import java.util.Map;

/**
 * hsk3807
 * 값을담을때 map에 담기지만
 * colmnKey 가 보통  aa_aaa_aa 이런식이면 aaAaaAa으로 리턴하기위함
 * 그리고 xss체크등 값을 변조할때 유용함
 * 그리고 주소,파일등 리스트에 나열하기힘든것을 entity로 묵기위함
 * convertReal을 이용해주시길 쿨럭
 */
public class CamelMap extends ListOrderedMap {


    public Map<String, Object> getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(Map<String, Object> addressInfo) {
        this.addressInfo = addressInfo;
    }

    public Map<String,Object> addressInfo;

    /**
     * 값을 담을때 ... 치환하는 함수 String만
     * 보안점검에 나올만한 항목
     * @param key
     * @param setValue
     * @return
     */
    private Object setXss(Object key,Object setValue){
        try {
            if(setValue instanceof String)
             {
                 String tempValue = ((String)setValue);
                 tempValue = tempValue.replaceAll("'","&#39;");
                 tempValue = tempValue.replaceAll("<","&lt;");
                 tempValue = tempValue.replaceAll(">","&gt;");
                 setValue  = tempValue;
             }

        } catch (Exception e){
            e.printStackTrace();
        }
        return setValue;
    }
    @Override
    public Object put(Object key, Object value) {
        if(key.toString().substring(0,2).equals("ai")){
             if(addressInfo==null){
                 addressInfo= new HashMap<String,Object>();
             }
            addressInfo.put(StringUtils.toCamel((String)key),value.toString()==null?"":value);
            return super.put("addressInfo", addressInfo);
            //return null;
        } else {

            //return super.put(StringUtils.toCamel((String)key), setXss(key,value.toString()==null?"":value));
            return super.put(StringUtils.toCamel((String)key), value);
        }

    }

}

