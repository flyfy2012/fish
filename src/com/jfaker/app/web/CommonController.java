/*
 *  Copyright 2014-2015 snakerflow.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.jfaker.app.web;

import java.util.Calendar;
import java.util.Date;

import com.jfinal.core.Controller;
import com.swust.utils.TimeExchange;

/**
 *  
 *   CommonController
*/



/**
 *  
 * @author Hmily
 *
 */
public abstract class CommonController extends Controller {

	protected int mCurrentTimeStamp = TimeExchange.getCurrentTimestamp();
	protected Date mCurrentDateTime = Calendar.getInstance().getTime();



	
}
