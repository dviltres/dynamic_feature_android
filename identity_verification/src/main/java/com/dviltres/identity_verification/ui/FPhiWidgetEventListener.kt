package com.dviltres.identity_verification.ui

import android.util.Log
import com.facephi.fphiwidgetcore.IFPhiWidgetEventListener

/*
TODO: This class implements the IFPhiWidgetEventListener interface which is necessary to receive event from the widget
*/
class FPhiWidgetEventListener : IFPhiWidgetEventListener {
    override fun onEvent(time: Long, type: String, info: String) {
        Log.i("FPhiWidget", "onEvent: (" + time + "ms) " + type + " - " + info)
    }
}