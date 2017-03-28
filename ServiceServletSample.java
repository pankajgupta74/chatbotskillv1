package ai.api.examples.web;

/***********************************************************************************************************************
 *
 * API.AI Java SDK - client-side libraries for API.AI
 * =================================================
 *
 * Copyright (C) 2017 by Speaktoit, Inc. (https://www.speaktoit.com)
 * https://www.api.ai
 *
 ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 ***********************************************************************************************************************/

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ai.api.GsonFactory;
import ai.api.model.AIResponse;
import ai.api.web.AIServiceServlet;
import api.consumer.service.CTPServiceAction;


/**
 * Servlet implementation class AIServiceServlet
 */
@WebServlet(urlPatterns = {"/ai"}, initParams = {
    @WebInitParam(name = ServiceServletSample.PARAM_API_AI_KEY,
        value = "1bea0a262c924f43bf87a88e4a69cd94")})
public class ServiceServletSample extends AIServiceServlet {
  private static final long serialVersionUID = 1L;
  private final static Gson GSON = GsonFactory.getDefaultFactory().getGson();
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
    	String serviceResp = null;
//      AIResponse aiResponse = request(request.getParameter("query"), request.getSession());
      String policyNo = request.getParameter("query");
      CTPServiceAction ctpserviceAction = new CTPServiceAction();
      serviceResp =  ctpserviceAction.getOTP(policyNo);
      String temp = "{    \"id\": \"80078a42-0aa6-465e-8e6e-b98b417f8987\",    \"timestamp\": \"2017-03-15T11:05:15.643Z\",    \"result\": {        \"source\": \"domains\",        \"resolvedQuery\": \"hello\",        \"action\": \"smalltalk.greetings\",        \"parameters\": {            \"simplified\": \"hello\"        },        \"metadata\": {},        \"fulfillment\": {            \"speech\": \""+serviceResp+"\"        },        \"score\": 1    },    \"status\": {        \"code\": 200,        \"errorType\": \"success\"    },    \"sessionId\": \"352D8F08F18C302AFEC1FE07CC7FD477\"}";
      AIResponse aiResponse = GSON.fromJson(temp, AIResponse.class);
      response.setContentType("text/plain");
      response.getWriter().append(aiResponse.getResult().getFulfillment().getSpeech());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
