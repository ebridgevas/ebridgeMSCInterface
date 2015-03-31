package com.ebridgevas.ss7interface.factory;

import com.ebridgevas.protocols.ss7.map.MAPStackImpl;
import com.ebridgevas.protocols.ss7.map.api.MAPDialogListener;
import com.ebridgevas.protocols.ss7.map.api.MAPProvider;
import com.ebridgevas.protocols.ss7.map.api.service.supplementary.MAPServiceSupplementaryListener;
import com.ebridgevas.protocols.ss7.sccp.impl.SccpStackImpl;
import com.ebridgevas.ss7interface.MAPDialogProcessor;
import com.ebridgevas.ss7interface.MAPServiceSupplementaryProcessor;
import com.ebridgevas.ss7interface.model.SS7Config;
import org.apache.log4j.Logger;

/**
 * @author david@tekeshe.com
 *
 * MAP - Mobile Application Part Initializer.
 *
 * SS7 Level 7. TCAP MAP - Transaction Capabilities Part - Mobile Application Part.
 *
 */
public class MAPStackManager {

    private SccpStackImpl sccpStack;
    private SS7Config localDpcConfig;

    private static Logger log = Logger.getLogger("MAPStackManager.class");

    public MAPStackManager( SccpStackImpl sccpStack,
                            SS7Config localDpcConfig) {

        this.sccpStack = sccpStack;
        this.localDpcConfig = localDpcConfig;
    }

    public void start() throws Exception {

        log.info("Initializing MAP Stack ....");

        MAPStackImpl mapStack
                = new MAPStackImpl( "eBridge MAP Stack",
                this.sccpStack.getSccpProvider(),
                localDpcConfig.getSubSystemNumber() );

        MAPProvider mapProvider = mapStack.getMAPProvider();

        MAPDialogListener mapDialogProcessor = new MAPDialogProcessor();

        mapProvider.addMAPDialogListener( mapDialogProcessor  );

        MAPServiceSupplementaryListener mapServiceSupplementaryProcessor
                = new MAPServiceSupplementaryProcessor( mapProvider );

        mapProvider.getMAPServiceSupplementary().addMAPServiceListener( mapServiceSupplementaryProcessor );

        mapProvider.getMAPServiceSupplementary().acivate();

        mapStack.start();

        log.info("MAP Stack Initialized.");
    }
}
