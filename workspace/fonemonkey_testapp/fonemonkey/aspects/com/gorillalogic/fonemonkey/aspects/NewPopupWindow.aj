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
//  NewPopupWindow.aj
//  FoneMonkey
//
//  Created by Shaun Terry (shaun.terry@gorillalogic.com) on 09/28/11.
//  Copyright 2011 Gorilla Logic, Inc. All rights reserved.
//
package com.gorillalogic.fonemonkey.aspects;
 
import org.aspectj.lang.Signature;
import org.aspectj.lang.JoinPoint;

import android.view.View;
import android.widget.*;

import com.gorillalogic.fonemonkey.*;
 
aspect NewPopupWindow
{
	// This will capture only instances where PopupWindow has been subclassed.
	// If a PopupupWindow is instantiated directly it must be registered with
	// the framework:
	//				FoneMonkey.register(popupWindow);
	//
    pointcut captureNewPopupWindow() : (execution(*.new(..)) && !within(com.gorillalogic.fonemonkey..*));

    after(): captureNewPopupWindow()
	{
		// Must capture all new popups even if playing back
		//if (Recorder.isPlayingBack()) return;

		Object target = thisJoinPoint.getThis();

		if (target != null && target instanceof PopupWindow)
		{
			Log.log("Registering " + target);

			PopupWindow pw = (PopupWindow) target;

			FoneMonkey.register((PopupWindow) target);
		}
    }
}

