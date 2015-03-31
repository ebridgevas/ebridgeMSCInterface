package com.ebridgevas.ss7interface.processors;

import com.ebridgevas.protocols.ss7.map.api.MAPDialog;
import com.ebridgevas.protocols.ss7.map.api.dialog.MAPAbortProviderReason;
import com.ebridgevas.protocols.ss7.map.api.dialog.MAPAbortSource;
import com.ebridgevas.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.apache.log4j.Logger;

/**
 * @author david@tekeshe.com
 *
 */
public class DialogProviderAbortProcessor {

    private static Logger log = Logger.getLogger("DialogProviderAbortProcessor.class");

    public static void process( MAPDialog mapDialog,
                                MAPAbortProviderReason abortProviderReason,
                                MAPAbortSource abortSource,
                                MAPExtensionContainer extensionContainer) {

        log.error(
                String.format(
                        "onDialogProviderAbort for " +
                                "DialogId=%d MAPAbortProviderReason=%s MAPAbortSource=%s MAPExtensionContainer=%s",
                        mapDialog.getLocalDialogId(), abortProviderReason, abortSource, extensionContainer));
    }
}
