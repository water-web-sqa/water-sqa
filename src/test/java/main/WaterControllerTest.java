package main;

import junit.framework.TestSuite;
import org.apache.xmlbeans.impl.tool.XSTCTester;

public class WaterControllerTest extends XSTCTester.TestCase {
    // @Test: Được sử dụng để đánh dấu đây là một phương thức test.@Test(timeout=500)
    // Được sử dụng khi cần giới hạn thời gian thực thi của một phương thức. Nếu vượt quá thời này thì phương thức sẽ fail.

    public static void main(String[] args) {
        junit.textui.TestRunner.run(new TestSuite(WaterControllerTest.class));
    }
}
