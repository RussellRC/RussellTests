package russell.tests;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

import org.apache.commons.lang3.RandomUtils;


public class WorkerRobot {

    public static void main(String[] args) throws Exception {
        doRobot();
    }

    public static void doRobot() throws Exception {
        final Robot robot = new Robot();
        while (true) {
            final Point mouseLoc = MouseInfo.getPointerInfo().getLocation();
            System.out.println(mouseLoc);
            robot.mouseMove(RandomUtils.nextInt(0, 1440), RandomUtils.nextInt(0, 900));
            System.out.println(mouseLoc);
            Thread.sleep(10000);
        }
    }
}
