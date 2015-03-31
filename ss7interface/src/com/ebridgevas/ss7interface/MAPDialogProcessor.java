package com.ebridgevas.ss7interface;

import com.ebridgevas.protocols.ss7.map.api.*;
import com.ebridgevas.protocols.ss7.map.api.dialog.*;
import com.ebridgevas.protocols.ss7.map.api.primitives.*;
import com.ebridgevas.protocols.ss7.tcap.asn.ApplicationContextName;
import com.ebridgevas.ss7interface.processors.*;
import org.apache.log4j.Logger;

/**
 * @author david@tekeshe.com
 *
 */
public class MAPDialogProcessor implements MAPDialogListener {

    private static Logger log = Logger.getLogger(MAPDialogProcessor.class);

    @Override
    public void onDialogAccept( MAPDialog mapDialog, MAPExtensionContainer extensionContainer ) {
        DialogAcceptProcessor.process(mapDialog, extensionContainer);
    }

    @Override
    public void onDialogClose( MAPDialog mapDialog ) {
        DialogCloseProcessor.process(mapDialog);
    }

    @Override
    public void onDialogDelimiter( MAPDialog mapDialog ) {
        DialogDelimiterProcessor.process(mapDialog);
    }

    @Override
    public void onDialogNotice(MAPDialog mapDialog, MAPNoticeProblemDiagnostic noticeProblemDiagnostic) {
        DialogNoticeProcessor.process(mapDialog, noticeProblemDiagnostic);
    }

    @Override
    public void onDialogProviderAbort( MAPDialog mapDialog,
                                       MAPAbortProviderReason abortProviderReason,
                                       MAPAbortSource abortSource,
                                       MAPExtensionContainer extensionContainer ) {

        DialogProviderAbortProcessor.process(mapDialog, abortProviderReason, abortSource, extensionContainer);
    }

    @Override
    public void onDialogReject( MAPDialog mapDialog,
                                MAPRefuseReason refuseReason,
                                ApplicationContextName alternativeApplicationContext,
                                MAPExtensionContainer extensionContainer) {

        DialogRejectProcessor.process(mapDialog, refuseReason, alternativeApplicationContext, extensionContainer );
    }

    @Override
    public void onDialogRelease(MAPDialog mapDialog) {
        DialogReleaseProcessor.process( mapDialog );
    }

    @Override
    public void onDialogRequest( MAPDialog mapDialog,
                                 AddressString destinationReference,
                                 AddressString originatingReference,
                                 MAPExtensionContainer extensionContainer) {

        DialogRequestProcessor.process(mapDialog, destinationReference, originatingReference, extensionContainer );
    }

    @Override
    public void onDialogRequestEricsson( MAPDialog mapDialog,
                                         AddressString destinationReference,
                                         AddressString originatingReference,
                                         IMSI imsi,
                                         AddressString vlr) {

        DialogRequestEricssonProcessor.process(mapDialog, destinationReference, originatingReference, imsi, vlr );
    }

    @Override
    public void onDialogTimeout( MAPDialog mapDialog ) {
        DialogTimeoutProcessor.process ( mapDialog );
    }

    @Override
    public void onDialogUserAbort( MAPDialog mapDialog,
                                   MAPUserAbortChoice userReason,
                                   MAPExtensionContainer extensionContainer) {

        DialogUserAbortProcessor.process( mapDialog, userReason, extensionContainer );
    }
}
