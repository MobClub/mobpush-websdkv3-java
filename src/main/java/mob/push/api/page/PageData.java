package mob.push.api.page;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageData<T> {
    private int totalPages;
    private int total;
    private List<T> content;
}
