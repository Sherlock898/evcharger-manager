package com.noder.cargadorws.ocpp.messages;
import com.noder.cargadorws.ocpp.messages.types.ConfigurationKey;

public record GetConfigurationConf(ConfigurationKey[] configurationKey, String[] unknownKey) {}