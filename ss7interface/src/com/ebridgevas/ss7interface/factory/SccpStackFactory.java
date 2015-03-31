package com.ebridgevas.ss7interface.factory;

import com.ebridgevas.protocols.ss7.m3ua.impl.M3UAManagementImpl;
import com.ebridgevas.protocols.ss7.sccp.RemoteSignalingPointCode;
import com.ebridgevas.protocols.ss7.sccp.RemoteSubSystem;
import com.ebridgevas.protocols.ss7.sccp.impl.RemoteSignalingPointCodeImpl;
import com.ebridgevas.protocols.ss7.sccp.impl.RemoteSubSystemImpl;
import com.ebridgevas.protocols.ss7.sccp.impl.SccpStackImpl;
import com.ebridgevas.protocols.ss7.sccp.impl.router.Mtp3Destination;
import com.ebridgevas.protocols.ss7.sccp.impl.router.Mtp3ServiceAccessPoint;
import com.ebridgevas.ss7interface.model.SS7Config;
import org.apache.log4j.Logger;

/**
 * @author david@tekeshe.com
 *
 * SCCP - Stream Connection Control Protocol Initializer.
 *
 */
public class SccpStackFactory {

    private M3UAManagementImpl m3UAManagement;

    private SS7Config localDpcConfig;
    private SS7Config remoteDpcConfig;

    private static Logger log = Logger.getLogger("SCCPFactory.class");

    public SccpStackFactory( SS7Config localDpcConfig,
                             SS7Config remoteDpcConfig,
                             M3UAManagementImpl m3UAManagement ) {

        this.localDpcConfig = localDpcConfig;
        this.remoteDpcConfig = remoteDpcConfig;
        this.m3UAManagement = m3UAManagement;
    }

    public SccpStackImpl instance() throws Exception {

        log.debug("Initializing SCCP Stack ....");

        SccpStackImpl sccpStack = new SccpStackImpl("MapLoadServerSccpStack");
        sccpStack.setMtp3UserPart( 1, m3UAManagement );

        sccpStack.start();
        sccpStack.removeAllResourses();

        RemoteSignalingPointCode rspc
                = new RemoteSignalingPointCodeImpl(remoteDpcConfig.getDestinationPointCode(), 0, 0);
        RemoteSubSystem rss
                = new RemoteSubSystemImpl( remoteDpcConfig.getDestinationPointCode(),
                remoteDpcConfig.getSubSystemNumber(), 0, false);

        sccpStack.getSccpResource().addRemoteSpc(0, rspc.getRemoteSpc(), rspc.getRemoteSpcFlag(), rspc.getMask());
        sccpStack.getSccpResource().addRemoteSsn(
                                                    0,
                                                    rss.getRemoteSpc(),
                                                    rss.getRemoteSsn(),
                                                    rss.getRemoteSsnFlag(),
                                                    rss.getMarkProhibitedWhenSpcResuming());

        Mtp3ServiceAccessPoint sap
                = new Mtp3ServiceAccessPoint(
                            1,
                            localDpcConfig.getDestinationPointCode(),
                            localDpcConfig.getNetworkIndicator());

        Mtp3Destination destination
                = new Mtp3Destination(
                            remoteDpcConfig.getDestinationPointCode(),
                            remoteDpcConfig.getDestinationPointCode(),
                            0, 255, 255);

        sccpStack
                .getRouter()
                .addMtp3ServiceAccessPoint(1, sap.getMtp3Id(), sap.getOpc(), sap.getNi());

        sccpStack
                .getRouter()
                .addMtp3Destination(
                        1,
                        1,
                        destination.getFirstDpc(),
                        destination.getLastDpc(),
                        destination.getFirstSls(),
                        destination.getLastSls(),
                        destination.getSlsMask());

        log.info("SCCP Stack Initialized.");

        return sccpStack;
    }
}
