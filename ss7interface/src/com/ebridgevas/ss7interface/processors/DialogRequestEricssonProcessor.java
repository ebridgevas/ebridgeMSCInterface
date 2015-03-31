package com.ebridgevas.ss7interface.processors;

import com.ebridgevas.protocols.ss7.map.api.MAPDialog;
import com.ebridgevas.protocols.ss7.map.api.primitives.AddressString;
import com.ebridgevas.protocols.ss7.map.api.primitives.IMSI;
import org.apache.log4j.Logger;

/**
 * @author david@tekeshe.com
 *
 */
public class DialogRequestEricssonProcessor {

    private static Logger log = Logger.getLogger("DialogRequestEricssonProcessor.class");

    public static void process( MAPDialog mapDialog,
                                AddressString destinationReference,
                                AddressString originatingReference,
                                IMSI imsi,
                                AddressString vlr) {

        log.debug(
                String.format(
                        "onDialogRequestEricsson for " +
                                "DialogId=%d DestinationReference=%s OriginReference=%s ",
                        mapDialog.getLocalDialogId(),
                        destinationReference,
                        originatingReference));
    }
}
