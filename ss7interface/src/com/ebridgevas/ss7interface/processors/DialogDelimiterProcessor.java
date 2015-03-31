package com.ebridgevas.ss7interface.processors;


import com.ebridgevas.protocols.ss7.map.api.MAPDialog;
import org.apache.log4j.Logger;

/**
 * @author david@tekeshe.com
 *
 */
public class DialogDelimiterProcessor {

    private static Logger log = Logger.getLogger("DialogDelimiterProcessor.class");

    public static void process(MAPDialog mapDialog) {
        log.debug(String.format("onDialogDelimiter for DialogId=%d", mapDialog.getLocalDialogId()));
    }
}
