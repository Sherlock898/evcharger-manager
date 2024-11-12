package com.noder.cargadorws.WebSocket;

import java.util.Date;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.noder.cargadorws.ocpp.messages.AuthorizeConf;
import com.noder.cargadorws.ocpp.messages.AuthorizeReq;
import com.noder.cargadorws.ocpp.messages.BootNotificationConf;
import com.noder.cargadorws.ocpp.messages.BootNotificationReq;
import com.noder.cargadorws.ocpp.messages.types.IdTagInfo;

public class OcppHandler extends TextWebSocketHandler {

	private final Gson gson = new Gson();
	private final int HEARTBEAT_INTERVAL_SECONDS = 30;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {

	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		// Calls message structure:
		// [2, "UniqueId", "action", {payload}]
		// Parse call
		JsonArray call;
		try {
			call = JsonParser.parseString(message.getPayload()).getAsJsonArray();
		} catch (JsonParseException e) {
			System.err.println("Error parsing JSON: " + e.getMessage());
			return;
		}

		// Check for message integrity: 4 fields, message id == 2
		if (call.size() != 4) {
			System.err.println("Error in message length");
			return;
		}

		int messageId;
		try {
			messageId = call.get(0).getAsInt();
		} catch (Exception e) {
			System.err.println("Error parsing JSON: " + e.getMessage());
			return;
		}
		if (messageId != 2) {
			// TODO: handle call error
			System.err.println("Wrong message id");
			return;
		}

		String uniqueId;
		try {
			uniqueId = call.get(1).getAsString();
		} catch (Exception e) {
			System.err.println("Couldn't parse to string: " + call.get(1));
			return;
		}
		if (uniqueId == null) {
			System.err.println("Null uniqueId");
			return;
		}

		String action;
		try {
			action = call.get(2).getAsString();
		} catch (Exception e) {
			System.err.println("Error parsing action " + call.get(2));
			return;
		}
		if (action == null) {
			System.err.println("Null action");
			return;
		}
	
		// ------------------------------
		// ---------- Payload -----------
		// ------------------------------
		JsonElement payload = call.get(3);
		switch (action) {
			case "Authorize":
				AuthorizeReq authorizeReq = gson.fromJson(payload, AuthorizeReq.class);
				// TODO: Handle authorizations. Rigth now it just accepts everything (not the correct behaviour)
				sendCallResult(session, messageId, new AuthorizeConf(new IdTagInfo(null, null, IdTagInfo.AuthorizationStatus.Accepted)));
				break;

			case "BootNotification":
				BootNotificationReq bootNotificationReq = gson.fromJson(payload, BootNotificationReq.class);
				// TODO: Handle BootNotification
				sendCallResult(session, messageId, new BootNotificationConf(new Date(), HEARTBEAT_INTERVAL_SECONDS, BootNotificationConf.Status.Accepted));
				break;

			/*case "DataTransfer":
				DataTransferReq dataTransferReq = gson.fromJson(payload, DataTransferReq.class);
				sendCallResult(session, messageId, new DataTransferConf());
				break;

			case "DiagnosticsStatusNotification":
				DiagnosticsStatusNotificationReq diagnosticsStatusNotificationReq = gson.fromJson(payload, DiagnosticsStatusNotificationReq.class);
				sendCallResult(session, messageId, new DiagnosticsStatusNotificationConf());
				break;

			case "FirmwareStatusNotification":
				FirmwareStatusNotificationReq firmwareStatusNotificationReq = gson.fromJson(payload, FirmwareStatusNotificationReq.class);
				sendCallResult(session, messageId, new FirmwareStatusNotificationConf());
				break;

			case "Heartbeat":
				HeartbeatReq heartbeatReq = gson.fromJson(payload, HeartbeatReq.class);
				sendCallResult(session, messageId, new HeartbeatConf());
				break;

			case "MeterValues":
				MeterValuesReq meterValuesReq = gson.fromJson(payload, MeterValuesReq.class);
				sendCallResult(session, messageId, new MeterValuesConf());
				break;

			case "StartTransaction":
				StartTransactionReq startTransactionReq = gson.fromJson(payload, StartTransactionReq.class);
				sendCallResult(session, messageId, new StartTransactionConf());
				break;

			case "StatusNotification":
				StatusNotificationReq statusNotificationReq = gson.fromJson(payload, StatusNotificationReq.class);
				sendCallResult(session, messageId, new StatusNotificationConf());
				break;

			case "StopTransaction":
				StopTransactionReq stopTransactionReq = gson.fromJson(payload, StopTransactionReq.class);
				sendCallResult(session, messageId, new StopTransactionConf());
				break;*/
		}
	}

	// Send callResult
	private void sendCallResult(WebSocketSession session, int messageId, Object payload) {
		JsonArray jsonArray = new JsonArray();
		jsonArray.add(3);
		jsonArray.add(messageId);
		jsonArray.add(gson.toJsonTree(payload));
		try {
			session.sendMessage(new TextMessage(gson.toJson(jsonArray)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
