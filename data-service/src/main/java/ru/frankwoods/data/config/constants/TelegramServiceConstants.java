package ru.frankwoods.data.config.constants;

public interface TelegramServiceConstants {

    String VALUE_PREFIX = "${telegram.service.";
    String VALUE_SUFFIX = "}";

    String VALUE_TELEGRAM_SERVICE_SERVER = VALUE_PREFIX + "server" + VALUE_SUFFIX;
    String VALUE_TELEGRAM_SERVICE_TRIGGERS_ENDPOINT = VALUE_PREFIX + "triggers-endpoint" + VALUE_SUFFIX;
}