package com.ebridgevas.ss7interface.processors;

import com.ebridgevas.protocols.ss7.map.api.MAPDialog;
import org.apache.log4j.Logger;

/**
 * @author david@tekeshe.com
 *
 */
public class InvokeTimeoutProcessor {

    private static Logger log = Logger.getLogger("InvokeTimeoutProcessor.class");

    public static void process(MAPDialog mapDialog, Long invokeId) {

        log.error(
                String.format(
                        "onInvokeTimeout for Dialog=%d and invokeId=%d", mapDialog.getLocalDialogId(), invokeId));
    }
}
