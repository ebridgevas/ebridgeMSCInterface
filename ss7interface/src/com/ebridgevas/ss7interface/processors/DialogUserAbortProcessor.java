package com.ebridgevas.ss7interface.processors;

import com.ebridgevas.protocols.ss7.map.api.MAPDialog;
import com.ebridgevas.protocols.ss7.map.api.dialog.MAPUserAbortChoice;
import com.ebridgevas.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.apache.log4j.Logger;

/**
 * @author david@tekeshe.com
 *
 */
public class DialogUserAbortProcessor {

    private static Logger log = Logger.getLogger("DialogUserAbortProcessor.class");

    public static void process( MAPDialog mapDialog,
                                MAPUserAbortChoice userReason,
                                MAPExtensionContainer extensionContainer) {

        log.error(
                String.format(
                        "onDialogUserAbort for DialogId=%d MAPUserAbortChoice=%s MAPExtensionContainer=%s",
                        mapDialog.getLocalDialogId(), userReason, extensionContainer));
    }
}
