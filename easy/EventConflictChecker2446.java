
/**
 * LeetCode Problem:
 * https://leetcode.cn/problems/determine-if-two-events-have-conflict/
 * 
 * Problem Description:
 * 判断两个事件是否存在冲突
 * 给你两个字符串数组 event1 和 event2 ，表示发生在同一天的两个闭区间时间段事件，其中：
 * 
 * event1 = [startTime1, endTime1] 且
 * event2 = [startTime2, endTime2]
 * 事件的时间为有效的 24 小时制且按 HH:MM 格式给出。
 * 
 * 当两个事件存在某个非空的交集时（即，某些时刻是两个事件都包含的），则认为出现 冲突 。
 * 
 * 如果两个事件之间存在冲突，返回 true ；否则，返回 false 。
 * 
 *
 * Example:
 * 输入：event1 = ["01:15","02:00"], event2 = ["02:00","03:00"]
 * 输出：true
 * 解释：两个事件在 2:00 出现交集。
 * 
 * 输入：event1 = ["01:00","02:00"], event2 = ["01:20","03:00"]
 * 输出：true
 * 解释：两个事件的交集从 01:20 开始，到 02:00 结束。
 * 
 * 输入：event1 = ["10:00","11:00"], event2 = ["14:00","15:00"]
 * 输出：false
 * 解释：两个事件不存在交集。
 * 
 * Solution Approach:
 * 当两个事件不存在冲突的充要条件是：一个事件的结束时间早于另一个事件的开始时间，可以直接用字符串的比较判断两个事件是否存在冲突。
 * 
 * Time Complexity: O(n), Space Complexity: O(n)
 */
public class LC_2446_DetermineIfTwoEventsHaveConflict {
    public static void main(String[] args) {
        String[] event3 = new String[] { "01:15", "02:00" };
        String[] event4 = new String[] { "02:00", "03:00" };

        String[] event5 = new String[] { "01:00", "02:00" };
        String[] event6 = new String[] { "01:20", "03:00" };

        String[] event7 = new String[] { "14:13", "22:08" };
        String[] event8 = new String[] { "02:40", "08:08" };

        String[] event9 = new String[] { "10:00", "11:00" };
        String[] event10 = new String[] { "14:00", "15:00" };

        String[] event1 = new String[] { "10:13", "13:02" };
        String[] event2 = new String[] { "13:17", "21:38" };

        LC_2446_DetermineIfTwoEventsHaveConflict instance = new LC_2446_DetermineIfTwoEventsHaveConflict();
        boolean result = instance.haveConflict(event1, event2);
        System.out.println(result);
    }

    public boolean haveConflict(String[] event1, String[] event2) {
        return !(event1[1].compareTo(event2[0]) < 0 || event2[1].compareTo(event1[0]) < 0);
    }
}