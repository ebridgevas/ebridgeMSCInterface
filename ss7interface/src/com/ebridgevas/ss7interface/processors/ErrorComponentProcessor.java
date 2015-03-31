package com.ebridgevas.ss7interface.processors;

import com.ebridgevas.protocols.ss7.map.api.MAPDialog;
import com.ebridgevas.protocols.ss7.map.api.errors.MAPErrorMessage;
import org.apache.log4j.Logger;

/**
 * @author david@tekeshe.com
 *
 */
public class ErrorComponentProcessor {

    private static Logger log = Logger.getLogger("ErrorComponentProcessor.class");

    public static void process(MAPDialog mapDialog, Long invokeId, MAPErrorMessage mapErrorMessage) {
        log.error(
                String.format(
                        "onErrorComponent for Dialog=%d and invokeId=%d MAPErrorMessage=%s",
                        mapDialog.getLocalDialogId(),
                        invokeId,
                        mapErrorMessage));
    }
}
