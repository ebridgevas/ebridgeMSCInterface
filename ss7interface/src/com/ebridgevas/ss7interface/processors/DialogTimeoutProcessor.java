package com.ebridgevas.ss7interface.processors;

import com.ebridgevas.protocols.ss7.map.api.MAPDialog;
import org.apache.log4j.Logger;

/**
 * @author david@tekeshe.com
 *
 */
public class DialogTimeoutProcessor {

    private static Logger log = Logger.getLogger("DialogTimeoutProcessor.class");

    public static void process(MAPDialog mapDialog) {

        log.error( String.format("onDialogTimeout for DialogId=%d", mapDialog.getLocalDialogId()) );
    }
}
