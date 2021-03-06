/* This file is part of FoneMonkey.

    FoneMonkey is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FoneMonkey is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with FoneMonkey.  If not, see <http://www.gnu.org/licenses/>.  */
//
//  OnTabChange.aj
//  FoneMonkey
//
//  Created by Shaun Terry (shaun.terry@gorillalogic.com) on 09/06/11.
//  Copyright 2011 Gorilla Logic, Inc. All rights reserved.
//
package com.gorillalogic.fonemonkey.aspects;
 
import android.view.View;
import android.widget.TabHost;

import com.gorillalogic.fonemonkey.*;
import com.gorillalogic.fonemonkey.automators.AutomationManager;
 
aspect OnTabChanged
{
    pointcut captureOnTabChanged() : (execution(* onTabChanged(String)));

    before(): captureOnTabChanged()
	{
//		String tabId = (String) thisJoinPoint.getArgs()[0];
//
//		if (AutomationManager.recordTab("Click", tabId)) {
//			return;
//		}
//
//		if (Recorder.isPlayingBack()) return;
//
//		Object target = thisJoinPoint.getTarget();
//
//		WidgetReference wref = WidgetReference.createForTab(tabId);
//
//		Recorder.addEvent(wref);
//
//        Log.log("OnTabChanged: " + wref);
    }
}

