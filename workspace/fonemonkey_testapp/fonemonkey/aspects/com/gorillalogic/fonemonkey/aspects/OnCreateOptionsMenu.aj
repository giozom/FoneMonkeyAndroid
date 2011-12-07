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
//  OnCreateOptionsMenu.aj
//  FoneMonkey
//
//  Created by Stu Stern (shaun.terry@gorillalogic.com) on 11/04/11.
//  Copyright 2011 Gorilla Logic, Inc. All rights reserved.
//
package com.gorillalogic.fonemonkey.aspects;

import android.app.Activity;
import android.view.Menu;

import com.gorillalogic.fonemonkey.ActivityManager;
import com.gorillalogic.fonemonkey.Log;
import com.gorillalogic.fonemonkey.Operation;
import com.gorillalogic.fonemonkey.Recorder;
import com.gorillalogic.fonemonkey.WidgetReference;
 
aspect OnCreateOptionsMenu
{
    pointcut captureOnCreateOptionsMenu() : (execution(* onCreateOptionsMenu(Menu)));

    before(): captureOnCreateOptionsMenu()
 	{   
		Menu menu = (Menu) thisJoinPoint.getArgs()[0];
		ActivityManager.setCurrentMenu(menu);
    }
}
