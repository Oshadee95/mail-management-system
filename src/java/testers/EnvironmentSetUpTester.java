/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testers;

import servers.CreateServer;

/**
 *
 * @author RED-HAWK
 */
public class EnvironmentSetUpTester {

    public static void main(String args[]) {

        try {

            CreateServer cs = new CreateServer();
            cs.setUpEnv();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
