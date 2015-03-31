package com.ebridgevas.ss7interface.processors;


import com.ebridgevas.protocols.ss7.map.api.MAPDialog;
import com.ebridgevas.protocols.ss7.tcap.asn.comp.Problem;
import org.apache.log4j.Logger;

/**
 * @author david@tekeshe.com
 *
 */
public class RejectComponentProcessor {

    private static Logger log = Logger.getLogger("");

    public static void process(MAPDialog mapDialog, Long invokeId, Problem problem, boolean isLocalOriginated) {
        log.debug("Component reject. map id : " + mapDialog.getLocalDialogId() );
    }
}
