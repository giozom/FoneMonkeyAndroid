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
//  OnBackPressed.aj
//  FoneMonkey
//
//  Created by Stu Stern (stu.stern@gorillalogic.com) on 11/03/11.
//  Copyright 2011 Gorilla Logic, Inc. All rights reserved.
//

package com.gorillalogic.fonemonkey.aspects;

import android.view.View;
import android.widget.ListView;

import com.gorillalogic.fonemonkey.Log;
import com.gorillalogic.fonemonkey.Operation;
import com.gorillalogic.fonemonkey.Recorder;
import com.gorillalogic.fonemonkey.WidgetReference;
	 
	aspect OnBackPressed
	{
	    pointcut captureOnBackPressed() : (execution(* onBackPressed()));
	
	    before(): captureOnBackPressed()
		{
			if (Recorder.isPlayingBack()) return;

			WidgetReference wref = new WidgetReference();
			wref.operation = Operation.Back;

			Recorder.addEvent(wref);

	        Log.log("OnBackPressed");
	    }
	}
	


