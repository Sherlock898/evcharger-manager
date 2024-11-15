package com.noder.cargadorws.WebSocket;

import java.net.URI;
import java.util.Date;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.noder.cargadorws.Types.ChargerManager;
import com.noder.cargadorws.ocpp.messages.AuthorizeConf;
import com.noder.cargadorws.ocpp.messages.AuthorizeReq;
import com.noder.cargadorws.ocpp.messages.BootNotificationConf;
import com.noder.cargadorws.ocpp.messages.BootNotificationReq;
import com.noder.cargadorws.ocpp.messages.DataTransferConf;
import com.noder.cargadorws.ocpp.messages.DataTransferReq;
import com.noder.cargadorws.ocpp.messages.DiagnosticsStatusNotificationConf;
import com.noder.cargadorws.ocpp.messages.DiagnosticsStatusNotificationReq;
import com.noder.cargadorws.ocpp.messages.FirmwareStatusNotificationConf;
import com.noder.cargadorws.ocpp.messages.FirmwareStatusNotificationReq;
import com.noder.cargadorws.ocpp.messages.HeartbeatConf;
import com.noder.cargadorws.ocpp.messages.MeterValuesConf;
import com.noder.cargadorws.ocpp.messages.MeterValuesReq;
import com.noder.cargadorws.ocpp.messages.StatusNotificationConf;
import com.noder.cargadorws.ocpp.messages.StatusNotificationReq;
import com.noder.cargadorws.ocpp.messages.types.IdTagInfo;


public class OcppHandler extends TextWebSocketHandler {

	private final Gson gson = new Gson();
	private final int HEARTBEAT_INTERVAL_SECONDS = 30;
	private final ChargerManager chargerManager = ChargerManager.getInstance();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		URI uri = session.getUri();
		if(uri == null){return;}
		String uris = uri.toString();
		String[] uriParts = uris.split("/");
        String id = uriParts[uriParts.length - 1];

		// Add charger object to manager, does nothing if already created
		chargerManager.addCharger(id);
		// TODO: Preguntarle a geoffrey si guardamos id o el objeto entero, si es una referencia es un puntero no mas? super lijero lol
		session.getAttributes().put("chargerId", id);
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

		// Get charger
		String chargerId = session.getAttributes().get("chargerId").toString();
		if(chargerId == null){
			// TODO: Don't print, do logs
			System.err.println("Error getting charger id from session");
		}
	
		// ------------------------------
		// ---------- Payload -----------
		// ------------------------------
		JsonElement payload = call.get(3);
		switch (action) {
			case "Authorize":
			AuthorizeReq authorizeReq = gson.fromJson(payload, AuthorizeReq.class);
			// TODO: We are authorizing everything, is this the intended behaviour?
			IdTagInfo idTagInfo = new IdTagInfo(null, authorizeReq.idTag(), IdTagInfo.AuthorizationStatus.Accepted);
			sendCallResult(session, uniqueId, new AuthorizeConf(idTagInfo));
			break;

			case "BootNotification":
				BootNotificationReq bootNotificationReq = gson.fromJson(payload, BootNotificationReq.class);
				chargerManager.loadBootNotificationInfo(chargerId, bootNotificationReq.chargePointModel(),
									bootNotificationReq.chargePointVendor(),
									bootNotificationReq.chargeBoxSerialNumber(),
									bootNotificationReq.chargeBoxSerialNumber(),
									bootNotificationReq.firmwareVersion(),
									bootNotificationReq.iccid(),
									bootNotificationReq.imsi(),
									bootNotificationReq.meterSerialNumber(),
									bootNotificationReq.meterType());
				// TODO: Check when to not accept boot notifications
				sendCallResult(session, uniqueId, new BootNotificationConf(new Date(), HEARTBEAT_INTERVAL_SECONDS, BootNotificationConf.Status.Accepted));
				break;

			case "DataTransfer":
				DataTransferReq dataTransferReq = gson.fromJson(payload, DataTransferReq.class);
				DataTransferConf dataTransferConf = new DataTransferConf(DataTransferConf.DataTransferStatus.Rejected, null);
				// TODO: Handle DataTransfer
				sendCallResult(session, uniqueId, dataTransferConf);
				break;

			case "DiagnosticsStatusNotification":
				DiagnosticsStatusNotificationReq diagnosticsStatusNotificationReq = gson.fromJson(payload, DiagnosticsStatusNotificationReq.class);
				// TODO: Handle DiagnosticsStatusNotification
				chargerManager.updateDiagnosticsStatus(chargerId, diagnosticsStatusNotificationReq.status());
				sendCallResult(session, uniqueId, new DiagnosticsStatusNotificationConf());
				break;

			// This message is used to notify the status of the firmware update.
			case "FirmwareStatusNotification":
				FirmwareStatusNotificationReq firmwareStatusNotificationReq = gson.fromJson(payload, FirmwareStatusNotificationReq.class);
				// TODO: Handle FirmwareStatusNotification
				chargerManager.updateFirmwareStatus(chargerId, firmwareStatusNotificationReq.status());
				sendCallResult(session, uniqueId, new FirmwareStatusNotificationConf());
				break;
			
			// This message is to verify that the connection is active.
			case "Heartbeat":
				sendCallResult(session, uniqueId, new HeartbeatConf(new Date()));
				break;

			case "MeterValues":
				MeterValuesReq meterValuesReq = gson.fromJson(payload, MeterValuesReq.class);
				// TODO: Handle Metervalues
				// If connectorId is zero, then it belongs to the charger.
				chargerManager.addMeterValues(meterValuesReq.connectorId(), chargerId, meterValuesReq.meterValue());
				sendCallResult(session, uniqueId, new MeterValuesConf());
				break;

			/*case "StartTransaction":
				StartTransactionReq startTransactionReq = gson.fromJson(payload, StartTransactionReq.class);
				chargerManager.startTransaction(chargerId, startTransactionReq.connectorId(), startTransactionReq.meterStart(), startTransactionReq.timestamp());

				sendCallResult(session, uniqueId, new StartTransactionConf());
				break;*/

			case "StatusNotification":
				StatusNotificationReq statusNotificationReq = gson.fromJson(payload, StatusNotificationReq.class);
				chargerManager.updateChargerStatus(chargerId, statusNotificationReq.connectorId(), statusNotificationReq.status(), statusNotificationReq.errorCode());
				sendCallResult(session, uniqueId, new StatusNotificationConf());
				break;

			/*case "StopTransaction":
				StopTransactionReq stopTransactionReq = gson.fromJson(payload, StopTransactionReq.class);
				// TODO: Handle StopTransaction
				sendCallResult(session, uniqueId, new StopTransactionConf());
				break;*/
		}
	}

	// Send callResult
	private void sendCallResult(WebSocketSession session, String uniqueId, Object payload) {
		JsonArray jsonArray = new JsonArray();
		jsonArray.add(3);
		jsonArray.add(uniqueId);
		jsonArray.add(gson.toJsonTree(payload));
		try {
			session.sendMessage(new TextMessage(gson.toJson(jsonArray)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
