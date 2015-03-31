package com.ebridgevas.ss7interface.processors;

import com.ebridgevas.protocols.ss7.map.api.MAPDialog;
import com.ebridgevas.protocols.ss7.map.api.MAPException;
import com.ebridgevas.protocols.ss7.map.api.MAPMessage;
import com.ebridgevas.protocols.ss7.map.api.MAPMessageType;
import com.ebridgevas.protocols.ss7.map.api.datacoding.CBSDataCodingScheme;
import com.ebridgevas.protocols.ss7.map.api.primitives.ISDNAddressString;
import com.ebridgevas.protocols.ss7.map.api.primitives.USSDString;
import com.ebridgevas.protocols.ss7.map.api.service.supplementary.MAPDialogSupplementary;
import com.ebridgevas.protocols.ss7.map.api.service.supplementary.ProcessUnstructuredSSRequest;
import com.ebridgevas.protocols.ss7.map.api.service.supplementary.UnstructuredSSRequest;
import com.ebridgevas.protocols.ss7.map.api.service.supplementary.UnstructuredSSResponse;
import com.ebridgevas.protocols.ss7.map.primitives.USSDStringImpl;
import com.ebridgevas.protocols.ss7.map.service.supplementary.ProcessUnstructuredSSResponseImpl;
import com.ebridgevas.protocols.ss7.map.service.supplementary.UnstructuredSSRequestImpl;
import com.ebridgevas.protocols.ss7.map.service.supplementary.UnstructuredSSResponseImpl;
import com.ebridgevas.protocols.ss7.tcap.api.tc.dialog.Dialog;
import com.ebridgevas.protocols.ss7.tcap.asn.InvokeImpl;
import com.ebridgevas.protocols.ss7.tcap.asn.comp.Invoke;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLStreamException;
import java.awt.*;

/**
 * @author david@tekeshe.com
 *
 */
public class MAPMessageProcessor {

    private static Logger log = Logger.getLogger("MAPMessageProcessor.class");

    public static void process( MAPMessage mapMessage ) {

//        long invokeId = mapMessage.getInvokeId();
        MAPDialog mapDialog = mapMessage.getMAPDialog();
        mapDialog.getLocalDialogId();
        mapDialog.getApplicationContext().getApplicationContextName();
        mapDialog.getLocalAddress().getGlobalTitle();
        mapDialog.getLocalAddress().getSignalingPointCode();

        MAPMessageType messageType = mapMessage.getMessageType();
        messageType.toString();
//        int operationCode = mapMessage.getOperationCode();


        try {

            USSDString ussdStr = null;
            byte[] data = null;

            log.info("message type : " + mapMessage.getMessageType() );

            switch (mapMessage.getMessageType()) {
                case processUnstructuredSSRequest_Request:
                    ProcessUnstructuredSSRequest processUnstructuredSSRequest = (ProcessUnstructuredSSRequest) mapMessage;
                    CBSDataCodingScheme cbsDataCodingScheme =  processUnstructuredSSRequest.getDataCodingScheme();

                    log.info("Received ProcessUnstructuredSSRequestIndication USSD String="
                                + processUnstructuredSSRequest.getUSSDString().getString(null));
                    log.debug(  "application Context : " +
                                processUnstructuredSSRequest
                                    .getMAPDialog()
                                        .getApplicationContext()
                                            .getApplicationContextName()
                                                .getApplicationContextCode() );
//                        session.setAttribute("ProcessUnstructuredSSRequest_InvokeId",
//                                processUnstructuredSSRequest.getInvokeId());

                    long invokeId = processUnstructuredSSRequest.getInvokeId();
                    ussdStr =
                        new USSDStringImpl(
                            "Telecel VAS Service:\n 1. Balance\n 2. Data Bundle", cbsDataCodingScheme, null);
//                    UnstructuredSSRequest unstructuredSSRequestIndication
//                            = new UnstructuredSSRequestImpl( cbsDataCodingScheme, ussdStr, null, null);

                    MAPDialogSupplementary dialog = processUnstructuredSSRequest.getMAPDialog(); // .addProcessUnstructuredSSResponse(invokeId,cbsDataCodingScheme,ussdStr);
//                    Dialog copy = new Dialog(DialogType.CONTINUE, original.getId(), null, null,unstructuredSSRequestIndication);
                    ISDNAddressString msisdn = null;
                    dialog.addUnstructuredSSRequest(cbsDataCodingScheme, ussdStr, null, msisdn);

//                    data = factory.serialize(copy);

//                    response.getOutputStream().write(data);
//                    response.flushBuffer();

                    Invoke invoke = new InvokeImpl();

                    invoke.setInvokeId( invokeId );
                    dialog.sendInvokeComponent(invoke);
                    int operationCode = processUnstructuredSSRequest.getOperationCode();
                    log.debug("operationCode : " + operationCode );
                    break;
//                        default:
//                            // This is error. If its begin it should be only Process
//                            // Unstructured SS Request
//                            logger.error("Received Dialog BEGIN but message is not ProcessUnstructuredSSRequestIndication. Message="
//                                    + mapMessage);
//                            break;
//                    }

                case unstructuredSSRequest_Response:
                    UnstructuredSSResponse unstructuredSSResponse = (UnstructuredSSResponseImpl) mapMessage;
                    cbsDataCodingScheme =  unstructuredSSResponse.getDataCodingScheme();
//                    invokeId = (Long) session.getAttribute("ProcessUnstructuredSSRequest_InvokeId");
                    invokeId = unstructuredSSResponse.getInvokeId();
                    log.info("Received UnstructuredSSResponse USSD String="
                            + unstructuredSSResponse.getUSSDString().getString(null)
                            + " invokeId=" + invokeId);

                    ussdStr = new USSDStringImpl("Thank You!", cbsDataCodingScheme, null);
                    ProcessUnstructuredSSResponseImpl processUnstructuredSSResponseIndication = new ProcessUnstructuredSSResponseImpl(
                            cbsDataCodingScheme, ussdStr);
                    processUnstructuredSSResponseIndication.setInvokeId(invokeId);

//                    Dialog copy1 = new Dialog(DialogType.END, original.getId(), processUnstructuredSSResponseIndication);
//
//                    data = factory.serialize(copy1);
//
//                    response.getOutputStream().write(data);
//                    response.response();

//                    try {
//
//                    } catch (Exception e) {
//                        session.invalidate();
//                        logger.error("Error while invalidating HttpSession=" + session.getId());
//                    }
                    break;
//                        default:
//                            // This is error. If its begin it should be only Process
//                            // Unstructured SS Request
//                            logger.error("Received Dialog CONTINUE but message is not UnstructuredSSResponseIndication. Message="
//                                    + mapMessage);
//                            break;
            }

//                    break;

//            }

        } catch (MAPException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        log.info("process()");
    }
}
