package com.ebridgevas.ss7interface;

import com.ebridge.billingplatform.inject.BillingPlatformInterfaceModule;
import com.ebridgevas.protocols.ss7.map.api.MAPDialog;
import com.ebridgevas.protocols.ss7.map.api.MAPMessage;
import com.ebridgevas.protocols.ss7.map.api.MAPProvider;
import com.ebridgevas.protocols.ss7.map.api.errors.MAPErrorMessage;
import com.ebridgevas.protocols.ss7.map.api.service.supplementary.*;

import com.ebridgevas.protocols.ss7.tcap.asn.comp.Problem;
import com.ebridgevas.servicemenutree.menu.ServiceMenuTree;
import com.ebridgevas.servicemenutree.menu.impl.DatabaseBackedServiceMenuTree;
import com.ebridgevas.ss7interface.processors.*;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author david@tekeshe.com
 *
 */
public class MAPServiceSupplementaryProcessor implements MAPServiceSupplementaryListener {

    private MAPProvider mapProvider;

    private ServiceMenuTree serviceMenuTree;

    public MAPServiceSupplementaryProcessor( MAPProvider mapProvider ) {

        this.mapProvider = mapProvider;
        Injector injector = Guice.createInjector(new BillingPlatformInterfaceModule());
//        billingPlatformInterface = injector.getInstance(BillingPlatformInterface.class);

        serviceMenuTree = new DatabaseBackedServiceMenuTree();
    }

    @Override
    public void onProcessUnstructuredSSRequest( ProcessUnstructuredSSRequest processUnstructuredSSRequest) {

        ProcessUnstructuredSSRequestProcessor.process(
                processUnstructuredSSRequest, mapProvider, serviceMenuTree);
    }

    @Override
    public void onMAPMessage( MAPMessage mapMessage ) {

        MAPMessageProcessor.process( mapMessage );
    }

    @Override
    public void onProcessUnstructuredSSResponse( ProcessUnstructuredSSResponse processUnstructuredSSResponse ) {

        ProcessUnstructuredSSResponseProcessor.process(processUnstructuredSSResponse);
    }

    @Override
    public void onUnstructuredSSNotifyRequest( UnstructuredSSNotifyRequest unstructuredSSNotifyRequest ) {

        UnstructuredSSNotifyRequestProcessor.process( unstructuredSSNotifyRequest );
    }

    @Override
    public void onUnstructuredSSNotifyResponse(UnstructuredSSNotifyResponse unstructuredSSNotifyResponse ) {

        UnstructuredSSNotifyResponseProcessor.process( unstructuredSSNotifyResponse );
    }

    @Override
    public void onUnstructuredSSRequest( UnstructuredSSRequest unstructuredSSRequest ) {

        UnstructuredSSRequestProcessor.process( unstructuredSSRequest );
    }

    @Override
    public void onUnstructuredSSResponse( UnstructuredSSResponse unstructuredSSResponse ) {

        UnstructuredSSResponseProcessor.process( unstructuredSSResponse, mapProvider );
    }

    @Override
    public void onRejectComponent( MAPDialog mapDialog, Long invokeId, Problem problem, boolean isLocalOriginated) {
        RejectComponentProcessor.process(mapDialog, invokeId, problem, isLocalOriginated);
    }

    @Override
    public void onInvokeTimeout(MAPDialog mapDialog, Long invokeId) {
        InvokeTimeoutProcessor.process(mapDialog, invokeId);
    }

    @Override
    public void onErrorComponent( MAPDialog mapDialog, Long invokeId, MAPErrorMessage mapErrorMessage ) {
        ErrorComponentProcessor.process(mapDialog, invokeId, mapErrorMessage);
    }
}
