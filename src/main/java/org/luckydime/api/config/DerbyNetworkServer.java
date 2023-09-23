package org.luckydime.api.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.derby.drda.NetworkServerControl;

import java.io.PrintWriter;
import java.net.InetAddress;

/*
 * Either setting manually, programatically, or via properties file the system property, the network server didn't start
 * automatically as stated: https://db.apache.org/derby/docs/10.16/adminguide/radminconfigstartnetworkserver.html
 * Note: org.apache.derby.jdbc.EmbeddedDriver is not an option since it just supports one connection per time.
 */
@Slf4j
public class DerbyNetworkServer {

    public static void start() throws Exception {
        System.setProperty("derby.drda.startNetworkServer", "true");
        NetworkServerControl server = new NetworkServerControl(InetAddress.getByName("localhost"),1527);
        server.start(new PrintWriter(System.out));
        log.info("Is network server started={}", isServerStarted(server, 3));
    }

    // Source: https://db.apache.org/derby/docs/10.16/adminguide/tadminconfigverifyingstartup.html
    private static boolean isServerStarted(NetworkServerControl server, int ntries) {
        for (int i = 1; i <= ntries; i ++) {
            try {
                Thread.sleep(500);
                server.ping();
                return true;
            } catch (Exception e) {
                if (i == ntries) {
                    return false;
                }
            }
        }
        return false;
    }
}
