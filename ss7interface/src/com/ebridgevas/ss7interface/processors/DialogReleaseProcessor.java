package com.ebridgevas.ss7interface.processors;

import com.ebridgevas.protocols.ss7.map.api.MAPDialog;
import org.apache.log4j.Logger;

/**
 * @author david@tekeshe.com
 *
 */
public class DialogReleaseProcessor {

    private static Logger log = Logger.getLogger("DialogReleaseProcessor.class");

    public static void process(MAPDialog mapDialog) {

        log.debug(String.format("onDialogResease for DialogId=%d", mapDialog.getLocalDialogId()));
    }
}
