package example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum WeekEnum {
    /**
     *
     */
    MONDAY(1,"monday"),
    TUESDAY(2, "tuesday"),
    WEDNESDAY(3, "wednsday"),
    THURSDAY(4, "thursday"),
    FRIDAY(5, "friday"),
    SATURDAY(6, "saturday"),
    SUNDAY(0, "sunday");
    private final Integer value;
    private final String desc;

    private static final Map<WeekEnum, String> MAP = new EnumMap<>(WeekEnum.class);

    public static String getCode(WeekEnum weekEnum) {
        if (MAP.size() == 0) {
            synchronized (WeekEnum.class) {
                if (MAP.size() == 0) {
                    MAP.put(MONDAY, "星期一");
                    MAP.put(TUESDAY, "星期二");
                    MAP.put(WEDNESDAY, "星期三");
                    MAP.put(THURSDAY, "星期四");
                    MAP.put(FRIDAY, "星期五");
                    MAP.put(SATURDAY, "星期六");
                    MAP.put(SUNDAY, "星期天");
                }
            }
        }
        return MAP.get(weekEnum);
    }


}
