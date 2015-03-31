package com.ebridgevas.ss7interface.processors;

import com.ebridgevas.protocols.ss7.map.api.MAPDialog;
import com.ebridgevas.protocols.ss7.map.api.primitives.AddressString;
import com.ebridgevas.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.apache.log4j.Logger;

/**
 * @author david@tekeshe.com
 *
 */
public class DialogRequestProcessor {

    private static Logger log = Logger.getLogger("DialogRequestProcessor.class");

    public static void process( MAPDialog mapDialog,
                                AddressString destinationReference,
                                AddressString originatingReference,
                                MAPExtensionContainer extensionContainer ) {

        log.debug(
                String.format(
                        "onDialogRequest for " +
                                "DialogId=%d DestinationReference=%s OriginReference=%s MAPExtensionContainer=%s",
                        mapDialog.getLocalDialogId(),
                        destinationReference,
                        originatingReference,
                        extensionContainer));

        mapDialog.getApplicationContext().getOID();
        mapDialog.getApplicationContext().getApplicationContextName();
        mapDialog.getApplicationContext().getApplicationContextVersion();

        mapDialog.getRemoteDialogId();
        Object userObject = mapDialog.getUserObject();
        mapDialog.getService();
        mapDialog.getTCAPMessageType();
        mapDialog.getState();

//        mapDialog.send();
    }
}
