package easy;

/**
 * 题目链接：https://leetcode.cn/problems/determine-if-two-events-have-conflict/
 * 
 * 解题思路: 双指针
 */
public class EventConflictChecker2446 {
    public boolean haveConflict(String[] event1, String[] event2) {
        return !(event1[1].compareTo(event2[0]) < 0 || event2[1].compareTo(event1[0]) < 0);
    }

    public static void main(String[] args) {
        // String[] event3 = new String[] { "01:15", "02:00" };
        // String[] event4 = new String[] { "02:00", "03:00" };

        // String[] event5 = new String[] { "01:00", "02:00" };
        // String[] event6 = new String[] { "01:20", "03:00" };

        // String[] event7 = new String[] { "14:13", "22:08" };
        // String[] event8 = new String[] { "02:40", "08:08" };

        // String[] event9 = new String[] { "10:00", "11:00" };
        // String[] event10 = new String[] { "14:00", "15:00" };

        String[] event1 = new String[] { "10:13", "13:02" };
        String[] event2 = new String[] { "13:17", "21:38" };

        EventConflictChecker2446 instance = new EventConflictChecker2446();
        boolean result = instance.haveConflict(event1, event2);
        System.out.println(result);
    }
}