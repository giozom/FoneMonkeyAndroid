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
//  OnOptionsItemSelected.aj
//  FoneMonkey
//
//  Created by Shaun Terry (shaun.terry@gorillalogic.com) on 09/20/11.
//  Copyright 2011 Gorilla Logic, Inc. All rights reserved.
//
package com.gorillalogic.fonemonkey.aspects;
 
import android.view.MenuItem;
import android.app.Activity;
import android.widget.*;

import com.gorillalogic.fonemonkey.*;
import com.gorillalogic.fonemonkey.automators.AutomationManager;
 
aspect OnOptionsItemSelected
{
    pointcut captureOnOptionItemSelected() : (execution(* onOptionsItemSelected(MenuItem)));

    before(): captureOnOptionItemSelected()
	{
    	
    	if (AutomationManager.record("Click", thisJoinPoint.getArgs())) {
    		return;
    	}
    	
		if (Recorder.isPlayingBack()) return;

		MenuItem item = (MenuItem) thisJoinPoint.getArgs()[0];
		Object target = thisJoinPoint.getTarget();

		if (!(target instanceof Activity)) return;

		Activity a = (Activity) target;

		WidgetReference wref = WidgetReference.create((Activity)target);
		wref.operation = Operation.MenuClick;

		Recorder.addMenuEvent(wref, item);

        Log.log("OnOptionsItemSelected: " + wref);
    }
}

