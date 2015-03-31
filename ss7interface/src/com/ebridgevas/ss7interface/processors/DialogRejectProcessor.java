package com.ebridgevas.ss7interface.processors;

import com.ebridgevas.protocols.ss7.map.api.MAPDialog;
import com.ebridgevas.protocols.ss7.map.api.dialog.MAPRefuseReason;
import com.ebridgevas.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import com.ebridgevas.protocols.ss7.tcap.asn.ApplicationContextName;
import org.apache.log4j.Logger;

/**
 * @author david@tekeshe.com
 *
 */
public class DialogRejectProcessor {

    private static Logger log = Logger.getLogger("DialogRejectProcessor.class");

    public static void process( MAPDialog mapDialog,
                                MAPRefuseReason refuseReason,
                                ApplicationContextName alternativeApplicationContext,
                                MAPExtensionContainer extensionContainer) {

        log.error(
                String.format(
                        "onDialogReject for " +
                                "DialogId=%d MAPRefuseReason=%s ApplicationContextName=%s MAPExtensionContainer=%s",
                        mapDialog.getLocalDialogId(), refuseReason, alternativeApplicationContext,
                        extensionContainer));
    }
}
