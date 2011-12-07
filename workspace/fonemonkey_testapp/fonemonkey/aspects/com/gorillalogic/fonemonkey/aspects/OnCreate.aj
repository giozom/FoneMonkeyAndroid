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
//  OnCreate.aj
//  FoneMonkey
//
//  Created by Shaun Terry (shaun.terry@gorillalogic.com) on 09/08/11.
//  Copyright 2011 Gorilla Logic, Inc. All rights reserved.
//
package com.gorillalogic.fonemonkey.aspects;

import java.util.*;
 
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.*;
import android.widget.*;
import android.gesture.*;

import com.gorillalogic.fonemonkey.*;
 
aspect OnCreate
{
	String firstActivity;

    pointcut captureOnCreate() : (execution(* onCreate(Bundle)));

    before(): captureOnCreate()
	{
		Object target = thisJoinPoint.getTarget();

		if (firstActivity == null) firstActivity = target.getClass().getName();
	}

    after(): captureOnCreate()
	{
		Object target = thisJoinPoint.getTarget();

		if (!(target instanceof Activity)) return;

		Activity a = (Activity) target;

		if (firstActivity.equals(target.getClass().getName()))
		{
			Recorder.setViewRoot((Activity) target);
			WidgetReference.setContext((Activity) target);
		}

		ActivityManager.addActivity(a);

		FunctionalityAdder.walkTree(a.getWindow().getDecorView().getRootView());
    }
    
    pointcut captureCreate() : (call(* create()));

    after() returning (AlertDialog d): captureCreate()
	{
    	ActivityManager.setCurrentDialog(d);
	}    
}

