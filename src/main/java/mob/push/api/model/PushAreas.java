package mob.push.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder()
@NoArgsConstructor
@AllArgsConstructor
public class PushAreas {

    /**
     * 需要推送的国家列表
     * NotEmpty(message = "地理位置[pushAreas]中国家列表[countries]不能为空")
     */
    private List<PushCountry> countries = new ArrayList<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PushCountry {
        /**
         * NotEmpty(message = "国家[country]不能为空")
         */
        private String country;

        /**
         * 如果 provinces 不为空，则只推送指定省份
         * 如果 provinces 为空，则推送全国
         */
        private List<PushProvince> provinces;
        /**
         * 如果 provinces 为空，则排除 excludeProvinces 相关省份的推送
         */
        private List<String> excludeProvinces;
    }

    @Data
    @Builder()
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PushProvince {

        /**
         * NotEmpty(message = "省份[province]不能为空")
         */
        private String province;

        /**
         * 如果 cities 不为空，则只推送指定城市
         * 如果 cities 为空，则推送全省
         */
        private List<String> cities;
        /**
         * 如果 cities 为空，则排除 excludeCities 相关城市的推送
         */
        private List<String> excludeCities;
    }
}
