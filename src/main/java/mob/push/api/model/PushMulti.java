package mob.push.api.model;

import lombok.Data;

import java.util.List;

@Data
public class PushMulti {

    /**
     * NotEmpty(message = "[items]不能为空")
     */
    private List<Item> items;

    private Push pushWork;

    @Data
    public static class Item {
        private String itemId;
        private String workNo;
        private List<String> alias;
        private List<String> rids;
        private String title;
        /**
         * NotEmpty(message = "[items[$]content]不能为空")
         */
        private String content;
    }
}
