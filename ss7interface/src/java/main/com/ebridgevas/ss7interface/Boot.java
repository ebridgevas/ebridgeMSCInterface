package com.ebridgevas.ss7interface;

import com.ebridgevas.ss7interface.dao.SS7ConfigDAO;
import com.ebridgevas.ss7interface.model.SS7Config;

import org.apache.log4j.Logger;

/**
 * david@tekeshe.com
 */
public class Boot {

    private static Logger log = Logger.getLogger(Boot.class);

    public static void main(String[] args) throws Exception {

        if ( args.length < 2 ) {
            System.out.println("Usage java Boot <localGlobalTitle> <remoteGlobalTitle>");
            System.exit( 1 );
        }

        String localGlobalTitle = args[ 0 ];
        String remoteGlobalTitle = args [ 1 ];

        log.info("################################################################");
        log.info("#                  eBridge SS7 Disruptor                        ");
        log.info("#                       ver 05.2014                             ");
        log.info("#    " + localGlobalTitle + " -> " + remoteGlobalTitle           );
        log.info("################################################################");

        SS7Config localDpcConfig = new SS7ConfigDAO().findById( localGlobalTitle );
        SS7Config remoteDpcConfig = new SS7ConfigDAO().findById(remoteGlobalTitle );
        log.info("local  dpc : " + localDpcConfig );
        log.info("remote dpc : " + remoteDpcConfig );
        SS7Interface.start(localDpcConfig, remoteDpcConfig);
    }
}
