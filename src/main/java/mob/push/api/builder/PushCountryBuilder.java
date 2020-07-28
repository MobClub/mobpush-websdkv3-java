package mob.push.api.builder;

import mob.push.api.model.PushAreas;
import mob.push.api.utils.ListUtil;

public class PushCountryBuilder {

    private final PushAreas.PushCountry pushCountry=new PushAreas.PushCountry();

    public PushAreas.PushCountry buid(){
        return pushCountry;
    }

    public PushCountryBuilder buildPushProvince(PushAreas.PushProvince ... pushProvince){
        pushCountry.setProvinces(ListUtil.newList(pushProvince));
        return this;
    }

    public PushCountryBuilder buildPushCountry(String country){
        pushCountry.setCountry(country);
        return this;
    }

    public PushCountryBuilder builExcludeProvinces(String ... province){
        pushCountry.setExcludeProvinces(ListUtil.newList(province));
        return this;
    }

}
