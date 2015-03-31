package com.ebridgevas.ss7interface.processors;


import com.ebridgevas.protocols.ss7.map.api.service.supplementary.UnstructuredSSNotifyResponse;
import org.apache.log4j.Logger;

/**
 * @author david@tekeshe.com
 *
 */
public class UnstructuredSSNotifyResponseProcessor {

    private static Logger log = Logger.getLogger("UnstructuredSSNotifyResponseProcessor.class");

    public static void process(UnstructuredSSNotifyResponse unstructuredSSNotifyResponse) {

        // This error condition. Client should never receive the
        // UnstructuredSSNotifyRequestIndication
        log.error(
                String.format(
                        "onUnstructuredSSNotifyResponse for Dialog=%d and invokeId=%d",
                        unstructuredSSNotifyResponse.getMAPDialog().getLocalDialogId(),
                        unstructuredSSNotifyResponse.getInvokeId()));

    }
}
