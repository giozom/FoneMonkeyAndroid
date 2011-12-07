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
//  OnSeekBarChange.aj
//  FoneMonkey
//
//  Created by Shaun Terry (shaun.terry@gorillalogic.com) on 09/27/11.
//  Copyright 2011 Gorilla Logic, Inc. All rights reserved.
//
package com.gorillalogic.fonemonkey.aspects;
 
import org.aspectj.lang.Signature;
import org.aspectj.lang.JoinPoint;

import android.view.View;
import android.widget.*;

import com.gorillalogic.fonemonkey.*;
import com.gorillalogic.fonemonkey.automators.AutomationManager;
 
aspect OnSeekBarChange
{
    pointcut captureOnSeekBarChange() : (execution(* onStopTrackingTouch(SeekBar)));

	before(): captureOnSeekBarChange()
	{
		
		
		if (AutomationManager.record(Operation.Select.toString(), thisJoinPoint.getArgs())) {
			return;
		}
		
		
		
		
		if (Recorder.isPlayingBack()) return;

		View view = (View) thisJoinPoint.getArgs()[0];
		int progress = (Integer) thisJoinPoint.getArgs()[1];

		WidgetReference wref = WidgetReference.create(view);
		wref.operation = Operation.Slide;
		wref.sliderValue = progress;

		Recorder.addEvent(wref);

        Log.log("OnSeekBarChange: " + wref);
    }
}

