package com.ebridgevas.ss7interface.processors;

import com.ebridgevas.protocols.ss7.map.api.MAPDialog;
import com.ebridgevas.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.apache.log4j.Logger;

/**
 * @author david@tekeshe.com.
 */
public class DialogAcceptProcessor {

    private static Logger log = Logger.getLogger("DialogAcceptProcessor.class");

    public static void process(MAPDialog mapDialog, MAPExtensionContainer extensionContainer) {
        log.debug(
                String.format(
                        "onDialogAccept for DialogId=%d MAPExtensionContainer=%s",
                            mapDialog.getLocalDialogId(),
                            extensionContainer) );
    }
}
