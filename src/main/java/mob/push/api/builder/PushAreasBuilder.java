package mob.push.api.builder;

import mob.push.api.model.PushAreas;
import mob.push.api.utils.ListUtil;


public class PushAreasBuilder {

    private final PushAreas pushAreas=new PushAreas();

    public PushAreas build(){
        return pushAreas;
    }

    public PushAreasBuilder buildCountries(PushAreas.PushCountry ... countries){
       pushAreas.setCountries(ListUtil.newList(countries));
       return this;
    }

}
