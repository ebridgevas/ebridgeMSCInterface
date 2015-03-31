package com.ebridgevas.ss7interface.processors;

import com.ebridgevas.protocols.ss7.map.api.MAPException;
import com.ebridgevas.protocols.ss7.map.api.MAPProvider;
import com.ebridgevas.protocols.ss7.map.api.datacoding.CBSDataCodingScheme;
import com.ebridgevas.protocols.ss7.map.api.primitives.AddressNature;
import com.ebridgevas.protocols.ss7.map.api.primitives.AddressString;
import com.ebridgevas.protocols.ss7.map.api.primitives.NumberingPlan;
import com.ebridgevas.protocols.ss7.map.api.primitives.USSDString;
import com.ebridgevas.protocols.ss7.map.api.service.supplementary.MAPDialogSupplementary;
import com.ebridgevas.protocols.ss7.map.api.service.supplementary.UnstructuredSSResponse;
import com.ebridgevas.protocols.ss7.map.datacoding.CBSDataCodingSchemeImpl;
import org.apache.log4j.Logger;

import java.nio.charset.Charset;

/**
 * @author david@tekeshe.com
 *
 */
public class UnstructuredSSResponseProcessor {

    private static Logger log = Logger.getLogger("UnstructuredSSResponseProcessor.class");

    public static void process( UnstructuredSSResponse unstructuredSSResponse, MAPProvider mapProvider) {

        log ( unstructuredSSResponse );

        try {

            USSDString ussdString =
                            mapProvider.getMAPParameterFactory()
                                    .createUSSDString(
                                            "Your balance is 500");

            //  byte ussdDataCodingScheme = (byte) 0x0F;
            CBSDataCodingScheme ussdDataCodingScheme = new CBSDataCodingSchemeImpl((byte) 0x0F);
            MAPDialogSupplementary dialog = unstructuredSSResponse.getMAPDialog();

            AddressString msisdn =
                            mapProvider.getMAPParameterFactory()
                                            .createAddressString( AddressNature.international_number,
                                                                  NumberingPlan.ISDN, "31628838002");

            dialog.addProcessUnstructuredSSResponse( ((Long) dialog.getUserObject()).longValue(),
                                                     ussdDataCodingScheme,
                                                     ussdString );

            dialog.close(false);

        } catch (MAPException e) {
            log.error("Error while sending UnstructuredSSRequest ", e);
        }
    }

    private static void log(UnstructuredSSResponse unstructuredSSResponse) {

        try {
            log.debug(
                    String.format(
                            "onUnstructuredSSResponseIndication for DialogId=%d Ussd String=%s",
                            unstructuredSSResponse.getMAPDialog().getLocalDialogId(),
                            unstructuredSSResponse.getUSSDString().getString(Charset.defaultCharset())));
        } catch (MAPException e) {
            e.printStackTrace();  // TODO implement exception handler
        }
    }
}
