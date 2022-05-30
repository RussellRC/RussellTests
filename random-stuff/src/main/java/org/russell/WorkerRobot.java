package org.russell;

import java.awt.*;
import java.util.Random;


public class WorkerRobot {

    public static void main(String[] args) throws Exception {
        doRobot();
    }

    public static void doRobot() throws Exception {
        Random random = new Random();
        final Robot robot = new Robot();
        while (true) {
            final Point mouseLoc = MouseInfo.getPointerInfo().getLocation();
            System.out.println(mouseLoc);
            robot.mouseMove(random.nextInt(1440), random.nextInt(900));
            System.out.println(mouseLoc);
            Thread.sleep(10000);
        }
    }
}
