package com.ebridgevas.ss7interface.processors;


import com.ebridgevas.protocols.ss7.indicator.NatureOfAddress;
import com.ebridgevas.protocols.ss7.indicator.RoutingIndicator;
import com.ebridgevas.protocols.ss7.map.api.MAPException;
import com.ebridgevas.protocols.ss7.map.api.MAPProvider;
import com.ebridgevas.protocols.ss7.map.api.datacoding.CBSDataCodingScheme;
import com.ebridgevas.protocols.ss7.map.api.primitives.AddressNature;
import com.ebridgevas.protocols.ss7.map.api.primitives.ISDNAddressString;
import com.ebridgevas.protocols.ss7.map.api.primitives.NumberingPlan;
import com.ebridgevas.protocols.ss7.map.api.primitives.USSDString;
import com.ebridgevas.protocols.ss7.map.api.service.supplementary.MAPDialogSupplementary;
import com.ebridgevas.protocols.ss7.map.api.service.supplementary.ProcessUnstructuredSSRequest;
import com.ebridgevas.protocols.ss7.map.datacoding.CBSDataCodingSchemeImpl;
import com.ebridgevas.protocols.ss7.sccp.parameter.GlobalTitle;
import com.ebridgevas.protocols.ss7.sccp.parameter.SccpAddress;
import com.ebridgevas.protocols.ss7.tcap.asn.InvokeImpl;
import com.ebridgevas.protocols.ss7.tcap.asn.comp.Invoke;
import com.ebridgevas.servicemenutree.menu.ServiceMenuTree;
import org.apache.log4j.Logger;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author david@tekeshe.com
 *
 */
public class ProcessUnstructuredSSRequestProcessor {

    private static Logger log = Logger.getLogger("ProcessUnstructuredSSRequestProcessor.class");

    public static void process( ProcessUnstructuredSSRequest processUnstructuredSSRequest,
                                MAPProvider mapProvider,
                                ServiceMenuTree serviceMenuTree ) {

        log ( processUnstructuredSSRequest );
        try {

            long invokeId = processUnstructuredSSRequest.getInvokeId();
            log.debug("current invoke id : " + invokeId );
//            ++invokeId;

//            String input = "901 33495 " + processUnstructuredSSRequest.getMSISDNAddressString().getAddress() + " 80";

//            USSDString ussdString =
//                    mapProvider.getMAPParameterFactory()
//                            .createUSSDString( serviceMenuTree.process( input ).getShortMessage() );
            USSDString ussdString =
                    mapProvider.getMAPParameterFactory()
                            .createUSSDString( "Welcome to Telecel USSD <CR> 1. Balance <CR> 2. Buy a bundle" );

            // byte ussdDataCodingScheme = (byte) 0x0F;
//            CBSDataCodingScheme ussdDataCodingScheme = new CBSDataCodingSchemeImpl((byte)0x0F);

            CBSDataCodingScheme ussdDataCodingScheme = processUnstructuredSSRequest.getDataCodingScheme();
            MAPDialogSupplementary dialog = processUnstructuredSSRequest.getMAPDialog();

            SccpAddress localAddress = dialog.getRemoteAddress();
            SccpAddress remoteAddress = dialog.getLocalAddress();
            dialog.setLocalAddress(localAddress);
            dialog.setRemoteAddress(remoteAddress);

            log.debug("new invoke id : " + invokeId );
            dialog.setUserObject( invokeId );

            ISDNAddressString msisdn =
                    mapProvider.getMAPParameterFactory()
                            .createISDNAddressString(
                                    AddressNature.international_number,
                                    NumberingPlan.ISDN,
                                    processUnstructuredSSRequest.getMSISDNAddressString().getAddress());

            dialog.addUnstructuredSSRequest( ussdDataCodingScheme, ussdString, null, msisdn );
            log.debug("---> { UnstructuredSSRequest : { ussdDataCodingScheme : " + ussdDataCodingScheme +
                    ", ussdString : " + ussdString + ", invokeId : " + invokeId + "} }");

            dialog.setLocalAddress(
                    new SccpAddress(
                            RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE,
                            1216,
                            GlobalTitle.getInstance(0,
                                    com.ebridgevas.protocols.ss7.indicator.NumberingPlan.ISDN_TELEPHONY,
                                    NatureOfAddress.INTERNATIONAL,
                                    "263730100066"),
                            5));

            dialog.setRemoteAddress(
                    new SccpAddress(
                            RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE,
                            9,
                            GlobalTitle.getInstance(0,
                                    com.ebridgevas.protocols.ss7.indicator.NumberingPlan.ISDN_TELEPHONY,
                                    NatureOfAddress.INTERNATIONAL,
                                    "263730100040"),
                            5));

            dialog.send();

        } catch (MAPException e) {
            log.error("Error while sending UnstructuredSSRequest ", e);
        }
    }

    private static void log(ProcessUnstructuredSSRequest processUnstructuredSSRequest) {
        try {
            log.debug(
                    String.format(
                            "<-- ProcessUnstructuredSSRequest for DialogId=%d. Ussd String=%s, msisdn=%s",
                            processUnstructuredSSRequest.getMAPDialog().getLocalDialogId(),
                            processUnstructuredSSRequest.getUSSDString().getString(Charset.defaultCharset()),
                            processUnstructuredSSRequest.getMSISDNAddressString().getAddress()));
        } catch (MAPException e) {
            e.printStackTrace(); // TODO implement exception handler
        }
    }
}
