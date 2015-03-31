package com.ebridgevas.ss7interface.processors;

import com.ebridgevas.protocols.ss7.map.api.MAPDialog;
import org.apache.log4j.Logger;

/**
 * @author david@tekeshe.com
 *
 */
public class DialogCloseProcessor {

    private static Logger log = Logger.getLogger("DialogCloseProcessor.class");

    public static void process(MAPDialog mapDialog) {
        log.debug(String.format("onDialogClose for Dialog=%d", mapDialog.getLocalDialogId()));
    }
}
