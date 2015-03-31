package com.ebridgevas.ss7interface.factory;


import com.ebridgevas.protocols.api.Management;
import com.ebridgevas.protocols.ss7.m3ua.ExchangeType;
import com.ebridgevas.protocols.ss7.m3ua.Functionality;
import com.ebridgevas.protocols.ss7.m3ua.IPSPType;
import com.ebridgevas.protocols.ss7.m3ua.M3UAManagement;
import com.ebridgevas.protocols.ss7.m3ua.impl.M3UAManagementImpl;
import com.ebridgevas.protocols.ss7.m3ua.impl.parameter.ParameterFactoryImpl;
import com.ebridgevas.protocols.ss7.m3ua.parameter.RoutingContext;
import com.ebridgevas.protocols.ss7.m3ua.parameter.TrafficModeType;
import com.ebridgevas.ss7interface.model.SS7Config;
import org.apache.log4j.Logger;

/**
 * @author david@tekeshe.com
 *
 * M3UA Initializer.
 * MTP Level 3: M3UA - Message Transfer Part 3 User Adaptation.
 */
public class M3UAFactory {

    private static Logger log = Logger.getLogger("M3UAFactory.class");


    private SS7Config localDpcConfig;
    private SS7Config remoteDpcConfig;
    private Management sctpManagement;

    public M3UAFactory( SS7Config localDpcConfig,
                        SS7Config remoteDpcConfig,
                        Management sctpManagement) {

        this.localDpcConfig = localDpcConfig;
        this.remoteDpcConfig = remoteDpcConfig;
        this.sctpManagement = sctpManagement;
    }

    public M3UAManagementImpl instance() throws Exception {

        log.info("Initializing M3UA Stack ....");

        M3UAManagementImpl m3UAManagement = new M3UAManagementImpl("Server");
        m3UAManagement.setTransportManagement( sctpManagement );
        m3UAManagement.start();
        m3UAManagement.removeAllResources();

        /* Step 1 : Create Application Server */

        ParameterFactoryImpl factory = new ParameterFactoryImpl();
        RoutingContext rc = factory.createRoutingContext(new long[] { localDpcConfig.getRoutingContext() });
        TrafficModeType trafficModeType = factory.createTrafficModeType(TrafficModeType.Loadshare);

        /* TODO verify the correct value for minAspActiveForLoadbalance */
        int minAspActiveForLoadBalance = 1;

        log.info("Functionality.AS : " + Functionality.AS );

        m3UAManagement.createAs( "RAS1",
                Functionality.AS,
                ExchangeType.SE,
                IPSPType.SERVER,
                rc,
                trafficModeType,
                minAspActiveForLoadBalance,
                factory.createNetworkAppearance(localDpcConfig.getNetworkAppearance()));

        /* Step 2 : Create ASP */
        m3UAManagement.createAspFactory( "RASP1", localDpcConfig.getServerName() );

        /* Step3 : Assign ASP to AS */
        m3UAManagement.assignAspToAs("RAS1", "RASP1");

        /* Step 4: Add Route. Remote point code is 2 */
        // int dpc, int opc, int si, String asName
        m3UAManagement.addRoute( remoteDpcConfig.getDestinationPointCode(),
                                 localDpcConfig.getDestinationPointCode(),
                                 3,
                                "RAS1");

        log.info("M3UA Stack Initialized.");

        return m3UAManagement;
    }
}
