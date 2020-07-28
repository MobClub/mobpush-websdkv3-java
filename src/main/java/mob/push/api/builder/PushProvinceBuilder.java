package mob.push.api.builder;

import mob.push.api.model.PushAreas;
import mob.push.api.utils.ListUtil;

public class PushProvinceBuilder {

    private PushAreas.PushProvince pushProvince=new PushAreas.PushProvince();

    public PushAreas.PushProvince build(){
        return pushProvince;
    }

    public PushProvinceBuilder buildProvince(String province){
        pushProvince.setProvince(province);
        return this;
    }

    public PushProvinceBuilder buildCities(String ... citys){
        pushProvince.setCities(ListUtil.newList(citys));
        return this;
    }

    public PushProvinceBuilder buildExcludeCities(String excludeCities){
        pushProvince.setExcludeCities(ListUtil.newList(excludeCities));
        return this;
    }


}
