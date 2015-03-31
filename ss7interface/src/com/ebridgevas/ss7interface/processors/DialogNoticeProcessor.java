package com.ebridgevas.ss7interface.processors;


import com.ebridgevas.protocols.ss7.map.api.MAPDialog;
import com.ebridgevas.protocols.ss7.map.api.dialog.MAPNoticeProblemDiagnostic;
import org.apache.log4j.Logger;

/**
 * @author david@tekeshe.com
 *
 */
public class DialogNoticeProcessor {

    private static Logger log = Logger.getLogger("DialogNoticeProcessor.class");

    public static void process(MAPDialog mapDialog, MAPNoticeProblemDiagnostic noticeProblemDiagnostic) {

        log.error(
                String.format(
                        "onDialogNotice for DialogId=%d MAPNoticeProblemDiagnostic=%s ",
                        mapDialog.getLocalDialogId(), noticeProblemDiagnostic));
    }
}
