package com.ebridgevas.ss7interface.factory;

import com.ebridgevas.protocols.api.IpChannelType;
import com.ebridgevas.protocols.api.Management;
import com.ebridgevas.protocols.sctp.ManagementImpl;
import com.ebridgevas.ss7interface.model.SS7Config;

import java.util.logging.Logger;

/**
 * @author david@tekeshe.com
 *
 * SCTP Initializer
 * - MTP Level 2: SCTP - Stream Control Transmission Protocol.
 *
 */
public class SCTPManagementFactory {

    private SS7Config localDpcConfig;
    private SS7Config remoteDpcConfig;

    private static Logger log = Logger.getLogger("SCTPFactory.class");

    public SCTPManagementFactory( SS7Config localDpcConfig, SS7Config remoteDpcConfig ) {
        this.localDpcConfig = localDpcConfig;
        this.remoteDpcConfig = remoteDpcConfig;
    }

    public Management instance( ) throws Exception {

        log.info("Initializing SCTP Stack ....");

        Management sctpManagement = new ManagementImpl( "Server" );
        sctpManagement.setSingleThread( true );
        sctpManagement.setConnectDelay( 10000 );
        sctpManagement.start();
        sctpManagement.removeAllResources();

        IpChannelType ipChannelType = IpChannelType.getInstance(localDpcConfig.getIpChannelType());

        /* 1. Create SCTP Server */
        sctpManagement.addServer(
                                    localDpcConfig.getServerName(),
                                    localDpcConfig.getIpAddress(),
                                    localDpcConfig.getPort(),
                                    ipChannelType,
                                    null );

        /* 2. Create SCTP Server Association */
        sctpManagement.addServerAssociation(
                                            remoteDpcConfig.getIpAddress(),
                                            remoteDpcConfig.getPort(),
                                            localDpcConfig.getServerName(),
                                            localDpcConfig.getAssociationName(),
                                            ipChannelType );

        /* 3. Start Server */
        sctpManagement.startServer( localDpcConfig.getServerName() );

        log.info("SCTP Stack Initialized.");

        return sctpManagement;
    }
}
